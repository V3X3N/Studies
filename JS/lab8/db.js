// db.js
const mysql = require('mysql');

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'hasloroot123',
    database: 'voting_portal'
});

connection.connect((err) => {
    if (err) {
        console.error('Błąd połączenia z bazą danych:', err);
        return;
    }
    console.log('Połączono z bazą danych MySQL');
});

const createCandidatesTable = `CREATE TABLE IF NOT EXISTS candidates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    votes INT DEFAULT 0
)`;

connection.query(createCandidatesTable, (err, results, fields) => {
    if (err) {
        console.error('Błąd podczas tworzenia tabeli kandydatów:', err);
        return;
    }
    console.log('Tabela kandydatów została utworzona lub już istnieje');

    // Dodajemy przykładowych kandydatów do tabeli, jeśli tabela jest pusta
    connection.query('SELECT * FROM candidates', (err, results) => {
        if (err) {
            console.error('Błąd podczas pobierania kandydatów:', err);
            return;
        }
        if (results.length === 0) {
            const insertQuery = `INSERT INTO candidates (name) VALUES ?`;
            const candidatesData = [
                ['Kandydat 1'],
                ['Kandydat 2'],
                ['Kandydat 3'],
                ['Kandydat 4']
            ];
            connection.query(insertQuery, [candidatesData], (err, results) => {
                if (err) {
                    console.error('Błąd podczas dodawania przykładowych kandydatów:', err);
                } else {
                    console.log('Dodano przykładowych kandydatów do tabeli kandydatów.');
                }
            });
        }
    });
});

// Inicjalizacja kluczy do głosowania w bazie danych
async function initializeVotingKeys(keys) {
    return new Promise((resolve, reject) => {
        const values = keys.map(key => `('${key}', FALSE)`).join(',');
        connection.query(`INSERT INTO voting_keys (key_value, used) VALUES ${values}`, (err, results) => {
            if (err) {
                reject(err);
            } else {
                resolve();
            }
        });
    });
}

// Pobieranie nieużytego klucza do głosowania z bazy danych
async function getUnusedVotingKey() {
    return new Promise((resolve, reject) => {
        connection.query('SELECT * FROM voting_keys WHERE used = FALSE LIMIT 1', (err, results) => {
            if (err) {
                reject(err);
            } else {
                if (results.length > 0) {
                    resolve(results[0]);
                } else {
                    resolve(null);
                }
            }
        });
    });
}

// Pobieranie kandydatów z bazy danych
async function getCandidates() {
    return new Promise((resolve, reject) => {
        connection.query('SELECT * FROM candidates', (err, results) => {
            if (err) {
                reject(err);
            } else {
                resolve(results);
            }
        });
    });
}

// Eksportowanie funkcji
module.exports = {
    initializeVotingKeys,
    getUnusedVotingKey,
    getCandidates
};
