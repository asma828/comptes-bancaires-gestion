package Controller;

import Model.*;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BanqueController {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, Compte> comptes = new HashMap<>();

    public static void createAccount() {
        try {
            System.out.println("1. Compte Epargne  2. Compte Courant");
            int type = scanner.nextInt();
            scanner.nextLine();

            if (type != 1 && type != 2) {
                System.out.println("Type de compte invalide! Veuillez choisir 1 ou 2.");
                return;
            }

            String code;
            while (true) {
                try {
                    System.out.print("Code compte (ex: CPT-12345): ");
                    code = scanner.nextLine();

                    if (code.matches("^CPT-\\d{5}$")) {
                        if (comptes.containsKey(code)) {
                            System.out.println("Ce code de compte existe déjà! Veuillez en choisir un autre.");
                            continue;
                        }
                        break;
                    } else {
                        System.out.println("Format invalide! Le code doit être comme : CPT-12345");
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de la saisie du code: " + e.getMessage());
                    scanner.nextLine();
                }
            }

            if (type == 1) {
                try {
                    System.out.print("Taux interet: ");
                    double taux = scanner.nextDouble();
                    if (taux < 0) {
                        System.out.println("Le taux d'intérêt ne peut pas être négatif!");
                        return;
                    }
                    comptes.put(code, new CompteEpargne(code, taux));
                } catch (InputMismatchException e) {
                    System.out.println("Erreur: Veuillez entrer un nombre valide pour le taux d'intérêt.");
                    scanner.nextLine();
                    return;
                }
            } else {
                try {
                    System.out.print("Découvert: ");
                    double decouvert = scanner.nextDouble();
                    if (decouvert < 0) {
                        System.out.println("Le découvert ne peut pas être négatif!");
                        return;
                    }
                    comptes.put(code, new CompteCourant(code, decouvert));
                } catch (InputMismatchException e) {
                    System.out.println("Erreur: Veuillez entrer un nombre valide pour le découvert.");
                    scanner.nextLine();
                    return;
                }
            }
            System.out.println("Compte créé avec succès!");

        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre valide pour le type de compte.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors de la création du compte: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void checkSolde() {
        try {
            System.out.print("Code compte: ");
            String code = scanner.nextLine();

            if (code == null || code.trim().isEmpty()) {
                System.out.println("Le code de compte ne peut pas être vide!");
                return;
            }

            Compte compte = comptes.get(code);

            if (compte != null) {
                compte.afficherDetails();
            } else {
                System.out.println("Compte introuvable avec le code: " + code);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la consultation du solde: " + e.getMessage());
        }
    }

    public static void versement() {
        try {
            System.out.print("Code compte: ");
            String code = scanner.nextLine();

            if (code == null || code.trim().isEmpty()) {
                System.out.println("Le code de compte ne peut pas être vide!");
                return;
            }

            Compte compte = comptes.get(code);

            if (compte != null) {
                double montant = 0;
                boolean montantValide = false;

                while (!montantValide) {
                    try {
                        System.out.print("Montant (>0): ");
                        montant = scanner.nextDouble();
                        scanner.nextLine();

                        if (montant <= 0) {
                            System.out.println("Le montant doit être positif!");
                        } else {
                            montantValide = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur: Veuillez entrer un nombre valide pour le montant.");
                        scanner.nextLine();
                    }
                }

                String source;
                do {
                    try {
                        System.out.print("Source: ");
                        source = scanner.nextLine();
                        if (source == null || source.trim().isEmpty()) {
                            System.out.println("La source ne peut pas être vide!");
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur lors de la saisie de la source: " + e.getMessage());
                        source = "";
                    }
                } while (source.trim().isEmpty());

                try {
                    compte.verser(montant, source);
                    System.out.println("Versement effectué avec succès!");
                } catch (Exception e) {
                    System.out.println("Erreur lors du versement: " + e.getMessage());
                }
            } else {
                System.out.println("Compte introuvable avec le code: " + code);
            }
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors du versement: " + e.getMessage());
        }
    }

    public static void retrait() {
        try {
            System.out.print("Code compte: ");
            String code = scanner.nextLine();

            if (code == null || code.trim().isEmpty()) {
                System.out.println("Le code de compte ne peut pas être vide!");
                return;
            }

            Compte compte = comptes.get(code);

            if (compte != null) {
                double montant = 0;
                boolean montantValide = false;

                while (!montantValide) {
                    try {
                        System.out.print("Montant (>0): ");
                        montant = scanner.nextDouble();
                        scanner.nextLine();

                        if (montant <= 0) {
                            System.out.println("Le montant doit être positif!");
                        } else {
                            montantValide = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur: Veuillez entrer un nombre valide pour le montant.");
                        scanner.nextLine();
                    }
                }

                String destination;
                do {
                    try {
                        System.out.print("Destination: ");
                        destination = scanner.nextLine();
                        if (destination == null || destination.trim().isEmpty()) {
                            System.out.println("La destination ne peut pas être vide!");
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur lors de la saisie de la destination: " + e.getMessage());
                        destination = "";
                    }
                } while (destination.trim().isEmpty());

                try {
                    if (compte.retirer(montant, destination)) {
                        System.out.println("Retrait effectué avec succès!");
                    } else {
                        System.out.println("Retrait impossible! Solde insuffisant ou autres restrictions.");
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors du retrait: " + e.getMessage());
                }
            } else {
                System.out.println("Compte introuvable avec le code: " + code);
            }
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors du retrait: " + e.getMessage());
        }
    }

    public static void virement() {
        try {
            System.out.print("Compte source: ");
            String codeSource = scanner.nextLine();
            System.out.print("Compte destination: ");
            String codeDest = scanner.nextLine();

            if (codeSource == null || codeSource.trim().isEmpty() ||
                    codeDest == null || codeDest.trim().isEmpty()) {
                System.out.println("Les codes de compte ne peuvent pas être vides!");
                return;
            }

            if (codeSource.equals(codeDest)) {
                System.out.println("Vous ne pouvez pas faire un virement vers le même compte.");
                return;
            }

            Compte source = comptes.get(codeSource);
            Compte dest = comptes.get(codeDest);

            if (source == null) {
                System.out.println("Compte source introuvable: " + codeSource);
                return;
            }

            if (dest == null) {
                System.out.println("Compte destination introuvable: " + codeDest);
                return;
            }

            double montant = 0;
            boolean montantValide = false;

            while (!montantValide) {
                try {
                    System.out.print("Montant (>0): ");
                    montant = scanner.nextDouble();
                    scanner.nextLine();

                    if (montant <= 0) {
                        System.out.println("Le montant doit être positif!");
                    } else {
                        montantValide = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erreur: Veuillez entrer un nombre valide pour le montant.");
                    scanner.nextLine();
                }
            }

            try {
                if (source.retirer(montant, "Virement vers " + codeDest)) {
                    try {
                        dest.verser(montant, "Virement de " + codeSource);
                        System.out.println("Virement effectué avec succès!");
                    } catch (Exception e) {
                        // Rollback: remettre l'argent dans le compte source
                        source.verser(montant, "Annulation virement");
                        System.out.println("Erreur lors du crédit du compte destination. Virement annulé: " + e.getMessage());
                    }
                } else {
                    System.out.println("Virement impossible! Solde insuffisant dans le compte source.");
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du virement: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors du virement: " + e.getMessage());
        }
    }

    public static void afficherHistorique() {
        try {
            System.out.print("Code compte: ");
            String code = scanner.nextLine();

            if (code == null || code.trim().isEmpty()) {
                System.out.println("Le code de compte ne peut pas être vide!");
                return;
            }

            Compte compte = comptes.get(code);

            if (compte != null) {
                try {
                    System.out.println("Historique du compte " + code + ":");

                    if (compte.getListeOperations() == null || compte.getListeOperations().isEmpty()) {
                        System.out.println("Aucune opération trouvée pour ce compte.");
                        return;
                    }

                    for (Operation ope : compte.getListeOperations()) {
                        try {
                            String type = "";
                            if (ope instanceof Versement) {
                                type = "Versement";
                            } else if (ope instanceof Retrait) {
                                type = "Retrait";
                            } else {
                                type = "Opération inconnue";
                            }

                            System.out.println(ope.getNumero() + " " + type + ":" + " " +
                                    "dans la date" + " " + ope.getDate() +
                                    " d'un montant de : " + ope.getMontant() + "DH");
                        } catch (Exception e) {
                            System.out.println("Erreur lors de l'affichage d'une opération: " + e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de l'accès à l'historique: " + e.getMessage());
                }
            } else {
                System.out.println("Compte introuvable avec le code: " + code);
            }
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors de l'affichage de l'historique: " + e.getMessage());
        }
    }
}