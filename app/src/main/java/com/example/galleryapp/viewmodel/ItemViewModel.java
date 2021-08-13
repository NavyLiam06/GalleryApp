package com.example.galleryapp.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;

import com.example.galleryapp.model.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class ItemViewModel {
    public static ArrayList<Item> listOfVideos(Context context) {
        Uri uri;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Video.Media._ID,
                MediaStore.MediaColumns.DATA,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DURATION};
        String orderBy = MediaStore.Video.Media.DATE_ADDED;
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null,
                orderBy + " DESC");

        ArrayList<Item> listAllVideos = new ArrayList<>();

        int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        int pathColum = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        int dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED);
        int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);

        while (cursor.moveToNext()){
            long id = cursor.getLong(idColumn);
            String name = cursor.getString(nameColumn);
            long addedDate = cursor.getLong(dateColumn);
            String path = cursor.getString(pathColum);
            int duration = cursor.getInt(durationColumn);
            Item item = new Item(id, name, addedDate, path, duration, false, true, -1);
            listAllVideos.add(item);
        }
        return listAllVideos;
    }

    public static ArrayList<Item> listOfImages(Context context) {
        Uri uri;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.DURATION};
        String orderBy = MediaStore.Images.Media.DATE_ADDED;
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null,
                orderBy + " DESC");

        ArrayList<Item> listAllImages = new ArrayList<>();

        int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        int pathColum = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED);
        int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DURATION);

        while (cursor.moveToNext()){
            long id = cursor.getLong(idColumn);
            String name = cursor.getString(nameColumn);
            long addedDate = cursor.getLong(dateColumn);
            String path = cursor.getString(pathColum);
            int duration = cursor.getInt(durationColumn);
            Item item = new Item(id, name, addedDate, path, duration, false, false, -1);
            listAllImages.add(item);
        }
        return listAllImages;
    }
    public static ArrayList<Item> listOfItems(Context context){
        ArrayList<Item> listAllItems = new ArrayList<>();
        ArrayList<Item> listAllImages = listOfImages(context);
        ArrayList<Item> listAllVideos = listOfVideos(context);
        listAllItems.addAll(listAllImages);
        listAllItems.addAll(listAllVideos);
        Collections.sort(listAllItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return Long.compare(item2.getAddedDate(), item1.getAddedDate());
            }
        });
        return listAllItems;
    }
    public static ArrayList<Item> listOfItemsandHeader(Context context){
        ArrayList<Item> listAllItems = listOfItems(context);
        ArrayList<Item> listItemsAndHeader = new ArrayList<>();

        if(listAllItems.size() == 0)
            return listAllItems;
        long date = listAllItems.get(0).getAddedDate();
        long tempDate = date * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = dateFormat.format(date);
        //
        Item item = new Item(-1, null, date, null, -1, true, false, -1);
        //listItemsAndHeader.add(item);
        //listItemsAndHeader.add(listAllItems.get(0));
        for (int i = 0; i < listAllItems.size(); i++){
            long tmp = listAllItems.get(i).getAddedDate() * 1000;
            String tmpStr = dateFormat.format(tmp);
            if(!tmpStr.equals(dateText)){
                long headerDate = listAllItems.get(i).getAddedDate();
                Item headerItem = new Item(-1, null, headerDate, null, -1, true, false, -1);
                listItemsAndHeader.add(headerItem);
            }
            listItemsAndHeader.add(listAllItems.get(i));
            dateText = tmpStr;
        }
        return listItemsAndHeader;
    }
}
