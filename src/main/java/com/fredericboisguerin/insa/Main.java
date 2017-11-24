package com.fredericboisguerin.insa;

import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import static org.mockito.Mockito.*;

public class Main {

    private static final String EXIT_MESSAGE = "Bye Bye !";
    private static final String SAVE_MESSAGE = "Saving contacts...";
    private static final String SAVE_ERROR_MESSAGE = "An error occured while persisting contacts";

    private static final GeekUI view = new GeekUI();
    private static ContactsManager contactList = new ContactsManager();
    private static ContactsManager contactList2 = mock(ContactsManager.class);


    public static void main(String argv[]) throws IOException {
        int userChoice;

        do {
            userChoice = view.askUser();

            switch (userChoice) {
                case GeekUI.MENU_DISPLAY_CONTACTS_VALUE:
                    contactList.printAllContacts();
                    Mockito.verify(contactList2).printAllContacts();
                    break;
                case GeekUI.MENU_ADD_CONTACTS_VALUE:
                    addContacts();
                    contactList.persistContacts();
                    break;
                case GeekUI.MENU_SEARCH_CONTACT_VALUE:
                    searchForContact();
                    break;
                case GeekUI.MENU_EXIT_VALUE:
                    exit();
                    break;
            }

            System.out.println(" -- ");

        } while (userChoice != GeekUI.MENU_EXIT_VALUE);

    }

    private static void addContacts() {
        HashMap<String, String> contactData = view.askUserForContactCreation();

        try {
            contactList.addContact(
                    contactData.get("name"),
                    contactData.get("email"),
                    contactData.get("phoneNumber")
            );
            Mockito.verify(contactList2).addContact(contactData.get("name"),
                    contactData.get("email"),
                    contactData.get("phoneNumber"));
        } catch (InvalidContactNameException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        }
    }

    private static void searchForContact() {
        String search = view.getSearch();
        contactList.searchContactByName(search);
    }

    private static void exit() {
        System.out.println(Main.SAVE_MESSAGE);
        try {
            contactList.persistContacts();
        } catch (IOException e) {
            System.out.println(Main.SAVE_ERROR_MESSAGE);
            e.printStackTrace();
        }
        System.out.println(Main.EXIT_MESSAGE);
    }
}
