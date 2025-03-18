import requests
import json
from datetime import datetime, timedelta

# Tallinna koordinaadid
latitude = 59.4370
longitude = 24.7535

# Yo.no API URL
url = f"https://api.met.no/weatherapi/locationforecast/2.0/compact?lat={latitude}&lon={longitude}"

# Lisame päised, et API päring töötaks õigesti (User-Agent)
headers = {
    "User-Agent": "WeatherApp/1.0 (your-email@example.com)"
}

# Teeme päringu API-le
response = requests.get(url, headers=headers)

# Kontrollime, kas päring õnnestus (staatuskood 200)
if response.status_code == 200:
    # Parsetame JSON vastuse
    data = response.json()
    
    # Kogume tunni prognoosid
    timeseries = data['properties']['timeseries']
    
    # Praegune aeg
    now = datetime.utcnow()
    
    # Tsükkel järgmiste tundide jaoks (nt järgmised 12 tundi)
    for i in range(0, len(timeseries)):
        # Aeg (kuupäev ja kellaaeg)
        timestamp = timeseries[i]['time']
        forecast_time = datetime.strptime(timestamp, "%Y-%m-%dT%H:%M:%SZ")
        
        # Kui prognoos on tulevikus ja vähem kui 12 tundi, siis prindime
        if forecast_time > now and forecast_time <= now + timedelta(hours=18):
            # Õhutemperatuur
            temperature = timeseries[i]['data']['instant']['air_temperature']
            
            # Prindime vastavalt nõutud formaadile
            print(f"{forecast_time.isoformat()} {temperature}C")
else:
    print("Päring ebaõnnestus, staatuskood:", response.status_code)
