#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025
#Description: Create a python file. Create a string as " hello world ".
#Use the common string methods like lower, upper, strip, lstrip, rstrip, replace, split, join, find, and index.

word = " hello world "
newWord = "replacedWord"

#Converts all characters from word to lowercase.
print(word.lower())
#Converts all characters from word to uppercase.
print(word.upper())
#Removes leading and trailing whitespaces from word.
print(word.strip())
#Removes whitespaces from the left side of word.
print(word.lstrip())
#Removes whitespaces from the right side of word.
print(word.rstrip())
#Replaces all occurences of one string with another string.
print(word.replace(word, newWord))
#Splits the string into a list of words.
print(word.split())
#Takes in new word as a list of characters and uses that to seperate the word.
print(word.join(newWord))
#Returns the starting index of the substring. Returns -1 if not found.
print(word.find("world"))
#Does the same thing as .find() but if a substring is not found it returns a ValueError.
print(word.index("h"))
