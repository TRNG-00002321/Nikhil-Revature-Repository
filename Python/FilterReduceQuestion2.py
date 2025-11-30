#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# 2. Filter Students by Grade
# Given a list of dictionaries, where each dictionary represents a student with name and grade keys,
# use filter() to extract students with a grade of 90 or higher.
# Example
# students = [
#     {"name": "Alice", "grade": 85},
#     {"name": "Bob", "grade": 92},
#     {"name": "Charlie", "grade": 78},
#     {"name": "David", "grade": 95}
# ]

students = [
     {"name": "Alice", "grade": 85},
     {"name": "Bob", "grade": 92},
     {"name": "Charlie", "grade": 78},
     {"name": "David", "grade": 95}
 ]

filtered_students = list(filter(lambda s: s["grade"] >= 90, students))

print(filtered_students)

