package com.dawes.IvernNature.modelo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@Data
@Entity
@Table(name = "contenidoseducativos")
public class ContenidoEducativoVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcontenidoeducativo;
	private String titulo;
	@Column(name = "tipo_archivo")
	private String tipoArchivo;
	@Column(unique = true)
	private String url;

	@ToString.Exclude
    @EqualsAndHashCode.Exclude
	@ManyToMany(mappedBy = "contenidos")
    private Set<CursoVO> cursos = new HashSet<>(); // Inicializa el Set
	
	public Set<CursoVO> getCursos() {
		return cursos;
	}

	public void setCursos(Set<CursoVO> cursos) {
		this.cursos = cursos;
	}

	public int getIdcontenidoeducativo() {
		return idcontenidoeducativo;
	}

	public void setIdcontenidoeducativo(int idcontenidoeducativo) {
		this.idcontenidoeducativo = idcontenidoeducativo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ContenidoEducativoVO() {
		this.cursos = new HashSet<>(); // Inicializa el Set en el constructor
	}

    public ContenidoEducativoVO(String titulo, String tipoArchivo, String url) {
        this();
        this.titulo = titulo;
        this.tipoArchivo = tipoArchivo;
        this.url = url;
    }
}
