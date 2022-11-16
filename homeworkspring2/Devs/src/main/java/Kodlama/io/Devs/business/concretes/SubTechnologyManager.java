package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.SubTechnologyService;
import Kodlama.io.Devs.business.requests.subTechnologies.CreateSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.subTechnologies.DeleteSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.subTechnologies.UpdateSubTechnologyRequest;
import Kodlama.io.Devs.business.responses.subTechnologies.GetAllSubTechnologiesResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.dataAccess.abstracts.SubTechnologyRepository;
import Kodlama.io.Devs.entities.concretes.Language;
import Kodlama.io.Devs.entities.concretes.SubTechnology;
@Service
public class SubTechnologyManager implements SubTechnologyService {

	private SubTechnologyRepository subTechnologyRepository;
	private LanguageRepository languageRepository;

	@Autowired
	public SubTechnologyManager(LanguageRepository languageRepository,
			SubTechnologyRepository subTechnologyRepository) {

		this.languageRepository = languageRepository;
		this.subTechnologyRepository = subTechnologyRepository;
	}

	@Override
	public List<GetAllSubTechnologiesResponse> getAll() {

		List<SubTechnology> subTechnologies = subTechnologyRepository.findAll();
		List<GetAllSubTechnologiesResponse> getAllSubTechnologiesResponses = new ArrayList<GetAllSubTechnologiesResponse>();

		for (SubTechnology subTechnology : subTechnologies) {

			GetAllSubTechnologiesResponse subTechnologyResponseItem = new GetAllSubTechnologiesResponse();
			Language language = languageRepository.getReferenceById(subTechnology.getLanguage().getId());

			subTechnologyResponseItem.setId(subTechnology.getId());
			subTechnologyResponseItem.setSubTechnologyName(subTechnology.getSubTechnologyName());
			subTechnologyResponseItem.setLanguageId(language.getId());
			subTechnologyResponseItem.setLanguageName(language.getLanguageName());
			getAllSubTechnologiesResponses.add(subTechnologyResponseItem);
		}

		return getAllSubTechnologiesResponses;
	}

	@Override
	public void add(CreateSubTechnologyRequest createSubTechnologyRequest) throws Exception {

		int id = 0;
		boolean isCheck = false;
		List<Language> languages = languageRepository.findAll();
		SubTechnology subTechnology = new SubTechnology();
		List<SubTechnology> subTechnologies = subTechnologyRepository.findAll();

		if (createSubTechnologyRequest.getLanguageName().equals("")
				|| createSubTechnologyRequest.getSubTechnologyName().equals("")) {

			throw new Exception("Dil veya alt dil boş olamaz.");
		}

		for (SubTechnology subTechnology2 : subTechnologies) {

			if (subTechnology2.getSubTechnologyName()
					.equalsIgnoreCase(createSubTechnologyRequest.getSubTechnologyName())) {
				throw new Exception("Alt dil mevcuttur.");
			}
		}

		for (Language language : languages) {

			if (language.getLanguageName().equals(createSubTechnologyRequest.getLanguageName())) {
				id = language.getId();
				isCheck = true;
			}
		}
		if (isCheck) {
			Language language = languageRepository.getReferenceById(id);
			subTechnology.setSubTechnologyName(createSubTechnologyRequest.getSubTechnologyName());
			subTechnology.setLanguage(language);
			subTechnologyRepository.save(subTechnology);

		} else {
			throw new Exception("dil mevcut değil önce dili giriniz.");
		}
	}

	@Override
	public void delete(DeleteSubTechnologyRequest deleteSubTechnologyRequest) {

		subTechnologyRepository.deleteById(deleteSubTechnologyRequest.getSubTechnologyId());
	}

	@Override
	public void update(UpdateSubTechnologyRequest updateSubTechnologyRequest) throws Exception {

		int id = 0;
		boolean isCheck = false;
		List<Language> languages = languageRepository.findAll();
		SubTechnology subTechnology = new SubTechnology();

		if (updateSubTechnologyRequest.getSubTechnologyId() <= 0) {

			throw new Exception("Alt dil id'si boş olamaz.");

		}

		if (updateSubTechnologyRequest.getLanguageName().equals("")
				|| updateSubTechnologyRequest.getSubTechnologyName().equals("")) {
			throw new Exception("Dil veya alt dil adı boş olamaz.");

		}

		for (Language language : languages) {

			if (language.getLanguageName().equals(updateSubTechnologyRequest.getLanguageName())) {

				id = language.getId();
				isCheck = true;
			}
		}

		if (isCheck) {

			Language language = languageRepository.getReferenceById(id);
			subTechnology.setId(updateSubTechnologyRequest.getSubTechnologyId());
			subTechnology.setSubTechnologyName(updateSubTechnologyRequest.getSubTechnologyName());
			subTechnology.setLanguage(language);
			subTechnologyRepository.save(subTechnology);

		} else {
			throw new Exception("Girdiğiniz dil mevcut değil önce dil girişi yapınız.");

		}
	}

}
