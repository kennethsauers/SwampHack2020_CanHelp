import cv2
import numpy as np
from tensorflow import keras
dir = 'ISIC_0029314.jpg'
model = "savedModels/path_to_my_model.h5"

def evalFromDir(dir = 'ISIC_0029314.jpg', model = "savedModels/path_to_my_model.h5"):
	img = cv2.imread(dir)

	print(img.size)
	print(img.shape)

	img = cv2.resize(img,(150,150))
	cv2.imwrite("image.png", img)

	img = img.reshape(-1,150,150,3)

	print(img.shape)


	new_model = keras.models.load_model(model)
	predictions = new_model.predict(img)
	print(predictions)
	return predictions

if __name__ == '__main__':
    evalFromDir()
