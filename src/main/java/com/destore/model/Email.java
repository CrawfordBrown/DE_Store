package com.destore.model;

public class Email {
    private int email_id;
    private int manager_id;
    private String email_Address;
    private String email_message;

    public int getEmail_id() {
        return email_id;
    }

    public void setEmail_id(int email_id) {
        this.email_id = email_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getEmail_Address() {
        return email_Address;
    }

    public void setEmail_Address(String email_Address) {
        this.email_Address = email_Address;
    }

    public String getEmail_message() {
        return email_message;
    }

    public void setEmail_message(String email_message) {
        this.email_message = email_message;
    }


}
