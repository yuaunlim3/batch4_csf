package vttp.batch4.csf.ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.services.JsonService;
import vttp.batch4.csf.ecommerce.services.PurchaseOrderService;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

  @Autowired
  private PurchaseOrderService poSvc;
  
  @Autowired private JsonService jSvc;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
    @PostMapping(path="/order")
  public ResponseEntity<String> postOrder(@RequestBody String payload) {

   Order order = jSvc.fromString(payload);
   poSvc.createNewPurchaseOrder(order);
	 
	 return ResponseEntity.ok(Json.createObjectBuilder().add("orderId", order.getOrderId()).build().toString());
  }
}
