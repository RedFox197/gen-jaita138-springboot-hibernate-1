package com.github.redfox197.demo.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public Utente() {
    }

    public Utente(String nome, String cognome, String username, String password, int credito) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.credito = credito;
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

    @Override
    public String toString() {
        return "Utente [\nid=" + id + ", \nnome=" + nome + ", \ncognome=" + cognome + ", \nusername=" + username + ", \npassword="
                + password + ", \ncredito=" + credito + ", \nrole=" + role + "\n]";
    }
}
