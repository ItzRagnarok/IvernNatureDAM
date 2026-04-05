package com.dawes.IvernNature.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "quizzes")
public class QuizzVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idquizz;

    private String titulo; 

    // Un quizz pertenece a un curso
    @ManyToOne
    @JoinColumn(name = "idcurso")
    @JsonIgnore
    private CursoVO curso;

    // Un quizz tiene muchas preguntas/casos
    @OneToMany(mappedBy = "quizz", cascade = CascadeType.ALL)
    private List<CasoQuizzVO> casos;
}