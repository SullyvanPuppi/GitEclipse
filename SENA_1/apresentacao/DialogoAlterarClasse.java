package apresentacao;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import negocios.Aluno;

public class DialogoAlterarClasse extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5311732741502958714L;
	private JPanel jContentPane = null;
	private JButton btnCancelar = null;
	private JButton btnConfirmar = null;
	private JLabel lblClasseAtual = null;
	private JTextField txtClasse = null;
	private JLabel lblCurso = null;
	private JTextField txtCurso = null;
	private JLabel lblCiclo = null;
	private JTextField txtCiclo = null;
	private JLabel lblMotivo = null;
	private JRadioButton jrbTransferencia = null;
	private JRadioButton jrbDesistencia = null;
	private JRadioButton jrbTrancamento = null;
	private JLabel lblDtAlteracao = null;
	private JFormattedTextField txtDtAlteracao = null;
	private JLabel lblClasseDestino = null;
	private JComboBox cmbClasseDestino = null;
	private JLabel lblDados = null;
	private JLabel lblCursoDestino = null;
	private JTextField txtCursoDestino = null;
	private JLabel lblCicloDestino = null;
	private JTextField txtCicloDestino = null;
	private JLabel lblDsMotivo = null;
	private JTextArea txtDsMotivo = null;
	
	//Grupo de botões de rádio para staus da alteração
	private ButtonGroup radioGroup =  new ButtonGroup();
	
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

	private String nmCurso;
	
	public String getNmCurso(){
		return nmCurso;
	}
	
	/**
	 * This is the xxx default constructor
	 */
	public DialogoAlterarClasse(String nmCurso) {
		super();
		this.nmCurso = nmCurso;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(409, 545);
		this.setTitle("][ Alterar classe ][");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblDsMotivo = new JLabel();
			lblDsMotivo.setBounds(new Rectangle(12, 344, 38, 20));
			lblDsMotivo.setText("Motivo");
			lblCicloDestino = new JLabel();
			lblCicloDestino.setBounds(new Rectangle(12, 314, 29, 20));
			lblCicloDestino.setText("Ciclo");
			lblCursoDestino = new JLabel();
			lblCursoDestino.setBounds(new Rectangle(12, 284, 38, 20));
			lblCursoDestino.setText("Curso");
			lblDados = new JLabel();
			lblDados.setBounds(new Rectangle(12, 249, 159, 20));
			lblDados.setText("Dados da classe de destino");
			lblClasseDestino = new JLabel();
			lblClasseDestino.setBounds(new Rectangle(12, 194, 103, 20));
			lblClasseDestino.setText("Classe de destino");
			lblDtAlteracao = new JLabel();
			lblDtAlteracao.setBounds(new Rectangle(12, 164, 103, 20));
			lblDtAlteracao.setText("Data da alteração");
			lblMotivo = new JLabel();
			lblMotivo.setBounds(new Rectangle(12, 104, 173, 20));
			lblMotivo.setText("Motivo da alteração de classe");
			lblCiclo = new JLabel();
			lblCiclo.setBounds(new Rectangle(12, 74, 29, 20));
			lblCiclo.setText("Ciclo");
			lblCurso = new JLabel();
			lblCurso.setBounds(new Rectangle(12, 44, 38, 20));
			lblCurso.setText("Curso");
			lblClasseAtual = new JLabel();
			lblClasseAtual.setBounds(new Rectangle(12, 14, 74, 20));
			lblClasseAtual.setText("Classe atual");
			JLabel lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-182, 474, 830, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setText("_____________________________________________________________________________");
			lblDivisao.setForeground(new Color(190, 190, 190));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnConfirmar(), null);
			jContentPane.add(lblClasseAtual, null);
			jContentPane.add(getTxtClasse(), null);
			jContentPane.add(lblCurso, null);
			jContentPane.add(getTxtCurso(), null);
			jContentPane.add(lblCiclo, null);
			jContentPane.add(getTxtCiclo(), null);
			jContentPane.add(lblMotivo, null);
			jContentPane.add(getJrbTransferencia(), null);
			jContentPane.add(getJrbDesistencia(), null);
			jContentPane.add(getJrbTrancamento(), null);
			jContentPane.add(lblDtAlteracao, null);
			jContentPane.add(getTxtDtAlteracao(), null);
			jContentPane.add(lblClasseDestino, null);
			jContentPane.add(getCmbClasseDestino(), null);
			jContentPane.add(lblDados, null);
			jContentPane.add(lblCursoDestino, null);
			jContentPane.add(getTxtCursoDestino(), null);
			jContentPane.add(lblCicloDestino, null);
			jContentPane.add(getTxtCicloDestino(), null);
			jContentPane.add(lblDsMotivo, null);
			jContentPane.add(getTxtDsMotivo(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(300, 494, 91, 21));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar alteração de classe e sair");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes btnConfirmar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton();
			btnConfirmar.setBounds(new Rectangle(200, 494, 91, 21));
			btnConfirmar.setText("Confirmar");
			btnConfirmar.setToolTipText("Confirmar alteração de classe");
			btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnConfirmar;
	}

	/**
	 * This method initializes txtClasse	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtClasse() {
		if (txtClasse == null) {
			txtClasse = new JTextField();
			txtClasse.setBounds(new Rectangle(74, 15, 320, 22));
			txtClasse.setToolTipText("Classe que o aluno está matriculado atualmente");
		}
		return txtClasse;
	}

	/**
	 * This method initializes txtCurso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCurso() {
		if (txtCurso == null) {
			txtCurso = new JTextField();
			txtCurso.setBounds(new Rectangle(40, 44, 320, 22));
			txtCurso.setToolTipText("Curso da classe atual");
		}
		return txtCurso;
	}

	/**
	 * This method initializes txtCiclo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCiclo() {
		if (txtCiclo == null) {
			txtCiclo = new JTextField();
			txtCiclo.setBounds(new Rectangle(40, 74, 40, 22));
			txtCiclo.setToolTipText("Ciclo da classe atual");
		}
		return txtCiclo;
	}

	/**
	 * This method initializes jrbTransferencia	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbTransferencia() {
		if (jrbTransferencia == null) {
			jrbTransferencia = new JRadioButton();
			radioGroup.add(jrbTransferencia);
			jrbTransferencia.setBounds(new Rectangle(11, 134, 105, 21));
			jrbTransferencia.setText("Transferência");
			jrbTransferencia.setToolTipText("Transferência de classe");
		}
		return jrbTransferencia;
	}

	/**
	 * This method initializes jrbDesistencia	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbDesistencia() {
		if (jrbDesistencia == null) {
			jrbDesistencia = new JRadioButton();
			radioGroup.add(jrbDesistencia);
			jrbDesistencia.setBounds(new Rectangle(120, 134, 97, 21));
			jrbDesistencia.setText("Desistência");
			jrbDesistencia.setToolTipText("Desistência do curso");
		}
		return jrbDesistencia;
	}

	/**
	 * This method initializes jrbTrancamento	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbTrancamento() {
		if (jrbTrancamento == null) {
			jrbTrancamento = new JRadioButton();
			radioGroup.add(jrbTrancamento);
			jrbTrancamento.setBounds(new Rectangle(218, 134, 105, 21));
			jrbTrancamento.setText("Trancamento");
			jrbTrancamento.setToolTipText("Trancamento de matrícula");
		}
		return jrbTrancamento;
	}

	/**
	 * This method initializes txtDtAlteracao	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JTextField getTxtDtAlteracao() {
		if (txtDtAlteracao == null) {
			txtDtAlteracao = new JFormattedTextField(setMascara("##/##/####"));
			txtDtAlteracao.setBounds(new Rectangle(101, 164, 80, 22));
			txtDtAlteracao.setToolTipText("Data da alteração do curso");
		}
		return txtDtAlteracao;
	}

	/**
	 * This method initializes cmbClasseDestino	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbClasseDestino() {
		if (cmbClasseDestino == null) {
			@SuppressWarnings("unused")
			Vector classes = new Vector();
			Aluno consultar = new Aluno();			
			classes = consultar.consultarCmbClasses(getNmCurso());
			cmbClasseDestino = new JComboBox(classes);
			cmbClasseDestino.setBounds(new Rectangle(11, 214, 320, 22));
			cmbClasseDestino.setToolTipText("Classe de destino");
		}
		return cmbClasseDestino;
	}

	/**
	 * This method initializes txtCursoDestino	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCursoDestino() {
		if (txtCursoDestino == null) {
			txtCursoDestino = new JTextField();
			txtCursoDestino.setBounds(new Rectangle(40, 284, 320, 22));
			txtCursoDestino.setToolTipText("Curso da classe de destino");
		}
		return txtCursoDestino;
	}

	/**
	 * This method initializes txtCicloDestino	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCicloDestino() {
		if (txtCicloDestino == null) {
			txtCicloDestino = new JTextField();
			txtCicloDestino.setBounds(new Rectangle(40, 314, 40, 20));
			txtCicloDestino.setToolTipText("Ciclo de destino");
		}
		return txtCicloDestino;
	}

	/**
	 * This method initializes txtDsMotivo	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsMotivo() {
		if (txtDsMotivo == null) {
			txtDsMotivo = new JTextArea();
			txtDsMotivo.setBounds(new Rectangle(12, 364, 380, 109));
			txtDsMotivo.setToolTipText("Motivo da alteração de classe");
		}
		return txtDsMotivo;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
