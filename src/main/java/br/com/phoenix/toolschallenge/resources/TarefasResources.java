package br.com.phoenix.toolschallenge.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.phoenix.toolschallenge.model.Tarefa;
import br.com.phoenix.toolschallenge.repository.TarefasRepository;
import br.com.phoenix.toolschallenge.service.TarefaService;

//Code By: João Marcello Cardoso - 06/11/2021
//New Version: 27/11/2021 

@Controller
@RestController
public class TarefasResources {

	@Autowired
	private TarefasRepository tarefasRepository;

	@Autowired
	private TarefaService tarefaService;

	@GetMapping("/listar")
	public List<Tarefa> showList() {

		return tarefaService.listar();
	}


//	@GetMapping("/listar-cumpridas")
//	public List<Tarefa> showCumpridas() {
//
//		return tarefaService.buscarCumpridas();
//	}

//	@GetMapping("/listar-nao-cumpridas")
//	public List<Tarefa> showNaoCumpridas() {
//
//		return tarefaService.buscarNaoCumpridas();
//	}

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Tarefa salvar(@RequestBody Tarefa tarefa) {

		return tarefaService.salvar(tarefa);
	}

	@PutMapping("/atualizar")
	@ResponseStatus(HttpStatus.CREATED)
	public Tarefa atualizar(@RequestBody Tarefa tarefa) {

		return tarefaService.atualizarTarefa(tarefa);
	}

	@PatchMapping("/modificar-cumprida/{id}")
	public Optional<Tarefa> modificarCumprida(@PathVariable("id") Long id) {

		return tarefaService.marcarCumprida(id);
	}
	
	@PatchMapping("/cancelar/{id}")
	public Optional<Tarefa> cancelarTarefa(@PathVariable("id") Long id) {

		return tarefaService.cancelarTarefa(id);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {

		tarefasRepository.deleteById(id);
	}
}
