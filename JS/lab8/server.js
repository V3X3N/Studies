// server.js

const express = require('express');
const http = require('http');
const WebSocket = require('websocket').server;
const db = require('./db');

const app = express();
const server = http.createServer(app);
// Użycie ES6 do destrukturyzacji obiektu
const wsServer = new WebSocket({ httpServer: server });

let candidates = [];

// Rozszerzenie prototypu za pomocą ES6
WebSocket.prototype.broadcast = function(data) {
    this.connections.forEach(connection => {
        connection.sendUTF(data);
    });
};

wsServer.on('request', async (request) => {
    const connection = request.accept(null, request.origin);
    console.log('Nowe połączenie WebSocket.');

    try {
        candidates = await db.getCandidates(); // Pobieranie kandydatów z bazy danych
        connection.sendUTF(JSON.stringify(candidates)); // Wysyłanie kandydatów do klienta
    } catch (err) {
        console.error("Błąd podczas pobierania kandydatów:", err); // Obsługa błędu pobierania kandydatów
    }

    connection.on('message', async (message) => {
        if (message.type === 'utf8') {
            // Dekonstrukcja tablicy za pomocą ES6
            const [key, candidateIndex] = message.utf8Data.split('|');
            if (!isNaN(candidateIndex) && candidateIndex >= 0 && candidateIndex < candidates.length) {
                try {
                    const unusedKey = await db.getUnusedVotingKey();
                    if (unusedKey) {
                        await db.markKeyAsUsed(unusedKey.key_value);
                        // Aktualizacja liczby głosów dla kandydata
                        await db.updateCandidateVotes(candidateIndex);
                        candidates[candidateIndex].votes++;
                        wsServer.broadcast(JSON.stringify(candidates)); // Wysyłanie zaktualizowanych danych do wszystkich klientów
                    } else {
                        console.log("Brak dostępnych kluczy do głosowania."); // Informacja o braku dostępnych kluczy
                    }
                } catch (err) {
                    console.error("Błąd podczas obsługi głosu:", err); // Obsługa błędu obsługi głosu
                }
            } else {
                console.log("Nieprawidłowy indeks kandydata:", message.utf8Data);
            }
        }
    });
});

app.use(express.static('public'));

app.get('/markKeyAsUsed/:key', async (req, res) => {
    const key = req.params.key; // Pobieranie klucza z parametrów żądania
    try {
        await db.markKeyAsUsed(key);
        res.json({ success: true, message: 'Klucz został oznaczony jako użyty.' });
    } catch (error) {
        console.error("Błąd podczas oznaczania klucza jako użyty:", error); // Obsługa błędu oznaczania klucza jako użyty
        res.status(500).json({ success: false, message: 'Wystąpił błąd podczas oznaczania klucza jako użyty.' });
    }
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, async () => {
    console.log(`Serwer działa na porcie ${PORT}`);
    try {
        const numKeys = 10;
        // Generowanie kluczy do głosowania
        const keys = generateVotingKeys(numKeys);
        // Inicjalizacja kluczy w bazie danych
        await db.initializeVotingKeys(keys);
        console.log("Klucze do głosowania zostały wygenerowane i zapisane w bazie danych.");
    } catch (err) {
        console.error("Błąd podczas generowania i zapisywania kluczy do głosowania w bazie danych:", err); // Obsługa błędu generowania kluczy
    }
});

function generateVotingKeys(numKeys) {
    const keys = [];
    for (let i = 0; i < numKeys; i++) {
        const key = generateRandomKey();
        keys.push(key);
    }
    return keys;
}

function generateRandomKey() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const keyLength = 10;
    let key = '';
    for (let i = 0; i < keyLength; i++) {
        key += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return key;
}
