package Kodlama.io.Devs.business.requests.subTechnologies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubTechnologyRequest {

	private int subTechnologyId;
	private String subTechnologyName;
	private String languageName;
}
