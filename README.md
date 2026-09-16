# 校园掌上购物系统

> 一个面向高校师生的综合性校园电商平台，采用前后端分离架构，包含 **移动端（uni-app）+ 管理后台（Vue 3）+ 服务端（Spring Boot）** 三部分。支持管理员、商家、用户三类角色，覆盖商品/店铺管理、购物车与订单、论坛互动、在线留言、收藏、出入库、实时通讯等场景。

---

## ✨ 功能特性

- **多角色权限**：管理员 / 商家 / 用户 ，基于 Shiro + Token 的菜单级权限控制
- **商品与店铺**：商品分类、商品上下架与审核、店铺管理与评论
- **交易闭环**：购物车、订单（待支付/已支付/已发货/已完成/已取消/已退款）、收货地址管理
- **校园互动**：论坛发帖与回复、在线留言、系统通知
- **实时通讯**：基于 WebSocket 的聊天 / 私信（商家 ↔ 用户）
- **数据可视化**：管理后台基于 ECharts 的统计数据展示（含词云）
- **跨端移动端**：uni-app 一套代码可编译 H5 / 微信小程序 / App

---

## 🧱 技术架构

```
┌─────────────────┐    ┌─────────────────┐    ┌──────────────────────┐
│  移动端 (uni-app) │    │ 管理后台 (Vue 3) │───▶│  Spring Boot 服务端   │
│  H5/小程序/App   │    │  Element Plus   │    │  MyBatis-Plus + Shiro │
└─────────────────┘    └─────────────────┘    └───────────┬──────────┘
                                                          │
                                                  ┌───────▼────────┐
                                                  │    MySQL 数据库   │
                                                  └────────────────┘
```

| 模块 | 技术栈 |
| --- | --- |
| 服务端 | Spring Boot 2.2.2（Java 8）、MyBatis-Plus、Apache Shiro、MySQL、WebSocket、FastJson、Hutool、Lombok |
| 管理后台 | Vue 3.3 + TypeScript + Vite 4 + Pinia + Vue Router + Element Plus + ECharts + TailwindCSS |
| 移动端 | uni-app（Vue 语法），可编译 H5 / 微信小程序 / App |
---

## 📁 项目结构

```
.
├── springbootzkgw/          # Spring Boot 后端服务
│   ├── pom.xml
│   ├── db/springbootzkgw.sql   # 数据库初始化脚本
│   └── src/main/
│       ├── java/com/            # controller / service / dao / entity ...
│       └── resources/
│           ├── application.yml   # 数据源、端口等配置
│           ├── mapper/           # MyBatis-Plus XML
│           └── static/upload/    # 上传文件目录
│
├── admin/admin/             # Vue 3 管理后台（Web 端）
│   ├── package.json
│   ├── vite.config.ts        # 开发代理 /dev-api → 后端
│   └── src/                  # 路由、状态、页面、接口
│
└── front/                   # uni-app 移动端
    ├── manifest.json         # 应用配置（AppID、名称等）
    ├── pages.json            # 页面与 TabBar 配置
    ├── api/base.js           # 后端接口地址（需按环境修改）
    └── pages/                # 各业务页面
```

> 说明：`front/front/` 目录下另存有一份与管理后台相同的 Vue 3 工程副本，日常开发与部署使用 `admin/admin/` 即可。

---

## 🚀 快速开始

### 环境要求

| 工具 | 版本 |
| --- | --- |
| JDK | 1.8 |
| Maven | 3.x |
| MySQL | 5.7 / 8.0 |
| Node.js | 18.x（管理后台 dev 依赖） |
| HBuilderX（推荐） | 最新版（运行移动端） |

---

### 1. 启动后端（Spring Boot）

```bash
# 1) 创建数据库并导入初始数据
mysql -uroot -proot
CREATE DATABASE springbootzkgw CHARACTER SET utf8mb4;
mysql -uroot -proot springbootzkgw < springbootzkgw/db/springbootzkgw.sql

# 2) 按需修改数据源配置
#    文件：springbootzkgw/src/main/resources/application.yml
#    spring.datasource.username / password 改为你自己的 MySQL 账号

# 3) 编译并运行
cd springbootzkgw
mvn clean package -DskipTests
java -jar target/springbootzkgw-0.0.1-SNAPSHOT.jar
```

后端默认地址：**http://localhost:8080/springbootzkgw**


---

### 2. 启动管理后台（Vue 3）

```bash
cd admin/admin
npm install        # 或 pnpm install
npm run dev        # 开发模式，默认 http://localhost:8888
```

开发服务器通过 Vite 代理把 `/dev-api` 转发到 `http://127.0.0.1:8080/springbootzkgw`（见 `.env.development`）。
如需修改后端地址，编辑 `admin/admin/.env.development` 中的 `VITE_APP_SERVICE_URL`。

构建生产包：`npm run build`（产物在 `dist/`，部署到 Nginx 等静态服务器，并将请求代理到后端）。

---

### 3. 运行移动端（uni-app）

**方式 A：HBuilderX（推荐）**
1. 用 HBuilderX 打开 `front/` 目录
2. 顶部菜单「运行」→ 运行到浏览器（H5）/ 运行到微信开发者工具 / 真机运行

**方式 B：命令行（H5）**
```bash
cd front
npm install
npm run dev:h5
```

> ⚠️ 移动端后端地址默认写死在 `front/api/base.js`：
> ```js
> url: "http://10.70.163.190:8080/springbootzkgw/"
> ```
> 请把它改成你本机或局域网可访问的地址（如 `http://localhost:8080/springbootzkgw/` 或你的内网 IP），否则手机/模拟器无法连上后端。

---

## 🔑 默认账号

> 初始账号由数据库初始化脚本写入，常见默认账号如下（具体以 `springbootzkgw.sql` 为准）：

| 角色 | 账号 | 说明 |
| --- | --- | --- |
| 管理员 | `admin` | 拥有全部权限 |
| 商家 | `商家账号` | 管理自己的店铺与商品 |
| 用户 | `用户账号` | 购物、收藏、互动 |

登录时请在登录页选择对应**角色**，并输入图形验证码。

---

## 📝 模块一览

后端接口（`com.controller`）覆盖：用户、商家、商品、商品分类、店铺、购物车、订单、地址、论坛、系统通知、在线留言、收藏、出入库/库房/出库、聊天、信件、问诊、预约、租赁、轮播图配置、文件上传等。

---

## 📄 许可证

本项目为毕业设计 / 学习用途，仅供个人学习与演示，请勿用于商业用途。

---

Made with ❤️ by [SHAZIITOO]
