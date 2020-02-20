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
	public CommandLineRunner demo(UserService userService, SkillService skillService) {
		return (args) -> {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String endcodedPassword = passwordEncoder.encode("1234");

			// Create skill objects
			Skill climbing = Skill.builder().name("Climbing").imageURL("climbing.jpg").build();
			Skill cooking = Skill.builder().name("Cooking").imageURL("cooking.jpg").build();
			Skill books = Skill.builder().name("Books").imageURL("books.jpg").build();
			Skill photography = Skill.builder().name("Photography").imageURL("photography.jpg").build();
			Skill fashion = Skill.builder().name("Fashion").imageURL("fashion.jpg").build();
			Skill golf = Skill.builder().name("Golf").imageURL("golf.jpg").build();
			Skill baking = Skill.builder().name("Baking").imageURL("baking.jpg").build();
			Skill dogs = Skill.builder().name("Dogs").imageURL("dogs.jpg").build();
			Skill motorbike = Skill.builder().name("Motorbike").imageURL("motorbike.jpg").build();

			// Create skills in DB
			skillService.createNewSkill(climbing);
			skillService.createNewSkill(cooking);
			skillService.createNewSkill(books);
			skillService.createNewSkill(photography);
			skillService.createNewSkill(fashion);
			skillService.createNewSkill(golf);
			skillService.createNewSkill(baking);
			skillService.createNewSkill(dogs);
			skillService.createNewSkill(motorbike);
			
			// Create user objects
			User susanne = User.builder().email("susanne-heer@web.de").password(endcodedPassword).firstName("Susanne")
					.lastName("GehtEuchNixAn").zipCode("28215").city("Bremen").dateOfBirth(LocalDate.of(1952, 5, 17))
					.imageURL("susanne.png").build();

			User mia = User.builder().email("mia-sommer07@gmx.de").password(endcodedPassword).firstName("Mia")
					.lastName("Sommer").zipCode("30453").city("Hannover").dateOfBirth(LocalDate.of(2002, 8, 1))
					.imageURL("mia.png").build();

			User lasse = User.builder().email("lasse82@outlook.de").password(endcodedPassword).firstName("Lasse")
					.lastName("Ruckart").zipCode("99092").city("Erfurt").dateOfBirth(LocalDate.of(1982, 3, 14))
					.imageURL("lasse.png").build();

			User alex = User.builder().email("alexander-Boy@gmx.de").password(endcodedPassword).firstName("Alex")
					.lastName("Schmidt").zipCode("10319").city("Berlin").dateOfBirth(LocalDate.of(1978, 2, 18))
					.imageURL("alex.png").description("Hi, I'm Alex and I'm cool!").build();

			User antonia = User.builder().email("antonia-mueller@gmx.de").password(endcodedPassword)
					.firstName("Antonia").lastName("Müller").zipCode("50676").city("Köln")
					.dateOfBirth(LocalDate.of(1992, 4, 17)).imageURL("antonia.png").build();

			User cem = User.builder().email("cem-champ@gmail.de").password(endcodedPassword).firstName("Cem")
					.lastName("Alan").zipCode("61290").city("Frankfurt").dateOfBirth(LocalDate.of(2000, 5, 20))
					.imageURL("cem.png").build();

			User claudia = User.builder().email("claudi-minigolf@arcor.de").password(endcodedPassword)
					.firstName("Claudia").lastName("Siebert").zipCode("90427").city("Erlangen")
					.dateOfBirth(LocalDate.of(1979, 11, 18)).imageURL("claudia.png").build();

			User daniel = User.builder().email("daniel-jaeger1970@web.de").password(endcodedPassword)
					.firstName("Daniel").lastName("Jäger").zipCode("43268").city("Fulda")
					.dateOfBirth(LocalDate.of(1970, 9, 4)).imageURL("daniel.png").build();

			User harald = User.builder().email("harald_Krueger@web.de").password(endcodedPassword).firstName("Harald")
					.lastName("Krüger").zipCode("23456").city("Braunschweig").dateOfBirth(LocalDate.of(1964, 3, 10))
					.imageURL("harald.png").build();

			User lennart = User.builder().email("lennipeter95@web.de").password(endcodedPassword).firstName("Lennart")
					.lastName("Peter").zipCode("78561").city("Leipzig").dateOfBirth(LocalDate.of(1995, 6, 21))
					.imageURL("lennart.png").build();

			User maike = User.builder().email("itsmemaike96@web.de").password(endcodedPassword).firstName("Maike")
					.lastName("Berger").zipCode("96325").city("Hannover").dateOfBirth(LocalDate.of(1996, 4, 16))
					.imageURL("maike.png").build();

			User marina = User.builder().email("marryM@web.de").password(endcodedPassword).firstName("Marina")
					.lastName("Bauer").zipCode("65123").city("Offenbach").dateOfBirth(LocalDate.of(1980, 7, 8))
					.imageURL("marina.png").build();

			User reinhardt = User.builder().email("reini50-lalalal@t-online.de").password(endcodedPassword)
					.firstName("Reinhardt").lastName("Lalalalal").zipCode("25456").city("Bremen")
					.dateOfBirth(LocalDate.of(1950, 5, 1)).imageURL("reinhardt.png").build();

			User robert = User.builder().email("robert-schmitzzz@gmail.de").password(endcodedPassword)
					.firstName("Robert").lastName("Schmitz").zipCode("12594").city("Kassel")
					.dateOfBirth(LocalDate.of(1989, 10, 12)).imageURL("robert.png").build();

			User rolf = User.builder().email("rolf-langner@gmail.de").password(endcodedPassword).firstName("Rolf")
					.lastName("Langner").zipCode("78652").city("Erfurt").dateOfBirth(LocalDate.of(1960, 9, 13))
					.imageURL("rolf.png").build();

			User till = User.builder().email("till_hausner1970@gmx.de").password(endcodedPassword).firstName("Till")
					.lastName("Hausner").zipCode("38751").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("till.png").build();

			// Create new users
			User arne = User.builder().email("arne@magenta.de").password(endcodedPassword).firstName("Arne")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("arne.png").build();
			User andre = User.builder().email("andre@magenta.de").password(endcodedPassword).firstName("Andre")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("andre.png").build();
			User claus = User.builder().email("claus@magenta.de").password(endcodedPassword).firstName("Claus")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("claus.png").build();
			User frank_f = User.builder().email("frank_f@magenta.de").password(endcodedPassword).firstName("Frank")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("frank_f.png").build();
			User frank_g = User.builder().email("frank_g@magenta.de").password(endcodedPassword).firstName("Frank")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("frank_g.png").build();
			User marcel = User.builder().email("marcel@magenta.de").password(endcodedPassword).firstName("Marcel")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("marcel.png").build();
			User markus = User.builder().email("markus@magenta.de").password(endcodedPassword).firstName("Markus")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("markus.png").build();
			User martin = User.builder().email("martin@magenta.de").password(endcodedPassword).firstName("Martin")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("martin.png").build();
			User metje = User.builder().email("metje@magenta.de").password(endcodedPassword).firstName("Metje")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("metje.png").build();
			User michael_be = User.builder().email("michael_be@magenta.de").password(endcodedPassword).firstName("Michael")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("michael_be.png").build();
			User michael_bl = User.builder().email("michael_bl@magenta.de").password(endcodedPassword).firstName("Michael")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("michael_bl.png").build();
			User michael_k = User.builder().email("michael_k@magenta.de").password(endcodedPassword).firstName("Michael")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("michael_k.png").build();
			User michael_o = User.builder().email("michael_o@magenta.de").password(endcodedPassword).firstName("Michael")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("michael_o.png").build();
			User ralf = User.builder().email("ralf@magenta.de").password(endcodedPassword).firstName("Ralf")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("ralf.png").build();
			User steffen = User.builder().email("steffen@magenta.de").password(endcodedPassword).firstName("Steffen")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("steffen.png").build();
			User stephanie = User.builder().email("stephanie@magenta.de").password(endcodedPassword).firstName("Stephanie")
					.lastName("Hausner").city("Düsseldorf").dateOfBirth(LocalDate.of(1970, 6, 2))
					.imageURL("stephanie.png").build();
			
			// Add some skills to users
			susanne.addSkill(cooking).addSkill(baking).addSkill(books);
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

			// Create users in DB
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
			
			// Create new users in DB
			userService.createNewUser(arne);
			userService.createNewUser(andre);
			userService.createNewUser(claus);
			userService.createNewUser(frank_f);
			userService.createNewUser(frank_g);
			userService.createNewUser(marcel);
			userService.createNewUser(markus);
			userService.createNewUser(martin);
			userService.createNewUser(metje);
			userService.createNewUser(michael_be);
			userService.createNewUser(michael_bl);
			userService.createNewUser(michael_k);
			userService.createNewUser(michael_o);
			userService.createNewUser(ralf);
			userService.createNewUser(steffen);
			userService.createNewUser(stephanie);
		};
	}

}
