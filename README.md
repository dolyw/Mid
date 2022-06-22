## 项目结构

| 模块        | 描述                             |
| ----------- | -------------------------------- |
| xxx-app     | 项目启动类及配置类               |
| xxx-open-api     | 项目服务API，对内及对外的服务API |
| xxx-service | 项目Service，Mapper，Dto，Entity |
| xxx-share   | 项目服务接口二方库，提供内部Feign服务接口               |
| xxx-web-api     | 项目WebAPI接口，对前端的API接口  |

**依赖关系**

* xxx-app   <   xxx-open-api   <   xxx-service   <   xxx-share
* xxx-app   <   xxx-web-api   <   xxx-service   <   xxx-share

## 技术架构

* SpringBoot 2.6.3
* SpringCloud 2021.0.1
* MybatisPlus 3.5.1

## 详细结构

``` java
xxx-app:
com.pcic
		└── config          // 全局系统配置
		└── handle          // 全局处理类
		└── filter          // 全局过滤器
		└── xxxxx           // 全局其他处理，拦截器等

xxx-service:
com.pcic 
		└── dto             // 业务数据传输对象，继承entity，根据业务扩展
			└── entity      // 此对象与数据表结构一一对应，继承BaseEntity（审计字段）
		└── enums           // 枚举类
		└── exception       // 异常类
		└── feign           // 内部系统无法提供二方库的Feign接口
		    └─ xxx          // 系统包名
		       └─ fallback
               └─ req	
               └─ resp
		└── mapper          // 数据访问层
		└── service         // 业务逻辑层
		└── util            // 业务工具类，可以使用hutool的不重复写
		└── xxxxx           // 其他业务处理包，handle，helper等
resources
        └── mapper          // 数据访问层XML文件
        
xxx-share:
com.pcic.api
		└── feign           // 提供内部系统调用接口
			└── fallback    // 接口降级处理
		└── req             // 接口调用请求对象
		└── resp            // 接口调用返回对象
		└── validator       // 接口对象参数检验分组
	
xxx-open-api:
com.pcic.api			    	
		└── feign           // 提供内部系统调用接口实现类，实现xxx-share的feign接口
		└── xxxxx           // 对外系统接口的统一处理
		XXXController       // 提供对外系统调用接口
	
xxx-web-api:
com.pcic			    	
		└── web             // 对前端的Controller
		└── xxxxx           // 对前端接口的统一处理
```

## 打包方式

在根目录执行Maven打包即可，部署xxx-app模块下的xxx-app.jar包

## 接口文档

* 采用SpringDoc，支持多分组，WebApi及服务Api接口分组
* 接口文档UI组件knife4j，文档导出Markdown，Html等多种格式，PDF可使用Markdown转换
* 访问地址: http://localhost:XXXX/doc.html
