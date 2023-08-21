package com.rpc.client.biz;

import com.demo.grpc.GreetServiceGrpc;
import com.demo.grpc.GreeterProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8900).usePlaintext().build();
        GreetServiceGrpc.GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);
        GreeterProto.HelloRequest request = GreeterProto.HelloRequest.newBuilder().setName("张三").setMsg("登陆").build();
        GreeterProto.HelloResponse helloResponse = stub.greet(request);
        System.out.println("response code: " + helloResponse.getCode()+", message: " + helloResponse.getMessage());

    }

}
