package com.weather.loader;

import com.weather.model.WeatherResponse;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static com.weather.loader.WeatherLoader.WEATHER_API_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherLoaderTest {

    private static final String CITY = "London";

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private WeatherLoader subject;

    @Test
    void shouldMakeRestCallToObtainWeatherResponse() {
        WeatherResponse expectedWeatherResponse = new WeatherResponse();
        when(restTemplate.getForObject(WEATHER_API_URL, WeatherResponse.class, CITY, 8 * 3)).thenReturn(expectedWeatherResponse);

        WeatherResponse actualWeatherResponse = subject.load(CITY);

        assertEquals(expectedWeatherResponse, actualWeatherResponse);
        verify(restTemplate).getForObject(WEATHER_API_URL, WeatherResponse.class, CITY, 8 * 3);
    }

}
