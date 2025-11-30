#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# 7. Write a Python program to iterate over dictionaries using for loops.
# Sample Output
# {"Name" : "Ram" , "Age" : 23 , "City" : "Salem", "Gender" : "Male"}
# Name : Ram
# Age : 23
# City : Salem
# Gender : Male

myDict = {"Name" : "Ram" , "Age" : 23 , "City" : "Salem", "Gender" : "Male"}

for x in myDict:
    print(x + " : " + str(myDict.get(x)))