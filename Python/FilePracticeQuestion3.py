#Name: Nikhil Reddy Nalabolu
#Date: 11/7/2025

# Write script(s) to receive information from console input to create a dictionary, then append to a file.
# Then, be able to read from the file and search for relevant data to return.
# Start with name search then can go to any data search.

import json

# File to store data
DATA_FILE = 'data.json'

def loadData():
    try:
        with open(DATA_FILE, 'r') as f:
            return json.load(f)
    except FileNotFoundError:
        return []


def saveData(data):
    with open(DATA_FILE, 'w') as f:
        json.dump(data, f, indent=2)
    print(f"Data saved to {DATA_FILE}\n")


def addEntry(data):
    print("\n--- Add New Entry ---")
    entry = {}

    name = input("Enter name: ").strip()
    if not name:
        print("Name cannot be empty!")
        return data
    entry['name'] = name
    entry['age'] = input("Enter age: ").strip()
    entry['email'] = input("Enter email: ").strip()
    entry['phone'] = input("Enter phone: ").strip()
    entry['city'] = input("Enter city: ").strip()

    data.append(entry)
    print(f"Added entry for {name}")
    return data

def viewAllEntries(data):
    if not data:
        print("\nNo entries found!")
        return

    print("\n--- All Entries ---")
    for i, entry in enumerate(data):
        print(f"\nEntry {i + 1}:")
        for key, value in entry.items():
            print(f"  {key.capitalize()}: {value}")


def searchByName(data):
    search_term = input("\nEnter name to search: ").strip().lower()

    results = [entry for entry in data if search_term in entry.get('name', '').lower()]

    if results:
        print(f"\n--- Found {len(results)} result(s) ---")
        for entry in results:
            print()
            for key, value in entry.items():
                print(f"  {key.capitalize()}: {value}")
    else:
        print("No matches found!")

def searchByAnyField(data):
    search_term = input("\nEnter search term: ").strip().lower()

    results = []
    for entry in data:
        for value in entry.values():
            if search_term in str(value).lower():
                results.append(entry)
                break
    if results:
        print(f"\n--- Found {len(results)} result(s) ---")
        for entry in results:
            print()
            for key, value in entry.items():
                print(f"  {key.capitalize()}: {value}")
    else:
        print("No matches found!")
def showMenu():
    print("\n=== Data Manager Menu ===")
    print("1. Add new entry")
    print("2. View all entries")
    print("3. Search by name")
    print("4. Search by any field")
    print("5. Save and exit")
    print("6. Exit without saving")
def main():
    data = loadData()
    print("Welcome to Data Manager!")

    while True:
        showMenu()
        choice = input("\nChoose an option (1-6): ")

        if choice == "1":
            data = addEntry(data)
        elif choice == "2":
            viewAllEntries(data)
        elif choice == "3":
            searchByName(data)
        elif choice == "4":
            searchByAnyField(data)
        elif choice == "5":
            saveData(data)
            print("Goodbye!")
            break
        elif choice == "6":
            print("Goodbye!")
            break
        else:
            print("Invalid choice! Please choose 1-6.")
main()

