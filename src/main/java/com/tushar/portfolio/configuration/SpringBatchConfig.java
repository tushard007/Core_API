package com.tushar.portfolio.configuration;

import com.tushar.portfolio.BatchProcessing.PortfolioBatchProcessor;
import com.tushar.portfolio.component.JobCompletionNotificationListener;
import com.tushar.portfolio.model.PortfolioBatch;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.BatchConfigurationSelector;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;


@EnableAutoConfiguration()
public class SpringBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<PortfolioBatch> reader() {
        return new FlatFileItemReaderBuilder<PortfolioBatch>()
                .name("PortfolioItemReader")
                .resource(new ClassPathResource("holdings.csv"))
                .delimited()
                .names(new String[]{"Instrument", "Qty","Avg_cost"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<PortfolioBatch>() {{
                    setTargetType(PortfolioBatch.class);
                }})
                .build();
    }

    @Bean
    public PortfolioBatchProcessor processor() {
        return new PortfolioBatchProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<PortfolioBatch> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<PortfolioBatch>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO portfolio_batch (Instrument,Qty,Avg_cost) VALUES (:instrument,:qty,:avg_cost)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importPortfolioJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importPortfolioJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<PortfolioBatch> writer) {
        return stepBuilderFactory.get("step1")
                .<PortfolioBatch, PortfolioBatch> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
