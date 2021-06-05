package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.pojo.projection.UserProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseRepository<User, Integer> {

    // 当使用Jpa投影的时候，sql的表名同实体类名，且字段必须使用as重命名
//    @Query("select u.id as id, u.name as name, u.age as age from User u")
    // 当使用原生的sql时，则不需要as重命名（备注：日期等类型可以在sql中使用to_char转换类型，否则投影报错）
    @Query(value = "select u.user_name, u.user_age c.companyName " +
                    "from user u, company c " +
                    "where u.company_id = c.company_id", nativeQuery = true)
    List<UserProjection> findAllUser();

    @Query("select u " +
            "from User u " +
            "where 1=1 " +
            "and (:userName is null or :userName = '' or u.userName = :userName)")
    List<User> findUsersByUserName(@Param("userName") String userName, Pageable pageable);

}