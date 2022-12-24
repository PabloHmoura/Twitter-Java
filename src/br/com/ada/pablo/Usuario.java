package br.com.ada.pablo;

public class Usuario {
     String usuario;
     String senha;
     int qtd = 1;
     String[] usuarios = new String[qtd];

     public String getUsuario() {
          return usuario;
     }

     public void setUsuario(String usuario) {
          this.usuario = usuario;
     }

     public String getSenha() {
          return senha;
     }

     public void setSenha(String senha) {
          this.senha = senha;
     }

     public int getQtd() {
          return qtd;
     }

     public void setQtd(int i) {
     }
}
