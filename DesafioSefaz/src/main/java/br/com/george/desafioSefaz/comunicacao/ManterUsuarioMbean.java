package br.com.george.desafioSefaz.comunicacao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.george.desafioSefaz.controle.TelefoneControle;
import br.com.george.desafioSefaz.controle.UsuarioControle;
import br.com.george.desafioSefaz.mapeamento.Telefone;
import br.com.george.desafioSefaz.mapeamento.Usuario;
import br.com.george.desafioSefaz.util.Constantes;

@ManagedBean
@ViewScoped
public class ManterUsuarioMbean {
	private List<Usuario> colecaoUsuario;
	
	private Usuario usuarioNovo;
	private Usuario usuarioSelecionado;
	
	private Telefone telefoneNovo;
	
	private UsuarioControle usuarioControle = UsuarioControle.getInstance();
	private TelefoneControle telefoneControle = TelefoneControle.getInstance();
	
	private String mensagemUsuarioComTelefones = Constantes.MENSAGEM_USUARIO_COM_TELEFONES;
	private Boolean mostrarMensagemExclusao = false;

	@PostConstruct
	public void inicializar() {
		limparCampos();
		try {
			colecaoUsuario = usuarioControle.listarUsuarios();
			for (Usuario usuario : colecaoUsuario) {
				usuario.setTelefones(telefoneControle.listarTelefonesPorUsuario(usuario));
			}
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}

	}

	public void salvarUsuario() {
		try {
			usuarioControle.salvar(usuarioNovo);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		colecaoUsuario.add(usuarioNovo);
	}
	
	public void alterarUsuario() {
		try {
			usuarioControle.alterarUsuario(usuarioSelecionado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
	}
	
	public void excluirUsuario () {
		try {
			usuarioControle.excluirUsuario(usuarioSelecionado);
			colecaoUsuario.remove(usuarioSelecionado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
	}
	
	public void salvarTelefone() {
		try {
			telefoneNovo.setUsuario(usuarioSelecionado);
			telefoneControle.salvar(telefoneNovo);
			telefoneNovo.getUsuario().getTelefones().add(telefoneNovo);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
	}
	
	public void selecionarUsuario (Usuario usuario) {
		telefoneNovo = new Telefone();
		usuarioSelecionado = usuario;
		telefoneNovo.setTipo("Celular");
	}
	
	public void selecionarUsuarioExclusao (Usuario usuario) {
		usuarioSelecionado = usuario;
		if (!usuarioSelecionado.getTelefones().isEmpty())
			mostrarMensagemExclusao = true;
		else
			mostrarMensagemExclusao = false;
	}
	
	public void limparCampos() {
		usuarioNovo = new Usuario();
		usuarioSelecionado = new Usuario();
		telefoneNovo = new Telefone();
	}

	public List<Usuario> getColecaoUsuario() {
		return colecaoUsuario;
	}

	public void setColecaoUsuario(List<Usuario> colecaoUsuario) {
		this.colecaoUsuario = colecaoUsuario;
	}

	public Usuario getUsuarioNovo() {
		return usuarioNovo;
	}

	public void setUsuarioNovo(Usuario usuarioNovo) {
		this.usuarioNovo = usuarioNovo;
	}

	public Telefone getTelefoneNovo() {
		return telefoneNovo;
	}

	public void setTelefoneNovo(Telefone telefoneNovo) {
		this.telefoneNovo = telefoneNovo;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public String getMensagemUsuarioComTelefones() {
		return mensagemUsuarioComTelefones;
	}

	public void setMensagemUsuarioComTelefones(String mensagemUsuarioComTelefones) {
		this.mensagemUsuarioComTelefones = mensagemUsuarioComTelefones;
	}

	public Boolean getMostrarMensagemExclusao() {
		return mostrarMensagemExclusao;
	}

	public void setMostrarMensagemExclusao(Boolean mostrarMensagemExclusao) {
		this.mostrarMensagemExclusao = mostrarMensagemExclusao;
	}
	
	

	
}
