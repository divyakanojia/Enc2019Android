// Cloud Functions in Firebase
const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

// Create and Deploy Your First Cloud Functions
// https://firebase.google.com/docs/functions/write-firebase-functions

exports.helloWorld = functions.https.onRequest((request, response) => {
	 
	// Write Javascript Code to return current date and time
	//var d = new Date();
	//response.send("This is Hello from Auribises !!"+d);

	// Fetch Data from Firebase Firestore and Convert it to 
	var db = admin.firestore();
	//var usersRef = db.collection('users').orderBy('name','desc');
	var usersRef = db.collection('users');
	var users = [];
	var allUsers = usersRef.get()
	.then(snapshot => {
	    snapshot.forEach(doc => {
	      console.log(doc.id, '=>', doc.data());
	      users.push(doc.data())
	    });
	    var jsonData = JSON.stringify(users);
	    response.send(jsonData);
  		return;
  	})
	.catch(err => {
	    console.log("Error getting documents"+err);
	});

});

exports.fetchUser = functions.https.onRequest((request, response) => {
	 
    const name = request.query.name;

	var db = admin.firestore();
	var usersRef = db.collection('users').where('name', '==', name);
	var users = [];
	var allUsers = usersRef.get()
	.then(snapshot => {
	    snapshot.forEach(doc => {
	      console.log(doc.id, '=>', doc.data());
	      users.push(doc.data())
	    });
	    var jsonData = JSON.stringify(users);
	    response.send(jsonData);
  		return;
  	})
	.catch(err => {
	    console.log("Error getting documents"+err);
	});

});

exports.addUser = functions.https.onRequest((request, response) => {

	const uname = request.query.name;
	const uphone = request.query.phone;
	const uemail = request.query.email;

	var db = admin.firestore();

	var addDoc = db.collection('myusers').add({
	   name: uname,
	   phone: uphone,
	   email: uemail
	}).then(ref => {
	   response.send(uname+" added in database");
  	   return;
	});

});

exports.sendCloudMessage = functions.https.onRequest((request, response) => {

	// FCM : Explore

});
