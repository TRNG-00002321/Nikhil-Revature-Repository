from flask import Flask, render_template

#app = Flask(__name__)
app = Flask(__name__, template_folder='views')

@app.route('/index')
def index():
    #return '<h1>Hello, Guest!</h1>'
    return render_template('hello2.html')

if __name__ == '__main__':
    app.run(debug=True)