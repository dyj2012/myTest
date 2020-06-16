package com.duyj.work.patterns.memento;

//待备份和还原的对象
public class Originator {
	
	private String data;
	
	private String other;

	public String getData() {
		return data;
	}

	public void setData(String state) {
		this.data = state;
	}
	
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	//返回需要存档的数据，封装在VO中
	public Memento getStates(){
		Memento m = new Memento(data);
		return m;
	}
	
	//使用存档数据还原私有变量
	public void setStates(Memento m){
		data = m.getState();
	}
	
	public void show()
	{
		System.out.println(data);
	}
	

}
