package vttp.batch4.csf.ecommerce.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batch4.csf.ecommerce.models.Cart;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;

@Service
public class JsonService {
    

    public Order fromJson(String payload){
        JsonReader jsonReader = Json.createReader(new StringReader(payload));
        JsonObject json = jsonReader.readObject();
        Order order = new Order();
/*
 * {"name":"fghgf","address":"fghf","priority":false,"comments":"",
 * "cart":
 *      {"lineItems":[{
 *                    "prodId":"67c540418f15c5a675de7238","quantity":1,"name":"Glow Assorted Loose Leaf Tea","price":1995}]}}
 */
        order.setName(json.getString("name"));
        order.setAddress(json.getString("address"));
        order.setPriority(json.getBoolean("priority"));
        order.setComments(json.getString("comments"));
        JsonObject lineItems = json.getJsonObject("cart");
        JsonArray array = lineItems.getJsonArray("lineItems");
        
        List<LineItem> lineItemList = new LinkedList<>();
        for(int i = 0; i < array.size();i++){
            LineItem lineItem = new LineItem();
            JsonObject item = array.getJsonObject(i);
            lineItem.setName(item.getString("name"));
            lineItem.setPrice(item.getInt("price"));
            lineItem.setProductId(item.getString("prodId"));
            lineItem.setQuantity(item.getInt("quantity"));
            lineItemList.add(lineItem);
        }

        Cart cart = new Cart();
        cart.setLineItems(lineItemList);
        order.setCart(cart);

        return order;

    }
}
