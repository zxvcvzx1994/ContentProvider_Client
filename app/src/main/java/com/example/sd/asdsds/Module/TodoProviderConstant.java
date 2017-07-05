package com.example.sd.asdsds.Module;

import android.net.Uri;

/**
 * Created by kh on 7/5/2017.
 */

public interface TodoProviderConstant {
    public  final String AUTHORITY = "com.example.kh.myapplication";

    public  final String PATH_TODO_LIST = "TODO_LIST";
    public  final String PATH_TODO_PLACE = "TODO_PLACE";
    public  final String PATH_TODO_COUNT = "TODO_COUNT";
    public  final Uri CONTENT_URI_1 = Uri.parse("content://"+AUTHORITY+"/"+PATH_TODO_LIST);
    public  final Uri CONTENT_URI_2 = Uri.parse("content://"+AUTHORITY+"/"+PATH_TODO_PLACE);
    public  final Uri CONTENT_URI_3 = Uri.parse("content://"+AUTHORITY+"/"+PATH_TODO_COUNT);
     final  String TEN ="ten";
     final String LOP = "lop";
     final String ID ="id";

}
