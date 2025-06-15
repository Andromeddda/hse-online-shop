package hse.andromeddda.config;

import hse.andromeddda.dto.PaymentRequestDto;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableScheduling
public class KafkaConfig
{
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentRequestDto> kafkaListenerContainerFactory(
            ConsumerFactory<String, PaymentRequestDto> consumerFactory)
    {
        /* create factory */
        ConcurrentKafkaListenerContainerFactory<String, PaymentRequestDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}