package minh.lehong.yourwindowyoursoul.facade.Impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.BackgroundDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.BackgroundRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.BackgroundResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.BackgroundFacade;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import minh.lehong.yourwindowyoursoul.service.FileStoreS3Service;
import minh.lehong.yourwindowyoursoul.service.ThemeService;
import minh.lehong.yourwindowyoursoul.utils.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BackgroundFacadeImpl implements BackgroundFacade {

    @Autowired
    private BackgroundService backgroundService;
    @Autowired
    private CommonConverter commonConverter;
    @Autowired
    private ThemeService themeService;

    @Autowired
    private FileStoreS3Service fileStore;

    @Override
    public Response getBackgroundByBackgroundId(String backgroundId) {
        Response response;
        try
        {
            Background background = backgroundService.findById(UUID.fromString(backgroundId));
            BackgroundResponse backgroundResponse = commonConverter.convertBackgroundDtoToBackGroundResponse(commonConverter.convertBackgroundEntityToBackgroundDto(background));

            response = new Response(backgroundResponse, true, "GET Background By BackgroundId Success!", HttpStatus.OK.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, "GET Background Failed", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Response getBackgroundListByThemeId(String themeId) throws IllegalArgumentException{
        UUID uuidThemeId = UUID.fromString(themeId);
        Theme theme = themeService.findThemeByThemeId(uuidThemeId);
        Response response = new Response();
        Collection<Background> backgrounds = backgroundService.findBackgroundsByTheme(theme);
        Collection<BackgroundDto> backgroundDtos = backgrounds
                .stream()
                .map(background -> commonConverter.convertBackgroundEntityToBackgroundDto(background))
                .collect(Collectors.toList());

        response.setData(backgroundDtos);
        response.setMessage("Get List Background based on themeId is ok!");
        response.setStatus(true);
        response.setReturnCode(HttpStatus.OK.value());

        return response;
    }

    @Override
    public Response addNewBackground(MultipartFile file, BackgroundRequest backgroundRequest) {
        Response response = null;

        try{
            if(!file.isEmpty())
            {
                // 1. Grab some metadata from file if any
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                metadata.setContentType(file.getContentType());
                // 2. Store the image in s3 and update database with s3 image link
                //String path = String.format("%s/image", BucketName.BUCKET_NAME.getBucketName());
                String fileName = String.format("image/%s", file.getOriginalFilename());
                // 3. start store
                String url = fileStore.save(fileName, file.getInputStream(), metadata);
                System.out.println(url);

                // 4. Store to DB
                backgroundRequest.setBackgroundImage(url);
                BackgroundDto backgroundDto = commonConverter.convertBackgroundRequestToBackgroundDto(new BackgroundDto(), backgroundRequest);
                if(backgroundRequest.getThemeId() != null)
                    backgroundDto.setThemeDto(commonConverter.convertThemeEntityToThemeDto(themeService.findThemeByThemeId(UUID.fromString(backgroundRequest.getThemeId()))));
                Background background = commonConverter.convertBackgroundDtoToBackGroundEntity(new Background(), backgroundDto);
                backgroundService.save(background);

                response = new Response(
                        commonConverter.convertBackgroundDtoToBackGroundResponse(commonConverter.convertBackgroundEntityToBackgroundDto(background)),
                        true,
                        "Add Background Success!",
                        HttpStatus.OK.value());
            }
            else{
                response = new Response(null, false, "Bad Request!", HttpStatus.BAD_REQUEST.value());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Response deleteBackground(String backgroundId) {
        Response response;
        try {
            if (backgroundId != null && !backgroundId.isEmpty()) {
                Background background = backgroundService.findById(UUID.fromString(backgroundId));
                if (background != null) {
                    background.setIsDeleted(true);
                    background = backgroundService.save(background);
                    response = new Response(commonConverter.convertBackgroundDtoToBackGroundResponse(commonConverter.convertBackgroundEntityToBackgroundDto(background)), true, "Delete Background Success", HttpStatus.OK.value());
                } else
                    response = new Response(null, false, "Bad Have No BackgroundId!", HttpStatus.BAD_REQUEST.value());
            } else
                response = new Response(null, false, "Bad Request Invalid BackgroundId!", HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response updateBackground(String backgroundId, MultipartFile file, BackgroundRequest backgroundRequest) {
        Response response;

        try{
            if (backgroundId != null && !backgroundId.isEmpty()) {
                if(file != null && !file.isEmpty())
                {
                    // 1. Grab some metadata from file if any
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(file.getSize());
                    metadata.setContentType(file.getContentType());
                    // 2. Store the image in s3 and update database with s3 image link
                    String path = String.format("%s/image", BucketName.BUCKET_NAME.getBucketName());
                    String fileName = String.format("%s", file.getOriginalFilename());
                    // 3. start store
                    String url = fileStore.save(fileName, file.getInputStream(), metadata);
                    System.out.println(url);

                    // 4. Store to DB
                    backgroundRequest.setBackgroundImage(url);
                }
                Background background = backgroundService.findById(UUID.fromString(backgroundId));
                if (background != null) {
                    BackgroundDto backgroundDto = commonConverter.convertBackgroundEntityToBackgroundDto(background);
                    backgroundDto = commonConverter.convertBackgroundRequestToBackgroundDto(backgroundDto, backgroundRequest);
                    background = commonConverter.convertBackgroundDtoToBackGroundEntity(background, backgroundDto);

                    background = backgroundService.save(background);
                    response = new Response(
                        commonConverter.convertBackgroundDtoToBackGroundResponse(commonConverter.convertBackgroundEntityToBackgroundDto(background)),
                        true,
                        "Update Background Success",
                        HttpStatus.OK.value()
                    );

                } else
                    response = new Response(null, false, "Bad Have No BackgroundId!", HttpStatus.BAD_REQUEST.value());
            } else
                response = new Response(null, false, "Bad Request Invalid BackgroundId!", HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception e) {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }
}
