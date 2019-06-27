package influenzer.bot.generic.view;

import influenzer.bot.generic.controle.AbstractBot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractCrawler {
    protected WebDriver driver = null;
    protected int tempoEspera = 10;
    protected int tempoEsperaReduzido = 2;
       
    protected static final Logger LOGGER = LogManager.getLogger(AbstractBot.class);
        
    public AbstractCrawler(WebDriver driver, int tempoEspera) {
        this.driver = driver;
        this.tempoEspera = tempoEspera;
    }

    public AbstractCrawler(WebDriver driver) {
        this.driver = driver;
    }

    public void acessaSite(String url) {
        driver.get(url);
    }

    protected void clicaEmBotaoPorXPath(String xpathBotao) {
        try {
            (new WebDriverWait(driver, tempoEsperaReduzido))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(xpathBotao))).click();

        } catch (RuntimeException exception) {
            LOGGER.error("Erro ao clicar em botao por xpath");
            LOGGER.error(exception);
        }
    }

    protected WebElement encontraComponentePeloNome(String nome) {
        return (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(nome)));
    }

    protected WebElement encontraComponentePorXPath(String nome) {

        return (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(nome)));
    }

    protected void espera(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AbstractCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            LOGGER.error(ex);
        }

        return imgComoVetorBytes;
    }
}
