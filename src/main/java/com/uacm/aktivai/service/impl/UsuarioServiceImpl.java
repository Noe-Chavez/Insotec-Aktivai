package com.uacm.aktivai.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uacm.aktivai.model.Rol;
import com.uacm.aktivai.model.Usuario;
import com.uacm.aktivai.model.repository.UsuarioRepository;
import com.uacm.aktivai.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario buscarPorMail(String mail) {
		return usuarioRepository.findByMail(mail);
	}
	
	@Override
    public List<Usuario> buscarTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long idUsuario) {
        for (Usuario usuario: usuarioRepository.findAll())
            if (usuario.getId() == idUsuario) return usuario;
        return null;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {
    	Long idLong = (long) id;
        Optional<Usuario> usuarioAEliminar = usuarioRepository.findById(idLong);
        usuarioAEliminar.ifPresent(usuario -> usuarioRepository.delete(usuario));
    }

    @Override
    public Page<Usuario> obtenerTodosLosUsuarioPaginados(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Page<Usuario> buscarPorPalabraClave(String palabraClave, Pageable pageable) {
        return usuarioRepository.buscarPorPalabraClave(palabraClave, pageable);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuariosConRolCliente() {
        return usuarioRepository.obtenerTodosLosUsuariosConRolCliente();

    }

    @Override
    public Usuario obtenerDatosClientePorNombresApellidos(String nombres, String apellidoMaterno, String apellidoPaterno) {
        return usuarioRepository.obtenerDatosClientePorNombresApellidos(nombres, apellidoMaterno, apellidoPaterno);
    }

    @Override
    public List<Usuario> buscarClientesPorPalabraClave(String palabraClave) {
        return usuarioRepository.buscarClientesPorPalabraClave(palabraClave);
    }

}
