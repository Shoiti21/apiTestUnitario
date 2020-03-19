package br.com.lojaCasaShow.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Venda {
	@ApiModelProperty(notes = "ID da venda", example = "1", position = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ApiModelProperty(notes = "ID do usuário", position = 2)
    @ManyToOne
    @JoinColumn
	private Usuario user;
	@ApiModelProperty(notes = "Data da venda", example = "DD/MM/YYYY", position = 4)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	@ApiModelProperty(notes = "ID do Evento", example = "1", position = 5)
    @ManyToOne
    @JoinColumn
	private Evento evento;
	@ApiModelProperty(hidden=true)
	@JsonInclude(Include.ALWAYS)
	private UUID ticket;
	public Venda(Long id, Usuario user, Date data, Evento evento) {
		this.id=id;
		this.user=user;
		this.data=data;
		this.evento=evento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public UUID getTicket() {
		return ticket;
	}
	public void setTicket(UUID ticket) {
		this.ticket = ticket;
	}
}
