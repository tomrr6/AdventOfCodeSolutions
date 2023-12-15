// package 2023.Day 4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class day4pt1 {
    public static void main(String[] args) throws IOException{

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day 4/pt1Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day 4/input.txt"));
        
        int sum = 0;
        String line;
        while((line = br.readLine()) != null){
            String[] parts = line.split("[:|]");

            Set<Integer> winningNums = new HashSet<>();
            for(String winningNum : parts[1].trim().split("\s+")){
                winningNums.add(Integer.parseInt(winningNum));
            }
            
            int myMatchingNumCount = 0;
            for(String myNum : parts[2].trim().split("\s+")){
                // System.out.println(myNum);
                if(winningNums.contains(Integer.parseInt(myNum))) myMatchingNumCount++;
            }

            if(myMatchingNumCount == 0) continue;
            sum += Math.pow(2, myMatchingNumCount-1);
        }

        System.out.println(sum);
        
    }

}
