package demo3;  
/** 
 * 上下文，维护一个对策略类对象的引用 
 * 
 */  
public class Context {  
    Strategy strategy;  
  
    public Context(Strategy strategy) {       
        this.strategy = strategy;  
    }  
      
    public void contextInterface(){  
        strategy.AlgorithmInterface();  
    }  
}  
  
  
