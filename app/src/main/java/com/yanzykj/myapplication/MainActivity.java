package com.yanzykj.myapplication;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*// 得到ContentResolver对象
        ContentResolver cr = this.getContentResolver();
        // 取得电话本中开始一项的光标,主要就是查询"contacts"表
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);*/
        getContacts();

    }
    private void getContacts() {

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        // 查询的字段
        String[] projection = { ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DATA1, "sort_key",
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY };
        // 得到ContentResolver对象
        ContentResolver cr = this.getContentResolver();
        // 取得电话本中开始一项的光标,主要就是查询"contacts"表
        // 按照sort_key升序查詢
        Cursor cursor =cr.query(uri, projection, null, null,
                "sort_key COLLATE LOCALIZED asc");
        while (cursor.moveToNext()) {





            StringBuilder sbLog = new StringBuilder();
            // 取得联系人名字 (显示出来的名字)，实际内容在 ContactsContract.Contacts中
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            String contactIds = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Log.e("TAG", "getContacts: +++++++++++++++++++++++++++++" + name+contactIds);
        }
        cursor.close();
    }
}
