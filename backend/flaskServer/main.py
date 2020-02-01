from flask import Flask, jsonify, request
import cv2
from tensorflow import keras

modelPath = '/home/kennethsauers/SwampHack2020_CanHelp/backend/savedModels/path_to_my_model.h5'

app = Flask(__name__)

def evalFromDir(dir = '../ISIC_0029314.jpg', model = "../savedModels/path_to_my_model.h5"):
	img = cv2.imread(dir)
	img = cv2.resize(img,(150,150))
	img = img.reshape(-1,150,150,3)
	new_model = keras.models.load_model(model)
	predictions = new_model.predict(img)
	return predictions

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

@app.route('/screening', methods = ['POST'])
def screening():
    data = evalFromDir()
    data = data.tolist()

    return {"benign" : data
    }



if __name__ == '__main__':
    app.run(host='127.0.0.1', port = 8080, debug = True)
