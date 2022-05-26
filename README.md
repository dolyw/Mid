## 项目结构

| 模块        | 描述                             |
| ----------- | -------------------------------- |
| xxx-api     | 项目服务API，对内及对外的服务API |
| xxx-app     | 项目启动类及配置类               |
| xxx-service | 项目Service，Mapper，Dto，Entity |
| xxx-share   | 项目服务接口二方库               |
| xxx-web     | 项目WebAPI接口，对前端的API接口  |

## 技术架构

* SpringBoot 2.5.6
* SpringCloud 2020.0.4
* Mybatis Plus 3.4.3
* knife4j 3.0.3

## 接口文档

* 采用 swagger 增强组件 knife4j，支持多分组，WebApi及服务Api接口分组
* 支持接口文档导出Markdown，Html等多种格式，PDF可使用Markdown转换
* 访问地址: [http://localhost:8086/doc.html](http://localhost:8086/doc.html)
 