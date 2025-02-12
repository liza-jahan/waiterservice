package group;

import com.example.waiterservice.model.request.GroupRequest;
import com.example.waiterservice.model.response.APIResponse;
import com.example.waiterservice.model.response.CreationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/create-group")
    public ResponseEntity<APIResponse<CreationResponse>> createGroup(@RequestBody GroupRequest groupRequest) {
        log.info("Received user registration request: {}", groupRequest);
        UUID userId = groupService.createGroup(groupRequest.getGroupName(), groupRequest.getUserIds());

        APIResponse<CreationResponse> responseDTO = APIResponse
                .<CreationResponse>builder()
                .dateTime(new Date().toString())
                .status(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK)
                .results(new CreationResponse(userId))
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/show-group")
    public ResponseEntity<List<GroupEntity>> getAllGroup() {
        List<GroupEntity> groupEntities = groupService.getAllGroups();
        return new ResponseEntity<>(groupEntities, HttpStatus.OK);
    }
}
