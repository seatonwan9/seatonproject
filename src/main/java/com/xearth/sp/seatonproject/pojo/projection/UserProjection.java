package com.xearth.sp.seatonproject.pojo.projection;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface UserProjection {

    String getUserName();

    Integer getUserAge();

    String getCompanyName();

}
