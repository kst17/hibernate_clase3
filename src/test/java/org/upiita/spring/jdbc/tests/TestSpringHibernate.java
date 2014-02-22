package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");

		//Obtenemos el bean de hibernate usuarioDAO
		UsuarioDAO usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(3);
		usuario.setNombre("Beto");
		usuario.setEmail("beto@email.com");
		usuario.setPassword("hola");
		usuarioDAO.creaUsuario(usuario);
		
		usuario.setPassword("1234");
		
		usuarioDAO.creaUsuario(usuario);
		System.out.println("Datos guardados");
		
		Usuario usuarioBd = usuarioDAO.buscaUsuarioPorId(3);
		System.out.println("ahi esta: " + usuarioBd);
		
		Usuario usuarioCrit = usuarioDAO.buscaPorEmailYPassword("beto@email.com", "hola");
		System.out.println("usuario Criteria: " + usuarioCrit);
		
		List<Usuario> listaUsuarios = usuarioDAO.buscaPorNombre("z");
		
		for(Usuario item : listaUsuarios){
			System.out.println("usuario: " + item);
		}
	}

}
