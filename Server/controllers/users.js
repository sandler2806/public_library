const {getUserModel} = require("../model/dbRealTime/users");


const getUser = async (req,res) => {
    const {username:userName} = req.params;
    const userSnap = await getUserModel(userName);
    const userJson = userSnap.val();
    console.log(userJson);
    res.status(200).json(userJson);
};

module.exports={
    getUser
};
