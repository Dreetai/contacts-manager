package com.fredericboisguerin.insa;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GeekUI {

    public static final int MENU_DISPLAY_CONTACTS_VALUE = 0;
    public static final int MENU_ADD_CONTACTS_VALUE = 1;
    public static final int MENU_SEARCH_CONTACT_VALUE = 2;
    public static final int MENU_EXIT_VALUE = 3;
    private static final String MENU_DISPLAY_CONTACTS_TEXT = "Afficher les contacts";
    private static final String MENU_ADD_CONTACTS_TEXT = "Ajouter des contacts";
    private static final String MENU_SEARCH_CONTACT_TEXT = "Rechercher un contact";
    private static final String MENU_EXIT_TEXT = "Quitter";
    private static final String CL_INVITE = "Que voulez-vous faire ? (écrire le numéro de l'option)";
    private Map<Integer, String> menu;

    public GeekUI() {
        this.menu = new HashMap<>();
        this.menu.put(GeekUI.MENU_DISPLAY_CONTACTS_VALUE, MENU_DISPLAY_CONTACTS_TEXT);
        this.menu.put(GeekUI.MENU_ADD_CONTACTS_VALUE, MENU_ADD_CONTACTS_TEXT);
        this.menu.put(GeekUI.MENU_SEARCH_CONTACT_VALUE, MENU_SEARCH_CONTACT_TEXT);
        this.menu.put(GeekUI.MENU_EXIT_VALUE, MENU_EXIT_TEXT);
    }

    private void displayMenu() {
        for (Map.Entry<Integer, String> entry : this.menu.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public int askUser() {
        this.displayMenu();

        Scanner sc = new Scanner(System.in);
        int userChoice = -1;
        Set<Integer> keySet = this.menu.keySet();

        while (!keySet.contains(userChoice)) {
            System.out.println(GeekUI.CL_INVITE);
            userChoice = sc.nextInt();
        }

        return userChoice;
    }

    public HashMap<String, String> askUserForContactCreation() {
        HashMap<String, String> contactData = new HashMap<>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Contact name: ");
        contactData.put("name", sc.nextLine());

        System.out.print("Contact email: ");
        contactData.put("email", sc.nextLine());

        System.out.print("Contact phone number: ");
        contactData.put("phoneNumber", sc.nextLine());

        return contactData;
    }

    public String getSearch() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom ou nom partiel: ");
        return sc.nextLine();
    }
}
