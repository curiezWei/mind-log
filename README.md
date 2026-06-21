# MindLog

一个基于 Spring Boot 的 Markdown 个人博客系统。

- 后端：Spring Boot 4 + MyBatis + MySQL + JWT
- 前端：jQuery + Editor.md

## 功能

- JWT 登录 / 登出
- Markdown 编写与渲染博客
- 博客列表、详情、新增、编辑、删除（软删除）

## 项目结构

```
mind-log/                     # Maven 项目
├── pom.xml
└── src/
    ├── main/
    │   ├── java/top/curiez/mindlog/
    │   │   ├── config/       # WebConfig, 异常处理, 响应包装
    │   │   ├── controller/   # BlogController, UserController
    │   │   ├── service/      # 业务逻辑
    │   │   ├── mapper/       # MyBatis Mapper
    │   │   ├── model/        # 实体类
    │   │   ├── interceptor/  # JWT 登录拦截器
    │   │   └── utils/        # JWT, 日期工具
    │   └── resources/
    │       ├── application.yml
    │       └── static/       # HTML, CSS, JS, Editor.md
    └── test/
```

## 快速开始

### 环境要求

- Java 17+
- Maven 3.6+
- MySQL 5.7+

### 1. 创建数据库

```sql
CREATE DATABASE java_blog_spring CHARACTER SET utf8;
USE java_blog_spring;

CREATE TABLE blog_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    user_id INT,
    delete_flag INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    github_url VARCHAR(255),
    delete_flag INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO user_info (user_name, password, github_url) VALUES ('curiez', '1234', 'https://github.com/curiez');
```

### 2. 修改数据库配置

编辑 `mind-log/src/main/resources/application.yml`，设置你的数据库用户名和密码。

### 3. 启动

```bash
cd mind-log
mvn spring-boot:run
```

访问 http://localhost:8080/blog_login.html

## API 接口

| Method | URL | 说明 |
|--------|-----|------|
| POST | `/user/login` | 登录，返回 JWT Token |
| GET | `/user/getUserInfo` | 获取当前用户信息 |
| GET | `/user/getAuthorInfo` | 获取作者信息 |
| GET | `/blog/getList` | 博客列表 |
| GET | `/blog/getBlogDetail` | 博客详情 |
| POST | `/blog/add` | 新增博客 |
| POST | `/blog/update` | 更新博客 |
| POST | `/blog/delete` | 删除博客（软删除） |
