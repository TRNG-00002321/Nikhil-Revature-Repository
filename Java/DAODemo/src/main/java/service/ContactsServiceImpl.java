package service;

import dao.ContactDAO;
import dao.ContactsJDBCImpl;
import model.Contacts;

public class ContactsServiceImpl implements ContactsService
{
    public Contacts getContact(int id)
    {
        ContactDAO contactDAO = new ContactsJDBCImpl();
        if (id > 0) {
            Contacts contacts = contactDAO.getContact(id);
            return contacts;
        }
        return null;
    }
}

