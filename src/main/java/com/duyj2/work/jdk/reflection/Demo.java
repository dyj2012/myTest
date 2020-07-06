package com.duyj2.work.jdk.reflection;

import com.duyj2.work.utils.Q;

interface Interface {
}

class Father{
	protected String father;
}

public class Demo extends Father implements Interface{
	
	private static String k = "k";
	
	private Integer id;
	
	private String name;
	
	Demo(){}
	
	public Demo(String name){
		this.name=name;
	}
	
	public Demo(int id, String name){
		this.id=id;
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void me() {
		Q.p("[" + id + "----" + name + "]");
	}
	
	public void you(int id, String name){
		Q.p("["+id+"----"+name+"]");
	}
	
	@Override
	public String toString(){
		return "["+id+"----"+name+"]";
	}
	
}
