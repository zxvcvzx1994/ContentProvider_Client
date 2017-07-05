package com.example.sd.asdsds;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sd.asdsds.Module.TodoProviderConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtCount)
    TextView txtCount;
    ContentResolver contentResolver;
    @BindView(R.id.etSpecific)
    EditText etSpecific;
    @BindView(R.id.txtData)
    TextView txtData;
    @BindView(R.id.etId)
    EditText etid;
    @BindView(R.id.etName)
    EditText etTen;
    @BindView(R.id.etLop)
    EditText etLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        contentResolver = getContentResolver();
    }

    @OnClick(R.id.btnCount)
        public void btnCount(){
        setcount();
    }

    @OnClick(R.id.btnShowDataSpecific)
    public void btnShowdata(){
        ShowSpecificData();
    }

    @OnClick(R.id.btnShowAllData)
    public void btnShowAllData(){
        showAllData();
    }

    @OnClick(R.id.btnUpdate)
    public void btnUpdate(){
        update();
    }

    @OnClick(R.id.btnInsert)
    public void btnInsert(){
        insert();
    }

    @OnClick(R.id.btnDelete)
    public void btnDelete(){
        delete();
    }
    public void setcount(){
        Cursor cursor  = contentResolver.query(TodoProviderConstant.CONTENT_URI_3,null,null,null,null);
        if(cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();
            txtCount.setText(""+cursor.getInt(0));
            cursor.close();
        }

    }
    public void ShowSpecificData(){
        if(etSpecific.getText().toString().trim().length()>0) {
            StringBuilder stringBuilder = new StringBuilder("");
            String specific = etSpecific.getText().toString().trim();
            Cursor cursor = contentResolver.query(TodoProviderConstant.CONTENT_URI_2, null, null, new String[]{specific}, null);
            if(cursor.getCount()>0 && cursor!=null){
                while(cursor.moveToNext()){
                    stringBuilder.append("id: "+cursor.getInt(0)+", ten: "+cursor.getString(1)+", lop: "+cursor.getString(2)+"\n");
                }
                txtData.setText(""+stringBuilder.toString());
            }
        }else{
            txtData.setText("");
            Toast.makeText(this, "specific is empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void showAllData(){

            StringBuilder stringBuilder = new StringBuilder("");
            String specific = etSpecific.getText().toString().trim();
            Cursor cursor = contentResolver.query(TodoProviderConstant.CONTENT_URI_1, null, null, null, null);
            if(cursor.getCount()>0 && cursor!=null){
                while(cursor.moveToNext()){
                    stringBuilder.append("id: "+cursor.getInt(0)+", ten: "+cursor.getString(1)+", lop: "+cursor.getString(2)+"\n");
                }
                txtData.setText(""+stringBuilder.toString());
            }

    }

    public void delete(){
        contentResolver.delete(TodoProviderConstant.CONTENT_URI_1,"id="+1,null);
        showAllData();
    }

    public void update(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten",etTen.getText().toString());
        contentValues.put("lop",etLop.getText().toString());
        String whereClause = "id=?";
        String[] whereArgument = new String[]{etid.getText().toString().trim()};
        contentResolver.update(TodoProviderConstant.CONTENT_URI_1,contentValues,whereClause,whereArgument);
        showAllData();
    }

    public void insert(){
        if(etTen.getText().toString().trim().length()>0  ) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", etid.getText().toString().trim());
            contentValues.put("lop", etLop.getText().toString().trim());
            contentValues.put("ten", etTen.getText().toString().trim());
            contentResolver.insert(TodoProviderConstant.CONTENT_URI_1, contentValues);
        }else{
            Toast.makeText(this, "id or ten is null", Toast.LENGTH_SHORT).show();
        }
        showAllData();
    }


}
