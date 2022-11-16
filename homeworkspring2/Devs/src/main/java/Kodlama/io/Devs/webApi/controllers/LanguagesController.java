package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.requests.languages.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.languages.DeleteLanguageRequest;
import Kodlama.io.Devs.business.requests.languages.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.languages.GetAllLanguagesResponse;
import Kodlama.io.Devs.business.responses.languages.GetByIdLanguagesResponse;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {

	private LanguageService languageService;

	@Autowired
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}

	@PostMapping("/delete")
	public void delete(DeleteLanguageRequest deleteLanguageRequest) {

		languageService.delete(deleteLanguageRequest);
	}

	@PostMapping("/update")
	public void update(UpdateLanguageRequest updateLanguageRequest) throws Exception {
		languageService.update(updateLanguageRequest);
	}

	@GetMapping("/getAll")
	public List<GetAllLanguagesResponse> getAll() {
		return languageService.getAll();
	}

	@GetMapping("/getId")
	public GetByIdLanguagesResponse getById(int id) {
		return languageService.getById(id);
	}

	@PostMapping("/add")
	public void add(CreateLanguageRequest createLanguageRequest) throws Exception {
		this.languageService.add(createLanguageRequest);
	}
}
