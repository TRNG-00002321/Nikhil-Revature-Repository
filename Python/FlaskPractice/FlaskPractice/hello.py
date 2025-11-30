import flask
from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return "hello world"

@app.route('/hello')
def hello_again():
    return 'Hello world again'

@app.route('/hello/<name>')
def hiname(name):
   return "Hi, %s" % name.upper()

@app.route('/square/<int:number>')
def squareNum(number):
    squared = number * number
    return "Number squared : %s" % squared

@app.route('/circleArea/<float:num>')
def circleArea(num):
    radius = num * num
    area = 3.14 * radius
    return "Circle area: %s" % area

@app.route('/sum')
def displaySum():
    num1 = flask.request.args.get('val1', type=int)
    num2 = flask.request.args.get('val2', type=int)
    total = num1 + num2
    return f"Total sum: {total}"

def hithere():
    return 'hi there'

#Defines an endpoint for a function.
app.add_url_rule('/hi', view_func=hithere)

if __name__ == '__main__':
    app.run(debug=True)

