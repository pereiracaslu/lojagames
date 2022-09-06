package com.generation.lojagames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "tb_produtos")
public class ProdutoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(min = 1, max = 255)
	private String nomeProduto;
//	@NotBlank
	//private double preco;
//	@NotBlank
	//private int quantidade;
	
	
	
	
	public CategoriaModel getCategoriaModel() {
		return categoriaModel;
	}
	public void setCategoriaModel(CategoriaModel categoriaModel) {
		this.categoriaModel = categoriaModel;
	}
	@ManyToOne
	@JsonIgnoreProperties ("produtoModel")
	private CategoriaModel categoriaModel;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
//	public double getPreco() {
//		return preco;
//	}
//	public void setPreco(double preco) {
//		this.preco = preco;
//	}
//	public int getQuantidade() {
//		return quantidade;
//	}
//	public void setQuantidade(int quantidade) {
//		this.quantidade = quantidade;
//	}

}
