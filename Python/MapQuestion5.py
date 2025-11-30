#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# 5. Apply a Custom Function: Define a function that takes a string and returns its length.
# Then, use map() to apply this function to a list of strings, returning a list of lengths.
# Example
#     words = ["apple", "banana", "cherry"]
#     # Expected output: [5, 6, 6]

words = ["apple", "banana", "cherry"]
def stringLen(x):
    return len(x)

res = list(map(lambda x : stringLen(x), words))

print(res)
