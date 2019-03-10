# sw-jooq
springboot + jooq + flyway整合测试例子

## 使用方法
1、修改application.yml中的jdbc连接信息，并创建jooq模式

2、执行mvn clean install，这步会利用flyway自动创建表结构、同时jooq会根据表结构创建相应的类

3、运行启动类Application
