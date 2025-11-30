#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

# 2. You are given a number k and a list arr[] that contains integers. You need to return list of numbers that are less than k.
# Example
# Input: arr[] = [54, 43, 2, 1, 5], k = 6
# Output: 2 1 5
# Explanation: 2 1 5 are less than 6.

k = 6
myList = [54, 43, 2, 1, 5]
emptyList = []

i = 0
while i < len(myList):
    if myList[i] < k:
        emptyList.append(myList[i])
    i += 1

print(emptyList)


