package com.uu.utils;

/**
 * 功能描述: <br>
 *
 * @since: 1.0.0
 * @Author:Created By Clarence
 * @Date: 2020/4/21 18:41
 */
public class RandomUtil {

    //静态内部类是最好的单例模式
    private static class Singleton{
        private static RandomUtil randomUtil = new RandomUtil();
    }

    public static RandomUtil getInstance(){
        return Singleton.randomUtil;
    }

    /**
     * 生成随机数字
     * @param size 位数
     * @return
     */
    public int getRandNum(int size) {

        int temp = 1;
        for (int i = 1; i < size; i++) {
            temp = temp * 10;
        }
        return (int)((Math.random()*9+1)*temp);
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getRandNum(4));
    }
}
