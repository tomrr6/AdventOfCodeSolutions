// package 2023.Day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class day1pt2 {

    private static final String[] NUMBER_WORDS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public static void main(String[] args) throws IOException{

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day1/pt2Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day1/pt1Input.txt"));
        int output = 0;
    
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);

            int firstDigit = -1;
            int secondDigit = -1;

            int indexOfFirstNumber = Integer.MAX_VALUE;
            int indexOfFinalNumber = 0;
            for(int i = 0; i < NUMBER_WORDS.length; i++){
                // System.out.println(NUMBER_WORDS[i]);

                int firstIndexOfWord = line.indexOf(NUMBER_WORDS[i]);
                int firstIndexOfDigit = line.indexOf('1'+i);

                if(firstIndexOfWord == -1) firstIndexOfWord = Integer.MAX_VALUE;
                if(firstIndexOfDigit == -1) firstIndexOfDigit = Integer.MAX_VALUE;
                int firstIndex = Math.min(firstIndexOfWord, firstIndexOfDigit);

                if(firstIndex < indexOfFirstNumber){
                    // System.out.println(NUMBER_WORDS[i] + " at " + firstIndex);
                    indexOfFirstNumber = firstIndex;
                    if(firstDigit == -1) secondDigit = i+1;
                    firstDigit = i+1;
                }

                int finalIndex = Math.max((line.lastIndexOf(NUMBER_WORDS[i])), (line.lastIndexOf('1'+i))); //check for word and digit
                if(finalIndex > indexOfFinalNumber){
                    // System.out.println("final " + NUMBER_WORDS[i] + " at " + finalIndex);
                    indexOfFinalNumber = finalIndex;
                    secondDigit = i+1;
                }

            }

            
            System.out.println(firstDigit + " " + secondDigit);
            
            output += (firstDigit*10) + secondDigit;
        }

        br.close();

        System.out.println(output);

    }
}
