package br.com.phoenix.toolschallenge.test.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.phoenix.toolschallenge.model.DescricaoTransacao;
import br.com.phoenix.toolschallenge.model.FormaPagamento;
import br.com.phoenix.toolschallenge.model.Transacao;
import br.com.phoenix.toolschallenge.model.dto.PagamentoDTO;
import br.com.phoenix.toolschallenge.model.enums.StatusTransacao;
import br.com.phoenix.toolschallenge.model.enums.TipoFormaPagamento;
import br.com.phoenix.toolschallenge.repository.TransacaoRepository;

@EnableAutoConfiguration
@AutoConfigureMockMvc
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PagamentoTestResources {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private Gson json = new GsonBuilder().create();

	@Autowired
	private MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	@AfterEach
	public void afterEach() {
		transacaoRepository.deleteAll();
	}

	@Test
	@DisplayName(value = "Teste de salvamento de processamento de um novo pagamento")
	void testSalvarUmPagamento() throws Exception {

		PagamentoDTO pagamento = new PagamentoDTO(new Transacao("0", "4444********1234",
				new DescricaoTransacao(new BigDecimal("500.50"), LocalDateTime.now(), "Pet Shop Mundo Cão"),
				new FormaPagamento(TipoFormaPagamento.AVISTA, 1L)));

		MvcResult mvcResult = mockMvc
				.perform(post("/pagamento/processar-pagamento").content(json.toJson(pagamento))
						.accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		PagamentoDTO resultado = json.fromJson(mvcResult.getResponse().getContentAsString(), PagamentoDTO.class);

		Assertions.assertNotNull(resultado.getTransacao().getId());
		Assertions.assertNotNull(resultado.getTransacao().getDescricao().getStatus());
		Assertions.assertNotNull(resultado.getTransacao().getDescricao().getNsu());
		Assertions.assertNotNull(resultado.getTransacao().getDescricao().getCodigoAutorizacao());
		Assertions.assertEquals(StatusTransacao.AUTORIZADO, resultado.getTransacao().getDescricao().getStatus());
		Assertions.assertEquals(pagamento.getTransacao().getDescricao().getEstabelecimento(),
				resultado.getTransacao().getDescricao().getEstabelecimento());
		Assertions.assertEquals(pagamento.getTransacao().getFormaPagamento().getTipo(),
				resultado.getTransacao().getFormaPagamento().getTipo());
	}

	@Test
	@DisplayName(value = "Test Erro caso consultar lista vazia")
	void testInformarErroNaSolicitacaoTodosPagamento() throws Exception {
		mockMvc.perform(get("/pagamento/listar").accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	}

	@Test
	@DisplayName(value = "Test Erro caso tentar consultar o pagamento e nao houver informacao")
	void testInformarErroBuscarPagamentoPorId() throws Exception {
		mockMvc.perform(get("/pagamento/{id}", "9999").accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	@DisplayName(value = "Test Erro caso tentar buscar estorno e nao houver informacao")
	void testInformarErroNaSolicitacaoEstorno() throws Exception {
		mockMvc.perform(get("/pagamento/pagamento-estornado/{id}", "9999").accept(APPLICATION_JSON_UTF8)
				.contentType(APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
