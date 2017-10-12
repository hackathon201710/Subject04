package se.polisen.hackathon.entity;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;

@Indexed
@Analyzer(definition = "domar")
@AnalyzerDef(name = "domar",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = StopFilterFactory.class, params = {
                        @org.hibernate.search.annotations.Parameter(name = "words", value = "stoplist.properties"),
                        @org.hibernate.search.annotations.Parameter(name = "ignoreCase", value = "false")
                })
        })
@Entity
@Table(name = "domar")
public class DomarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Field
    private String title;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
