package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocios.Notificacoes;


public class FrmNotificacoes extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8936787470286562133L;

	FrmEvento formEvento;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblDivisao = null;
	private JButton btnNovo = null;
	private JButton btnAbrir = null;
	private JButton btnRemover = null;
	private JButton btnCancelar = null;

	private JTable tbNotificacoes = null;
	private JScrollPane jspNotificacoes = null;

	Box linhaUm = Box.createHorizontalBox();
	Box linhas = Box.createVerticalBox();

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	private String selecionado = "";

	//----Atributo para armazenar os itens da tabela(linhasXcolunas)	
	DefaultTableModel tabela;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	//--Retornar seleção
	public String getSelecionado(){
		return this.selecionado;
	}
	public void setSelecionado(String selecao){
		this.selecionado = selecao;
	}
	//----Método para construir tabela de pesquisa
	public void construirTabela(){
		tbNotificacoes = new JTable(getTabela());
		jspNotificacoes = new JScrollPane( tbNotificacoes );
		jspNotificacoes.setPreferredSize(new Dimension(360, 400));
		linhaUm.add(jspNotificacoes);
	}

	//----Consultar camada de negócios - Classe Funcionário
	public void consultarNotificacoes(){
		Notificacoes consultar = new Notificacoes();
		consultar.consultar();
		this.tabela = consultar.getTabela();
		if(tabela.getRowCount()<=0){
			Object linha[] = new Object[3];
			linha[0] = "";
			linha[1] = "";
			linha[2] = "";
			tabela.addRow(linha);
			construirTabela();			
		}else{
			construirTabela();			
		}
	}
	private String loginAtual = "";
	public String getLogin(){
		return loginAtual;
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmNotificacoes(JDesktopPane desktop, int permissao, String login) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.loginAtual = login;
		consultarNotificacoes();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Notificações");	
		this.setResizable(false);

		lblDivisao = new JLabel("_______________________________________________________________");
		btnAbrir = new JButton("Abrir");
		btnAbrir.setToolTipText("Exibir item selecionado");
		btnNovo = new JButton("Novo");
		btnNovo.setToolTipText("Iniciar novo cadastro");
		btnRemover = new JButton("Remover");
		btnRemover.setToolTipText("Remover item selecionado");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");

		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaDoze.add(lblDivisao);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnRemover);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAbrir);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnNovo);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		linhas.add(linhaUm);
		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formEvento = new FrmEvento(getPermissao(),0,"", getLogin());
				formEvento.setLocation(300,180);
				formEvento.setVisible(true);
				atualizaTabela();
			}});
		tbNotificacoes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) ""+tbNotificacoes.getValueAt(tbNotificacoes.getSelectedRow(), 0));
			}
		});
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().trim().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um evento para poder vizualizá-lo","Notificação",1);
				}else{
					setSelecionado((String) ""+tbNotificacoes.getValueAt(tbNotificacoes.getSelectedRow(), 0));
					formEvento = new FrmEvento(getPermissao(),1, getSelecionado(), getLogin());
					formEvento.setLocation(300,180);
					formEvento.setVisible(true);
					atualizaTabela();
				}
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String assunto = (String) tbNotificacoes.getValueAt(tbNotificacoes.getSelectedRow(), 1);
				if(assunto.equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um evento para poder removê-la","Notificação",1);
				}else{
					if(assunto.equals("Visita")){
						JOptionPane.showMessageDialog(null,"Não é possível remover evento de visita através deste formulário\npara alterações deste tipo abra o formulário correspondente","Notificação",1);							
					}else{
						setSelecionado((String) ""+tbNotificacoes.getValueAt(tbNotificacoes.getSelectedRow(), 0));
						Notificacoes remover = new Notificacoes();
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Tem certeza que deseja remover este evento?", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							remover.remover(getSelecionado(), assunto);
							atualizaTabela();
						}	
					}							
				}					
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
	private void atualizaTabela(){
		jspNotificacoes.remove(tbNotificacoes);
		linhaUm.remove(jspNotificacoes);
		//contato.remove(linhaContato);
		//abaContatos.remove(contato);
		consultarNotificacoes();
		linhaUm.doLayout();
		tbNotificacoes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) ""+tbNotificacoes.getValueAt(tbNotificacoes.getSelectedRow(), 0));
			}
		});
	}
}