package influenzer.bot.cetecop.controle;

import influenzer.bot.cetecop.dao.CategoriaDao;
import influenzer.bot.cetecop.dao.EventoDao;
import influenzer.bot.cetecop.dao.ProblemaDao;
import influenzer.bot.cetecop.dao.TestCaseDao;
import influenzer.bot.cetecop.model.Categoria;
import influenzer.bot.cetecop.model.Evento;
import influenzer.bot.cetecop.model.Problema;
import influenzer.bot.cetecop.model.TestCase;
import influenzer.bot.cetecop.view.CodCadCrawler;
import influenzer.bot.generic.controle.AbstractBot;
import java.util.List;
import org.openqa.selenium.TimeoutException;

public class CodCadBot extends AbstractBot {

    private final CodCadCrawler cod;
    private final int primeiroProblema = 1;
    private final int ultProblema = 221;
    private final ProblemaDao pDao;
    private final TestCaseDao tDao;
    private final CategoriaDao cDao;
    private final EventoDao eDao;
    private static int contadorProblemas = 0;

    public CodCadBot() {
        super();
        contadorProblemas = primeiroProblema;
        cod = new CodCadCrawler(driver);
        pDao = new ProblemaDao();
        tDao = new TestCaseDao();
        cDao = new CategoriaDao();
        eDao = new EventoDao();
    }

    @Override
    public void executa() {
        acessaSite("http://www.codcad.com/");
        for (int i = primeiroProblema; i <= ultProblema; i++) { // para cada problema:
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Problema " + contadorProblemas);
            acessaSite("http://www.codcad.com/problem/" + i);
            try{
            Problema p = pegaProblema();
            if(p!=null){
                String cat = pegaCategoria();
                if(!"".equals(cat)){
                    Categoria c = verificaCategoria(cat);
                    if(c!=null) p.setIdCategoria(c);
                }
                String evt = pegaEvento();
                if(!"".equals(evt)){
                    Evento e = verificaEvento(evt);
                    if(e!=null) p.setIdEvento(e);
                }
                pDao.create(p);
                pegaTestCases(p);
            }
            }catch(TimeoutException ex){
                System.out.println("");
            }
            contadorProblemas++;
            System.out.println("------------------------------------------------------------------------------------");
        }
    }

    private Problema pegaProblema() {
        Problema p = new Problema();
        int i = 1;
        p.setNmproblema(cod.pegaNomeProblema());
        try {
            while (true) {
                if(p.getDescricao()!=null){
                    p.setDescricao(p.getDescricao() + cod.pegaDadosProblema(i));
                }else{
                    p.setDescricao(cod.pegaDadosProblema(i));
                }
                i++;
            }
        } catch (TimeoutException ex) {
            return p;
        }
    }

    private void pegaTestCases(Problema p) {
        int cont = 1;
        try {
            while (true) {
                TestCase testEntrada = cod.pegaTestCaseEntrada(cont);
                TestCase testSaida = cod.pegaTestCaseSaida(cont);
                testEntrada.setIdProblema(p);
                testSaida.setIdProblema(p);

                tDao.create(testEntrada);
                tDao.create(testSaida);
                cont++;
            }
        } catch (TimeoutException e) {
            System.out.println("");
        }
    }
    
    public String pegaCategoria(){
        try{
            return cod.pegaCategoria();
        }catch(TimeoutException ex){
            return "";
        }
    }
    
    public Categoria verificaCategoria(String descricao){
        Categoria cat = new Categoria();
        List<Categoria> list = cDao.findAll();
        boolean igual = false;
        int pos = 0;
        if(list.isEmpty()){
            cat.setDescricao(descricao);
            cDao.create(cat);
        }else {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getDescricao().equals(descricao)) {
                    igual = false;
                } else {
                    igual = true;
                    pos = i;
                    break;
                }
            }
            if (igual) {
                cat = list.get(pos);
            } else {
                cat.setDescricao(descricao);
                cDao.create(cat);
            }            
        }
        
        return cat;
    }
    
    public String pegaEvento(){
        try{
            return cod.pegaEvento();
        }catch(TimeoutException ex){
            return "";
        }        
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

    public static void main(String[] args) {
        CodCadBot c = new CodCadBot();
        c.executa();
    }

}
