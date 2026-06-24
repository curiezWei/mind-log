# MindLog

一个基于 Spring Boot 的个人博客系统，支持 Markdown 编写与渲染。

## 技术栈

| 层次 | 技术 |
|------|------|
| 后端框架 | Spring Boot 4.0 + Spring MVC |
| ORM | MyBatis + MyBatis-Plus |
| 数据库 | MySQL 5.7+ |
| 认证授权 | JWT（jjwt 0.11.5） |
| 前端 | jQuery + Editor.md（Markdown 编辑器） |
| 构建工具 | Maven 3.6+ |
| 语言 | Java 17+ |

## 功能特性

- **JWT 认证** — 用户名密码登录 / 游客模式，Token 自动注入与过期处理
- **博客 CRUD** — 新增、编辑、查看、软删除，支持 Markdown 编辑与实时预览
- **访客/作者视图** — 游客可浏览，登录用户可管理自己的文章
- **统一响应封装** — 所有 API 返回统一格式的 `Result<T>`
- **全局异常处理** — 统一错误返回，避免信息泄露

## 项目结构

```
mind-log
├── pom.xml
└── src/main/java/top/curiez/mindlog/
    ├── MindLogApplication.java        # 启动类
    ├── config/                        # 拦截器、异常处理、响应包装配置
    ├── constant/                      # 常量定义
    ├── controller/                    # API 控制器（Blog / User）
    ├── enums/                         # 响应状态枚举
    ├── interceptor/                   # JWT 登录拦截器
    ├── mapper/                        # MyBatis Mapper 接口
    ├── model/                         # 实体类与统一响应模型
    ├── service/                       # 业务逻辑层
    └── utils/                         # JWT 工具、日期工具
```

前端静态页面位于 `src/main/resources/static/`，包含登录、列表、详情、编辑等页面。

## 快速开始

### 1. 创建数据库与表

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

INSERT INTO user_info (user_name, password, github_url)
VALUES ('curiez', '1234', 'https://github.com/curiez');
```

### 2. 配置数据库连接

编辑 `mind-log/src/main/resources/application.yml`，设置你的数据库用户名和密码。

### 3. 启动

```bash
cd mind-log
mvn spring-boot:run
```

访问 http://localhost:8080/blog_login.html

默认账号：`curiez` / `1234`，或点击「游客模式」直接浏览。

## API 接口

| Method | URL | 认证要求 | 说明 |
|--------|-----|----------|------|
| POST | `/user/login` | 否 | 登录，返回 JWT Token |
| POST | `/user/guest` | 否 | 游客登录 |
| GET | `/user/getUserInfo` | 是 | 获取当前用户信息 |
| GET | `/user/getAuthorInfo?blogId=X` | 是 | 获取作者信息 |
| GET | `/blog/getList` | 是 | 博客列表 |
| GET | `/blog/getBlogDetail?blogId=X` | 是 | 博客详情 |
| POST | `/blog/add` | 是（非游客） | 新增博客 |
| POST | `/blog/update` | 是（非游客） | 更新博客 |
| POST | `/blog/delete?blogId=X` | 是（非游客） | 删除博客（软删除） |
