package br.com.angulosistemas.groupmanagement.repository;

import br.com.angulosistemas.groupmanagement.bean.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GrupoRepositorio extends JpaRepository<Grupo, Long> {


    @Query(value = " SELECT * FROM grupo WHERE id_grupo = ? ; ", nativeQuery = true)
    Grupo getGrupo(String id_grupo);


    @Query(value = " SELECT * FROM grupo ; ", nativeQuery = true)
    List<Grupo> getGrupos();


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM loja_grupo WHERE id_grupo = ?; ", nativeQuery = true)
    int deleteLojasGrupo(String id_grupo);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM grupo WHERE id_grupo = ?; ", nativeQuery = true)
    int deleteGrupo(String id_grupo);



    @Transactional
    @Modifying
    @Query(value = "UPDATE grupo SET data_criacao = ?2, descricao_grupo = ?3, nome_grupo = ?4 WHERE id_grupo = ?1 ;", nativeQuery = true)
    int updateGrupo(String id_grupo, String data_criacao, String descricao_grupo, String nome_grupo);

    @Query(value = "SELECT * FROM grupo WHERE id_grupo = ?", nativeQuery = true)
    Grupo getInformationGrupo(String id_grupo);
}
