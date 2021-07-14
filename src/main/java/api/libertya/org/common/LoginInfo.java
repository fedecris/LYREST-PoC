package api.libertya.org.common;

public class LoginInfo {

    protected String userName;
    protected String password;
    protected int clientID;
    protected int orgID;

    public LoginInfo(String userName, String password, int clientID, int orgID) {
        this.userName = userName;
        this.password = password;
        this.clientID = clientID;
        this.orgID = orgID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getOrgID() {
        return orgID;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }

}

