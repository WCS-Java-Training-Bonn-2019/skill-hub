package com.wildcodeschool.skillhub;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.service.SkillService;
import com.wildcodeschool.skillhub.service.UserService;
import com.wildcodeschool.skillhub.service.UserSkillService;

@SpringBootApplication
public class SkillHubApplication {

	private static final Logger log = LoggerFactory.getLogger(SkillHubApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SkillHubApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public CommandLineRunner demo(UserService userService, SkillService skillService, UserSkillService userSkillService) {
		return (args) -> {
			
			// Create users manually
			User susanne = new User("susanne.png", "Susanne", "GehtEuchNixAn", LocalDate.of(1952, 5, 17),
					"28215", "Bremen", "susanne-heer@web.de", "1234", "");
			User mia = new User("mia.png", "Mia", "Sommer", LocalDate.of(2002, 8, 1), "30453", "Hannover",
					"mia-sommer07@gmx.de", "1234", "");
			User lasse = new User("lasse.png", "Lasse", "Ruckart", LocalDate.of(1982, 3, 14), "99092",
					"Erfurt", "lasse82@outlook.de", "1234", "");
			User alex = new User("alex.png", "Alex", "Schmidt", LocalDate.of(1978, 2, 18),
					"10319", "Berlin", "alexander-Boy@gmx.de", "1234", "Hi, I'm Alex and I'm cool!");
			User antonia = new User("antonia.png", "Antonia", "Müller", LocalDate.of(1992, 4, 17),
					"1992", "Köln", "antonia-mueller@gmx.de", "1234", "");
			User cem = new User("cem.png", "Cem", "Alan", LocalDate.of(2000, 5, 20),
					"53290", "Frankfurt", "cem-champ@gmail.de", "1234", "");
			User claudia = new User("claudia.png", "Claudia", "Siebert", LocalDate.of(1979, 11, 18),
					"90427", "Erlangen", "claudi-minigolf@arcor.de", "1234", "");
			User daniel = new User("daniel.png", "Daniel", "Jäger", LocalDate.of(1970, 9, 4),
					"43268", "Fulda", "daniel-jaeger1970@web.de", "1234", "");
			User harald = new User("harald.png", "Harald", "Krüger", LocalDate.of(1964, 3, 10),
					"23456", "Braunschweig", "harald_Krueger@web.de", "1234", "");
			User lennart = new User("lennart.png", "Lennart", "Peter", LocalDate.of(1995, 6, 21),
					"78561", "Leipzig", "lennipeter95@web.de", "1234", "");
			User maike = new User("maike.png", "Maike", "Berger", LocalDate.of(1996, 4, 16),
					"96325", "Hannover", "itsmemaike96@web.de", "1234", "");
			User marina = new User("marina.png", "Marina", "Bauer", LocalDate.of(1980, 7, 8),
					"65123", "Offenbach", "marryM@web.de", "1234", "");
			User reinhardt = new User("reinhardt.png", "Reinhardt", "Lalalalal", LocalDate.of(1950, 5, 1),
					"25456", "Bremen", "reini50-lalalal@t-online.de", "1234", "");
			User robert = new User("robert.png", "Robert", "Schmitz", LocalDate.of(1989, 10, 12),
					"12594", "Kassel", "robert-schmitzzz@gmail.de", "1234", "");
			User rolf = new User("rolf.png", "Rolf", "Langner", LocalDate.of(1960, 9, 13),
					"78652", "Erfurt", "rolf-langner@gmail.de", "1234", "");
			User till = new User("till.png", "Tipp", "Hausner", LocalDate.of(1970, 6, 2),
					"38751", "Düsseldorf", "till_hausner1970@web.de", "1234", "");
			
			
			//Create skills manually
			Skill climbing = new Skill("Climbing", "climbing.jpg");
			Skill cooking = new Skill("Cooking", "cooking.jpg");
			Skill books = new Skill("Books", "books.jpg");
			Skill photography = new Skill("Photography", "photography.jpg");
			Skill fashion = new Skill("Fashion", "fashion.jpg");
			Skill golf = new Skill("Golf", "golf.jpg");
			Skill baking = new Skill("Baking", "baking.jpg");
			Skill dogs = new Skill("Dogs", "dogs.jpg");
			Skill motorbike = new Skill ("Motorbike", "motorbike.jpg");
			
			
			//Save users
			userService.createNewUser(susanne);
			userService.createNewUser(mia);
			userService.createNewUser(lasse);
			userService.createNewUser(alex);
			userService.createNewUser(antonia);
			userService.createNewUser(cem);
			userService.createNewUser(claudia);
			userService.createNewUser(daniel);
			userService.createNewUser(harald);
			userService.createNewUser(lennart);
			userService.createNewUser(maike);
			userService.createNewUser(marina);
			userService.createNewUser(reinhardt);
			userService.createNewUser(robert);
			userService.createNewUser(rolf);
			userService.createNewUser(till);
			
			//Save skills
			skillService.createNewSkill(cooking);
			skillService.createNewSkill(books);
			skillService.createNewSkill(photography);
			skillService.createNewSkill(fashion);
			skillService.createNewSkill(golf);
			skillService.createNewSkill(baking);
			skillService.createNewSkill(dogs);
			skillService.createNewSkill(motorbike);
			
			System.out.println(userService.getUsers());
			System.out.println(skillService.getSkills());

			
			log.info("Users found with findAll():");
			log.info("---------------------------");
			for (User user : userService.getUsers()) {
				log.info(user.toString());
			}
			log.info("");

			log.info("Skills found with findAll():");
			log.info("----------------------------");
			for (Skill skill : skillService.getSkills()) {
				log.info(skill.toString());
			}
			log.info("");

			userSkillService.addNewUserSkill(susanne, cooking);
			userSkillService.addNewUserSkill(susanne, baking);
			userSkillService.addNewUserSkill(susanne, books);
			userSkillService.addNewUserSkill(mia ,fashion);
			userSkillService.addNewUserSkill(lasse, books);
			userSkillService.addNewUserSkill(alex, climbing);
			userSkillService.addNewUserSkill(antonia, fashion);
			userSkillService.addNewUserSkill(cem, photography);
			userSkillService.addNewUserSkill(claudia, fashion);
			userSkillService.addNewUserSkill(daniel, motorbike);
			userSkillService.addNewUserSkill(harald, golf);
			userSkillService.addNewUserSkill(lennart, climbing);
			userSkillService.addNewUserSkill(maike, baking);
			userSkillService.addNewUserSkill(marina, baking);
			userSkillService.addNewUserSkill(reinhardt, motorbike);
			userSkillService.addNewUserSkill(robert, photography);
			userSkillService.addNewUserSkill(rolf, books);
			userSkillService.addNewUserSkill(till, dogs);

			log.info("Users found with findBySkills_SkillId():");
			log.info("----------------------------------------");
			for (User user : userService.getUsersBySkillId(climbing.getId())) {
				log.info(user.toString());
			}
			log.info("");

			userSkillService.removeUserSkill(susanne, climbing);
			userService.createNewUser(susanne);

			log.info("Users found with findBySkills_SkillId():");
			log.info("----------------------------------------");
			for (User user : userService.getUsersBySkillId(climbing.getId())) {
				log.info(user.toString());
			}
			log.info("");
			

		};
	}

}
