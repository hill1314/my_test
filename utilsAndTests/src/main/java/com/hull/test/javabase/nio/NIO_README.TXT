http://www.iteye.com/magazines/132-Java-NIO

（一）Java NIO提供了与标准IO不同的IO工作方式：

Channels and Buffers（通道和缓冲区）：
    标准的IO基于字节流和字符流进行操作的，而NIO是基于通道（Channel）和缓冲区（Buffer）进行操作，
    数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。
Asynchronous IO（异步IO）：
    Java NIO可以让你异步的使用IO，例如：当线程从通道读取数据到缓冲区时，线程还是可以进行其他事情。
    当数据被写入到缓冲区时，线程可以继续处理它。从缓冲区写入通道也类似。
Selectors（选择器）：
    Java NIO引入了选择器的概念，选择器用于监听多个通道的事件（比如：连接打开，数据到达）。
    因此，单个的线程可以监听多个数据通道。

(二)Java NIO 由以下几个核心部分组成：
    Channels
    Buffers
    Selectors
虽然Java NIO 中除此之外还有很多类和组件，但在我看来，Channel，Buffer 和 Selector 构成了核心的API。
其它组件，如Pipe和FileLock，只不过是与三个核心组件共同使用的工具类。

（1）Channel 和 Buffer
基本上，所有的 IO 在NIO 中都从一个Channel 开始。Channel 有点象流。
数据可以从Channel读到Buffer中，也可以从Buffer 写到Channel中。

Channel和Buffer有好几种类型。下面是JAVA NIO中的一些主要Channel的实现：
FileChannel
DatagramChannel
SocketChannel
ServerSocketChannel
正如你所看到的，这些通道涵盖了UDP 和 TCP 网络IO，以及文件IO。 （关于UDP和TCP:http://www.cnblogs.com/bizhu/archive/2012/05/12/2497493.html）

与这些类一起的有一些有趣的接口，但为简单起见，我尽量在概述中不提到它们。本教程其它章节与它们相关的地方我会进行解释。

以下是Java NIO里关键的Buffer实现：
ByteBuffer
CharBuffer
DoubleBuffer
FloatBuffer
IntBuffer
LongBuffer
ShortBuffer

这些Buffer覆盖了你能通过IO发送的基本数据类型：byte, short, int, long, float, double 和 char。

Java NIO 还有个 Mappedyteuffer，用于表示内存映射文件， 我也不打算在概述中说明。

（2）Selector

Selector允许单线程处理多个 Channel。如果你的应用打开了多个连接（通道），但每个连接的流量都很低，使用Selector就会很方便。
例如，在一个聊天服务器中。

要使用Selector，得向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。
一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新连接进来，数据接收等。

(三)Java NIO和IO的主要区别
（1）面向流与面向缓冲
Java NIO和IO之间第一个最大的区别是，IO是面向流的，NIO是面向缓冲区的。
Java IO面向流意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。此外，它不能前后移动流中的数据。
如果需要前后移动从流中读取的数据，需要先将它缓存到一个缓冲区。 Java NIO的缓冲导向方法略有不同。数据读取到一个它稍后处理的缓冲区，
需要时可在缓冲区中前后移动。这就增加了处理过程中的灵活性。但是，还需要检查是否该缓冲区中包含所有您需要处理的数据。
而且，需确保当更多的数据读入缓冲区时，不要覆盖缓冲区里尚未处理的数据。

（2）阻塞与非阻塞IO
   Java IO的各种流是阻塞的。这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。
   该线程在此期间不能再干任何事情了。 Java NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，
   如果目前没有数据可用时，就什么都不会获取。而不是保持线程阻塞，所以直至数据变的可以读取之前，该线程可以继续做其他的事情。
   非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。
   线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道（channel）。

（3）选择器（Selectors）
   Java NIO的选择器允许一个单独的线程来监视多个输入通道，你可以注册多个通道使用一个选择器，然后使用一个单独的线程来“选择”通道：
   这些通道里已经有可以处理的输入，或者选择已准备写入的通道。这种选择机制，使得一个单独的线程很容易来管理多个通道。


（四）API调用
   当然，使用NIO的API调用时看起来与使用IO时有所不同，但这并不意外，因为并不是仅从一个InputStream逐字节读取，而是数据必须先读入缓冲区再处理。

NIO的实现会有所不同，下面是一个简单的例子：

ByteBuffer buffer = ByteBuffer.allocate(48);
int bytesRead = inChannel.read(buffer);

NIO可让您只使用一个（或几个）单线程管理多个通道（网络连接或文件），但付出的代价是解析数据可能会比从一个阻塞流中读取数据更复杂。

如果需要管理同时打开的成千上万个连接，这些连接每次只是发送少量的数据，例如聊天服务器，实现NIO的服务器可能是一个优势。
同样，如果你需要维持许多打开的连接到其他计算机上，如P2P网络中，使用一个单独的线程来管理你所有出站连接，可能是一个优势。

如果你有少量的连接使用非常高的带宽，一次发送大量的数据，也许典型的IO服务器实现可能非常契合。

选择：NIO : 很多个连接，每次发生少量的数据 （一个线程处理多个连接）
      IO: 很少的连接，一次发送大量的数据 （一个线程处理一个连接）

（五）通道（Channel）
Java NIO的通道类似流，但又有些不同：
    既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
    通道可以异步地读写。
    通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。

（1）Channel的实现
这些是Java NIO中最重要的通道的实现：
FileChannel：从文件中读写数据。
DatagramChannel：能通过UDP读写网络中的数据。
SocketChannel：能通过TCP读写网络中的数据。
ServerSocketChannel：可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
基本的 Channel 示例