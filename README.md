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
