package uottawa.engineering.mydbhandler;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper{

    String _id;
    String _productname;
    String _sku;


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "productname";
    private static final String COLUMN_SKU = "SKU";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME
                + " TEXT," + COLUMN_SKU + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getProductName());
        values.put(COLUMN_SKU, product.getSku());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public Product findProduct(String productname){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME + "=\"" + productname + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Product product = new Product();
        if (cursor.moveToFirst()){
            product.setId(String.valueOf(Integer.parseInt(cursor.getString(0))));
            product.setProductName(cursor.getString(1));
            product.setSku(String.valueOf(Integer.parseInt(cursor.getString(2))));
            cursor.close();
        }else{
            product = null;
        }
        db.close();
        return product;
    }

    public boolean deleteProduct(String productname){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME + "=\"" + productname + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
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

}