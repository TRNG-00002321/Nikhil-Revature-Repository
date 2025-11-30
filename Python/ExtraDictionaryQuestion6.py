#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# 6. Write a Python program to check whether a given key already exists in a dictionary.
# Sample Output
# {'Name' : 'Ram', 'Age' : 23,}
# Key = Name
# Key is Available in the Dictionary

myDict = {'Name': 'Ram', 'Age': 23, }

def checkKey(userKey, userDict):
    if userKey in userDict:
        print("Key is Available in the Dictionary")
    else:
        print("Key is Not Available in the Dictionary")

checkKey('Age', myDict)

