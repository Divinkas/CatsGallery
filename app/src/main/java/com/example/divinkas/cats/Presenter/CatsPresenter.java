package com.example.divinkas.cats.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.cats.Adapter.GalleryAdapter;
import com.example.divinkas.cats.Data.Hit;
import com.example.divinkas.cats.Model.CatsModel;
import com.example.divinkas.cats.View.IcatsView;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class CatsPresenter extends MvpPresenter<IcatsView> {

    private CatsModel catsModel;
    private GalleryAdapter galleryAdapter;
    Context context;

    public CatsPresenter(){
        catsModel = new CatsModel();
    }

    public void setBaseContext(Context context){
        if(this.context== null){
            this.context = context;
            galleryAdapter = new GalleryAdapter(new ArrayList<>(), context);
            catsModel.updateData(galleryAdapter);
        }
    }

    public GalleryAdapter getAdapter() {
        return galleryAdapter;
    }
}
