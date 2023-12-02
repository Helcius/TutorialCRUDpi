package org.example.service;

import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Filme;
import org.example.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

    @Slf4j
    @Service
    public class FilmeService {
        @Autowired
        private FilmeRepository repository;

        public Filme alterar(Filme filme){

            if(Objects.nonNull(filme.getId())){
                filme = repository.save(filme);
            }else{
                throw new NotFoundException();
            }
            return filme;
        }

        public Boolean excluir(Integer id){
            try {
                repository.deleteById(id);
            }catch (Exception e ){
                log.info("Erro ao realizar Exclusão : {}", e);
                return false;
            }
            return true;
        }

        public Filme consultarPorId(Integer id){
            //recupera o model do banco
            var filme = repository
                    .findById(id)
                    .orElseThrow(NotFoundException::new);

            return filme;
        }

        public Filme salvar(Filme filme){
            filme = repository.save(filme);
            return filme;
        }

        public List<Filme> listar(){
            return repository.findAll();
        }
}
