package cn.vvkeep.power_file_view;


import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

public class PowerFileViewPlugin implements FlutterPlugin, ActivityAware {

    public static final String channelName = "vvkeep.power_file_view.io.channel";
    public static final String viewName = "vvkeep.power_file_view.view";

    private MethodChannel channel;
    private FlutterPluginBinding pluginBinding;
    private NetworkBroadcastReceiver mReceiver;
    private Activity activity;
    private Context context;
    private EngineState engineState = EngineState.none;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        LogUtils.e("onAttachedToEngine");
        pluginBinding = flutterPluginBinding;
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        LogUtils.e("onDetachedFromEngine");
        if (mReceiver != null && context != null) {
            context.unregisterReceiver(mReceiver);
        }
    }

    private void init(Context context, BinaryMessenger messenger) {
        this.context = context;
        registerBroadcast(context);
        channel = new MethodChannel(messenger, channelName);
        channel.setMethodCallHandler(new MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
                switch (call.method) {
                    default:
                        result.notImplemented();
                        break;
                }
            }
        });
    }

    private void registerBroadcast(Context context) {
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        LogUtils.e("onDetachedFromActivityForConfigChanges");
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        LogUtils.e("onReattachedToActivityForConfigChanges");
    }

    @Override
    public void onDetachedFromActivity() {
        LogUtils.e("onDetachedFromActivity");
    }

    public interface OnInitListener {
        void onInit(EngineState status);

        void onDownload(int progress);
    }

}
