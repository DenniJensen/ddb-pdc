package de.ddb.pdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main and configuration class.
 */
@EnableAutoConfiguration
@ComponentScan
public class Main {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }
}
