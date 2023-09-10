package com.example.projetov2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetov2.model.Informations;
import com.example.projetov2.presenter.MainPresenter;
import com.example.projetov2.presenter.contract.MainContract;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    public FlutterEngine flutterEngine;
    private MainContract.Presenter presenter;
    private static final String CHANNEL = "samples.flutter.dev/battery";

    private Context context;
    private MainContract.Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnReact = findViewById(R.id.btn_react);
        Button btnFlutter = findViewById(R.id.btn_flutter);
        EditText txtMessage = findViewById(R.id.txt_texto);

        model = Informations.getInstance();

        presenter = new MainPresenter(this,model);

        context = getBaseContext();

        initializerFlutterEngine();

        btnFlutter.setOnClickListener(view -> presenter.onFlutterButtonClick(txtMessage.getText().toString()));
        btnReact.setOnClickListener(view -> presenter.onReactButtonClick(txtMessage.getText().toString()));
    }

    @Override
    public void showFlutterActivity() {
        startActivity(
                FlutterActivity
                        .withCachedEngine("my_engine_id")
                        .build(context)
        );
    }

    @Override
    public void showReactActivity(String message) {
        Intent intent = new Intent(MainActivity.this, MyReactActivity.class);
        intent.putExtra("message_from_native", message);
        startActivity(intent);
    }

    @Override
    public void initializerFlutterEngine() {
        flutterEngine = new FlutterEngine(this);
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        FlutterEngineCache
                .getInstance()
                .put("my_engine_id", flutterEngine);

        // aqui faz a comunicacao nativo - flutter
        FlutterEngine engine = FlutterEngineCache.getInstance().get("my_engine_id");
        if (engine != null) {
            // Set a MethodCallHandler to that engine.
            new MethodChannel(engine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                    .setMethodCallHandler((MethodCall call, MethodChannel.Result result) -> {
                        if ("getMessage".equals(call.method)) {
                            result.success(model.getMessageFromNative()); // Send whatever you need here.
                        } else {
                            result.notImplemented();
                        }
                    });
        }
    }

    @Override
    public void initializerReactEngine() {

    }
}