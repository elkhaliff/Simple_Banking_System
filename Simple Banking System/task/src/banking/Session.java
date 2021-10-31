package banking;

public class Session {

    private static final int LVL_1 = 10;
    private static final int NEW_PAN = 11;
    private static final int LOGIN = 12;
    private static final int LVL_2 = 20;
    private static final int BALANCE = 21;
    private static final int LOGOUT = 22;
    private static final int EXIT1 = 10;
    private static final int EXIT2 = 20;
    private static final String MENU_LVL1 = "1. Create an account\n" +
                                            "2. Log into account\n" +
                                            "0. Exit";

    private static final String MENU_LVL2 = "1. Balance\n" +
                                            "2. Log out\n" +
                                            "0. Exit";

    private static final String GET_PAN = "Enter your card number:";
    private static final String GET_PIN = "Enter your PIN:";

    private final DataBase dataBase;
    long pan;
    int pin;

    public Session() {
        dataBase = new DataBase();
    }

    private void getPanPin() {
        pan = Utils.getLong(GET_PAN);
        pin = Utils.getInt(GET_PIN);
    }

    public void run() {
        TransactionBroker transactionBroker = new TransactionBroker();
        Command command;

        int currAction;
        int currLvl = LVL_1;

        do {
            currAction = currLvl + Utils.getInt((currLvl == LVL_1) ? MENU_LVL1 : MENU_LVL2);

            switch (currAction) {
                case NEW_PAN: {
                    command = new CreatePAN(dataBase);
                    break;
                }
                case LOGIN: {
                    getPanPin();
                    command = new Login(dataBase, pan, pin);
                    break;
                }
                case BALANCE: {
                    command = new Balance(dataBase);
                    break;
                }
                case LOGOUT: {
                    command = new Logout(dataBase);
                    break;
                }
                case EXIT1:
                case EXIT2: {
                    System.exit(0);
                }
                default: {
                    throw new IllegalStateException("Unexpected action: " + currAction);
                }
            }

            transactionBroker.setCommand(command);
            transactionBroker.executeCommand();

            Response response = transactionBroker.getResultCommand();
            String resp = response.getResponse();
            if (resp != "")
                Utils.println(response.getResponse());

            if (currAction == LOGIN && response.isNoError()) currLvl = LVL_2;
            if (currAction == LOGOUT && response.isNoError()) currLvl = LVL_1;
        } while (currAction != EXIT1 || currAction != EXIT2);
    }

}