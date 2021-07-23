package api.libertya.org;

import api.libertya.org.common.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class GeneralTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    /** Credenciales de acceso correctas */
    protected LoginInfo credentialsCorrect = new LoginInfo("System", "System", 0, 0);
    /** Credenciales de acceso erroneas */
    protected LoginInfo credentialsWrong = new LoginInfo("System", "SystemX", 0, 0);

    protected String getBaseURL() {
        return String.format("http://localhost:%d/api/v1", port);
    }

    protected HttpEntity<?> getHeadlessRequest() {
        return new HttpEntity<>(new HttpHeaders());
    }

    protected HttpEntity<?> getAuthRequest(String body) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("credentials", credentialsCorrect.toJson());
        return new HttpEntity<>(body, headers);
    }

    protected HttpEntity<?> getWrongAuthRequest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("credentials", credentialsWrong.toJson());
        return new HttpEntity<>(headers);
    }



}
