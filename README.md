Play-with-Solr
==============

he purpose of this project is to integrate the two concepts:  solr search coupled with content retrieval from mongodb.  All calls to mongo and solr, however go through Play2.2  REST calls as would be done on a hosted web service.  In order to help speed the response the EHCache (default) from Play is used to hold previous suggests.
