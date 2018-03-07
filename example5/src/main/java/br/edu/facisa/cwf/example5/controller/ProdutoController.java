package br.edu.facisa.cwf.example5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.facisa.cwf.example5.domain.Produto;
import br.edu.facisa.cwf.example5.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listarTodosProdutos() {

		List<Produto> listaUsuarios = produtoService.listarTodosProdutos();

		HttpStatus status;

		status = listaUsuarios != null && !listaUsuarios.isEmpty() 
				? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<List<Produto>>(listaUsuarios, status);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> retornarProduto(@PathVariable String id) {

		Produto produto = produtoService.getById(id);

		return produto == null ? new ResponseEntity<Produto>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> criarProduto(@RequestBody Produto produto) {

		HttpStatus status;
		
		try {
			produtoService.salvar(produto);
			status = HttpStatus.CREATED;

		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(status);
	}
	
	@RequestMapping(value = "/atualizado", method = RequestMethod.PUT)
	public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto) {
		
		try {
			return new ResponseEntity<Produto>(produtoService.atualizar(produto), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Produto>(produto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> removerProduto(@RequestBody Produto produto) {

		HttpStatus status;
		
		try {
			if(produtoService.remover(produto)) {
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.UNPROCESSABLE_ENTITY;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(status);
	}

	public ProdutoService getUserService() {
		return produtoService;
	}
}
