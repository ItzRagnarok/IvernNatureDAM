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

@AllArgsConstructor
@Data
@Entity
@Table(name = "respuestas")

public class RespuestaVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrespuesta;
	private String texto;
	@ManyToOne
	@JoinColumn(name="idtema", unique = true)
	private TemaVO tema;
	@ManyToOne
	@JoinColumn(name="idusuario")
	private UsuarioVO usuario;
	
	
	public int getIdrespuesta() {
		return idrespuesta;
	}

	public void setIdrespuesta(int idrespuesta) {
		this.idrespuesta = idrespuesta;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public TemaVO getTema() {
		return tema;
	}

	public void setTema(TemaVO tema) {
		this.tema = tema;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	
	
	
	public RespuestaVO() {}

    public RespuestaVO(String texto) {
        this.texto = texto;
    }

	
	public RespuestaVO(String texto, TemaVO tema, UsuarioVO usuario) {
		super();
		this.texto = texto;
		this.tema = tema;
		this.usuario = usuario;
	}

}
