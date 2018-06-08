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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocios.CapturaTela;
import negocios.Funcionario;

public class FrmConsultaFuncionarios extends JInternalFrame {

	FrmRelatorioFuncionarios formRelatoriosFuncionario;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7244964163351702373L;
	FrmCadastroFuncionario formCadastroFuncionario;
	FrmCadastroFuncionarioCompleto formCadastroFuncionarioCompleto;
	FrmNovoFuncionario formNovoFuncionario;

	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblFuncionario = null;
	private JTextField txtFuncionario = null;
	private JLabel lblNmFuncionario = null;
	private JTextField txtNmFuncionario = null;

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnBuscar = null;
	private JButton btnRemover = null;
	private JPanel jContentPane = null;

	JTable tbFuncionarios = null;
	JScrollPane jspFuncionarios = null;
	Box linhaDois = Box.createHorizontalBox();
	Box linhas = Box.createVerticalBox();

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		
			btnRemover.setEnabled(true);
		}else if(getPermissao()==2){//Secretaria
			btnRemover.setEnabled(false);
		}else if(getPermissao()==3){//Convidado
			btnRemover.setEnabled(false);
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
		tbFuncionarios = new JTable(getTabela());
		jspFuncionarios = new JScrollPane( tbFuncionarios );
		jspFuncionarios.setPreferredSize(new Dimension(500, 400));
		linhaDois.add(jspFuncionarios);
		tbFuncionarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbFuncionarios.getValueAt(tbFuncionarios.getSelectedRow(), 0));
			}
		});
	}

	//----Consultar camada de negócios - Classe Funcionário
	public void consultarFuncionarios(String matricula, String nome){
		Funcionario consultar = new Funcionario();
		consultar.consultarFuncionarios(matricula, nome);
		this.tabela = consultar.getTabelaFuncionarios();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado com o critério de pesquisa solicitado.","Consulta",1);
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
	public FrmConsultaFuncionarios(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		consultarFuncionarios("", "");
		initialize();
		ajustaPermissao();
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

		lblFuncionario = new JLabel("Matrícula");
		txtFuncionario = new JTextField("",10);
		lblNmFuncionario = new JLabel("Nome");
		txtNmFuncionario = new JTextField("",45);
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

		linhaUm.add(lblFuncionario);
		linhaUm.add(txtFuncionario);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblNmFuncionario);
		linhaUm.add(txtNmFuncionario);
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

		//--Ações
		tbFuncionarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbFuncionarios.getValueAt(tbFuncionarios.getSelectedRow(), 0));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				formNovoFuncionario = new FrmNovoFuncionario(desk, getPermissao());
				CapturaTela tela = new CapturaTela();
				formNovoFuncionario.setLocation((tela.getLarguraTela()/2)-(400/2), (tela.getAlturaTela()/2)-(100/2));
				formNovoFuncionario.setVisible(true);
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jspFuncionarios.remove(tbFuncionarios);
				linhaDois.remove(jspFuncionarios);
				jContentPane.remove(linhaDois);
				consultarFuncionarios(txtFuncionario.getText().trim(), txtNmFuncionario.getText().trim());
				linhaDois.doLayout();
				tbFuncionarios.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						setSelecionado((String) tbFuncionarios.getValueAt(tbFuncionarios.getSelectedRow(), 0));
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
					if(x.equals("FrmCadastroFuncionario")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroFuncionarioCompleto")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					String tipo = (String) tbFuncionarios.getValueAt(tbFuncionarios.getSelectedRow(), 2);
					if(tipo.equals("Simplificado")){
						formCadastroFuncionario = new FrmCadastroFuncionario(desk, getPermissao(), 1, getSelecionado(), tipo);
						desk.add(formCadastroFuncionario, new Integer(2));
						formCadastroFuncionario.setLocation(100, 100);
						formCadastroFuncionario.show();	
					}else if(tipo.equals("Completo")){
						formCadastroFuncionarioCompleto = new FrmCadastroFuncionarioCompleto(desk, getPermissao(), 1, getSelecionado(), tipo);
						desk.add(formCadastroFuncionarioCompleto, new Integer(2));
						formCadastroFuncionarioCompleto.setLocation(100, 100);
						formCadastroFuncionarioCompleto.show();
					}	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de funcionário","Notificação",1);
				}
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um funcionário para poder removê-lo","Notificação",1);
				}else{
					String nome = (String) tbFuncionarios.getValueAt(tbFuncionarios.getSelectedRow(), 1);
					Funcionario remover = new Funcionario();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover este funcionário?\n\nFuncionário: "+nome, "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado(), nome);
						jspFuncionarios.remove(tbFuncionarios);
						linhaDois.remove(jspFuncionarios);
						jContentPane.remove(linhaDois);
						consultarFuncionarios("", "");
						linhaDois.doLayout();
						setSelecionado("");
						tbFuncionarios.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								setSelecionado((String) tbFuncionarios.getValueAt(tbFuncionarios.getSelectedRow(), 0));
							}
						});
					}	
				}					
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Funcionario gerar = new Funcionario();
				gerar.gerarRelatorio(txtFuncionario.getText().trim(), txtNmFuncionario.getText().trim()).show();
			}});
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
