import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day3pt1 {

    public static void main(String[] args) throws IOException{

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day 3/pt1Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day 3/input.txt"));

        int sum = 0;

        ArrayDeque<Integer> prevRowNumbers= new ArrayDeque<>();
        ArrayDeque<int[]> prevRowNumberLocations= new ArrayDeque<>();
        ArrayDeque<Integer> prevRowSymbolLocations= new ArrayDeque<>();
        
        String line;
        while((line = br.readLine()) != null){

            ArrayDeque<Integer> rowNumbers= new ArrayDeque<>();
            ArrayDeque<int[]> rowNumberLocations= new ArrayDeque<>();
            int[] thisRange = {-1, -1};
            ArrayDeque<Integer> rowSymbolLocations= new ArrayDeque<>();

            char[] row = line.toCharArray();

            for(int i = 0; i <= row.length; i++){
                if(i == row.length || !Character.isDigit(row[i])){

                    if(thisRange[0] != -1){ //number range just ended
                        thisRange[1] = i-1;
                        rowNumbers.addLast(Integer.parseInt(line.substring(thisRange[0], thisRange[1]+1)));
                        rowNumberLocations.addLast(new int[]{thisRange[0], thisRange[1]});
                        thisRange[0] = -1;
                        thisRange[1] = -1;

                        //see if this number touches a symbol on row above or before it on current row
                        int leftBound = Math.max(0, rowNumberLocations.peekLast()[0]-1);
                        if(!rowSymbolLocations.isEmpty() && leftBound == rowSymbolLocations.peekLast()){
                            //there was a symbol on current row right before this number
                            System.out.println(rowNumbers.peekLast() + " symbol in back");
                            sum += rowNumbers.removeLast();
                            rowNumberLocations.removeLast();
                        }
                        else{
                            //check for symbol in row above
                            int rightBound = Math.min(row.length, rowNumberLocations.peekLast()[1]+1);
                            while(!prevRowSymbolLocations.isEmpty() && prevRowSymbolLocations.peekFirst() <= rightBound){
                                if(prevRowSymbolLocations.peekFirst() >= leftBound){
                                    System.out.println(rowNumbers.peekLast() + " symbol in row above");
                                    sum += rowNumbers.removeLast();
                                    rowNumberLocations.removeLast();
                                    break;
                                }
                                prevRowSymbolLocations.removeFirst();
                            }
                        }


                    } 
                }

                if(i == row.length || row[i] == '.') continue;

                if(Character.isDigit(row[i])){

                    if(thisRange[0] == -1){
                        thisRange[0] = i;
                    }
                    continue;
                }

                //row[i] is a symbol
                rowSymbolLocations.addLast(i);
                // System.out.println("added symbol: " + row[i]);
                //if row[i-1] was a number, add it to sum
                if(!rowNumberLocations.isEmpty() && rowNumberLocations.peekLast()[1] == Math.max(0, i-1)){
                    System.out.println(rowNumbers.peekLast() + " symbol in front");
                    sum += rowNumbers.removeLast();
                    rowNumberLocations.removeLast();
                }
                //check for numbers in row above
                while(!prevRowNumberLocations.isEmpty() && i > prevRowNumberLocations.peekFirst()[1]+1){
                    //this symbol is beyond bounds of leftmost number in prev row, so evict it.
                    System.out.println("skipping " + prevRowNumbers.peekFirst());
                    prevRowNumbers.removeFirst();
                    prevRowNumberLocations.removeFirst();
                }
                while(!prevRowNumberLocations.isEmpty() && 
                    i >= prevRowNumberLocations.peekFirst()[0]-1 && i <= prevRowNumberLocations.peekFirst()[1]+1){
                    //this symbol touches a number in the prev row
                    System.out.println(prevRowNumbers.peekFirst() + " symbol in row below");
                    sum += prevRowNumbers.removeFirst();
                    prevRowNumberLocations.removeFirst();
                }
            }

            prevRowNumbers = rowNumbers;
            prevRowNumberLocations = rowNumberLocations;
            prevRowSymbolLocations = rowSymbolLocations;

        }

        br.close();

        System.out.println(sum);
    }
}