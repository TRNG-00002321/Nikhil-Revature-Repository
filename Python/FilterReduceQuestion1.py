#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# Filter and Reduce Assignment
# 1. Filter Strings by Length
# Given a list of strings, use filter() to create a new list containing only the strings with a length greater than 5.
# Example
# words = ["apple", "banana", "cat", "dog", "elephant", "frog"]

words = ["apple", "banana", "cat", "dog", "elephant", "frog"]
def wordLen(x):
    if len(x) > 5:
        return len(x)

res = filter(lambda x : wordLen(x), words)

print(list(res))