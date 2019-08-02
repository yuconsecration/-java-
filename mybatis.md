## 1.原生态jdbc编程中的问题总结

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

2. 在MyBatis_01_HelloWorld工程中新建一个名为lib的包，在该包中导入log4j-1.2.17.jar,mybatis-3.4.1.jar,mysql-connector-java-5.1.37-bin.jar三个jar包（这三个包均在之前下载的文件中），其中log4j-1.2.17.jar包的作用为方便在控制台查看mybatis打印的数据库的相关文件，并且要想使用该包必须配置一个名为log4j.xml的文件（注意这里的名称为硬性要求），在MyBatis_01_HelloWorld中创建一个包名为conf，在该包中创建log4j.xml，创建方式为：
