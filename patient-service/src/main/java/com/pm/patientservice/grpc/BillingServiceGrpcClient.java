package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);

    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    //localhost:9001/BillingService/CreatePatientAccount
    //aws.grpc:123213/BillingService/CreatePatientAccount
    public BillingServiceGrpcClient(@Value("${billing_service.address:localhost}") String serverAddress,
                                    @Value("${billing_service.grpc.port:9001}") int serverPort)
    {
        log.info("Connecting to Billing Service GRPC Service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();

        blockingStub = BillingServiceGrpc.newBlockingStub(channel);

    }

    public BillingResponse createBillingAccount(String patientId, String name, String email){

        BillingRequest  request = BillingRequest.newBuilder().setPatientId(patientId).setName(name).setEmail(email).build();

        BillingResponse response = blockingStub.createBillingAccount(request);

        log.info("Received response from Billing Service via grpc: {}", response);

        return response;

    }

}
