package com.paymentcheckservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentcheckservice.model.PaymentInfo;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PaymentInfoSerializer implements Serializer<PaymentInfo> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public byte[] serialize(String topic, PaymentInfo data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing PaymentInfo", e);
        }
    }

    @Override
    public void close() { }
}
