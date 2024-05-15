// script.js

const websocket = new WebSocket('ws://localhost:3000'); // Deklaracja stałej z użyciem 'const'

websocket.onmessage = (event) => { // Wyrażenie strzałkowe dla funkcji obsługi zdarzenia
    const data = JSON.parse(event.data);
    updateChart(data);
    updateCandidates(data);
};

function updateCandidates(data) {
    const container = document.getElementById('candidates-container');
    container.innerHTML = '';

    data.forEach((candidate, index) => { // Wyrażenie strzałkowe dla funkcji zwrotnej forEach
        const candidateElement = document.createElement('div');
        candidateElement.classList.add('candidate');
        candidateElement.textContent = `${candidate.name}: ${candidate.votes} głosów`;
        container.appendChild(candidateElement);
    });
}

function updateChart(data) {
    const labels = data.map(candidate => candidate.name); // Metoda tablicowa 'map' i wyrażenie strzałkowe
    const votes = data.map(candidate => candidate.votes); // Metoda tablicowa 'map' i wyrażenie strzałkowe

    if (!chart) { // Nowy zapis skrócony dla sprawdzenia wartości null lub undefined
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
                            stepSize: 1
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

let chart;

document.getElementById('vote-button').addEventListener('click', () => { // Metoda 'addEventListener' z użyciem funkcji strzałkowej
    const selectElement = document.getElementById('candidate-select');
    const selectedIndex = selectElement.selectedIndex;
    if (selectedIndex !== 0) {
        const candidateIndex = selectedIndex - 1;
        const key = document.getElementById('key-input').value.trim();
        if (key !== '') {
            websocket.send(`${key}|${candidateIndex}`);
            selectElement.selectedIndex = 0;
            // Dodajmy tutaj wywołanie markKeyAsUsed
            fetch(`/markKeyAsUsed/${key}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => console.log(data))
                .catch(error => console.error('Error:', error));
        } else {
            console.log("Wprowadź klucz głosowania przed oddaniem głosu.");
        }
    } else {
        console.log("Wybierz kandydata przed oddaniem głosu.");
    }
});

websocket.onclose = () => { // Wyrażenie strzałkowe dla funkcji obsługi zdarzenia
    document.getElementById('warning-message').classList.remove('hidden');
};
