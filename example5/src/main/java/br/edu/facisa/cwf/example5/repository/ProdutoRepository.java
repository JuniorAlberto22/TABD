package br.edu.facisa.cwf.example5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.facisa.cwf.example5.domain.Produto;
import br.edu.facisa.cwf.example5.domain.User;

public interface ProdutoRepository extends JpaRepository<Produto, String>{

	public Produto findByNome(String nome);
	
}
