package com.manikanta.microservices.project.OrderService.OrderBatchProccesing;

import com.manikanta.microservices.project.OrderService.Entity.Order;
import com.manikanta.microservices.project.OrderService.Repository.OrderRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaPagingItemReader<Order> itemReader() {
        JpaPagingItemReader<Order> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory); // Use the EntityManagerFactory
        reader.setQueryString("SELECT o FROM Order o WHERE o.orderStatus = 'Pending'"); // Query to fetch PENDING orders
        reader.setPageSize(2); // Set the page size
        return reader;
    }

//    private LineMapper<Product> lineMapper(){
//        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
//        delimitedLineTokenizer.setDelimiter(",");
//        delimitedLineTokenizer.setStrict(false);
//        delimitedLineTokenizer.setNames("id","itemNames", "quantity", "price", "description");
//        BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Product.class);
//
//        lineMapper.setLineTokenizer(delimitedLineTokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return lineMapper;
//    }

    @Bean
    public ItemProcessor<Order, Order> processor() {
        return product -> {
            // Update order status from PENDING to SUCCESS
            product.setOrderStatus("Delivered");
            return product;
        };
    }

    @Bean
    public RepositoryItemWriter<Order> itemWriter(){
        RepositoryItemWriter<Order> writer = new RepositoryItemWriter<>();
        writer.setRepository(orderRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step stepOne(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("csv-step", jobRepository)
                .<Order, Order>chunk(2,platformTransactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job runJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("csv-job", jobRepository)
                .flow(stepOne(jobRepository,platformTransactionManager))
                .end().build();
    }

}