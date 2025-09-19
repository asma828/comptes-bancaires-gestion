import Controller.BanqueController;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n--- MENU BANQUE ---");
            System.out.println("1. Créer compte");
            System.out.println("2. Voir solde");
            System.out.println("3. Versement");
            System.out.println("4. Retrait");
            System.out.println("5. Virement");
            System.out.println("6. Historique");
            System.out.println("0. Quitter");
            System.out.print("Choix: ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: BanqueController.createAccount(); break;
                case 2: BanqueController.checkSolde(); break;
                case 3: BanqueController.versement(); break;
                case 4: BanqueController.retrait(); break;
                case 5: BanqueController.virement(); break;
                case 6: BanqueController.afficherHistorique(); break;
                case 0: continuer = false; break;
                default: System.out.println("Choix invalide");
            }
        }

        System.out.println("Au revoir!");
        scanner.close();
    }
}