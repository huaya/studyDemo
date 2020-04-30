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