
const admin = require("firebase-admin");// Function connects/certificate to firestore

const serviceAccount = require(`../../public-library-8027f-firebase-adminsdk-zv6nr-7f0c48a3ae.json`);

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app"
});
const getDB = admin.database
module.exports = {
    getDB
};