package influenzer.bot.cetecop.view;

import influenzer.bot.cetecop.model.Problema;
import influenzer.bot.cetecop.model.TestCase;
import influenzer.bot.cetecop.model.Uri;
import influenzer.bot.generic.view.AbstractCrawler;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UriCrawler extends AbstractCrawler {

    public UriCrawler(WebDriver driver, int tempoEspera) {
        super(driver, tempoEspera);
    }

    public UriCrawler(WebDriver driver) {
        super(driver);
    }

    public String pegaIdioma() {
        try {
            String idioma = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div"))).getText();
            if (idioma.contains("United States")) {
                return "ingles";
            } else {
                return "portugues";
            }
        } catch (Exception ex) {
            return "";
        }
    }

    public boolean isAutor() {
        String s = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div"))).getText();
        return !(s.contains("maratona") || s.contains("Maratona"));
    }

    public int pegaTimeLimit() {
        String timelimit = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header > strong:nth-child(4)"))).getText();
        String tl = timelimit.substring(11, timelimit.length());
        return Integer.parseInt(tl);
    }

    public TestCase pegaTestCaseEntrada(int i) {
        TestCase testEntrada = new TestCase();
        testEntrada.setCaso("entrada");
        try {
            String in = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.problem > table:nth-child(" + i + ") > tbody > tr > td.division > p"))).getText();
            testEntrada.setConteudo(in);
        } catch (TimeoutException ex) {
            throw new TimeoutException();
        }
        return testEntrada;
    }

    public TestCase pegaTestCaseSaida(int i) {
        TestCase test = new TestCase();
        test.setCaso("saída");
        try {
            String out = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.problem > table:nth-child(" + i + ") > tbody > tr > td:nth-child(2) > p"))).getText();
            test.setConteudo(out);
        } catch (TimeoutException ex) {
            throw new TimeoutException();
        }
        return test;
    }

    public String pegaNomeAutorOrEvento() {

        try {
            //autor
            String nome = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div"))).getText();
            System.out.println(nome);
            String autor = "";
            if (nome.contains("Adaptado por")) {
                autor = nome.replace("Adaptado por ", "");
            } else if (nome.contains("Por ")) {
                autor = nome.replace("Por ", "");
            } else if (nome.contains("By ")) {
                autor = nome.replace("By ", "");
            } else if (nome.contains("Maratona ")) {
                return nome;
            }
            int n = 0;
            for (int i = 0; i < autor.length(); i++) {
                if (autor.charAt(i) == ',') {
                    n = i;
                    break;
                }
            }
            String autor2 = "";
            if (autor.contains("United States")) {
                autor2 = autor.replace("United States", "");
            } else if (autor.contains("Poland")) {
                autor2 = autor.replace("Poland", "");
            } else if (autor.contains("Argentina")) {
                autor2 = autor.replace("Argentina", "");
            } else if (autor.contains("Uruguai")) {
                autor2 = autor.replace("Uruguai", "");
            } else if(autor.contains("Brazil")){
                autor2 = autor.replace("Brazil","");
            } else if(autor.contains("China")){
                autor2 = autor.replace("China","");
            } else if(autor.contains("Brasil")){
                autor2 = autor.replace("Brasil", "");
            } else if(autor.contains("Espanha")){
                autor2 = autor.replace("Espanha", "");
            } else if(autor.contains("Bangladesh")){
                autor2 = autor.replace("Bangladesh","");
            } else if(autor.contains("Venezuela")){
                autor2 = autor.replace("Venezuela", "");
            } else if(autor.contains("Alemanha")){
                autor2 = autor.replace("Alemanha","");
            }
            System.out.println(autor2);
            if(n>0) autor2 = autor.substring(0, n);
            return autor2;
        } catch (Exception ex) {
            return "Desconhecido";
        }
    }

    public Problema pegaDadosProblema() {

        // titulo
        String nmproblema = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.header > h1"))).getText();

        // descricao
        String descricao = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.className("description"))).getText();
        // texto entrada
        String input = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.className("input"))).getText();
        // texto saida
        String output = (new WebDriverWait(driver, tempoEspera))
                .until(ExpectedConditions.elementToBeClickable(By.className("output"))).getText();

        Problema p = new Problema();
        p.setNmproblema(nmproblema);
        p.setDescricao(descricao);
        p.setInput(input);
        p.setOutput(output);
        return p;
    }
    
    public Uri pegaDadosTabela(int i){
        try {
            int id = Integer.parseInt((new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"element\"]/table/tbody/tr["+ i +"]/td[1]"))).getText());

            String nome = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"element\"]/table/tbody/tr[ " + i + " ]/td[3]/a"))).getText();

            String categoria = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"element\"]/table/tbody/tr[" + i + "]/td[4]/a"))).getText();

            String resolvidos_string = (new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"element\"]/table/tbody/tr[" + i + "]/td[5]"))).getText();        


            int nivel = Integer.parseInt((new WebDriverWait(driver, tempoEspera))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"element\"]/table/tbody/tr["+ i +"]/td[6]"))).getText());        
            
            int resolvidos = Integer.parseInt(resolvidos_string.replace(".", ""));

            Uri u = new Uri(id, nome, categoria, resolvidos, nivel);

            return u;
        } catch(Exception ex){
            return null;
        }
    }
}
