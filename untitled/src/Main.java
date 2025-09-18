import Controller.BanqueController;
import Model.Compte;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quitter = true;
        while (quitter) {
            System.out.println("** Welcome to Owr Bank **");
            System.out.println("\n ** votre Menu **");
            System.out.println("1. Créer un compte");
            System.out.println("2. Vérifier le solde");
            System.out.println("3. Faire un dépôt");
            System.out.println("4. Effectuer un retrait");
            System.out.println("5. Transférer de l'argent");
            System.out.println("6. Afficher l'historique des transactions");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    BanqueController.createAccount();
                    break;
                case 2:
                    BanqueController.checkSold();
                    break;
                case 3:
                    BanqueController.versement();
                    break;
                case 4:
                    BanqueController.retrait();
                    break;
                default:
                    System.out.println("option incorrect");
            }
        }

    }

}

