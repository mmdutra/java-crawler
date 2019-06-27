package influenzer.bot.cetecop.dao;

import influenzer.bot.cetecop.model.Uri;
import influenzer.bot.generic.dao.AbstractDao;
import java.util.List;

public class UriDao extends AbstractDao<Uri, Integer>{

    public UriDao() {
        super(Uri.class);
    }

    @Override
    public List<Uri> find(Uri t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
