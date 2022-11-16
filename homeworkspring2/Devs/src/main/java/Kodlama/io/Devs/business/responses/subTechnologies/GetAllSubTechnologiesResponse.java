package Kodlama.io.Devs.business.responses.subTechnologies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSubTechnologiesResponse {
	
	private int id;
	private String languageName;
	private int languageId;
	private String subTechnologyName;

}
