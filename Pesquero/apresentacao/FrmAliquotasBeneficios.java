package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import negocios.AliquotasBeneficios;

public class FrmAliquotasBeneficios extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4895955751310408819L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;
	
	//Direitos de usuário determinado pelo grupo
	private int permissao;
	
	//Data atual
	AliquotasBeneficios consultar = new AliquotasBeneficios();
	private String hoje = consultar.getHoje();
	
	public String getHoje(){
		return this.hoje;
	}
	
	private JLabel lblVlTransporte = null;
	private JFormattedTextField txtVlTransporte = null;
	private JLabel lblVlRefeicao = null;
	private JFormattedTextField txtVlRefeicao = null;
	private JLabel lblVlFgts = null;
	private JFormattedTextField txtVlFgts = null;
	private JLabel lblVlInss1 = null;
	private JFormattedTextField txtVlInss1 = null;
	private JFormattedTextField txtVlInss1P = null;
	private JLabel lblVlInss2 = null;
	private JFormattedTextField txtVlInss2 = null;
	private JFormattedTextField txtVlInss2P = null;
	private JLabel lblVlInss3 = null;
	private JFormattedTextField txtVlInss3 = null;
	private JFormattedTextField txtVlInss3P = null;
	private JLabel lblVlInssTeto = null;
	private JFormattedTextField txtVlInssTeto = null;
	private JLabel lblVlIr1 = null;
	private JFormattedTextField txtVlIr1 = null;
	private JFormattedTextField txtVlIr1P = null;
	private JFormattedTextField txtVlIr1Ded = null;
	private JLabel lblVlIr2 = null;
	private JFormattedTextField txtVlIr2 = null;
	private JFormattedTextField txtVlIr2P = null;
	private JFormattedTextField txtVlIr2Ded = null;
	private JLabel lblVlIr3 = null;
	private JFormattedTextField txtVlIr3 = null;
	private JFormattedTextField txtVlIr3P = null;
	private JFormattedTextField txtVlIr3Ded = null;
	private JLabel lblVlDependentes = null;
	private JFormattedTextField txtVlDependentes = null;
	private JLabel lblVlFamilia1 = null;
	private JFormattedTextField txtVlFamilia1 = null;
	private JFormattedTextField txtVlFamilia1P = null;
	private JLabel lblVlFamilia2 = null;
	private JFormattedTextField txtVlFamilia2 = null;
	private JFormattedTextField txtVlFamilia2P = null;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JLabel lblAtualizado = null;
	private JLabel lblAtualizadoEm = null;	
	
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
	public FrmAliquotasBeneficios(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		initialize();
		ajustaDados();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Alíquotas e benefícios");
		lblVlTransporte = new JLabel("Vale transporte");
		txtVlTransporte = new JFormattedTextField(setMascara("###%"));
		lblVlRefeicao = new JLabel("Vale refeição");
		txtVlRefeicao = new JFormattedTextField(setMascara("R$####,##"));
		lblVlFgts = new JLabel("FGTS");
		txtVlFgts = new JFormattedTextField(setMascara("###%"));
		JLabel lblInss = new JLabel("INSS");
		lblVlInss1 = new JLabel("INSS - Faixa 1");
		txtVlInss1 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlInss1P = new JFormattedTextField(setMascara("###%"));
		lblVlInss2 = new JLabel("INSS - Faixa 2");
		txtVlInss2 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlInss2P = new JFormattedTextField(setMascara("###%"));
		lblVlInss3 = new JLabel("INSS - Faixa 3");
		txtVlInss3 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlInss3P = new JFormattedTextField(setMascara("###%"));
		lblVlInssTeto = new JLabel("Teto máximo de contribuição");
		txtVlInssTeto = new JFormattedTextField(setMascara("R$####,##"));
		JLabel lblIr = new JLabel("Imposto de renda");
		lblVlIr1 = new JLabel("IR - Faixa 1");
		txtVlIr1 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlIr1P = new JFormattedTextField(setMascara("###%"));
		txtVlIr1Ded = new JFormattedTextField(setMascara("R$####,##"));
		lblVlIr2 = new JLabel("IR - Faixa 2");
		txtVlIr2 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlIr2P = new JFormattedTextField(setMascara("###%"));
		txtVlIr2Ded = new JFormattedTextField(setMascara("R$####,##"));
		lblVlIr3 = new JLabel("IR - Faixa 3");
		txtVlIr3 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlIr3P = new JFormattedTextField(setMascara("###%"));
		txtVlIr3Ded = new JFormattedTextField(setMascara("R$####,##"));
		lblVlDependentes = new JLabel("Dedução por dependentes");
		txtVlDependentes = new JFormattedTextField(setMascara("R$####,##"));
		JLabel lblFamila = new JLabel("Salário família");
		lblVlFamilia1 = new JLabel("Salário família - Faixa 1");
		txtVlFamilia1 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlFamilia1P = new JFormattedTextField(setMascara("R$####,##"));
		lblVlFamilia2 = new JLabel("Salário família - Faixa 2");
		txtVlFamilia2 = new JFormattedTextField(setMascara("R$####,##"));
		txtVlFamilia2P = new JFormattedTextField(setMascara("R$####,##"));
		lblDivisao = new JLabel("________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAlterar = new JButton("Abrir");
		btnAlterar.setToolTipText("Exibir item selecionado");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");
		
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
				
		Box linhaP1 = Box.createHorizontalBox();
		Box linhaP2 = Box.createHorizontalBox();
		Box linhaP3 = Box.createHorizontalBox();
		
		
		Box linhaAtualizado = Box.createHorizontalBox();
		Box linhaAtualizadoEm = Box.createHorizontalBox();
		
		linhaP1.add(txtVlInss1P);
		linhaP1.add(txtVlInss2P);
		linhaP1.add(txtVlInss3P);
		
		linhaP2.add(txtVlIr1P);
		linhaP2.add(txtVlIr2P);
		
		linhaP3.add(txtVlFamilia1P);
		linhaP3.add(txtVlFamilia2P);
		
		linhaUm.add(lblVlTransporte);
		linhaUm.add(txtVlTransporte);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblVlRefeicao);
		linhaUm.add(txtVlRefeicao);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblVlFgts);
		linhaUm.add(txtVlFgts);
		
		linhaDois.add(lblInss);
		Box linhaInss1 = Box.createVerticalBox();
		Box linhaInss2 = Box.createVerticalBox();
		Box linhaInss3 = Box.createVerticalBox();
		Box linhaInss4 = Box.createVerticalBox();
		Box linhaInss5 = Box.createHorizontalBox();
	
		linhaInss1.add(linhaDois);
		linhaInss2.add(lblVlInss1);
		linhaInss2.add(txtVlInss1);
		linhaInss2.add(txtVlInss1P);
		linhaInss2.add(Box.createHorizontalStrut(5));
		linhaInss3.add(lblVlInss2);
		linhaInss3.add(txtVlInss2);
		linhaInss3.add(txtVlInss2P);
		linhaInss3.add(Box.createHorizontalStrut(5));
		linhaInss4.add(lblVlInss3);
		linhaInss4.add(txtVlInss3);
		linhaInss4.add(txtVlInss3P);
		linhaInss4.add(Box.createHorizontalStrut(5));
		linhaInss5.add(lblVlInssTeto);
		linhaInss5.add(txtVlInssTeto);
		
		linhaTres.add(linhaInss2);
		linhaTres.add(linhaInss3);
		linhaTres.add(linhaInss4);
		
		linhaQuatro.add(lblIr);
		Box linhaIr1 = Box.createVerticalBox();
		Box linhaIr2 = Box.createVerticalBox();
		Box linhaIr3 = Box.createVerticalBox();
		Box linhaIr4 = Box.createVerticalBox();
		
		linhaIr1.add(linhaQuatro);
		linhaIr2.add(lblVlIr1);
		linhaIr2.add(txtVlIr1);
		linhaIr2.add(txtVlIr1P);
		linhaIr2.add(txtVlIr1Ded);
		linhaIr3.add(lblVlIr2);
		linhaIr3.add(txtVlIr2);
		linhaIr3.add(txtVlIr2P);
		linhaIr3.add(txtVlIr2Ded);
		linhaIr4.add(lblVlIr3);
		linhaIr4.add(txtVlIr3);
		linhaIr4.add(txtVlIr3P);
		linhaIr4.add(txtVlIr3Ded);
		linhaCinco.add(linhaIr2);
		linhaCinco.add(linhaIr3);
		linhaCinco.add(linhaIr4);
		
		linhaSeis.add(lblVlDependentes);
		linhaSeis.add(txtVlDependentes);
		
		linhaSete.add(lblFamila);
		Box linhaFamilia1 = Box.createVerticalBox();
		Box linhaFamilia2 = Box.createVerticalBox();
		Box linhaFamilia3 = Box.createVerticalBox();
	
		linhaFamilia1.add(linhaDois);
		linhaFamilia2.add(lblVlFamilia1);
		linhaFamilia2.add(txtVlFamilia1);
		linhaFamilia2.add(txtVlFamilia1P);
		linhaFamilia2.add(Box.createHorizontalStrut(5));
		linhaFamilia3.add(lblVlFamilia2);
		linhaFamilia3.add(txtVlFamilia2);
		linhaFamilia3.add(txtVlFamilia2P);
		linhaFamilia3.add(Box.createHorizontalStrut(5));
			
		linhaOito.add(linhaFamilia2);
		linhaOito.add(linhaFamilia3);
		
		linhaNove.add(lblDivisao);
		linhaDez.add(btnRelatorio);
		linhaDez.add(Box.createHorizontalStrut(5));
		linhaDez.add(btnAlterar);
		linhaDez.add(Box.createHorizontalStrut(5));
		linhaDez.add(btnConfirmar);
		linhaDez.add(Box.createHorizontalStrut(5));
		linhaDez.add(btnCancelar);
		
		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(15));		
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaInss5);
		linhas.add(Box.createVerticalStrut(15));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(15));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		if(consultar.verificaExiste()==1){
			lblAtualizado = new JLabel("Atualizado em: ");
			consultar.consultar();
			lblAtualizadoEm = new JLabel(consultar.getAtualizado());	
			linhaAtualizado.add(lblAtualizado);
			linhaAtualizadoEm.add(lblAtualizadoEm);	
			linhas.add(Box.createVerticalStrut(10));
			linhas.add(linhaAtualizado);
			linhas.add(Box.createVerticalStrut(5));
			linhas.add(linhaAtualizadoEm);	
		}		
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaDez);
		
		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();
		
		//--Ações
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos();
				btnAlterar.setEnabled(false);
			}});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AliquotasBeneficios cadastrar = new AliquotasBeneficios();
				if(cadastrar.validar(txtVlTransporte.getText().trim(), txtVlRefeicao.getText().trim(), txtVlFgts.getText().trim(), txtVlInss1.getText().trim(), txtVlInss2.getText().trim(), txtVlInss3.getText().trim(), txtVlIr1.getText().trim(), txtVlIr2.getText().trim(), txtVlDependentes.getText().trim(), txtVlFamilia1.getText().trim(), txtVlFamilia2.getText().trim(), txtVlInss1P.getText().trim(), txtVlInss2P.getText().trim(), txtVlInss3P.getText().trim(), txtVlIr1P.getText().trim(), txtVlIr2P.getText().trim(), txtVlFamilia1P.getText().trim(), txtVlFamilia2P.getText().trim(), txtVlIr3.getText().trim(), txtVlIr3P.getText().trim(), txtVlIr1Ded.getText().trim(), txtVlIr2Ded.getText().trim(), txtVlIr3Ded.getText().trim(), txtVlInssTeto.getText().trim())>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrar();
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja alterar as informações da empresa?", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							cadastrar.alterar();
						}
					}
					desabilitarCampos();
					btnAlterar.setEnabled(true);	
				}
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				AliquotasBeneficios gerar = new AliquotasBeneficios();
				//exibe o resultado
				gerar.gerarRelatorio().show();
			}});
		
	}
	public void habilitarCampos(){
		txtVlTransporte.setEditable(true);
		txtVlRefeicao.setEditable(true);
		txtVlFgts.setEditable(true);
		txtVlInss1.setEditable(true);
		txtVlInss2.setEditable(true);
		txtVlInss3.setEditable(true);
		txtVlIr1.setEditable(true);
		txtVlIr2.setEditable(true);
		txtVlIr3.setEditable(true);
		txtVlIr1Ded.setEditable(true);
		txtVlIr2Ded.setEditable(true);
		txtVlIr3Ded.setEditable(true);
		txtVlDependentes.setEditable(true);
		txtVlFamilia1.setEditable(true);
		txtVlFamilia2.setEditable(true);
		txtVlInss1P.setEditable(true);
		txtVlInss2P.setEditable(true);
		txtVlInss3P.setEditable(true);
		txtVlInssTeto.setEditable(true);
		txtVlIr1P.setEditable(true);
		txtVlIr2P.setEditable(true);
		txtVlIr3P.setEditable(true);
		txtVlDependentes.setEditable(true);
		txtVlFamilia1P.setEditable(true);
		txtVlFamilia2P.setEditable(true);
	}
	public void desabilitarCampos(){
		txtVlTransporte.setEditable(false);
		txtVlRefeicao.setEditable(false);
		txtVlFgts.setEditable(false);
		txtVlInss1.setEditable(false);
		txtVlInss2.setEditable(false);
		txtVlInss3.setEditable(false);
		txtVlIr1.setEditable(false);
		txtVlIr2.setEditable(false);
		txtVlIr3.setEditable(false);
		txtVlIr1Ded.setEditable(false);
		txtVlIr2Ded.setEditable(false);
		txtVlIr3Ded.setEditable(false);
		txtVlDependentes.setEditable(false);
		txtVlFamilia1.setEditable(false);
		txtVlFamilia2.setEditable(false);
		txtVlInss1P.setEditable(false);
		txtVlInss2P.setEditable(false);
		txtVlInss3P.setEditable(false);
		txtVlInssTeto.setEditable(false);
		txtVlIr1P.setEditable(false);
		txtVlIr2P.setEditable(false);
		txtVlIr3P.setEditable(false);
		txtVlDependentes.setEditable(false);
		txtVlFamilia1P.setEditable(false);
		txtVlFamilia2P.setEditable(false);
	}
	public void ajustaDados(){
		AliquotasBeneficios consultar = new AliquotasBeneficios();
		if(consultar.verificaExiste()==1){
			consultar.consultar();
			txtVlTransporte.setText(consultar.getVlTransporte());
			txtVlRefeicao.setText(consultar.getVlRefeicao());
			txtVlFgts.setText(consultar.getVlFgts());
			txtVlInss1.setText(consultar.getVlInss1());
			txtVlInss2.setText(consultar.getVlInss2());
			txtVlInss3.setText(consultar.getVlInss3());
			txtVlIr1.setText(consultar.getVlIr1());
			txtVlIr2.setText(consultar.getVlIr2());
			txtVlIr3.setText(consultar.getVlIr3());
			txtVlDependentes.setText(consultar.getVlDependentes());
			txtVlFamilia1.setText(consultar.getVlFamilia1());
			txtVlFamilia2.setText(consultar.getVlFamilia2());
			txtVlInss1P.setText(consultar.getVlInss1P());
			txtVlInss2P.setText(consultar.getVlInss2P());
			txtVlInss3P.setText(consultar.getVlInss3P());
			txtVlInssTeto.setText(consultar.getVlInssTeto());
			txtVlIr1P.setText(consultar.getVlIr1P());
			txtVlIr2P.setText(consultar.getVlIr2P());
			txtVlIr3P.setText(consultar.getVlIr3P());
			txtVlFamilia1P.setText(consultar.getVlFamilia1P());
			txtVlFamilia2P.setText(consultar.getVlFamilia2P());
			txtVlIr1Ded.setText(consultar.getVlIrDed1());
			txtVlIr2Ded.setText(consultar.getVlIrDed2());
			txtVlIr3Ded.setText(consultar.getVlIrDed3());
		}		
	}
} 
