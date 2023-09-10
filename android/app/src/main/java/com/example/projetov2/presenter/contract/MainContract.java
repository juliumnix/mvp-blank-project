package com.example.projetov2.presenter.contract;

public interface MainContract {
    interface View {
        void showFlutterActivity();
        void showReactActivity(String message);
        void initializerFlutterEngine();
        void initializerReactEngine();
    }

    interface Presenter {
        void onFlutterButtonClick(String message);
        void onReactButtonClick(String message);
        void initFlutter();
        void initReact();
    }

    interface Model {
        String getMessageFromNative();
        void setMessageFromNative(String message);
    }
}