package banking;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private static final String ERROR_PAN_PIN = "Wrong card number or PIN!";
    private static final String CREATED_PAN = "Your card has been created\nYour card number:\n";
    private static final String CREATED_PIN = "Your card PIN:\n";
    private static final String SUCCESS_LOGIN = "You have successfully logged in!";
    private static final String SUCCESS_LOGOUT = "You have successfully logged out!";

    private static final String BIN = "400000";

    private static final String fileDB = "db.data";
    private static final String SP = File.separator;
    private static final String dbFilePath =
            System.getProperty("user.dir") + SP +
//            "File Server" + SP + "task" + SP +
                    "src" + SP + "server" + SP + "data";

    private final Map<Long, Integer> db;
    private transient Response out;

    public DataBase() {
        db = new HashMap<>();
    }

    private long pan;
    private int pin;


    public String getDbFilePath() {
        return dbFilePath + SP + fileDB;
    }

    private void initTran() {
        out = new Response();
    }

    public Response getOut() {
        return out;
    }

    private String getN(int n) {
        String ret = "";
        while (ret.length() != n){
            ret += (int)(Math.random()*9);
            ret = ret.trim();
        }
        return ret;
    }

    public void createPAN() {
        initTran();
        pan = Long.parseLong(BIN + getN(9) + getN(1));
        pin = Integer.parseInt(getN(4));

        db.put(pan, pin);
        StringBuilder resp = new StringBuilder();
        resp.append(CREATED_PAN);
        resp.append(pan + "\n");
        resp.append(CREATED_PIN);
        resp.append(pin + "\n");
        out.setResponse(resp.toString());
    }

    public void login(Long pan, Integer pin) {
        initTran();

        if (db.containsKey(pan) && db.get(pan).equals(pin)) {
            out.setResponse(SUCCESS_LOGIN);
            out.setNoError(true);
        } else {
            out.setResponse(ERROR_PAN_PIN);
            out.setNoError(false);
        }
    }

    public void logout() {
        initTran();
        pan = 0;
        pin = 0;
        out.setResponse(SUCCESS_LOGOUT);
        out.setNoError(true);
    }


    public void balance() {
        initTran();
        out.setResponse("0");
    }
}