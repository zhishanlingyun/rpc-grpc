package com.rpc.biz;

import com.demo.grpc.GreetServiceGrpc;
import com.demo.grpc.GreeterProto;
import io.grpc.stub.StreamObserver;

public class GreetImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreeterProto.HelloRequest request, StreamObserver<GreeterProto.HelloResponse> responseObserver) {
        String name = request.getName();
        System.out.println("name: " + name);
        GreeterProto.HelloResponse response = GreeterProto.HelloResponse.newBuilder().setCode(200).setMessage(name + ": success").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
