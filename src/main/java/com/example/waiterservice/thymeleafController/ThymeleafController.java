package com.example.waiterservice.thymeleafController;

import com.example.waiterservice.group.GroupEntity;
import com.example.waiterservice.group.GroupService;
import com.example.waiterservice.model.request.AnonymousUserRequest;
import com.example.waiterservice.model.request.UserRegistrationRequest;
import com.example.waiterservice.userRegistration.UserEntity;
import com.example.waiterservice.userRegistration.UserRepository;
import com.example.waiterservice.userRegistration.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ThymeleafController {

    private final UserService userService;
    private final GroupService groupService;
    private  final UserRepository userRepository;


    @GetMapping("/guest-registration-page")
    public String showRegistrationPage(Model model) {
        model.addAttribute("guestUser", new AnonymousUserRequest());
        return "contact";
    }


    @GetMapping("/user-registration-page")
    public String showUserRegistrationPage(Model model) {
        model.addAttribute("userRequest", new UserRegistrationRequest());
        return "userRegistrationForm";
    }

    @GetMapping("/home-page")
    public String showHomePage(Model model) {
        // model.addAttribute("userRequest", new UserRegistrationRequest());
        return "homePage";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user-info-table")
    public String getDashboardPage(Model model) {
        List<UserEntity> userEntities = userService.getAllUser();
        model.addAttribute("registrations", userEntities);
        return "userinfoTable";
    }

    @GetMapping("/service-page")
    public String servicePage(Model model) {
        // model.addAttribute("userRequest", new UserRegistrationRequest());
        return "service";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        // You can add any model attributes here if needed
        return "loginPage"; // This corresponds to login.html (Thymeleaf template)
    }

    @GetMapping("/gallery")
    public String showGallery() {
        return "gallery";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/guest-user-info")
    public String showAnonymousUserInfo() {
        return "anonymousUserInfo";
    }

//    @GetMapping("/user-profile")
//    public String showProfile() {
//        return "userProfile";
//    }

    @GetMapping("/userinfoTable")
    public String showUserInfoTable(Model model) {
        List<UserEntity> users = userService.getAllUser(); // Fetch user data
        model.addAttribute("users", users);
        return "userinfoTable"; // Ensure this matches the template name
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage() {
        return "resetPasswordPage";
    }
   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/show-group-list")
    public String showGroups(Model model) {
        List<GroupEntity> groups =  groupService.getAllGroupsWithMembers();
        model.addAttribute("groups", groups);
        return "groupList";
    }
    @GetMapping("/about-us")
    public String aboutUs() {
        return "aboutUs";
    }
    // Show create group form
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new GroupEntity());
        model.addAttribute("users", userRepository.findAll());
        return "group-form";
    }

    // Handle group creation
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createGroup(@RequestParam String groupName, @RequestParam List<UUID> userIds) {
        groupService.createGroup(groupName, userIds);
        return "redirect:/userinfoTable";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete-group")
    public String deleteGroup(@RequestParam UUID groupId) {
        groupService.deleteGroup(groupId);
        return "redirect:/show-group-list"; // Redirect after deletion
    }



    // Handle adding a user to a group
    @PostMapping("/{groupName}/add-user")
    public String addUserToGroup(@PathVariable String groupName, @RequestParam UUID userId) {
        groupService.addUserToGroup(groupName, userId);
        return "redirect:/userinfoTable";
    }

  // user profile
  @GetMapping("/profile/{id}")
  public String showProfile(@PathVariable UUID id, Model model) {
      UserEntity userEntity = userService.getUserProfile(id);
      model.addAttribute("userProfile", userEntity);  // Add user data to the model
      return "userProfile";  // Return to the Thymeleaf user profile page
  }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.setHeader("Authorization", "");

        return "redirect:/login";
    }

  }


