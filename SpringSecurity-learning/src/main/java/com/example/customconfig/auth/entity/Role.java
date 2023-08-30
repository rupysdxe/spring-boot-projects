package com.example.customconfig.auth.entity;

import com.example.customconfig.auth.enums.ERole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @Override
    public String getAuthority() {
        return this.role.toString();
    }
}
