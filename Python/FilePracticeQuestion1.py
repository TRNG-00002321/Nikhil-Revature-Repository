#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# Write a python script to copy the content of one file into another file.

import json
import os.path

with open("input.txt", "r") as file:
    content = file.read()

with open("output.txt", "w") as file1:
    file1.write(content)