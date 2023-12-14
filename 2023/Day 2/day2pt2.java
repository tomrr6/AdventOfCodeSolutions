import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day2pt2 {

    public static void main(String[] args) throws IOException{

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day 2/pt1Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day 2/input.txt"));

        int sumOfPowers = 0;

        String game;
        while ((game = br.readLine()) != null) {
            sumOfPowers += IDifGameIsValid(game);
        }

        br.close();

        // return sumOfValidIDs;
        System.out.println(sumOfPowers);
    }

    //returns ID of given game if it is valid, 0 otherwise
    private static int IDifGameIsValid(String game){
        Map<String, Integer> minPossibleCubesAtOnce = new HashMap<>();

        String[] rounds = game.split("[;:]");

        for(int i = 1; i < rounds.length; i++){
            String[] hands = rounds[i].split(",");

            for(String hand : hands){
                String[] countAndColor = hand.split("\s");
                minPossibleCubesAtOnce.compute(countAndColor[2], (k, v) -> (v==null) ? Integer.parseInt(countAndColor[1]) : Math.max(Integer.parseInt(countAndColor[1]), v));
            }
        }

        int output = 1;
        for(int minCubes : minPossibleCubesAtOnce.values()){
            output *= minCubes;
        }

        return output;
    }
}