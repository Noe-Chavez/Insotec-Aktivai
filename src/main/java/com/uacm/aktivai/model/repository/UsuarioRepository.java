package com.uacm.aktivai.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uacm.aktivai.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByMail(String mail);
	
	@Query(value = "SELECT * FROM usuario u WHERE (u.nombre LIKE %?1%) OR (u.paterno LIKE %?1%) OR (u.materno LIKE %?1%)", nativeQuery = true)
    public Page<Usuario> buscarPorPalabraClave(String palabraClave, Pageable pageable);
	
	public Optional<Usuario> findById(Long id);

	@Query(value = "SELECT * FROM usuario JOIN usuario_roles ON usuario.id = usuario_roles.usuario_id JOIN rol ON usuario_roles.roles_id = rol.id WHERE roles_id = 5;", nativeQuery = true)
	public List<Usuario> obtenerTodosLosUsuariosConRolCliente();

	//TODO temporal para poder vincular el servicio
	@Query(value = "SELECT * FROM USUARIO Cliente WHERE (Cliente.nombre LIKE %?1%) AND (Cliente.paterno LIKE %?2%) AND (Cliente.materno LIKE %?3%);", nativeQuery = true)
	public Usuario obtenerDatosClientePorNombresApellidos(String nombres, String apellidoMaterno, String apellidoPaterno);

	// Buscar cliente por palabra clave (Sin paginación).
	@Query(value = "SELECT u.* \n" +
			"FROM usuario u\n" +
			"INNER JOIN usuario_roles r\n" +
			"ON u.id = r.usuario_id\n" +
			"WHERE ((u.nombre LIKE %?1%) OR (u.materno LIKE %?1%) OR (u.paterno LIKE %?1%)) AND r.roles_id = '5'", nativeQuery = true)
	public List<Usuario> buscarClientesPorPalabraClave(String palabraClave);

}
