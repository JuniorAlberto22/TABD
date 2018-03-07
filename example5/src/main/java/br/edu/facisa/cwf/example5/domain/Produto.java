package br.edu.facisa.cwf.example5.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	private String id;
	private String nome;
	private Float valor;
	private Float descontoAVista;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Float getDescontoAVista() {
		return descontoAVista;
	}

	public void setDescontoAVista(Float descontoAVista) {
		this.descontoAVista = descontoAVista;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
