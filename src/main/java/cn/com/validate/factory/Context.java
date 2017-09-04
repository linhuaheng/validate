package cn.com.validate.factory;

import cn.com.validate.service.Strategy;

 

 

public class Context {

 

    private Strategy strategy;

   

    public boolean calRecharge(String type, String val) {

       strategy = StrategyFactory.getInstance().creator(type);
        if(strategy == null){
            return false;
        }
       return strategy.calRecharge(val);

    }

 

    public Strategy getStrategy() {

       return strategy;

    }

 

    public void setStrategy(Strategy strategy) {

       this.strategy = strategy;

    }

   

} 