#Name: Nikhil Reddy Nalabolu
#Date: 11/6/2025

# Write script to copy an image file's contents to another file.

with open("cat.jpg", "rb") as file:
    data = file.read()

with open("output.jpg", "wb") as file1:
    file1.write(data)