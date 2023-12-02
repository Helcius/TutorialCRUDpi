package org.example.service;

import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Filme;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Métodos como esse podem ser usados para alterar um atributo de uma Entidade
    public void alteraFilmeFavorito(Usuario usuario, Filme filme) {
        if (Objects.nonNull(filme.getId())) {
            // Verifica se o filme existe no banco de dados antes de atualizar
            if (repository.existsById(filme.getId())) {
                usuario.setFilmeFavorito(filme);
                // Salva a alteração no banco de dados
                repository.save(usuario);
            } else {
                throw new NotFoundException("Filme não encontrado");
            }
        } else {
            throw new IllegalArgumentException("O ID do filme não pode ser nulo");
        }
    }

    public Usuario salvar(Usuario usuario){
        return  repository.save(usuario);

    }
    public List<Usuario> listar(){
        return repository.findAll();
    }

    public Usuario consultarPorId(Integer id){
        return repository.findById(id).get();
    }

    public void excluir(Usuario usuario){
        repository.delete(usuario);
    }

    public void excluirPorId(int id){
        repository.deleteById(id);
    }
}
