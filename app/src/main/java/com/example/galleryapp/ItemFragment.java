package com.example.galleryapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.galleryapp.model.Item;
import com.example.galleryapp.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView img_recyclerView;
    ItemAdapter itemAdapter;
    ArrayList<Item> items;

    private  static final int MY_READ_PERMISSION_CODE = 101;

    public ItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemFragment newInstance(String param1, String param2) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        img_recyclerView = view.findViewById(R.id.rcv_ListImage);

        if(ContextCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_PERMISSION_CODE);
        }else{
            loadItems();
        }
        return view;
    }

    private void loadItems() {
        img_recyclerView.setHasFixedSize(true);
        items = ItemViewModel.listOfItemsandHeader(this.getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 5);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(items.get(position).isHeader())
                    return 5;
                else
                    return 1;
            }
        });
        img_recyclerView.setLayoutManager(layoutManager);
        //img_recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 5));
        itemAdapter = new ItemAdapter(this.getContext(), items, new ItemAdapter.ItemListener() {
            @Override
            public void onItemClick(Item item) {
                Toast.makeText(getContext(), "" + item.getPath(), Toast.LENGTH_SHORT).show();
            }
        });
        img_recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_READ_PERMISSION_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this.getContext(), "GRANTED", Toast.LENGTH_SHORT).show();
                loadItems();
            }else{
                Toast.makeText(this.getContext(), "DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}