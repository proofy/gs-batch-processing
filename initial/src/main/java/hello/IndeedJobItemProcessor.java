package hello;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class IndeedJobItemProcessor implements ItemProcessor<IndeedJob,IndeedJob>  {
	
	private static final Logger log = LoggerFactory.getLogger(IndeedJobItemProcessor.class);

	@Override
	public IndeedJob process(final IndeedJob jobItem) throws Exception {
		
		final String compName = jobItem.getCompany().toLowerCase();
		final String snipText = Jsoup.parse(jobItem.getSnippet()).text();
		
		final IndeedJob transJob = new IndeedJob(compName, snipText);
		
		log.info("Converting (" + jobItem + ") into " + transJob + ")");
		
		return transJob;
	}

}
