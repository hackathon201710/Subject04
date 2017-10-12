package se.polisen.hackathon.stateless;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import se.polisen.hackathon.entity.TestEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SearchBean {

    @PersistenceContext(unitName = "lucene")
    private EntityManager em;

    private QueryBuilder getQueryBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(TestEntity.class).get();
    }


    public List<TestEntity> findByInfo(String keyword) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        Query query = getQueryBuilder(fullTextEntityManager).keyword().onField("info").matching(keyword).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query);
        @SuppressWarnings("unchecked")
        List<TestEntity> list = (List<TestEntity>) fullTextQuery.getResultList();
        return list;
    }
}
