package br.com.george.desafioSefaz.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.george.desafioSefaz.mapeamento.Telefone;
import br.com.george.desafioSefaz.mapeamento.Usuario;
import br.com.george.desafioSefaz.util.HibernateUtil;

public class TelefoneDAO {
	
	private Session sessao =  null;;
	private Transaction transacao = null;
	
	public void salvar (Telefone telefone) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.save(telefone);
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
	public List<Telefone> listar (){
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Telefone> colecaoTelefone = new ArrayList<Telefone>(); 
		try {
			Criteria criteria = sessao.createCriteria(Telefone.class);
			colecaoTelefone =  criteria.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		} 
		return colecaoTelefone;
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> listarPorUsuario (Usuario usuario){
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Telefone> colecaoTelefone = new ArrayList<Telefone>(); 
		try {
			Criteria criteria = sessao.createCriteria(Telefone.class);
			colecaoTelefone =  criteria.add(Restrictions.eq("usuario", usuario)).list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		} 
		return colecaoTelefone;
	}
	
	public Telefone buscar (Integer codObjeto) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Telefone telefone = new Telefone();
		try {
			Criteria criteria = sessao.createCriteria(Telefone.class);
			telefone = (Telefone) criteria.add(Restrictions.eq("codObjeto", codObjeto)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		return telefone;
	}
	
	public void excluir (Telefone telefone) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(telefone);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) 
				transacao.rollback();
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public void alterar (Telefone telefone) {
		sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.update(telefone);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) 
				transacao.rollback();
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
