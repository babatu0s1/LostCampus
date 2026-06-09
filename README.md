# 🏫 校园失物招领管理系统

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-blue)](https://openjdk.org/)
[![License](https://img.shields.io/badge/license-MIT-green)](./LICENSE)

课程设计项目 —— 基于 **Spring Boot + MyBatis + MySQL + Thymeleaf + Bootstrap** 构建的校园失物招领 Web 应用。

## ✨ 功能

- **失物信息管理** — 支持失物信息的发布、编辑、删除
- **多条件查询** — 按关键词、类别、状态、悬赏金额等维度组合筛选
- **服务端校验** — 基于 DTO 的参数校验，保证数据合法性
- **全局异常处理** — 统一捕获业务异常，返回友好错误信息
- **AOP 接口监控** — 通过切面统计每个接口的执行耗时

## 🛠 技术栈

| 层面     | 技术               |
| -------- | ------------------ |
| 框架     | Spring Boot 3.3.5  |
| ORM      | MyBatis            |
| 数据库   | MySQL              |
| 模板引擎 | Thymeleaf          |
| 前端     | Bootstrap          |
| JDK      | 17                 |

## 📁 项目结构

```text
src/main/java/com/example/finalexam/
├── FinalExamLostApplication.java     # 启动类
├── aspect/
│   └── OperationLogAspect.java       # AOP 接口耗时统计
├── component/
│   └── StudentProfile.java           # 学生信息组件
├── config/
│   ├── StudentTokenInterceptor.java  # 登录拦截器
│   └── WebConfig.java                # Web MVC 配置
├── controller/
│   ├── LostItemApiController.java    # 失物 REST API
│   └── PageController.java           # 页面路由
├── dto/
│   ├── LostItemCreateDTO.java        # 创建失物 DTO
│   └── LostItemQueryDTO.java         # 查询条件 DTO
├── entity/
│   └── LostItem.java                 # 失物实体类
├── exception/
│   ├── BusinessException.java        # 业务异常
│   └── GlobalExceptionHandler.java   # 全局异常处理器
├── mapper/
│   └── LostItemMapper.java           # MyBatis Mapper 接口
└── service/
    └── LostItemService.java          # 业务逻辑层

src/main/resources/
├── application.yml                   # 主配置（数据库、MyBatis、端口）
├── application.properties            # 补充配置
├── mapper/
│   └── LostItemMapper.xml            # MyBatis XML 映射
└── templates/
    └── items.html                    # Thymeleaf 页面模板
```

## 🚀 本地运行

### 前置条件

- JDK 17+
- Maven 3.6+
- MySQL 5.7+（或 8.0+）

### 步骤

1. **克隆项目**

   ```bash
   git clone https://github.com/babatu0s1/LostCampus.git
   cd LostCampus
   ```

2. **创建数据库**

   ```sql
   CREATE DATABASE IF NOT EXISTS final_web_exam
     DEFAULT CHARACTER SET utf8mb4
     DEFAULT COLLATE utf8mb4_unicode_ci;
   ```

   然后执行项目中的 SQL 建表脚本。

3. **修改配置** — 编辑 `src/main/resources/application.yml`

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/final_web_exam?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
       username: your_username
       password: your_password
   ```

4. **启动应用**

   ```bash
   # 方式一：Maven 命令
   mvn spring-boot:run

   # 方式二：在 IDEA 中运行
   # 右键 FinalExamLostApplication.java → Run
   ```

5. **访问** → 浏览器打开 `http://localhost:8081`

## ⚙️ 配置说明

| 配置项 | 说明 | 默认值 |
| --- | --- | --- |
| `server.port` | 应用端口 | `8081` |
| `spring.datasource.url` | 数据库连接 | `jdbc:mysql://localhost:3306/final_web_exam` |
| `mybatis.configuration.map-underscore-to-camel-case` | 驼峰映射 | `true` |
| `spring.thymeleaf.cache` | 模板缓存（开发建议关闭） | `false` |
