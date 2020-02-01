package br.com.george.desafioSefaz.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.george.desafioSefaz.mapeamento.Telefone;
import br.com.george.desafioSefaz.mapeamento.Usuario;

public class TelefoneDAOTeste {
	private TelefoneDAO telefoneDAO = new TelefoneDAO();
	
	@Ignore
	@Test
	public void salvar () {
		Telefone telefone = new Telefone();
		telefone.setDdd(81);
		telefone.setNumero("98991-8610");
		telefone.setTipo("Celular");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(3);
		telefone.setUsuario(usuario);
		telefoneDAO.salvar(telefone);
	}
	
	@Ignore
	@Test
	public void listar (){
		List<Telefone> colecaoTelefone = telefoneDAO.listar();
		for (Telefone telefone: colecaoTelefone) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getUsuario().getNome());
			
		}
	}
	
	@Ignore
	@Test
	public void buscar () {
		Telefone telefone = telefoneDAO.buscar(1);
		System.out.println(telefone.getNumero());
		System.out.println(telefone.getUsuario().getNome());
	}
	@Ignore
	@Test
	public void excluir () {
		Telefone telefone = telefoneDAO.buscar(3);
		telefoneDAO.excluir(telefone);
	}
	
	@Test
	public void alterar () {
		Telefone telefone = telefoneDAO.buscar(2);
		telefone.setDdd(88);
		telefoneDAO.alterar(telefone);
	}

}
