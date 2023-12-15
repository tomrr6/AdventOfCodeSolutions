import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day3pt2 {

    public static void main(String[] args) throws IOException{

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day 3/pt1Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day 3/input.txt"));

        int sum = 0;

        
        Map<Integer, List<Integer>> prevGears = new HashMap<>(); //column, list of factors
        ArrayDeque<Integer> prevRowNumbers= new ArrayDeque<>();
        ArrayDeque<int[]> prevRowNumberLocations= new ArrayDeque<>();
        ArrayDeque<Integer> prevRowSymbolLocations= new ArrayDeque<>();
        
        String line;
        Map<Integer, List<Integer>> gears  = new HashMap<>(); //column, list of factors
        while((line = br.readLine()) != null){

            gears = new HashMap<>(); //column, list of factors
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
                            
                            gears.get(rowSymbolLocations.peekLast()).add(rowNumbers.removeLast());
                            rowNumberLocations.removeLast();
                        }
                        else{
                            //check for symbol in row above
                            int rightBound = Math.min(row.length, rowNumberLocations.peekLast()[1]+1);
                            while(!prevRowSymbolLocations.isEmpty() && prevRowSymbolLocations.peekFirst() <= rightBound){
                                if(prevRowSymbolLocations.peekFirst() >= leftBound){
                                    System.out.println(rowNumbers.peekFirst() + " symbol in row above");
                                    prevGears.get(prevRowSymbolLocations.peekFirst()).add(rowNumbers.removeLast());
                                    rowNumberLocations.removeLast();
                                    break;
                                }
                                prevRowSymbolLocations.removeFirst();
                            }
                        }


                    } 
                }

                if(i == row.length) continue;

                if(Character.isDigit(row[i])){

                    if(thisRange[0] == -1){
                        thisRange[0] = i;
                    }
                    continue;
                }

                if(row[i] != '*') continue;

                //row[i] is a symbol
                rowSymbolLocations.addLast(i);
                gears.put(i, new LinkedList<>());
                System.out.println("added gear: " + i);
                //if row[i-1] was a number, add it to sum
                if(!rowNumberLocations.isEmpty() && rowNumberLocations.peekLast()[1] == Math.max(0, i-1)){
                    System.out.println(rowNumbers.peekLast() + " symbol in front");
                    gears.get(i).add(rowNumbers.removeLast());
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
                    gears.get(i).add(prevRowNumbers.removeFirst());
                    prevRowNumberLocations.removeFirst();
                }
            }

            if(prevGears != null){
                for(List<Integer> factors : prevGears.values()){
                    if(factors.size() != 2) continue;
                    System.out.println(factors.get(0) + " " + factors.get(1));
                    sum += (factors.get(0) * factors.get(1));
                }
            }

            prevRowNumbers = rowNumbers;
            prevRowNumberLocations = rowNumberLocations;
            prevRowSymbolLocations = rowSymbolLocations;
            prevGears = gears;
        }

        br.close();

        if(gears != null){
            for(List<Integer> factors : gears.values()){
                if(factors.size() != 2) continue;
                System.out.println(factors.get(0) + " " + factors.get(1));
                sum += (factors.get(0) * factors.get(1));
            }
        }

        System.out.println(sum);
    }
}