/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package influenzer.bot.generic.controle;

import java.awt.Robot;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * foi só uma classe de testes, não usei oficialmente no projeto
 *
 */
public class Selenium {

    public static WebDriver driver = null;
    static Robot root = null;

    public Selenium(String navegador) {
        File file = null;
        try {
            switch (navegador) {
                case "IE": {
                    file = new File("/home/alessiomjr/Downloads/automatizador_testes_java/IEDriverServer_x64_3.12.0");
                    System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    break;
                }
                case "mozilla": {
                    file = new File("geckodriver");
                    System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability("marionette", true);
                    //                capabilities.setCapability(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, true);
                    driver = new FirefoxDriver();
                    break;
                }
                case "chrome":
                    file = new File("chromedriver");
                    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
//                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//                capabilities.setCapability(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, true);
//                driver.manage().window().maximize();
//                driver.get(url);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fechaNavegador(boolean fechaTudo) {
        if (fechaTudo) {
            driver.quit();
        } else {
            driver.close();
        }

    }

    public void abreNavegador(String url, boolean maximizaTela) {
        if (maximizaTela) {
            driver.manage().window().maximize();
        }
        driver.get(url);
    }

    public String pagAtual() {
        return driver.getCurrentUrl();
    }

    public String pageSource() {
        return driver.getPageSource();
    }
}
