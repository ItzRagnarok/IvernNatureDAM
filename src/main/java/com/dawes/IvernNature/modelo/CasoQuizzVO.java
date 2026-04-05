package com.dawes.IvernNature.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "casos_quizz")
public class CasoQuizzVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcasoquizz;

    private String solucion; 
    private String texto;
    private String img; 
    private String mensajeExito;
    private String pistaError;

    @ManyToOne
    @JoinColumn(name = "idquizz")
    @JsonIgnore
    private QuizzVO quizz; 
}