package com.training.config;

import java.util.HashMap;
import java.util.Map;

import com.training.model.Attendance;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig
{
    public static final String GROUP_ID = "group1";

    @Bean
    public ConsumerFactory<String, Attendance> consumerFactory()
    {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Attendance.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Attendance> attendanceListener()
    {
        ConcurrentKafkaListenerContainerFactory<String, Attendance> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
