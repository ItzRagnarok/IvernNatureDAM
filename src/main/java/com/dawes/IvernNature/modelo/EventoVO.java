package com.dawes.IvernNature.modelo;

import java.time.LocalDate;

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
@Table(name = "eventos")

public class EventoVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idevento;
	@Column(unique = true)
	private String nombre;
	private LocalDate fecha;
	@ManyToOne
	@JoinColumn(name="idpropietario")
	private UsuarioVO usuario;
	
	public EventoVO(String nombre, LocalDate fecha, UsuarioVO usuario) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.usuario = usuario;
	}

}
