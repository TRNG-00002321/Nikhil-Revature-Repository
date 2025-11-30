#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# 3. Add Corresponding Elements: Given two lists of numbers of the same length,
# use map() to return a new list containing the sum of corresponding elements.
# Example
# 	  list1 = [1, 2, 3]
#     list2 = [4, 5, 6]
#     # Expected output: [5, 7, 9]


list1 = [1, 2, 3]
list2 = [4, 5, 6]

res = map(lambda x, y: x + y, list1, list2)

print(list(res))


