from flask import Flask, render_template

app = Flask(__name__)

@app.route('/hello/<name>')
def hello_world(name):
    return render_template('helloname.html', name=name)

@app.route('/hello/<int:score>')
def hello_score(score):
    return render_template('scores.html', marks=score)

if __name__ == '__main__':
    app.run(debug=True)