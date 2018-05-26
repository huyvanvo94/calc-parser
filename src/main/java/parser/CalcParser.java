package parser;

import java.util.*;

public class CalcParser {

    private Queue<Token> tokenList;

    public CalcParser(Queue<Token> t){
        tokenList = t;
    }

    public void debug(){
        System.out.println(tokenList);
    }

    public CalcParser( ){
        tokenList = new LinkedList<>();
    }

    public CalcParser(String expression){

        parse(expression);
    }

    public Queue<Token> getTokenList() {
        return tokenList;
    }

    public void parse(String expression){
        tokenList = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for(char c: expression.toCharArray()){

            if(!Character.isDigit(c)){

                if (stringBuilder.length() != 0) {

                    add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }

                add(String.valueOf(c));


            }else{
                stringBuilder.append(String.valueOf(c));
            }
        }

        if(stringBuilder.length() != 0){
            add(stringBuilder.toString());
        }
    }

    public void add(String value){
        if(value.equals("*") || value.equals("/")){

            tokenList.add(new SecondLevelOperatorToken(value));
            return;
        }

        if(value.equals("+") || value.equals("-")){
            tokenList.add(new BaseOperatorToken(value));

            return;
        }

        if(value.equals(")") || value.equals("(")){
            tokenList.add(new ParameterToken(value));
            return;
        }

        tokenList.add(new NumberToken(value));


    }

    public double solve(){
        return solve(convert());
    }

    public double solve(Queue<Token> q){

        Stack<Token> stack = new Stack<>();

        while (!q.isEmpty()){
            Token t = q.remove();

            if(t instanceof NumberToken) {
                handle(stack, (NumberToken) t);
                continue;
            }

            if(t instanceof SecondLevelOperatorToken){
                handle(stack, (SecondLevelOperatorToken) t);
                continue;
            }

            if(t instanceof BaseOperatorToken){
                handle(stack, (BaseOperatorToken) t);
                continue;
            }

        }
        return Double.valueOf(stack.pop().getValue());
    }

    private void handle(Stack<Token> s, NumberToken token){
       s.push(token);
    }

    private void handle(Stack<Token> s, BaseOperatorToken token){

        double a = Double.valueOf(s.pop().getValue());
        double b = Double.valueOf(s.pop().getValue());
        if(token.getValue().equals("+")){
            s.push(new NumberToken(String.valueOf(a+b)));
            return;
        }

        if(token.getValue().equals("-")){
            s.push(new NumberToken(String.valueOf(a-b)));
        }
    }

    private void handle(Stack<Token> s, SecondLevelOperatorToken token){
        double a = Double.valueOf(s.pop().getValue());
        double b = Double.valueOf(s.pop().getValue());

        if(token.getValue().equals("*")){
            s.push(new NumberToken(String.valueOf(a*b)));
            return;
        }

        if(token.getValue().equals("/")){
            s.push(new NumberToken(String.valueOf(a/b)));

        }

    }

    public Queue<Token> convert(){
        return new PostFixConverter(tokenList).convert();
    }

}