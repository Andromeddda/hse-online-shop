package hse.andromeddda.config;

import hse.andromeddda.dto.PaymentOutcome;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class KafkaConfig
{
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentOutcome> kafkaListenerContainerFactory(
            ConsumerFactory<String, PaymentOutcome> consumerFactory)
    {
        /* Create factory */
        ConcurrentKafkaListenerContainerFactory<String, PaymentOutcome> factory
                = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}