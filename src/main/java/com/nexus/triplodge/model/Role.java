package com.nexus.triplodge.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRole> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    // public void assignRoleToUser(User user) {
    //     user.getRoles().add(this);
    //     this.getUsers().add(user);
    // }

    // public void removeRoleFromUser(User user) {
    //     user.getRoles().remove(this);
    //     this.getUsers().remove(user);
    // }

    // public void removeAllUsersFromRole() {
    //     if (this.getUsers() != null) {
    //         Set<User> users = this.getUsers();
    //         users.forEach(this :: removeRoleFromUser);
    //     }
    // }

    // public String getRoleName() {
    //     return name != null ? name : "";
    // }
}
