package kz.App;

import kz.App.entity.Question;
import kz.App.repository.QuestionRepository;
import kz.App.service.QuestionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Array;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int  i = 0; i <= num; i++) {
            String in = scanner.next();
            String tinkoff = "FFIKNOT";

            if(in.length() == 7) {
                char[] inChar = in.toCharArray();
                Arrays.sort(inChar);
                int count = 0;
                for(int j = 0; j < 7; j++ ){
                    if (tinkoff.charAt(count) == inChar[count]){
                        count++;
                    }
                }
                if (count == 7){
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
            }else {
                System.out.println("no");
            }
        }
        scanner.close();
    }

//    public static void prooved( String in, String tinkoff, int idx){
//        if (idx == 8){
//            System.out.println("Yes");
//            return;
//        }
////        if{in.toCharArray().
////        boolean result = true;
////
////    }
}

