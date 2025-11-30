#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# 4. Write a Python program to add a key to a dictionary
# Sample Output
# dictionary = {"Name" : "Ram" , "Age" : 23}
# add_key = {"City" : "Salem"}
# dictionary = {'Name' : 'Ram', 'Age' : 23, 'City' : 'Salem'}

dictionary = {"Name" : "Ram" , "Age" : 23}
add_key = {"City" : "Salem"}

dictionary.update(add_key)
print(dictionary)




