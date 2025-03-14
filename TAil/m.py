#need to bash /install pip install requests

import requests
import json

#geograafilised koordinaadid
latitude = 59.4370
longitude = 24.7535

url = f"https://api.met.no/weatherapi/locationforecast/2.0/compact?lat={latitude}&lon={longitude}"

#headers = {"User-Agent": "WeatherApp/1.0 (your-email@example.com)"}
#response = requests.get(headers=headers)

response = requests.get(url,)
if response.status_code == 200:
    data = response.json()
    
    for timeseries in data['properties']['timeseries']:
        timestamp = timeseries['time']
        
        
        temperature = timeseries['data']['instant']['details']['air_temperature']
        
      
        print(f"{timestamp} {temperature}C")
else:
    print("Päring ebaõnnestus, staatuskood:", response.status_code)
