package com.duyj.work.javassist;

import javassist.*;

/**
 * Created by LG on 2017/12/3.
 */
public class JavassistTest {

    public static void main(String[] args) throws Exception {
        OldClass old = changeMethod("com.duyj.work.javassist.OldClass", "add");
        System.out.println("New result: " + old.add());
    }

    private static OldClass changeMethod(String classpath, String methodName) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        // 用于取得字节码类，必须在当前的classpath中，使用全称
        CtClass ctClass = ClassPool.getDefault().get(classpath);

        // 需要修改的方法名称
        CtMethod mold = ctClass.getDeclaredMethod(methodName);

        // 修改原有的方法名称
        mold.setName(methodName + "$impl");

        // 创建新的方法，复制原来的方法
        CtMethod mnew = CtNewMethod.copy(mold, methodName, ctClass, null);

        // 主要的注入代码
        StringBuffer body = new StringBuffer();
        body.append("{long start = System.currentTimeMillis();");
        // 调用原有代码，类似于method(); ($$)表示所有的参数
        body.append(mold.getName() + "($$);");
        body.append("System.out.println(\"Cost: \"+(System.currentTimeMillis()-start)+\"ms\");");
        body.append("System.out.println(\"Change return value to 123\");");
        body.append("return 123;");
        body.append("}");
        System.out.println("new Method: " + body.toString());

        // 替换新方法
        mnew.setBody(body.toString());
        // 增加新方法
        ctClass.addMethod(mnew);

        // 类已经更改，注意不能使用A a=new A()或Old.class.getName()，因为在同一个classloader中，不允许装载同一个类两次
        return (OldClass) ctClass.toClass().newInstance();
    }

}
