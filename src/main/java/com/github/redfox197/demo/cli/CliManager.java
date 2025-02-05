package com.github.redfox197.demo.cli;

import java.util.Optional;
import java.util.Scanner;

import com.github.redfox197.demo.database.entity.Role;
import com.github.redfox197.demo.database.entity.Utente;
import com.github.redfox197.demo.database.service.RoleService;
import com.github.redfox197.demo.database.service.UtenteService;

public class CliManager {
    private Scanner scanner;
    private UtenteService utenteService;
    private RoleService roleService;

    public CliManager(UtenteService utenteService, RoleService roleService) {
        this.utenteService = utenteService;
        this.roleService = roleService;

        scanner = new Scanner(System.in);
        printOptions();
    }

    private void printOptions() {
        System.out.println("Opzioni Disponibili:");
        System.out.println("1. Stampa tutti gli utenti presenti in tabella");
        System.out.println("2. Inserisci un nuovo utente");
        System.out.println("3. Modifica i dettagli di un utente");
        System.out.println("4. Eliminare un utente a partire dall'id");
        System.out.println("5. Trovare tutti gli utenti con nome che inizia per a");
        System.out.println("6. Trovare tutti gli utenti con credito > 10");
        System.out.println("7. Trovare tutti gli utenti con nome o cognome null");
        System.out.println("8. Trovare tutti gli utenti con credito positivo, ma inferiore a 10");
        System.out.println("9. Termina il programma");
        System.out.println();

        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                readAll();
                break;
            case 2:
                insert();
                break;
            case 3:
                edit();
                break;
            case 4:
                delete();
                break;
            case 5:
                opt5();
                break;
            case 6:
                opt6();
                break;
            case 7:
                opt7();
                break;
            case 8:
                opt8();
                break;
            case 9:
                return;
            default:
                System.out.println("Operazione non valida");
                break;
        }

        printOptions();
    }

    private void readAll() {
        System.out.println("Utenti:");
        System.out.println(utenteService.findAll());
        System.out.println();
    }

    private void insert() {
        Utente utente = new Utente();
        salvaUtente(utente);
    }

    private void salvaUtente(Utente utente) {
        boolean isEdit = utente.getId() != null;
        
        System.out.print("Nome" + (isEdit ? "(" + utente.getNome() + ")" : "") + ": ");
        utente.setNome(scanner.nextLine());

        System.out.print("Cognome: " + (isEdit ? "(" + utente.getCognome() + ")" : "") + ": ");
        utente.setCognome(scanner.nextLine());

        System.out.print("Username: " + (isEdit ? "(" + utente.getUsername() + ")" : "") + ": ");
        utente.setUsername(scanner.nextLine());

        System.out.print("Password: " + (isEdit ? "(" + utente.getPassword() + ")" : "") + ": ");
        utente.setPassword(scanner.nextLine());

        System.out.print("Credito: " + (isEdit ? "(" + utente.getCredito() + ")" : "") + ": ");
        utente.setCredito(scanner.nextInt());
        scanner.nextLine();

        printRoles();
        System.out.print("Role id: " + (isEdit ? "(" + utente.getRole().getId() + ")" : ""));
        Role role = roleService.findById(scanner.nextLong()).orElse(null);
        utente.setRole(role);
        scanner.nextLine();

        utenteService.save(utente);
        System.out.println("Utente salvato!");
        System.out.println();
    }

    private void printRoles() {
        System.out.println("Ruoli disponibili");
        System.out.println(roleService.findAll());
        System.out.println();
    }

    private void edit() {
        System.out.print("id: ");
        Optional<Utente> optUtente = utenteService.findById(scanner.nextLong());
        scanner.nextLine();

        if (!optUtente.isPresent()) {
            System.out.println("Utente non trovato!");
            return;
        }

        Utente utente = optUtente.get();
        salvaUtente(utente);
    }

    private void delete() {
        System.out.print("id: ");
        Optional<Utente> optUtente = utenteService.findById(scanner.nextLong());
        scanner.nextLine();

        if (!optUtente.isPresent()) {
            System.out.println("Utente non trovato!");
            return;
        }

        utenteService.delete(optUtente.get());
        System.out.println("Utente eliminato!");
        System.out.println();
    }

    private void opt5() {
        System.out.println("Utenti che iniziano con a");
        System.out.println(utenteService.findByNomeStartingWithA());
        System.out.println();
    }

    private void opt6() {
        System.out.println("Utenti con credito superiore a 10");
        System.out.println(utenteService.findByCreditoGreatherThan10());
        System.out.println();
    }

    private void opt7() {
        System.out.println("Utenti con nome o cognome null");
        System.out.println(utenteService.findByNomeNullOrCognomeNull());
        System.out.println();
    }

    private void opt8() {
        System.out.println("Utente con credito positivo e inferiore a 10");
        System.out.println(utenteService.findyByCreditoBetween0And10());
        System.out.println();
    }
}
