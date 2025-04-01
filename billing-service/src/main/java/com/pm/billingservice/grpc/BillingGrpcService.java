package com.pm.billingservice.grpc;


import billing.BillingResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver) {

        log.info("createBillingAccount request received {}", billingRequest.toString());

        // Business logic - eg save to database. perform calculations, etc




        //create a billing response
        BillingResponse response = BillingResponse.newBuilder().setAccountId("12345").setStatus("Active").build();

        //send a response to client and end of cycle
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
