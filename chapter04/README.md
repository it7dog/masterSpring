1、java 反射

<<<<<<< HEAD
类的装载器就是寻找类的字节码文件并构造出类在JVM内部标识对象的组件。
在java中,类装载器把一个类装入JVM中，需要经过一下步骤：
（1）、装载：查找和导入class文件。
 (2)、链接：执行校验、准备和解析步骤，其中解析步骤是可以选择的：
   校验：检查载入的class文件数据是否正确；
   准备：给类的静态变量分配存库空间；
   解析：将符号引用转换成直接引用；
 （3）、初始化：对类的静态变量、静态代码块执行初始化操作。
 类装载工作由ClassLoader及其子类负责。ClassLoader是一个重要的Java运行时系统
 组件，它负责在运行时查找和装入Class字节码文件。JVM在运行时会产生3个ClassLoader:
 根装载器、ExtClassLoader(扩展类装载器)和AppClassLoader(应用类装载器)。
 ClassLoader的重要方法：
 Class loadClass(String name):name参数指定类装载器需要装载类的名字，必须使用
 全限定类名，如：com.smart.beans.car;
 Class defineClass(String name,byte[] b,int off,int len)将类文件的字节
 数组转换成JVM内部的java.lang.Class文件。
 Class findSystemClass(String name):从本地文件系统载入Class文件。
 Class findLoadedClass(String name):调用该方法来查看ClassLoader是否已装入
 某个类；
 ClassLoader getParent(): 获取类装载器的父装载器。
=======
类的装载器就是寻找类的字节码文件并构造出类在JVM内部标识对象的组件。在java中,类装载器把一个类装入JVM中，需要经过一下步骤：<br/>
(1)装载：查找和导入class文件。<br/>
(2)链接：执行校验、准备和解析步骤，其中解析步骤是可以选择的：<br/>
    校验：检查载入的class文件数据是否正确；<br/>
    准备：给类的静态变量分配存库空间；<br/>
    解析：将符号引用转换成直接引用；<br/>
(3)初始化：对类的静态变量、静态代码块执行初始化操作。<br/>

类装载工作由ClassLoader及其子类负责。ClassLoader是一个重要的Java运行时系统 组件，它负责在运行时查找和装入Class字节码文件。JVM在运行时会产生3个ClassLoader:根装载器、ExtClassLoader(扩展类装载器)和AppClassLoader(应用类装载器)。<br/> 

 ClassLoader的重要方法：<br/>
 Class loadClass(String name):name参数指定类装载器需要装载类的名字，必须使用全限定类名，如：com.smart.beans.car;<br/>
 Class defineClass(String name,byte[] b,int off,int len)将类文件的字节数组转换成JVM内部的java.lang.Class文件。<br/>
 Class findSystemClass(String name):从本地文件系统载入Class文件。<br/>
 Class findLoadedClass(String name):调用该方法来查看ClassLoader是否已装入某个类；<br/>
 ClassLoader getParent(): 获取类装载器的父装载器。<br/>
>>>>>>> 991c76d3276f65b01fa9d9434266331e62eb6f3c
