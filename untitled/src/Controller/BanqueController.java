package Controller;

import Model.*;

import java.util.HashMap;
import java.util.Scanner;

public class BanqueController {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, Compte> comptes = new HashMap<>();

    public static void createAccount() {
        System.out.println("1. Compte Epargne  2. Compte Courant");
        int type = scanner.nextInt();
        scanner.nextLine();
        String code;
        while (true) {
            System.out.print("Code compte (ex: CPT-12345): ");
            code = scanner.nextLine();

            if (code.matches("^CPT-\\d{5}$")) {
                break;
            } else {
                System.out.println("Format invalide! Le code doit être comme : CPT-12345");
            }
        }

        if (type == 1) {
            System.out.print("Taux interet: ");
            double taux = scanner.nextDouble();
            comptes.put(code, new CompteEpargne(code, taux));
        } else {
            System.out.print("Découvert: ");
            double decouvert = scanner.nextDouble();
            comptes.put(code, new CompteCourant(code, decouvert));
        }
        System.out.println("Compte créé!");
    }

    public static void checkSolde() {
        System.out.print("Code compte: ");
        String code = scanner.nextLine();
        Compte compte = comptes.get(code);

        if (compte != null) {
            compte.afficherDetails();
        } else {
            System.out.println("Compte introuvable");
        }
    }

    public static void versement() {
        System.out.print("Code compte: ");
        String code = scanner.nextLine();
        Compte compte = comptes.get(code);

        if (compte != null) {
            System.out.print("Montant: ");
            double montant = scanner.nextDouble();
            scanner.nextLine();
            if (montant > 0) {
                System.out.print("Source: ");
                String source = scanner.nextLine();

                compte.verser(montant, source);
                System.out.println("Versement effectué!");
            } else {
                System.out.println("votre montant est negative");
            }
        } else {
            System.out.println("Compte introuvable");
        }
    }

    public static void retrait() {
        System.out.print("Code compte: ");
        String code = scanner.nextLine();
        Compte compte = comptes.get(code);

        if (compte != null) {
            System.out.print("Montant: ");
            double montant = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Destination: ");
            String destination = scanner.nextLine();

            if (compte.retirer(montant, destination)) {
                System.out.println("Retrait effectué!");
            } else {
                System.out.println("Retrait impossible!");
            }
        } else {
            System.out.println("Compte introuvable");
        }
    }

    public static void virement() {
        System.out.print("Compte source: ");
        String codeSource = scanner.nextLine();
        System.out.print("Compte destination: ");
        String codeDest = scanner.nextLine();

        Compte source = comptes.get(codeSource);
        Compte dest = comptes.get(codeDest);

        if (source != null && dest != null) {
            System.out.print("Montant: ");
            double montant = scanner.nextDouble();

            if (source.retirer(montant, "Virement")) {
                dest.verser(montant, "Virement");
                System.out.println("Virement effectué!");
            } else {
                System.out.println("Virement impossible!");
            }
        } else {
            System.out.println("Compte introuvable");
        }
    }

    public static void afficherHistorique() {
        System.out.print("Code compte: ");
        String code = scanner.nextLine();
        Compte compte = comptes.get(code);

        if (compte != null) {
            System.out.println("Historique du compte " + code + ":");
            for (Operation ope : compte.getListeOperations()) {
                String type = "";
                if (ope instanceof Versement) {
                    type = "Versement";
                } else if (ope instanceof Retrait) {
                    type = "Retrait";
                }
                System.out.println(ope.getNumero() + " " + type + ":" + " " + "dans la date" + " " + ope.getDate() + " d'un montant de :" + ope.getMontant() + "DH");
            }
        } else {
            System.out.println("Compte introuvable");
        }
    }
}