package influenzer.bot.cetecop.controle;

import java.util.List;
import org.openqa.selenium.TimeoutException;
import influenzer.bot.cetecop.model.Autor;
import influenzer.bot.cetecop.dao.ProblemaDao;
import influenzer.bot.cetecop.model.Problema;
import influenzer.bot.cetecop.model.TestCase;
import influenzer.bot.cetecop.dao.AutorDao;
import influenzer.bot.cetecop.dao.EventoDao;
import influenzer.bot.cetecop.dao.TestCaseDao;
import influenzer.bot.cetecop.dao.UriDao;
import influenzer.bot.cetecop.model.Evento;
import influenzer.bot.cetecop.model.Uri;
import influenzer.bot.cetecop.view.UriCrawler;
import influenzer.bot.generic.controle.AbstractBot;

public class UriBot extends AbstractBot {

    private final int primeiroProblema = 1;
    private final int ultProblema = 77;
    private ProblemaDao pDao;
    private AutorDao aDao;  
    private TestCaseDao tDao;
    private EventoDao eDao;
    private UriDao uDao;
    private static int contadorProblemas = 1;
    
    private final UriCrawler uri;

    public static void main(String[] args) {
        UriBot u = new UriBot();
        u.executa();
    }
    
    public UriBot() {
        super();
        this.uri = new UriCrawler(driver);
    }
    
    @Override
    public void executa() {
        pDao = new ProblemaDao();
        aDao = new AutorDao();
        tDao = new TestCaseDao();
        eDao = new EventoDao();
        uDao = new UriDao();
        acessaSite("https://www.urionlinejudge.com.br/judge/pt/login");
        vaiEmListarTodos();
        pegaProblemas();
    }
    
    private void clicaEmProblemas() {
        acessaSite("https://www.urionlinejudge.com.br/judge/pt/problems/all");
    }

    private void vaiEmListarTodos() {
        acessaSite("https://www.urionlinejudge.com.br/judge/pt/problems/all");
    }

    private void pegaProblemas() {
        for (int i = primeiroProblema; i <= ultProblema; i++) { // para cada problema:
            acessaSite("https://www.urionlinejudge.com.br/judge/pt/problems/all?page=" + i);
            pegaDadosProblema();
        }
    }
         
    public void pegaDadosProblemaAntigo() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.print("Problema " + contadorProblemas);
        System.out.println("------------------------------------------------------------------------------------");
        contadorProblemas++;

        
        Problema p = uri.pegaDadosProblema();
        String nome = "";
        String idioma = uri.pegaIdioma();
        if (!"".equals(idioma)) {
            if ("portugues".equals(idioma)) {
                boolean t = false;
                if (uri.isAutor()) {
                    nome = uri.pegaNomeAutorOrEvento();
                    t = true;
                } else {
                    nome = uri.pegaNomeAutorOrEvento();
                }
                if(!"".equals(nome) && t){
                    p.setIdAutor(verificaAutor(nome));
                }else if(!"".equals(nome) && !t){
                    p.setIdEvento(verificaEvento(nome));
                }else {
                    p.setIdAutor(null);
                    p.setIdEvento(null);
                }
            } else if ("ingles".equals(idioma)) {
                nome = uri.pegaNomeAutorOrEvento();
                p.setIdEvento(verificaEvento(nome));
            }
        } else {
            System.out.println("só vai na fé");
        }

        pDao.create(p);
        
        int cont = 7;
        try {
            while (true) {
                TestCase testEntrada = uri.pegaTestCaseEntrada(cont);
                TestCase testSaida = uri.pegaTestCaseSaida(cont);
                testEntrada.setIdProblema(p);
                testSaida.setIdProblema(p);
                tDao.create(testEntrada);
                tDao.create(testSaida);
                cont++;
            }
        }catch(TimeoutException ex){
            System.out.println("");
        }
    }
    
    public void pegaDadosProblema(){
        System.out.println("------------------------------------------------------------------------------------");
        System.out.print("Página " + contadorProblemas);
        System.out.println("------------------------------------------------------------------------------------");
        contadorProblemas++;

        int i = 1;
        while(i<26){
            Uri u = uri.pegaDadosTabela(i);
            if(u!=null) {
                try{
                    uDao.create(u);
                    System.out.println(u.getIdUri() + " -> Enviado com sucesso");
                }catch(Exception ex){
                    System.out.println("Não deu :/");
                }
            }
            i++;
        }
       
    }
    
    public Autor verificaAutor(String autor){
        List<Autor> list = aDao.findAll();
        Autor a = new Autor();
        boolean igual = false;
        int pos = 0;
        if(list.isEmpty()){
            a.setNmAutor(autor);
            aDao.create(a);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getNmAutor().equals(autor)) {
                    igual = false;
                } else {
                    igual = true;
                    pos = i;
                    break;
                }
            }
            if (igual) {
                a = list.get(pos);
            } else {
                a.setNmAutor(autor);
                aDao.create(a);
            }
        }
        return a;
    }
    
    public Evento verificaEvento(String evento){
        List<Evento> list = eDao.findAll();
        Evento e = new Evento();
        boolean igual = false;
        int pos = 0;
        if(list.isEmpty()){
            e.setNmEvento(evento);
            eDao.create(e);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getNmEvento().equals(evento)) {
                    igual = false;
                } else {
                    igual = true;
                    pos = i;
                    break;
                }
            }
            if (igual) {
                e = list.get(pos);
            } else {
                e.setNmEvento(evento);
                eDao.create(e);
            }
        }
        return e;
    }

}
