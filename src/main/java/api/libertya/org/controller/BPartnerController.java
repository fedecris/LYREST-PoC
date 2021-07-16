package api.libertya.org.controller;

import api.libertya.org.service.BPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BPartnerController extends GeneralController {

    protected final BPartnerService bPartnerService;

    @Autowired
    public BPartnerController(BPartnerService bPartnerService) {
        this.bPartnerService = bPartnerService;
    }

    @GetMapping(path = "api/v1/bpartner/{bPartnerID}")
    public ResponseEntity<String> bPartnerRetrieveByID(@RequestHeader("credentials") String credentials,
                                                       @PathVariable("bPartnerID") int bPartnerID ) {
        return command(credentials, () -> jsonSerialize(bPartnerService.retrieveByID(bPartnerID)) );
    }

    @DeleteMapping(path = "api/v1/bpartner/{bPartnerID}")
    public ResponseEntity<String> bPartnerDeleteByID(@RequestHeader("credentials") String credentials,
                                                     @PathVariable("bPartnerID") int bPartnerID ) {
        return command(credentials, () -> bPartnerService.deleteByID(bPartnerID) );
    }

    @PutMapping(path = "api/v1/bpartner/{bPartnerID}")
    public ResponseEntity<String> bPartnerUpdateByID(@RequestHeader("credentials") String credentials,
                                                     @PathVariable("bPartnerID") int bPartnerID,
                                                     @RequestBody() String values) {
        return command(credentials, () -> bPartnerService.updateByID(bPartnerID, jsonToMap(values)) );
    }

    @PostMapping(path = "api/v1/bpartner")
    public ResponseEntity<String> bPartnerInsert(@RequestHeader("credentials") String credentials,
                                                 @RequestBody() String values) {
        return command(credentials, () -> bPartnerService.insert(jsonToMap(values)) );
    }

}
