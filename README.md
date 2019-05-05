# safe-container 是什么

* 一系列容器类的集合，容器包含如下特性
  * 容器中不能存放null或者empty （空字符串，空集合，空数组）
  * 容器可以修改
  * 容器不是线程安全的
* 在Map,List,Set的基础上增加了一些便于使用的方法

# 设计思想
1. 对无效数据fast-fail
2. 提供更多常用方法，简化代码

java8 的Optional类从语法层面很好的减少了空指针的问题，但是对于集合类型很难从语法层面去约束其中的内容。故打算通过新增相关类型来解决这个问题。
* NoneNullMap: map中不允许存在null元素
* NoneNullList: list中不允许存在null元素
* NoneNullSet: set中不允许存在null元素
* NoneEmptyMap: map中不允许存在empty元素
* NoneEmptyList: list中不允许存在empty元素
* NoneEmptySet: set中不允许存在empty元素

# 常用工具类
* Maps: 提供map相关的常用方法
* Collections2: 提供Collection相关的常用方法
