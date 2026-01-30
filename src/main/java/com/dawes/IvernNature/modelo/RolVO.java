//package com.dawes.IvernNature.modelo;
//
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Entity
//@Table(name = "roles")
//
//public class RolVO {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int idrol;
//	@Column(unique = true)
//	private String nombre;
//	@OneToMany(mappedBy="rol")
//	private List<UsuarioVO> usuario;
//	
//	public RolVO(String nombre, List<UsuarioVO> usuario) {
//		super();
//		this.nombre = nombre;
//		this.usuario = usuario;
//	}
//
//}
