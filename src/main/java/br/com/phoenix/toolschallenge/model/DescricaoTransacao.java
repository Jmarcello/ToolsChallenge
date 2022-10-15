package br.com.phoenix.toolschallenge.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.phoenix.toolschallenge.model.enums.StatusTransacao;

@Entity
@Table(name = "DescricaoTransacao")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DescricaoTransacao {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long id;

	@Positive
	private BigDecimal valor;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime dataHora;

	@NotBlank
	private String estabelecimento;

	private String nsu;
	private String codigoAutorizacao;
	private StatusTransacao status;

	public DescricaoTransacao() {
	}

	public DescricaoTransacao(BigDecimal valor, LocalDateTime dataHora, String estabelecimento) {
		this.valor = valor;
		this.dataHora = dataHora;
		this.estabelecimento = estabelecimento;
	}

	public DescricaoTransacao(BigDecimal valor, LocalDateTime dataHora, String estabelecimento, String nsu,
			String codigoAutorizacao, StatusTransacao status) {
		this.valor = valor;
		this.dataHora = dataHora;
		this.estabelecimento = estabelecimento;
		this.nsu = nsu;
		this.codigoAutorizacao = codigoAutorizacao;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHoram) {
		this.dataHora = dataHoram;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public void setStatus(StatusTransacao status) {
		this.status = status;
	}
}
