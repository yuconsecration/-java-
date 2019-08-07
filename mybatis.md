123## 1.原生态jdbc编程中的问题总结

### 1.1jdbc程序
#### 1.1.1jdbc是什么？

>JDBC英文名为：Java Data Base Connectivity(Java数据库连接)，官方解释它是Java编程语言和广泛的数据库之间独立于数据库的连接标准的Java API（applacation programming inferce应用程序编程接口），根本上说JDBC是一种规范，它提供的接口，一套完整的，允许便捷式访问底层数据库。可以用JAVA来写不同类型的可执行文件：JAVA应用程序、JAVA Applets、Java Servlet、JSP等，不同的可执行文件都能通过JDBC访问数据库，又兼备存储的优势。简单说它就是JAVA与数据库的连接的桥梁或者插件，用JAVA代码就能操作数据库的增删改查、存储过程、事务等。

#### 1.1.2jdbc的缺点

1. SQL代码夹在Java代码中，耦合度高导致代码硬编码内伤。
2. 维护不易且实际开发需求过程中sql的需求时不断变化的，频繁修改的情况多变。

## 2.MyBatis的相关说明

### 2.1为什么要学习MyBatis

1. 对开发人员而言，核心sql还是需要自己优化。
2. sql和java编码分开，功能边界清晰，一个专注业务，一个专注数据。

### 2.2什么是MyBatis

1. mybatis是一个优秀的Java持久层的框架，它内部封装了JDBC，使开发者只需要关注sql语句本身，而不需要花费精力去处理加载驱动、创建连接、创建statement等繁杂过程。
2. mybatis是通过xml或者注解的方式将要执行的各种statement配置起来，并通过java对象和statement中sql的动态参数进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射为java对象返回。
### 2.3相关文件下载

#### 2.3.1MyBaits下载地址

> https://github.com/mybatis/mybatis-3/releases

> 说明：
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801222649342.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)

#### 2.3.2java与mysql连接jar包下载

> 下载地址：链接：https://pan.baidu.com/s/1hZEdWNa6qN1wBFMAnpTuKQ 
提取码：jhb7 

#### 2.3.3Apache日志文件log4j下载

> 下载地址：链接：https://pan.baidu.com/s/1RwmL_CNy_AGIEBBGW37TRQ 
提取码：v229 

## 3.MyBatis_HelloWorld

### 3.1mysql数据库的相关操作
1. 打开navicat数据库，新建一个数据库名为mybatis，在该数据库中新建一个表名为tbl_employee，表中相关设置如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190802211944184.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)
2. 打开表tbl_employee，设置一组初始值，例如：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190802212231846.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)

### 3.2eclipse相关操作

1. 代开eclipse新建一个Java工程名为MyBatis_01_HelloWorld，在该工程下创建一个名为Employee的类，并将其放在包com.dt.mybatis.bean下，代码如下：

```
package com.dt.mybatis.bean;

public class Employee {
	private Integer id;
	private String lastName;//这里设置一个不同于数据库中的名字，用于在后面的数据库查询中修改属性名
	private String email;
	private String gender;//为这四个变量添加get,set以及toString函数
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + "]";
	}
	
}
```

2. 在MyBatis_01_HelloWorld工程中新建一个名为lib的包，在该包中导入log4j-1.2.17.jar,mybatis-3.4.1.jar,mysql-connector-java-5.1.37-bin.jar三个jar包（这三个包均在之前下载的文件中），其中log4j-1.2.17.jar包的作用为方便在控制台查看mybatis打印的数据库的相关文件，并且要想使用该包必须配置一个名为log4j.xml的文件（注意这里的名称为硬性要求），导入这三个包后还不能直接使用需要进行相关的配置：具体操作如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019080318072870.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190803181119138.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)

在MyBatis_01_HelloWorld中创建一个包名为conf，在该包中创建log4j.xml，创建方式为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190803175755594.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190803180037876.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)
> 打开log4j.xml并在其中添加如下代码：

```
<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/' >

   <!--对于ConsoleAppender来说，在开发阶段还是比较有用的，能直接在ide中看到输出的日志内容，
   但是在实际的产品阶段，我们更希望将日志输出到指定的文件上查看，这就需要用到FileAppender-->
   <appender name="CONSOLE" class="org.apache.log4j.ConsoleAppender">
      <param name="encoding" value="UTF-8" />
      <layout class="org.apache.log4j.PatternLayout">
         <param name="ConversionPattern" value="%d %-5p %t %l %m%n" />
      </layout>
      <!--如果有过滤器，当前appender只会处理满足过滤器条件的日志信息-->
      <!--<filter class="org.apache.log4j.varia.LevelRangeFilter">
         <param name="levelMin" value="DEBUG" />
         <param name="levelMax" value="FATAL" />
         <param name="AcceptOnMatch" value="true" />
      </filter>-->
   </appender>

   <!-- 写到远端日志 -->
   <appender name="DEFAULT-APPENDER-REMOTE" class="org.apache.log4j.net.SyslogAppender">
      <param name="SyslogHost" value="${logServer}"/> 
       <param name="Facility" value="LOCAL1"/>
          <param name="FacilityPrinting" value="true"/>
       <layout class="org.apache.log4j.PatternLayout">
           <param name="ConversionPattern" value="%d %-5p %t %c %m%n" />
       </layout> 
   </appender>
   
   <appender name="FRAMEWORK-APPENDER-REMOTE" class="org.apache.log4j.net.SyslogAppender">
      <param name="SyslogHost" value="${logServer}"/> 
       <param name="Facility" value="LOCAL3"/>
          <param name="FacilityPrinting" value="true"/>
       <layout class="org.apache.log4j.PatternLayout">
           <param name="ConversionPattern" value="%d %-5p %t %c %m%n" />
       </layout> 
   </appender>
   <!-- 写到本地日志 -->
   <!--第一步：配置appender-->
   <appender name="INFO-LOCAL" class="org.apache.log4j.DailyRollingFileAppender">
      <param name="file" value="/opt/logs/info.log" />
      <param name="append" value="true" /><!--一般必须配置true，否则会覆盖而不是累加-->
      <param name="encoding" value="UTF-8" />
      <layout class="org.apache.log4j.PatternLayout">
         <param name="ConversionPattern" value="%d %-5p %t %l %m%n" />
      </layout>
      <!--如果有过滤器，日志文件中就只会有符合过滤器的日志信息-->
      <filter class="org.apache.log4j.varia.LevelRangeFilter">
         <param name="levelMin" value="DEBUG" />
         <param name="levelMax" value="INFO" />
         <param name="AcceptOnMatch" value="true" />
      </filter>
   </appender>
   <appender name="ERROR-LOCAL" class="org.apache.log4j.DailyRollingFileAppender">
      <param name="file" value="/opt/logs/error.log" />
      <param name="append" value="true" /><!--一般必须配置true，否则会覆盖而不是累加-->
      <param name="encoding" value="UTF-8" />
      <layout class="org.apache.log4j.PatternLayout">
         <param name="ConversionPattern" value="%d %-5p %t %l %m%n" />
      </layout>
      <!--如果有过滤器，日志文件中就只会有符合过滤器的日志信息-->
      <filter class="org.apache.log4j.varia.LevelRangeFilter">
         <param name="levelMin" value="WARN" />
         <param name="levelMax" value="FATAL" />
         <param name="AcceptOnMatch" value="true" />
      </filter>
   </appender>

   <!--第二步：配置logger。
   如果不配置，系统会采用默认策略，即additivity="true"，level value="DEBUG"。
   注意：additivity=true，表示遵循缺省的继承机制，此时就不应该继续配置appender-ref，否则日志会出现重复
   相反，如果additivity=false，就必须要配置appender-ref，否则日志不会被打印，配置也就没有意义-->
   <logger name="com.test.log4j" additivity="true">
      <!--给具体的logger配置日志级别，比如com.test包下面日志级别大于等于DEBUG的，
      才会交给appender进行处理。
      注意：对于com.test包而言，此处的级别会覆盖root下面的日志级别-->
      <level value="INFO" />
      <!--如果此处配置了append-ref,而且additivity="true"，将会和root里面的同一个
      append-ref形成累加，即重复日志-->
      <!--<appender-ref ref="CONSOLE" />-->
      <!--<appender-ref ref="DEFAULT-LOCAL"/>-->
      <!--<appender-ref ref="ERROR-LOCAL"/>-->
   </logger>
   <logger name="com.test2" additivity="false">
      <level value="INFO" />
      <appender-ref ref="INFO-LOCAL" />
   </logger>

   <!--第三步：配置root-->
   <!--root的作用是收集所有配置的logger反馈上来的信息流并根据配置在root中appender进行输出，
   只要你在looger中配置了additivity="false"，就不会反馈到root中。-->
   <root>
      <!--优先级小于具体的logger中配置的级别，只有当具体的logger没有配置的时候，
      这里才会对具体的looger起作用-->
      <level value="DEBUG" />
      <appender-ref ref="CONSOLE" />
      <appender-ref ref="INFO-LOCAL" />
      <appender-ref ref="ERROR-LOCAL"/>
   </root>
</log4j:configuration>  
```

> 说明：xml是Extensible Markup Language的简称，为扩展性标识语言，文件的后缀名为：.xml。就像HTML的作用是显示数据，XML的作用是传输和存储数据，为了便于不同应用、不同平台之间的数据共享和通信。

3. 在conf文件夹下新建一个.xml文件，命名为：mybatis-config.xml，在其中输入如下内容：

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
 PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
 "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
 <environments default="development">
 <environment id="development">
 <transactionManager type="JDBC" />
 <dataSource type="POOLED">
 <property name="driver" value="com.mysql.jdbc.Driver"/>
 <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
 <property name="username" value="root" />
 <property name="password" value="1333" />
 </dataSource>
 </environment>
 </environments>
 <mappers>
 <mapper resource="EmployeeMapper.xml"/>
 </mappers>
</configuration>
```

说明：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190803182217492.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190803182338509.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)
> 注意：

4. 创建一个mybatis的测试类，重命名为MyBatisTest.java，并将其放在包com.dt.mybatis.test下

> 向其中导入如下代码：

```
@Test
public void test() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
```
> 然后对于该部分代码按住ctrl+shift+o键，然后选择java.io.inputStream，接着选择org.apache.ibatis.io.Resources，对于有异常的部分进行抛出异常，相关截图如下：
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190803183903695.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lUX1NvZnRFbmdpbmVlcg==,size_16,color_FFFFFF,t_70)
> 	说明：如何向java中插入已经下载的mybatis源码程序，用来查看相关函数的作用是什么： 将鼠标停靠在需要查看的函数上，然后点击open declaration，然后点击attach source，选择external location，点击external file选择下载好的mybatis源码包，点击OK即可。

