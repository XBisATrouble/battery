package com.bupt.battery.repository;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.entity.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDORepository extends BaseRepository<UserDO,Long>{
    UserDO findUserDOByUsername(String username);
}
