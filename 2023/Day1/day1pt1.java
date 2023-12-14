// package 2023.Day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day1pt1 {

    public static void main(String[] args) throws IOException{

        // BufferedReader br = new BufferedReader(new FileReader("2023/Day1/pt1Sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("2023/Day1/pt1Input.txt"));
        int output = 0;
    
        String line;
        while((line = br.readLine()) != null){
            int firstDigit = -1;
            int secondDigit = -1;

            for(char c : line.toCharArray()){
                if(Character.isDigit(c)){
                    if(firstDigit == -1) firstDigit = c - '0';
                    secondDigit = c - '0';
                }
            }
            
            output += (firstDigit*10) + secondDigit;
        }

        br.close();

        System.out.println(output);

    }
}
