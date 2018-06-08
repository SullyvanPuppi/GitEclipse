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

import negocios.Anotacao;

public class FrmAnotacoes extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1455776264491311775L;

	FrmAnotacao formAnotacao;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblDivisao = null;
	private JButton btnNovo = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRemover = null;
	JTable tbMsgs = null;
	JScrollPane jspMsgs = null;
	Box linhaDois = Box.createHorizontalBox();
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
		tbMsgs = new JTable(getTabela());
		jspMsgs = new JScrollPane( tbMsgs );
		jspMsgs.setPreferredSize(new Dimension(500, 400));
		linhaDois.add(jspMsgs);
	}

	//----Consultar camada de negócios - Classe Funcionário
	public void consultarMsgs(){
		Anotacao consultar = new Anotacao();
		consultar.consultarMsgs(getLogin());
		this.tabela = consultar.getTabelaMsgs();
		if(tabela.getRowCount()<=0){
			Object linha[] = new Object[4];
			linha[0] = "";
			linha[1] = "";
			linha[2] = "";
			linha[3] = "";
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
	public FrmAnotacoes(JDesktopPane desktop, int permissao, String login) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.loginAtual = login;
		consultarMsgs();
		initialize();		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Mensagens recebidas");
		this.setResizable(false);

		lblDivisao = new JLabel("_________________________________________________________________________________________________________");
		btnAlterar = new JButton("Abrir");
		btnAlterar.setToolTipText("Exibir item selecionado");
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
		linhaTreze.add(btnAlterar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnNovo);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		linhas.add(linhaDois);
		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//----Ações
		tbMsgs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbMsgs.getValueAt(tbMsgs.getSelectedRow(), 0));
			}
		});
		btnAlterar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione uma mensagem para poder visualizá-la","Notificação",1);
				}else{
					formAnotacao = new FrmAnotacao(desk, getPermissao(), 1, getLogin(), getSelecionado());
					formAnotacao.setLocation(300, 180);
					formAnotacao.show();
					atualizaRecados();
				}				
			}});
		btnNovo.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				formAnotacao = new FrmAnotacao(desk, getPermissao(), 0, getLogin(), "");
				formAnotacao.setLocation(300, 180);
				formAnotacao.show();
				atualizaRecados();
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione uma mensagem para poder removê-la","Notificação",1);
				}else{
					setSelecionado((String) tbMsgs.getValueAt(tbMsgs.getSelectedRow(), 0));
					Anotacao remover = new Anotacao();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover esta mensagem?", "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getLogin(), getSelecionado());
						atualizaRecados();
					}	
				}					
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
	private void atualizaRecados(){
		jspMsgs.remove(tbMsgs);
		linhaDois.remove(jspMsgs);
		jContentPane.remove(linhaDois);
		consultarMsgs();
		linhaDois.doLayout();
		tbMsgs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbMsgs.getValueAt(tbMsgs.getSelectedRow(), 0));
			}
		});
	}
}