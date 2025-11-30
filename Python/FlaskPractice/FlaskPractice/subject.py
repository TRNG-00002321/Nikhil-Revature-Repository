from flask import Flask, render_template

app = Flask(__name__)

# Create a dictionary of marks
marks = {
    "Physics": 88,
    "Chemistry": 92,
    "Math": 95
}

@app.route('/subjects/<subjects>')
def displayTable(subject):
    return render_template('helloname.html', subject=subject)

if __name__ == '__main__':
    app.run(debug=True)


