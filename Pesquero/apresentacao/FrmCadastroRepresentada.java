package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import negocios.Contato;
import negocios.Representada;

public class FrmCadastroRepresentada extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3290207413438265584L;

	//--Instanciação do formulário de contato
	FrmCadastroContato formCadastroContato;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	JTabbedPane abaPrincipal = null;
	JPanel abaDados = null;
	JPanel abaContatos = null;

	private JFormattedTextField txtNmRepresentada = null;
	private JFormattedTextField txtCnpjRepresentada = null;
	private JComboBox cmbSegmento = null;
	private JTextArea txtDsAtividade = null;
	private JFormattedTextField txtRepresentadaDesde = null;
	private JFormattedTextField txtDtCadastro = null;
	private JFormattedTextField txtTelRepresentada = null;
	private JFormattedTextField txtFaxRepresentada = null;
	private JFormattedTextField txtEmail = null;
	private JTextField txtSite = null;
	private JTextArea txtObs = null;
	private JTable tbContatos = null;
	private JScrollPane jspContatos = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnAdicionarContato = null;
	private JButton btnAlterarContato = null;
	private JButton btnRemoverContato = null;
	private JButton btnRelContatos = null;

	private Box contato = Box.createVerticalBox();
	private Box linhaContato = Box.createHorizontalBox();

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
	private boolean status = false;

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
		jspContatos.setPreferredSize(new Dimension(500, 500));
		contato.add(jspContatos);
		contato.add(linhaContato);
	}

	//----Consultar camada de negócios - Classe Funcionário
	public void consultarContatos(String nmEmpresa){
		Contato consultar = new Contato();
		consultar.consultarContatosEmpresa(nmEmpresa.trim());
		this.tabela = consultar.getTabelaContatosEmpresa();	
		if(tabela.getRowCount()<=0){
			Object linha[] = new Object[2];
			linha[0] = "";
			linha[1] = "";
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
	public void ajustaDados(String cnpj, String tipo){
		Representada consultar = new Representada();
		consultar.consultarRepresentada(cnpj, tipo);
		txtNmRepresentada.setText(consultar.getTxtNmRepresentada());
		txtCnpjRepresentada.setText(consultar.getTxtCnpjRepresentada());
		cmbSegmento.setSelectedItem((String) consultar.getCmbSegmento());
		txtDsAtividade.setText(consultar.getTxtDsAtividade());
		txtRepresentadaDesde.setText(consultar.getTxtRepresentadaDesde());
		txtDtCadastro.setText(consultar.getTxtDtCadastro());
		txtTelRepresentada.setText(consultar.getTxtTelRepresentada());
		txtFaxRepresentada.setText(consultar.getTxtFaxRepresentada());
		txtEmail.setText(consultar.getTxtEmail());
		txtSite.setText(consultar.getTxtSite());
		txtObs.setText(consultar.getTxtObs());
	}
	private int op;

	public int getOp(){
		return op;
	}
	private void desabilitaCampos(){
		txtNmRepresentada.setEditable(false);
		txtCnpjRepresentada.setEditable(false);
		cmbSegmento.setEnabled(false);
		txtDsAtividade.setEditable(false);
		txtRepresentadaDesde.setEditable(false);
		txtDtCadastro.setEditable(false);
		txtTelRepresentada.setEditable(false);
		txtFaxRepresentada.setEditable(false);
		txtEmail.setEditable(false);
		txtSite.setEditable(false);
		txtObs.setEditable(false);
	}
	private void habilitaCampos(){
		cmbSegmento.setEnabled(true);
		txtDsAtividade.setEditable(true);
		txtRepresentadaDesde.setEditable(true);
		txtTelRepresentada.setEditable(true);
		txtFaxRepresentada.setEditable(true);
		txtEmail.setEditable(true);
		txtSite.setEditable(true);
		txtObs.setEditable(true);
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroRepresentada(JDesktopPane desktop, int permissao, int op, String nmEmpresa, String tipo) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		consultarContatos(nmEmpresa);
		initialize();
		if(getOp()==0){//Novo cadastro
			Representada novo = new Representada();
			txtDtCadastro.setText(novo.getHoje());
			txtDtCadastro.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnConfirmar.setEnabled(true);
		}else{//Exibir dados
			status = true;
			ajustaDados(nmEmpresa, tipo);
			desabilitaCampos();
			txtDtCadastro.setEnabled(false);
			btnAlterar.setEnabled(true);
			btnConfirmar.setEnabled(false);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de empresa representada simplificado");
		this.setResizable(false);

		abaPrincipal = new JTabbedPane();
		abaDados = new JPanel();
		abaContatos = new JPanel();

		JLabel lblNmRepresentada = new JLabel("Nome da empresa");
		txtNmRepresentada = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblCnpjRepresentada = new JLabel("CNPJ");
		txtCnpjRepresentada = new JFormattedTextField(setMascara("##.###.###/####-##"));
		JLabel lblSegmento = new JLabel("Segmento");
		cmbSegmento = new JComboBox(getSegmentos());
		JLabel lblDsAtividade = new JLabel("Descrição da atividade");
		txtDsAtividade = new JTextArea();
		JScrollPane jspDsAtividade = new JScrollPane(txtDsAtividade);
		jspDsAtividade.setPreferredSize(new Dimension(40, 100));
		JLabel lblRepresentadaDesde = new JLabel("Representada desde");
		txtRepresentadaDesde = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblDtCadastro = new JLabel("Cadastro");
		txtDtCadastro = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblTelRepresentada = new JLabel("Telefone");
		txtTelRepresentada = new JFormattedTextField(setMascara("(##)####-####"));
		JLabel lblFaxRepresentada = new JLabel("Fax");
		txtFaxRepresentada = new JFormattedTextField(setMascara("(##)####-####"));
		JLabel lblEmail = new JLabel("E-mail");
		txtEmail = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblSite = new JLabel("Site");
		txtSite = new JTextField("",30);
		//txtSite = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblObs = new JLabel("Observações");
		txtObs = new JTextArea();
		JScrollPane jspTxtObs = new JScrollPane(txtObs);
		jspTxtObs.setPreferredSize(new Dimension(40, 100));

		btnAdicionarContato = new JButton("Novo");
		btnAdicionarContato.setToolTipText("Adicionar contato da empresa representada");
		btnAlterarContato = new JButton("Abrir");
		btnAlterarContato.setToolTipText("Exibir item selecionado");
		btnRemoverContato = new JButton("Remover");
		btnRemoverContato.setToolTipText("Remover item selecionado");
		btnRelContatos = new JButton("Relatório");
		btnRelContatos.setToolTipText("Gerar relatório de contatos desta empresa reprsentada");
		
		lblDivisao = new JLabel("________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setToolTipText("Alterar dados cadastrados");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar dados para cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar cadastro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTresA = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();
		Box linhaNove = Box.createHorizontalBox();

		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaContato.add(btnRelContatos);
		linhaContato.add(btnAlterarContato);
		linhaContato.add(btnAdicionarContato);
		linhaContato.add(btnRemoverContato);

		linhaUm.add(lblNmRepresentada);
		linhaUm.add(txtNmRepresentada);
		linhaDois.add(lblCnpjRepresentada);
		linhaDois.add(txtCnpjRepresentada);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(lblSegmento);
		linhaDois.add(cmbSegmento);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaTresA.add(lblDsAtividade);
		linhaTres.add(jspDsAtividade);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblRepresentadaDesde);
		linhaQuatro.add(txtRepresentadaDesde);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblDtCadastro);
		linhaQuatro.add(txtDtCadastro);
		linhaCinco.add(lblTelRepresentada);
		linhaCinco.add(txtTelRepresentada);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblFaxRepresentada);
		linhaCinco.add(txtFaxRepresentada);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaSeis.add(lblEmail);
		linhaSeis.add(txtEmail);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(lblSite);
		linhaSeis.add(txtSite);
		linhaSete.add(lblObs);
		linhaOito.add(lblObs);
		linhaNove.add(jspTxtObs);

		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
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
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTresA);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);	
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);

		Box linhaA = Box.createVerticalBox();

		abaPrincipal.addTab("Dados da empresa", null, abaDados, null);
		abaPrincipal.addTab("Contatos", null, abaContatos, null);

		abaDados.add(linhas);
		abaContatos.add(contato);

		linhaA.add(abaPrincipal);
		linhaA.add(linhaDoze);
		linhaA.add(Box.createVerticalStrut(10));
		linhaA.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhaA);
		getContentPane().add(jContentPane);
		pack();

		//--Ações 
		txtRepresentadaDesde.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().equals("  /  /    ")){
					txtRepresentadaDesde.setText("");
					return ((JFormattedTextField) txtRepresentadaDesde).isEditValid();
				}else{
					Representada consultar = new Representada();
					if(consultar.validaDataCadastro(txtRepresentadaDesde.getText(), txtDtCadastro.getText())>0){
						txtRepresentadaDesde.setText("");
						txtRepresentadaDesde.grabFocus();
						return false;
					}else{
						return ((JFormattedTextField) txtRepresentadaDesde).isEditValid();
					}
				}				
			}
		});
		tbContatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Representada cadastrar = new Representada();
				cadastrar.ajustaDadosSimples(txtNmRepresentada.getText().trim(), txtCnpjRepresentada.getText().trim(), (String) cmbSegmento.getSelectedItem(), txtDsAtividade.getText().trim(), txtRepresentadaDesde.getText().trim(), txtDtCadastro.getText().trim(), txtTelRepresentada.getText().trim(), txtFaxRepresentada.getText().trim(), txtEmail.getText().trim(), txtSite.getText().trim(), txtObs.getText().trim());
				if(cadastrar.validarSimples()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrarSimples();
						desabilitaCampos();
						btnAlterar.setEnabled(true);
						btnConfirmar.setEnabled(false);
						status = true;
					}else{
						if(op==1){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja alterar as informações da empresa representada?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								cadastrar.alterarSimples();
								desabilitaCampos(); 
								btnAlterar.setEnabled(true);
								btnConfirmar.setEnabled(false);
							}	
						}else{
							JOptionPane.showMessageDialog(null, "Empresa representada ja está cadastrada","Erro",2);
						}						
					}
				}
			}});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitaCampos();
				btnAlterar.setEnabled(false);
				btnConfirmar.setEnabled(true);
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Representada gerar = new Representada();
				gerar.gerarRelatorio(txtCnpjRepresentada.getText().trim(), txtNmRepresentada.getText().trim()).show();
			}});
		btnRelContatos.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Representada gerar = new Representada();
				gerar.gerarRelatorioContatos(txtCnpjRepresentada.getText().trim(), txtNmRepresentada.getText().trim()).show();
			}});
		btnAdicionarContato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(status){
					formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 0, "", txtNmRepresentada.getText(), txtCnpjRepresentada.getText(), "Representada","");
					formCadastroContato.setLocation(300,300);
					formCadastroContato.show();
					atualizaContatos();	
				}else{
					JOptionPane.showMessageDialog(null,"Cadastre primeiro a empresa representada antes de adicionar contatos à ela.","Notificação", 1);
				}				
			}});
		btnAlterarContato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder visualizar seus dados","Notificação", 1);
				}else{
					formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 1, getSelecionado().trim(), txtNmRepresentada.getText().trim(), txtCnpjRepresentada.getText().trim(), "Representada","");
					formCadastroContato.setLocation(300,300);
					formCadastroContato.show();	
					atualizaContatos();
				}				
			}});
		btnRemoverContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder removê-lo","Notificação",1);
				}else{
					String nome = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0);
					Contato remover = new Contato();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover este contato?\n\nContato: "+nome, "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado(), txtNmRepresentada.getText().trim(), txtCnpjRepresentada.getText().trim(), "Representada");
						atualizaContatos();
					}	
				}					
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});

	}
	private void atualizaContatos(){
		jspContatos.remove(tbContatos);
		contato.remove(jspContatos);
		//contato.remove(linhaContato);
		//abaContatos.remove(contato);
		consultarContatos(txtNmRepresentada.getText().trim());
		abaContatos.add(contato);
		contato.doLayout();
		abaContatos.doLayout();
		//abaPrincipal.doLayout();
		//jContentPane.doLayout();
		tbContatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0));
			}
		});
	}
	public DefaultComboBoxModel getSegmentos(){
		DefaultComboBoxModel segmentos = new DefaultComboBoxModel();
		//Lista de segmentos
		segmentos.addElement(new String("--------------------------------"));
		segmentos.addElement(new String("00 - Não informado"));
		segmentos.addElement(new String("01 - Alimentos"));
		segmentos.addElement(new String("02 - Automobilística"));
		segmentos.addElement(new String("03 - Bebidas"));
		segmentos.addElement(new String("04 - Celulose"));
		segmentos.addElement(new String("05 - Cimentos"));
		segmentos.addElement(new String("06 - Siderurgia"));
		segmentos.addElement(new String("07 - Escadas rolantes"));
		segmentos.addElement(new String("08 - Fertilizantes"));
		segmentos.addElement(new String("09 - Fumageira"));
		segmentos.addElement(new String("10 - Fundição"));
		segmentos.addElement(new String("11 - Graneiro"));
		segmentos.addElement(new String("12 - Madeira"));
		segmentos.addElement(new String("13 - Mineração"));
		segmentos.addElement(new String("14 - Químico"));
		segmentos.addElement(new String("15 - Diversos"));
		segmentos.addElement(new String("16 - Revenda"));
		segmentos.addElement(new String("17 - Óleos"));
		segmentos.addElement(new String("18 - Rações"));
		segmentos.addElement(new String("19 - Gás"));
		segmentos.addElement(new String("20 - Usina"));
		return segmentos;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
