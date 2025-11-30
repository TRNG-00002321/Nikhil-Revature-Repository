#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025
import functools
import operator

# 3. Concatenate Strings
# Given a list of strings, concatenate them into a single string using reduce.
# Example :
# 	words = ["Python", "is", "awesome", "!"]

words = ["Python", "is", "awesome", "!"]

print(functools.reduce(operator.add, words))


