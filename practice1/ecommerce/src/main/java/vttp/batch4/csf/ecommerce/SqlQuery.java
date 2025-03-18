package vttp.batch4.csf.ecommerce;

public class SqlQuery {
    public static final String INSERT_ORDER_DETAILS = """
            INSERT INTO orderdetails(order_id,order_date,name,address,priority,comments) VALUES(?,?,?,?,?,?)
            """;
    public static final String INSERT_LINEITEMS = """
            INSERT INTO orderList(order_id,product_id,name,quantity,price) VALUES(?,?,?,?,?)
            """;
}
