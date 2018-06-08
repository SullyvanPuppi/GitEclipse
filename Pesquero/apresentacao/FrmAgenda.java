package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import negocios.Contato;

public class FrmAgenda extends JInternalFrame {

/**
	 * 
	 */
	private static final long serialVersionUID = -4938145409142215705L;

	//	--Instanciação do formulário de contato
	FrmCadastroContato formCadastroContato;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnRemover = null;
	private JButton btnBuscar = null;
	private JTextField txtPesquisa = null;
	private JComboBox cmbClasse = null;

	JTable tbContatos = null;
	JScrollPane jspContatos = null;
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
		tbContatos = new JTable(getTabela());
		jspContatos = new JScrollPane( tbContatos );
		jspContatos.setPreferredSize(new Dimension(500, 400));
		linhaDois.add(jspContatos);
	}
	Contato consultar = new Contato();
	//----Consultar camada de negócios - Classe Funcionário
	public void consultarContatos(String nmContato, String categoria){	
		consultar.consultarContatos(nmContato, categoria);
		this.tabela = consultar.getTabelaContatos();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhuma contato cadastrada com o critério de pesquisa solicitado.","Consulta",1);
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
	//---Classe responsável por formatar márcaras de entrada de campos específicos
	@SuppressWarnings("unused")
	private MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try {  
			mask = new MaskFormatter(mascara);  
			mask.setPlaceholderCharacter('_');  
		}  
		catch (java.text.ParseException exc) { 

		}  
		return mask;  
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmAgenda(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		consultarContatos("", "");
		//consultarContatos(txtPesquisa.getText(), (String) cmbClasse.getSelectedItem());
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Agenda de contatos");
		JLabel lblPesquisa = new JLabel("Contato");
		txtPesquisa = new JTextField("",45);
		JLabel lblPesquisaPor = new JLabel("Categoria");
		String[] classes = new String[4];
		classes[0] = "Funcionário";
		classes[1] = "Representada";
		classes[2] = "Cliente";
		classes[3] = "Outros";
		cmbClasse = new JComboBox(classes);
		btnBuscar = new JButton("Buscar");
		btnBuscar.setToolTipText("Pesquisar registros");

		lblDivisao = new JLabel("_________________________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAlterar = new JButton("Abrir");
		btnAlterar.setToolTipText("Exibir item selecionado");
		btnConfirmar = new JButton("Novo");
		btnConfirmar.setToolTipText("Iniciar novo cadastro");
		btnRemover = new JButton("Remover");
		btnRemover.setToolTipText("Remover item selecionado");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblPesquisa);
		linhaUm.add(txtPesquisa);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblPesquisaPor);
		linhaUm.add(cmbClasse);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(btnBuscar);		

		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnRemover);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAlterar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);

		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//-----Ações
		tbContatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 2));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {					
				formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 0, "", "", "", "Outros","");
				formCadastroContato.setLocation(300,300);
				formCadastroContato.show();
				jspContatos.remove(tbContatos);
				linhaDois.remove(jspContatos);
				jContentPane.remove(linhaDois);
				consultarContatos("", "");
				linhaDois.doLayout();
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jspContatos.remove(tbContatos);
				linhaDois.remove(jspContatos);
				jContentPane.remove(linhaDois);
				consultarContatos(txtPesquisa.getText(), (String) cmbClasse.getSelectedItem());
				linhaDois.doLayout();
				JOptionPane.showMessageDialog(null, "Encontrado(s) "+consultar.registros()+" registro(s)","Consulta",1);
			}});
		btnAlterar.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder visualizar seus dados","Notificação", 1);
				}else{
					String nmEmpresa = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0);
					String nmContato = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 2);
					String categoria = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 1);
					formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 1, nmContato.trim(), nmEmpresa, "", categoria, "");
					formCadastroContato.setLocation(300,300);
					formCadastroContato.show();
					jspContatos.remove(tbContatos);
					linhaDois.remove(jspContatos);
					jContentPane.remove(linhaDois);
					consultarContatos("", "");
					linhaDois.doLayout();
				}				
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder removê-lo","Notificação",1);
				}else{	
					String nmEmpresa = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0);
					String categoria = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 1);
					Contato remover = new Contato();
					if(categoria.equals("Outros")){
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Tem certeza que deseja remover este contato?\n\n"+getSelecionado(), "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							remover.remover(getSelecionado(), nmEmpresa, "", "Outros");
							jspContatos.remove(tbContatos);
							linhaDois.remove(jspContatos);
							jContentPane.remove(linhaDois);
							consultarContatos("", "");
							linhaDois.doLayout();
						}	
					}else{
						JOptionPane.showMessageDialog(null,"Não é possível remover contatos por este formulário da categoria de:\nfuncionários, empresas representadas e clientes\npara alterações deste tipo abra o formulário correspondente","Notificação",1);
					}	
				}				
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Contato gerar = new Contato();
				gerar.gerarRelatorio(txtPesquisa.getText(), (String) cmbClasse.getSelectedItem()).show();
			}});	
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
}
