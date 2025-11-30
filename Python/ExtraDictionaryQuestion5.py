#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# 5. Write a Python program to concatenate following dictionaries to create a new one.
# Sample Output
# Dictionary 1 = {"Name" : "Ram" , "Age" : 23}
# Dictionary 2 = {"City" : "Salem", "Gender" : "Male"}
# Concatenate Dictionaries = {'Name' : 'Ram', 'Age' : 23, 'City' : 'Salem', 'Gender': 'Male'}

Dictionary1 = {"Name" : "Ram" , "Age" : 23}
Dictionary2 = {"City" : "Salem", "Gender" : "Male"}

Dictionary1.update(Dictionary2)

print(Dictionary1)
