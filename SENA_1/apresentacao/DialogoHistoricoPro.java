package apresentacao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import negocios.Aluno;

public class DialogoHistoricoPro extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520786919070115357L;

	private JPanel jcpHistoricoPro = null;

	private JLabel lblAtividade = null;

	private JTextField txtAtividade = null;

	private JLabel lblDataHistoricoPro = null;

	private JFormattedTextField txtDtHistoricoPro = null;

	private JLabel lblDsHistoricoPro = null;

	private JTextArea txtDsHistoricoPro = null;

	private JLabel lblDivisao2 = null;

	private JButton btnConfirmaAtividade = null;

	private JButton btnCancelarAtividade = null;
	
	//---Classe responsável por formatar márcaras de entrada de campos específicos
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
	
	private String cdMatricula;
	
	private String nmAluno;
	
	public String getCdMatricula() {
		return cdMatricula;
	}

	public String getNmAluno() {
		return nmAluno;
	}
	
	public DialogoHistoricoPro(String matricula, String nmAluno){
		super();
		this.cdMatricula = matricula;
		this.nmAluno = nmAluno;
		initialize();
	}
	public void initialize(){
		this.setContentPane(getJcpHistoricoHistoricoPro());
		this.setSize(new Dimension(402, 288));
		this.setTitle("][ Atividade positiva do aluno ][");
	}
	
	/**
	 * This method initializes jcpHistoricoHistoricoPro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJcpHistoricoHistoricoPro() {
		if (jcpHistoricoPro == null) {
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-183, 217, 830, 16));
			lblDivisao2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao2.setText("_____________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblDsHistoricoPro = new JLabel();
			lblDsHistoricoPro.setBounds(new Rectangle(12, 74, 131, 20));
			lblDsHistoricoPro.setText("Descrição da atividade");
			lblDataHistoricoPro = new JLabel();
			lblDataHistoricoPro.setBounds(new Rectangle(282, 12, 29, 20));
			lblDataHistoricoPro.setText("Data");
			lblAtividade = new JLabel();
			lblAtividade.setBounds(new Rectangle(12, 44, 69, 20));
			lblAtividade.setText("Atividade");
			jcpHistoricoPro = new JPanel();
			jcpHistoricoPro.setLayout(null);
			jcpHistoricoPro.add(lblAtividade, null);
			jcpHistoricoPro.add(getTxtAtividade(), null);
			jcpHistoricoPro.add(lblDataHistoricoPro, null);
			jcpHistoricoPro.add(getTxtDtHistoricoPro(), null);
			jcpHistoricoPro.add(lblDsHistoricoPro, null);
			jcpHistoricoPro.add(getTxtDsHistoricoPro(), null);
			jcpHistoricoPro.add(lblDivisao2, null);
			jcpHistoricoPro.add(getBtnConfirmaAtividade(), null);
			jcpHistoricoPro.add(getBtnCancelarAtividade(), null);
		}
		return jcpHistoricoPro;
	}
	/**
	 * This method initializes txtAtividade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtAtividade() {
		if (txtAtividade == null) {
			txtAtividade = new JTextField();
			txtAtividade.setBounds(new Rectangle(68, 44, 320, 22));
			txtAtividade.setToolTipText("Atividade positiva do aluno");
		}
		return txtAtividade;
	}
	/**
	 * This method initializes txtDtHistoricoPro	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtHistoricoPro() {
		if (txtDtHistoricoPro == null) {
			txtDtHistoricoPro = new JFormattedTextField(setMascara("##/##/####"));
			txtDtHistoricoPro.setBounds(new Rectangle(307, 12, 80, 22));
			txtDtHistoricoPro.setToolTipText("Data da atividade positiva do aluno");
		}
		return txtDtHistoricoPro;
	}
	/**
	 * This method initializes txtDsHistoricoPro	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsHistoricoPro() {
		if (txtDsHistoricoPro == null) {
			txtDsHistoricoPro = new JTextArea();
			txtDsHistoricoPro.setBounds(new Rectangle(12, 94, 375, 126));
			txtDsHistoricoPro.setToolTipText("Descrição da atividade positiva do aluno");
		}
		return txtDsHistoricoPro;
	}
	/**
	 * This method initializes btnConfirmaAtividade	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmaAtividade() {
		if (btnConfirmaAtividade == null) {
			btnConfirmaAtividade = new JButton();
			btnConfirmaAtividade.setBounds(new Rectangle(196, 237, 91, 21));
			btnConfirmaAtividade.setText("Confirmar");
			btnConfirmaAtividade.setToolTipText("Confirmar atividade positiva do aluno e fechar");
			btnConfirmaAtividade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aluno cadastrar = new Aluno();
					cadastrar.ajustaHistoricoPro(getCdMatricula(), getNmAluno(), getTxtAtividade().getText(), getTxtDtHistoricoPro().getText(), getTxtDsHistoricoPro().getText());
					if(cadastrar.validarHistoricoPro()>0){
						JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarDocumentoApresentado()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
					}else{
						cadastrar.cadastrarHistoricoPro();
						dispose();
					}
				}
			});
		}
		return btnConfirmaAtividade;
	}
	/**
	 * This method initializes btnCancelarAtividade	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarAtividade() {
		if (btnCancelarAtividade == null) {
			btnCancelarAtividade = new JButton();
			btnCancelarAtividade.setBounds(new Rectangle(296, 237, 91, 21));
			btnCancelarAtividade.setText("Cancelar");
			btnCancelarAtividade.setToolTipText("Cancelar atividade positiva do aluno e fechar");
			btnCancelarAtividade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelarAtividade;
	}
} 