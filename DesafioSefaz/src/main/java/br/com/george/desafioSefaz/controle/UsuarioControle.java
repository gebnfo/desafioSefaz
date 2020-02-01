package br.com.george.desafioSefaz.controle;

import java.util.List;

import br.com.george.desafioSefaz.dao.UsuarioDAO;
import br.com.george.desafioSefaz.mapeamento.Usuario;

public class UsuarioControle {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public static UsuarioControle getInstance () {
		UsuarioControle usuarioControle = new UsuarioControle();
		return usuarioControle;
	}
	
	public void salvar (Usuario usuario) {
		usuarioDAO.salvar(usuario);
	}
	
	public List<Usuario> listarUsuarios (){
		return usuarioDAO.listar();
	}
	
	public void alterarUsuario (Usuario usuario) {
		usuarioDAO.alterar(usuario);
	}
	
	public void excluirUsuario (Usuario usuario) {
		usuarioDAO.excluir(usuario);
	}

}
