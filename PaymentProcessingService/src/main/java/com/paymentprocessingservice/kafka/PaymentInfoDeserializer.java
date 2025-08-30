package com.paymentprocessingservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentprocessingservice.model.PaymentInfo;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PaymentInfoDeserializer implements Deserializer<PaymentInfo> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public PaymentInfo deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, PaymentInfo.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing PaymentInfo", e);
        }
    }

    @Override
    public void close() { }
}
