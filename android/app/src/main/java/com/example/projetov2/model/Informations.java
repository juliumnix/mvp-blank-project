package com.example.projetov2.model;

import com.example.projetov2.presenter.contract.MainContract;

public class Informations implements MainContract.Model {
    private static final String CHANNEL_FLUTTER = "samples.flutter.dev/battery";
    private static final String Channel_Id = "my_engine_id";
    private String Message_From_Native = "";

    private static Informations instance;

    private Informations() {

    }

    public static synchronized Informations getInstance() {
        if (instance == null) {
            instance = new Informations();
        }
        return instance;
    }

    public static String getChannelFlutter() {
        return CHANNEL_FLUTTER;
    }

    public static String getChannel_Id() {
        return Channel_Id;
    }
    @Override
    public String getMessageFromNative() {
        return Message_From_Native;
    }
    @Override
    public void setMessageFromNative(String message_From_Native) {
        Message_From_Native = message_From_Native;
    }
}