package minh.lehong.yourwindowyoursoul.facade.Impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.MotivationalQuoteDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.MotivationalQuoteRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.MotivationalQuoteFacade;
import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import minh.lehong.yourwindowyoursoul.service.MotivationalQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MotivationalQuoteFacadeImpl implements MotivationalQuoteFacade {
    @Autowired
    private MotivationalQuoteService motivationalQuoteService;

    @Autowired
    private CommonConverter commonConverter;


    @Override
    public Response getAllMotivationalQuotes() {
        Response response;

        try{
            List<MotivationalQuote> motivationalQuotes = motivationalQuoteService.getAllMotivationalQuotes();
            if(!motivationalQuotes.isEmpty())
            {
                List<MotivationalQuoteDto> motivationalQuoteDtos = motivationalQuotes
                        .stream()
                        .map(motivationalQuote -> commonConverter.convertMotivationalQuoteEntityToMotivationalQuoteDto(motivationalQuote))
                        .collect(Collectors.toList());

                response = new Response(motivationalQuoteDtos, true, "Get All Motivational Quotes Success!", HttpStatus.OK.value());
            }
            else
                response = new Response(null, false, "Have No Motivational Quotes", HttpStatus.NO_CONTENT.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Get All Motivational Quotes Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Response addNewMotivationalQuote(MotivationalQuoteRequest motivationalQuoteRequest) {
        Response response;

        try{
            if(motivationalQuoteRequest != null)
            {
                MotivationalQuoteDto motivationalQuoteDto = commonConverter.convertMotivationalQuoteRequestToMotivationalQuoteDto(new MotivationalQuoteDto(), motivationalQuoteRequest);
                MotivationalQuote motivationalQuote = commonConverter.convertMotivationalQuoteDtoToMotivationalQuoteEntity(new MotivationalQuote(), motivationalQuoteDto);
                motivationalQuote = motivationalQuoteService.save(motivationalQuote);

                response = new Response(
                        commonConverter.convertMotivationalQuoteEntityToMotivationalQuoteDto(motivationalQuote),
                        true,
                        "Add New Motivational Quote Success!",
                        HttpStatus.OK.value());
            }
            else
                response = new Response(null, false, "Add Motivational Quote Missing Body Request", HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Add Motivational Quote Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response deleteMotivationalQuoteById(String quoteId) {
        Response response;

        try{
            MotivationalQuote motivationalQuote = motivationalQuoteService.findById(UUID.fromString(quoteId));
            if(motivationalQuote != null)
            {
                motivationalQuoteService.deleteMotivationalQuoteById(UUID.fromString(quoteId));
                response = new Response(
                        commonConverter.convertMotivationalQuoteEntityToMotivationalQuoteDto(motivationalQuote),
                        true,
                        "Delete Motivational Quote Success!",
                        HttpStatus.OK.value()
                );
            }
            else
                response = new Response(null, false, "Have No Motivational Quote ID");
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Delete Motivational Quote Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response updateMotivationalQuote(String quoteId, MotivationalQuoteRequest request) {
        Response response;

        try{
            if(request != null && quoteId != null)
            {
                MotivationalQuote quote = motivationalQuoteService.findById(UUID.fromString(quoteId));
                if(quote != null){
                    MotivationalQuoteDto quoteDto = commonConverter.convertMotivationalQuoteEntityToMotivationalQuoteDto(quote);
                    quoteDto = commonConverter.convertMotivationalQuoteRequestToMotivationalQuoteDto(quoteDto, request);
                    quote = commonConverter.convertMotivationalQuoteDtoToMotivationalQuoteEntity(quote, quoteDto);

                    quote = motivationalQuoteService.save(quote);
                    response = new Response(
                            commonConverter.convertMotivationalQuoteEntityToMotivationalQuoteDto(quote),
                            true,
                            "Update Motivational Quote Success!",
                            HttpStatus.OK.value()
                    );
                }
                else
                    response = new Response(
                            null,
                            false,
                            "Have No that Quote Getting By QuoteId",
                            HttpStatus.NO_CONTENT.value()
                    );
            }
            else
                response = new Response(null, false, "Update Motivational Quote Missing Body Request", HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception e)
        {
        response = new Response(null, false, "Update Motivational Quote Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
                return response;
    }
}
