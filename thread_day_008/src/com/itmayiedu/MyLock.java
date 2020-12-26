package com.itmayiedu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {
	private final Sync sync;

	public MyLock() {
		sync = new Sync();
	}

	static class Sync extends AbstractQueuedLongSynchronizer {
		protected boolean isHeldExclusively() {
			return Thread.currentThread() == getExclusiveOwnerThread();
		}

		@Override
		protected boolean tryAcquire(long arg) {
			Thread currentThread = Thread.currentThread();
			long state = getState();
			if (state == 0) {
				// »ñÈ¡Ëø
				if (hasQueuedPredecessors()) {
					return false;
				}
				boolean flag = compareAndSetState(1, 4);
				System.out.println(flag);
				setExclusiveOwnerThread(currentThread);
				return true;
			}
			if (isHeldExclusively()) {
				setState(state + arg);
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(long arg) {
			long newState = getState() - arg;
			setState(newState < 0 ? 0 : newState);
			if (newState == 0) {
				setExclusiveOwnerThread(null);
				return true;
			}
			return false;
		}
	}

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {

		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {

		return false;
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {

		return null;
	}

}
