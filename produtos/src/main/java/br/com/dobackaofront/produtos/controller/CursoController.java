package br.com.dobackaofront.produtos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;

@Controller
@ResponseBody/*O @ResponseBody serve para retornar objetos
 (ou qualquer tipo de dado) para o cliente*/
@RequestMapping(value="/cursos")//definir a rota
public class CursoController {
	
	@GetMapping
	public ModelAndView cursoJavaNaPratica() {
		ModelAndView template = new ModelAndView("index");//passando oendereço/nome da pagina como parametro
		return template;
	}
}
