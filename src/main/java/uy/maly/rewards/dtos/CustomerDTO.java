package uy.maly.rewards.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CustomerDTO {
	private Long id;
	@NonNull 
	private String familyName;
	@NonNull
	private String name;
	

}
