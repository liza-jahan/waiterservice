package group;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    UUID createGroup(String groupName, List<UUID> userIds);
    void deleteGroup(UUID groupId);
    List<GroupEntity> getAllGroups();
}
