package api.libertya.org.controller;

import api.libertya.org.common.Paths;
import api.libertya.org.service.BPartnerService;
import api.libertya.org.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
public class BPartnerController extends GeneralController {

    protected final BPartnerService bPartnerService;

    @Autowired
    public BPartnerController(BPartnerService bPartnerService) {
        this.bPartnerService = bPartnerService;
    }

    @GetMapping(path = Paths.BPARTNER + "/{bPartnerID}")
    public ResponseEntity<String> bPartnerRetrieveByID(@RequestHeader("credentials") String credentials,
                                                       @PathVariable("bPartnerID") int bPartnerID ) {
        return command(credentials, () -> JSON.serialize(bPartnerService.retrieveByID(bPartnerID)) );
    }

    @DeleteMapping(path = Paths.BPARTNER + "/{bPartnerID}")
    public ResponseEntity<String> bPartnerDeleteByID(@RequestHeader("credentials") String credentials,
                                                     @PathVariable("bPartnerID") int bPartnerID ) {
        return command(credentials, () -> bPartnerService.deleteByID(bPartnerID) );
    }

    @PutMapping(path = Paths.BPARTNER + "/{bPartnerID}")
    public ResponseEntity<String> bPartnerUpdateByID(@RequestHeader("credentials") String credentials,
                                                     @PathVariable("bPartnerID") int bPartnerID,
                                                     @RequestBody() String values) {
        return command(credentials, () -> bPartnerService.updateByID(bPartnerID, JSON.toMap(values)) );
    }

    @PostMapping(path = Paths.BPARTNER)
    public ResponseEntity<String> bPartnerInsert(@RequestHeader("credentials") String credentials,
                                                 @RequestBody() String values) {
        return command(credentials, () -> bPartnerService.insert(JSON.toMap(values)) );
    }

}
