package br.com.angulosistemas.groupmanagement.service;

import br.com.angulosistemas.groupmanagement.bean.Bean;
import br.com.angulosistemas.groupmanagement.bean.Grupo;
import br.com.angulosistemas.groupmanagement.bean.Loja;
import br.com.angulosistemas.groupmanagement.bean.LojaGrupo;
import br.com.angulosistemas.groupmanagement.repository.GrupoRepositorio;
import br.com.angulosistemas.groupmanagement.repository.LojaGrupoRepositorio;
import br.com.angulosistemas.groupmanagement.repository.LojaRepositorio;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GrupoService {

    @Autowired
    GrupoRepositorio grupoRepositorio;

    @Autowired
    LojaRepositorio lojaRepositorio;

    @Autowired
    LojaGrupoRepositorio lojaGrupoRepositorio;

    public String createGrupo (String json) {

        try {

            if (json != null && !json.isEmpty()) {
                Gson gson = new Gson();
                Grupo grupo = gson.fromJson(json, Grupo.class);

                if (grupo.getNome_grupo() == null || grupo.getNome_grupo().isEmpty()) return "O campo 'nome do grupo' está vazio.";
                if (grupo.getLoja() == null) return "Não foi inserida uma loja matriz na criação do grupo.";

                Optional<Loja> optionalLoja = lojaRepositorio.findById(grupo.getLoja().getId_cliente());
                if (!optionalLoja.isPresent())return "Impossivel criar o grupo, pois a loja matriz inserida não existe.";

                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                grupo.setData_criacao(simpleDateFormat.format(date));

                grupoRepositorio.save(grupo);

            }else {return "Grupos nulos ou vazios não pode ser criados.";}

            return "sucesso";

        }catch (Exception e){
            e.printStackTrace();
            return "fracasso";
        }

    }

    public String getGrupo (String id_grupo) {

        try {

            if (id_grupo == null || id_grupo.isEmpty()) return "Nenhum parâmetro foi inserido no método.";

            Grupo grupo = grupoRepositorio.getGrupo(id_grupo);
            if (grupo == null) return "Não existe grupo com este identificador.";

            Bean bean = new Bean();
            bean.setGrupo(grupo);

            List<LojaGrupo> lojaGrupoList = lojaGrupoRepositorio.getLojasGrupo(id_grupo);

            if (lojaGrupoList != null && !lojaGrupoList.isEmpty()){

                List<Loja> lojas = new ArrayList<>();

                for (LojaGrupo lojaGrupo: lojaGrupoList){ lojas.add(lojaGrupo.getLoja()); }
                bean.setLojas(lojas);

            }

            Gson gson = new Gson();
            return gson.toJson(bean);


        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao consultar o grupo.";
    }

    public String getGrupos () {


        try {
            List<Grupo> grupos = grupoRepositorio.getGrupos();
            if (grupos == null || grupos.isEmpty()) return "Não há grupos para consulta.";

            List<Bean> beans = new ArrayList<>();

            for (Grupo grupo: grupos){

                Bean bean = new Bean();
                bean.setGrupo(grupo);

                List<LojaGrupo> lojaGrupoList = lojaGrupoRepositorio.getLojasGrupo(String.valueOf(grupo.getId_grupo()));

                if (lojaGrupoList != null && !lojaGrupoList.isEmpty()){

                    List<Loja> lojas = new ArrayList<>();

                    for (LojaGrupo lojaGrupo: lojaGrupoList){ lojas.add(lojaGrupo.getLoja()); }
                    bean.setLojas(lojas);
                }

                beans.add(bean);
            }

            Gson gson = new Gson();
            return gson.toJson(beans);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao consultar grupos.";
    }

    public String deleteGrupo (String id_grupo) {

        try {

            int rowsAffected = grupoRepositorio.deleteLojasGrupo(id_grupo);
            int deleted = grupoRepositorio.deleteGrupo(id_grupo);

            if (deleted > 0) return "Grupo excluido, " + rowsAffected + " membro(s) deste grupo foram removidos.";
            else return "Não há grupo para exclusão com este identificador.";

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao consultar o grupo.";

    }

    public String updateGrupo (String json) {

        try {

            if (json != null && !json.isEmpty()) {
                Gson gson = new Gson();
                Grupo grupo = gson.fromJson(json, Grupo.class);
                System.out.println(grupo);

                Grupo information = grupoRepositorio.getInformationGrupo(String.valueOf(grupo.getId_grupo()));
                if (information == null) return "Não há grupo para atualizar com este identificador.";

                if (grupo.getData_criacao() == null || grupo.getData_criacao().isEmpty())
                    grupo.setData_criacao(information.getData_criacao());

                if (grupo.getNome_grupo() == null || grupo.getNome_grupo().isEmpty())
                    grupo.setNome_grupo(information.getNome_grupo());

                if (grupo.getDescricao_grupo() == null || grupo.getDescricao_grupo().isEmpty())
                    grupo.setDescricao_grupo(information.getDescricao_grupo());

                int rowsAffected = grupoRepositorio.updateGrupo(String.valueOf(grupo.getId_grupo()),grupo.getData_criacao(),
                        grupo.getDescricao_grupo(), grupo.getNome_grupo());

                if (rowsAffected > 0) return "O grupo foi atualizado.";
                else return "Erro ao atualizar o grupo.";

            }else {return "Grupos nulos ou vazios não pode ser atualizados.";}

        }catch (Exception e){
            e.printStackTrace();
            return "fracasso";
        }
    }


}
