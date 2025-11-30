#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

# 3. You are given a list arr that contains integers. You need to return average of the non negative integers.
# Examples:
# Input: arr = [-12, 8, -7, 6, 12, -9, 14]
# Output: avg = 10.0
# Explanation: The non negative numbers are 8 6 12 14. The sum is 8+6+12+14 = 40, Average = 40/4 = 10.0

import statistics

myList = [-12, 8, -7, 6, 12, -9, 14]
emptyList = []

i = 0
while i < len(myList):
    if myList[i] > 0:
        emptyList.append(myList[i])
    i += 1

print(statistics.mean(emptyList))
