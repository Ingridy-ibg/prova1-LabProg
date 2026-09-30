package br.edu.gestaotarefas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tarefa")
public class Tarefa {

    private static final String CARACTERES_PERMITIDOS = "^[\\p{L} \\p{N}.,!?:()/\\-]*$";
    private static final String MSG_CARACTERES = "Use apenas letras, números, espaços e pontuação básica (. , ! ? : - ( ))";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    @Pattern(regexp = CARACTERES_PERMITIDOS, message = MSG_CARACTERES)
    @Column(nullable = false, length = 100)
    private String titulo;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    @Pattern(regexp = CARACTERES_PERMITIDOS, message = MSG_CARACTERES)
    @Column(length = 500)
    private String descricao;

    @NotNull(message = "O prazo de conclusão é obrigatório")
    @FutureOrPresent (message = "O prazo não pode ser uma data no passado")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private LocalDate prazo;

    @Column(nullable = false)
    private boolean concluida = false;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}
