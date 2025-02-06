package com.github.redfox197.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.redfox197.demo.cli.CliManager;
import com.github.redfox197.demo.database.service.RoleService;
import com.github.redfox197.demo.database.service.SubRedditService;
import com.github.redfox197.demo.database.service.UtenteService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SubRedditService subRedditService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new CliManager(utenteService, roleService, subRedditService);
	}

}
