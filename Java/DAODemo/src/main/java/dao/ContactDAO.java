package dao;

import model.Contacts;

import java.util.List;

public interface ContactDAO
{
    public default List<Contacts> getAllContacts()
    {
        System.out.println("Default");
        return null;
    }
    public default Contacts getContact(int id)
    {
        return null;
    }
    public default void save(Contacts contact)
    {

    }
    public default Contacts update(int id)
    {

    }
    public default void delete(int id)
    {

    }

}
