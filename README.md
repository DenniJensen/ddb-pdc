# ddb-pdc  [![Build Status](https://magnum.travis-ci.com/DenniJensen/ddb-pdc.svg?token=LXPadLLZHBGUqXF9dTdc)](https://magnum.travis-ci.com/DenniJensen/ddb-pdc)
DDB-PDC calculates which works from the inventory of the Deutsche Digitale Bibliothek (DDB) can be put in the public domain.

## Installing
### Back End
1. Make sure you have Maven and Java 7 installed.
2. Create an account on DDB and generate your API-key at https://www.deutsche-digitale-bibliothek.de/user/apikey . Put this key into the `config/application.properties` file.
3. Make sure you don't have a tomcat running. On Ubuntu, this can be done by `sudo service tomcat7 stop`.
4. Run `mvn spring-boot:run` to start the server.

### Front End
* The main Drupal module can be found at `drupal/ddb_pdc`. For information about configuration and dependencies, please take a look at the readme there.

* The module located at`drupal/fuberlin` is a theme created for demo purposes, installing this is optional.


## Testing
* `mvn test` will run all tests
* `mvn verify` will generate a code coverage report at `target/site/jacoco`


# Implementation
## Code Basis of PDC / Answerers

- To decide if an item is in the public domain or not, the decision tree provided by outofcopyright.eu for Germany is used. Find it here: http://outofcopyright.eu/media.html. It is reformulated so it only contains yes-no-questions.
- The decision tree is implemented in the file FlowChartStateGermany.java https://github.com/DenniJensen/ddb-pdc/blob/master/src/main/java/de/ddb/pdc/core/FlowChartStateGermany.java
- Although all of the decision tree is implemented in FlowChartStateGermany.java we will always use the decision tree part for "art or literature" due to a missing way of determining the category of the calculated item
- The questions from the flow chart will be answered by the Answerer classes found here https://github.com/DenniJensen/ddb-pdc/tree/master/src/main/java/de/ddb/pdc/core/answerers. One Answerer class per Question. The answerers use the Informations provided by MetaFetcher to answer a question.
- Answerers can return the answer yes, assumed yes, no, assumed no, or unknown. The answer unknown will always abort the analysis of the item and return an error.
- Answerers can also create a note that is displayed in the front end describing on which data their decision was based.
- A list of all answered Questions and their answer is then return to the front end to display it.

## Crawler
- The crawler crawls "random" elements from the ddb
- Random Elements are chosen by searching for "*" and using a random result offset and limit
- public domain is calculated for these random items and statistics about outcome and errors is created
- all exceptions and results will be logged to the console by the crawler
- by default the crawler is not active. to activate the crawler start the server with the command line argument -c or --crawler
- use the command line argument --depth to define the number of items the crawler will crawl through. Defaults to 1000 items. When the number of items is reached the crawler will stop
- use the command line argument --fetchSize to define the number of items that are fetched in one request to the ddb api. larger numbers will speed up the crawler but will stress the ddb api web service. Defaults to 100
