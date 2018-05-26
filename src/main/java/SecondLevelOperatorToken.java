public class SecondLevelOperatorToken extends BaseOperatorToken{

    public final static int IMPORTANCE = 2;

    public SecondLevelOperatorToken(String v) {
        super(v);
    }

    @Override
    public int getImportance(){
        return IMPORTANCE;
    }
}
