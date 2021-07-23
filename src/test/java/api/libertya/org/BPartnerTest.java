package api.libertya.org;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BPartnerTest extends GeneralTest {

    final int bPartnerID = 1012145;

    @Test
    public void getBPartnerHeadlessShouldReturnBadRequest()  {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/bpartner/" + bPartnerID, HttpMethod.GET, getHeadlessRequest(), String.class);
        assertThat(response.getStatusCode().toString()).contains("400");
    }

    @Test
    public void getBPartnerWithWrongCredentialsShouldReturnForbidden() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/bpartner/" + bPartnerID, HttpMethod.GET, getWrongAuthRequest(), String.class);
        assertThat(response.getStatusCode().toString()).contains("403");
    }

    @Test
    public void getBPartnershouldReturnOK() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/bpartner/" + bPartnerID, HttpMethod.GET, getAuthRequest(null), String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
    }

    @Test
    public void deleteBPartnerWithReferencesShouldReturnBadRequest() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/bpartner/" + bPartnerID, HttpMethod.DELETE, getAuthRequest(null), String.class);
        assertThat(response.getStatusCode().toString()).contains("400");
    }

    @Test
    public void putBPartnerChangeNameShouldReturnOKAndID() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/bpartner/" + bPartnerID, HttpMethod.PUT, getAuthRequest("{ \"Name\" : \"FooBar\" }"), String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
        assertThat(Integer.parseInt(Objects.requireNonNull(response.getBody()))).isEqualTo(bPartnerID);
    }


}
