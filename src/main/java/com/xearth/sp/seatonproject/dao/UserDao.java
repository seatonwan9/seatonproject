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

    /**
     * Vo
     * 1.当使用JPA映射（类VO）时，需要指定VO类地址，且参数和顺序必须一一对应
     * new com.xearth.environment.pojo.Vo.GovernequipVo(...)
     * 2.当时使用原生映射（类VO）时，SQL的返回形式是 Map、List<Map> 或 Object，因此使用 List<Map<String,Object>> 接收
     */

    /**
     * Projection
     * 1.当使用JPA映射（接口VO）时，SQL的表名同实体类名，且字段必须使用as重命名
     * @Query("select u.id as id, u.name as name, u.age as age from User u")
     * 2.当使用原生SQL映射（接口VO）时，字段也必须使用as重命名（备注：日期等类型可以在sql中使用to_char转换类型，否则投影报错）
     */
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