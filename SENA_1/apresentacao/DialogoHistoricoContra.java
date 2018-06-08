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

public class DialogoHistoricoContra extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520786919070115357L;

	private JPanel jcpHistoricoContra = null;

	private JLabel lblAtividade = null;

	private JTextField txtAtividade = null;

	private JLabel lblDataHistoricoContra = null;

	private JFormattedTextField txtDtHistoricoContra = null;

	private JLabel lblDsHistoricoContra = null;

	private JTextArea txtDsHistoricoContra = null;

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
	
	public DialogoHistoricoContra(String matricula, String nmAluno){
		super();
		this.cdMatricula = matricula;
		this.nmAluno = nmAluno;
		initialize();
	}
	public void initialize(){
		this.setContentPane(getJcpHistoricoHistoricoContra());
		this.setSize(new Dimension(402, 288));
		this.setTitle("][ Atividade negativa do aluno ][");
	}
	
	/**
	 * This method initializes jcpHistoricoHistoricoContra	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJcpHistoricoHistoricoContra() {
		if (jcpHistoricoContra == null) {
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-183, 217, 830, 16));
			lblDivisao2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao2.setText("_____________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblDsHistoricoContra = new JLabel();
			lblDsHistoricoContra.setBounds(new Rectangle(12, 74, 131, 20));
			lblDsHistoricoContra.setText("Descrição da atividade");
			lblDataHistoricoContra = new JLabel();
			lblDataHistoricoContra.setBounds(new Rectangle(282, 12, 29, 20));
			lblDataHistoricoContra.setText("Data");
			lblAtividade = new JLabel();
			lblAtividade.setBounds(new Rectangle(12, 44, 69, 20));
			lblAtividade.setText("Atividade");
			jcpHistoricoContra = new JPanel();
			jcpHistoricoContra.setLayout(null);
			jcpHistoricoContra.add(lblAtividade, null);
			jcpHistoricoContra.add(getTxtAtividade(), null);
			jcpHistoricoContra.add(lblDataHistoricoContra, null);
			jcpHistoricoContra.add(getTxtDtHistoricoContra(), null);
			jcpHistoricoContra.add(lblDsHistoricoContra, null);
			jcpHistoricoContra.add(getTxtDsHistoricoContra(), null);
			jcpHistoricoContra.add(lblDivisao2, null);
			jcpHistoricoContra.add(getBtnConfirmaAtividade(), null);
			jcpHistoricoContra.add(getBtnCancelarAtividade(), null);
		}
		return jcpHistoricoContra;
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
			txtAtividade.setToolTipText("Nome do documento apresentado pelo aluno");
		}
		return txtAtividade;
	}
	/**
	 * This method initializes txtDtHistoricoContra	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtHistoricoContra() {
		if (txtDtHistoricoContra == null) {
			txtDtHistoricoContra = new JFormattedTextField(setMascara("##/##/####"));
			txtDtHistoricoContra.setBounds(new Rectangle(307, 12, 80, 22));
			txtDtHistoricoContra.setToolTipText("Data da atividade negativa");
		}
		return txtDtHistoricoContra;
	}
	/**
	 * This method initializes txtDsHistoricoContra	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsHistoricoContra() {
		if (txtDsHistoricoContra == null) {
			txtDsHistoricoContra = new JTextArea();
			txtDsHistoricoContra.setBounds(new Rectangle(12, 94, 375, 126));
			txtDsHistoricoContra.setToolTipText("Descrição da atividade negativa do aluno");
		}
		return txtDsHistoricoContra;
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
			btnConfirmaAtividade.setToolTipText("Confirmar atividade negativa do aluno e fechar");
			btnConfirmaAtividade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aluno cadastrar = new Aluno();
					cadastrar.ajustaHistoricoContra(getCdMatricula(), getNmAluno(), getTxtAtividade().getText(), getTxtDtHistoricoContra().getText(), getTxtDsHistoricoContra().getText());
					if(cadastrar.validarHistoricoContra()>0){
						JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarDocumentoApresentado()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
					}else{
						cadastrar.cadastrarHistoricoContra();
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
			btnCancelarAtividade.setToolTipText("Cancelar atividade negativa do aluno e fechar");
			btnCancelarAtividade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelarAtividade;
	}
}