package com.generation.lojagames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name ="tb_categoria")
public class CategoriaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size (min=1, max=200)
	@NotBlank
	private String nomeCategoria;
	
	@Size (min=1, max= 1000)
	@NotBlank
	private String descricaoCategoria;

	@OneToMany (mappedBy = "categoriaModel", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("categoriaModel")
	private List<ProdutoModel> produtoModel;
	
	

	

	public List<ProdutoModel> getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(List<ProdutoModel> produtoModel) {
		this.produtoModel = produtoModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
	
	
	
	
	
	
	
}
