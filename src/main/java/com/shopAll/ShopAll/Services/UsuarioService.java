package com.shopAll.ShopAll.Services;

import com.shopAll.ShopAll.Models.Usuario;
import com.shopAll.ShopAll.Repository.UsuarioRepository;
import com.shopAll.ShopAll.Excepcion.InvalidUsuarioDataException;
import com.shopAll.ShopAll.Excepcion.NotFoundException;
import com.shopAll.ShopAll.Services.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardar(Usuario usuario) {
        // Encriptar la contraseña
        usuario.setPassword(SHA256Util.hash(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id: " + id));

        existingUsuario.setNombre(usuario.getNombre());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setEstatus(usuario.getEstatus());
        // Encriptar la nueva contraseña si se está actualizando
        if (usuario.getPassword() != null) {
            existingUsuario.setPassword(SHA256Util.hash(usuario.getPassword()));
        }

        return usuarioRepository.save(existingUsuario);
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id: " + id));
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
