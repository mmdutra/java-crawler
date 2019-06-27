package influenzer.bot.generic.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

public abstract class AbstractDao<T extends Serializable, K> implements IDAO<T, K> {

    protected static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("CetecopBotPU");
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());
    protected EntityManager em;

    private final Class<T> entityClass;

    public AbstractDao(Class<T> entityClass) {
        em = getEntityManager();
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = FACTORY.createEntityManager();
        }
        return em;
    }

    @Override
    public T create(T entity) {

        System.out.println(entity);
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.flush();
            em.getTransaction().commit();
            em.refresh(entity);
            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return null;
        }

    }

    @Override
    public boolean delete(T t) {
        try {
            em.getTransaction().begin();
            //t = em.find(t.getClass(), t.getId());
            em.remove(t);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public T update(T t) {
        try {
            em.getTransaction().begin();
            t = (T) this.getEntityManager().merge(t);
            em.getTransaction().commit();
            return t;
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public T findById(K id) {
        T item = em.find(entityClass, id);
        return item;
    }

    @Override
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq;
        cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Collection<T> findByNamedQuery(String namedQueryName) {
        return this.getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }

    @Override
    public Collection<T> findByNamedQuery(String queryName, int resultLimit) {
        return this.getEntityManager().createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    @Override
    public List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.getEntityManager().createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        return findByNamedQuery(namedQueryName, parameters, 0);
    }

    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);

        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public <T> List<T> findByNativeQuery(String sql, Class<T> type) {
        return this.getEntityManager().createNativeQuery(sql, type).getResultList();
    }
    
//    public T buscaOuCriaEntidade(K id){
//        T obj = findById(id);
//        if(obj==null){
//            obj = ...
//        }
//        return obj;
//    }

}
