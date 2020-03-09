package br.com.lojaCasaShow.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a person tracked by the application.")
@Entity
public class Usuario implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4956740313319129540L;
	@ApiModelProperty(notes = "ID do usuário", example = "1", position = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ApiModelProperty(notes = "Username do usuário", example = "Leandro", position = 2)
	@NotEmpty(message = "username não pode ser vazio!")
	@NotNull(message = "username não pode ser null!")
	@Column(unique = true)
	private String username;
	@ApiModelProperty(notes = "Senha do usuário", example = "123456s3nh4", position = 3)
	@NotEmpty(message = "senha não pode ser vazio!")
	@NotNull(message = "senha não pode ser null!")
	@JsonInclude(Include.ALWAYS)
	private String senha;
	@ApiModelProperty(notes = "Tipo de permissão do usuário", example = "ROLE_CLIENT", position = 4)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Roles role;
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="user")
	@JsonIgnore
	private List<Venda> venda;
	
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public List<Venda> getvenda() {
		return venda;
	}
	public void setvenda(List<Venda> venda) {
		this.venda = venda;
	}
	public String getSenha() {
		return senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@ApiModelProperty(hidden=true)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Roles> lista = Arrays.asList(role);
		return (Collection<? extends GrantedAuthority>) lista;
	}
	@Override
	public String getUsername() {
		return this.username;
	}
	@ApiModelProperty(hidden=true)
	@Override
	public String getPassword() {
		return this.senha;
	}
	@ApiModelProperty(hidden=true)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@ApiModelProperty(hidden=true)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@ApiModelProperty(hidden=true)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@ApiModelProperty(hidden=true)
	@Override
	public boolean isEnabled() {
		return true;
	}
}
