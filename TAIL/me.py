import requests
import json

# Geograafilised koordinaadid
latitude = 59.4370
longitude = 24.7535

url = f"https://api.met.no/weatherapi/locationforecast/2.0/compact?lat={latitude}&lon={longitude}"

# Päringu saatmine
response = requests.get(url)

if response.status_code == 200:
    data = response.json()

    # Kuvame ainult 5 esimest tulemust
    for i, timeseries in enumerate(data['properties']['timeseries']):
        if i >= 5:
            break
        
        timestamp = timeseries['time']
        temperature = timeseries['data']['instant']['details']['air_temperature']
        
        print(f"{timestamp} {temperature}C")
else:
    print("Päring ebaõnnestus, staatuskood:", response.status_code)
