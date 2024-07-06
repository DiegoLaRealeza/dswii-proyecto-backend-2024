package com.back.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@CommonsLog
public class Dswii2024ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dswii2024ProyectoApplication.class, args);
		log.info("termino de cargar");
	}

}
