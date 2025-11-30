#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# Create a program to add, view, mark as complete, and delete tasks from a list/dictionary,
# potentially saving the list/dictionary to a file. Please use the following as well while writing code
# Functions
# Modules (Optional)
# Imports (Optional)
import json

from flask import Flask, render_template

app = Flask(__name__)

# Load tasks from file if it exists
def loadTasks():
    try:
        with open('tasks.json', 'r') as f:
            return json.load(f)
    except FileNotFoundError:
        return []

# Save tasks to file
def saveTasks(tasks):
    with open('tasks.json', 'w') as f:
        json.dump(tasks, f, indent=2)
    print("Tasks saved to tasks.json")

@app.route('template/add')
def addTask(tasks):
    userInput = input("Enter a task to add: ")
    tasks.append({"task": userInput, "completed": False})
    #print(f"Added: {userInput}")
    return tasks


def viewTasks(tasks):
    if not tasks:
        print("No tasks in your list!")
        return

    print("\n--- Your Tasks ---")
    for i, task in enumerate(tasks):
        status = "✓" if task["completed"] else " "
        print(f"{i}. [{status}] {task['task']}")
    print()


def markTaskCompleted(tasks):
    viewTasks(tasks)
    if not tasks:
        return tasks
    try:
        userInput = int(input("Enter task number to mark as complete: "))
        if 0 <= userInput < len(tasks):
            tasks[userInput]["completed"] = True
            print(f"Marked as complete: {tasks[userInput]['task']}")
        else:
            print("Invalid task number!")
    except ValueError:
        print("Please enter a valid number!")
    return tasks

def deleteTask(tasks):
    viewTasks(tasks)
    if not tasks:
        return tasks
    try:
        userInput = int(input("Enter task number to delete: "))
        if 0 <= userInput < len(tasks):
            deleted = tasks.pop(userInput)
            print(f"Deleted: {deleted['task']}")
        else:
            print("Invalid task number!")
    except ValueError:
        print("Please enter a valid number!")
    return tasks


def showMenu():
    print("\n=== To-Do List Menu ===")
    print("1. Add task")
    print("2. View tasks")
    print("3. Mark task as complete")
    print("4. Delete task")
    print("5. Save and exit")
    print("6. Exit without saving")


def main():
    tasks = loadTasks()
    print("Welcome to your To-Do List!")

    while True:
        showMenu()
        choice = input("Choose an option (1-6): ")

        if choice == "1":
            tasks = addTask(tasks)
        elif choice == "2":
            viewTasks(tasks)
        elif choice == "3":
            tasks = markTaskCompleted(tasks)
        elif choice == "4":
            tasks = deleteTask(tasks)
        elif choice == "5":
            saveTasks(tasks)
            print("Goodbye!")
            break
        elif choice == "6":
            print("Goodbye!")
            break
        else:
            print("Invalid choice! Please choose 1-6.")

if __name__ == '__main__':
    app.run(debug=True)
