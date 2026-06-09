# 校园失物招领管理系统

课程设计项目，基于 Spring Boot + MyBatis + MySQL + Thymeleaf + Bootstrap。

## 功能
- 发布 / 编辑 / 删除失物信息
- 多条件查询（关键词、类别、状态、悬赏金额）
- 服务端数据校验 + 全局异常处理
- AOP 统计接口耗时

## 技术栈
- Spring Boot
- MyBatis
- MySQL
- Thymeleaf
- Bootstrap

## 本地运行
1. 克隆项目：`git clone https://github.com/babatu0s1/LostCampus.git`
2. 导入 IDEA，配置数据库（SQL 文件在项目中）
3. 修改 `application.yml` 数据库连接
4. 运行 `FinalExamLostApplication.java`
5. 访问 `http://localhost:8080`

## 项目结构
简要说明（列出主要包和职责）