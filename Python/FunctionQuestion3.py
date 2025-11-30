#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

# 3. Given a number n, find the first digit of the number.

n = 455809

def findFirstNumber(n):
    while n >= 10:
        n = n / 10

    return int(n)

print("The first number is: " "\n")
print(findFirstNumber(n))