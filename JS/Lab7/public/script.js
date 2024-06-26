const websocket = new WebSocket('ws://localhost:3000');

// Obsługa wiadomości od serwera
websocket.onmessage = (event) => {
    const data = JSON.parse(event.data);
    updateChart(data);
    updateCandidates(data);
};

// Aktualizacja listy kandydatów
function updateCandidates(data) {
    const container = document.getElementById('candidates-container');
    container.innerHTML = '';

    data.forEach((candidate, index) => {
        const candidateElement = document.createElement('div');
        candidateElement.classList.add('candidate');
        candidateElement.textContent = `${candidate.name}: ${candidate.votes} głosów`;
        container.appendChild(candidateElement);
    });
}

// Aktualizacja wykresu
function updateChart(data) {
    const labels = data.map(candidate => candidate.name);
    const votes = data.map(candidate => candidate.votes);

    if (!chart) {
        const ctx = document.getElementById('vote-chart').getContext('2d');
        chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Głosy',
                    data: votes,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1 // Ustawienie kroku na osi Y na 1
                        }
                    }
                }
            }
        });
    } else {
        chart.data.labels = labels;
        chart.data.datasets[0].data = votes;
        chart.update();
    }
}

let chart; // Zmienna przechowująca obiekt wykresu

// Obsługa głosowania
document.getElementById('vote-button').addEventListener('click', () => {
    const selectElement = document.getElementById('candidate-select');
    const selectedIndex = selectElement.selectedIndex;
    if (selectedIndex !== 0) {
        const candidateIndex = selectedIndex - 1; // Indeks kandydata w tablicy kandydatów
        const key = document.getElementById('key-input').value.trim();
        if (key !== '') {
            websocket.send(`${key}|${candidateIndex}`);
            selectElement.selectedIndex = 0; // Resetowanie listy rozwijanej po oddaniu głosu
        } else {
            console.log("Wprowadź klucz głosowania przed oddaniem głosu.");
        }
    } else {
        console.log("Wybierz kandydata przed oddaniem głosu.");
    }
});

// Dodanie komunikatu ostrzegawczego
websocket.onclose = () => {
    document.getElementById('warning-message').classList.remove('hidden');
};
