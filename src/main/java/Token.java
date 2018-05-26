public abstract class Token {

    private String value;

    public Token(String v){
        value = v;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return value;
    }
}