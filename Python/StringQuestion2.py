#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

#2. Given a string s, you need to check if it is palindrome or not.
# A palidrome is a string that reads the same from front and back.

s = "racecar"

if s == s[::-1]:
    print("Palindrome")
else:
    print("Not a palindrome")