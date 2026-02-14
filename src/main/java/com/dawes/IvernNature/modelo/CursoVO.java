package com.dawes.IvernNature.modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@Table(name = "cursos")
public class CursoVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcurso;
	@Column(name = "nombre_grupo", unique = true)
	private String nombreGrupo;
	@Column(name = "imagen_url")
	private String imagenUrl;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	@OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
	private List<ParticipaVO> usuarios;

	@ManyToMany
    @JoinTable(
        name = "curso_contenido",
        joinColumns = @JoinColumn(name = "idCurso"),
        inverseJoinColumns = @JoinColumn(name = "idContenidoEducativo")
    )
    private Set<ContenidoEducativoVO> contenidos = new HashSet<>(); // Inicializa el Set
	
	public Set<ContenidoEducativoVO> getContenidos() {
		return contenidos;
	}

	public void setContenidos(Set<ContenidoEducativoVO> contenidos) {
		this.contenidos = contenidos;
	}

	public int getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(int idcurso) {
		this.idcurso = idcurso;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombre) {
		this.nombreGrupo = nombre;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<ParticipaVO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<ParticipaVO> usuarios) {
		this.usuarios = usuarios;
	}

	public CursoVO() {
		this.contenidos = new HashSet<>(); // Inicializa el Set en el constructor
	}

	public CursoVO(String nombreGrupo, String imagenUrl) {
		this();
		this.nombreGrupo = nombreGrupo;
		this.imagenUrl = imagenUrl;
	}

	public CursoVO(String nombreGrupo, LocalDate fechaInicio, LocalDate fechaFin, List<ParticipaVO> usuarios) {
		this();
		this.nombreGrupo = nombreGrupo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.usuarios = usuarios;
	}
}
