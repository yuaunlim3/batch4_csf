package vttp.batch4.csf.ecommerce.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch4.csf.ecommerce.SqlQuery;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.models.Exception.FailedException;

@Repository
public class PurchaseOrderRepository {

  @Autowired
  private JdbcTemplate template;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  // You may only add Exception to the method's signature
  public void create(Order order) {
    // TODO Task 3
    int checker = template.update(SqlQuery.INSERT_ORDER_DETAILS, order.getOrderId(),order.getDate(),order.getName(),order.getAddress(),order.getPriority(),order.getComments());
    if(checker < 1){
      throw new FailedException();
    }
    else{
      List<LineItem> lineItems = order.getCart().getLineItems();
      for (LineItem item:lineItems){
        checker = template.update(SqlQuery.INSERT_LINEITEMS, order.getOrderId(),item.getProductId(),item.getName(),item.getQuantity(),item.getPrice());
        if (checker < 1){
          throw new FailedException();
        }
      }
    }
  }
}
