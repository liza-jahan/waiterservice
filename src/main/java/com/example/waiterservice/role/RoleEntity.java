package com.example.waiterservice.role;

import com.example.waiterservice.userRegistration.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String name;


    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> userEntity = new HashSet<>();

}