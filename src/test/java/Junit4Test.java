/*
  Created by IntelliJ IDEA.
  User: Long
  Date: 2018/10/24
  Time: 23:01
  To change this template use File | Settings | File Templates.
*/

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(value = SpringJUnit4ClassRunner.class) // 测试运行器
@ContextConfiguration(locations = { "classpath:springConfig/spring-mybatis.xml" }) // 加载配置文件
@Transactional(transactionManager="transactionManager")
@Rollback(value=true)
public class Junit4Test{}