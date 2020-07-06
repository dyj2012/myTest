package com.duyj2.work.jdk.object;

import java.io.*;

/**
 * 	Serializable 为序列化标记接口，不包括任何方法
 */
public class Serialize1{

	//同样需要实现Serializable
	private static class Data implements Serializable {
		private static final long serialVersionUID = 3502676600783498362L;
		private String name;
		public Data(String name){this.name=name;}
		public String toString(){return "d:"+name;}
	}


	private static class User implements Serializable {
		//序列化标识，用于标记类是否发生变化
		private static final long serialVersionUID = -6950896768312602572L;
		private transient String id;		//transient关键字可以阻止该字段被序列化
		private String name;
		private int age;
		private Data data;		//包含另一个对象
		public static String kind="human";		//static 字段不可序列化
		public User(String id, String name, int age){
			this.id=id;
			this.name=name;
			this.age=age;
			this.data=new Data(name);
		}
		@Override
		public String toString(){
			return "["+id+"-"+name+"-"+age+"-"+kind+"-"+data+"]";
		}
	}



	//序列化会暴露对象所有信息(包括private)，所以要对敏感信息加密后再序列化
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		serialize();		//序列化
		deSerialize();		//反序列化
	}

	public static void serialize() throws IOException {
		User bean1 = new User("1", "lg", 123);
		User bean2 = new User("2", "ss", 23);
		//ObjectOutputStream为对象输出流，输出到.users文件中，名称后缀随意
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(".users")));
		oo.writeObject(bean1);		//将对象序列化到输出流中
		oo.writeObject(bean2);		//写入另一个
		oo.close();
	}

	//如果远程jvm中没有User.class，将抛出ClassNotFoundException
	public static void deSerialize() throws IOException, ClassNotFoundException {
		//使用ObjectInputStream对象输入流读取文件，反序列化对象
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(".users")));
		User bean1 = (User) ois.readObject();		//通过二进制构造对象，并不是通过构造函数
		User bean2 = (User) ois.readObject();		//读取下一个
		ois.close();
		System.out.println(bean1);
		System.out.println(bean2);
	}

}
