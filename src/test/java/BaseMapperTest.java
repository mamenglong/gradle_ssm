/*
  Created by IntelliJ IDEA.
  User: Long
  Date: 2018/10/24
  Time: 18:54
  To change this template use File | Settings | File Templates.
*/

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;

public class BaseMapperTest {

    private static SqlSessionFactory factory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSqlsession() {
        return factory.openSession();
    }

}
