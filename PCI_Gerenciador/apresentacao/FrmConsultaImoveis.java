package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
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

//import negocios.CapturaTela;
import negocios.Imovel;

public class FrmConsultaImoveis extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7244964163351702373L;
	FrmCadastroImovel formCadastroFuncionario;
	FrmCadastroImovel formCadastroImovel;

	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	private JLabel lblImovel = null;
	private JTextField txtImovel = null;
	private JLabel lblNmImovel = null;
	private JTextField txtNmImovel = null;
	private JLabel lblTipo = null;
	private JComboBox cmbTipo = new JComboBox();

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnBuscar = null;
	private JButton btnRemover = null;
	private JPanel jContentPane = null;

	JTable tbImoveis = null;
	JScrollPane jspImoveis = null;
	Box linhaDois = Box.createHorizontalBox();
	Box linhas = Box.createVerticalBox();

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
		tbImoveis = new JTable(getTabela());
		jspImoveis = new JScrollPane( tbImoveis );
		jspImoveis.setPreferredSize(new Dimension(500, 400));
		linhaDois.add(jspImoveis);
		tbImoveis.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbImoveis.getValueAt(tbImoveis.getSelectedRow(), 0));
			}
		});
	}

	//----Consultar camada de negócios - Classe Funcionário
	public void consultarImoveis(String matricula, String nome, String tipo){
		Imovel consultar = new Imovel();
		consultar.consultarImoveis(matricula, nome, tipo);
		this.tabela = consultar.getTabelaImoveis();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhum imóvel cadastrado com o critério de pesquisa solicitado.","Consulta",1);
			Object linha[] = new Object[3];
			linha[0] = "";
			linha[1] = "";
			linha[2] = "";
			tabela.addRow(linha);
			construirTabela();			
		}else{
			JOptionPane.showMessageDialog(null, "Encontrado(s) "+consultar.registros()+" registro(s)","Consulta",1);
			construirTabela();			
		}
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmConsultaImoveis(JDesktopPane desktop) {
		super();
		this.desk = desktop;
		consultarImoveis("", "", "--");
		initialize();
	}
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * 
	 */
	private void initialize() {
		this.setTitle("Cadastro de funcionários");
		this.setResizable(false);

		lblImovel = new JLabel("Código");
		txtImovel = new JTextField("",10);
		lblNmImovel = new JLabel("Nome");
		txtNmImovel = new JTextField("",45);
		lblTipo = new JLabel("Tipo de imóvel");
		cmbTipo = new JComboBox(getTipo());
		cmbTipo.setEditable(false);
		btnBuscar = new JButton("Buscar");

		lblDivisao = new JLabel("_________________________________________________________________________________________________________");
		btnAlterar = new JButton("Abrir");
		btnConfirmar = new JButton("Novo");
		btnCancelar = new JButton("Cancelar");
		btnRemover = new JButton("Remover");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblImovel);
		linhaUm.add(txtImovel);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblNmImovel);
		linhaUm.add(txtNmImovel);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblTipo);
		linhaUm.add(cmbTipo);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(btnBuscar);	

		linhaDoze.add(lblDivisao);
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

		//--Ações
		tbImoveis.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbImoveis.getValueAt(tbImoveis.getSelectedRow(), 0));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				formCadastroImovel = new FrmCadastroImovel(desk, 0, "");
				desk.add(formCadastroImovel, new Integer(2));
				//formCadastroImovel.setLocation(getPosX(), getPosY());
				formCadastroImovel.show();	
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jspImoveis.remove(tbImoveis);
				linhaDois.remove(jspImoveis);
				jContentPane.remove(linhaDois);
				consultarImoveis(txtImovel.getText().trim(), txtNmImovel.getText().trim(), (String) cmbTipo.getSelectedItem());
				linhaDois.doLayout();
				tbImoveis.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						setSelecionado((String) tbImoveis.getValueAt(tbImoveis.getSelectedRow(), 0));
					}
				});
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
					if(x.equals("FrmCadastroImovel")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroImovel")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					if(getSelecionado().equals("") || getSelecionado().equals(null)){
						JOptionPane.showMessageDialog(null,"Selecione um imóvel para poder exibi-lo","Notificação",1);
					}else{
						formCadastroImovel = new FrmCadastroImovel(desk, 1, getSelecionado());
						desk.add(formCadastroImovel, new Integer(2));
						formCadastroImovel.setLocation(100, 100);
						formCadastroImovel.show();	
					}	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de imóvel","Notificação",1);
				}
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um imóvel para poder removê-lo","Notificação",1);
				}else{
					String nome = (String) tbImoveis.getValueAt(tbImoveis.getSelectedRow(), 1);
					Imovel remover = new Imovel();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover este imóvel?\n\nImóvel: "+nome, "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado(), nome);
						jspImoveis.remove(tbImoveis);
						linhaDois.remove(jspImoveis);
						jContentPane.remove(linhaDois);
						consultarImoveis("", "", "--");
						linhaDois.doLayout();
						setSelecionado("");
						tbImoveis.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								setSelecionado((String) tbImoveis.getValueAt(tbImoveis.getSelectedRow(), 0));
							}
						});
					}	
				}					
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
	public DefaultComboBoxModel getTipo(){
		DefaultComboBoxModel tipos = new DefaultComboBoxModel();
		//Lista de tipos de imóveis
		tipos.addElement(new String("--"));
		tipos.addElement(new String("Lançamento"));
		tipos.addElement(new String("Destaque"));
		tipos.addElement(new String("Pronto para morar"));
		tipos.addElement(new String("Em construção"));		
		return tipos;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
