package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.Weather;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface WeatherDao extends JpaRepositoryImplementation<Weather, Integer> {
}
