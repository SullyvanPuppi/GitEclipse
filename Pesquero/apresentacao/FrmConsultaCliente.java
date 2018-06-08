package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
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

import negocios.CapturaTela;
import negocios.Cliente;

public class FrmConsultaCliente extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 715696193694829437L;
	FrmCadastroCliente formCadastroCliente;
	FrmCadastroClienteCompleto formCadastroClienteCompleto;
	FrmNovoCliente formNovoCliente;
	FrmRelatorioClientes formRelatoriosCliente;
	
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblCliente = null;
	private JFormattedTextField txtCliente = null;
	private JLabel lblNmCliente = null;
	private JTextField txtNmCliente = null;

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnBuscar = null;
	private JButton btnRemover = null;

	JTable tbClientes = null;
	JScrollPane jspClientes = null;
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
		tbClientes = new JTable(getTabela());
		jspClientes = new JScrollPane( tbClientes );
		jspClientes.setPreferredSize(new Dimension(500, 400));
		linhaDois.add(jspClientes);
		tbClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbClientes.getValueAt(tbClientes.getSelectedRow(), 0));
			}
		});
	}
	Cliente consultar = new Cliente();
	//----Consultar camada de negócios - Classe Funcionário
	public void consultarClientes(String cnpj, String nome){	
		consultar.consultarClientes(cnpj.trim(), nome.trim());
		this.tabela = consultar.getTabelaClientes();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhuma empresa Cliente cadastrada com o critério de pesquisa solicitado.","Consulta",1);
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
	//---Classe responsável por formatar márcaras de entrada de campos específicos
	private MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try {  
			mask = new MaskFormatter(mascara);  
			mask.setPlaceholderCharacter(' ');  
		}  
		catch (java.text.ParseException exc) { 

		}  
		return mask;  
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmConsultaCliente(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		consultarClientes("", "");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de empresas clientes");
		this.setResizable(false);

		lblCliente = new JLabel("CNPJ");
		txtCliente = new JFormattedTextField(setMascara("##.###.###/####-##"));
		lblNmCliente = new JLabel("Nome");
		txtNmCliente = new JTextField("",45);
		btnBuscar = new JButton("Buscar");
		
		lblDivisao = new JLabel("_________________________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnAlterar = new JButton("Abrir");
		btnConfirmar = new JButton("Novo");
		btnCancelar = new JButton("Cancelar");
		btnRemover = new JButton("Remover");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblCliente);
		linhaUm.add(txtCliente);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblNmCliente);
		linhaUm.add(txtNmCliente);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(btnBuscar);		
		linhaDois.add(jspClientes);
		
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

		Box linhas = Box.createVerticalBox();
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
		
		//--Ações
		tbClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbClientes.getValueAt(tbClientes.getSelectedRow(), 0));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				formNovoCliente = new FrmNovoCliente(desk, getPermissao());
				CapturaTela tela = new CapturaTela();
				formNovoCliente.setLocation((tela.getLarguraTela()/2)-(400/2), (tela.getAlturaTela()/2)-(100/2));
				formNovoCliente.setVisible(true);
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jspClientes.remove(tbClientes);
				linhaDois.remove(jspClientes);
				jContentPane.remove(linhaDois);
				consultarClientes(txtCliente.getText().trim(), txtNmCliente.getText().trim());
				linhaDois.doLayout();
				tbClientes.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						setSelecionado((String) tbClientes.getValueAt(tbClientes.getSelectedRow(), 0));
					}
				});
				JOptionPane.showMessageDialog(null, "Encontrado(s) "+consultar.registros()+" registro(s)","Consulta",1);
			}});
		btnAlterar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroCliente")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroClienteCompleto")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null,"Selecione uma empresa Cliente para poder removê-lo","Notificação",1);
					}else{
						setSelecionado((String) tbClientes.getValueAt(tbClientes.getSelectedRow(), 1));
						String tipo = (String) tbClientes.getValueAt(tbClientes.getSelectedRow(), 2);
						if(tipo.equals("Simplificado")){
							formCadastroCliente = new FrmCadastroCliente(desk, getPermissao(), 1, getSelecionado(), tipo);
							desk.add(formCadastroCliente, new Integer(2));
							formCadastroCliente.setLocation(50, 50);
							formCadastroCliente.show();
						}else if(tipo.equals("Completo")){
							formCadastroClienteCompleto = new FrmCadastroClienteCompleto(desk, getPermissao(), 1, getSelecionado(), tipo);
							desk.add(formCadastroClienteCompleto, new Integer(2));
							formCadastroClienteCompleto.setLocation(50, 50);
							formCadastroClienteCompleto.show();
						}			
					}		
				}else{
					JOptionPane.showMessageDialog(null,"Feche a janela aberta de cadastro de cliente","Notificação",2);
				}	
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um funcionário para poder removê-lo","Notificação",1);
				}else{
					String cnpj = (String) tbClientes.getValueAt(tbClientes.getSelectedRow(), 0);
					Cliente remover = new Cliente();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover esta empresa Cliente?\n\nEmpresa: "+getSelecionado(), "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(cnpj, getSelecionado());
						jspClientes.remove(tbClientes);
						linhaDois.remove(jspClientes);
						jContentPane.remove(linhaDois);
						consultarClientes("", "");
						linhaDois.doLayout();
						tbClientes.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								setSelecionado((String) tbClientes.getValueAt(tbClientes.getSelectedRow(), 0));
							}
						});
					}	
				}					
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Cliente gerar = new Cliente();
				gerar.gerarRelatorio(txtCliente.getText().trim(), txtNmCliente.getText().trim()).show();			
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
}