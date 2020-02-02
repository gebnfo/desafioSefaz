package br.com.george.desafioSefaz.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.george.desafioSefaz.mapeamento.Usuario;
import br.com.george.desafioSefaz.util.HibernateUtil;

public class UsuarioDAO {
	
	private Session sessao =  null;;
	private Transaction transacao = null;
	
	public void salvar (Usuario usuario) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.save(usuario);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) 
				transacao.rollback();
			throw erro;
		} finally {
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listar () throws RuntimeException {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Usuario> colecaoUsuario = new ArrayList<Usuario>(); 
		try {
			Criteria criteria = sessao.createCriteria(Usuario.class);
			colecaoUsuario =  criteria.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		} 
		return colecaoUsuario;
	}
	
	public Usuario buscar (Integer codObjeto) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Usuario usuario = new Usuario();
		try {
			Criteria criteria = sessao.createCriteria(Usuario.class);
			usuario = (Usuario) criteria.add(Restrictions.eq("codObjeto", codObjeto)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		return usuario;
	}
	
	public void excluir (Usuario usuario) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(usuario);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) 
				transacao.rollback();
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public void alterar (Usuario usuario) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.update(usuario);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) 
				transacao.rollback();
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public Boolean verificaSeExisteEmailCadastrado(String email) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Usuario usuario = new Usuario();
		try {
			Criteria criteria = sessao.createCriteria(Usuario.class);
			usuario = (Usuario) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		if (usuario != null)
			return true;
		else
			return false;

	}
	
	public Usuario autenticarUsuario (String email, String senha) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Usuario usuario = new Usuario();
		try {
			Criteria criteria = sessao.createCriteria(Usuario.class);
			usuario = (Usuario) criteria.add(Restrictions.eq("email", email)).add(Restrictions.eq("senha", senha)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		return usuario;

	}

}
