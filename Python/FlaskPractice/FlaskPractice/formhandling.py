import flask
from flask import Flask, request, redirect, url_for

app = Flask(__name__)

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['nm']
    else:
        username = request.args.get('nm')

    return redirect(url_for('welcome', username=username))

@app.route('/welcome/<username>')
def welcome(username):
    return f"Welcome {username}!"

app.add_url_rule('/login', view_func=login)

if __name__ == '__main__':
    app.run(debug=True)