package com.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> mostrarProdutos(ProdutoModel produtoModel) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}
	@GetMapping("/{nomeProduto}")
	public ResponseEntity<List<ProdutoModel>> mostrarProdutosPorNome(@PathVariable String nomeProduto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> pegarPorId(@PathVariable Long id) {
		return produtoRepository.findById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ProdutoModel> salvarProdutos(@Valid @RequestBody ProdutoModel produtoModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
//		return categoriaRepository.findById(bulhufas.getCategoriaModel().getId())
//				.map(response -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(bulhufas)))
//				.orElse(ResponseEntity.badRequest().build());
	}
	
	@DeleteMapping("/{id}")
	public void apagarProduto(@PathVariable Long id) {
		Optional<ProdutoModel> produtoModel = produtoRepository.findById(id);
		if (produtoModel.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModel> atualizarProduto(@RequestBody @Valid ProdutoModel produtoModel){
		return produtoRepository.findById(produtoModel.getCategoriaModel().getId())
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel)))
				.orElse(ResponseEntity.notFound().build());
		
//		if(produtoRepository.existsById(produtoModel.getId())) {
//			return categoriaRepository.findById(produtoModel.getCategoriaModel().getId())
//					.map(response -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel)))
//					.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
//		}
//		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		
//	}

}
}