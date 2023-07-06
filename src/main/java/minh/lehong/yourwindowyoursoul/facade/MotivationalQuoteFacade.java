package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.request.MotivationalQuoteRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;

public interface MotivationalQuoteFacade {
    Response getAllMotivationalQuotes();

    Response addNewMotivationalQuote(MotivationalQuoteRequest motivationalQuoteRequest);

    Response deleteMotivationalQuoteById(String quoteId);

    Response updateMotivationalQuote(String quoteId, MotivationalQuoteRequest request);
}
