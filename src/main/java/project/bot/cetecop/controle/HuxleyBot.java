cetecoppackage influenzer.bot.cetecop.controle;

import org.openqa.selenium.TimeoutException;
import influenzer.bot.cetecop.model.Autor;
import influenzer.bot.cetecop.dao.ProblemaDao;
import influenzer.bot.cetecop.model.Problema;
import influenzer.bot.cetecop.model.TestCase;
import influenzer.bot.cetecop.dao.AutorDao;
import influenzer.bot.cetecop.dao.CategoriaDao;
import influenzer.bot.cetecop.dao.TestCaseDao;
import influenzer.bot.cetecop.model.Categoria;
import influenzer.bot.cetecop.view.TheHuxleyCrawler;
import influenzer.bot.generic.controle.AbstractBot;
import java.util.ArrayList;
import java.util.List;

public class HuxleyBot extends AbstractBot {

    private ArrayList<Integer> numPassados;
    private final int primeiroProblema = 40;
    private final int ultProblema = 874;
    private ProblemaDao pDao;
    private AutorDao aDao;
    private TestCaseDao tDao;
    private CategoriaDao cDao;
    private static int contadorProblemas = 0;
    
    private final TheHuxleyCrawler crawler;

    public HuxleyBot() {
        super();
        this.crawler = new TheHuxleyCrawler(driver);
        pDao = new ProblemaDao();
        aDao = new AutorDao();
        tDao = new TestCaseDao();
        cDao = new CategoriaDao();
        numPassados = new ArrayList();
    }
    
    @Override
    public void executa() {
        acessaSite("https://www.thehuxley.com/");
        vaiEmListarTodos();
        adicionarNomeProblema();
    }

    private void clicaEmProblemas() {
        acessaSite("https://www.thehuxley.com/problems");
    }

    private void vaiEmListarTodos() {
        acessaSite("https://www.thehuxley.com/problems");
    }

    private void pegaProblemas() {
       int temp = primeiroProblema;
       while(temp<ultProblema){
            try{
                acessaSite("https://www.thehuxley.com/problem/" + temp);
                pegaDadosProblema();
            }catch(TimeoutException ex){
                    
            }
            System.out.println("Problema -> "+ temp);
            temp++;
       }
    }

    public void adicionarNomeProblema(){
        List<Problema> problemas = pDao.findAll();
       int temp = primeiroProblema;
       while(temp<ultProblema){
            try{
                acessaSite("https://www.thehuxley.com/problem/" + temp);
                for(int i=0;i<problemas.size();i++){
                        Problema p = crawler.relacionarNmProblema(problemas.get(i));
                        if(p!=null){
                            pDao.update(p);
                        }
                }
            }catch(TimeoutException ex){
                    
            }
            System.out.println("Problema -> "+ temp);
            temp++;
       }
    }
    
    public void pegaDadosProblema() {
        
        String autor = crawler.pegaNomeAutor();
        String c = crawler.pegaCategoria();
        Problema p = crawler.pegaDadosProblema();
        if(!autor.isEmpty()) p.setIdAutor(verificaAutor(autor));
        else p.setIdAutor(null);
        if(!c.isEmpty()) p.setIdCategoria(verificaCategoria(c));
        else p.setIdCategoria(null);
        pDao.create(p);
        TestCase in = crawler.pegaTestCaseEntrada();
        in.setIdProblema(p);
        TestCase out = crawler.pegaTestCaseSaida();
        out.setIdProblema(p);
        tDao.create(in);
        tDao.create(out);
        
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
   

}
