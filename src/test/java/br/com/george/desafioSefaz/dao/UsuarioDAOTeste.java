package br.com.george.desafioSefaz.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.george.desafioSefaz.mapeamento.Usuario;

public class UsuarioDAOTeste {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	@Ignore
	@Test
	public void salvar () {
		Usuario usuario = new Usuario();
		usuario.setNome("George");
		usuario.setSenha("teste2");
		usuario.setEmail("teste");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	@Ignore
	@Test
	public void listar () {
		List<Usuario> colecaoUsuario = usuarioDAO.listar();
		for (Usuario usuario: colecaoUsuario) {
			System.out.println(usuario.getNome());
		}
	}
	
	@Ignore
	@Test
	public void buscar () {
		Usuario usuario = usuarioDAO.buscar(3);
		if (usuario != null)
			System.out.println(usuario.getNome());
		else 
			System.out.println("usuario não encontrado!");
	}
	@Ignore
	@Test
	public void excluir () {
		Usuario usuario = usuarioDAO.buscar(3);
		usuarioDAO.excluir(usuario);
	}
	@Test
	public void alterar () {
		Usuario usuario = usuarioDAO.buscar(4);
		usuario.setEmail("ge.georgesantos@hotmail.com");
		usuarioDAO.alterar(usuario);
	}

}
