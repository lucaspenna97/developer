package br.com.angulosistemas.groupmanagement.repository;

import br.com.angulosistemas.groupmanagement.bean.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface LojaRepositorio extends JpaRepository<Loja, Long> {

    @Query(value = "SELECT * FROM loja WHERE cnpj = ? ;", nativeQuery = true )
    Loja getLoja(String cnpj);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM loja WHERE cnpj = ? ;", nativeQuery = true )
    int deleteLoja(String cnpj);

}
