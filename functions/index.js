// imports firebase-functions module
const functions = require('firebase-functions');
// imports firebase-admin module
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

/* Listens for new messages added to /antrian/:pushId and sends a notification to subscribed users */
exports.pushNotification = functions.database.ref('/antrian/{pushId}').onWrite( ( change,context) => {
console.log('Push notification event triggered');
/* Grab the current value of what was written to the Realtime Database */
    var valueObject = change.after.val();
/* Create a notification and data payload. They contain the notification information, and message to be sent respectively */ 
    const payload = {
        notification: {
            title: 'App Name',
            body: "New kode",
            sound: "default"
        },
        data: {
            title: valueObject.title,
            message: valueObject.message,
	    name: valueObject.name
        }
    };
/* Create an options object that contains the time to live for the notification and the priority. */
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24 //24 hours
    };
return admin.messaging().sendToTopic("notifications", payload, options);
});