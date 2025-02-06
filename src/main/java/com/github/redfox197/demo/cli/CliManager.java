package com.github.redfox197.demo.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.github.redfox197.demo.database.entity.Role;
import com.github.redfox197.demo.database.entity.SubReddit;
import com.github.redfox197.demo.database.entity.Utente;
import com.github.redfox197.demo.database.service.RoleService;
import com.github.redfox197.demo.database.service.SubRedditService;
import com.github.redfox197.demo.database.service.UtenteService;

public class CliManager {
    private Scanner scanner;
    private UtenteService utenteService;
    private RoleService roleService;
    private SubRedditService subRedditService;

    public CliManager(UtenteService utenteService, RoleService roleService, SubRedditService subRedditService) {
        this.utenteService = utenteService;
        this.roleService = roleService;
        this.subRedditService = subRedditService;

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
                findByNomeStartingWithA();
                break;
            case 6:
                findByCreditoGreatherThan10();
                break;
            case 7:
                findByNomeNullOrCognomeNull();
                break;
            case 8:
                findyByCreditoBetween0And10();
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

    private void edit() {
        System.out.print("id: ");
        Utente utente = utenteService.findById(scanner.nextLong());
        scanner.nextLine();

        if (utente == null) {
            System.out.println("Utente non trovato!");
            return;
        }

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
        System.out.println();

        if (isEdit) {
            System.out.println("SubReddit Attuali");
            System.out.println(utente.getSubReddits());

            System.out.println("Resettarli per modificare?");
            if (scanner.nextLine().equalsIgnoreCase("y"))
                utente.setSubReddits(new ArrayList<>());
        }

        while (true) {
            System.out.println("Vuoi aggiungere dei SubReddit? (y/n)");
            if (!scanner.nextLine().equalsIgnoreCase("y"))
                break;

            List<SubReddit> avSub = avaibleSubReddits(utente);
            if (avSub.isEmpty()) {
                System.out.println("Non ci sono SubReddit disponibili!");
                break;
            }

            System.out.println("SubReddit disponibili: ");
            avSub.forEach(System.out::println);

            System.out.println("SubReddit id: ");
            Long subRedditId = scanner.nextLong();
            scanner.nextLine();

            SubReddit subReddit = subRedditService.findById(subRedditId);
            if (subReddit == null) {
                System.out.println("SubReddit non trovato");
                continue;
            }

            utente.getSubReddits().add(subReddit);
        }

        utenteService.save(utente);
        System.out.println("Utente salvato!");
        System.out.println();
    }

    private void printRoles() {
        System.out.println("Ruoli disponibili");
        System.out.println(roleService.findAll());
        System.out.println();
    }

    private List<SubReddit> avaibleSubReddits(Utente utente) {
        List<Long> sbID = utente.getSubReddits().stream().map(SubReddit::getId).toList();
        return subRedditService.findAll().stream().filter(sb -> !sbID.contains(sb.getId())).toList();
    }

    private void delete() {
        System.out.print("id: ");
        Utente utente = utenteService.findById(scanner.nextLong());
        scanner.nextLine();

        if (utente == null) {
            System.out.println("Utente non trovato!");
            return;
        }

        utenteService.delete(utente);
        System.out.println("Utente eliminato!");
        System.out.println();
    }

    private void findByNomeStartingWithA() {
        System.out.println("Utenti che iniziano con a");
        System.out.println(utenteService.findByNomeStartingWithA());
        System.out.println();
    }

    private void findByCreditoGreatherThan10() {
        System.out.println("Utenti con credito superiore a 10");
        System.out.println(utenteService.findByCreditoGreatherThan10());
        System.out.println();
    }

    private void findByNomeNullOrCognomeNull() {
        System.out.println("Utenti con nome o cognome null");
        System.out.println(utenteService.findByNomeNullOrCognomeNull());
        System.out.println();
    }

    private void findyByCreditoBetween0And10() {
        System.out.println("Utente con credito positivo e inferiore a 10");
        System.out.println(utenteService.findyByCreditoBetween0And10());
        System.out.println();
    }
}
