package com.dawes.IvernNature.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "participa", uniqueConstraints = { @UniqueConstraint(columnNames = { "idusuario", "idcurso" }) })


public class ParticipaVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idparticipa;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private UsuarioVO usuario;
	@ManyToOne
	@JoinColumn(name = "idcurso")
	private CursoVO curso;
	
	public ParticipaVO(UsuarioVO usuario, CursoVO curso) {
		super();
		this.usuario = usuario;
		this.curso = curso;
	}

}
