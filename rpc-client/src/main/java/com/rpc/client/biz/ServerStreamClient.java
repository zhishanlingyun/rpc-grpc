package com.rpc.client.biz;

import com.demo.grpc.GreetServiceGrpc;
import com.demo.grpc.GreeterProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class ServerStreamClient {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8900).usePlaintext().build();
        GreetServiceGrpc.GreetServiceStub stub = GreetServiceGrpc.newStub(channel);
        GreeterProto.HelloRequest request = GreeterProto.HelloRequest.newBuilder().setName("张三").setMsg("登陆").build();
        stub.c2ss(request, new StreamObserver<GreeterProto.HelloResponse>() {
            @Override
            public void onNext(GreeterProto.HelloResponse helloResponse) {
                System.out.println("response code: " + helloResponse.getCode()+", message: " + helloResponse.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
                latch.countDown();
            }
        });

        try {
            latch.await();
            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
