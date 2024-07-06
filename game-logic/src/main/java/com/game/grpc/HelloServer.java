//package com.game.grpc;
//
//import com.game.service.HelloServiceImpl;
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import lombok.extern.slf4j.Slf4j;
//
//
//@Slf4j
//public class HelloServer {
//
//    private int port;
//    private Server server;
//
//    public HelloServer(int port) {
//        this.port = port;
//    }
//
//    public void start() throws Exception {
//        server = ServerBuilder.forPort(port)
//                .addService(new HelloServiceImpl())
//                .build()
//                .start();
//        log.error("Server started, listening on {}",port);
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            log.error("*** shutting down gRPC server since JVM is shutting down");
//            HelloServer.this.stop();
//            log.error("*** server shut down");
//        }));
//    }
//
//    private void stop() {
//        if (server != null) {
//            server.shutdown();
//        }
//    }
//
//    private void blockUntilShutdown() throws InterruptedException {
//        if (server != null) {
//            server.awaitTermination();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        HelloServer server = new HelloServer(9090);
//        server.start();
//        server.blockUntilShutdown();
//    }
//}
