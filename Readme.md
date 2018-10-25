# Gradle 构建ssm（spring +springmvc+mybatis）项目
- 使用 intellij idea 2018.2
- 集成 mybatis Generator，逆向生成entity，mapper，mapping
- mybatis框架
- spring mvc框架
- Gradle 管理
- [访问博客](https://blog.csdn.net/smallbabylong/article/details/83375680)
## 创建Gradle项目
1.创建gradle项目：File->New->Project
 ![在这里插入图片描述](https://img-blog.csdn.net/20181025093211590?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)![在这里插入图片描述](https://img-blog.csdn.net/20181025093120728?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![在这里插入图片描述](https://img-blog.csdn.net/20181025093140479?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![在这里插入图片描述](https://img-blog.csdn.net/20181025093148811?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
![在这里插入图片描述](https://img-blog.csdn.net/2018102509325228?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

* 先给出项目结构避免不知道配置文件创建何处
![在这里插入图片描述](https://img-blog.csdn.net/20181025093830644?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![在这里插入图片描述](https://img-blog.csdn.net/20181025093855689?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
## 添加springmvc框架
1.添加依赖

```gradle
 //spring依赖
    compile 'org.springframework:spring-webmvc:5.1.1.RELEASE'
    compile 'org.springframework:spring-beans:5.1.1.RELEASE'
    compile 'org.springframework:spring-context:5.1.1.RELEASE'
    compile 'org.springframework:spring-context-support:5.1.1.RELEASE'
    compile 'org.springframework:spring-web:5.1.1.RELEASE'
    compile 'org.springframework:spring-tx:5.1.1.RELEASE'
    compile 'org.springframework:spring-jdbc:5.1.1.RELEASE'
    compile 'org.springframework:spring-test:5.1.1.RELEASE'
```
2. resource添加配置文件applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!--Spring会去自动扫描base-package的值所表示的包的位置中的java文件，如果扫描到有@Component、@Controller、@Service、@Repository等类似注解的类，会将这个类注册为Bean-->
    <context:component-scan base-package="org.controller"></context:component-scan>

    <!-- 开启SpringMVC注解模式 -->
    <mvc:annotation-driven/>
    <!-- 静态资源默认servlet配置 -->
    <mvc:default-servlet-handler/>
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```
3.配置web.xml
![在这里插入图片描述](https://img-blog.csdn.net/20181025095834903?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

web.xml文件内容：里面涉及到的配置文件将在稍后进行介绍添加。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- 编码过滤器 -->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- web容器启动/停止监听器 -->
    <listener>
        <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
    </listener>

    <!-- web容器与spring上下文整合的监听器 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!-- Spring和mybatis的配置文件 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springConfig/spring-mybatis.xml</param-value>
    </context-param>

    <!-- 配置springmvc的前端控制器：dispatcherServlet -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springConfig/applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
        <async-supported>true</async-supported>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <!-- 此处可以可以配置成*.do，对应struts的后缀习惯 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```
3.添加全局spring配置文件
applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!--Spring会去自动扫描base-package的值所表示的包的位置中的java文件，如果扫描到有@Component、@Controller、@Service、@Repository等类似注解的类，会将这个类注册为Bean-->
    <context:component-scan base-package="org.controller"></context:component-scan>

    <!-- 开启SpringMVC注解模式 -->
    <mvc:annotation-driven/>
    <!-- 静态资源默认servlet配置 -->
    <mvc:default-servlet-handler/>
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```
4.在web.xml里配置，主要是以下内容，位置在图片里标注了

```xml
 <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springConfig/applicationContext.xml</param-value>
        </init-param>
```
![在这里插入图片描述](https://img-blog.csdn.net/20181025100341524?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)


## 添加mybatis generator框架
  1 .添加配置文件
  - 在resource资源目录下创建jdbc_config.properties文件
 *注：可以创建二级目录或者更改文件名，但要保证用到的地方名字保持一致，本项目文件名都是配好的可直接使用，更改文件名时请注意搜索一下引用，用到的包名需要一致，具体可查看目录结构。*
```properties
# MySQL 驱动
jdbc.driverClassName = com.mysql.jdbc.Driver
# JDBC URL
jdbc.url =jdbc:mysql://127.0.0.1:3306/myproject?useUnicode:true&characterEncoding:UTF-8
# 数据库用户名及密码
jdbc.username = root
jdbc.password = 123456
# 实体类所在包
package.model = org.mybatis.entity
#  mapper 所在包
package.mapper = org.mybatis.mapper
#  mapper xml 文件所在包 默认存储在 resources 目录下
package.xml = mybatis/mapping


# 初始化连接大小
initialSize=0
# 连接池最大数量
maxActive=20
# 连接池最大空闲
maxIdle=20
# 连接池最小空闲
minIdle=1
# 获取连接最大等待时间
maxWait=60000

```
- resource目录下创建generatorConfig.xml配置文件
*注意包名一致*
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--配置文件详解https://www.cnblogs.com/ygjlch/p/6471924.html-->
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--
    context:生成一组对象的环境
    id:必选，上下文id，用于在生成错误时提示
    defaultModelType:指定生成对象的样式
        1，conditional：类似hierarchical；
        2，flat：所有内容（主键，blob）等全部生成在一个对象中；
        3，hierarchical：主键生成一个XXKey对象(key class)，Blob等单独生成一个对象，其他简单属性在一个对象中(record class)
    targetRuntime:
        1，MyBatis3：默认的值，生成基于MyBatis3.x以上版本的内容，包括XXXBySample；
        2，MyBatis3Simple：类似MyBatis3，只是不生成XXXBySample；
    introspectedColumnImpl：类全限定名，用于扩展MBG
-->
    <context id="default" targetRuntime="MyBatis3Simple" defaultModelType="flat" >
        <!-- 生成的pojo，将implements Serializable -->
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"></plugin>
        <commentGenerator>
            <!-- 注释生成器（false 开启默认 true关闭） -->
            <property name="suppressAllComments" value="true"></property>
            <!-- 注释生成器时间显示（false 开启默认 true关闭） -->
            <property name="suppressDate" value="true"></property>
            <!-- java 文件编码格式  -->
            <property name="javaFileEncoding" value="utf-8"/>
            <!--格式化java代码-->
            <property name="javaFormatter" value="org.mybatis.generator.api.dom.DefaultJavaFormatter"></property>
            <!-- 格式化XML代码 -->
            <property name="xmlFormatter" value="org.mybatis.generator.api.dom.DefaultXmlFormatter"/>
        </commentGenerator>

        <!-- jdbc 连接信息  -->
        <jdbcConnection driverClass="${driverClass}"
                        connectionURL="${connectionURL}"
                        userId="${userId}"
                        password="${password}">
        </jdbcConnection>
        <!-- 定义java类型解析器的属性 -->
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!-- 实体类所在包名 -->
        <javaModelGenerator targetPackage="${modelPackage}" targetProject="${src_main_java}">
            <property name="enableSubPackages" value="true"></property>
            <property name="trimStrings" value="true"></property>
        </javaModelGenerator>

        <!-- 生成映射文件配置 xml 所在包名 -->
        <sqlMapGenerator targetPackage="${sqlMapperPackage}" targetProject="${src_main_resources}">
            <property name="enableSubPackages" value="true"></property>
        </sqlMapGenerator>
        <!--
               type= ANNOTATEDMAPPER 表示不生成xml文件
               这里我用 XMLMAPPER
               -->
        <!-- org.mapper 所在包名 -->
        <javaClientGenerator targetPackage="${mapperPackage}" targetProject="${src_main_java}" type="XMLMAPPER">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <!-- 要生成的表 -->
        <!-- 这里的百分号代表对该数据库中所有的表进行生成 -->
        <table tableName="%">
            <generatedKey column="id" sqlStatement="Mysql" identity="true"/>
        </table>
        <!--<table tableName="city" domainObjectName="City" enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
   -->
    </context>
</generatorConfiguration>
```

 2.添加依赖
 
```gradle
configurations {
    mybatisGenerator
}
    dependencies{
       //  mybatis-generator mybatis逆向
        mybatisGenerator 'org.mybatis.generator:mybatis-generator-core:1.3.7'
        mybatisGenerator 'mysql:mysql-connector-java:5.1.45'
        mybatisGenerator 'tk.mybatis:mapper:4.0.4'
        }
def getDbProperties = {
    def properties = new Properties()
    file("src/main/resources/myBatisGenerator/jdbc_config.properties").withInputStream { inputStream ->
        properties.load(inputStream)
    }
    properties
}

task mybatisGenerator << {
    def properties = getDbProperties()
    ant.properties['targetProject'] = projectDir.path
    ant.properties['driverClass'] = properties.getProperty("jdbc.driverClassName")
    ant.properties['connectionURL'] = properties.getProperty("jdbc.url")
    ant.properties['userId'] = properties.getProperty("jdbc.username")
    ant.properties['password'] = properties.getProperty("jdbc.password")
    ant.properties['src_main_java'] = sourceSets.main.java.srcDirs[0].path
    ant.properties['src_main_resources'] = sourceSets.main.resources.srcDirs[0].path
    ant.properties['modelPackage'] = properties.getProperty("package.model")
    ant.properties['mapperPackage'] = properties.getProperty("package.mapper")
    ant.properties['sqlMapperPackage'] = properties.getProperty("package.xml")
    ant.taskdef(
            name: 'mbgenerator',
            classname: 'org.mybatis.generator.ant.GeneratorAntTask',
            classpath: configurations.mybatisGenerator.asPath
    )
    ant.mbgenerator(overwrite: true,
            configfile: 'src/main/resources/myBatisGenerator/generatorConfig.xml', verbose: true) {
        propertyset {
            propertyref(name: 'targetProject')
            propertyref(name: 'userId')
            propertyref(name: 'driverClass')
            propertyref(name: 'connectionURL')
            propertyref(name: 'password')
            propertyref(name: 'src_main_java')
            propertyref(name: 'src_main_resources')
            propertyref(name: 'modelPackage')
            propertyref(name: 'mapperPackage')
            propertyref(name: 'sqlMapperPackage')
        }
    }
}
```
3.配置完成后生成文件
![在这里插入图片描述](https://img-blog.csdn.net/20181025094847147?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NtYWxsYmFieWxvbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## 添加mybatis框架
1.添加依赖

```gradle
dependencies{
//mybatis依赖
    compile 'org.mybatis:mybatis:3.4.6'
    //mysql依赖
    compile 'mysql:mysql-connector-java:5.1.45'
        //mybatis-spring整合
    compile 'org.mybatis:mybatis-spring:1.3.2'
    }
    
```
2.resource目录下添加配置文件spring-mybatis.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
 http://www.springframework.org/schema/context
 http://www.springframework.org/schema/context/spring-context-3.1.xsd
 http://www.springframework.org/schema/mvc
 http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd">
    <!-- 自动扫描 -->
    <context:component-scan base-package="org.mybatis.mapper" />
    <!-- 引入配置文件 -->
    <bean id="propertyConfigurer"
          class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="classpath:myBatisGenerator/jdbc_config.properties" />
    </bean>

    <bean name="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driverClassName}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
        <!-- 初始化连接大小 -->
        <property name="initialSize" value="${initialSize}"></property>
        <!-- 连接池最大数量 -->
        <property name="maxActive" value="${maxActive}"></property>
        <!-- 连接池最大空闲 -->
        <!--     <property name="maxIdle" value="${maxIdle}"></property>
         -->    <!-- 连接池最小空闲 -->
        <property name="minIdle" value="${minIdle}"></property>
        <!-- 获取连接最大等待时间 -->
        <property name="maxWait" value="${maxWait}"></property>
    </bean>

    <!-- spring和MyBatis完美整合，不需要mybatis的配置映射文件 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- 自动扫描mapping.xml文件 -->
        <property name="mapperLocations" value="classpath:mybatis/mapping/*.xml"></property>
    </bean>

    <!-- DAO接口所在包名，Spring会自动查找其下的类 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="org.mybatis.mapper" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>

    <!-- (事务管理)transaction manager, use JtaTransactionManager for global tx -->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>

</beans>
```
3.web.xm里配置读取配置

```xml
    <!-- Spring和mybatis的配置文件 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springConfig/spring-mybatis.xml</param-value>
    </context-param>
```

## 编写一个测试
- 已经生成mapper，entity，mapping等
- 创建一个UserController

```java
/*
  Created by IntelliJ IDEA.
  User: Long
  Date: 2018/10/24
  Time: 11:30
  To change this template use File | Settings | File Templates.
*/
package org.controller;

import org.mybatis.entity.User;
import org.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController   {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/insert", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String insertUser() {
        User user = new User();
        user.setEmail("sssssasas");
        user.setPassword("44555");
        user.setSex("sss");
        userMapper.insert(user);
        return user.toString();
    }

    @RequestMapping(value = "/findAll",produces={"application/json; charset=UTF-8"})
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}

```

## 采坑批注
- 如果没有引入 mybatis和spring的整合包 `compile 'org.mybatis:mybatis-spring:1.3.2'`会出现错误，在 测试时会出现：

```j
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 
```
-解决方式：
引入依赖，并正确配置spring-mybatis.xml文件


### 附
- [mybatis配置官方网站](http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html#Auto-mapping)
- > [spring注解解析](https://blog.csdn.net/LF_Software_Studio/article/details/8256510)
- [依赖搜索网站](https://search.maven.org/)



























  
