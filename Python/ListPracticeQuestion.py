#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

# Create a list containing 5 elements (anything).
# Based on this list, create another list having elements from the previous list containing the character 'a'.

myList = ['apple', 'kiwi', 'banana']

myList = [item for item in myList if 'a' in item]

print(myList)




