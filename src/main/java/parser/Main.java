package parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        CalcParser calcParser = new CalcParser("1+-1");

        System.out.println(calcParser.getTokenList());
        System.out.println(calcParser.solve());



        //start();

    }

    static void start(){
        CalcParser calcParser = new CalcParser();

        Scanner in = new Scanner(System.in);

        while (true){
            System.out.print("input: ");


            try{

                String expression = in.next();

                if(expression.equals("exit")){
                    System.exit(0);
                }

                calcParser.parse(expression);

                System.out.println(calcParser.solve());
            }catch (Exception e){
                System.out.println("exit");
                System.exit(0);
            }
        }
    }
}
