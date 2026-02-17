package com.dawes.IvernNature.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuarios")

public class UsuarioVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuario;
	@Column(name = "nombre_usuario", unique = true)
	private String nombreUsuario;
	@Column(name = "correo_electronico")
	private String correoElectronico;
	@Column(name = "nombreyapellidos")
	private String nombreYApellidos;
	private String password;
	
	@Transient
    private String password2;
	
	private String avatar;
//	private MultipartFile avatar;
	
	@Transient
	private boolean esProfesor; 
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<ParticipaVO> cursos;
	@OneToMany(mappedBy = "usuario")
	private List<EventoVO> eventos;
	@OneToMany(mappedBy = "usuario")
	private List<TemaVO> temas;
	@ManyToOne
	@JoinColumn(name="idrol")
	private RolVO rol;
	@OneToMany(mappedBy = "usuario")
	private List<RespuestaVO> respuestas;

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getNombreYApellidos() {
		return nombreYApellidos;
	}

	public void setNombreYApellidos(String nombreYApellidos) {
		this.nombreYApellidos = nombreYApellidos;
	}

	public List<ParticipaVO> getCursos() {
		return cursos;
	}

	public void setCursos(List<ParticipaVO> cursos) {
		this.cursos = cursos;
	}

	public List<EventoVO> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoVO> eventos) {
		this.eventos = eventos;
	}

	public List<TemaVO> getTemas() {
		return temas;
	}

	public void setTemas(List<TemaVO> temas) {
		this.temas = temas;
	}

	public RolVO getRol() {
		return rol;
	}




	public void setRol(RolVO rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

	public List<RespuestaVO> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<RespuestaVO> respuestas) {
		this.respuestas = respuestas;
	}
	
//	public MultipartFile getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(MultipartFile avatar) {
//        this.avatar = avatar;
//    }
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public boolean isEsProfesor() {
	    return esProfesor;
	}

	public void setEsProfesor(boolean esProfesor) {
	    this.esProfesor = esProfesor;
	}
	

	// Constructor con parámetros para facilitar la creación de instancias
    public UsuarioVO(int idusuario, String nombreUsuario, String correoElectronico, String nombreYApellidos, String password, String avatar) {
        this.idusuario = idusuario;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.nombreYApellidos = nombreYApellidos;
        this.password = password;
        this.avatar = avatar;
    }

	public UsuarioVO(String nombreUsuario, String correoElectronico, String nombreYApellidos, String password,
			String avatar, List<ParticipaVO> cursos, List<EventoVO> eventos, List<TemaVO> temas,
			List<RespuestaVO> respuestas) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.correoElectronico = correoElectronico;
		this.nombreYApellidos = nombreYApellidos;
		this.password = password;
		this.avatar = avatar;
		this.cursos = cursos;
		this.eventos = eventos;
		this.temas = temas;
		this.respuestas = respuestas;
	}
	
	
	
	
	



//	public UsuarioVO(String nombreUsuario, String correoElectronico, String nombreYApellidos, List<ParticipaVO> cursos,
//			List<EventoVO> eventos, List<TemaVO> temas, RolVO rol, List<RespuestaVO> respuestas) {
//		super();
//		this.nombreUsuario = nombreUsuario;
//		this.correoElectronico = correoElectronico;
//		this.nombreYApellidos = nombreYApellidos;
//		this.cursos = cursos;
//		this.eventos = eventos;
//		this.temas = temas;
////		this.rol = rol;
//		this.respuestas = respuestas;
//	}

}
