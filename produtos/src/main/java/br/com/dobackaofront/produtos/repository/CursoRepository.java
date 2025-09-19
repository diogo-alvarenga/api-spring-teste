package br.com.dobackaofront.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dobackaofront.produtos.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{//prieiro parametro: classe que irá gerenciar(Classe Curso(a classe da entidade)), segundo parametro: o identificador(atributo id) da classe Curso 
	//a interface trabalha a parte de armazenamento, atualização, deleção de dados da entidade curso
	//ela gerencia cursos atraves do id
	//para metodo de deletar é só informar o id
	//para metodo de atualizar, é só informar o objeto(entidade) e o id
	//para o metodo de criar, é so informar o objeto(entidade)
	//para o metodo de pesquisar, é só informar o id
	
	
}
