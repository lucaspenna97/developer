package br.com.angulosistemas.groupmanagement.repository;

import br.com.angulosistemas.groupmanagement.bean.LojaGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LojaGrupoRepositorio extends JpaRepository<LojaGrupo, Long> {


    @Query(value = "SELECT COUNT(*) FROM loja_grupo WHERE id_grupo = ?1 AND id_cliente = ?2 ;", nativeQuery = true)
    int existsLojaGrupos(String id_grupo, String id_cliente);

    @Query(value = "SELECT * FROM loja_grupo WHERE id_grupo = ?1 ;", nativeQuery = true)
    List<LojaGrupo> getLojasGrupo(String id_grupo);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM loja_grupo WHERE id_grupo = ?1 AND id_cliente = ?2 ;", nativeQuery = true)
    int deleteLojaGrupo(String id_grupo, String id_cliente);



}
