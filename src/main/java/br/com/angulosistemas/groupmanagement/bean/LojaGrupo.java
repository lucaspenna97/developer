package br.com.angulosistemas.groupmanagement.bean;

import javax.persistence.*;

@Entity
@Table(name = "loja_grupo")
public class LojaGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Loja loja;

    public LojaGrupo() {}

    public LojaGrupo(Grupo grupo, Loja loja) {
        this.grupo = grupo;
        this.loja = loja;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "Membro [ id: " + id +
                "| cnpjLoja: " + loja.getCnpj() +
                "| idGrupo: "  + grupo.getNome_grupo() +
                "];";
    }
}
