package org.example.controller;

import jakarta.ws.rs.NotFoundException;
import org.example.model.Filme;
import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        usuario = service.salvar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Usuario>consultarPorId(@PathVariable int idConsulta){
        return ResponseEntity.ok(service.consultarPorId(idConsulta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirPorId(@PathVariable int deletId){
        service.excluirPorId(deletId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/alterar-filme-favorito")
    public ResponseEntity<String> alterarFilmeFavorito(
            @PathVariable int id,
            @RequestBody Filme filme) {
        try {
            Usuario usuario = consultarPorId(id).getBody();
            service.alteraFilmeFavorito(usuario, filme);
            return ResponseEntity.ok("Filme favorito alterado com sucesso para o usuário com ID: " + id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID do filme não pode ser nulo");
        }
    }

}
