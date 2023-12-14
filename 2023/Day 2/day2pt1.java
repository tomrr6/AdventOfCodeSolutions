import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day2pt1 {

    public static void main(String[] args) throws IOException{

        Map<String, Integer> possibleCubesAtOnce = new HashMap<>();
        possibleCubesAtOnce.put("red", 12);
        possibleCubesAtOnce.put("green", 13);
        possibleCubesAtOnce.put("blue", 14);

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day 2/pt1Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day 2/input.txt"));

        int sumOfValidIDs = 0;

        String game;
        while ((game = br.readLine()) != null) {
            sumOfValidIDs += IDifGameIsValid(game, possibleCubesAtOnce);
        }

        br.close();

        // return sumOfValidIDs;
        System.out.println(sumOfValidIDs);
    }

    //returns ID of given game if it is valid, 0 otherwise
    private static int IDifGameIsValid(String game,  Map<String, Integer> possibleCubesAtOnce){
        String[] rounds = game.split("[;:]");

        for(int i = 1; i < rounds.length; i++){
            String[] hands = rounds[i].split(",");

            for(String hand : hands){
                String[] countAndColor = hand.split("\s");
                if(possibleCubesAtOnce.get(countAndColor[2]) < Integer.parseInt(countAndColor[1])){
                    // System.out.println(hand);
                    return 0;
                }
            }
        }

        //everything is valid
        String[] header = rounds[0].split("\s");
        return Integer.parseInt(header[1]);
    }
}