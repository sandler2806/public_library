const express = require('express');

const usersRouter = require('./routes/users');

const app = express();

app.use(express.json());

app.use('/api/users', usersRouter);

const PORT = 3000;
app.listen(PORT, () => {
  console.log(`listening to port ${PORT}...`)
});