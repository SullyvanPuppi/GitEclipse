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

public class DialogoDocumentoApresentado extends JInternalFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1738968265754125418L;

	private JPanel jcpDocApresentado = null;

	private JLabel lblAtividade = null;

	private JTextField txtNmDocumento = null;

	private JLabel lblDataDocApresentado = null;

	private JFormattedTextField txtDtDocApresentado = null;

	private JLabel lblDsDocApresentado = null;

	private JTextArea txtDsDocApresentado = null;

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
	
	public DialogoDocumentoApresentado(String matricula, String nmAluno){
		super();
		this.cdMatricula = matricula;
		this.nmAluno = nmAluno;
		initialize();
	}
	public void initialize(){
		this.setContentPane(getJcpHistoricoDocApresentado());
		this.setSize(new Dimension(402, 288));
		this.setTitle("][ Documento apresentado do aluno ][");
	}
	
	/**
	 * This method initializes jcpHistoricoDocApresentado	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJcpHistoricoDocApresentado() {
		if (jcpDocApresentado == null) {
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-183, 217, 830, 16));
			lblDivisao2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao2.setText("_____________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblDsDocApresentado = new JLabel();
			lblDsDocApresentado.setBounds(new Rectangle(12, 74, 145, 20));
			lblDsDocApresentado.setText("Descrição do documento");
			lblDataDocApresentado = new JLabel();
			lblDataDocApresentado.setBounds(new Rectangle(282, 12, 29, 20));
			lblDataDocApresentado.setText("Data");
			lblAtividade = new JLabel();
			lblAtividade.setBounds(new Rectangle(12, 44, 69, 20));
			lblAtividade.setText("Documento");
			jcpDocApresentado = new JPanel();
			jcpDocApresentado.setLayout(null);
			jcpDocApresentado.add(lblAtividade, null);
			jcpDocApresentado.add(getTxtNmDocumento(), null);
			jcpDocApresentado.add(lblDataDocApresentado, null);
			jcpDocApresentado.add(getTxtDtDocApresentado(), null);
			jcpDocApresentado.add(lblDsDocApresentado, null);
			jcpDocApresentado.add(getTxtDsDocApresentado(), null);
			jcpDocApresentado.add(lblDivisao2, null);
			jcpDocApresentado.add(getBtnConfirmaDoc(), null);
			jcpDocApresentado.add(getBtnCancelarDoc(), null);
		}
		return jcpDocApresentado;
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
			txtNmDocumento.setToolTipText("Nome do documento apresentado pelo aluno");
		}
		return txtNmDocumento;
	}
	/**
	 * This method initializes txtDtDocApresentado	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtDocApresentado() {
		if (txtDtDocApresentado == null) {
			txtDtDocApresentado = new JFormattedTextField(setMascara("##/##/####"));
			txtDtDocApresentado.setBounds(new Rectangle(307, 12, 80, 22));
			txtDtDocApresentado.setToolTipText("Data de apresentação do documento");
		}
		return txtDtDocApresentado;
	}
	/**
	 * This method initializes txtDsDocApresentado	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsDocApresentado() {
		if (txtDsDocApresentado == null) {
			txtDsDocApresentado = new JTextArea();
			txtDsDocApresentado.setBounds(new Rectangle(12, 94, 375, 126));
			txtDsDocApresentado.setToolTipText("Descrição do documento apresentado pelo aluno");
		}
		return txtDsDocApresentado;
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
			btnConfirmaDoc.setToolTipText("Confirmar documento apresentado do aluno e fechar");
			btnConfirmaDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aluno cadastrar = new Aluno();
					cadastrar.ajustaDocumentoApresentado(getCdMatricula(), getNmAluno(), getTxtNmDocumento().getText(), getTxtDtDocApresentado().getText(), getTxtDsDocApresentado().getText());
					if(cadastrar.validarDocumentoApresentado()>0){
						JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarDocumentoApresentado()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
					}else{
						cadastrar.cadastrarDocumentoApresentado();
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
			btnCancelarDoc.setToolTipText("Cancelar documento apresentado do aluno e fechar");
			btnCancelarDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelarDoc;
	}
}