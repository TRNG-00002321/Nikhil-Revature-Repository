#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# 2. Capitalize a List of Names: Given a list of names, use map() to return a new list where each name is capitalized.
# Example
#     names = ["alice", "bob", "charlie"]
#     Expected output: ["Alice", "Bob", "Charlie"]

names = ["alice", "bob", "charlie"]
res = list(map(str.capitalize, names))
print(res)


