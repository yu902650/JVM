#第七章 虚拟机的类加载机制 228页

###类的生命周期
加载  验证  准备  解析  初始化 使用  卸载

###Java类初始化要满足条件
>1.遇到new,getstatic,putstatic,invokestatic这4条字节码
new: 使用new关键字去实例化对象的时候
getstatic: 读取或者设置一个类的静态字段,
putstatic: **(被final修饰,已在编译期把结果放入常量池的静态字段除外)**
invokestatic: 调用一个类的静态方法

>2.use java.lang.reflect包的方法对类进行反射调用的时候,如果类没有进行初始化,则要先初始化

>3.当初始化一个类的时候,这个类的父类还没有初始化,要先触发其父类的初始化

>4.当虚拟机启动时,用户需要指定一个要执行的类(包含main方法的类),虚拟机会先初始化这个主类

>5.jdk1.7的动态语言支持的时候,如果一个java.lang.invoke.MethodHandle实例最后的
解析结果CONSTANT_Class_info、CONSTANT_Fieldref_info、CONSTANT_Methodref_info的句柄.
并且这个句柄对应的类没有进行初始化,则需要先触发其初始化

###Java虚拟机类加载的过程
加载  验证  准备  解析  初始化

####加载
加载阶段需要完成下面三件事:
1.通过一个类的全限定名来获取定义此类的二进制字节流
2.通过字节流所代表的静态存储结构转化为方法区的运行时数据结构
3.内存中生成一个代表这个类的java.lang.Class对象,作为方法区这个类的各种数据的访问入口

####验证(安全性)  
1.文件格式验证
2.元数据验证
3.字节码验证
4.符合引用验证

>-Xverify:none 关闭大部分的类验证,缩短虚拟机的类加载时间

####准备
准备阶段是正式为类变量分配内存并设置变量初始值的阶段.这些变量所使用的内存都在方法区中进行分配.
这些变量是指被static修饰的变量,不包括实例变量,实例变量是在初始化的时候放区java堆中

基础数据类型的变量
  int    0       |  boolean false  | long    0L     |   float     0.0f   | byte  (byte)0
short (short 0)  |  double   0.0d  | char '\u0000'  | reference   null  

####解析
解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程
符号引用和直接引用
-------------------------------------------------------
1.类或者接口的解析
2.字段解析
3.类方法解析
4.接口方法解析


####初始化
如果一个类中没有静态语句块,也就没有变量的赋值操作,那么编译器可以不为这个类生成<clinit>()方法
接口中不能使用静态语句块,但仍然有变量初始化的赋值操作,因此接口与类一样都会生成<clinit>()方法.
虚拟机会保证一个类的<clinit>()方法在多线程中被正确的加锁,同步

####类加载器
通过一个类的全限定名来获取二进制的字节流,这个动作放到Java虚拟机外部去实现,以便让程序自己决定去获取
所需要的类,实现这个动作的代码模块,成为类加载器

####类与类加载器:
类加载器虽然只实现类的加载动作,但他在java程序中起到的作用却远不止类加载阶段.
比较两个类是否相等,只有在这两个类是由同一个类加载器加载的前提下才有意义.
否则,即使这两个类源于同一Class文件,被同一虚拟机加载,只要加载他们的类加载器不同,那么这两个类必定不相等.

problem
句柄
全限定名:有绝对路径的意思

------------------------------------------------------------------------------
####双亲委派模型
Java虚拟机角度来讲,只存在两种不同的加载器:
一种是启动类加载器(BootStarp ClassLoader),此类加载器使用C++实现.是虚拟机的一部分
另一种就是所有其他的类加载器,这些类加载器都由Java语言实现,独立于虚拟机外部,并且全都继承自抽象类java.lang.ClassLoader

双亲委派模型的工作过程是:如果一个类加载器收到了类的加载请求,首先不会自己去尝试加载这个类,而是把这个请求委派给自己的父类加载器去完成,
每一个层次的类都是如此,因此所有的请求最终都应传送到顶层的启动类加载器中,只有父类加载器反馈自己无法完成这个加载请求,子加载器才会去尝试
自己加载

好处:
Java类随着它的类加载器一起具备了**一种带有优先级的层次关系**. 例如:类java.lang.Object,它存放在rt.jar中,无论哪个
类加载器都要加载这个类,最终都会委派给处于模型最顶端的启动类加载器来进行加载,因此Object类在程序的各种类加载器环境中
都是用一个类. 相反,如果没有使用双亲委派模型,各个类加载器自行去加载的话,如果用户编写一个称为java.lang.Object的类,
并放在程序的ClassPath中,那系统会多出多个不同的Object类,Java类型体系中最基础的行为也就无法保证,应用程序也将会变
的一片混乱;

代码实现双亲委派模型:

```
      public Class<?> loadClass(String name) throws ClassNotFoundException {
          return loadClass(name, false);
      }
      //              -----👇👇-----
      protected Class<?> loadClass(String name, boolean resolve)
          throws ClassNotFoundException
      {
              // First, check if the class has already been loaded
              Class<?> c = findLoadedClass(name);
              if (c == null) {
                  try {
                      if (parent != null) {
                          c = parent.loadClass(name, false);
                      } else {
                          c = findBootstrapClassOrNull(name);
                      }
                  } catch (ClassNotFoundException e) {
                      // ClassNotFoundException thrown if class not found
                      // from the non-null parent class loader
                  }
   
                  if (c == null) {
                      // If still not found, then invoke findClass in order
                      // to find the class.
                      c = findClass(name);
                  }
              }
              return c;
      }

```

####破坏双亲委派模型
上面提到双亲委派不是强制性的约束模型,而是推荐的类加载器的实现方式
双亲委派模型的缺点:回调基础类又要调用回用户的代码






























