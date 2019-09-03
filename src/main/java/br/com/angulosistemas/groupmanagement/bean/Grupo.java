package br.com.angulosistemas.groupmanagement.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_grupo;

    @Column(nullable = false)
    private String nome_grupo;

    @Column(nullable = true)
    private String descricao_grupo;

    @Column(nullable = true)
    private String data_criacao;

    @ManyToOne
    @JoinColumn(name = "id_matriz", referencedColumnName = "id_cliente")
    private Loja loja;

    public Grupo (){}

    public Grupo (long id_grupo, String nomeGrupo, String descricaoGrupo, String dataCriacao, Loja cnpjMatriz){
        this. id_grupo = id_grupo;
        this.nome_grupo = nomeGrupo;
        this.descricao_grupo = descricaoGrupo;
        this.data_criacao = dataCriacao;
        this.loja = cnpjMatriz;
    }

    public long getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(long id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNome_grupo() {
        return nome_grupo;
    }

    public void setNome_grupo(String nome_grupo) {
        this.nome_grupo = nome_grupo;
    }

    public String getDescricao_grupo() {
        return descricao_grupo;
    }

    public void setDescricao_grupo(String descricao_grupo) {
        this.descricao_grupo = descricao_grupo;
    }

    public String getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "Grupo [ id: "         + id_grupo +
                "| nome_grupo: "      + nome_grupo +
                "| descricao_grupo: " + descricao_grupo +
                "| data_criacao: "    + data_criacao +
                "| loja: "            + loja +
                "];";
    }
}
