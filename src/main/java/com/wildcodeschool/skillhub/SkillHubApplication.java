package com.wildcodeschool.skillhub;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.repository.SkillRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import com.wildcodeschool.skillhub.repository.UserSkillRepository;

@SpringBootApplication
public class SkillHubApplication {

	private static final Logger log = LoggerFactory.getLogger(SkillHubApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SkillHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, SkillRepository skillRepository, UserSkillRepository userSkillRepository) {
		return (args) -> {
			
			// Create users manually
			User susanne = new User("Susi723", "susanne.png", "Susanne", "GehtEuchNixAn", LocalDate.of(1952, 5, 17),
					"28215", "Bremen", "susanne-heer@web.de", "");
			User mia = new User("MiLove", "mia.png", "Mia", "Sommer", LocalDate.of(2002, 8, 1), "30453", "Hannover",
					"mia-sommer07@gmx.de", "");
			User lasse = new User("LasseRuckart", "lasse.png", "Lasse", "Ruckart", LocalDate.of(1982, 3, 14), "99092",
					"Erfurt", "lasse82@outlook.de", "");
			User alex = new User("alexBoy", "alex.png", "Alex", "Schmidt", LocalDate.of(1978, 2, 18),
					"10319", "Berlin", "alexander-Boy@gmx.de", "Hi, I'm Alex and I'm cool!");
			User antonia = new User("tonimoni", "antonia.png", "Antonia", "Müller", LocalDate.of(1992, 4, 17),
					"1992", "Köln", "antonia-mueller@gmx.de", "");
			User cem = new User("CemYOLO", "cem.png", "Cem", "Alan", LocalDate.of(2000, 5, 20),
					"53290", "Frankfurt", "cem-champ@gmail.de", "");
			User claudia = new User("cLaUdIa", "claudia.png", "Claudia", "Siebert", LocalDate.of(1979, 11, 18),
					"90427", "Erlangen", "claudi-minigolf@arcor.de", "");
			User daniel = new User("dannyDanger", "daniel.png", "Daniel", "Jäger", LocalDate.of(1970, 9, 4),
					"43268", "Fulda", "daniel-jaeger1970@web.de", "");
			User harald = new User("harryH", "harald.png", "Harald", "Krüger", LocalDate.of(1964, 3, 10),
					"23456", "Braunschweig", "harald_Krueger@web.de", "");
			User lennart = new User("lennarT", "lennart.png", "Lennart", "Peter", LocalDate.of(1995, 6, 21),
					"78561", "Leipzig", "lennipeter95@web.de", "");
			User maike = new User("maikeMi", "maike.png", "Maike", "Berger", LocalDate.of(1996, 4, 16),
					"96325", "Hannover", "itsmemaike96@web.de", "");
			User marina = new User("marryM", "marina.png", "Marina", "Bauer", LocalDate.of(1980, 7, 8),
					"65123", "Offenbach", "marryM@web.de", "");
			User reinhardt = new User("reini50", "reinhardt.png", "Reinhardt", "Lalalalal", LocalDate.of(1950, 5, 1),
					"25456", "Bremen", "reini50-lalalal@t-online.de", "");
			User robert = new User("Robot-Ro", "robert.png", "Robert", "Schmitz", LocalDate.of(1989, 10, 12),
					"12594", "Kassel", "robert-schmitzzz@gmail.de", "");
			User rolf = new User("Rolfi", "rolf.png", "Rolf", "Langner", LocalDate.of(1960, 9, 13),
					"78652", "Erfurt", "rolf-langner@gmail.de", "");
			User till = new User("tiLL", "till.png", "Tipp", "Hausner", LocalDate.of(1970, 6, 2),
					"38751", "Düsseldorf", "till_hausner1970@web.de", "");
			
			
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
			userRepository.save(susanne);
			userRepository.save(mia);
			userRepository.save(lasse);
			userRepository.save(alex);
			userRepository.save(antonia);
			userRepository.save(cem);
			userRepository.save(claudia);
			userRepository.save(daniel);
			userRepository.save(harald);
			userRepository.save(lennart);
			userRepository.save(maike);
			userRepository.save(marina);
			userRepository.save(reinhardt);
			userRepository.save(robert);
			userRepository.save(rolf);
			userRepository.save(till);
			
			//Save skills
			skillRepository.save(climbing);
			skillRepository.save(cooking);
			skillRepository.save(books);
			skillRepository.save(photography);
			skillRepository.save(fashion);
			skillRepository.save(golf);
			skillRepository.save(baking);
			skillRepository.save(dogs);
			skillRepository.save(motorbike);
			
			System.out.println(userRepository.findAll());
			System.out.println(skillRepository.findAll());

			log.info("Users found with findAll():");
			log.info("---------------------------");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
			log.info("");

			log.info("Skills found with findAll():");
			log.info("----------------------------");
			for (Skill skill : skillRepository.findAll()) {
				log.info(skill.toString());
			}
			log.info("");

			susanne.addSkill(cooking);
			mia.addSkill(fashion);
			lasse.addSkill(books);
			alex.addSkill(climbing);
			antonia.addSkill(fashion);
			cem.addSkill(photography);
			claudia.addSkill(fashion);
			daniel.addSkill(motorbike);
			harald.addSkill(golf);
			lennart.addSkill(climbing);
			maike.addSkill(baking);
			marina.addSkill(baking);
			reinhardt.addSkill(motorbike);
			robert.addSkill(photography);
			rolf.addSkill(books);
			till.addSkill(dogs);
			
			userRepository.save(susanne);
			userRepository.save(mia);
			userRepository.save(lasse);
			userRepository.save(alex);
			userRepository.save(antonia);
			userRepository.save(cem);
			userRepository.save(claudia);
			userRepository.save(daniel);
			userRepository.save(harald);
			userRepository.save(lennart);
			userRepository.save(maike);
			userRepository.save(marina);
			userRepository.save(reinhardt);
			userRepository.save(robert);
			userRepository.save(rolf);
			userRepository.save(till);

			log.info("Users found with findBySkills_SkillId():");
			log.info("----------------------------------------");
			for (User user : userRepository.findBySkills_SkillId(climbing.getId())) {
				log.info(user.toString());
			}
			log.info("");

			susanne.removeSkill(climbing, userSkillRepository);
			userRepository.save(susanne);

			log.info("Users found with findBySkills_SkillId():");
			log.info("----------------------------------------");
			for (User user : userRepository.findBySkills_SkillId(climbing.getId())) {
				log.info(user.toString());
			}
			log.info("");
			

		};
	}

}
