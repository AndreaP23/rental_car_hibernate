package com.jwt.hibernate.bean;

import java.util.LinkedList;
import java.util.List;

public class User {
	private int id;
	private String nome;
	private String cognome;
	private String password;
	private String email;
	private String telefono;
	private String dataNascita; 
	private String ruolo;
	private List<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public String getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public User() {
		this.users= new LinkedList<User>();
	}
	
	public void setLista(User user) {
		users.add(user);
	}
	
	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome="+ nome + ", cognome=" + cognome + ", email=" + email + "]";
	}

}