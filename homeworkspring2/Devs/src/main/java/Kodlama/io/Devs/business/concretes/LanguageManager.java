package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.requests.languages.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.languages.DeleteLanguageRequest;
import Kodlama.io.Devs.business.requests.languages.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.languages.GetAllLanguagesResponse;
import Kodlama.io.Devs.business.responses.languages.GetByIdLanguagesResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.entities.concretes.Language;
@Service
public class LanguageManager implements LanguageService {

	private LanguageRepository languageRepository;

	@Autowired
	public LanguageManager(LanguageRepository languageRepository) {

		this.languageRepository = languageRepository;
	}

	@Override
	public List<GetAllLanguagesResponse> getAll() {

		List<Language> languages = languageRepository.findAll();
		List<GetAllLanguagesResponse> languageResponse = new ArrayList<GetAllLanguagesResponse>();

		for (Language language : languages) {

			GetAllLanguagesResponse responseItem = new GetAllLanguagesResponse();
			responseItem.setId(language.getId());
			responseItem.setLanguageName(language.getLanguageName());
			languageResponse.add(responseItem);
		}
		return languageResponse;
	}

	@Override
	public GetByIdLanguagesResponse getById(int id) {

		Language language = languageRepository.findById(id).get();
		GetByIdLanguagesResponse response = new GetByIdLanguagesResponse();
		response.setLanguageName(language.getLanguageName());

		return response;
	}

	@Override
	public void add(CreateLanguageRequest createLanguageRequest) throws Exception {

		Language language = new Language();

		List<Language> languages = languageRepository.findAll();
		if (createLanguageRequest.getLanguageName().isEmpty()) {
			throw new Exception("Dil adı kısmı boş olamaz");

		}
		for (Language language1 : languages) {
			if (language1.getLanguageName().equalsIgnoreCase(createLanguageRequest.getLanguageName())) {
				throw new Exception("Dil adı aynı olamaz.");
			}
		}

		language.setLanguageName(createLanguageRequest.getLanguageName());
		this.languageRepository.save(language);
	}

	@Override
	public void update(UpdateLanguageRequest updateLanguageRequest) throws Exception {

		boolean isCheck = true;

		Language language = new Language();

		List<Language> languages = languageRepository.findAll();

		if (updateLanguageRequest.getLanguageName().equals("") || updateLanguageRequest.getId() <= 0) {

			throw new Exception("Dil kısmı boş olamaz");
		}

		for (Language language1 : languages) {

			if (language1.getLanguageName().equalsIgnoreCase(updateLanguageRequest.getLanguageName())) {

				throw new Exception("Bu dil mevcuttur.");
			}
			if (language1.getId() == updateLanguageRequest.getId()) {

				isCheck = false;
			}
		}
		if (isCheck) {
			throw new Exception("ID Mecvut değil");
		}

		language.setId(updateLanguageRequest.getId());
		language.setLanguageName(updateLanguageRequest.getLanguageName());
		languageRepository.save(language);

	}

	@Override
	public void delete(DeleteLanguageRequest deleteLanguageRequest) {

		languageRepository.deleteById(deleteLanguageRequest.getId());
	}

}
