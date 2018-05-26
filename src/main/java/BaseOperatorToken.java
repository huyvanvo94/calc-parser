public class BaseOperatorToken extends Token{

    public static final int IMPORTANCE = 1;

    public BaseOperatorToken(String v) {
        super(v);
    }

    public int getImportance(){
        return IMPORTANCE;
    }
}
