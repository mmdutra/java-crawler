package influenzer.bot.cetecop.dao;

import java.util.List;
import influenzer.bot.cetecop.model.Problema;
import influenzer.bot.generic.dao.AbstractDao;

public class ProblemaDao extends AbstractDao<Problema, Integer>{

    public ProblemaDao() {
        super(Problema.class);
    }

    @Override
    public List<Problema> find(Problema t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
