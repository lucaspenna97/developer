package br.com.angulosistemas.groupmanagement.service;

import br.com.angulosistemas.groupmanagement.bean.Loja;
import br.com.angulosistemas.groupmanagement.repository.LojaRepositorio;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    LojaRepositorio lojaRepositorio;

    public String insertLoja (String json) {

        try {

            if (json != null && !json.isEmpty()) {
                Gson gson = new Gson();
                Loja loja = gson.fromJson(json, Loja.class);

                if (String.valueOf(loja.getCnpj()).length() < 14) return "CNPJ faltante ou incorreto.";
                if (loja.getNome_loja().isEmpty()) return "Nome da loja faltante.";
                if (loja.getRazao().isEmpty()) return "Razão da loja faltante";

                lojaRepositorio.save(loja);

            }else {return "Lojas nulas ou vazias não pode ser inseridas.";}

            return "sucesso";

        }catch (Exception e){
            e.printStackTrace();
            return "fracasso";
        }

    }

    public String getLoja (String id_cliente, String cnpj) {

        try {

            if (id_cliente.isEmpty() && cnpj.isEmpty()) return "Nenhum parâmetro foi inserido no método.";

            if (!id_cliente.isEmpty()){
                Optional<Loja> optionalLoja = lojaRepositorio.findById(Long.parseLong(id_cliente));

                if (optionalLoja.isPresent()){
                    Loja loja = optionalLoja.get();
                    return new Gson().toJson(loja);
                }else {return "Não há lojas com o id_cliente indicado.";}
            }

            if (!cnpj.isEmpty()){
                Loja loja = lojaRepositorio.getLoja(cnpj);
                if (loja != null) return new Gson().toJson(loja);
                else return "Não há lojas com o cnpj indicado.";
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao consultar a loja.";
    }

    public String deleteLoja (String id_cliente, String cnpj) {

        try {

            if (id_cliente.isEmpty() && cnpj.isEmpty()) return "Nenhum parâmetro foi inserido no método.";

            if (!id_cliente.isEmpty()){

                boolean verification = lojaRepositorio.findById(Long.parseLong(id_cliente)).isPresent();
                if (!verification) return "Não existe registro a ser excluido com o id_cliente buscado";

                lojaRepositorio.deleteById(Long.parseLong(id_cliente));
                boolean exists = lojaRepositorio.findById(Long.parseLong(id_cliente)).isPresent();
                if (!exists) return "registro excluído com sucesso.";
            }

            if (!cnpj.isEmpty()){
                int rowsDeleted = lojaRepositorio.deleteLoja(cnpj);
                if (rowsDeleted > 0) return "registro excluído com sucesso.";
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Erro ao excluir registro de loja.";

    }

    public String updateLoja(String json){

        try {

            Gson gson = new Gson();
            Loja loja = gson.fromJson(json, Loja.class);

            Optional<Loja> optionalLoja = lojaRepositorio.findById(loja.getId_cliente());

            if (optionalLoja.isPresent()){
                lojaRepositorio.save(loja);
                return "registro atualizado.";
            }else { return "Não existe registro com este id_cliente.";}

        }catch (Exception e){
            e.printStackTrace();
            return "Erro ao atualizar registro de loja.";
        }

    }


}
