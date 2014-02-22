package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.upiita.spring.jdbc.entidades.Usuario;

public interface UsuarioDAO {

	public Usuario buscaUsuarioPorId(Integer usuarioId);

	public void creaUsuario(Usuario usuario);
	
	//Si se quita el public en una interface es lo mismo por que por default una interfaz queremos que sea publica
	public Usuario buscaPorEmailYPassword(String email, String password);
	
	public List<Usuario> buscaPorNombre(String nombre);

}
