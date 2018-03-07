package br.edu.facisa.cwf.example5.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.edu.facisa.cwf.example5.domain.Produto;
import br.edu.facisa.cwf.example5.domain.User;
import br.edu.facisa.cwf.example5.exceptions.UserAlreadyExistsException;
import br.edu.facisa.cwf.example5.repository.ProdutoRepository;

@Service
@Validated
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	 public Produto getById(String id) {    
	    	return repository.findOne(id);
    }
    
	public List<Produto> listarTodosProdutos() {
		return repository.findAll();
	}
	
    @Transactional
    public Produto salvar(@NotNull @Valid final Produto produto) throws Exception {
    	
        Produto existing = repository.findOne(produto.getNome());

        if (existing != null) {
            throw new UserAlreadyExistsException(String.format("There already exists a participant with email=%s", produto.getNome()));
        }

        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(@NotNull @Valid final Produto produto) throws Exception {
    	
    	Produto existing = getById(produto.getId());

        if (existing != null) {
        	produto.setId(existing.getId());
        	return repository.save(produto);
        }
        return produto;
    }
    
    @Transactional
    public Boolean remover(@NotNull @Valid final Produto produto) throws Exception {
    	
    	Produto existing = getById(produto.getId());

        if (existing != null) {
        	produto.setId(existing.getId());
        	repository.delete(produto);
        	return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
	public ProdutoRepository getRepository() {
		return repository;
	}

	public void setRepository(ProdutoRepository repository) {
		this.repository = repository;
	}

}
