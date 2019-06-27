package influenzer.bot.cetecop.view;

import influenzer.bot.cetecop.model.Problema;
import influenzer.bot.cetecop.model.TestCase;
import influenzer.bot.generic.view.AbstractCrawler;
import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TheHuxleyCrawler extends AbstractCrawler {
   
    public TheHuxleyCrawler(WebDriver driver, int tempoEspera) {
        super(driver, tempoEspera);
    }

    public TheHuxleyCrawler(WebDriver driver) {
        super(driver);
    }
    
    public static void main(String[] args) {
        WebDriver drive = new ChromeDriver(configuracoesExtrasChrome());
        TheHuxleyCrawler t = new TheHuxleyCrawler(drive);
        drive.get("https://www.thehuxley.com/problem/"+1022);
        Problema p = t.pegaDadosProblema();
    }
    
    
    public Problema pegaDadosProblema(){
        
        String nmproblema = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[2]/div[1]/div[1]/div[2]/h3"))).getText();
        
        String description = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/ng-transclude/div[1]/div[1]/div[2]"))).getText();

        String input = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/ng-transclude/div[1]/div[2]/div"))).getText();        

        String output = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/ng-transclude/div[1]/div[3]/div"))).getText();        
       
        Problema p = new Problema();
        p.setDescricao(description);
        p.setIdEvento(null);
        p.setInput(input);
        p.setOutput(output);
        
        return p;
    }
    
    public String pegaDescricao(){
        String description = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/ng-transclude/div[1]/div[1]/div[2]"))).getText();
        return description;
    }
    
    public String pegaNomeAutor(){
        //autor
        String nome = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[2]/div[2]/div[1]/div[2]/p[1]/a"))).getText();
        return nome;
    }
    
    public int pegaTimeLimit(){
         String timelimit = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[2]/div[2]/div[1]/div[1]/p[1]"))).getText();
        String tl = timelimit.substring(timelimit.length()-2, timelimit.length()-1);
        return Integer.parseInt(tl);       
    }
    
    public TestCase pegaTestCaseEntrada(){
        String entrada = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/ng-transclude/div[1]/div[4]/div/div/div[1]/div"))).getText();
        TestCase test = new TestCase();
        test.setCaso("entrada");
        test.setConteudo(entrada);
        return test;
    }
    
    public TestCase pegaTestCaseSaida(){
        String saida = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/ng-transclude/div[1]/div[4]/div/div/div[2]/div"))).getText();
        TestCase test = new TestCase();
        test.setCaso("saída");
        test.setConteudo(saida);
        return test;
    }
    
    public String pegaCategoria(){
        String classificacao = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[2]/div[2]/div[1]/div[1]/p[2]"))).getText();
        return classificacao;
    }
    
    public void pegaDificuldade(){
        String dificuldade = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[2]/div[1]/div[1]/div[3]"))).getText();
        
    }
    
    protected static final ChromeOptions configuracoesExtrasChrome() {
        File file = new File("chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return options;
    }
    
    
    public Problema relacionarNmProblema(Problema problema){
        Problema p = null;
        String description = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/ng-transclude/div[1]/div[1]/div[2]"))).getText();
        if(description.equals(problema.getDescricao())){
            String nmproblema = (new WebDriverWait(driver, tempoEspera))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[2]/div[1]/div[1]/div[2]/h3"))).getText();
            p = problema;
            p.setNmproblema(nmproblema);
        }
        return p;
    }
}
