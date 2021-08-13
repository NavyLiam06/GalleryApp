package com.example.galleryapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.galleryapp.model.Item;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFullscreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFullscreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imageView;
    VideoView videoView;
    public FragmentFullscreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_fullscreen.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFullscreen newInstance(String param1, String param2) {
        FragmentFullscreen fragment = new FragmentFullscreen();
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
        View view = inflater.inflate(R.layout.fragment_fullscreen, container, false);
        imageView = view.findViewById(R.id.full_image_view);
        videoView = view.findViewById(R.id.full_video_view);
        Bundle bundle = new Bundle();
        bundle = getArguments();
        Item item = (Item) bundle.getSerializable("item");
        String path = item.getPath();
        if(!item.isVideo()){
            imageView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            Glide.with(getContext()).load(path).into(imageView);
        }else{
            imageView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(path);
            videoView.setMediaController(new MediaController(this.getContext()));
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        return view;
    }

}