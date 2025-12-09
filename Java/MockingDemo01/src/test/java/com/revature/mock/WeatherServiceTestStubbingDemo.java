package com.revature.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherServiceTestStubbingDemo
{
    @Test
    public void testWeatherServiceWithStubbing()
    {
        WeatherApiClient apiClient = Mockito.mock(WeatherApiClient.class);

        //Stubbing: define what the mock should return
        Mockito.when(apiClient.fetchTemperature("Plano")).thenReturn(35.0);

        WeatherService service = new WeatherService(apiClient);
        String message = service.getWeatherMessages("Plano");
        assertEquals("It's hot in Plano", message);
    }
}
