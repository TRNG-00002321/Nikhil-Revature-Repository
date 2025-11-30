#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# 4. Find the Maximum Element
# Given a list of numbers, find the maximum element using reduce.
# Example:
# 	numbers = [10, 3, 25, 7, 18]

import functools
numbers = [10, 3, 25, 7, 18]
greatNum = functools.reduce(lambda x, y: max(x, y), numbers)
print(greatNum)


