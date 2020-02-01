package br.com.george.desafio.util;

import org.hibernate.Session;

import br.com.george.desafioSefaz.util.HibernateUtil;

public class HibernateUtilTeste {
	public void conectar () {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

}
