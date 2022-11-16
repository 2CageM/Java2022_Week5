package Kodlama.io.Devs.business.requests.subTechnologies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubTechnologyRequest {
	
	private String languageName;
	private int subTechnologyId;
	private String subTechnologyName;

}
