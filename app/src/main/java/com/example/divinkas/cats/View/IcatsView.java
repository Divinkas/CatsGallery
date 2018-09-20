package com.example.divinkas.cats.View;

import com.arellomobile.mvp.MvpView;
import com.example.divinkas.cats.Data.PicktureObj;

public interface IcatsView extends MvpView {
    void showListImages(PicktureObj picktureObj);
    void findElementsView();
}
