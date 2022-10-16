package br.com.phoenix.toolschallenge.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phoenix.toolschallenge.java.Utils;
import br.com.phoenix.toolschallenge.java.ValidacaoUtils;
import br.com.phoenix.toolschallenge.java.ValidadorCartaoCredito;
import br.com.phoenix.toolschallenge.model.DescricaoTransacao;
import br.com.phoenix.toolschallenge.model.FormaPagamento;
import br.com.phoenix.toolschallenge.model.Transacao;
import br.com.phoenix.toolschallenge.model.dto.PagamentoDTO;
import br.com.phoenix.toolschallenge.model.enums.StatusTransacao;
import br.com.phoenix.toolschallenge.model.enums.TipoFormaPagamento;
import br.com.phoenix.toolschallenge.repository.TransacaoRepository;
import br.com.phoenix.toolschallenge.resources.exceptions.ParametroInvalidoException;
import br.com.phoenix.toolschallenge.resources.exceptions.RecursoNaoEncontradoException;

@Service
public class PagamentoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	// Busca a transação do pagamento por ID
	public PagamentoDTO buscarId(String id) {

		ValidacaoUtils.validarNaoVazio(id, "ID não informado.");
		ValidacaoUtils.validarNumerico(id, "Id informado inválido");

		Optional<Transacao> tarefa = transacaoRepository.findById(Long.parseLong(id));

		// Retornado dessa maneira para seguir o exemplo enviado
		if (tarefa.isPresent()) {
			return new PagamentoDTO(tarefa.get());
		} else {
			throw new RecursoNaoEncontradoException("Pagamento de id :" + id
					+ " não encontrado. Em caso de dúvidas Por Favor entre em contato com nosso Suporte");
		}

	}

	// Lista todas os Pagamentos
	public List<PagamentoDTO> listar() {

		List<Transacao> transacoesEncontradas = transacaoRepository.findAll();

		// Se caso não houver Transações retornar uma lista Vazia
		if (transacoesEncontradas.isEmpty()) {
			return new ArrayList<>();
		}

		List<PagamentoDTO> pagamentos = new ArrayList<>();

		for (Iterator<Transacao> transacoesIt = transacoesEncontradas.iterator(); transacoesIt.hasNext();) {
			Transacao transacao = transacoesIt.next();

			pagamentos.add(new PagamentoDTO(transacao));
		}

		// Retornado para seguir exatamente como o exemplo apresentado
		return pagamentos;
	}

	// Retorna o extorno por ID
	public PagamentoDTO buscarEstornoPorId(String id) {

		ValidacaoUtils.validarNaoVazio(id, "ID não informado.");
		ValidacaoUtils.validarNumerico(id, "Id informado inválido");

		try {

			Transacao transacao = transacaoRepository
					.findByIdAndDescricaoStatusIn(Long.parseLong(id), List.of(StatusTransacao.NEGADO))
					.orElseThrow(() -> null);

			// Retornado dessa maneira para seguir o exemplo enviado
			return new PagamentoDTO(transacao);

		} catch (Exception e) {
			// Tratamento de erro
			throw new RecursoNaoEncontradoException("Extorno de ID: " + id + " não encontrado. "
					+ "Em caso de dúvidas Por Favor entre em contato com nosso Suporte");
		}
	}

	// Realiza o processo de pagamento
	public PagamentoDTO processarPagamento(Transacao transacao) {

		// Validação Json Recebido
		this.validacaoPagamento(transacao);

		DescricaoTransacao descricao = transacao.getDescricao();

		if (descricao.getStatus() == null) {
			descricao.setStatus(StatusTransacao.AUTORIZADO);
		}

		// Códigos Gerados Aleatóriamente apenas para demonstração
		descricao.setNsu(Utils.gerarSequenciaNumerica(10));
		descricao.setCodigoAutorizacao(Utils.gerarSequenciaNumerica(6));

		transacao.setDescricao(descricao);
		transacao = transacaoRepository.save(transacao);
		return new PagamentoDTO(transacao);
	}

	// Realiza a solicitação de Extorno de processo de pagamento
	public PagamentoDTO processarSolicitarExtornoPagamento(String id) {

		PagamentoDTO pagamento = buscarId(id);

		if (pagamento.getTransacao().getDescricao().getStatus() == StatusTransacao.NEGADO) {
			throw new ParametroInvalidoException(
					"Não possivel Extornar pagamento de ID: " + id + " pois pagamento já se encontra NEGADO. "
							+ "Em caso de dúvidas por favor entre em contato com nosso Suporte");
		}

		pagamento.getTransacao().getDescricao().setStatus(StatusTransacao.NEGADO);

		pagamento.setTransacao(transacaoRepository.save(pagamento.getTransacao()));

		return pagamento;

	}

	// Validadores
	private void validacaoPagamento(Transacao transacao) {

		ValidacaoUtils.validarNaoNulo(transacao, "As informações do Pagamento devem ser informadas");

		ValidacaoUtils.validarNaoVazio(transacao.getCartao(), "O Cartão Deve Ser informado");

		// Validação de Cartão de Crédito
		ValidadorCartaoCredito.cartaoValido(transacao.getCartao());

		// Validação da descrição do Pagamento
		this.validacaoDescricao(transacao.getDescricao());

		// Validação da Forma de Pagamento da Transação
		this.validacaoFormaPagamento(transacao.getFormaPagamento());

	}

	private void validacaoDescricao(DescricaoTransacao descricao) {

		ValidacaoUtils.validarNaoNulo(descricao, "As informações da Descrição do pagamento devem ser informadas");

		ValidacaoUtils.validarNaoNulo(descricao.getValor(), "O Valor Deve Ser informado");

		// Validação se valor é Positivo
		ValidacaoUtils.valorPositivo(descricao.getValor(), "O Valor da Transação deve ser superior a 0");

		// Validações Sobre o nome do Estabelecimento
		ValidacaoUtils.validarNaoVazio(descricao.getEstabelecimento(), "O Estabelecimento Deve Ser informado");
		ValidacaoUtils.validarTamanhoMinimo(descricao.getEstabelecimento(), 3,
				"O nome do Estabelicimento deve possuir ao menos 3 Caracteres");
		ValidacaoUtils.validarTamanhoMaximo(descricao.getEstabelecimento(), 120,
				"O nome do Estabelicimento deve possuir no máximo 120 Caracteres");

		// Validação de caso a data e Hora não forem preenchias será considerado o
		// horário atual
		if (descricao.getDataHora() == null) {
			descricao.setDataHora(LocalDateTime.now());
		}

	}

	private void validacaoFormaPagamento(FormaPagamento formaPagamento) {

		ValidacaoUtils.validarNaoNulo(formaPagamento.getTipo(), "O Tipo de Forma de Pagamento Deve Ser informado");

		if (formaPagamento.getTipo() == TipoFormaPagamento.AVISTA) {
			formaPagamento.setParcelas(Long.parseLong("1"));
		} else {
			ValidacaoUtils.validarNaoNulo(formaPagamento.getParcelas(),
					"A quantidade de parcelas deve Ser informado se caso a forma de pagamento não seja á vista");
		}

	}
}
