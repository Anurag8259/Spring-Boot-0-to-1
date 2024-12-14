package com.example.week1hw.Week1.HW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week1HwApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Week1HwApplication.class, args);
	}


	@Autowired
	private CakeBaker cakeBaker;
	@Override
	public void run(String... args) throws Exception {
		Frosting chocolateFrosting=new ChocolateFrosting();
		Syrup chocolateSyrup=new ChocolateSyrup();
		cakeBaker=new CakeBaker(chocolateFrosting,chocolateSyrup);
		cakeBaker.bakeCake();

	}
}
