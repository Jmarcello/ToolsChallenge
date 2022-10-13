package br.com.rrp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rrp.model.Tarefa;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

}
