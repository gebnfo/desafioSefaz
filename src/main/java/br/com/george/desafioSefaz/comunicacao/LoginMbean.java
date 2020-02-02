package br.com.george.desafioSefaz.comunicacao;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;

import br.com.george.desafioSefaz.controle.UsuarioControle;
import br.com.george.desafioSefaz.mapeamento.Usuario;
import br.com.george.desafioSefaz.util.Util;

@ManagedBean
@SessionScoped
public class LoginMbean {
	private String email;
	private String senha;
	private Usuario usuarioAutenticado;
	private Util util;

	private UsuarioControle usuarioControle = UsuarioControle.getInstance();
	
	@PostConstruct
	public void inicializar () {
		email = "";
		senha = "";
		usuarioAutenticado = null;
	}

	public void logar() {
		util = new Util();
		try {
			usuarioAutenticado = usuarioControle.autenticar(email, senha);
			if (usuarioAutenticado == null) {
				util.getMenssagemAlerta("Seu email ou senha estão incorretos!");
			} else {
				Faces.redirect("./paginas/manterUsuario.xhtml");
			}
		} catch (IOException erro) {
			erro.printStackTrace();
			util.getMenssagemErro();
		}

	}

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

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

}
