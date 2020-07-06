package com.duyj2.work.jdk.object;

import java.io.*;

public class SerializeBean implements Externalizable{
	private int i;
	private String s;
	private String ss;
	
	//从运行结果可见反序列化需要调用该默认构造器，如果为private则会抛出InvalidClassException
	public SerializeBean(){
		System.out.println("SerializeBean default constractor.");
	}
	
	public SerializeBean(int i, String s, String ss){
		this.i=i;
		this.s=s;
		this.ss=ss;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		SerializeBean bean1 = new SerializeBean(99, "iamstring", "ss");
		SerializeBean bean2 = new SerializeBean(44, "string too", "ss");

		//序列化到文件
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.out"));
        oos.writeObject(bean1);
		oos.writeObject(bean2);
		oos.close();
		bean1 = null;
		bean2 = null;

		//从文件反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.out"));
        bean1 = (SerializeBean) ois.readObject();
		bean2 = (SerializeBean) ois.readObject();
		System.out.println(bean1);
		System.out.println(bean2);
	}
	
	@Override
	public String toString(){
		return "["+i+"-"+s+"-"+ss+"]";
	}

	//序列化过程中自动调用
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("writeExternal here");
		out.writeObject(s);		//此处添加需要序列化的属性
		out.writeInt(i);
	}

	//反序列化过程中自动调用
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("readExternal here");
		s=(String)in.readObject();		//此处添加自定义处理逻辑
		i=in.readInt();
	}

}
