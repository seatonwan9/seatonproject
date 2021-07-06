package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.WeatherDao;
import com.xearth.sp.seatonproject.pojo.Weather;
import com.xearth.sp.seatonproject.service.WeatherService;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl extends BaseServiceImpl<WeatherDao, Weather, Integer> implements WeatherService {
}
