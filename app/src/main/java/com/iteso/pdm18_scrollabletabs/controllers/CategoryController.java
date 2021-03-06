package com.iteso.pdm18_scrollabletabs.controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.beans.Category;

import java.util.ArrayList;

public class CategoryController {

    public ArrayList<Category> getCategories (DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "Select " + DataBaseHandler.CATEGORY_ID + ","
                + DataBaseHandler.CATEGORY_NAME
                + " FROM " + DataBaseHandler.TABLE_CATEGORY;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){
        }

        return categories;
    }

}
