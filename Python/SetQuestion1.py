#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

# 1. You are given an array arr[] of size n. You have to insert all elements of arr[] into a set and return that set.
# You are also given a interger x. If x is found in set then erase it from set and print "erased x", otherwise, print "not found".

def process_set(arr, x):
    s = set(arr)
    if x in s:
        s.remove(x)
        print(f"erased {x}")
    else:
        print("not found")
    return s

arr = [1, 2, 3, 4, 5]
x = 3
result = process_set(arr, x)
print("Final set:", result)

