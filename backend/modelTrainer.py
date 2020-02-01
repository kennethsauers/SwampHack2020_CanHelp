train_dir = "train"
test_dir = "test"
save_dir = "savedModels/"



from keras.preprocessing.image import ImageDataGenerator
from keras import optimizers
from keras.applications import VGG16
from keras import layers
from keras import models
from keras import optimizers
batchSize = 32
numCategories = 2
classMode = "categorical"
# data augmentation

train_datagen = ImageDataGenerator(
    rescale=1./255,
    rotation_range=40,
    width_shift_range=0.2,
    height_shift_range=0.2,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True,
    fill_mode='nearest'
)

train_generator = train_datagen.flow_from_directory(
    train_dir,
    target_size=(150, 150),
    color_mode="rgb",
    batch_size=batchSize,
    class_mode=classMode)

validation_datagen = ImageDataGenerator(rescale=1./255)

validation_generator = validation_datagen.flow_from_directory(
    validation_dir,
    target_size=(150, 150),
    color_mode="rgb",
    batch_size=batchSize,
    class_mode=classMode)




conv_base = VGG16(
    weights='imagenet',
    include_top=False,
    input_shape=(150, 150, 3))

conv_base.trainable = False

model = models.Sequential()
model.add(conv_base)
model.add(layers.Flatten())
model.add(layers.Dense(256, activation='relu'))
model.add(layers.Dropout(0.5))
model.add(layers.Dense(256, activation='relu'))
model.add(layers.Dropout(0.5))
model.add(layers.Dense(numCategories, activation='sigmoid'))


model.compile(
    loss='mean_squared_error',
    #
    # choose a smaller learning rate
    #
    optimizer=optimizers.RMSprop(lr=1e-5),
    metrics=['acc'])

epoch = 10
steps = 10
history = model.fit_generator(
    train_generator,
    steps_per_epoch=steps,
    epochs=epoch,
    validation_data=validation_generator,
    validation_steps=steps)

conv_base.trainable = True

set_trainable = False
for layer in conv_base.layers:
  if layer.name == 'block5_conv1':
    set_trainable = True
  if set_trainable:
    layer.trainable = True
  else:
    layer.trainable = False


history = model.fit_generator(
    train_generator,
    steps_per_epoch=10,
    epochs=2,
    validation_data=validation_generator,
    validation_steps=10)

model.save(save_dir + 'path_to_my_model.h5')
