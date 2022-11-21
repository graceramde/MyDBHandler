package uottawa.engineering.mydbhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button readbtn = (Button) findViewById(R.id.ReadButton);
        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lookupProduct();
            }
        });

        Button addbtn = (Button) findViewById(R.id.AddButton);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProduct();
            }
        });

        Button delbtn = (Button) findViewById(R.id.DelButton);
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProduct();
            }
        });
    }

    public void newProduct(){
        Product product = new Product();
        EditText idEdit = findViewById(R.id.PRODUCT_ID);
        EditText nameEdit = findViewById(R.id.PRODUCT_NAME);
        EditText qtyEdit = findViewById(R.id.PRODUCT_SKU);

        product.setProductName(nameEdit.getText().toString());
        product.setId(idEdit.getText().toString());
        product.setSku(qtyEdit.getText().toString());

        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addProduct(product);
        idEdit.setText("");
        nameEdit.setText("");
        qtyEdit.setText("");
    }

    public void lookupProduct(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        EditText productBox = findViewById(R.id.PRODUCT_NAME);
        if(!productBox.getText().toString().isEmpty() && !(productBox.getText().toString() == null)){
            Product product = dbHandler.findProduct(productBox.getText().toString());
            Toast.makeText(getApplicationContext(),"ID : " + product.getId() + " QUANTITY: " + product.getSku(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Veuillez mettre un nom correcte s'il vous plait", Toast.LENGTH_SHORT).show();
        }

    }

    public void removeProduct(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        EditText productBox = findViewById(R.id.PRODUCT_NAME);
        if(!productBox.getText().toString().isEmpty() && !(productBox.getText().toString() == null)){
            boolean result = dbHandler.deleteProduct(productBox.getText().toString());
            if(result){
                Toast.makeText(getApplicationContext(),"DELETED", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"THERE IS NO SUCH PRODUCTS", Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(this, "Veuillez mettre un nom correcte s'il vous plait", Toast.LENGTH_SHORT).show();
        }


    }

}