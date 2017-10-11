package se.polisen.hackathon;

import org.apache.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import se.polisen.hackathon.entity.TestEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class Starter {

    @PersistenceContext(unitName = "lucene")
    private EntityManager em;
    private static Logger log = Logger.getLogger(Starter.class);

    @PostConstruct
    public void onStart()   {
        log.info("- BEFORE START -");

        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager( em );
            fullTextEntityManager.createIndexer(TestEntity.class).startAndWait();
        } catch (Exception e) {
            log.error("onStart", e);
        }

        log.info("- AFTER START -");


    }
}
