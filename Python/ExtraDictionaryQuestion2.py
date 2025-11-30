#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# 2. Make a dictionary of student names and their scores.
# Write a function to find the student with the highest score.

x = {"Nikhil" : 70,
         "Josh" : 90,
         "Eric" : 40,
    }
def highScore(scores):
    if not scores:
        return None

    topStudent = max(scores.items(), key = lambda item: item[1])
    return topStudent

print(highScore(x))


