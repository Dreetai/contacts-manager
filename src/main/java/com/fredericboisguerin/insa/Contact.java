package com.fredericboisguerin.insa;

public class Contact {

    private String name;
    private String email;
    private String phoneNumber;

    public Contact(String name, String email, String phoneNumber) throws InvalidContactNameException,InvalidEmailException{
        if(name == null || name == ""){
            throw new InvalidContactNameException();
        }
        this.name = name;
        if(email != null && !email.contains("@")){
            throw new InvalidEmailException();
        }
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        String ret = this.name;
        if (this.email != null)
            ret += ", "+this.email;
        if (this.phoneNumber != null)
            ret += ", "+this.phoneNumber;
        return ret;
    }

    public String getName(){
        return this.name;
    }

    public String [] toCSVString(){
        return new String[]{this.name, this.email, this.phoneNumber};
    }

}
