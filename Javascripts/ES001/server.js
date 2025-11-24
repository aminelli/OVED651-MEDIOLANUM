// server.js
const express = require('express');
const path = require('path');
const app = express();
const PORT = 3000;

// Middleware per servire file statici dalla cartella 'public'
app.use(express.static('public'));

// Route per la homepage
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Avvio del server
app.listen(PORT, () => {
  console.log(`Server in ascolto su http://localhost:${PORT}`);
});