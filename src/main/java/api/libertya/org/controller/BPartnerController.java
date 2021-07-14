package api.libertya.org.controller;

import api.libertya.org.common.LoginInfo;
import api.libertya.org.service.BPartnerService;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.openXpertya.model.X_C_BPartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BPartnerController extends GeneralController {

    protected final BPartnerService bPartnerService;

    @Autowired
    public BPartnerController(BPartnerService bPartnerService) {
        this.bPartnerService = bPartnerService;
    }

    @GetMapping(path = "api/v1/bpartner/{bPartnerID}")
    public ResponseEntity<String> bPartnerRetrieveByID(@RequestHeader("userName") String userName,
                                                       @RequestHeader("password") String password,
                                                       @RequestHeader("clientID") int clientID,
                                                       @RequestHeader("orgID") int orgID,
                                                       @PathVariable("bPartnerID") int bPartnerID ) {
        loginInfo = new LoginInfo(userName, password, clientID, orgID);
        return super.command(() -> jsonSerialize(bPartnerService.retrieveByID(bPartnerID)) );
    }

    @DeleteMapping(path = "api/v1/bpartner/{bPartnerID}")
    public ResponseEntity<String> bPartnerDeleteByID(@RequestHeader("userName") String userName,
                                                     @RequestHeader("password") String password,
                                                     @RequestHeader("clientID") int clientID,
                                                     @RequestHeader("orgID") int orgID,
                                                     @PathVariable("bPartnerID") int bPartnerID ) {
        loginInfo = new LoginInfo(userName, password, clientID, orgID);
        return super.command(() -> bPartnerService.deleteByID(bPartnerID) );
    }

    @PutMapping(path = "api/v1/bpartner/{bPartnerID}")
    public ResponseEntity<String> bPartnerUpdateByID(@RequestHeader("userName") String userName,
                                                     @RequestHeader("password") String password,
                                                     @RequestHeader("clientID") int clientID,
                                                     @RequestHeader("orgID") int orgID,
                                                     @PathVariable("bPartnerID") int bPartnerID,
                                                     @RequestBody() String data) {
        loginInfo = new LoginInfo(userName, password, clientID, orgID);
        return super.command(() -> bPartnerService.updateByID(bPartnerID, jsonToMap(data)) );
    }

}
