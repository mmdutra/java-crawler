package influenzer.bot.cetecop.dao;

import java.util.List;
import influenzer.bot.cetecop.model.Autor;
import influenzer.bot.generic.dao.AbstractDao;

public class AutorDao extends AbstractDao<Autor, Integer>{

    public AutorDao() {
        super(Autor.class);
    }

    @Override
    public List<Autor> find(Autor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
