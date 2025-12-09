package com.revature.mock;

public class WeatherService
{
    private WeatherApiClient weatherApiClient;

    public WeatherService(WeatherApiClient weatherApiClient)
    {
        this.weatherApiClient = weatherApiClient;
    }

    //int temp = 15;
    public String getWeatherMessages(String city)
    {
        double temp = weatherApiClient.fetchTemperature(city);
        if(temp > 30)
        {
            return "It's Hot in " + city;
        }else if(temp > 15)
        {
            return "It's Warm in " + city;
        }
        return "It's cold in " + city;
    }

    public void refresh(String city)
    {
        weatherApiClient.fetchTemperature(city);
    }
}
