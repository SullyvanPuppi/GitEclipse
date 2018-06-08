package apresentacao;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import negocios.Imovel;

public class FrmCadastroImovel extends JInternalFrame{

	/**
	 * Autor: Sullyvan Puppi
	 */
	private static final long serialVersionUID = -2646567386157691456L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Campos do formulário
	private JTextField txtCdImovel = null;

	private JTextField txtNmImovel = null;

	private JTextField txtNmEndereco = null;

	private JTextField txtNmBairro = null;

	private JTextField txtNmCidade = null;

	private JComboBox cmbSgEstado = null;

	private JTextField txtNmTamanho = null;

	private JTextField txtDormitorios = null;

	private JTextField txtDtInicio = null;

	private JTextField txtDtEntrega = null;

	private JTextArea txtDsImovel = null;

	private JTextField txtvlImovel = null;

	private JTextArea txtDsFormaPagamento = null;

	private JTextField txtDsFoto1 = null;

	private JTextField txtDsFoto2 = null;

	private JTextField txtDsFoto3 = null;

	private JTextField txtDsFoto4 = null;

	private JTextField txtDsFoto5 = null;

	private JComboBox cmbTipo = null;

	private JButton btnConfirmar = null;

	private JButton btnAlterar = null;

	private JButton btnCancelar = null;
	
	private JButton btnFoto1 = null;
	
	private JButton btnFoto2 = null;
	
	private JButton btnFoto3 = null;
	
	private JButton btnFoto4 = null;
	
	private JButton btnFoto5 = null;

	private JButton btnFoto1X = null;
	
	private JButton btnFoto2X = null;
	
	private JButton btnFoto3X = null;
	
	private JButton btnFoto4X = null;
	
	private JButton btnFoto5X = null;

	//Selecionar foto 1
	private String caminhoFoto1 = "";
	
	public String getCaminhoFoto1(){
		return this.caminhoFoto1;
	}
	public void setCaminhoFoto1(String caminho){
		this.caminhoFoto1 = caminho;
	}
	private String nmFoto1 = null;
	
	public void setNmFoto1(String nmFoto){
		this.nmFoto1 = nmFoto;
	}
	public String getNmFoto1(){
		return this.nmFoto1;
	}
//	Selecionar foto 2
	private String caminhoFoto2 = "";
	
	public String getCaminhoFoto2(){
		return this.caminhoFoto2;
	}
	public void setCaminhoFoto2(String caminho){
		this.caminhoFoto2 = caminho;
	}
	private String nmFoto2 = null;
	
	public void setNmFoto2(String nmFoto){
		this.nmFoto2 = nmFoto;
	}
	public String getNmFoto2(){
		return this.nmFoto2;
	}
//	Selecionar foto 3
	private String caminhoFoto3 = "";
	
	public String getCaminhoFoto3(){
		return this.caminhoFoto3;
	}
	public void setCaminhoFoto3(String caminho){
		this.caminhoFoto3 = caminho;
	}
	private String nmFoto3 = null;
	
	public void setNmFoto3(String nmFoto){
		this.nmFoto3 = nmFoto;
	}
	public String getNmFoto3(){
		return this.nmFoto3;
	}
//	Selecionar foto 4
	private String caminhoFoto4 = "";
	
	public String getCaminhoFoto4(){
		return this.caminhoFoto4;
	}
	public void setCaminhoFoto4(String caminho){
		this.caminhoFoto4 = caminho;
	}
	private String nmFoto4 = null;
	
	public void setNmFoto4(String nmFoto){
		this.nmFoto4 = nmFoto;
	}
	public String getNmFoto4(){
		return this.nmFoto4;
	}
//	Selecionar foto 5
	private String caminhoFoto5 = "";
	
	public String getCaminhoFoto5(){
		return this.caminhoFoto5;
	}
	public void setCaminhoFoto5(String caminho){
		this.caminhoFoto5 = caminho;
	}
	private String nmFoto5 = null;
	
	public void setNmFoto5(String nmFoto){
		this.nmFoto5 = nmFoto;
	}
	public String getNmFoto5(){
		return this.nmFoto5;
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
	private int op;

	public int getOp(){
		return op;
	}
	public void ajustaDados(String matricula){
		Imovel consultar = new Imovel();
		consultar.consultarImovel(matricula);
		txtCdImovel.setText(consultar.getCdImovel());
		txtNmImovel.setText(consultar.getNmImovel());
		txtNmEndereco.setText(consultar.getNmEndereco());
		txtNmBairro.setText(consultar.getNmBairro());
		txtNmCidade.setText(consultar.getNmCidade());
		cmbSgEstado.setSelectedItem(consultar.getSgEstado());
		txtNmTamanho.setText(consultar.getNmTamanho());
		txtDormitorios.setText(""+consultar.getDormitorios());
		txtDtInicio.setText(consultar.getDtInicio());
		txtDtEntrega.setText(consultar.getDtEntrega());
		txtDsImovel.setText(consultar.getDsImovel());
		txtvlImovel.setText(consultar.getVlImovel());
		txtDsFormaPagamento.setText(consultar.getDsFormaPagamento());
		txtDsFoto1.setText(consultar.getDsFoto1());
		txtDsFoto2.setText(consultar.getDsFoto2());
		txtDsFoto3.setText(consultar.getDsFoto3());
		txtDsFoto4.setText(consultar.getDsFoto4());
		txtDsFoto5.setText(consultar.getDsFoto5());
		cmbTipo.setSelectedItem(""+consultar.getNmTipo());
	}
	public void desabilitaCampos(){
		txtCdImovel.setEditable(false);
		txtNmImovel.setEditable(false);
		txtNmEndereco.setEditable(false);
		txtNmBairro.setEditable(false);
		txtNmCidade.setEditable(false);
		cmbSgEstado.setEnabled(false);
		txtNmTamanho.setEditable(false);
		txtDormitorios.setEditable(false);
		txtvlImovel.setEnabled(false);
		txtDtInicio.setEditable(false);
		txtDtEntrega.setEditable(false);
		txtDsImovel.setEditable(false);
		txtDsFormaPagamento.setEditable(false);
		cmbTipo.setEnabled(false);
		btnFoto1.setEnabled(false);
		btnFoto2.setEnabled(false);
		btnFoto3.setEnabled(false);
		btnFoto4.setEnabled(false);
		btnFoto5.setEnabled(false);
		btnFoto1X.setEnabled(false);
		btnFoto2X.setEnabled(false);
		btnFoto3X.setEnabled(false);
		btnFoto4X.setEnabled(false);
		btnFoto5X.setEnabled(false);
	}
	public void habilitaCampos(){
		txtCdImovel.setEditable(true);
		txtNmImovel.setEditable(true);
		txtNmEndereco.setEditable(true);
		txtNmBairro.setEditable(true);
		txtNmCidade.setEditable(true);
		cmbSgEstado.setEditable(true);
		txtNmTamanho.setEditable(true);
		txtDormitorios.setEditable(true);
		txtvlImovel.setEnabled(true);
		txtDtInicio.setEditable(true);
		txtDtEntrega.setEditable(true);
		txtDsImovel.setEditable(true);
		txtDsFormaPagamento.setEditable(true);
		cmbTipo.setEnabled(true);
		if(txtDsFoto1.getText().equals("")){
			btnFoto1.setEnabled(true);
			btnFoto1X.setEnabled(false);
		}else{
			btnFoto1.setEnabled(false);
			btnFoto1X.setEnabled(true);
		}
		if(txtDsFoto2.getText().equals("")){
			btnFoto2.setEnabled(true);
			btnFoto2X.setEnabled(false);
		}else{
			btnFoto2.setEnabled(false);
			btnFoto2X.setEnabled(true);
		}
		if(txtDsFoto3.getText().equals("")){
			btnFoto3.setEnabled(true);
			btnFoto3X.setEnabled(false);
		}else{
			btnFoto3.setEnabled(false);
			btnFoto3X.setEnabled(true);
		}
		if(txtDsFoto4.getText().equals("")){
			btnFoto4.setEnabled(true);
			btnFoto4X.setEnabled(false);
		}else{
			btnFoto4.setEnabled(false);
			btnFoto4X.setEnabled(true);
		}
		if(txtDsFoto5.getText().equals("")){
			btnFoto5.setEnabled(true);
			btnFoto5X.setEnabled(false);
		}else{
			btnFoto5.setEnabled(false);
			btnFoto5X.setEnabled(true);
		}
	}

	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroImovel(JDesktopPane desktop, int op, String matricula) {
		super();
		this.desk = desktop;
		this.op = op;
		this.setMinimumSize(new Dimension(700, 500));
		this.setMaximizable(true);
		this.setSize(new Dimension(700, 500));
		initialize();
		txtCdImovel.setEnabled(false);
		txtDsFoto1.setEnabled(false);
		txtDsFoto2.setEnabled(false);
		txtDsFoto3.setEnabled(false);
		txtDsFoto4.setEnabled(false);
		txtDsFoto5.setEnabled(false);
		if(getOp()==0){//Novo cadastro
			Imovel novo = new Imovel();
			txtCdImovel.setText(novo.novoImovel());
			btnAlterar.setEnabled(false);
			btnConfirmar.setEnabled(true);
			btnFoto1.setEnabled(true);
			btnFoto2.setEnabled(false);
			btnFoto3.setEnabled(false);
			btnFoto4.setEnabled(false);
			btnFoto5.setEnabled(false);
			btnFoto1X.setEnabled(false);
			btnFoto2X.setEnabled(false);
			btnFoto3X.setEnabled(false);
			btnFoto4X.setEnabled(false);
			btnFoto5X.setEnabled(false);
		}else{//Exibir dados
			ajustaDados(matricula);
			btnAlterar.setEnabled(true);
			btnConfirmar.setEnabled(false);
			desabilitaCampos();
			
		}	
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de imóvel");
		//this.setModal(true); 

		JLabel lblCdImovel = new JLabel("Código do imóvel");
		txtCdImovel = new JTextField("",8);
		JLabel lblNmImovel = new JLabel("Nome do imóvel");
		txtNmImovel = new JTextField("",45);
		JLabel lblNmEndereco = new JLabel("Endereço");
		txtNmEndereco = new JTextField("",90);
		JLabel lblBairro = new JLabel("Bairro");
		txtNmBairro = new JTextField("",45);
		JLabel lblNmCidade = new JLabel("Cidade");
		txtNmCidade = new JTextField("",45);
		JLabel lblSgEstado = new JLabel("Estado");
		cmbSgEstado = new JComboBox(getEstados());
		cmbSgEstado.setEditable(false);
		JLabel lblNmTipo = new JLabel("Tipo de imóvel");
		JLabel lblNmTamanho = new JLabel("Tamanho do imóvel");
		txtNmTamanho = new JTextField("",45);
		JLabel lblDormitorios = new JLabel("Dormitórios");
		txtDormitorios = new JTextField();
		JLabel lblVlImovel = new JLabel("Valor do imóvel");
		txtvlImovel = new JTextField("", 18);
		JLabel lblDtInicio = new JLabel("Data de início do empreendimento");
		txtDtInicio = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblDtEntrega = new JLabel("Data de entrega do empreendimento");
		txtDtEntrega = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblDsImovel = new JLabel("Descrição do imóvel");
		txtDsImovel = new JTextArea();
		JScrollPane jspTxtDsImovel = new JScrollPane(txtDsImovel);
		jspTxtDsImovel.setPreferredSize(new Dimension(80, 100));
		JLabel lblPagamento = new JLabel("Forma de pagamento");
		txtDsFormaPagamento = new JTextArea();
		JScrollPane jspTxtDsPagamento = new JScrollPane(txtDsFormaPagamento);
		jspTxtDsPagamento.setPreferredSize(new Dimension(40, 100));
		txtDsFoto1 = new JTextField("");
		btnFoto1 = new JButton("Foto 1");
		btnFoto1.setToolTipText("Clique para selecionar a foto");
		btnFoto1X = new JButton("Exluir");
		btnFoto1X.setToolTipText("Clique para excluir a foto");
		txtDsFoto2 = new JTextField("");
		btnFoto2 = new JButton("Foto 2");
		btnFoto2.setToolTipText("Clique para selecionar a foto");
		btnFoto2X = new JButton("Exluir");
		btnFoto2X.setToolTipText("Clique para excluir a foto");
		txtDsFoto3 = new JTextField("");
		btnFoto3 = new JButton("Foto 3");
		btnFoto3.setToolTipText("Clique para selecionar a foto");
		btnFoto3X = new JButton("Exluir");
		btnFoto3X.setToolTipText("Clique para excluir a foto");
		txtDsFoto4 = new JTextField("");
		btnFoto4 = new JButton("Foto 4");
		btnFoto4.setToolTipText("Clique para selecionar a foto");
		btnFoto4X = new JButton("Exluir");
		btnFoto4X.setToolTipText("Clique para excluir a foto");
		txtDsFoto5 = new JTextField("");
		btnFoto5 = new JButton("Foto 5");
		btnFoto5.setToolTipText("Clique para selecionar a foto");
		btnFoto5X = new JButton("Exluir");
		btnFoto5X.setToolTipText("Clique para excluir a foto");
		cmbTipo = new JComboBox(getTipo());
		cmbTipo.setEditable(false);

		JLabel lblDivisao = new JLabel("____________________________________________________________________________________________________________________________");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setToolTipText("Alterar dados cadastrados");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar dados para cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar cadastro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();
		Box linhaNove = Box.createHorizontalBox();
		Box linhaDez = Box.createHorizontalBox();
		Box linhaOnze = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();
		Box linhaDs = Box.createHorizontalBox();

		linhaUm.add(lblCdImovel);
		linhaUm.add(txtCdImovel);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblNmImovel);
		linhaUm.add(txtNmImovel);
		linhaDois.add(lblNmEndereco);
		linhaDois.add(txtNmEndereco);
		linhaTres.add(lblBairro);
		linhaTres.add(txtNmBairro);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblNmCidade);
		linhaTres.add(txtNmCidade);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblSgEstado);
		linhaTres.add(cmbSgEstado);
		linhaQuatro.add(lblNmTamanho);
		linhaQuatro.add(txtNmTamanho);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblDormitorios);
		linhaQuatro.add(txtDormitorios);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblVlImovel);
		linhaQuatro.add(txtvlImovel);
		linhaCinco.add(lblDtInicio);
		linhaCinco.add(txtDtInicio);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblDtEntrega);
		linhaCinco.add(txtDtEntrega);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblNmTipo);
		linhaCinco.add(cmbTipo);
		linhaDs.add(lblDsImovel);
		linhaSeis.add(lblPagamento);
		linhaDs.add(jspTxtDsImovel);
		linhaSeis.add(jspTxtDsPagamento);
		linhaSete.add(txtDsFoto1);
		linhaSete.add(btnFoto1);
		linhaSete.add(Box.createHorizontalStrut(5));
		linhaSete.add(btnFoto1X);
		linhaOito.add(txtDsFoto2);
		linhaOito.add(btnFoto2);
		linhaOito.add(Box.createHorizontalStrut(5));
		linhaOito.add(btnFoto2X);
		linhaNove.add(txtDsFoto3);
		linhaNove.add(btnFoto3);
		linhaNove.add(Box.createHorizontalStrut(5));
		linhaNove.add(btnFoto3X);
		linhaDez.add(txtDsFoto4);
		linhaDez.add(btnFoto4);
		linhaDez.add(Box.createHorizontalStrut(5));
		linhaDez.add(btnFoto4X);
		linhaOnze.add(txtDsFoto5);
		linhaOnze.add(btnFoto5);
		linhaOnze.add(Box.createHorizontalStrut(5));
		linhaOnze.add(btnFoto5X);
		
		linhaDoze.add(lblDivisao);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAlterar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

//		linhaTreze.add(Box.createHorizontalStrut(5));

		Box linhas = Box.createVerticalBox();

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
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaDs);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaDez);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOnze);

		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);		

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//--Ações
		txtvlImovel.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				try{
					double valor = Double.parseDouble(txtvlImovel.getText().replace(".",",").replace(",","."));
					NumberFormat nf = NumberFormat.getCurrencyInstance();
					String valorFormatado = nf.format(valor);
					txtvlImovel.setText(valorFormatado);	
				}catch(Exception ex){
					txtvlImovel.setText("");
					//txtComissao.grabFocus();
				}}
		});
		txtDtInicio.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().trim().equals("/  /")){
					txtDtInicio.setText("");
					return false;
				}else{
					Imovel consultar = new Imovel();
					if(consultar.validaData(txtDtInicio.getText())>0){
						txtDtInicio.setText("");
						JOptionPane.showMessageDialog(null,"Data de início inválida","Erro",2);
						return false;
					}else{
						return ((JFormattedTextField) txtDtInicio).isEditValid();
					}
				}				
			}
		});
		txtDtEntrega.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().trim().equals("/  /")){
					txtDtEntrega.setText("");
					JOptionPane.showMessageDialog(null,"Data de entrega inválida","Erro",2);
					return false;
				}else{
					Imovel consultar = new Imovel();
					if(consultar.validaData(txtDtEntrega.getText())>0){
						txtDtEntrega.setText("");
						JOptionPane.showMessageDialog(null,"Data de entrega inválida","Erro",2);
						return false;
					}else{
						if(consultar.validaDataEntrega(txtDtInicio.getText(), txtDtEntrega.getText())>0){
							txtDtEntrega.setText("");
							JOptionPane.showMessageDialog(null,"Data de entrega inválida","Erro",2);
							return false;	
						}else{
							return ((JFormattedTextField) txtDtInicio).isEditValid();
						}
					}
				}				
			}
		});
		btnFoto1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileFilter() {
					public boolean accept(File f) {
						return f.getName().toLowerCase().endsWith(".jpg")
						|| f.isDirectory();
					}
					public String getDescription() {
						return "JPG Images";
					}
				});
				int r = chooser.showOpenDialog(new JFrame());
				if (r == JFileChooser.APPROVE_OPTION){
					setCaminhoFoto1(chooser.getSelectedFile().getAbsolutePath());
					setNmFoto1(txtCdImovel.getText());
					btnFoto2.setEnabled(true);
				}
				txtDsFoto1.setText(getCaminhoFoto1());
				btnFoto1X.setEnabled(true);
			}});
		btnFoto1X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDsFoto1.setText("");
				btnFoto1X.setEnabled(false);
				btnFoto1.setEnabled(true);
			}});
		btnFoto2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileFilter() {
					public boolean accept(File f) {
						return f.getName().toLowerCase().endsWith(".jpg")
						|| f.isDirectory();
					}
					public String getDescription() {
						return "JPG Images";
					}
				});
				int r = chooser.showOpenDialog(new JFrame());
				if (r == JFileChooser.APPROVE_OPTION){
					setCaminhoFoto2(chooser.getSelectedFile().getAbsolutePath());
					setNmFoto2(txtCdImovel.getText());
					btnFoto3.setEnabled(true);
				}
				txtDsFoto2.setText(getCaminhoFoto2());
				btnFoto2X.setEnabled(true);
			}});
		btnFoto2X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDsFoto2.setText("");
				btnFoto2X.setEnabled(false);
				btnFoto2.setEnabled(true);
			}});
		btnFoto3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileFilter() {
					public boolean accept(File f) {
						return f.getName().toLowerCase().endsWith(".jpg")
						|| f.isDirectory();
					}
					public String getDescription() {
						return "JPG Images";
					}
				});
				int r = chooser.showOpenDialog(new JFrame());
				if (r == JFileChooser.APPROVE_OPTION){
					setCaminhoFoto3(chooser.getSelectedFile().getAbsolutePath());
					setNmFoto3(txtCdImovel.getText());
					btnFoto4.setEnabled(true);
				}
				txtDsFoto3.setText(getCaminhoFoto3());
				btnFoto3X.setEnabled(true);
			}});
		btnFoto3X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDsFoto3.setText("");
				btnFoto3X.setEnabled(false);
				btnFoto3.setEnabled(true);
			}});
		btnFoto4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileFilter() {
					public boolean accept(File f) {
						return f.getName().toLowerCase().endsWith(".jpg")
						|| f.isDirectory();
					}
					public String getDescription() {
						return "JPG Images";
					}
				});
				int r = chooser.showOpenDialog(new JFrame());
				if (r == JFileChooser.APPROVE_OPTION){
					setCaminhoFoto4(chooser.getSelectedFile().getAbsolutePath());
					setNmFoto4(txtCdImovel.getText());
					btnFoto5.setEnabled(true);
				}
				txtDsFoto4.setText(getCaminhoFoto4());
				btnFoto4X.setEnabled(true);
			}});
		btnFoto4X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDsFoto4.setText("");
				btnFoto4X.setEnabled(false);
				btnFoto4.setEnabled(true);
			}});
		btnFoto5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileFilter() {
					public boolean accept(File f) {
						return f.getName().toLowerCase().endsWith(".jpg")
						|| f.isDirectory();
					}
					public String getDescription() {
						return "JPG Images";
					}
				});
				int r = chooser.showOpenDialog(new JFrame());
				if (r == JFileChooser.APPROVE_OPTION){
					setCaminhoFoto5(chooser.getSelectedFile().getAbsolutePath());
					setNmFoto5(txtCdImovel.getText());
				}
				txtDsFoto5.setText(getCaminhoFoto5());
				btnFoto5X.setEnabled(true);
			}});
		btnFoto5X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDsFoto5.setText("");
				btnFoto5X.setEnabled(true);
				btnFoto5.setEnabled(true);
			}});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Imovel cadastrar = new Imovel();
				cadastrar.ajustaDados(txtCdImovel.getText(), txtNmImovel.getText(), txtNmEndereco.getText(), txtNmBairro.getText(), txtNmCidade.getText(), (String) cmbSgEstado.getSelectedItem(), (String) cmbTipo.getSelectedItem(), txtNmTamanho.getText(), txtDormitorios.getText(), txtDtInicio.getText(), txtDtEntrega.getText(), txtDsImovel.getText(), txtvlImovel.getText(), txtDsFormaPagamento.getText(), txtDsFoto1.getText(), txtDsFoto2.getText(), txtDsFoto3.getText(), txtDsFoto4.getText(), txtDsFoto5.getText());
				if(cadastrar.validaDados()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR));
						cadastrar.cadastrar();						
						btnAlterar.setEnabled(true);
						btnConfirmar.setEnabled(false);
						setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR));
					}else{
						if(cadastrar.verificaExiste()==1){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Já existe um imóvel cadastrado com o mesmo nome, deseja continuar?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR));
								cadastrar.ajustarFotos(txtDsFoto1.getText(), txtDsFoto2.getText(), txtDsFoto3.getText(), txtDsFoto4.getText(), txtDsFoto5.getText());
								cadastrar.alterar();						
								btnAlterar.setEnabled(true);
								btnConfirmar.setEnabled(false);
								setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR));
							}
						}
						if(cadastrar.verificaExiste()==2){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja alterar as informações do imóvel?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR));
								cadastrar.ajustarFotos(txtDsFoto1.getText(), txtDsFoto2.getText(), txtDsFoto3.getText(), txtDsFoto4.getText(), txtDsFoto5.getText());
								cadastrar.alterar();
								btnAlterar.setEnabled(true);
								btnConfirmar.setEnabled(false);
								setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR));
							}else if(cadastrar.verificaExiste()==3){
								JOptionPane.showMessageDialog(null, "Imóvel ja está cadastrado","Erro",2);
							}	
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
	public DefaultComboBoxModel getEstados(){
		DefaultComboBoxModel estados = new DefaultComboBoxModel();
		//Lista de estados brasileiros
		estados.addElement(new String("--"));
		estados.addElement(new String("AC"));
		estados.addElement(new String("AL"));
		estados.addElement(new String("AP"));
		estados.addElement(new String("AM"));
		estados.addElement(new String("BA"));
		estados.addElement(new String("CE"));
		estados.addElement(new String("ES"));
		estados.addElement(new String("GO"));
		estados.addElement(new String("MA"));
		estados.addElement(new String("MT"));
		estados.addElement(new String("MS"));
		estados.addElement(new String("MG"));
		estados.addElement(new String("PA"));
		estados.addElement(new String("PB"));
		estados.addElement(new String("PR"));
		estados.addElement(new String("PE"));
		estados.addElement(new String("PI"));
		estados.addElement(new String("RJ"));
		estados.addElement(new String("RN"));
		estados.addElement(new String("RS"));
		estados.addElement(new String("RO"));
		estados.addElement(new String("RR"));
		estados.addElement(new String("SC"));
		estados.addElement(new String("SP"));
		estados.addElement(new String("SE"));
		estados.addElement(new String("TO"));
		estados.addElement(new String("DF"));
		return estados;
	}

}
//@jve:decl-index=0:visual-constraint="10,10"
