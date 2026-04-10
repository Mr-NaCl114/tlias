# TLiAS Random 文档（前端已打包到 Nginx）


## 1. 现状说明

- 前端容器：`tliasqd`（镜像：`qdd/tliasqd:latest`）
- 后端容器：`tliashd`（镜像：`qdd/tliashd:latest`）
- 前端访问端口：`18080`（宿主机）-> `80`（Nginx 容器）
- 后端访问端口：`18081`（宿主机）-> `8080`（Spring Boot 容器）
- 前端目录挂载：`./qd/dist:/usr/share/nginx/html:ro`
- 可以直接将nginx-1.28.3跑起来，将代码拉取到本地，运行nginx.exe文件就能跑起来

来源：`dev-ops/docker-compose.yml`

## 2. Nginx 代理规则

`dev-ops/qd/nginx.conf` 中已配置：

- `/api/` 转发到后端容器：`http://tliashd:8080/`
- 其余路径走前端静态资源，并支持前端路由刷新（`try_files ... /index.html`）

这意味着：
- 浏览器应访问前端地址：`http://localhost:18080`
- 前端请求接口时用相对路径（例如：`/api/login`）即可由 Nginx 转发

## 3. 启动方式（Docker Compose）

先进入 `dev-ops` 目录并确保外部网络存在（`software_my-network`）：

```powershell
Set-Location "D:\Projects\tlias\dev-ops"
docker network create software_my-network
```

启动：

```powershell
docker compose up -d
```

查看状态：

```powershell
docker compose ps
```

查看日志：

```powershell
docker compose logs -f bigeventqd
docker compose logs -f bigeventhd
```

停止：

```powershell
docker compose down
```

## 4. 常见问题：`POST http://localhost:5173/api/login` 返回 401，后端没收到

这个现象通常是**请求没有经过 Docker 里的 Nginx**，而是走了本地 Vite 开发服务器（`5173`）。

优先按下面顺序排查：

1. 你访问的是不是 `http://localhost:18080`（容器 Nginx）而不是 `http://localhost:5173`（Vite dev server）。
2. 如果必须用 `5173` 开发模式，确认 Vite 是否配置了 `/api` 代理到后端。
3. 登录接口路径是否与后端一致（例如 `/api/login` 或 `/api/emps/login`，以实际后端路由为准）。
4. 登录后请求是否带上 token（一般放在请求头）。

## 5. 联调建议

- 纯联调/演示：优先使用 `http://localhost:18080`，避免本地代理差异。
- 本地前端开发（5173）：补齐 Vite 代理配置，再请求 `/api/*`。
- 后端连库失败时，先检查 `SPRING_DATASOURCE_*` 环境变量和 MySQL 网络可达性。

## 6. 快速自检清单

- 前端页面可打开：`http://localhost:18080`
- 后端容器状态正常：`docker compose ps`
- `/api` 已走 Nginx 反向代理（非 5173 本地开发服务）
- 登录接口路径与后端控制器一致
- 登录后 token 正常透传


