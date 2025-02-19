package com.example.waiterservice.userRegistration;

import com.example.waiterservice.entity.BaseEntity;
import com.example.waiterservice.role.RoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder

public class UserEntity extends BaseEntity {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String gender;
    private String occupation;
    private Date dateOfBirth;
    private String nationalIdCard;
    private String bkash;
    private String state;
    private String role;


//
//    @Enumerated(EnumType.STRING)
//    private Role role;

    //    @ManyToMany(mappedBy = "users")
//    private Set<GroupEntity> groups = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

}
