from flask import Flask, jsonify, request

modelPath = '/home/kennethsauers/SwampHack2020_CanHelp/backend/savedModels/path_to_my_model.h5'

app = Flask(__name__)

@app.route('/', methods = ['POST'])
def hello_world():
    return 'Hello, World!'

@app.route('/calc', methods = ['POST'])
def calc():
    content = request.json
    print(content)
    return {"data" : content["value"]  * 3}

@app.route('/greeting', methods =['POST'])
def greeting():
    content = request.json
    str = "hello %s of age %d"%(content["name"],content["age"])
    data = {
            "greeting" : str,
            "name" : content["name"],
            "age": content["age"]
           }
    return data


if __name__ == '__main__':
    app.run(host='127.0.0.1', port = 8080, debug = True)
