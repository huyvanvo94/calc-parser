package parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PostFixConverter {
    private Queue<Token> tokens;
    private Stack<Token> stack = new Stack<>();

    private Queue<Token> convertedTokens = new LinkedList<>();
    public PostFixConverter(Queue<Token> t){
        tokens = t;
    }

    public Queue<Token> convert(){

        while (!tokens.isEmpty()){
            Token t = tokens.remove();
            if(t instanceof BaseOperatorToken){
                handleOperator((BaseOperatorToken) t);
            }else if(t instanceof ParameterToken){

                if(t.getValue().equals("(")){
                    stack.push(t);
                }

                else if(t.getValue().equals(")")){
                    handleParamemter((ParameterToken) t);
                }
            }

            else{
                convertedTokens.add(t);
            }
        }

        while (!stack.isEmpty()){
            convertedTokens.add(stack.pop());
        }
        return convertedTokens;
    }

    private void handleOperator(BaseOperatorToken token){

        while (!stack.isEmpty()){
            Token opTop = stack.pop();
            if(opTop.getValue().equals("("))
            {
                stack.push(opTop);
            }
            else
            {

                if( ((BaseOperatorToken) opTop).getImportance() < token.getImportance() ){
                    stack.push(opTop);
                    break;
                }else{
                    convertedTokens.add(opTop);
                }

            }
        }

        stack.push(token);
    }

    private void handleParamemter(ParameterToken token)
    {
        while (!stack.isEmpty()){
            Token chx = stack.pop();
            if(chx.getValue().equals("("))
            {
                break;
            }else{
                convertedTokens.add(chx);
            }
        }
    }
}
