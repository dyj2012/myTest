package com.duyj.work.patterns.memento;

//用于备忘的数据的封装
//备忘录对象：封装另外一个对象内部状态的对象
public class Memento {
	
	private String state;
	
	public Memento(String state){
		this.setState(state);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
