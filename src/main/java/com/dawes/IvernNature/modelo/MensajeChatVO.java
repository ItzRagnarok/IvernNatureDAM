package com.dawes.IvernNature.modelo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "mensajes_chat")
public class MensajeChatVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties({"mensajes", "contenidos", "quizzes", "usuarios", "eventos"})
    private CursoVO curso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({
        "password", "password2", "cursos", "eventos", "temas", "respuestas", "rol"
    })
    private UsuarioVO autor;

    @Column(length = 1000)
    private String texto;

    private LocalDateTime fecha = LocalDateTime.now();

    
}
