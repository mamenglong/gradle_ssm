/*
  Created by IntelliJ IDEA.
  User: Long
  Date: 2018/10/24
  Time: 18:57
  To change this template use File | Settings | File Templates.
*/


import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.mybatis.entity.User;
import org.mybatis.mapper.UserMapper;

import java.util.List;

public class MapperTest extends BaseMapperTest {
    private UserMapper userMapper=getSqlsession().getMapper(UserMapper.class);
    @Test
    public void SelectAllUser() {
        List<User> users = userMapper.selectAll();
        Assert.assertNotNull(users);
    }

}
