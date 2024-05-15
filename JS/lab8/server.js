// server.js

const express = require('express'); // Importowanie modułu za pomocą 'require'
const http = require('http');
const WebSocket = require('websocket').server; // Importowanie modułu za pomocą 'require' i destrukturyzacja
const db = require('./db'); // Importowanie modułu lokalnego

const app = express(); // Deklaracja stałej z użyciem 'const'
const server = http.createServer(app); // Tworzenie serwera HTTP
const wsServer = new WebSocket({ httpServer: server }); // Tworzenie serwera WebSocket

let candidates = []; // Deklaracja zmiennej tablicowej

WebSocket.prototype.broadcast = function(data) { // Dodanie metody broadcast do prototypu WebSocket
    this.connections.forEach(connection => {
        connection.sendUTF(data);
    });
};

wsServer.on('request', async (request) => { // Obsługa zdarzenia 'request' dla serwera WebSocket
    const connection = request.accept(null, request.origin); // Akceptowanie połączenia WebSocket
    console.log('Nowe połączenie WebSocket.');

    try {
        candidates = await db.getCandidates(); // Pobieranie kandydatów z bazy danych
        connection.sendUTF(JSON.stringify(candidates)); // Wysyłanie kandydatów do klienta
    } catch (err) {
        console.error("Błąd podczas pobierania kandydatów:", err); // Obsługa błędu pobierania kandydatów
    }

    connection.on('message', async (message) => { // Obsługa zdarzenia 'message' dla połączenia WebSocket
        if (message.type === 'utf8') {
            const key = message.utf8Data.split('|')[0]; // Pobieranie klucza z wiadomości
            const candidateIndex = parseInt(message.utf8Data.split('|')[1]); // Pobieranie indeksu kandydata z wiadomości
            if (!isNaN(candidateIndex) && candidateIndex >= 0 && candidateIndex < candidates.length) { // Sprawdzenie poprawności indeksu kandydata
                try {
                    const unusedKey = await db.getUnusedVotingKey(); // Pobieranie nieużytego klucza głosowania
                    if (unusedKey) {
                        await db.markKeyAsUsed(unusedKey.key_value); // Oznaczanie klucza jako użyty
                        await db.updateCandidateVotes(candidateIndex); // Aktualizacja liczby głosów dla kandydata
                        candidates[candidateIndex].votes++; // Zwiększanie liczby głosów dla kandydata
                        wsServer.broadcast(JSON.stringify(candidates)); // Wysyłanie zaktualizowanych danych do wszystkich klientów
                    } else {
                        console.log("Brak dostępnych kluczy do głosowania."); // Informacja o braku dostępnych kluczy
                    }
                } catch (err) {
                    console.error("Błąd podczas obsługi głosu:", err); // Obsługa błędu obsługi głosu
                }
            } else {
                console.log("Nieprawidłowy indeks kandydata:", message.utf8Data); // Informacja o nieprawidłowym indeksie kandydata
            }
        }
    });
});

app.use(express.static('public')); // Ustawianie katalogu statycznego dla aplikacji Express

// Endpoint obsługujący oznaczanie klucza jako użyty
app.get('/markKeyAsUsed/:key', async (req, res) => { // Definicja endpointu GET
    const key = req.params.key; // Pobieranie klucza z parametrów żądania
    try {
        await db.markKeyAsUsed(key); // Oznaczanie klucza jako użyty w bazie danych
        res.json({ success: true, message: 'Klucz został oznaczony jako użyty.' }); // Wysyłanie odpowiedzi JSON
    } catch (error) {
        console.error("Błąd podczas oznaczania klucza jako użyty:", error); // Obsługa błędu oznaczania klucza jako użyty
        res.status(500).json({ success: false, message: 'Wystąpił błąd podczas oznaczania klucza jako użyty.' }); // Wysyłanie odpowiedzi błędu
    }
});

const PORT = process.env.PORT || 3000; // Określenie portu serwera
server.listen(PORT, async () => { // Nasłuchiwanie na zadanym porcie
    console.log(`Serwer działa na porcie ${PORT}`); // Wyświetlanie komunikatu o uruchomieniu serwera
    try {
        const numKeys = 10; // Liczba generowanych kluczy
        const keys = generateVotingKeys(numKeys); // Generowanie kluczy do głosowania
        await db.initializeVotingKeys(keys); // Inicjalizacja kluczy w bazie danych
        console.log("Klucze do głosowania zostały wygenerowane i zapisane w bazie danych."); // Informacja o wygenerowanych kluczach
    } catch (err) {
        console.error("Błąd podczas generowania i zapisywania kluczy do głosowania w bazie danych:", err); // Obsługa błędu generowania kluczy
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
