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

@SpringBootApplication
public class SkillHubApplication {

	private static final Logger log = LoggerFactory.getLogger(SkillHubApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SkillHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, SkillRepository skillRepository) {
		return (args) -> {
			User susanne = new User("Susi723", "susanne.png", "Susanne", "GehtEuchNixAn", LocalDate.of(1952, 5, 17),
					"28215", "Bremen", "susanne-heer@web.de", "");

			User mia = new User("MiLove", "mia.png", "Mia", "Sommer", LocalDate.of(2002, 8, 1), "30453", "Hannover",
					"mia-sommer07@gmx.de", "");

			User lasse = new User("LasseRuckart", "lasse.png", "Lasse", "Ruckart", LocalDate.of(1982, 3, 14), "99092",
					"Erfurt", "lasse82@outlook.de", "");

			userRepository.save(susanne);
			userRepository.save(mia);
			userRepository.save(lasse);

			log.info("Users found with findAll():");
			log.info("---------------------------");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
			log.info("");

			Skill climbing = new Skill("climbing");
			
			skillRepository.save(climbing);

			log.info("Skills found with findAll():");
			log.info("----------------------------");
			for (Skill skill : skillRepository.findAll()) {
				log.info(skill.toString());
			}
			log.info("");

			susanne.addSkill(climbing);
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
