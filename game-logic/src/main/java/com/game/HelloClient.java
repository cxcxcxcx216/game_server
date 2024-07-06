package com.game;

import com.game.proto.Hello;
import com.game.proto.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HelloClient {
    private final ManagedChannel channel;
    private final HelloServiceGrpc.HelloServiceBlockingStub blockingStub;

    public HelloClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
    }

    public HelloClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = HelloServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void sayHello(String name) {
        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName(name).build();
        Hello.HelloResponse response = blockingStub.sayHello(request);
        System.out.println("Greeting: " + response.getGreeting());
    }

    public static void main(String[] args) throws Exception {
        HelloClient client = new HelloClient("localhost", 9090);
        int i = 0;
        try {
            while (true) {
                i++;
                client.sayHello("Alice"+i);
            }
        } finally {
            client.shutdown();
        }
    }
}