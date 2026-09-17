package com.example.lab1;

import com.example.lab1.database.DatabaseConnection;
import com.example.lab1.database.model.Player;
import com.example.lab1.database.repository.PlayerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
