package com.example.waiterservice.group;

import com.example.waiterservice.userRegistration.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_group")
public class GroupEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(columnDefinition = "VARCHAR(36)")
     @JdbcTypeCode(SqlTypes.VARCHAR)
     private UUID id;
     private String groupName;

     @ManyToMany
     @JoinTable(
             name = "user_groups",
             joinColumns = @JoinColumn(name = "group_id"),
             inverseJoinColumns = @JoinColumn(name = "user_id")
     )
     private Set<UserEntity> users = new HashSet<>();
}
