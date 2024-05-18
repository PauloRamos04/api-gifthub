package com.example.GiftHub.domain.user;


import com.example.GiftHub.domain.address.Address;
import com.example.GiftHub.domain.cart.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long userId;

    @Column(name = "nome_completo")
    private String fullName;

    @Column(name = "login")
    @NotNull(message = "O login é obrigatorio")
    private String login;

    @Column(name = "email")
    @NotNull(message = "email é obrigatorio")
    private String email;

    @Column(name = "cpf")
    @NotNull(message = "cpf é obrigatorio")
    private String cpf;

    @Column(name = "dt_nasc")
    @NotNull(message = "data de nascimento é obrigatorio")
    private String dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    @NotNull(message = "Endereço obrigatorio")
    private Address address;

    @Column(name = "senha")
    @NotNull(message = "Senha obrigatoria")
    private String password;

    @Column(name = "cargo")
    private UserRole role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();


    public User(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(UserDTO dto) {
        this.fullName = dto.fullName();
        this.email = dto.email();
        this.login = dto.login();
        this.cpf = dto.cpf();
        this.dateOfBirth = dto.dateOfBirth();
        this.password = dto.password();
        this.role = dto.role();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

