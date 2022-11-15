package uottawa.engineering.mydbhandler;

/**
 * Created by Miguel Garzón on 2017-05-07.
 */

public class Product {
    String _id;
    String _productname;
    String _sku;
    //double _price;

    public Product() {
    }
    public Product(String id, String productname){//, double price) {
        _id = id;
        _productname = productname;
        //_price = price;
    }
    public Product(String productname){//, double price) {
        _productname = productname;
        //_price = price;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setProductName(String productname) {
        _productname = productname;
    }
    public String getProductName() {
        return _productname;
    }

    public void setSku(String sku){
        _sku = sku;
    }
    public String getSku(){
        return _sku;
    }

    //public void setPrice(double price) {
        //_price = price;
    }
    //public double getPrice() {
        //return _price;
    //}
//}


