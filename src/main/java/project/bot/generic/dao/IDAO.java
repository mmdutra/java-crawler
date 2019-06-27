package influenzer.bot.generic.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IDAO<T extends Serializable, K> {

    public  T create(T t);
    public boolean delete(T t);
    public  T update(T t);    

    public  T findById(K id);
    
    public  List<T> find(T t);
    public  List<T> findAll();
    
    public Collection<T> findByNamedQuery(String queryName);
    public Collection<T> findByNamedQuery(String queryName,int resultLimit);
    public List<T> findByNamedQuery(String namedQueryName, Map<String,Object> parameters);
    public List<T> findByNamedQuery(String namedQueryName, Map<String,Object> parameters,int resultLimit);
    
}
