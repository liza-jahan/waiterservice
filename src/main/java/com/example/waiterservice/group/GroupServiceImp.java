package group;

import com.example.waiterservice.userRegistration.UserEntity;
import com.example.waiterservice.userRegistration.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImp implements GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    @Override
    public UUID createGroup(String groupName, List<UUID> userIds) {
        Set<UserEntity> userEntities=userRepository.findAllById(userIds).stream().collect(Collectors.toSet());
        GroupEntity groupEntity=new GroupEntity();

        groupEntity.setGroupName(groupName.toUpperCase());
        groupEntity.setUsers(userEntities);
        groupRepository.save(groupEntity);
        return groupEntity.getId();
    }

    @Override
    public void deleteGroup(UUID groupId) {
      groupRepository.deleteById(groupId);
    }

    @Override
    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }
}
