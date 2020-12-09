package com.example.firstspringboot.algorithm.linkDemo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :sunwenwu
 * @Date : 2020/12/8 14:24
 * @Description :
 */
public class Solution_01 {
    public int[] twoSum(int[] nums, int target) {

        //[-1,-2,-3,-4,-5]
        int indexA = 0;

        int a = 0;

        for (int i= 0;i<nums.length;i++){
            /*if (nums[i] > target) {
                continue;
            }*/
            a = nums[i];
            indexA = i;

            for (int j = indexA + 1;j<nums.length;j++) {
                if ((target - a) != nums[j]) {
                    continue;
                }
                return new int[]{indexA,j};
            }
        }


        return null;
    }

    public static void main(String[] args) {
        Solution_01 s = new Solution_01();
        int[] ints = {-1,-2,-3,-4,-5};


        int[] result = s.twoSum(ints, -8);

        System.out.println(JSON.toJSONString(result));


        //10 =  00001010
        //20 =  00010100
        //5  =  00000101
        System.out.println(10 << 1);
        System.out.println(10 >> 1);
    }
}
