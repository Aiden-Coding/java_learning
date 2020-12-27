package com.itmayiedu;

//策略模式 定义抽象方法 所有支持公共接口
abstract class Strategy {

	// 算法方法
	abstract void algorithmInterface();

}

class StrategyA extends Strategy {

	@Override
	void algorithmInterface() {
		System.out.println("算法A");

	}

}

class StrategyB extends Strategy {

	@Override
	void algorithmInterface() {
		System.out.println("算法B");

	}

}

class StrategyC extends Strategy {

	@Override
	void algorithmInterface() {
		System.out.println("算法C");

	}

}
// 使用上下文维护算法策略

class Context {

	Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public void algorithmInterface() {
		strategy.algorithmInterface();
	}

}

class ClientTestStrategy {
	public static void main(String[] args) {
		Context context;
		context = new Context(new StrategyA());
		context.algorithmInterface();
		context = new Context(new StrategyB());
		context.algorithmInterface();
		context = new Context(new StrategyC());
		context.algorithmInterface();

	}
}
