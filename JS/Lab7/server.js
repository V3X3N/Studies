const express = require('express');
const http = require('http');
const WebSocket = require('websocket').server;

const app = express();
const server = http.createServer(app);
const wsServer = new WebSocket({ httpServer: server });

// Lista kandydatów i początkowe głosy
let candidates = [
    { name: 'Kandydat 1', votes: 0 },
    { name: 'Kandydat 2', votes: 0 },
    { name: 'Kandydat 3', votes: 0 },
    { name: 'Kandydat 4', votes: 0 }
];

// Rozszerzenie obiektu WebSocket o funkcję broadcast
WebSocket.prototype.broadcast = function(data) {
    this.connections.forEach(connection => {
        connection.sendUTF(data);
    });
};

// WebSocket - obsługa połączeń
wsServer.on('request', (request) => {
    const connection = request.accept(null, request.origin);
    console.log('Nowe połączenie WebSocket.');

    // Wysyłanie aktualnych danych o kandydatach do klienta
    connection.sendUTF(JSON.stringify(candidates));

    // Obsługa głosów od klienta
    connection.on('message', (message) => {
        if (message.type === 'utf8') {
            const candidateIndex = parseInt(message.utf8Data);
            if (!isNaN(candidateIndex) && candidateIndex >= 0 && candidateIndex < candidates.length) {
                candidates[candidateIndex].votes++;
                // Wysyłanie zaktualizowanych danych do wszystkich klientów
                wsServer.broadcast(JSON.stringify(candidates));
            }
        }
    });
});

// Serwowanie statycznych plików z folderu 'public'
app.use(express.static('public'));

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Serwer działa na porcie ${PORT}`);
});
