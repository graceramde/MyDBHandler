package uottawa.engineering.mydbhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newProduct(Product product){
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addProduct(product);
    }

    public void lookupProduct(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        EditText productBox = findViewById(R.id.PRODUCT_NAME);
        Product product = dbHandler.findProduct(productBox.getText().toString());
    }

    public void removeProduct(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        EditText productBox = findViewById(R.id.PRODUCT_NAME);
        boolean result = dbHandler.deleteProduct(productBox.getText().toString());
    }

}