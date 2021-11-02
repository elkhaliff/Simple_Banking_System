package banking;

public class Main {
    public static void main(String[] args) {
        String fileName = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-fileName"))
                fileName = args[i+1];
        }
        Session session = new Session(fileName);
        session.run();
    }
}