const websocket = new WebSocket('ws://localhost:3000');

// Inicjalizacja wykresu słupkowego
let chart;

// Obsługa wiadomości od serwera
websocket.onmessage = (event) => {
    const data = JSON.parse(event.data);
    updateChart(data);
    updateCandidates(data);
};

// Aktualizacja wykresu słupkowego
function updateChart(data) {
    const labels = data.map(candidate => candidate.name);
    const votes = data.map(candidate => candidate.votes);

    if (chart) {
        chart.data.labels = labels;
        chart.data.datasets[0].data = votes;
        chart.update();
    } else {
        const ctx = document.getElementById('vote-chart').getContext('2d');
        chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Votes',
                    data: votes,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
}

// Aktualizacja listy kandydatów
function updateCandidates(data) {
    const container = document.getElementById('candidates-container');
    container.innerHTML = '';

    data.forEach((candidate, index) => {
        const candidateElement = document.createElement('div');
        candidateElement.classList.add('candidate');
        candidateElement.textContent = `${candidate.name}: ${candidate.votes} głosów`;
        candidateElement.onclick = () => {
            websocket.send(index.toString());
        };
        container.appendChild(candidateElement);
    });
}
