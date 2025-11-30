#Name: Nikhil Reddy Nalabolu
#Date: 11/05/2025

# 1.Given an array arr[], find the first repeating element.
# The element should occur more than once and the index of its first occurrence should be the smallest.
# Examples:
# Input: arr[] = [1, 5, 3, 4, 3, 5, 6]
# Output: 2
# Explanation: 5 appears twice and its first appearance is at index 2 which is less than 3 whose first the occurring index is 3.

def first_repeating_element(arr):
    index_map = {}  # Store first occurrence index
    min_index = float('inf')

    for i, value in enumerate(arr):
        if value in index_map:
            # Element repeats, check if its first occurrence is smaller
            min_index = min(min_index, index_map[value])
        else:
            # Store first occurrence
            index_map[value] = i

    return min_index + 1 if min_index != float('inf') else -1

# Example
arr = [1, 5, 3, 4, 3, 5, 5]
print(first_repeating_element(arr))  # Output: 2
