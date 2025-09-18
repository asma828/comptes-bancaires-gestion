package Controller;

import Model.Compte;
import Model.CompteCourant;
import Model.CompteEpargne;

import java.util.HashMap;
import java.util.Scanner;

public class BanqueController {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, Compte> comptes = new HashMap<>();

    public static void createAccount() {
        System.out.println("\n ** CREATE ACCOUNT **");
        System.out.println("1. Compte d'épargne");
        System.out.println("2. Compte courant");
        System.out.println("3. Retour au menu principal");

        int type = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                System.out.println("entrer le code du compte :");
                String codeEpagne = scanner.nextLine();
                comptes.put(codeEpagne, new CompteEpargne(codeEpagne, 0));
                System.out.println("compte creé avec succes");
                break;

            case 2:
                System.out.println("entrer le code du compte :");
                String codeCourant = scanner.nextLine();
                comptes.put(codeCourant, new CompteCourant(codeCourant, 0));
                System.out.println("compte creé avec succes");
        }


    }

    public static void checkSold() {
        System.out.println("entre le code du compte");
        String code = scanner.nextLine();
        Compte compte = comptes.get(code);
        if (compte != null) {
            System.out.println("votre solde est " + compte.getSolde());
        } else {
            System.out.println("ce compte n'existe pas");
        }

    }

    public static void versement() {
        System.out.println("entre le code du compte");
        String code = scanner.nextLine();
        Compte compte = comptes.get(code);
        if (compte == null) {
            System.out.println("Compte introuvable");
            return;
        }


        System.out.println("entre le montant a deposer");
        double montant = scanner.nextDouble();
        compte.setSolde(compte.getSolde() + montant);
        comptes.put(code, compte);
        System.out.println("Dépôt effectué");
        System.out.println(comptes.get(code).getSolde());

    }


}
