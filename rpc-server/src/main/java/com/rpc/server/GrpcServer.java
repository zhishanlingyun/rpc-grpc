package com.rpc.server;

import com.rpc.biz.GreetImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    public static void main(String[] args) {
        ServerBuilder sb = ServerBuilder.forPort(8900);
        sb.addService(new GreetImpl());
        Server server = sb.build();
        try {
            server.start();
            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
