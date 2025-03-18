package vttp.batch4.csf.ecommerce.services;

import java.io.StringReader;
import java.util.Date;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import vttp.batch4.csf.ecommerce.models.Cart;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;

@Service
public class JsonService {
    
    public Order fromString(String payload){
        JsonObject json = Json.createReader(new StringReader(payload)).readObject();
        System.out.println(json);
//{"name":"Bob","address":"Bedrock 1","priority":false,"comments":"",
//"cart":[{"prodId":"67d0d74abebbe4b4ae8cf02d","quantity":1,"name":"Atlantic Salmon Fish","price":3879}]}

        Order order = new Order();
        order.setDate(new Date());
        order.setName(json.getString("name"));
        order.setPriority(json.getBoolean("priority"));
        order.setComments(json.getString("comments"));
        order.setAddress(json.getString("address"));

        JsonArray array = json.getJsonArray("cart");
        Cart cart = new Cart();
        for(int i = 0; i<array.size();i++){
            JsonObject item = array.getJsonObject(i);
            LineItem lineItem = getLineItem(item);
            cart.addLineItem(lineItem);
        }
        order.setCart(cart);

        return order;
    }

    private LineItem getLineItem(JsonObject item){
        LineItem lineItem = new LineItem();
        lineItem.setProductId(item.getString("prodId"));
        lineItem.setQuantity(item.getInt("quantity"));
        lineItem.setName(item.getString("name"));
        JsonNumber jsonNumber = item.getJsonNumber("price");
        float price = (float) jsonNumber.doubleValue();
        lineItem.setPrice(price);
        return lineItem;
    }
}
