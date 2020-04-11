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