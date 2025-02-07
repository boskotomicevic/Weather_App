
import './weather.css'
import clear_icon from '../assets/clear.png'
import cloud_icon from '../assets/cloud.png'
import drizzle_icon from '../assets/drizzle.png'
import humidity_icon from '../assets/humidity.png'
import rain_icon from '../assets/rain.png'
import snow_icon from '../assets/snow.png'
import wind_icon from '../assets/wind.png'
import React, {useState, useEffect, useRef} from 'react'

const Weather = () => {

   
    const API_KEY = import.meta.env.VITE_APP_ID;
    const [weather, setWeather] = useState(null);
    const [weatherData, setWeatherData] = useState(false);
    const inputRef = useRef();

    const allIcons = {
        "01d":clear_icon,
        "01n": clear_icon,
        "02d": cloud_icon,
        "02n":cloud_icon,
        "03d":cloud_icon,
        "03n":cloud_icon,
        "04d":drizzle_icon,
        "04n":drizzle_icon,
        "09d": rain_icon,
        "09n": rain_icon,
        "10d": rain_icon,
        "10n": rain_icon,
        "13d": snow_icon,
        "13n":snow_icon,
    }
    const fetchLatestWeather = async () => {
        const response = await fetch("http://localhost:8080/weather/latest");
        const data = await response.json();
        setWeather(data);  // Postavlja u state
      };
    
      useEffect(() => {
        fetchLatestWeather();
        const interval = setInterval(fetchLatestWeather, 30000); // Proverava na svakih 30 sekundi
        return () => clearInterval(interval);
      }, []);
    

    const search = async (city) =>{
        if(city === ""){
            alert("Enter City Name");
            return;
        }
        try {
            const url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${API_KEY}`
            
            const response = await fetch(url);
            const data = await response.json();
            if(!response.ok){
                alert(data.message);
                return;
            }
            console.log(data);

            const icon = allIcons[data.weather[0].icon] || clear_icon;

            setWeatherData({
                humidity: data.main.humidity,
                windSpeed: data.wind.speed,
                temperature: Math.floor(data.maim.temp),
                location: data.name,
                icon: icon
            });

        } catch (error) {
            setWeatherData(false);
            console.error("Error in fetching weather data");
        }
    }
    useEffect(() => {
        search("New York")
    },[])
  return (
    <div className='weather'>
        <div className="search-bar">
            <input type="text" ref={inputRef} placeholder='Search'/>
            <button onClick={()=>search(inputRef.current.value)}>Search</button>
        </div>
        {weatherData?<>
            <img src={weatherData.icon} className='weather-icon'/>
        <p className='temperature'>{weatherData.temperature}</p>
        <p className='location'>{weatherData.location}</p>
        <p className='recomendation'>Slojevito oblacenje</p>

        <div className='weather-data'>
            <div className="col">
                <img src={humidity_icon} className='humidity-icon'/>
                <div>
                    <p>{weatherData.humidity}</p>
                    <span>Humidity</span>
                </div>
            </div>

            <div className="col">
                <img src={wind_icon} className='wind-icon'/>
                <div>
                    <p>{weatherData.windSpeed}</p>
                    <span>Wind Speed</span>
                </div>
            </div>
            <p>Outfit Recommendation: {weather.weather_outfit_recommendation}</p>
        </div>
        </>:<></>}

        

    </div>
  )
}

export default Weather;
