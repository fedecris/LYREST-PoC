package api.libertya.org.controller;

import api.libertya.org.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController extends GeneralController {

    protected final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/api/v1/product/{productID}")
    public ResponseEntity<String> productRetrieveByID(@RequestHeader("credentials") String credentials,
                                                      @PathVariable("productID") int productID) {
        return command(credentials, () -> jsonSerialize(productService.retrieveByID(productID)) );
    }

    @DeleteMapping(path = "/api/v1/product/{productID}")
    public ResponseEntity<String> productDeleteByID(@RequestHeader("credentials") String credentials,
                                                    @PathVariable("productID") int productID) {
        return command(credentials, () -> productService.deleteByID(productID) );
    }

    @PutMapping(path = "/api/v1/product/{productID}")
    public ResponseEntity<String> productUpdateByID(@RequestHeader("credentials") String credentials,
                                                    @PathVariable("productID") int productID,
                                                    @RequestBody() String values) {
        return command(credentials, () -> productService.updateByID(productID, jsonToMap(values)) );
    }

    @PostMapping(path = "/api/v1/product")
    public ResponseEntity<String> productInsert(@RequestHeader("credentials") String credentials,
                                                @RequestBody() String values) {
        return command(credentials, () -> productService.insert(jsonToMap(values)) );
    }
}
