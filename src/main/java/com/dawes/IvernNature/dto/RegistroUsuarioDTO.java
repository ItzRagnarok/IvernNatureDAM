package com.dawes.IvernNature.dto;

import org.springframework.web.multipart.MultipartFile;

public class RegistroUsuarioDTO {
//    @NotEmpty(message = "El nombre de usuario es obligatorio")
	private String username;
//    @NotEmpty(message = "El nombre completo es obligatorio")
    private String fullName;
//    @Email(message = "Email no válido")
//    @NotEmpty(message = "El email es obligatorio")
    private String email;
//    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    private MultipartFile avatar; // Para manejar la carga de archivos
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public MultipartFile getAvatar() {
		return avatar;
	}
	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}
    

}
