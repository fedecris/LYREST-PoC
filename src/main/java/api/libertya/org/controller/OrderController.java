package api.libertya.org.controller;

import api.libertya.org.service.OrderService;
import api.libertya.org.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController extends GeneralController {

    protected final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/api/v1/order/{orderID}")
    public ResponseEntity<String> orderRetrieveByID(@RequestHeader("credentials") String credentials,
                                                    @PathVariable("orderID") int orderID) {
        return command(credentials, () -> JSON.serialize((orderService.retrieveByID(orderID))) );
    }

    @DeleteMapping(path = "/api/v1/order/{orderID}")
    public ResponseEntity<String> orderDeleteByID(@RequestHeader("credentials") String credentials,
                                                  @PathVariable("orderID") int orderID) {
        return command(credentials, () -> (orderService.deleteByID(orderID)) );
    }

    @PutMapping(path = "/api/v1/order/{orderID}")
    public ResponseEntity<String> orderUpdateByID(@RequestHeader("credentials") String credentials,
                                                  @PathVariable("orderID") int orderID,
                                                  @RequestBody() String values) {
        return command(credentials, () -> (orderService.updateByID(orderID, JSON.toMap(values))) );
    }

    @PostMapping(path = "/api/v1/order")
    public ResponseEntity<String> orderInsert(@RequestHeader("credentials") String credentials,
                                              @RequestBody() String values) {
        return command(credentials, () -> (orderService.insert(JSON.toMap(values))) );
    }
}
