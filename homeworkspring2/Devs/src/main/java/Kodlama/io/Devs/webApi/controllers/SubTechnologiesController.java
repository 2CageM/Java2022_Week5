package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Devs.business.abstracts.SubTechnologyService;
import Kodlama.io.Devs.business.requests.subTechnologies.CreateSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.subTechnologies.DeleteSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.subTechnologies.UpdateSubTechnologyRequest;
import Kodlama.io.Devs.business.responses.subTechnologies.GetAllSubTechnologiesResponse;


@RestController
@RequestMapping("/api/subTechnologies")
public class SubTechnologiesController {

	private SubTechnologyService subTechnologyService;

	public SubTechnologiesController(SubTechnologyService subTechnologyService) {
		this.subTechnologyService = subTechnologyService;
	}

	@GetMapping("/getAll")
	public List<GetAllSubTechnologiesResponse> getAll() {

		return subTechnologyService.getAll();
	}

	@PostMapping("/add")
	public void add(CreateSubTechnologyRequest createSubTechnologyRequest) throws Exception {
		subTechnologyService.add(createSubTechnologyRequest);

	}

	@PostMapping("/update")
	public void update(UpdateSubTechnologyRequest updateSubTechnologyRequest) throws Exception {
		subTechnologyService.update(updateSubTechnologyRequest);

	}

	@PostMapping("/delete")
	public void delete(DeleteSubTechnologyRequest deleteSubTechnologyRequest) {
		subTechnologyService.delete(deleteSubTechnologyRequest);
	}
}
