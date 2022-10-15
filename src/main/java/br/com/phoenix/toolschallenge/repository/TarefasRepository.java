package br.com.phoenix.toolschallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phoenix.toolschallenge.model.Tarefa;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

}
