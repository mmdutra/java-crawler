package influenzer.bot.generic.controle;

import influenzer.bot.generic.view.IView;
import influenzer.bot.generic.view.ViewConsole;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBot {

    protected WebDriver driver = null;
    protected int tempoEspera = 30;
    protected int tempoEsperaReduzido = 3;
    protected Robot robot;
    protected IView view;

    protected static final Logger LOGGER = LogManager.getLogger(AbstractBot.class);
    
    public AbstractBot() {

        driver = new ChromeDriver(configuracoesExtrasChrome());
//        driver = new FirefoxDriver()
        inicializaRobot();
        view = new ViewConsole(true);
    }

    public void terminar() {
        driver.close();
        driver.quit();
    }

    /**
     * executa o que voce quer que o bot faca majoritariarmente
     */
    public abstract void executa();

    public void testes() {
    }

    //kjhhgc
    
    private void inicializaRobot() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            LOGGER.fatal(ex);
        }
    }

    protected void clicaEmBotaoPorXPath(String xpathBotao) {
        try {
            (new WebDriverWait(driver, tempoEsperaReduzido))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(xpathBotao))).click();

        } catch (RuntimeException exception) {
            view.msgErro("Erro ao clicar em botao por xpath");
            view.msgErro(exception);
        }
    }

    protected void clicaEmBotaoPorCssSelector(String xpathBotao) {
        try {
            (new WebDriverWait(driver, tempoEsperaReduzido))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .cssSelector(xpathBotao))).click();
        } catch (RuntimeException exception) {
            view.msgErro("Erro ao clicar em botao por xpath");
            view.msgErro(exception);
        }
    }

    protected List<WebElement> retornaListaDeElementos(String componenteBuscado) {
        throw new UnsupportedOperationException("Ainda nao esta feito ,tenho que pensar numa maneira eficiente de fazer uma unica funcao que"
                + " aceita multiplos parametros estaticos do By.algumaFormaDeAcesso");
    }

    protected void mexeMouseAleatoriamente() {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        robot.mouseMove(screenSize.width / 2, screenSize.height / 2);
//        robot.mouseMove(screenSize.width / 3, screenSize.height / 3);
//        robot.mouseMove(screenSize.width / 2, screenSize.height / 2);
        robot.mouseMove((int) (Math.random() * screenSize.width), (int) (Math.random() * screenSize.height));
        robot.mouseMove(screenSize.width/2, screenSize.width/2); // vai para o centro
        robot.mouseMove((int) (Math.random() * screenSize.width), (int) (Math.random() * screenSize.height));
        robot.mouseMove((int) (Math.random() * screenSize.width), (int) (Math.random() * screenSize.height));

    }

    public AbstractBot(String navegador, int tempoEspera, int tempoEsperaReduzido) {
        this.tempoEspera = tempoEspera;
        this.tempoEsperaReduzido = tempoEsperaReduzido;
        switch (navegador) {
            case "Chrome":
                driver = new ChromeDriver(configuracoesExtrasChrome());
                //driver.sw
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
        }
        inicializaRobot();
    }

    protected WebElement encontraComponentePeloNome(String nome) {
        return (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(nome)));
    }

    public byte[] tiraFotoDaTela(WebElement elemento) {
        byte[] imgComoVetorBytes = null;
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File fileTemp = new File("imgTemp.png");

        try {
            if (fileTemp.exists()) {
                fileTemp.delete();
            }
            BufferedImage image = ImageIO.read(file);
            Point p = elemento.getLocation();
            BufferedImage elImg = image.getSubimage(p.getX(), p.getY(), elemento.getSize().getWidth(),
                    elemento.getSize().getHeight());
            ImageIO.write(elImg, "png", file);

            FileUtils.copyFile(file, new File("imgTemp.png"));

            InputStream in = new FileInputStream(file);
            imgComoVetorBytes = new byte[(int) file.length()];
            in.read(imgComoVetorBytes);

        } catch (IOException ex) {
            view.msgErro(ex);
        }

        return imgComoVetorBytes;
    }

    private DesiredCapabilities configuracoesExtrasMozzila() {
        File file = new File("geckodriver");
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        capabilities.setCapability(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, true);

//        Proxy proxy = new Proxy();
//        proxy.setProxyType(Proxy.ProxyType.AUTODETECT);
////        proxy.setHttpProxy("webproxy:80");
////        proxy.setSslProxy("webproxy:80");
////proxy.setNoProxy("localhost, 123.34.54.*, andsoon.com"); DO NOT USE THIS
//        capabilities.setCapability(CapabilityType.PROXY, proxy);
//
//        FirefoxProfile profile = new FirefoxProfile();
////        profile.setPreference("network.proxy.no_proxies_on", "localhost, 123.34.54.*, andsoon.com"); // INSTEAD use this one
//        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return capabilities;
    }

    public void acessaSite(String url) {
        driver.get(url);
    }

    protected void espera(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            view.msgErro(ex);
        }

    }

    protected String navegaPaginaERetornaTitulo(String pageSource) {
        Document doc = Jsoup.parse(pageSource);
        Elements content = doc.getElementsByTag("title");
        String titulo = null;
        for (Element element : content) {
            titulo = element.text();
        }
        return titulo;
    }

    protected static final ChromeOptions configuracoesExtrasChrome() {
        File file = new File("chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return options;
    }

}
