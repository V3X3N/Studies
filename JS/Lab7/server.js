const express = require('express');
const http = require('http');
const WebSocket = require('websocket').server;
const fs = require('fs');

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

// Generowanie kluczy do głosowania
const generateVotingKeys = (numKeys) => {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const keyLength = 10;
    const keys = [];
    for (let i = 0; i < numKeys; i++) {
        let key = '';
        for (let j = 0; j < keyLength; j++) {
            key += characters.charAt(Math.floor(Math.random() * characters.length));
        }
        keys.push(key);
    }
    return keys;
};

// Zapisywanie kluczy do pliku
const saveKeysToFile = (keys) => {
    fs.writeFile('voting_keys.txt', keys.join('\n'), (err) => {
        if (err) {
            console.error("Błąd zapisu pliku z kluczami do głosowania:", err);
            return;
        }
        console.log("Zapisano klucze do głosowania w pliku 'voting_keys.txt'");
    });
};

// Liczba kluczy do wygenerowania
const numKeys = 10;

// Generowanie kluczy i zapisywanie ich do pliku
const keys = generateVotingKeys(numKeys);
saveKeysToFile(keys);

// Lista kluczy do głosowania
let votingKeys = keys.map(key => ({ key: key, used: false }));

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
            const key = message.utf8Data.split('|')[0];
            const keyIndex = votingKeys.findIndex(k => k.key === key && !k.used);
            if (keyIndex !== -1) {
                const candidateIndex = parseInt(message.utf8Data.split('|')[1]);
                if (!isNaN(candidateIndex) && candidateIndex >= 0 && candidateIndex < candidates.length) {
                    candidates[candidateIndex].votes++;
                    votingKeys[keyIndex].used = true;
                    // Wysyłanie zaktualizowanych danych do wszystkich klientów
                    wsServer.broadcast(JSON.stringify(candidates));
                    // Zapisanie zmian w kluczach do głosowania
                    fs.writeFile('voting_keys.txt', votingKeys.map(k => k.key + (k.used ? '|used' : '')).join('\n'), (err) => {
                        if (err) {
                            console.error("Błąd zapisu pliku z kluczami do głosowania:", err);
                        }
                    });
                } else {
                    console.log("Nieprawidłowy indeks kandydata:", message.utf8Data);
                }
            } else {
                console.log("Nieprawidłowy klucz lub klucz został już użyty:", key);
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
