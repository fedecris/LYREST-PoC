package api.libertya.org;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest extends GeneralTest {

    int orderID = 20477397;

    @Test
    protected void postOrderCompleteShouldReturnCO() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/order/" + orderID + "/complete" , HttpMethod.POST, getAuthRequest("{ \"AD_Client_ID\":1010016, \"AD_Org_ID\":1010053, \"Name\":\"Test\", \"Value\":\"TEST\", \"C_BP_Group_ID\":1010043 }"), String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
        assertThat(response.getBody()).contains("CO");
    }

    @Test
    protected void postOrderCloseShouldReturnCL() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/order/" + orderID + "/close" , HttpMethod.POST, getAuthRequest("{ \"AD_Client_ID\":1010016, \"AD_Org_ID\":1010053, \"Name\":\"Test\", \"Value\":\"TEST\", \"C_BP_Group_ID\":1010043 }"), String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
        assertThat(response.getBody()).contains("CL");
    }

    @Test
    protected void postOrderVoidShouldReturnVO() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/order/" + orderID + "/void" , HttpMethod.POST, getAuthRequest("{ \"AD_Client_ID\":1010016, \"AD_Org_ID\":1010053, \"Name\":\"Test\", \"Value\":\"TEST\", \"C_BP_Group_ID\":1010043 }"), String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
        assertThat(response.getBody()).contains("VO");
    }
}
