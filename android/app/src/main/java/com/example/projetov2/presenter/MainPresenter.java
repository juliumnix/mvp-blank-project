package com.example.projetov2.presenter;

import com.example.projetov2.presenter.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View view;
    private MainContract.Model model;

    public MainPresenter(MainContract.View view, MainContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onFlutterButtonClick(String message) {
        model.setMessageFromNative(message);
        view.showFlutterActivity();
    }

    @Override
    public void onReactButtonClick(String message) {
        model.setMessageFromNative(message);
        view.showReactActivity(message);
    }

    @Override
    public void initFlutter() {

    }

    @Override
    public void initReact() {

    }
}