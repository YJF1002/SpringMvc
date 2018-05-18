## springmvc和mybatis整合开发项目
  
  - 学习注册映射器、适配器和视图解析器
  
  - 整合mybatis 学会其配置文件（springmvc.xml;sqlMapConfig.xml;applicationContext.xml）的配置；
  
  - 项目运行时遇到--springmvc整合mybatis项目中跳转方法时候出现Error creating bean with name 'itemController':错误
      
      最终找到原因：是因为service实现类中没有配置@Service注解并且没有在springmvc.xml或者applicationContext.xml开启
		  相应的注解扫描；
      
  - 学习参数的简单绑定、pojo类型、pojo包装类型和自定义类型的绑定；
  
  - 了解了springmvc和struts2的区别
      
      1、springmvc的入口是一个servlet即前端控制器，而struts2入口是一个filter过滤器。
      
      2、springmvc是基于方法开发(一个url对应一个方法)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)，
      struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。
     
      3、Struts采用值栈存储请求和响应的数据，通过OGNL存取数据， 
      springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成ModelAndView对象，
      最后又将ModelAndView中的模型数据通过request域传输到页面。Jsp视图解析器默认使用jstl。
  
  
  
