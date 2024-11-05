package com.manikanta.microservices.project.ProductService.Controller;

public class HelloWorld {
    public static void main(String...args){
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        int small=99999,big=0,result=0;
        for(int i=0;i<prices.length;i++){
            if(prices[i]< small && i != prices.length-1){
                small = prices[i];
            }
            else if(small != 99999 && prices[i] > big && prices[i] > small || big-result < prices[i]-small){
                big=prices[i];
                result=small;
            }
        }
        if(big == 0) System.out.println(0);
        System.out.println(big-result);
    }
}
