package com.uacm.aktivai.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uacm.aktivai.model.Usuario;

public interface UsuarioService {

	public Usuario buscarPorMail(String mail);
	List<Usuario> buscarTodosLosUsuarios();
    Usuario buscarPorId(Long idUsuario);
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(int id);
    Page<Usuario> obtenerTodosLosUsuarioPaginados(Pageable pageable);
    public Page<Usuario> buscarPorPalabraClave(String palabraClave, Pageable pageable);
    public List<Usuario> obtenerTodosLosUsuariosConRolCliente();
    public Usuario obtenerDatosClientePorNombresApellidos(String nombres, String apellidoMaterno, String apellidoPaterno);
    public List<Usuario> buscarClientesPorPalabraClave(String palabraClave);
	
}
