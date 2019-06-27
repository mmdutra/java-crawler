package influenzer.bot.cetecop.dao;

import java.util.List;
import influenzer.bot.cetecop.model.TestCase;
import influenzer.bot.generic.dao.AbstractDao;

/**
 *
 * @author mateus
 */
public class TestCaseDao extends AbstractDao<TestCase, Integer>{

    public TestCaseDao() {
        super(TestCase.class);
    }
    
    @Override
    public List<TestCase> find(TestCase t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
