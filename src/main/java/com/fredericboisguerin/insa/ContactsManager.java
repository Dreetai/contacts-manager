package com.fredericboisguerin.insa;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ContactsManager {

    private ArrayList<Contact> contacts;

    private static final String CONTACT_FILE_NAME = "./donnees.csv";

    public ContactsManager() {

        this.contacts = new ArrayList<>();

        try {
            this.loadContacts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidContactNameException e) {
            e.printStackTrace();
        }

    }

    public void addContact(String name, String email, String phoneNumber) throws InvalidContactNameException, InvalidEmailException{
        Contact contactToBeAdded = new Contact(name,email,phoneNumber);
        this.contacts.add(contactToBeAdded);
    }

    public void printAllContacts() {
        for(Contact c : contacts){
            System.out.println(c.toString());
        }
    }

    public void searchContactByName(String name) {
        for(Contact c : contacts){
            if(c.getName().toLowerCase().contains(name.toLowerCase()))
                System.out.println(c.toString());
        }
    }

    public void persistContacts() throws IOException {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(ContactsManager.CONTACT_FILE_NAME));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"Name", "Email", "Phone"};
            csvWriter.writeNext(headerRecord);

            for (Contact c : this.contacts) {
                csvWriter.writeNext(c.toCSVString());
            }
        }
    }

    public void loadContacts() throws IOException, InvalidEmailException, InvalidContactNameException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(ContactsManager.CONTACT_FILE_NAME));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                this.addContact(nextRecord[0], nextRecord[1], nextRecord[2]);
            }
        }
    }

}
