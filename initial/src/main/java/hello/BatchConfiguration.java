package hello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<IndeedJob> reader() {
    	
    	BSFManager.registerScriptingEngine("ruby", "org.jruby.javasupport.bsf.JRubyEngine", new String[]{"rb"});
        BSFManager manager = new BSFManager();

        //--- Load a ruby file
        try {
			manager.exec("ruby", "joernsindeedsearch.rb", -1, -1, new String(Files.readAllBytes(Paths.get("/home/sven/Dokumente/prog/github/gs-batch-processing/initial/src/main/ruby/joernsindeedsearch.rb"))));
		} catch (BSFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
        FlatFileItemReader<IndeedJob> reader = new FlatFileItemReader<IndeedJob>();
        reader.setResource(new ClassPathResource("file.csv"));
        reader.setLineMapper(new DefaultLineMapper<IndeedJob>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "company", "snippet" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<IndeedJob>() {{
                setTargetType(IndeedJob.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<? super IndeedJob, ? extends IndeedJob> processor() {
        return new IndeedJobItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<IndeedJob> writer() {
        JdbcBatchItemWriter<IndeedJob> writer = new JdbcBatchItemWriter<IndeedJob>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<IndeedJob>());
        writer.setSql("INSERT INTO workplace (company_name, snippet) VALUES (:company, :snippet)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::listener[]

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener(new JdbcTemplate(dataSource));
    }

    // end::listener[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<IndeedJob, IndeedJob> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
    
    
    
}