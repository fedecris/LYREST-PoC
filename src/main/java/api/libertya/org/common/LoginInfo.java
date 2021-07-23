package api.libertya.org.common;

import api.libertya.org.util.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginInfo {

    protected String userName;
    protected String password;
    protected int clientID;
    protected int orgID;

    public LoginInfo() {

    }

    public static LoginInfo create(String credentials) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(credentials, LoginInfo.class);
    }

    public LoginInfo(String userName, String password, int clientID, int orgID) {
        setUserName(userName);
        setPassword(password);
        setClientID(clientID);
        setOrgID(orgID);
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

    public String toJson() throws Exception {
        return JSON.serialize(this).replace("\n", "");
    }
}

