package org.example;

public class Address
{
    /*
    Create a class named "Address" having the following fields: street, city, zip code.
    Create constructors, getters, setters, and toString() methods.
    Create another class, Person, having name, phone, and address as attributes.
    Create a main class wherein you create two Person objects, one with an address and another
    without an address.
    Check the nullability of address for each Person object.
     */

    private String street;
    private String city;
    private int zipcode;

    public Address(String street, String city, int zipcode)
    {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(int zipcode)
    {
        this.zipcode = zipcode;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}