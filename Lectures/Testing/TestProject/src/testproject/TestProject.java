/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

import java.util.ArrayList;

/**
 *
 * @author rdash
 */
public class TestProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] nums = {1, 0, 0, 0, 0};
        
        for (int i = 2; i < nums.length; i++) {
            nums[i] = nums[i-1] + nums[i-2];
        }
        
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
    
}
