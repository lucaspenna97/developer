package br.com.angulosistemas.groupmanagement.service;

import br.com.angulosistemas.groupmanagement.bean.Grupo;
import br.com.angulosistemas.groupmanagement.bean.Loja;
import br.com.angulosistemas.groupmanagement.bean.LojaGrupo;
import br.com.angulosistemas.groupmanagement.repository.GrupoRepositorio;
import br.com.angulosistemas.groupmanagement.repository.LojaGrupoRepositorio;
import br.com.angulosistemas.groupmanagement.repository.LojaRepositorio;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaGrupoService {

    @Autowired
    LojaGrupoRepositorio lojaGrupoRepositorio;

    @Autowired
    LojaRepositorio lojaRepositorio;

    @Autowired
    GrupoRepositorio grupoRepositorio;


    public String createLojaGrupo (String id_grupo, String id_cliente) {

        try {

            if (id_grupo == null || id_grupo.isEmpty()) return "O parâmetro id_grupo não foi passado.";
            if (id_cliente == null || id_cliente.isEmpty()) return "O parâmetro id_loja não foi passado.";

            boolean lojaExistente = lojaRepositorio.findById(Long.parseLong(id_cliente)).isPresent();
            if (!lojaExistente) return "O id_cliente passado não refere-se a nenhuma loja existente.";

            boolean grupoExistente = grupoRepositorio.findById(Long.parseLong(id_grupo)).isPresent();
            if (!grupoExistente) return "O id_grupo passado não refere-se a nenhum grupo existente.";

            int rowsReturned = lojaGrupoRepositorio.existsLojaGrupos(id_grupo, id_cliente);
            if (rowsReturned > 0) return "Está loja já pertence a este grupo.";

            Loja loja = new Loja();
            loja.setId_cliente(Long.parseLong(id_cliente));

            Grupo grupo = new Grupo();
            grupo.setId_grupo(Long.parseLong(id_grupo));

            LojaGrupo lojaGrupo = new LojaGrupo();
            lojaGrupo.setLoja(loja);
            lojaGrupo.setGrupo(grupo);

            lojaGrupoRepositorio.save(lojaGrupo);

            return "Loja " + id_cliente + " inserido no grupo " + id_grupo;

        }catch (Exception e){
            e.printStackTrace();
            return "Erro ao criar vinculo loja/grupo";
        }

    }

    public String getLojasGrupo (String id_grupo) {

        try {

            if (id_grupo == null || id_grupo.isEmpty()) return "O parâmetro id_grupo não foi passado.";

            boolean grupoExistente = grupoRepositorio.findById(Long.parseLong(id_grupo)).isPresent();
            if (!grupoExistente) return "O id_grupo passado não refere-se a nenhum grupo existente.";

            Gson gson = new Gson();
            List<LojaGrupo> lojaGrupoList = lojaGrupoRepositorio.getLojasGrupo(id_grupo);

            if (lojaGrupoList != null && !lojaGrupoList.isEmpty()) return gson.toJson(lojaGrupoList);
            else return "Grupo nulo ou vazio.";

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao consultar as lojas do grupo.";
    }

    public String getLojasGrupos () {

        try {

            List<LojaGrupo> lojaGrupoList = lojaGrupoRepositorio.findAll();

            Gson gson = new Gson();
            if (lojaGrupoList.size() > 0) return gson.toJson(lojaGrupoList);
            else return "Nenhum registro referente a lojas/grupos.";

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao consultar as lojas dos grupos.";
    }

    public String deleteLojaGrupo (String id_grupo, String id_cliente) {

        try {

            if (id_cliente == null || id_cliente.isEmpty()) return "O parâmetro id_loja não foi passado.";
            if (id_grupo == null || id_grupo.isEmpty()) return "O parâmetro id_grupo não foi passado.";

            boolean lojaExistente = lojaRepositorio.findById(Long.parseLong(id_cliente)).isPresent();
            if (!lojaExistente) return "O id_cliente passado não refere-se a nenhuma loja existente.";

            boolean grupoExistente = grupoRepositorio.findById(Long.parseLong(id_grupo)).isPresent();
            if (!grupoExistente) return "O id_grupo passado não refere-se a nenhum grupo existente.";

            int rowsAffected = lojaGrupoRepositorio.deleteLojaGrupo(id_grupo, id_cliente);

            if (rowsAffected > 0) return "Loja removida do grupo.";
            else return "Está loja já não pertence a este grupo.";

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao excluir a loja do grupo.";

    }



}
