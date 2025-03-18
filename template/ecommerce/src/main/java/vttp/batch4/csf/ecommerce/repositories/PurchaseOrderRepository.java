package vttp.batch4.csf.ecommerce.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch4.csf.ecommerce.Utils;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;

@Repository
public class PurchaseOrderRepository {

  @Autowired
  private JdbcTemplate template;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  // You may only add Exception to the method's signature
  public boolean create(Order order) {
    // TODO Task 3

    //INSERT ORDER
    int checker = template.update(Utils.SQL_INSERT_ORDERS, order.getOrderId(),order.getDate(),order.getName(),order.getAddress(),order.getComments());
    if(checker>0){
      for(LineItem lineItem: order.getCart().getLineItems()){
        checker = template.update(Utils.SQL_INSERT_ITEMS, lineItem.getProductId(),order.getOrderId(),lineItem.getName(),lineItem.getQuantity(),lineItem.getPrice());

        if(checker <= 0 ){
          return false;
        }
        
      }

      return true;
    }
    return false;
  }
}
