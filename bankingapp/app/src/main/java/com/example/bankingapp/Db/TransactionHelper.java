package com.example.bankingapp.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TransactionHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "transaction.db";
    private static final int DATABASE_VERSION = 1;

    public TransactionHelper(@Nullable Context context) {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TRANSACTION_TABLE =  "CREATE TABLE " + TransactionContract.TransactionEntry.TABLE_NAME + " ("
                + TransactionContract.TransactionEntry.COLUMN_FROM_NAME + " VARCHAR, "
                + TransactionContract.TransactionEntry.COLUMN_TO_NAME + " VARCHAR, "
                + TransactionContract.TransactionEntry.COLUMN_AMOUNT + " INTEGER, "
                + TransactionContract.TransactionEntry.COLUMN_STATUS + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TransactionContract.TransactionEntry.TABLE_NAME);
            onCreate(db);
        }
    }
    public boolean insertTransferData (String fromName, String toName, String amount, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TransactionContract.TransactionEntry.COLUMN_FROM_NAME, fromName);
        contentValues.put(TransactionContract.TransactionEntry.COLUMN_TO_NAME, toName);
        contentValues.put(TransactionContract.TransactionEntry.COLUMN_AMOUNT, amount);
        contentValues.put(TransactionContract.TransactionEntry.COLUMN_STATUS, status);
        Long result = db.insert(TransactionContract.TransactionEntry.TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
