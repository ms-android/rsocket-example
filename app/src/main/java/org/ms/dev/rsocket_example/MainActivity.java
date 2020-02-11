package org.ms.dev.rsocket_example;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.function.Consumer;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import reactor.core.publisher.Flux;

public class MainActivity extends AppCompatActivity {


    private static final String HOST = "192.168.1.8";
    private static final int PORT = 9006;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RSocket rsocket = RSocketFactory.connect()
                .errorConsumer(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {

                    }
                })
                .transport(TcpClientTransport.create(HOST, PORT))
                .start()
                .block();


        Controller controller = new Controller();

        rsocket.requestChannel(Flux.from(controller))
                .doOnNext(controller::processPayload)
                .blockLast();


    }
}
