package br.com.phoenix.toolschallenge.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phoenix.toolschallenge.model.Transacao;
import br.com.phoenix.toolschallenge.model.dto.PagamentoDTO;
import br.com.phoenix.toolschallenge.service.PagamentoService;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/pagamento", consumes = "application/json", produces = "application/json")
public class PagamentoResources {

	@Autowired
	private PagamentoService pagamentoService;

	@ApiOperation(value = "Processa um Pagamento e retona o Pagamento DTO Autorizado")
	@PostMapping(value = "/processar-pagamento")
	public ResponseEntity<?> processarPagamento(@Valid @RequestBody Transacao transacao) {
		PagamentoDTO response = pagamentoService.processarPagamento(transacao);
		return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Retona um Pagamento DTO por ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPagamentoById(@PathVariable(required = false) String id) {
		PagamentoDTO response = pagamentoService.buscarId(id);
		return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Retona a Lista Completa de Pagamentos DTO")
	@GetMapping(value = "/listar")
	public ResponseEntity<?> getAllPagamento() {
		List<PagamentoDTO> response = pagamentoService.listar();
		return response.isEmpty() ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Extorna um Pagamento DTO Autorizado por ID")
	@PutMapping(value = "/estornar/{id}")
	public ResponseEntity<?> estornarPagamentoById(@PathVariable(required = false) String id) {
		PagamentoDTO response = pagamentoService.processarSolicitarExtornoPagamento(id);
		return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Retona um Pagamento DTO Extornado por ID")
	@GetMapping(value = "/pagamento-estornado/{id}")
	public ResponseEntity<?> getEstornoById(@PathVariable(required = false) String id) {
		PagamentoDTO response = pagamentoService.buscarEstornoPorId(id);
		return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
	}

}
