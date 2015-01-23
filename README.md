# DDB Public Domain Calculator

[![Build Status](https://magnum.travis-ci.com/DenniJensen/ddb-pdc.svg?token=LXPadLLZHBGUqXF9dTdc)](https://magnum.travis-ci.com/DenniJensen/ddb-pdc)

The DDB Public Domain Calculator (or DDB-PDC in short) is a service
which, based on the the database of the
[Deutsche Digitale Bibliothek](https://deutsche-digitale-bibliothek.de/),
is able to determine if an artistic or literary work is likely to be
in the
[public domain](https://en.wikipedia.org/wiki/Public_domain). It
consists of two parts:

* A backend service which provides a REST API for calculating the
  public-domain status of any work in the DDB.

* A [Drupal](https://www.drupal.org/) module which allows querying the
  public domain calculator through a simple web interface.


## Installation and Setup

The following sections describe how to set up both the DDB-PDC backend
and Drupal module on a server. Both were mainly tested on Ubuntu
Server, but are likely to work on any other Unix-based system.

### Prerequsites

Installing and running the DDB-PDC backend requires the following:

* An API key for access to the Deutsche Digitable Bibliothek API. See the
  [DDB API documentation](https://api.deutsche-digitale-bibliothek.de/doku/display/ADD/API+der+Deutschen+Digitalen+Bibliothek)
  for instructions.

* Java 7 or newer. For being able to build the code, install the full
  JDK. [Maven](http://maven.apache.org/) is also required for
  building. On Ubutu, install both with the following command:

  ```
  sudo apt-get install openjdk-7-jdk maven
  ```

* [MongoDB](http://www.mongodb.com/) if you want to use the PDC result
  cache (see
  [Configuring the PDC Result Database](#configuring-the-pdc-result-database)). This
  is entirely optional. Install it on Ubuntu as follows:

  ```
  sudo apt-get install mongodb-server
  ```

The Drupal module merely needs an existing installation of Drupal 7 or
higher. See the
[Drupal Installation Guide](https://www.drupal.org/documentation/install).

### Building the Backend

After cloning this repository, move to its root folder and run

    mvn install

This will compile the DDB-PDC backend, test it, and finally create an
executable JAR file named `ddb-pdc-<version>.jar` in the `target/`
sub-directory. You can copy it anywhere you want, e.g.:

    mkdir -p /opt/ddb-pdc
    cp target/ddb-pdc-<version>.jar /opt/ddb-pdc/ddb-pdc.jar

### Configuring the Backend

Configuration of the DDB-PDC backend is done in the file
`application.properties`, which can be found in `config/`. When
running the backend, this file must be either in the current working
directory or in the `config/` sub-directory thereof. The easiest
approach is to copy `application.properties` into the same folder as
the backend JAR file.

You can pretty much leave the default configuration as-is. However,
you do need to fill out the `ddb.apikey` option, which tells the
backend which API key to use for accessing the DDB API:

    ddb.apikey=AbcDE12345...

### Configuring the PDC Result Database

Optionally, you can configure the backend to save the result of every
public-domain calculation - including a trace explaining why a work
was considered public-domain or not - to a MongoDB database. If the
same work is queried afterwards, the backend will serve the result
directly from the database instead of hitting the DDB API and
recalculating the public-domain status. (If the cached result is
outdated because it is from an earlier year, it is re-calculated
anyway.)

This feature doesn't only speed up multiple public-domain calculation
requests for the same work, but also makes it possible to do further
processing on the collected results in the future, for instance to
generate reports.

To enable the result database, set `ddb.storage.enable=true` in
`application.properties` and configure the database connection
parameters:

    ddb.storage.enable=truenspring.data.mongodb.host=127.0.0.1
    spring.data.mongodb.port=27017
    spring.data.mongodb.database=pdc
    spring.data.mongodb.collection=pdc_results

### Running the Backend

To start the backend server, simply run the JAR file with `java -jar`:

    cd /opt/ddb-pdc
    java -jar ddb-pdc.jar

Make sure that you are in the directory containing
`application.properties` (or `config/application.properties`) when you
run the command. The backend will now listen for HTTP requests on the
port configured through the `server.port` option (8080 by default).

For Ubuntu, this repository contains a
[Upstart](http://upstart.ubuntu.com/) job file that lets you run the
backend as a background service. Simply copy `extras/ddb-pdc.conf` to
`/etc/init` and adapt it to suit your needs. After this is done, you
can control the backend server like any other Upstart job:

    sudo start ddb-pdc
    sudo restart ddb-pdc
    sudo stop ddb-pdc

### Installing the Drupal Module

The Drupal module is contained in `drupal/ddb_pdc/`. An accompanying
example theme is located in `drupal/ddb_fuberlin/`. To install these,
copy both directories to the `sites/<your site>/modules` directory of
your Drupal installation. You can then enable and configure the theme
and module in the Drupal admin interface.

## Development

The backend service is a Java-based
[Spring Boot](http://projects.spring.io/spring-boot/) application. See
the
[Spring Boot Manual](http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/)
for more information about the general structure of Spring Boot
projects and the available Maven goals. Below is a quick overview
of the most common commands.

### Testing

The backend is accompanied by a suite of unit and integration tests
based on [JUnit](http://junit.org/). To run them, execute

    mvn test

Running `mvn verify` will also generate a code coverage report with
[JaCoCo](http://jacoco.org/) and save it to `target/site/jacoco/`.

### Running

You can run the backend server from the command line during development with

    mvn spring-boot:run

The configuration in `config/application.properties` will be used. Of
course, you can also run the server from your IDE as normal (the main
class is `de.ddb.pdc.Main`).
