package br.com.rrp.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import br.com.rrp.java.Data;

@javax.persistence.Entity
public class Tarefa {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String dataMaxima;

	private String dataMaximaPorExtenso = Data.getData("dd/MM/yyyy HH:mm:ss");

	private String dataCriacaoTarefa = Data.getData("dd/MM/yyyy HH:mm:ss");

	private String dataCriacaoPorExtenso;

	private String observacao;

	private String autor;

	private int diasParaRealizarTarefa;

	private SituacaoTarefa situacaoTarefa = SituacaoTarefa.EM_ABERTO;

	private boolean cumprida = false;

	private boolean vencida = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataMaxima() {
		return dataMaxima;
	}

	public void setDataMaxima(String dataMaxima) {
		this.dataMaxima = dataMaxima;
	}

	public boolean isCumprida() {
		return cumprida;
	}

	public void setCumprida(boolean cumprida) {
		this.cumprida = cumprida;
	}

	public boolean isVencida() {
		return vencida;
	}

	public void setVencida(boolean vencida) {
		this.vencida = vencida;
	}

	public SituacaoTarefa getSituacaoTarefa() {
		return situacaoTarefa;
	}

	public void setSituacaoTarefa(SituacaoTarefa situacaoTarefa) {
		this.situacaoTarefa = situacaoTarefa;
	}

	public String getDataCriacaoPorExtenso() {
		return dataCriacaoPorExtenso;
	}

	public void setDataCriacaoPorExtenso(String dataCriacaoPorExtenso) {
		this.dataCriacaoPorExtenso = dataCriacaoPorExtenso;
	}

	public String getDataMaximaPorExtenso() {
		return dataMaximaPorExtenso;
	}

	public void setDataMaximaPorExtenso(String dataMaximaPorExtenso) {
		this.dataMaximaPorExtenso = dataMaximaPorExtenso;
	}

	public String getDataCriacaoTarefa() {
		return dataCriacaoTarefa;
	}

	public void setDataCriacaoTarefa(String dataCriacaoTarefa) {
		this.dataCriacaoTarefa = dataCriacaoTarefa;
	}

	public int getDiasParaRealizarTarefa() {
		return diasParaRealizarTarefa;
	}

	public void setDiasParaRealizarTarefa(int diasParaRealizarTarefa) {
		this.diasParaRealizarTarefa = diasParaRealizarTarefa;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
