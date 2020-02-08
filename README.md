# Blight-Eye | SwampHacks 2020

### Developers: 
[Kenneth Sauers](https://github.com/kennethsauers)   
[Jarret Torres](https://github.com/jtorres305)   
[Mykola Maslych](https://github.com/maslychm)

### Description
Use this app to take a picture of a skin disorder and get a preliminary diagnosis.   
Built in 36 hours during [SwampHacks 2020](https://2020.swamphacks.com/) at University of Florida.
 
The images show Start Screen, Evaluation Screen, Results Screen (result is the scientific name for a common mole)

![Start Page](https://github.com/kennethsauers/SwampHack2020_CanHelp/blob/master/repo_resources/StartPage_sm.jpg)
![Evaluation Page](https://github.com/kennethsauers/SwampHack2020_CanHelp/blob/master/repo_resources/EvaluationPage_sm.jpg)
![Results Page](https://github.com/kennethsauers/SwampHack2020_CanHelp/blob/master/repo_resources/ResultsPage_sm.jpg)

### Front-end: Native Android (Java) 
Built on Native Android, the app takes a picture and saves it to local storage. When user clicks to send the image, app converts it to a byte array, packs it into a JSON Object and sends a POST request to the server. The server responds with a string of the name of a potential skin disorder. Depending on the response, app's result screen presents a disclainer and shows additional information about the diagnosis.

### Back-End: GCP, Flask (Python)
Our Flask server is hosted on GCP's App Engine. It receives a POST request, decodes the byte array into an image and feeds it into a trained CNN.    
We trained a CNN on a public [dataset](https://www.nature.com/articles/sdata2018161) to categorize a picture into one of the seven skin disorders.

### Links
[Link to Devpost Submission](https://devpost.com/software/blight-eye)   
[Link to YouTube Video](https://www.youtube.com/watch?v=r9QnUqZJaLg)
