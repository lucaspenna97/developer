package br.com.angulosistemas.groupmanagement.bean;

import java.util.List;

public class Bean {

    private Grupo grupo;
    private List<Loja> lojas;

    public  Bean () {}

    public Bean(Grupo grupo, List<Loja> lojas) {
        this.grupo = grupo;
        this.lojas = lojas;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(List<Loja> lojas) {
        this.lojas = lojas;
    }
}
