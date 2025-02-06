package com.github.redfox197.demo.database.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String nome;

    @Column(length = 64)
    private String cognome;

    @Column(length = 128)
    private String username;

    @Column(length = 64)
    private String password;
    private int credito;

    @ManyToOne
    private Role role;

    @ManyToMany
    private List<SubReddit> subReddits = new ArrayList<>();

    public Utente() {
    }

    public Utente(String nome, String cognome, String username, String password, int credito) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.credito = credito;
    }

    public Long getId() {
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<SubReddit> getSubReddits() {
        return subReddits;
    }

    public void setSubReddits(List<SubReddit> subReddits) {
        this.subReddits = subReddits;
    }

    @Override
    public String toString() {
        return "Utente [\nid=" + id + ", \nnome=" + nome + ", \ncognome=" + cognome + ", \nusername=" + username + ", \npassword="
                + password + ", \ncredito=" + credito + ", \nrole=" + role + "\n]";
    }
}
