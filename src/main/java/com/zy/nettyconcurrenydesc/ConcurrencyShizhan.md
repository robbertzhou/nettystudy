一、对共享的可变数据进行正确的同步：  
关键字sychronized可以保证同一时刻，只有一个线程执行某一个方法或代码块。  
同步的作用不仅仅是互斥，另一个作用是修改了数据并释放锁后其他线程可以获取最新的值。 
private final Map<ChannelOption<?>, Object> options = new LinkedHashMap<ChannelOption<?>, Object>();   
/**  
     * Allow to specify a {@link ChannelOption} which is used for the {@link Channel} instances once they got  
     * created. Use a value of {@code null} to remove a previous set {@link ChannelOption}.  
     */
    @SuppressWarnings("unchecked")  
    public <T> B option(ChannelOption<T> option, T value) {  
       &#8195; if (option == null) {  
            &#8195;&#8195;throw new NullPointerException("option");  
       &#8195; }  
       &#8195; if (value == null) {  
         &#8195; &#8195;  synchronized (options) {  
            &#8195;&#8195; &#8195;   options.remove(option);  
         &#8195; &#8195;  }  
       &#8195; } else {  
         &#8195; &#8195;  synchronized (options) {  
          &#8195;&#8195;&#8195;      options.put(option, value);  
         &#8195;&#8195;&#8195; &#8195;  }  
        &#8195;&#8195;&#8195;}  
     &#8195; &#8195;  return (B) this;  
   &#8195; }  
 提供的最后一个批注是 @SuppressWarnings。该批注的作用是给编译器一条指令，告诉它对被批注的代码元素内部的某些警告保持静默。 
 
 二、正确使用锁  
 对锁的范围、加锁的时机和锁的协同要有足够的认识。  
 学习ForkJoinTask 
 
 三、volatile正确使用  
 关键字volatile是Java提供的最轻量级的同步机制，java内存模型对volatile专门定义了一些特殊的访问规则。  
 1、线程可见性：也就是当一个线程修改了volatile修饰的变量，无论是否加锁，其他线程是立刻获取该变量的最新值。  
 当一个线程向被volatile关键字修饰的变量写入数据的时候，虚拟机会强制它被值刷新到主内存中。  
 2、禁止指令重排序优化  
 指令重排序是编译器和处理器为了高效对程序进行优化的手段，它只能保证程序执行的结果时正确的，但是无法保证程序的操作顺序与代码顺序一致。这在单线程中不会构成问题，但是在多线程中就会出现问题。非常经典的例子是在单例方法中同时对字段加入voliate，就是为了防止指令重排序。
 