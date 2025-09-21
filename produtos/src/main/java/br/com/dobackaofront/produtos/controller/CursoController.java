package br.com.dobackaofront.produtos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	/*			MATERIAL DE ESTUDO:
	 * 
	 * RESPONSE ENTITY é usado em protocolos http
	 * 
		O que é Response Entity?
	No Spring Boot, o ResponseEntity é uma 
	classe que representa toda a resposta HTTP. 
	Ela te dá controle completo sobre o que você 
	envia de volta ao cliente, incluindo o corpo da 
	resposta, os cabeçalhos HTTP e o código de status 
	(como 200 OK, 404 Not Found, 500 Internal Server 
	Error, etc.).*/
	@GetMapping
	public ResponseEntity<List<Curso>> listarTodosCursos(){
		List<Curso> cursos = cursoService.listarTodosCursos();
		return ResponseEntity.ok(cursos);
		//linha acima diz que a requisição deu certo
		

	}
	
	
	/*o pathvariable pega o que esta na rota,
	 * por exemplo:
	 * api/cursos/1
	 * ele pega o 1*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id){
		Optional<Curso> curso = cursoService.buscarCursoPorId(id);
		return curso.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

		
	}
	
	
	/*O front enviará uma requisição post(JSON), e quando chegar aqui(METODO ABAIXO),
	 * ele será transformado em objeto
	 * 
	 *  MATERIAL DE ESTUDO
	 *  O @RequestBody no Spring Boot (e em outros 
	 *  frameworks Java) faz exatamente isso: ele pega 
	 *  o corpo da requisição HTTP, que geralmente é um 
	 *  JSON ou XML, e automaticamente o converte em 
	 *  um objeto Java.

		Esse processo é chamado de deserialização. Ele 
		é fundamental para que você possa receber dados 
		de um cliente (como um navegador ou um aplicativo 
		móvel) e trabalhar com eles de forma simples e orientada 
		a objetos no seu código.*/
	
	@PostMapping
	public ResponseEntity<Curso> adicionarCurso(@RequestBody Curso curso){

		Curso novoCurso = cursoService.salvarCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso cursoAtualizado){
		Optional<Curso> curso = cursoService.buscarCursoPorId(id);
		
		//se o objeto curso esta presente(se existir)
		if(curso.isPresent()) {
			cursoAtualizado.setId(id);//o objeto nao possui id(pois sao apenas os dados novos, o id vem do auto incremento, entao nao da para atualizar), entao aqui eu coloco nele
			Curso cursoAtualizadoSalvo = cursoService.salvarCurso(cursoAtualizado);//salvo o objeto com o id
			return ResponseEntity.ok(cursoAtualizado);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirCurso(@PathVariable Long id){
		Optional<Curso> curso = cursoService.buscarCursoPorId(id);
		
		if(curso.isPresent()) {
			cursoService.excluirCurso(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
}
