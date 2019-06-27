package influenzer.bot.cetecop.dao;

import influenzer.bot.cetecop.model.Categoria;
import influenzer.bot.generic.dao.AbstractDao;
import java.util.List;

public class CategoriaDao extends AbstractDao<Categoria, Integer>{

    public CategoriaDao() {
        super(Categoria.class);
    }

    @Override
    public List<Categoria> find(Categoria t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
