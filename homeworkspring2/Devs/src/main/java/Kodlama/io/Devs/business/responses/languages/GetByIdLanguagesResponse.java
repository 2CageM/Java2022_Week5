package Kodlama.io.Devs.business.responses.languages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetByIdLanguagesResponse {
	
	private int id;
	private String languageName;

}
