package influenzer.bot.cetecop.dao;

import influenzer.bot.cetecop.model.Evento;
import influenzer.bot.generic.dao.AbstractDao;
import java.util.List;

public class EventoDao extends AbstractDao<Evento, Integer>{

    public EventoDao() {
        super(Evento.class);
    }

    @Override
    public List<Evento> find(Evento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
