package com.dawes.IvernNature.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "temas")

public class TemaVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtema;
	@Column(unique = true)
	private String titulo;
	@ManyToOne
	@JoinColumn(name="idusuario")
	private UsuarioVO usuario;
	
	public TemaVO(String titulo, UsuarioVO usuario) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
	}

}
