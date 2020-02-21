package test.garbarino.martinKaynar.dtos;

import javax.validation.constraints.NotBlank;

public class CreateCartDto {

	@NotBlank(message = "Name is mandatory")
	private String fullName;
	
	@NotBlank(message = "Email is mandatory")
	private String email;

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
