#Name: Nikhil Reddy Nalabolu
#Date: 11/06/2025
import sys

# For this problem you are going to make a program that simulates the output of a vending machine that only takes in
# coins of American currency.
# 1.Your program should take an integer as an input from the user (either a 1, 2, or 3) that corresponds with an option
# for a drink from the vending machine outlined below and assign the corresponding price to a variable as a float.
# Use your knowledge of if, elif, and else statements to complete this part of the problem. Your code should
# have an else statement that prints a message and ends the program using sys.exit() if the user does not enter a valid
# input number.
# Vending Machine:
# 1.water = $1.00
# 2.cola = $1.50
# 3.gatorade = $2.00
# 2.After placing an order, the user should be prompted to enter inputs 4 times:
# 1.The first time, the user should be prompted to enter the number of quarters they put in the machine. Assign this
# number to a variable as an integer.
# 2.The second time, the user should be prompted to enter the number of dimes they put in the machine. Assign this
# number to a variable as an integer.
# 3.The third time, the user should be prompted to enter the number of nickles they put in the machine. Assign this
# number to a variable as an integer.
# 4.The fourth time, the user should be prompted to enter the number of pennies they put in the machine. Assign this
# number to a variable as an integer.
# 3.Create a variable to hold the total value of all the coins the user has put into the machine.
# 4.Use flow control statements to print the user's change or output a message asking the user to try again depending
# on whether the total value of the coins the user has put into the machine is enough to pay for the item they ordered.
# New knowledge for this problem:
# 1.%f format specifier
# 2.import sys and sys.exit()
# 3.int()

class VendingMachine:
    def __int__(self):
        print("init")

    def vendingMachine(self):
        print("Welcome to drink vending machine!")
        print("These are the options: " + "\n")
        print("1.water = $1.00 | 2.cola = $1.50 | 3.gatorade = $2.00" + "\n")
        userChoice = int(input("Enter which option you would like: " + "\n"))

        water = 1.00
        cola = 1.50
        gatorade = 2.00

        if userChoice == 1:
            a = water
        elif userChoice == 2:
            b = cola
        elif userChoice == 3:
            c = gatorade

        if (userChoice > 3 or userChoice < 0):
            print("Invalid input choice. Please try again.")
            sys.exit()

        userQuarters = int(input("Enter the amount of quarters: " + "\n"))
        quarterTotal = float(userQuarters * 0.25)
        userDimes = int(input("Enter the amount of dimes: " + "\n"))
        dimeTotal = float(userDimes * 0.1)
        userNickles = int(input("Enter the amount of nickles: " + "\n"))
        nickelTotal = float(userNickles * 0.05)
        userPennies = int(input("Enter the amount of pennies: " + "\n"))
        pennyTotal = float(userPennies * 0.1)

        totalUserAmount = float(quarterTotal + dimeTotal + nickelTotal + pennyTotal)

        if totalUserAmount == water:
            print("Here's your drink!")
            return 0
        elif totalUserAmount == cola:
            print("Here's your drink!")
            return 0
        elif totalUserAmount == gatorade:
            print("Here's your drink!")
            return 0
        elif totalUserAmount < water:
            print("Not enough change, please try again.")
            return 0
        elif totalUserAmount < cola:
            print("Not enough change, please try again.")
            return 0
        elif totalUserAmount < gatorade:
            print("Not enough change, please try again.")
            return 0
        elif totalUserAmount > water:
            totalChange = totalUserAmount - water
            print("Here's your change: " + str(totalChange))
        elif totalUserAmount > cola:
            totalChange1 = totalUserAmount - cola
            print("Here's your change: " + str(totalChange1))
        elif totalUserAmount > gatorade:
            totalChange2 = totalUserAmount - gatorade
            print("Here's your change: " + str(totalChange2))

v = VendingMachine
v.vendingMachine(0)

