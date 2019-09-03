package br.com.angulosistemas.groupmanagement.bean;

import javax.persistence.*;

@Entity
@Table(name = "loja")
public class Loja {

    @Id
    private long id_cliente;

    @Column(nullable = false)
    private long cnpj;

    @Column(nullable = false)
    private String nome_loja;

    @Column(nullable = false)
    private String razao;

    public Loja() {}

    public Loja(long cnpj, String nome_loja, String razao) {
        this.cnpj = cnpj;
        this.nome_loja = nome_loja;
        this.razao = razao;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome_loja() {
        return nome_loja;
    }

    public void setNome_loja(String nome_loja) {
        this.nome_loja = nome_loja;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    @Override
    public String toString() {
        return "Loja [ cnpj: "   + cnpj +
                "| id_cliente: " + id_cliente +
                "| nome_loja: "  + nome_loja +
                "| razao: "      + razao +
                "];";
    }
}
