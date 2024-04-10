function analyzeAndDisplayWeatherData(data) {
  const temperatureKelvin = data.main.temp;
  const temperatureCelsius = temperatureKelvin - 273.15;

  const weatherDescription = data.weather[0].description;
  const iconCode = data.weather[0].icon;
  const iconUrl = `https://openweathermap.org/img/w/${iconCode}.png`;

  const weatherIconElement = document.getElementById('weather-icon');
  weatherIconElement.innerHTML = `<img src="${iconUrl}" alt="Weather Icon" class="icon">`;

  const weatherDescriptionElement = document.getElementById('weather-description');
  weatherDescriptionElement.textContent = `Weather Description: ${weatherDescription}`;

  const temperatureElement = document.getElementById('temperature');
  temperatureElement.textContent = `Temperature: ${temperatureCelsius.toFixed(2)}°C`;

  const br = document.createElement("br");
  weatherDescriptionElement.parentNode.insertBefore(br, weatherDescriptionElement.nextSibling);

  const humidity = data.main.humidity;
  const windSpeed = data.wind.speed;
  const pressure = data.main.pressure;
  const windDirection = data.wind.deg;
  const rainProbability = data.rain ? (data.rain['1h'] ? data.rain['1h'] : 0) : 0;

  const otherInfoElement = document.getElementById('other-info');
  otherInfoElement.innerHTML = `
    <p>Humidity: ${humidity}%</p>
    <p>Wind Speed: ${windSpeed} m/s</p>
    <p>Pressure: ${pressure} hPa</p>
    <p>Wind Direction: ${windDirection}°</p>
    <p>Rain Probability: ${rainProbability} mm/h</p>
  `;
}

function getWeatherDataForKatowice() {
  const city = "YOUR-CITY-NAME";
  const apiKey = 'YOUR-API-HERE';

  const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`;

  fetch(apiUrl)
    .then(response => response.json())
    .then(data => {
      analyzeAndDisplayWeatherData(data);
    })
    .catch(error => console.error('Error fetching weather data:', error));
}

document.addEventListener("DOMContentLoaded", function() {
  getWeatherDataForKatowice();

  setInterval(getWeatherDataForKatowice, 30000);
});
