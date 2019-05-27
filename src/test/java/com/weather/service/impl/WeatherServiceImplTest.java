package com.weather.service.impl;

import com.google.common.cache.LoadingCache;
import com.weather.model.WeatherResponse;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    private static final String CITY = "London";

    @Mock
    private LoadingCache<String, WeatherResponse> weatherCache;
    @InjectMocks
    private WeatherServiceImpl subject;

    @Test
    void shouldLoadWeatherResponseFromCache() throws ExecutionException {
        WeatherResponse expectedWeatherResponse = new WeatherResponse();
        when(weatherCache.get(CITY)).thenReturn(expectedWeatherResponse);

        WeatherResponse actualWeatherResponse = subject.getWeatherData(CITY);

        assertEquals(expectedWeatherResponse, actualWeatherResponse);
        verify(weatherCache).get(CITY);
    }

}
