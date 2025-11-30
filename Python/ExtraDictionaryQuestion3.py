#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# 3. Create a nested dictionary of three employees, each with keys for name, age, and salary.
# Write a function to give each employee a 10% raise and print the updated dictionary.

x = {
    'employee1' : {"name" : "Nikhil", "age" : 23, "salary" : 50000},
    'employee2' : {"name" : "Bob", "age" : 24, "salary" : 60000},
    'employee3' : {"name" : "Josh", "age" : 20, "salary" : 30000}, }
def raiseSalary(x):
    for emp in x.values():
        emp["salary"] *= 1.10
    return x

print(raiseSalary(x))


