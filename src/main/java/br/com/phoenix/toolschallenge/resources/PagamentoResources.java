package br.com.phoenix.toolschallenge.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phoenix.toolschallenge.model.Transacao;
import br.com.phoenix.toolschallenge.service.PagamentoService;

@Controller
@RestController
@RequestMapping(value = "/pagamento", consumes ="application/json", produces = "application/json")
public class PagamentoResources {
	
	@Autowired
	private PagamentoService pagamentoService;

	@GetMapping("/")
    public ResponseEntity<String> helloWord() {
        return ResponseEntity.ok("Bem vindo a API de Pagamentos");
    }

    @PostMapping(value = "/processar-pagamento")
    public ResponseEntity<?> processarPagamento(@Valid @RequestBody Transacao transacao) {
        Transacao response = pagamentoService.processarPagamento(transacao);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPagamentoById(@PathVariable String id) {
    	Optional<Transacao> response = pagamentoService.buscarId(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAllPagamento() {
        List<Transacao> response = pagamentoService.listar();
        return response.isEmpty() ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
    
    @PutMapping(value = "/estornar/{id}")
    public ResponseEntity<?> estornarPagamentoById(@PathVariable String id) {
        Transacao response = pagamentoService.processarSolicitarExtornoPagamento(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping(value = "/estorno/{id}")
    public ResponseEntity<?> getEstornoById(@PathVariable String id) {
        Transacao response = pagamentoService.buscarEstornoPorId(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

}
