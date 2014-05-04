SOLR-MONGODB-ANGULARJS
===========
This is an extension of an existing github project  : https://github.com/lashford/modern-web-template

In addition there is incorporated into the project an autoComplete with Solr as explained at http://www.cominvent.com/2012/01/25/super-flexible-autocomplete-with-solr/


The purpose of this project is to integrate the two concepts:  solr search coupled with content retrieval from mongodb.  All calls to mongo and solr, however go through Play2.2 
REST calls as would be done on a hosted web service.  In order to help speed the response the EHCache (default) from Play is used to hold previous suggests.



Setup;

First,  install mongodb and using the console load the loadPlaces (example-data/mongodb) into the test db
Second,  install solr and create the ac folder using the contents of the example-data/solr folder.  Start Solr  (solr-xxxx/example)  java -jar start.jar,
  select the ac core, and from the documents tab upload the file into the core.  If you don't see in the overview that documents were added then restart solr.
  
  You can test that the basic queries work by using the console query utility.