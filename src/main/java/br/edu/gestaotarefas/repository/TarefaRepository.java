package br.edu.gestaotarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.gestaotarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByOrderByPrazoAsc();
}
