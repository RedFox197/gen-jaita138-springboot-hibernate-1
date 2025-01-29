package com.github.redfox197.demo.database.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String descrizione;

    @OneToMany(mappedBy = "role")
    private List<Utente> utenti;

    public Role() {
    }

    public Role(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Role [\nid=" + id + ", \nnome=" + nome + ", \ndescrizione=" + descrizione + "\n]";
    }
}
