// server.js
const express = require('express');
const http = require('http');
const WebSocket = require('websocket').server;
const db = require('./db');

const app = express();
const server = http.createServer(app);
const wsServer = new WebSocket({ httpServer: server });

let candidates = [];

WebSocket.prototype.broadcast = function(data) {
    this.connections.forEach(connection => {
        connection.sendUTF(data);
    });
};

wsServer.on('request', async (request) => {
    const connection = request.accept(null, request.origin);
    console.log('Nowe połączenie WebSocket.');

    try {
        candidates = await db.getCandidates();
        connection.sendUTF(JSON.stringify(candidates));
    } catch (err) {
        console.error("Błąd podczas pobierania kandydatów:", err);
    }

    connection.on('message', async (message) => {
        if (message.type === 'utf8') {
            const key = message.utf8Data.split('|')[0];
            const candidateIndex = parseInt(message.utf8Data.split('|')[1]);
            if (!isNaN(candidateIndex) && candidateIndex >= 0 && candidateIndex < candidates.length) {
                try {
                    const unusedKey = await db.getUnusedVotingKey();
                    if (unusedKey) {
                        await db.markKeyAsUsed(unusedKey.key_value);
                        await db.updateCandidateVotes(candidateIndex);
                        candidates[candidateIndex].votes++;
                        wsServer.broadcast(JSON.stringify(candidates));
                    } else {
                        console.log("Brak dostępnych kluczy do głosowania.");
                    }
                } catch (err) {
                    console.error("Błąd podczas obsługi głosu:", err);
                }
            } else {
                console.log("Nieprawidłowy indeks kandydata:", message.utf8Data);
            }
        }
    });
});

app.use(express.static('public'));

const PORT = process.env.PORT || 3000;
server.listen(PORT, async () => {
    console.log(`Serwer działa na porcie ${PORT}`);
    try {
        const numKeys = 10;
        const keys = generateVotingKeys(numKeys);
        await db.initializeVotingKeys(keys);
        console.log("Klucze do głosowania zostały wygenerowane i zapisane w bazie danych.");
    } catch (err) {
        console.error("Błąd podczas generowania i zapisywania kluczy do głosowania w bazie danych:", err);
    }
});

// Funkcja do generowania kluczy do głosowania
function generateVotingKeys(numKeys) {
    const keys = [];
    for (let i = 0; i < numKeys; i++) {
        const key = generateRandomKey();
        keys.push(key);
    }
    return keys;
}

// Funkcja generująca losowy klucz
function generateRandomKey() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const keyLength = 10;
    let key = '';
    for (let i = 0; i < keyLength; i++) {
        key += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return key;
}
