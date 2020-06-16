package com.duyj.work.jdk.spi;

/**
 * SPI: Service Provider Interface 服务提供者接口    提供给服务提供厂商或扩展功能的开发者使用的接口。
 * 开发时不关心具体使用哪个服务实现，开发完成之后要切换服务只需要修改配置文件即可
 * <p>
 * 如java.sql.Driver的SPI实现 mysql驱动和oracle驱动、common-logging的日志接口实现
 * <p>
 * SPI机制的约定：
 * 1. 在META-INF/services/目录中创建以接口全限定名命名的文件该文件内容为Api具体实现类的全限定名
 * 2. 使用ServiceLoader类动态加载META-INF中的实现类
 * 3. 如SPI的实现类为Jar则需要放在主程序classPath中
 * 4. Api具体实现类必须有一个不带参数的构造方法
 * <p>
 * 配置文件路径 PREFIX = "META-INF/services/"， 命名为接口类全名，文件内容为实现类全名
 */