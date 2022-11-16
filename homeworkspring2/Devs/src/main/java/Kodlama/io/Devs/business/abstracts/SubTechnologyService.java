package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.languages.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.subTechnologies.CreateSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.subTechnologies.DeleteSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.subTechnologies.UpdateSubTechnologyRequest;
import Kodlama.io.Devs.business.responses.subTechnologies.GetAllSubTechnologiesResponse;

public interface SubTechnologyService {
	
List<GetAllSubTechnologiesResponse> getAll();

void add(CreateSubTechnologyRequest createLanguageRequest) throws Exception;

void delete(DeleteSubTechnologyRequest deleteSubTechnologyRequest);

void update(UpdateSubTechnologyRequest updateSubTechnologyRequest) throws Exception;



}
