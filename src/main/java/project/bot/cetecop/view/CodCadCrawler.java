package influenzer.bot.cetecop.view;

import influenzer.bot.cetecop.model.TestCase;
import influenzer.bot.generic.view.AbstractCrawler;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CodCadCrawler extends AbstractCrawler {
    
    public CodCadCrawler(WebDriver driver, int tempoEspera) {
        super(driver, tempoEspera);
    }

    public CodCadCrawler(WebDriver driver) {
        super(driver);
    }

    public String pegaNomeProblema(){
        return (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.container > div > h1"))).getText();
    }
    
    public String pegaDadosProblema(int i) {
        String descricao = (new WebDriverWait(driver, tempoEspera))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/p["+i+"]"))).getText();
        if(!descricao.contains("Subtask") && !descricao.contains("Pontuação"))
            return "<p>" + descricao + "</p>";
        else 
            return "";
    }

    public TestCase pegaTestCaseEntrada(int i) {
        String entrada = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/table/tbody/tr[" + i + "]/td[1]"))).getText();
        TestCase test = new TestCase();
        test.setCaso("entrada");
        test.setConteudo(entrada);
        return test;
    }

    public TestCase pegaTestCaseSaida(int i) {
        String saida = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/table/tbody/tr[" + i + "]/td[2]"))).getText();
        TestCase test = new TestCase();
        test.setCaso("saída");
        test.setConteudo(saida);
        return test;
    }

    public String pegaCategoria() {
        int x = 0;
        try {
            String classificacao = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.container > div > h4 > a"))).getText();
            if(classificacao.contains("|")){
                for (int i = 0; i < classificacao.length(); i++) {
                    if (classificacao.charAt(i) == '|') {
                        x = i;
                    }
                }
                return classificacao.substring(0, x);
            }else {
                return classificacao;
            }
        } catch (TimeoutException ex) {
            return "";
        }
    }
    
    public String pegaEvento(){
        return (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/h4[2]"))).getText();
    }
}
