package org.example;

import java.util.Optional;

public class Person
{
    private String name;
    private String phone;
    private Address address;

    Optional<Address> checkNull = Optional.ofNullable(address);

    public Person(String name, String phone, Address address)
    {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Optional<Address> getAddressOptional() {
        return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", address=" + address +
                '}';
    }
}
