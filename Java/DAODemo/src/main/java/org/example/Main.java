package org.example;

import dao.ContactDAO;
import dao.ContactsJDBCImpl;
import model.Contacts;
import org.example.util.ConnectionUtil;
import service.ContactsService;
import service.ContactsServiceImpl;

import java.sql.Connection;

public class Main
{
    public static void main(String[] args)
    {
        ContactsService contactsService = new ContactsServiceImpl();
        Contacts contacts = contactsService.getContact(2);

        //ContactDAO contactDAO = new ContactsJDBCImpl();
        //Contacts contacts = contactDAO.getContact(2);
        System.out.println(contacts);
    }
}
