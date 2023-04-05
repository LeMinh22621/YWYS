package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private BackgroundService backgroundService;

    @GetMapping("/backgrounds")
    public ResponseEntity<?> getBackgroundByTheme(@RequestParam("theme_id") String themeId){
        Response response = backgroundService.getBackgroundListByThemeId(themeId);
        return ResponseEntity.ok(response);
    }
}
