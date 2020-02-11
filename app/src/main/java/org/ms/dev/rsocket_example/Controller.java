package org.ms.dev.rsocket_example;

import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.nio.ByteBuffer;

import io.rsocket.Payload;


public class Controller implements Publisher<Payload> {


    private static final String TAG = "Controller";

    private Subscriber<? super Payload> mSubscriber;


    @Override
    public void subscribe(Subscriber<? super Payload> s) {
        mSubscriber = s;
    }

    public void processPayload(Payload payload) {

        ByteBuffer data = payload.getData();
        ByteBuffer metadata = payload.getMetadata();
        String dataUtf8 = payload.getDataUtf8();
        String metadataUtf8 = payload.getMetadataUtf8();

        Log.e(TAG, "processPayload dataUtf8: " + dataUtf8);
        Log.e(TAG, "processPayload metadataUtf8 : " + metadataUtf8);

    }
}
