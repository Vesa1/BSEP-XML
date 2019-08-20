package auth.service.authservice.dto;

public class RegistrationDTO {
	
	private String name;
	private String surname;
	private String email;
	private String password;
	private String passwordR;
	private String city;
	private String adress;
	private String telephone;
	
	public RegistrationDTO() {
		
	}
	
	public RegistrationDTO(String name, String surname, String email, String password, String passwordR, String city,
			String adress, String telephone) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.passwordR = passwordR;
		this.city = city;
		this.adress = adress;
		this.telephone = telephone;
	}
	
	@Override
	public String toString() {
		return "RegistrationDTO [name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + ", passwordR=" + passwordR + ", city=" + city + ", adress=" + adress + ", telephone="
				+ telephone + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordR() {
		return passwordR;
	}
	public void setPasswordR(String passwordR) {
		this.passwordR = passwordR;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}