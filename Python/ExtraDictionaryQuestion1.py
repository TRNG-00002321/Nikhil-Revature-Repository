#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# 1. Create a dictionary of five countries and their capitals.
# Write a function that takes a country name as input and returns its capital.

def getCapital(name):
    countries = {"USA": "Washington", "UAE": "Dubai", "Qatar": "Doha", "Oman": "Muscat", "Japan": "Tokyo"}
    return countries.get(name, "Capital not found")

print(getCapital("USA"))
print(getCapital("India"))
print(getCapital("Washington"))





