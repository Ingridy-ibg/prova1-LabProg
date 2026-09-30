package br.edu.gestaotarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import br.edu.gestaotarefas.model.Tarefa;
import br.edu.gestaotarefas.repository.TarefaRepository;

import jakarta.validation.Valid;

@Controller
public class TarefaController {

    private final TarefaRepository repository;

    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/tarefas";
    }

    @GetMapping("/tarefas")
    public String listar(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        model.addAttribute("tarefas", repository.findAllByOrderByPrazoAsc());
        return "tarefas";
    }

    @PostMapping("/tarefas")
    public String cadastrar(@Valid @ModelAttribute("tarefa") Tarefa tarefa,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
             model.addAttribute("tarefas", repository.findAllByOrderByPrazoAsc());
             return "tarefas";
        }
        tarefa.setId(null);
        tarefa.setConcluida(false);
        repository.save(tarefa);
        return "redirect:/tarefas";
    }

    @PostMapping("/tarefas/{id}/concluir")
    public String alternarConclusao(@PathVariable Long id) {
        repository.findById(id).ifPresent(t -> {
            t.setConcluida(!t.isConcluida());
            repository.save(t);
        });
        return "redirect:/tarefas";
    }

    @PostMapping("/tarefas/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/tarefas";
    }
}
