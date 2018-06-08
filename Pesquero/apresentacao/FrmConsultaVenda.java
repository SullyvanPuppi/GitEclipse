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

import negocios.Venda;

public class FrmConsultaVenda extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8152800840324075670L;

	FrmCadastroVenda formCadastroVenda;

	private JTextField txtTipo1 = null;
	private JTextField txtTipo2 = null;
	private JTextField txtTipo3 = null;
	private JFormattedTextField txtDe = null;
	private JFormattedTextField txtA = null;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblDivisao = null;
	private JButton btnRemover = null;
	private JButton btnCancelar = null;
	private JButton btnRelatorio = null;
	private JButton btnConfirmar = null;
	private JButton btnAlterar = null;
	private JButton btnBuscar = null;

	JTable tbVendas = null;
	JScrollPane jspVendas = null;
	Box linhaDois = Box.createHorizontalBox();
	Box linhas = Box.createVerticalBox();
	Box linhaCinco = Box.createHorizontalBox();

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
		tbVendas = new JTable(getTabela());
		jspVendas = new JScrollPane( tbVendas );
		jspVendas.setPreferredSize(new Dimension(500, 400));
		linhaCinco.add(jspVendas);
		tbVendas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbVendas.getValueAt(tbVendas.getSelectedRow(), 0));
			}
		});
	}

	//----Consultar camada de negócios - Classe Funcionário
	public void consultarVendas(String nmCliente, String nmRepresentada, String nmVendedor, String periodoDe, String periodoA){
		Venda consultar = new Venda();
		consultar.consultarVendas(nmCliente, nmRepresentada, nmVendedor, periodoDe, periodoA);
		this.tabela = consultar.getTabelaVendas();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhuma venda cadastrada com o critério de pesquisa solicitado.","Consulta",1);
			Object linha[] = new Object[6];
			linha[0] = "";
			linha[1] = "";
			linha[2] = "";
			linha[3] = "";
			linha[4] = "";
			linha[5] = "";
			tabela.addRow(linha);
			construirTabela();			
		}else{
			JOptionPane.showMessageDialog(null, "Encontrado(s) "+consultar.registros()+" registro(s)","Consulta",1);
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
	public FrmConsultaVenda(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		consultarVendas("", "","","/  /","/  /");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de vendas realizadas");
		this.setResizable(false);

		JLabel lblCriterioUm = new JLabel("Cliente");
		txtTipo1 = new JTextField();
		JLabel lblCriterioDois = new JLabel("Representada");
		txtTipo2 = new JTextField();
		JLabel lblCriterioTres = new JLabel("Vendedor");
		txtTipo3 = new JTextField();
		JLabel lblCriterioQuatro = new JLabel("Por período: ");
		JLabel lblCriterioCinco = new JLabel("De");
		txtDe = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblCriterioSeis = new JLabel("À");
		txtA = new JFormattedTextField(setMascara("##/##/####"));

		btnBuscar = new JButton("Buscar");

		JTable tbClientes = new JTable();
		JScrollPane jspClientes = new JScrollPane(tbClientes);
		jspClientes.setPreferredSize(new Dimension(500, 400));

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
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();


		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblCriterioUm);
		linhaUm.add(txtTipo1);
		linhaDois.add(lblCriterioDois);
		linhaDois.add(txtTipo2);
		linhaTres.add(lblCriterioTres);
		linhaTres.add(txtTipo3);
		linhaQuatro.add(lblCriterioQuatro);
		linhaQuatro.add(lblCriterioQuatro);
		linhaQuatro.add(lblCriterioCinco);
		linhaQuatro.add(txtDe);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblCriterioSeis);
		linhaQuatro.add(txtA);
		linhaQuatro.add(btnBuscar);

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
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);
		linhas.add(Box.createVerticalStrut(5));

		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();


		//---Ações
		tbVendas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbVendas.getValueAt(tbVendas.getSelectedRow(), 0));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {					
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroVenda")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					formCadastroVenda = new FrmCadastroVenda(desk, getPermissao(), 0,"");
					desk.add(formCadastroVenda, new Integer(2));
					formCadastroVenda.setLocation(100, 100);
					formCadastroVenda.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de venda","Notificação",1);
				}			
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jspVendas.remove(tbVendas);
				linhaCinco.remove(jspVendas);
				jContentPane.remove(linhaCinco);
				consultarVendas(txtTipo1.getText().trim(), txtTipo2.getText().trim(), txtTipo3.getText().trim(), txtDe.getText().trim(), txtA.getText().trim());
				linhaCinco.doLayout();
				tbVendas.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						setSelecionado((String) tbVendas.getValueAt(tbVendas.getSelectedRow(), 0));
					}
				});
			}});
		btnAlterar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				int countComponents = desk.countComponents();
				int c = countComponents;
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroVenda")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null,"Selecione uma venda para poder visualizar seus dados","Notificação", 1);
					}else{
						formCadastroVenda = new FrmCadastroVenda(desk, getPermissao(), 1, getSelecionado());
						desk.add(formCadastroVenda, new Integer(2));
						formCadastroVenda.setLocation(100, 100);
						formCadastroVenda.show();	
					}
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de funcionário","Notificação",1);
				}
		}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione uma venda para poder removê-lo","Notificação",1);
				}else{
					Venda remover = new Venda();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover esta venda?\n\nVenda: "+getSelecionado(), "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado());
						jspVendas.remove(tbVendas);
						linhaCinco.remove(jspVendas);
						jContentPane.remove(linhaCinco);
						consultarVendas("","","","/  /","/  /");
						linhaCinco.doLayout();
						tbVendas.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								setSelecionado((String) tbVendas.getValueAt(tbVendas.getSelectedRow(), 0));
							}
						});
					}	
				}					
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Venda gerar = new Venda();
				gerar.gerarRelatorio(getPermissao(), txtTipo1.getText().trim(), txtTipo2.getText().trim(), txtTipo3.getText().trim(), txtDe.getText().trim(), txtA.getText().trim(), "").show();
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
}
}