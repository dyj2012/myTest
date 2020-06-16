package com.duyj.work.patterns.memento;

//备忘录管理类 维护保持的备份
public class CareTaker {
	
	private Memento meme;
	
	public Memento getMeme() {
		return meme;
	}

	public void setMeme(Memento meme) {
		this.meme = meme;
	}	

}
