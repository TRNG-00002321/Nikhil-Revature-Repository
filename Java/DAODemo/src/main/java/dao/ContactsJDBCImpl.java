package dao;

import model.Contacts;
import org.example.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsJDBCImpl implements ContactDAO
{
    Connection connection = null;
    public Contacts getContact(int id)
    {
        connection = ConnectionUtil.dbConnetion();
        Contacts contacts = null;
        String getContact = "select * from contacts where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getContact);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                contacts = new Contacts(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4) );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
