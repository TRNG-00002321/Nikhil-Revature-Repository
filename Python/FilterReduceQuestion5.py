#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025
import functools

# 5. Flatten a List of Lists
# Given a list of lists, flatten it into a single list using reduce.
# Example:
# 	list_of_lists = [[1, 2], [3, 4], [5, 6]]

list_of_lists = [[1, 2], [3, 4], [5, 6]]

res = functools.reduce(lambda x, y : x + y, list_of_lists)

print(res)