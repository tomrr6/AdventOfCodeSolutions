// package 2023.Day 4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class day4pt2 {
    public static void main(String[] args) throws IOException{

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day 4/pt1Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day 4/input.txt"));
        
        int sum = 0;
        int cardID = 1;
        String line;
        Map<Integer, Integer> cardCount = new HashMap<>(); //card ID, how much of it I have
        while((line = br.readLine()) != null){
            String[] parts = line.split("[:|]");

            cardCount.compute(cardID, (k,v) -> (v==null) ? 1 : v+1);

            Set<Integer> winningNums = new HashSet<>();
            for(String winningNum : parts[2].trim().split("\s+")){
                winningNums.add(Integer.parseInt(winningNum));
            }
            int myMatchingNumCount = 0;
            for(String myNum : parts[1].trim().split("\s+")){
                if(winningNums.contains(Integer.parseInt(myNum))) myMatchingNumCount++;
            }

            System.out.println("won "+ myMatchingNumCount + " new cards");
            int multiplier = cardCount.get(cardID);
            for(int i = 1; i <= myMatchingNumCount; i++){
                System.out.println("won "+ multiplier +" copies of: " + (cardID+i));
                cardCount.compute(cardID+i, (k,v) -> (v==null) ? multiplier : v+multiplier);
            }
            cardID++;
        }

        br.close();
        for(int v : cardCount.values()){
            sum += v;
        }
        System.out.println(sum);
        
    }

}
