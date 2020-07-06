package com.duyj2.work.patterns.command;

public class ConcreteCommand implements Command {
	
	private Receiver receiver = null;
    private String state;

    public ConcreteCommand(Receiver receiver){
       this.receiver = receiver;
    }  


	@Override
	public void excute() {
		 receiver.action();
	}


}
