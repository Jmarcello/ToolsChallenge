package br.com.phoenix.toolschallenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phoenix.toolschallenge.model.Transacao;
import br.com.phoenix.toolschallenge.model.enums.StatusTransacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findAllByDescricaoStatusIn(List<StatusTransacao> status);

    Optional<Transacao> findByIdAndDescricaoStatusIn(Long id, List<StatusTransacao> status);
}
