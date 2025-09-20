package br.com.dobackaofront.produtos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.dobackaofront.produtos.entity.Curso;
import br.com.dobackaofront.produtos.service.CursoService;
import ch.qos.logback.core.model.Model;

//@Controller
/*@ResponseBody/*O @ResponseBody serve para retornar objetos
 (ou qualquer tipo de dado) para o cliente*/
@RestController/*para trabalhar apenas com o envio e recebimento de arquivos de texto como json para ser consumido na api e retornar para o front*/
@RequestMapping(value="/api/cursos")//definir a rota
public class CursoController {
	
	private final CursoService cursoService;
	
	@Autowired
	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	@GetMapping
	public ResponseEntity<List<Curso>> listarTodosCursos(){
		List<Curso> cursos = cursoService.listarTodosCursos();
		return ResponseEntity.ok(cursos);
		//linha acima diz que a requisição deu certo
		
		/*MATERIAL DE ESTUDO:
		 * 
		 * Response entity é usado em protocolos http
			O que é Response Entity?
		No Spring Boot, o ResponseEntity é uma 
		classe que representa toda a resposta HTTP. 
		Ela te dá controle completo sobre o que você 
		envia de volta ao cliente, incluindo o corpo da 
		resposta, os cabeçalhos HTTP e o código de status 
		(como 200 OK, 404 Not Found, 500 Internal Server 
		Error, etc.).*/
	}
	
	@GetMapping
	public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id){
		Optional<Curso> curso = cursoService.buscarCursoPorId(id);
		return curso.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		/*o pathvariable pega o que esta na rota,
		 * por exemplo:
		 * api/cursos/1
		 * ele pega o 1*/
		
	}
}
