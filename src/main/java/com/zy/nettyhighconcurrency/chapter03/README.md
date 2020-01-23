一、NIO Buffer类属性
1、capacity：容量，可以容最大的数据量，在缓冲区创建时指定。  
2、limit：上限，缓冲区的当前数据量大小。  
3、position：位置，缓冲区中下一个要被读或写的元素索引。  
4、mark：调用mark方法来设置mark=position，再调用reset可以让position恢复到mark标记的位置即position=mark.  

二、NIO buffer的方法  
1、flip():反转  
2、reset和mark


三、Channel  