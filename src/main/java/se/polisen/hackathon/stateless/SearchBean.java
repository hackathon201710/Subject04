package se.polisen.hackathon.stateless;

import org.apache.lucene.search.Query;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import se.polisen.hackathon.entity.DomarEntity;
import se.polisen.hackathon.entity.TestEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SearchBean {

    @PersistenceContext(unitName = "lucene")
    private EntityManager em;

    private QueryBuilder getTestQueryBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(TestEntity.class).get();
    }

    private QueryBuilder getDomarQueryBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(DomarEntity.class).get();
    }


    public List<TestEntity> searchInfoByKeyword(String keyword) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityManager();
        Query query = getTestQueryBuilder(fullTextEntityManager).keyword().onField("info").matching(keyword).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query);
        @SuppressWarnings("unchecked")
        List<TestEntity> list = (List<TestEntity>) fullTextQuery.getResultList();
        return list;
    }
    
    public List<DomarEntity> searchDomarByKeyword(String keyword) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityManager();
        Query query = getDomarQueryBuilder(fullTextEntityManager).keyword().onField("title").matching(keyword).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query);
        @SuppressWarnings("unchecked")
        List<DomarEntity> list = (List<DomarEntity>) fullTextQuery.getResultList();
        return list;
    }

    public List<DomarEntity> searchDomarByPhrase(String phrase) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityManager();
        Query query = getDomarQueryBuilder(fullTextEntityManager).phrase().onField("title").sentence(phrase).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query);
        @SuppressWarnings("unchecked")
        List<DomarEntity> list = (List<DomarEntity>) fullTextQuery.getResultList();
        return list;
    }

    
    private FullTextEntityManager getFullTextEntityManager() {
        return Search.getFullTextEntityManager(em);
    }

    
    private SearchFactory getSearchFactory(FullTextEntityManager fullTextEntityManager)  {
        return fullTextEntityManager.getSearchFactory();
    }
}
