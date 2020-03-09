package br.com.lojaCasaShow.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority{
	ROLE_MASTER,
	ROLE_ADMIN,
	ROLE_CLIENT;

	@Override
	public String getAuthority() {
		return name();
	}
}
