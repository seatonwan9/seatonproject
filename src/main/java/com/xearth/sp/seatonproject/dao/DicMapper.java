package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.Dic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DicMapper extends JpaRepository<Dic,Integer> {

}