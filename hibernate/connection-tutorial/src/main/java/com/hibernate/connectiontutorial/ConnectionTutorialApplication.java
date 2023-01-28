package com.hibernate.connectiontutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectionTutorialApplication {

	public static void main(String[] args) {
		// String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		// String user = "hbstudent";
		// String pass = "hbstudent";

		// try {
		// 	System.out.println("Connection to database: " + jdbcUrl);

		// 	Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

		// 	System.out.println("Connection successful");
		// } catch (Exception e) {
		// 	System.out.println(e.getMessage());
		// }

		SpringApplication.run(ConnectionTutorialApplication.class, args);
	}

}
