const {getDB} = require("./connectDB");

const db = getDB();

const userRef = db.ref("users");

const getUserModel = async (userName) => {
    try {
        return await userRef.child(userName).get();
    }catch (err) {
        console.log("error in get user document");
    }
}

module.exports={
    getUserModel
};