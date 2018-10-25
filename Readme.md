# Gradle 构建ssm（spring +springmvc+mybatis）项目
- 使用 intellij idea 2018.2
- 集成 mybatis Generator，逆向生成entity，mapper，mapping
- mybatis框架
- spring mvc框架
- Gradle 管理
## 创建Gradle项目
1.创建gradle项目：File->New->Project
 
## 添加mybatis generator框架
1.添加依赖
'''
dependencies{
   //  mybatis-generator mybatis逆向
    mybatisGenerator 'org.mybatis.generator:mybatis-generator-core:1.3.7'
    mybatisGenerator 'mysql:mysql-connector-java:5.1.45'
    mybatisGenerator 'tk.mybatis:mapper:4.0.4'
    }
 '''
    