package vttp.batch4.csf.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.services.JsonService;
import vttp.batch4.csf.ecommerce.services.PurchaseOrderService;

@Controller
@RequestMapping(path = "/api",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

  @Autowired
  private PurchaseOrderService poSvc;

  @Autowired private JsonService jsonService;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  @PostMapping(path = "/order")
  public ResponseEntity<String> postOrder(@RequestBody String payload) {

    // TODO Task 3

    JsonObjectBuilder json;
    Order order = jsonService.fromJson(payload);
    try{
      if(poSvc.createNewPurchaseOrder(order)){
        json = Json.createObjectBuilder().add("orderId", order.getOrderId());
        return ResponseEntity.status(200).body(json.build().toString());
      }
      else{
        json = Json.createObjectBuilder().add("message", "Failed to make order");
        return ResponseEntity.status(400).body(json.build().toString());
      }
    }
    catch(Exception ex){
      json = Json.createObjectBuilder().add("message", ex.getMessage());
      return ResponseEntity.status(400).body(json.build().toString());
    }
  }
}
