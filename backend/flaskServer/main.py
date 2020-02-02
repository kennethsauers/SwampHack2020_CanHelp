from flask import Flask, jsonify, request
from PIL import Image
from tensorflow import keras
import numpy as np
import io
import base64

app = Flask(__name__)
diseaseList = ["Bowen's disease", "basal cell carcinoma", "benign keratosis-like", "dermatofibroma", "melanoma", "melanocytic nevi ", "vascular lesions vasc."]
def evalFromDir(dir = '../ISIC_0029314', model = "vgg_8cat.h5"):
	img = Image.open("{}.jpg".format(dir))
	size = [150,150]
	img = img.resize(size)
	img = np.array(img)
	img = img.reshape([-1,150,150,3])
	img = np.array(img)
	print(img.shape)
	new_model = keras.models.load_model(model)
	predictions = new_model.predict(img)
	i = np.argmax(predictions)
	json = {"disease" : diseaseList[i]}
	return json

	#data is json object
def saveImg(data):
	fileName = 'filler'
	#print(type(data['data']))
	dataBytes = (data['imgdata'])
	print(type(dataBytes))
	f = io.BytesIO(base64.b64decode((dataBytes)))
	pilimage = Image.open(f)
	pilimage.save('{}.jpg'.format(fileName), "JPEG")
	return fileName

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
	json = request.json
	fileName = saveImg(json)
	data = evalFromDir(dir = fileName)


	return data



if __name__ == '__main__':
    app.run(host='0.0.0.0', port = 8080, debug = True)
