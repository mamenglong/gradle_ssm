package org.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.entity.User;
import org.springframework.stereotype.Repository;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}