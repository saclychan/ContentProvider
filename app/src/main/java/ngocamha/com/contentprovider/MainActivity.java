package ngocamha.com.contentprovider;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnContact = (Button) findViewById(R.id.btn_contact);
        Button btnMusic = (Button) findViewById(R.id.btn_music);
        Button btnImage = (Button) findViewById(R.id.btn_image);

        btnContact.setOnClickListener(this);
        btnMusic.setOnClickListener(this);
        btnImage.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.lv);
        gridView = (GridView) findViewById(R.id.gv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_contact:
                Toast.makeText(this, "Ahihi", Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getContactsV2());
                listView.setAdapter(adapter);
                break;
            case R.id.btn_music:
                ArrayAdapter<String> imageAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getMusic());
                listView.setAdapter(imageAdapter);
                break;
            case R.id.btn_image:
                GridviewAdapter gridviewAdapter = new GridviewAdapter(MainActivity.this, getImage());
                gridView.setAdapter(gridviewAdapter);
                break;
        }
    }

    private ArrayList<String> getContacts() {
        ArrayList<String> data = new ArrayList<>();
        //get contact
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                data.add(name);
                Log.d("mainactivityyyy", name);

            } while (cursor.moveToNext());
        }
        return data;
    }

    private ArrayList<String> getContactsV2() {
        ArrayList<String> data = new ArrayList<>();
        //get contact
        //Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String [] {ContactsContract.Contacts.DISPLAY_NAME} , null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        CursorLoader cursorLoader = new CursorLoader(MainActivity.this, ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                data.add(name);
                Log.d("mainactivityyyy", name);

            } while (cursor.moveToNext());
        }
        return data;
    }

    private ArrayList<String> getMusic() {
        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{MediaStore.Audio.Media.DISPLAY_NAME}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                data.add(name);
                Toast.makeText(this, "name " + name, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }

        return data;
    }


    private ArrayList<Bitmap> getImage() {
        ArrayList<Bitmap> data = new  ArrayList<>();
        Cursor cursor =  getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String []{MediaStore.Images.Media._ID}, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                Bitmap bitmap =  MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                data.add(bitmap);
            }while(cursor.moveToNext());
        }

        return  data;
    }

}
