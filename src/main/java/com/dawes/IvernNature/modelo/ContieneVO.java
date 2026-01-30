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
@Table(name = "contiene", uniqueConstraints = { @UniqueConstraint(columnNames = { "idcurso", "idcontenidoEducativo" }) })

public class ContieneVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcontiene;
	@ManyToOne
	@JoinColumn(name = "idcurso")
	private CursoVO curso;
	@ManyToOne
	@JoinColumn(name = "idcontenidoEducativo")
	private ContenidoEducativoVO contenidoEducativo;
	
	public ContieneVO(CursoVO curso, ContenidoEducativoVO contenidoEducativo) {
		super();
		this.curso = curso;
		this.contenidoEducativo = contenidoEducativo;
	}

}
