package com.github.redfox197.demo.cli;

import java.util.Optional;
import java.util.Scanner;

import com.github.redfox197.demo.database.entity.Utente;
import com.github.redfox197.demo.database.service.UtenteService;

public class CliManager {
    private Scanner scanner;
    private UtenteService service;

    public CliManager(UtenteService service) {
        this.service = service;

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
        System.out.println(service.findAll());
        System.out.println();
    }

    private void insert() {
        Utente utente = new Utente();

        System.out.print("Nome: ");
        utente.setNome(scanner.nextLine());

        System.out.print("Cognome: ");
        utente.setCognome(scanner.nextLine());

        System.out.println("Username: ");
        utente.setUsername(scanner.nextLine());

        System.out.println("Password: ");
        utente.setPassword(scanner.nextLine());

        System.out.println("Credito: ");
        utente.setCredito(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Utente salvato!");
        System.out.println();
    }

    private void edit() {
        System.out.print("id: ");
        Optional<Utente> optUtente = service.findById(scanner.nextLong());

        if (!optUtente.isPresent()) {
            System.out.println("Utente non trovato!");
            return;
        }

        Utente utente = optUtente.get();
        System.out.print("nome (" + utente.getNome() + "): ");
        utente.setNome(scanner.nextLine());

        System.out.print("cognome (" + utente.getCognome() + "): ");
        utente.setCognome(scanner.nextLine());

        System.out.print("username (" + utente.getUsername() + "): ");
        utente.setUsername(scanner.nextLine());

        System.out.print("password (" + utente.getPassword() + "): ");
        utente.setPassword(scanner.nextLine());

        System.out.print("credito (" + utente.getCredito() + "): ");
        utente.setCredito(scanner.nextInt());
        scanner.nextLine();

        service.save(utente);
        System.out.println("Utente modificato!");
        System.out.println();
    }

    private void delete() {
        System.out.println("id: ");
        Optional<Utente> optUtente = service.findById(scanner.nextLong());

        if (!optUtente.isPresent()) {
            System.out.println("Utente non trovato!");
            return;
        }

        service.delete(optUtente.get());
        System.out.println("Utente eliminato!");
        System.out.println();
    }

    private void opt5() {
        System.out.println("Utenti che iniziano con a");
        System.out.println(service.findByNomeStartingWithA());
        System.out.println();
    }

    private void opt6() {
        System.out.println("Utenti con credito superiore a 10");
        System.out.println(service.findByCreditoGreatherThan10());
        System.out.println();
    }

    private void opt7() {
        System.out.println("Utenti con nome o cognome null");
        System.out.println(service.findByNomeNullOrCognomeNull());
        System.out.println();
    }

    private void opt8() {
        System.out.println("Utente con credito positivo e inferiore a 10");
        System.out.println(service.findyByCreditoBetween0And10());
        System.out.println();
    }
}
