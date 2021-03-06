package be.solid.paperboy.model;

import be.solid.paperboy.service.PaperPriceService;
import com.google.common.collect.ImmutableList;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.List;

public class PaperFactory {

    private final PaperPriceService priceService;

    public PaperFactory(PaperPriceService priceService) {
        this.priceService = priceService;
    }

    public List<Paper> printPapers(int nr, LocalDate date) {
        final ImmutableList.Builder<Paper> builder = ImmutableList.builder();
        for (int i = 0; i < nr; i++) {
            builder.add(createPaper(date));
        }
        return builder.build();
    }

    private Paper createPaper(LocalDate date) {
        final Paper element = new Paper();
        element.setDate(date);
        final MonetaryAmount paperPrice = priceService.getPaperPrice(date);
        element.setUnitPriceOfPaper(paperPrice);
        return element;
    }


}
