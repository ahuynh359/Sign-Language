package signlanguage;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import signlanguage.dao.CardDao;
import signlanguage.dao.CategoryDao;
import signlanguage.entity.Category;
import signlanguage.entity.ECard;
import signlanguage.service.StorageService;


import java.util.Map;

@SpringBootApplication
public class SignLanguageApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SignLanguageApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (CategoryDao categoriesDao, CardDao cardDao, StorageService storageService) {
		return arg -> {
			int sizeCategory = categoriesDao.findAll().size();
			if (sizeCategory != 0) return;
			setUpMysql(categoriesDao, cardDao, storageService);
		};
	}

	@Transactional
	public void setUpMysql(CategoryDao categoriesDao, CardDao cardDao, StorageService storageService) {
		Category aslAlphabet = Category.builder()
				.id("ASL alphabet")
				.name("ASL alphabet")
				.build();
		Category aslNumber = Category.builder()
				.id("ASL number")
				.name("ASL number")
				.build();
		aslAlphabet = categoriesDao.save(aslAlphabet);
		aslNumber = categoriesDao.save(aslNumber);

		Map<String, String> aslNumbers =  storageService.getASLNumber();
		Map<String, String> aslAlphabets =storageService.getASLAlphabet();
		for (Map.Entry<String, String> entry : aslNumbers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			ECard card = ECard.builder()
					.category(aslNumber)
					.front(key)
					.back(value)
					.build();
			cardDao.save(card);
		}
		for (Map.Entry<String, String> entry : aslAlphabets.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			ECard card = ECard.builder()
					.category(aslAlphabet)
					.front(key)
					.back(value)
					.build();
			cardDao.save(card);
		}
	}
}
