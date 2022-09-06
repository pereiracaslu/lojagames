package com.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.ProdutoModel;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;
import com.generention.blogpessoal.model.Postagem;




@RestController
@RequestMapping ("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> mostrarProdutos(ProdutoModel produtoModel){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());	
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> pegarPorId(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build()); 
		}
	
	
	@PostMapping
    public ResponseEntity<ProdutoModel> salvarProdutos(@Valid @RequestBody ProdutoModel produtoModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
       // return categoriaRepository.findById(produtoModel.getCategoriaModel().getId())
          //      .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel)))
            //    .orElse(ResponseEntity.badRequest().build());
  }
	@PostMapping 
	public ResponseEntity<ProdutoModel> post(@Valid @RequestBody ProdutoModel postagem){
		if(categoriaRepository.existsById(rodutoModel.getCategoria()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(postagemRepository.save(postagem));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
		
	@DeleteMapping("/{id}")
	public void deletaProduto (@PathVariable Long id) {
		Optional<ProdutoModel> produto = produtoRepository.findById(id);
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
	}
	
	@PutMapping 
	public ResponseEntity<ProdutoModel> atualizarProdutos(ProdutoModel produtoModel) { 
		return produtoRepository.findById(produtoModel.getCategoriaModel().getId())
			.map(response -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	
	
}
