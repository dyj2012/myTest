package com.duyj2.work.jdk.system;

import com.duyj2.work.jdk.reflection.ReflectTool;
import com.duyj2.work.utils.Q;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * ClassLoadingMXBean
 * 用于Java虚拟机的类加载系统的管理接口。
 * <p>
 * CompilationMXBean
 * 用于Java虚拟机的编译系统的管理接口。
 * <p>
 * GarbageCollectorMXBean
 * 用于Java虚拟机的垃圾回收的管理接口。
 * <p>
 * MemoryManagerMXBean
 * 内存管理器的管理接口。
 * <p>
 * MemoryMXBean
 * Java虚拟机内存系统的管理接口。
 * <p>
 * MemoryPoolMXBean
 * 内存池的管理接口。
 * <p>
 * OperatingSystemMXBean
 * 用于操作系统的管理接口，Java虚拟机在此操作系统上运行。
 * <p>
 * RuntimeMXBean
 * Java虚拟机的运行时系统的管理接口。
 * <p>
 * ThreadMXBean
 * Java虚拟机线程系统的管理接口。
 */
public class ManagementTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        String name = mxbean.getName();
        // get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

//        ReflectTool.listMethod(mxbean);
//        ReflectTool.printGet(mxbean);

//        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
//        ReflectTool.all(classLoadingMXBean);

        List<GarbageCollectorMXBean> garbageCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean g : garbageCollectorMXBean) {
            Q.p(g.getName());
        }
        ReflectTool.all(garbageCollectorMXBean.get(0));



    }


}
