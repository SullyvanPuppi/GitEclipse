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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class DialogoDocumentoSolicitado extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1525094760620780560L;

	private JPanel jcpDocSolicitado = null;

	private JLabel lblAtividade = null;

	private JTextField txtNmDocumento = null;

	private JLabel lblDataDocSolicitado = null;

	private JFormattedTextField txtDtDocSolicitado = null;

	private JLabel lblDsDocSolicitado = null;

	private JTextArea txtDsMotivo = null;

	private JLabel lblDivisao2 = null;

	private JButton btnPublicarDoc = null;

	private JButton btnCancelarDoc = null;

	private JLabel lblRecado = null;

	private JTextArea txtAvaliacao = null;
	
	private String cdMatricula;
	
	private String nmAluno;
	
	private String nmDocumento;
	
	public String getCdMatricula() {
		return cdMatricula;
	}

	public String getNmAluno() {
		return nmAluno;
	}
	
	public String getNmDocumento(){
		return nmDocumento;
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
		
	public DialogoDocumentoSolicitado(String cdMatricula, String nmAluno, String nmDocumento){
		super();
		this.cdMatricula = cdMatricula;
		this.nmAluno = nmAluno;
		this.nmDocumento = nmDocumento;
		initialize();
	}
	public void initialize(){
		this.setContentPane(getJcpHistoricoDocSolicitado());
		this.setSize(new Dimension(406, 533));
		this.setTitle("][ Documento solicitado do aluno ][");
	}
	
	/**
	 * This method initializes jcpHistoricoDocSolicitado	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJcpHistoricoDocSolicitado() {
		if (jcpDocSolicitado == null) {
			lblRecado = new JLabel();
			lblRecado.setBounds(new Rectangle(12, 270, 138, 20));
			lblRecado.setText("Mensagem para o aluno");
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-182, 449, 830, 16));
			lblDivisao2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao2.setText("_____________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblDsDocSolicitado = new JLabel();
			lblDsDocSolicitado.setBounds(new Rectangle(12, 74, 121, 20));
			lblDsDocSolicitado.setText("Motivo da solicitação");
			lblDataDocSolicitado = new JLabel();
			lblDataDocSolicitado.setBounds(new Rectangle(297, 14, 29, 20));
			lblDataDocSolicitado.setText("Data");
			lblAtividade = new JLabel();
			lblAtividade.setBounds(new Rectangle(12, 44, 67, 20));
			lblAtividade.setText("Documento");
			jcpDocSolicitado = new JPanel();
			jcpDocSolicitado.setLayout(null);
			jcpDocSolicitado.add(lblAtividade, null);
			jcpDocSolicitado.add(getTxtNmDocumento(), null);
			jcpDocSolicitado.add(lblDataDocSolicitado, null);
			jcpDocSolicitado.add(getTxtDtDocSolicitado(), null);
			jcpDocSolicitado.add(lblDsDocSolicitado, null);
			jcpDocSolicitado.add(getTxtDsMotivo(), null);
			jcpDocSolicitado.add(lblDivisao2, null);
			jcpDocSolicitado.add(getBtnPublicarDoc(), null);
			jcpDocSolicitado.add(getBtnCancelarDoc(), null);
			jcpDocSolicitado.add(lblRecado, null);
			jcpDocSolicitado.add(getTxtAvaliacao(), null);
		}
		return jcpDocSolicitado;
	}
	/**
	 * This method initializes txtNmDocumento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmDocumento() {
		if (txtNmDocumento == null) {
			txtNmDocumento = new JTextField();
			txtNmDocumento.setBounds(new Rectangle(73, 44, 320, 22));
			txtNmDocumento.setToolTipText("Nome do documento solicitado");
		}
		return txtNmDocumento;
	}
	/**
	 * This method initializes txtDtDocSolicitado	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtDocSolicitado() {
		if (txtDtDocSolicitado == null) {
			txtDtDocSolicitado = new JFormattedTextField(setMascara("##/##/####"));
			txtDtDocSolicitado.setBounds(new Rectangle(322, 14, 70, 22));
			txtDtDocSolicitado.setToolTipText("Data da solicitação do documento");
		}
		return txtDtDocSolicitado;
	}
	/**
	 * This method initializes txtDsMotivo	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsMotivo() {
		if (txtDsMotivo == null) {
			txtDsMotivo = new JTextArea();
			txtDsMotivo.setBounds(new Rectangle(12, 94, 378, 164));
			txtDsMotivo.setToolTipText("Motivo da solicitação do documento");
		}
		return txtDsMotivo;
	}
	/**
	 * This method initializes btnPublicarDoc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPublicarDoc() {
		if (btnPublicarDoc == null) {
			btnPublicarDoc = new JButton();
			btnPublicarDoc.setBounds(new Rectangle(200, 474, 91, 21));
			btnPublicarDoc.setText("Publicar");
			btnPublicarDoc.setToolTipText("Publicar documento solicitado do aluno para download e fechar");
		}
		return btnPublicarDoc;
	}
	/**
	 * This method initializes btnCancelarDoc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarDoc() {
		if (btnCancelarDoc == null) {
			btnCancelarDoc = new JButton();
			btnCancelarDoc.setBounds(new Rectangle(300, 474, 91, 21));
			btnCancelarDoc.setText("Cancelar");
			btnCancelarDoc.setToolTipText("Cancelar documento solicitado do aluno e fechar");
			btnCancelarDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelarDoc;
	}
	/**
	 * This method initializes txtAvaliacao	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtAvaliacao() {
		if (txtAvaliacao == null) {
			txtAvaliacao = new JTextArea();
			txtAvaliacao.setBounds(new Rectangle(12, 290, 377, 164));
			txtAvaliacao.setToolTipText("Avaliação da solicitação");
		}
		return txtAvaliacao;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"