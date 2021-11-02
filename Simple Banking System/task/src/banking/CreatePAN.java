package banking;

public class CreatePAN implements Command {
    private final DataBase dataBase;
    private final Response response;
    private String pan;
    private String pin;

    private static final String BIN = "400000";

    private static final String CREATED_PAN = "Your card has been created\nYour card number:\n";
    private static final String CREATED_PIN = "Your card PIN:\n";

    public CreatePAN(DataBase dataBase) {
        this.dataBase = dataBase;
        response = new Response();
    }

    private String getN(int n) {
        StringBuilder ret = new StringBuilder();
        while (ret.length() < n){
            Integer rnd = (int) (Math.random() * 9);
            if (rnd != null)
                ret.append(rnd);
            ret = new StringBuilder(ret.toString().trim());
        }
        return ret.toString();
    }

    private void createPAN() {
        String strPan = BIN + getN(9);
        int l = 0;
        while (!Utils.isLuhn(strPan + l)) l++;
        pan =  strPan + l;
        pin = getN(4);
    }

    @Override
    public void execute() {
        createPAN();
        if (dataBase.createAccount(pan, pin)) {
            String resp = CREATED_PAN +
                    pan + "\n" +
                    CREATED_PIN +
                    pin + "\n";
            response.setResponse(resp);
        }
    }

    @Override
    public Response getResult() {
        return response;
    }
}