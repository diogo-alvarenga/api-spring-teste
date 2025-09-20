package br.com.dobackaofront.produtos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dobackaofront.produtos.entity.Curso;
import br.com.dobackaofront.produtos.repository.CursoRepository;

@Service
public class CursoService {
	
	private final CursoRepository cursoRepository;
	
	@Autowired 
	//quem gerencia o ciclo de vida do objeto é o spring
	public CursoService(CursoRepository cursoRepository) {
		//inicializando o objeto cursoRepository
		this.cursoRepository = cursoRepository;
	}
	
	/*o front manda um objeto em json para o controller
	 * o controller recebe e manda o json para o Service
	 * o service manda para o repository(interface)*/
	public Curso salvarCurso(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	
	/* OPTIONAL = estrutura que o spring entende 
	 * que irei trabalhar com ela depois
	 * 
	 * O Optional é uma classe do Java (a partir da versão 8) 
	 * que atua como uma "caixa" ou container para um objeto. 
	 * O principal objetivo do Optional é ajudar a evitar o 
	 * erro mais comum e temido em Java: o NullPointerException.

		Em vez de um método retornar null (ou "nada"), ele retorna 
		um Optional que pode conter o objeto que você procura ou 
		estar vazio.*/
	
	public Optional<Curso> buscarCursoPorId(Long id){
		return cursoRepository.findById(id);
	}
	
	public List<Curso> listarTodosCursos(){
		return cursoRepository.findAll();
	}
	
	public void excluirCurso(Long id) {
		cursoRepository.deleteById(id);
	}
	
}
