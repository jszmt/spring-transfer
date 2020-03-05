1、学员自定义@Service、@Autowired、@Transactional注解类，完成基于注解的IOC容器（Bean对象创建及依赖注入维护）和声明式事务控制，写到转账工程中，并且可以实现转账成功和转账异常时事务回滚

注意考虑以下情况：

 1）注解有无value属性值【@service（value=""@Repository（value=""）】

 2）service层是否实现接口的情况【jdk还是cglib】

 答：实现通过com.lagou.edu.factory.MyApplicationContext 类进行初始化构建


2、根据源码剖析，记录spring循环依赖处理机制中的调用关系，画出uml时序图【主要方法或者类的作用进行文字注明】

  答：根目录下spring源码循环依赖.png
