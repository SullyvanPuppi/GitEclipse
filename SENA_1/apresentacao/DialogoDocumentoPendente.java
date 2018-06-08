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


public class DialogoDocumentoPendente extends JInternalFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7580185283428648038L;

	private JPanel jcpDocPendente = null;

	private JLabel lblAtividade = null;

	private JTextField txtNmDocumento = null;

	private JLabel lblDataDocPendente = null;

	private JFormattedTextField txtDtDocPendente = null;

	private JLabel lblDsDocPendente = null;

	private JTextArea txtDsDocPendente = null;

	private JLabel lblDivisao2 = null;

	private JButton btnConfirmaDoc = null;

	private JButton btnCancelarDoc = null;
	
	private String cdMatricula;
	
	private String nmAluno;
	
	public String getCdMatricula() {
		return cdMatricula;
	}

	public String getNmAluno() {
		return nmAluno;
	}

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
	
	public DialogoDocumentoPendente(String matricula, String nmAluno){
		super();
		this.cdMatricula = matricula;
		this.nmAluno = nmAluno;
		initialize();
	}
	
	public void initialize(){
		this.setContentPane(getJcpHistoricoDocPendente());
		this.setSize(new Dimension(402, 288));
		this.setTitle("][ Documento pendente do aluno ][");
	}
	
	/**
	 * This method initializes jcpHistoricoDocPendente	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJcpHistoricoDocPendente() {
		if (jcpDocPendente == null) {
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-183, 217, 830, 16));
			lblDivisao2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao2.setText("_____________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblDsDocPendente = new JLabel();
			lblDsDocPendente.setBounds(new Rectangle(12, 74, 145, 20));
			lblDsDocPendente.setText("Descrição do documento");
			lblDataDocPendente = new JLabel();
			lblDataDocPendente.setBounds(new Rectangle(282, 12, 29, 20));
			lblDataDocPendente.setText("Data");
			lblAtividade = new JLabel();
			lblAtividade.setBounds(new Rectangle(12, 44, 69, 20));
			lblAtividade.setText("Documento");
			jcpDocPendente = new JPanel();
			jcpDocPendente.setLayout(null);
			jcpDocPendente.add(lblAtividade, null);
			jcpDocPendente.add(getTxtNmDocumento(), null);
			jcpDocPendente.add(lblDataDocPendente, null);
			jcpDocPendente.add(getTxtDtDocPendente(), null);
			jcpDocPendente.add(lblDsDocPendente, null);
			jcpDocPendente.add(getTxtDsDocPendente(), null);
			jcpDocPendente.add(lblDivisao2, null);
			jcpDocPendente.add(getBtnConfirmaDoc(), null);
			jcpDocPendente.add(getBtnCancelarDoc(), null);
		}
		return jcpDocPendente;
	}
	/**
	 * This method initializes txtNmDocumento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmDocumento() {
		if (txtNmDocumento == null) {
			txtNmDocumento = new JTextField();
			txtNmDocumento.setBounds(new Rectangle(68, 44, 320, 22));
			txtNmDocumento.setToolTipText("Nome do documento pendente do aluno");
		}
		return txtNmDocumento;
	}
	/**
	 * This method initializes txtDtDocPendente	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtDocPendente() {
		if (txtDtDocPendente == null) {
			txtDtDocPendente = new JFormattedTextField(setMascara("##/##/####"));
			txtDtDocPendente.setBounds(new Rectangle(307, 12, 80, 22));
			txtDtDocPendente.setToolTipText("Data da pendência");
		}
		return txtDtDocPendente;
	}
	/**
	 * This method initializes txtDsDocPendente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsDocPendente() {
		if (txtDsDocPendente == null) {
			txtDsDocPendente = new JTextArea();
			txtDsDocPendente.setBounds(new Rectangle(12, 94, 375, 126));
			txtDsDocPendente.setToolTipText("Descrição do documento pendente do aluno");
		}
		return txtDsDocPendente;
	}
	/**
	 * This method initializes btnConfirmaDoc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmaDoc() {
		if (btnConfirmaDoc == null) {
			btnConfirmaDoc = new JButton();
			btnConfirmaDoc.setBounds(new Rectangle(196, 237, 91, 21));
			btnConfirmaDoc.setText("Confirmar");
			btnConfirmaDoc.setToolTipText("Confirmar documento pendente do aluno e fechar");
			btnConfirmaDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aluno cadastrar = new Aluno();
					cadastrar.ajustaDocumentoPendente(getCdMatricula(), getNmAluno(), getTxtNmDocumento().getText(), getTxtDtDocPendente().getText(), getTxtDsDocPendente().getText());
					if(cadastrar.validarDocumentoPendente()>0){
						JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarDocumentoPendente()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
					}else{
						cadastrar.cadastrarDocumentoPendente();
						dispose();
					}
				}
			});
		}
		return btnConfirmaDoc;
	}
	/**
	 * This method initializes btnCancelarDoc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarDoc() {
		if (btnCancelarDoc == null) {
			btnCancelarDoc = new JButton();
			btnCancelarDoc.setBounds(new Rectangle(296, 237, 91, 21));
			btnCancelarDoc.setText("Cancelar");
			btnCancelarDoc.setToolTipText("Cancelar documento pendente do aluno e fechar");
			btnCancelarDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelarDoc;
	}
}