package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import negocios.Venda;

public class FrmExtratoVendas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8389452140999434000L;

	String login = "";
	
	private JTextField txtVendedor = null;
	private JComboBox cmbVendedores = null;
	private JFormattedTextField txtDe = null;
	private JFormattedTextField txtA = null;
	
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

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
//	---Classe responsável por formatar márcaras de entrada de campos específicos
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
	public FrmExtratoVendas(JDesktopPane desktop, String login, int permissao) {
		super();
		this.desk = desktop;
		this.login = login;
		this.permissao = permissao;
		initialize();
		txtVendedor.setEditable(false);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Extrato de vendas");
		this.setSize(560,150);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Selecione a o funcionário e o período desejado?");
		JLabel lblMatricula = new JLabel("Matrícula");
		txtVendedor = new JTextField("",10);
		JLabel lblNmVendedor = new JLabel("Nome do vendedor");
		cmbVendedores = new JComboBox(getVendedores());
		cmbVendedores.setPreferredSize(new Dimension(280, 20));
		JLabel lblCriterioCinco = new JLabel("De");
		txtDe = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblCriterioSeis = new JLabel("À");
		txtA = new JFormattedTextField(setMascara("##/##/####"));
		
		JButton btnConfirmar = new JButton("Relatório");
		btnConfirmar.setToolTipText("Gerar relatório do extrato de vendas do funcionário");
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar relatório e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();

		linhaUm.add(lblNovo);
		linhaDois.add(lblMatricula);
		linhaDois.add(txtVendedor);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(lblNmVendedor);
		linhaDois.add(cmbVendedores);
		linhaTres.add(lblCriterioCinco);
		linhaTres.add(txtDe);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblCriterioSeis);
		linhaTres.add(txtA);
		
		linhaQuatro.add(btnConfirmar);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaQuatro);
		

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		setContentPane(jContentPane);
			
		cmbVendedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					Venda consultar = new Venda();
					txtVendedor.setText(consultar.codFuncionario((String) cmbVendedores.getSelectedItem()));
				}				
			}
		});		
		btnConfirmar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Venda gerar = new Venda();
				setVisible(false);
				if(!(gerar.gerarRelatorioExtrato(getPermissao(), (String) cmbVendedores.getSelectedItem(), txtDe.getText().trim(), txtA.getText().trim())==null)){
					gerar.gerarRelatorioExtrato(getPermissao(), (String) cmbVendedores.getSelectedItem(), txtDe.getText().trim(), txtA.getText().trim()).show();	
				}				
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
	@SuppressWarnings("unchecked")
	public Vector getVendedores(){
		Venda consultar = new Venda();
		Vector vendedores = new Vector();
		vendedores.addElement("--------------------------------");
		vendedores.addAll(consultar.consultarVendedores());
		return vendedores;
	}
}