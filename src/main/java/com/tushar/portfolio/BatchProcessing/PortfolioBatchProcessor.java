package com.tushar.portfolio.BatchProcessing;

import com.tushar.portfolio.model.PortfolioBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PortfolioBatchProcessor implements
        ItemProcessor<PortfolioBatch, PortfolioBatch> {
    private static final Logger log = LoggerFactory.getLogger(PortfolioBatch.class);

    @Override
    public PortfolioBatch process(final PortfolioBatch portfolio) throws Exception {
        final Integer id = portfolio.getId();
        final String Instrument = portfolio.getInstrument();
        final float Qty = portfolio.getQty();
        final float Avg_cost = portfolio.getAvg_cost();
        final PortfolioBatch transformedPortfolioBatch = new PortfolioBatch(id, Instrument, Qty, Avg_cost);
        log.info("Converting (" + portfolio + ") into (" + transformedPortfolioBatch + ")");

        return transformedPortfolioBatch;
    }

}

