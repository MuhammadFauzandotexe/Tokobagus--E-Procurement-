package org.ojan.tokobagus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication
@Slf4j
public class TokobagusApplication implements CommandLineRunner{
	@Autowired
	public TokobagusApplication() {}
	public static void main(String[] args) {
		SpringApplication.run(TokobagusApplication.class, args);
	}
	@Override
	public void run(String... args) {
		log.info("Ojan join in the party");
	}
}
