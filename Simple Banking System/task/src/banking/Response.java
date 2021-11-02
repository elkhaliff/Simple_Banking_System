package banking;

public class Response {
    private String response;
    private int id;
    private boolean noError;

    public Response() {
        response = "";
        noError = true;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() { return response; }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public void setNoError(boolean noError) { this.noError = noError; }

    public boolean isNoError() { return noError; }
}