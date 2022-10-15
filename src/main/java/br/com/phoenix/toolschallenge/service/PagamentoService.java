package br.com.phoenix.toolschallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phoenix.toolschallenge.java.Utils;
import br.com.phoenix.toolschallenge.java.ValidacaoUtils;
import br.com.phoenix.toolschallenge.model.DescricaoTransacao;
import br.com.phoenix.toolschallenge.model.FormaPagamento;
import br.com.phoenix.toolschallenge.model.Transacao;
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
	public Optional<Transacao> buscarId(String id) {

		ValidacaoUtils.validarNaoVazio(id, "ID não informado.");
		ValidacaoUtils.validarNumerico(id, "Id informado inválido");

		Optional<Transacao> tarefa = transacaoRepository.findById(Long.parseLong(id));

		return tarefa;
	}

	// Lista todas os Pagamentos
	public List<Transacao> listar() {

		return transacaoRepository.findAll();
	}

	// Retorna o extorno por ID
	public Transacao buscarEstornoPorId(String id) {

		ValidacaoUtils.validarNaoVazio(id, "ID não informado.");
		ValidacaoUtils.validarNumerico(id, "Id informado inválido");

		try {

			Transacao transacao = transacaoRepository
					.findByIdAndDescricaoStatusIn(Long.parseLong(id), List.of(StatusTransacao.NEGADO))
					.orElseThrow(() -> null);
			return transacao;

		} catch (Exception e) {
			// Tratamento de erro
			throw new ParametroInvalidoException(
					"Extorno não encontrado. Por Favor entre em contato com nosso Suporte");
		}
	}

	// Realiza o processo de pagamento
	public Transacao processarPagamento(Transacao transacao) {

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
		return transacao;
	}

	// Realiza a solicitação de Extorno de processo de pagamento
	public Transacao processarSolicitarExtornoPagamento(String id) {

		Optional<Transacao> transacao = buscarId(id);

		if (transacao.isPresent()) {
			
			transacao.get().getDescricao().setStatus(StatusTransacao.NEGADO);

			return transacaoRepository.save(transacao.get());
		} else {
			throw new RecursoNaoEncontradoException("ID informado não encontrado.");
		}

	}

	// Validadores
	private void validacaoPagamento(Transacao transacao) {

		ValidacaoUtils.validarNaoNulo(transacao, "As informações do Pagamento devem ser informadas");

		ValidacaoUtils.validarNaoVazio(transacao.getCartao(), "O Cartão Deve Ser informado");

		// Validação de Cartão de Crédito

		// Validação da descrição do Pagamento
		this.validacaoDescricao(transacao.getDescricao());

		// Validação da Forma de Pagamento da Transação
		this.validacaoFormaPagamento(transacao.getFormaPagamento());

	}

	private void validacaoDescricao(DescricaoTransacao descricao) {

		ValidacaoUtils.validarNaoNulo(descricao, "As informações da Descrição do pagamento devem ser informadas");

		ValidacaoUtils.validarNaoNulo(descricao.getValor(), "O Valor Deve Ser informado");

		// Validação se valor é Positivo

		ValidacaoUtils.validarNaoVazio(descricao.getEstabelecimento(), "O Estabelecimento Deve Ser informado");

		// Validação de caso a data e Hora não forem preenchias será considerado o
		// horário atual
		if (descricao.getDataHora() == null) {
		}

	}

	private void validacaoFormaPagamento(FormaPagamento formaPagamento) {

		ValidacaoUtils.validarNaoNulo(formaPagamento.getTipo(), "O Tipo de Forma de Pagamento Deve Ser informado");

		if (formaPagamento.getTipo() == TipoFormaPagamento.AVISTA) {
			formaPagamento.setParcelas(new Long(1));
		} else {
			ValidacaoUtils.validarNaoNulo(formaPagamento.getParcelas(),
					"A quantidade de parcelas deve Ser informado se caso a forma de pagamento não seja á vista");
		}

	}
}
