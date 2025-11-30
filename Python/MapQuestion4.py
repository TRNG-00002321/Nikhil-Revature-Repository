#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# 4. Concatenate Strings: Given two lists of strings, use map() to concatenate corresponding elements with a space in between.
# Example
#     first_names = ["John", "Jane"]
#     last_names = ["Doe", "Smith"]
#     # Expected output: ["John Doe", "Jane Smith"]

first_names = ["John", "Jane"]
last_names = ["Doe", "Smith"]

res = map(lambda x, y: x + ' ' + y, first_names, last_names)

print(list(res))