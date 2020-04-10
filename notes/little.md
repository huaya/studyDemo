1. addShutdownHook 在jvm执行关闭前才会执行
~~~~
Thread shutdownThread = new Thread();
Runtime.getRuntime().addShutdownHook(shutdownThread);
~~~~
2. deleteOnExit 在线程结束时执行
~~~~
file.deleteOnExit()
~~~~