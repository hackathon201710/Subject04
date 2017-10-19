# Subject04 Efficient search techniques

**Details:** 
Explore the Lucene framework used the java space to enable smarter searching.  

**How:** 
* Setup a standard relation database with sample data, lot of text information.    
* Use Lucene to build an index of all information and perform Lucene search operations using this index. 
* The index can be kept initial in the internal memory of the application server for simplicity.
* Expose some REST endpoints to be able to perform search querys from the browser.  


**Bonus:** 
Use Docker to run server dependencies like database(mysql) and Java EE application server(wildfly) 


## Links used to develop this
* https://lucene.apache.org/core/
* https://github.com/Sanne/tweets-cli-demo
* https://www.docker.com/

## Conclusions

* Lucene makes it easy to perform complicated text searches that are almost impossible to do in standard SQL. 
* Lucene is a rather rich library and it takes some time to explore the full API.  
* In a real world application the index should be persisted in a central place on disk.
* Docker is a very convenient technique for setting up server dependencies and quick way switch between projects and configurations.
