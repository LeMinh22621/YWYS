package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.dto.payload.request.BackgroundRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.MotivationalQuoteRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.ThemeRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.BackgroundFacade;
import minh.lehong.yourwindowyoursoul.facade.MotivationalQuoteFacade;
import minh.lehong.yourwindowyoursoul.facade.ThemeFacade;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ThemeFacade themeFacade;
    @Autowired
    private BackgroundFacade backgroundFacade;

    @Autowired
    private MotivationalQuoteFacade motivationalQuoteFacade;
    /**
     * User account
     */
    @GetMapping("accounts")
    public ResponseEntity<Response> getAllUserAccount()
    {
        Response response = userFacade.findAllByIsDeletedAndRole(Role.USER);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete/{user_id}")
    public ResponseEntity<?> deleteRoomById(@PathVariable("user_id") String userId){
        Response response = userFacade.deleteUserByUserId(userId);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("update/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable("user_id") String userId, @RequestBody UserRequest userRequest){
        Response response = userFacade.updateUser(userId, userRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Background
     */
    @PostMapping(value = "/background-upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleFileUpload(@RequestPart("file") MultipartFile file,@RequestPart("backgroundRequest") BackgroundRequest backgroundRequest) {
        Response response = backgroundFacade.addNewBackground(file, backgroundRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/backgrounds/{background_id}")
    public ResponseEntity<?> deleteBackgroundById(@PathVariable("background_id") String backgroundId){
        Response response = backgroundFacade.deleteBackground(backgroundId);
        return ResponseEntity.ok(response);
    }
    @PatchMapping(value = "backgrounds/{background_id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable("background_id") String backgroundId,MultipartFile file,@RequestPart("backgroundRequest") BackgroundRequest backgroundRequest){
        Response response = backgroundFacade.updateBackground(backgroundId, file, backgroundRequest);
        return ResponseEntity.ok(response);
    }
    /**
     * Theme
     */
    @PostMapping(value = "/theme")
    public ResponseEntity<?> handleAddNewTheme(@RequestBody ThemeRequest themeRequest) {
        Response response = themeFacade.newTheme(themeRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/themes/{theme_id}")
    public ResponseEntity<?> deleteThemeById(@PathVariable("theme_id") String themeId){
        Response response = themeFacade.delete(themeId);
        return ResponseEntity.ok(response);
    }
    @PatchMapping(value = "themes/{theme_id}")
    public ResponseEntity<?> updateUser(@PathVariable("theme_id") String themeId, @RequestBody ThemeRequest themeRequest){
        Response response = themeFacade.updateTheme(themeId, themeRequest);
        return ResponseEntity.ok(response);
    }
    /**
     * Motivational quote
     */
    @GetMapping("motivational-quotes")
    public ResponseEntity<Response> getAllMotivationalQuotes()
    {
        Response response = motivationalQuoteFacade.getAllMotivationalQuotes();
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/motivational-quotes-add")
    public ResponseEntity<?> addNewMotivationalQuote(@RequestBody MotivationalQuoteRequest motivationalQuoteRequest) {
        Response response = motivationalQuoteFacade.addNewMotivationalQuote(motivationalQuoteRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("motivational-quotes/{motivaional_quote_id}")
    public ResponseEntity<?> deleteMotivationalQuoteById(@PathVariable("motivaional_quote_id") String quoteId) {
        Response response = motivationalQuoteFacade.deleteMotivationalQuoteById(quoteId);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("motivational-quotes/{motivaional_quote_id}")
    public ResponseEntity<?> updateMotivationalQuote(@PathVariable("motivaional_quote_id") String quoteId, @RequestBody MotivationalQuoteRequest request) {
        Response response = motivationalQuoteFacade.updateMotivationalQuote(quoteId, request);
        return ResponseEntity.ok(response);
    }
    /**
     * Clear cache
     */
    @GetMapping(value = "/clear-all-cache")
    public ResponseEntity<?> clearAllCache() {
        for(String cacheName : cacheManager.getCacheNames())
        {
            cacheManager.getCache(cacheName).clear();
        }
        return ResponseEntity.ok("OK");
    }
}
