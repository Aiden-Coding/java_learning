package com.itmayiedu.sms;

public class ObserverA implements Observer {
	private int myState;//需要和目标对象的state值保持一致.

	@Override
	public void update(Subjecct subjecct) {
		myState=((RealObserver)subjecct).getState();
	}

	public int getMyState() {
		return myState;
	}

	public void setMyState(int myState) {
		this.myState = myState;
	}

	
}
