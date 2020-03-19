package br.com.lojaCasaShow.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Evento {
	@ApiModelProperty(notes = "ID do Evento", example = "1", position = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ApiModelProperty(notes = "Nome do Evento", example = "User", position = 2)
	@NotEmpty
	@NotNull
	private String nome;
	@ApiModelProperty(notes = "Tipo de gênero do Evento", example = "ROCK", position = 3)
	@Enumerated(EnumType.STRING)
	private Genero genero;
	@ApiModelProperty(notes = "ID da casa", position = 1)
    @ManyToOne
    @JoinColumn
	private Casa casa;
	@ApiModelProperty(notes = "Data do Evento", example = "DD/MM/YYYY", position = 4)
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	@ApiModelProperty(notes = "Capacidade do Evento", example = "100", position = 5)
	@NotEmpty
    @NotNull
	private int qtdIngressoDisp;
	@ApiModelProperty(notes = "Preço do Evento", example = "0.00", position = 6)
	@NotEmpty
	@NotNull
    private BigDecimal preco;
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="evento")
	@JsonIgnore
	private List<Venda> venda;
	public Evento(Long id, String nome, Genero genero, Casa casa, Date data, int qtdIngressoDisp, BigDecimal preco) {
		this.id=id;
		this.nome=nome;
		this.genero=genero;
		this.casa=casa;
		this.data=data;
		this.qtdIngressoDisp=qtdIngressoDisp;
		this.preco=preco;
	}
	public List<Venda> getVenda() {
		return venda;
	}
	public void setVenda(List<Venda> venda) {
		this.venda = venda;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Casa getCasa() {
		return casa;
	}
	public void setCasa(Casa casa) {
		this.casa = casa;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getQtdIngressoDisp() {
		return qtdIngressoDisp;
	}
	public void setQtdIngressoDisp(int qtdIngressoDisp) {
		this.qtdIngressoDisp = qtdIngressoDisp;
	}
}
