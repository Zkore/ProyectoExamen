package com.iteso.pdm18_scrollabletabs.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "itesoStore.db";
    private static final int DATABASE_VERSION = 1;

    private static DataBaseHandler dataBaseHandler;

    //TABLES
    public static final String TABLE_STORE = "Store";
    public static final String TABLE_CITY = "City";
    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_PRODUCT = "Product";
    public static final String TABLE_STOREPRODUCT = "StoreProduct";

    //Columns STORE
    public static final String STORE_ID = "IdStore";
    public static final String STORE_NAME = "Name";
    public static final String STORE_PHONE = "Phone";
    public static final String STORE_IDCITY= "IdCity";
    public static final String STORE_THUMBNAIL = "Thumbnail";
    public static final String STORE_LATITUDE = "Latitude";
    public static final String STORE_LONGITUDE = "Longitude";

    //Columns City
    public static final String CITY_ID = "IdCity";
    public static final String CITY_NAME = "Name";

    //Columns Category
    public static final String CATEGORY_ID = "IdCategory";
    public static final String CATEGORY_NAME = "Name";

    //Columns Product
    public static final String PRODUCT_ID = "IdProduct";
    public static final String PRODUCT_TITLE = "Title";
    public static final String PRODUCT_IMAGE = "Image";
    public static final String PRODUCT_IDCATEGORY = "IdCategory";

    //Columns StoreProduct
    public static final String STOREPRODUCT_ID = "IdStoreProduct";
    public static final String STOREPRODUCT_IDPRODUCT = "IdProduct";
    public static final String STOREPRODUCT_IDSTORE = "IdStore";


    private DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null)
            dataBaseHandler = new DataBaseHandler(context);
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableStore = "CREATE TABLE " + TABLE_STORE + " ("
                + STORE_ID + " INTEGER PRIMARY KEY,"
                + STORE_NAME + " TEXT, "
                + STORE_PHONE + " TEXT, "
                + STORE_THUMBNAIL + " INTEGER, "
                + STORE_LATITUDE + " DOUBLE, "
                + STORE_LONGITUDE + " DOUBLE, "
                + "FOREIGN KEY(" + STORE_IDCITY + ") REFERENCES "
                + TABLE_CITY + "(" + CITY_ID + "));";

        db.execSQL(tableStore);

        String tableCity = "CREATE TABLE " + TABLE_CITY + " ("
                + CITY_ID + " INTEGER PRIMARY KEY, "
                + CITY_NAME + " TEXT);";

        db.execSQL(tableCity);

        String tableCategory = "CREATE TABLE " + TABLE_CATEGORY + " ("
                + CATEGORY_ID + " INTEGER PRIMARY KEY,"
                + CATEGORY_NAME + " TEXT);";

        db.execSQL(tableCategory);

        String tableProduct = "CREATE TABLE " + TABLE_PRODUCT + " ("
                + PRODUCT_ID + " INTEGER PRIMARY KEY,"
                + PRODUCT_TITLE + " TEXT, "
                + PRODUCT_IMAGE + "INTEGER"
                + "FOREIGN KEY(" + PRODUCT_IDCATEGORY + ") REFERENCES "
                + TABLE_CATEGORY + "(" + CATEGORY_ID + "));";

        db.execSQL(tableProduct);

        String tableStoreProduct = "CREATE TABLE " + TABLE_STOREPRODUCT + " ("
                + STOREPRODUCT_ID + " INTEGER PRIMARY KEY,"
                + "FOREIGN KEY(" + STOREPRODUCT_IDPRODUCT + ") REFERENCES "
                + TABLE_PRODUCT + "(" + PRODUCT_ID + ")), "
                + "FOREIGN KEY(" + STOREPRODUCT_IDSTORE + ") REFERENCES "
                + TABLE_STORE + "(" + STORE_ID + "));";

        db.execSQL(tableStoreProduct);

        String insertCategoryTechnology = "INSERT INTO "
                + TABLE_CATEGORY + " (Name)"
                + " VALUES ('Technology'); ";

        db.execSQL(insertCategoryTechnology);

        String insertCategoryHome = "INSERT INTO "
                + TABLE_CATEGORY + " (Name)"
                + " VALUES ('Home'); ";

        db.execSQL(insertCategoryHome);

        String insertCategoryElectronics = "INSERT INTO "
                + TABLE_CATEGORY + " (Name)"
                + " VALUES ('Electronics'); ";

        db.execSQL(insertCategoryElectronics);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void upgradeV2(SQLiteDatabase db){
        String alterTable = "ALTER Table " + TABLE_PRODUCT + " ADD Phone TEXT";
        db.execSQL(alterTable);
    }

    private void upgradeV3(SQLiteDatabase db){
        String alterTable2 = "ALTER Table " + TABLE_PRODUCT + " ADD Address TEXT";
        db.execSQL(alterTable2);
    }
}











