package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.dto.payload.request.BackgroundRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.BackgroundFacade;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin")
//@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.19:3000"})
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CacheManager cacheManager;


    @Autowired
    private BackgroundFacade backgroundFacade;
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
