Collection
├List		（1）容量可变的列表，（2）可按索引访问集合中的元素（3）有序、可重复
│├LinkedList	（1）数据结构为链表，允许快速随机存取，提供最佳顺序存取，适合插入和移除元素（2）可以像栈或队列一样被使用
│├ArrayList	数据结构为数组，允许快速随机存取
│└Vector
│　└Stack
└Set			（1）元素不可重复，（2）不按特定方式排序（3）对Set中成员的访问和操作是通过Set中对象的引用进行的
│└HashSet 	能够快速定位一个元素,存入HashSet中的对象必须实现HashCode()方法
特点：
 不能保证元素的排列顺序，顺序有可能发生变化
 不是同步的
 集合元素可以是null,但只能放入一个null
│└TreeSet 	将放入其中的元素按序存放
    1、TreeSet 是二差树实现的,Treeset中的数据是自动排好序的，不允许放入null值。
    2、HashSet 是哈希表实现的,HashSet中的数据是无序的，可以放入null，但只能放入一个null，两者中的值都不能重复，就如数据库中唯一约束。
    3、HashSet要求放入的对象必须实现HashCode()方法，放入的对象，是以hashcode码作为标识的，而具有相同内容的 String对象，
        hashcode是一样，所以放入的内容不能重复。但是同一个类的对象可以放入不同的实例 。

Map			key不允许重复
├Hashtable	实现一个映象，所有的键和值 必须非空。为了能高效的工作，定义键的类必须实现hashcode()方法和equal()方法。
    这个类是前面java实现的一个继承，并且通常能在实现映象的其他类中更好的使用
    特点：1 方法是同步的
        2 方法不允许value==null
        3 方法调用了key的hashCode方法，如果key==null,会抛出空指针异常
├HashMap	实现一个键到值映射的哈希表，通过键取得值对象，没有顺序，通过get(key)来获取value，允许存储空对象，
    而且允许键是空(由于键必须是唯一的，当然只能有一个)
    特点：1 方法是非同步的
        2 方法允许key==null
        3 方法并没有对value进行任何调用，所以允许为null
└WeakHashMap


└ConcurrentHashMap
    http://blog.csdn.net/xuefeng0707/article/details/40834595

当元素的顺序很重要时选用TreeMap，当元素不必以特定的顺序进行存储时，使用HashMap。Hashtable的使用不被推荐，因为HashMap提供了所有类似的功能，并且速度更快。当你需要在多线程环境下使用时，HashMap也可以转换为同步的。