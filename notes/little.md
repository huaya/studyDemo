1. addShutdownHook 在jvm执行关闭前才会执行
    ~~~~
    Thread shutdownThread = new Thread();
    Runtime.getRuntime().addShutdownHook(shutdownThread);
    ~~~~
2. deleteOnExit 在线程结束时执行
    ~~~~
    file.deleteOnExit()
    ~~~~
3. 递归容易引起栈溢出，尾递归能一定程度优化

4. Function Predicate Consumer Supplier区别

     **_Function_**
        Function<T, R> => R apply(T t);
        接受一个输入参数，返回一个结果
    
    **_Predicate_**
        Predicate<T> => boolean test(T t);
        接受一个输入参数，返回布尔值
        
    **_Consumer_**
        Consumer<T> => void accept(T t);
        接受一个输入参数，无返回值
    
    **_Supplier_**
        Supplier<T> => T get();
        无输入参数，返回一个结果
5. RDBMS(关系型数据库管理系统) 和 MapReduce的比较

| |传统关系型数据库|MapReduce|
|:----    |:--------: |:-----:|
|数据大小|GB|PB|
|访问|交互式和批处理|批处理|
|更新|多次写入和读取|一次写入，多次读取|
|结构|静态模式|动态模式|
|完整性|高|低|
|横向扩展|非线性|线性|

6、伪共享原因
 - CPU缓存是以缓存行为单位，一个缓存行可能包含多个变量；
 - 同时只能有一个线程访问缓存行
 - 多个线程访问缓存行多个变量时，导致性能下降   
 测试类 FalseShareBenchmark

7、双亲委派模式
  - 工作流程：
      1. 当前classload首先从自身类加载缓存中查询是否存在，如已存在直接返回该类，并放入自身加载缓存；
      2. 如果不存在，委托父加载类去加载，父加载类采取相同策略，直至bootstrp ClassLoader；
      3. 当所有父类都没找到时，则有自身加载，并放入缓存；
  - 好处
      1. 防止类重复加载;
      2. 安全原因，防止同名类替代java api类；

8、常见负载均衡策略: 轮询、随机、权重、hash（ip或URL）、最少连接   
   