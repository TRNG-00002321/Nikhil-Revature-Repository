#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

# Write a python program to input two numbers. Calculate and display their division. Handle exceptions.

x = int(input("Enter number 1: "))
y = int(input("Enter number 2: "))

try:
    result = x // y
    print(result)
except:
    print("An exception has occurred!")
