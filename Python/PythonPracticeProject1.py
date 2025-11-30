import json

# --- Step 1: Store questions in a dictionary ---
quiz_data = {
    "questions": [
        {
            "question": "What is the capital of France?",
            "options": ["A) Berlin", "B) Paris", "C) Madrid", "D) Rome"],
            "answer": "B"
        },
        {
            "question": "Which planet is known as the Red Planet?",
            "options": ["A) Earth", "B) Jupiter", "C) Mars", "D) Venus"],
            "answer": "C"
        },
        {
            "question": "What is 5 + 7?",
            "options": ["A) 10", "B) 11", "C) 12", "D) 13"],
            "answer": "C"
        }
    ]
}

# --- Step 2: Save quiz data to JSON file (optional) ---
with open("quiz_data.json", "w") as f:
    json.dump(quiz_data, f, indent=4)

# --- Step 3: Load quiz data from JSON ---
with open("quiz_data.json", "r") as f:
    data = json.load(f)

# --- Step 4: Ask questions and keep score ---
score = 0
print("Welcome to the Quiz!\n")

for i, q in enumerate(data["questions"], 1):
    print(f"Q{i}: {q['question']}")
    for option in q["options"]:
        print(option)
    answer = input("Your answer (A/B/C/D): ").strip().upper()

    if answer == q["answer"]:
        print("✅ Correct!\n")
        score += 1
    else:
        print(f"❌ Wrong! The correct answer was {q['answer']}.\n")

# --- Step 5: Display final score ---
print(f"Your final score: {score}/{len(data['questions'])}")
percentage = (score / len(data["questions"])) * 100
print(f"That's {percentage:.2f}% correct.")