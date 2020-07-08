# SpringBoot-CRUD
SpringBoot整合CRUD实现员工管理案例，将Mybatis整合到原项目中，加入了MySQL数据库。
#### 环境要求

- IDEA
- MySQL 
- Maven  
- 需要熟练掌握MySQL数据库，SpringBoot及MyBatis知识，简单的前端知识；

#### 数据库环境

创建案例所使用的数据库

```sql
CREATE DATABASE `springboot`;

USE `springboot`;
```

创建登陆用户数据表

```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `user_name` varchar(255)  NOT NULL COMMENT '用户名',
  `password` varchar(255)  NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

INSERT INTO `user` VALUES (1, 'admin', '123456');

```



创建部门信息的数据库表

```sql
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(10) NOT NULL,
  `department_name` varchar(255)  NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `department` VALUES (1, '市场部');
INSERT INTO `department` VALUES (2, '技术部');
INSERT INTO `department` VALUES (3, '销售部');
INSERT INTO `department` VALUES (4, '客服部');
INSERT INTO `department` VALUES (5, '公关部');
COMMIT;
```



创建存放员工信息的数据库表

```sql
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(255)  NOT NULL COMMENT '员工姓名',
  `email` varchar(255)  NOT NULL COMMENT '员工邮箱',
  `gender` int(2) NOT NULL COMMENT '员工性别',
  `department_id` int(10) NOT NULL COMMENT '部门编号',
  `date` date NOT NULL COMMENT '入职日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `employee` VALUES (1, '张三', 'zhangsan@gmail.com', 0, 1, '2020-02-12');
INSERT INTO `employee` VALUES (2, '李四', 'lisi@qq.com', 1, 2, '2020-02-05');
INSERT INTO `employee` VALUES (3, '王五', 'wangwu@126.com', 0, 3, '2020-02-15');
INSERT INTO `employee` VALUES (4, '赵六', 'zhaoliu@163.com', 1, 4, '2020-02-21');
INSERT INTO `employee` VALUES (5, '田七', 'tianqi@foxmail.com', 0, 3, '2020-02-14');
INSERT INTO `employee` VALUES (10, '王伟', 'wangwei@gmail.com', 1, 3, '2020-02-08');
INSERT INTO `employee` VALUES (11, '张伟', 'zhangwei@gmail.com', 1, 2, '2020-02-11');
INSERT INTO `employee` VALUES (12, '李伟', 'liwei@gmail.com', 1, 3, '2020-02-18');
COMMIT;
```

#### 基本环境搭建

1. 新建Spring项目， 添加Lombok，Spring Web，Thymeleaf，Mybatis，MySQL Driver的支持
2. 相关的pom依赖

```xml
      <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```

3. 建立基本结构和配置框架

- com.mqp.pojo
- com.mqp.dao
- com.mqp.mapper
- com.mqp.service   
- com.mqp.config

4. application.properties里配置数据库连接信息及Mapper映射文件信息

```properties
spring.datasource.username=root
spring.datasource.password=m12345
spring.datasource.url=jdbc:mysql://localhost:3306/springboot?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
mybatis.type-aliases-package=com.mqp.pojo
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.configuration.map-underscore-to-camel-case=true
spring.messages.basename=i18n.login

```

5. 测试数据库连接

```java
package com.mqp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringbootCrudApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
        connection.close();
    }

}
```
