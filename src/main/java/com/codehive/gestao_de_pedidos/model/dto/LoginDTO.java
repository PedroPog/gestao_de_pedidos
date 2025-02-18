package com.codehive.gestao_de_pedidos.model.dto;


import com.codehive.gestao_de_pedidos.model.RoleName;

public class LoginDTO {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
