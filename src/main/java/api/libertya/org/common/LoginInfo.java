package api.libertya.org.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginInfo {

    protected String userName;
    protected String password;
    protected int clientID;
    protected int orgID;

    public static LoginInfo create(String credentials) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        LoginInfo instance = mapper.readValue(credentials, LoginInfo.class);
        return instance;
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

