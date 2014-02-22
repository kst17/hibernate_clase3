package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

//Registra la clase en el contexto de spring
@Component("usuarioDAO")
public class HibernateUsuarioDAO implements UsuarioDAO {
	@Autowired
	private SessionFactory sessionFactory; 
	
	public Usuario buscaUsuarioPorId(Integer usuarioId) {
		//Abre sesion 
		Session sesion = sessionFactory.openSession();
		//Etapas de la transaccion
		sesion.beginTransaction();//iniciando
		//Uso de reflections para acceder a la clase Usuario
		Usuario usuario = (Usuario) sesion.get(Usuario.class, usuarioId);//De la tabla usuario obtener el id
		
		sesion.getTransaction().commit();//confirmando transaccion
		sesion.close();//Cerrando transaccion
		
		return usuario;
	}

	public void creaUsuario(Usuario usuario) {
		//Abre sesion 
		Session sesion = sessionFactory.openSession();
		//Etapas de la transaccion
		sesion.beginTransaction();//iniciando
		sesion.saveOrUpdate(usuario);//procesando
		sesion.getTransaction().commit();//confirmando transaccion
		sesion.close();//Cerrando transaccion
	}

	public Usuario buscaPorEmailYPassword(String email, String password) {
		
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		
		//Estableciendo el criterio de la consulta
		Criteria criterio = sesion.createCriteria(Usuario.class);
		//los nombres de los campos serian realmente los definidos en el bean de java
		criterio.add(Restrictions.or(Restrictions.eq("email", email),//or es un varArgs es decir recibe una numero no especificado de argumentos 
					 				  Restrictions.eq("password", password)));
		
		//CRITERIO QUE POR DEFAULT USA LA OPERACION AND
//		criterio.add(Restrictions.eq("email", email));
//		criterio.add(Restrictions.eq("password", password));
		
		//Si no encuentra nada regresa un null
		Usuario usuario = (Usuario) criterio.uniqueResult();
		
		sesion.getTransaction().commit();
		sesion.close();
		//System.out.println("Este es el usuario: " + usuario);
		
		return usuario;
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		
		//Estableciendo el criterio de la consulta
		Criteria criterio = sesion.createCriteria(Usuario.class);
		//los nombres de los campos serian realmente los definidos en el bean de java
		criterio.add(Restrictions.like("nombre", "%" + nombre + "%"));
		
		//Si no encuentra nada regresa un null
		List<Usuario> resultado= criterio.list();
		
		sesion.getTransaction().commit();
		sesion.close();
		
		
		return resultado;
	}
	
	

}
