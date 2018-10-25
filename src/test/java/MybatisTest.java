/*
  Created by IntelliJ IDEA.
  User: Long
  Date: 2018/10/24
  Time: 23:03
  To change this template use File | Settings | File Templates.
*/

import org.junit.Assert;
import org.junit.Test;
import org.mybatis.entity.User;
import org.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import java.util.List;

public class MybatisTest extends Junit4Test {
    @Autowired
    private UserMapper userMapper;
    @Test
    @Transactional
    @Rollback(false)//防止事务自动回滚
    public void SelectAllUser() {
        List<User> users = userMapper.selectAll();
        Assert.assertNotNull(users);
    }
}
