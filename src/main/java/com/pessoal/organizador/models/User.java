package com.pessoal.organizador.models;

import com.pessoal.organizador.DTOs.DadosUsuarioLogin;
import com.pessoal.organizador.DTOs.DadosUsuarioSignup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



@Table(name="users")
@Entity(name="User")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Getter
    private String login;

    private String nome;

    private String senha;



    public User(DadosUsuarioLogin d){
        this.login = d.login();
        this.senha = d.senha();
    }

    public User(String login, String nome, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }
}
