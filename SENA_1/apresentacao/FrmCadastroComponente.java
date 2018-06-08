/**
 * 
 */
package apresentacao;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import negocios.ComponenteCurricular;

/**
 * @author Sullyvan Puppi
 *
 * Formulário de cadastro de componente curricular
 */
public class FrmCadastroComponente extends JInternalFrame {
	
	//--Tabela cursoComponentes
	private JScrollPane jspTbCursoComponente = null;
	private JTable jtbCursoComponente = null;
	
	//----Atributo para armazenar os itens da tabela componentes(linhasXcolunas)
	DefaultTableModel tabelaCursoComponente;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaCursoComponente(){
		return this.tabelaCursoComponente;
	}
	public void construirTabelaCursoComponentes(){
		jtbCursoComponente = new JTable(getTabelaCursoComponente());
		jspTbCursoComponente = new JScrollPane( jtbCursoComponente );
		jspTbCursoComponente.setBounds(324, 64, 260, 145);
		jContentPane.add(jspTbCursoComponente, null);
	}
	public void consultarCursoComponente(String nmComponente){
		ComponenteCurricular consultar = new ComponenteCurricular();
		consultar.consultarComponenteCurso(nmComponente);
		this.tabelaCursoComponente = consultar.getTabelaComponentes();
		if(tabelaCursoComponente.getRowCount()<=0){
			Object linhaD[] = new Object[1];
			linhaD[0] = "";
			tabelaCursoComponente.addRow(linhaD);
			construirTabelaCursoComponentes();			
		}else{
			construirTabelaCursoComponentes();			
		}
	}
	//----Permissão de acordo com o grupo de usuários
	private int permissao;	
	
	public int getPermissao(){
		return permissao;	
	}
	public void setPermissao(int x){
		this.permissao = x;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1140358502504345169L;
	private JPanel jContentPane = null;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnEditar = null;
	private JLabel lblComponente = null;
	private JTextField txtNmComponente = null;
	private JLabel lblDsComponente = null;
	private JTextArea txtDsComponente = null;
	private JLabel lblCursos = null;
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroComponente(JDesktopPane desktop, int permissao, int op, String nmComponente) {
		super();
		initialize(op, nmComponente);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int op, String nmComponente) {
		this.setSize(600, 285);
		this.setContentPane(getJContentPane());
		String titulo = "";
		if(op==0){
			titulo = "][ Novo cadastro de componente curricular ][";
		}else{
			titulo = "][ Cadastro de componente curricular ][ Componente curricular: "+nmComponente;
		}
		this.setTitle(titulo);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblCursos = new JLabel();
			lblCursos.setBounds(new Rectangle(324, 47, 279, 20));
			lblCursos.setText("Este componente está associado ao(s) curso(s):");
			lblDsComponente = new JLabel();
			lblDsComponente.setBounds(new Rectangle(12, 44, 209, 20));
			lblDsComponente.setText("Descrição do componente curricular");
			lblComponente = new JLabel();
			lblComponente.setBounds(new Rectangle(12, 14, 184, 20));
			lblComponente.setText("Nome do componente curricular");
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-50, 213, 722, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setForeground(new Color(190, 190, 190));
			lblDivisao.setText("_____________________________________________________________________________________________________________________");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnConfirmar(), null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnEditar(), null);
			jContentPane.add(lblComponente, null);
			jContentPane.add(getTxtNmComponente(), null);
			jContentPane.add(lblDsComponente, null);
			jContentPane.add(getTxtDsComponente(), null);
			jContentPane.add(lblCursos, null);
			consultarCursoComponente(getTxtNmComponente().getText());
		}
		return jContentPane;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton();
			btnConfirmar.setBounds(new Rectangle(392, 233, 91, 22));
			btnConfirmar.setText("Confirmar");
			btnConfirmar.setToolTipText("Confirmar o cadastro");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					ComponenteCurricular cadastrar = new ComponenteCurricular();
					cadastrar.ajustaComponente(getTxtNmComponente().getText(), getTxtDsComponente().getText());
					if(cadastrar.getExisteComponente(getTxtNmComponente().getText())==1){
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Salvar alterações?", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							cadastrar.alterarComponente();
							getTxtNmComponente().setText("");
							getTxtDsComponente().setText("");
							getTxtNmComponente().setEnabled(false);
							getTxtDsComponente().setEnabled(false);
						}
					}else{//Não existe componente curricular cadastrado
						if(cadastrar.validarComponente()>=1){
							JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarComponente()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
						}else{
							cadastrar.cadastrarComponente();
							getTxtNmComponente().setText("");
							getTxtDsComponente().setText("");
							getTxtNmComponente().setEnabled(false);
							getTxtDsComponente().setEnabled(false);
							//construir tabela
						}
						
					}
				}
			});
		}
		return btnConfirmar;
	}

	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(492, 233, 91, 22));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar o cadastro");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Fecha a janela
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes btnEditar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton();
			btnEditar.setBounds(new Rectangle(292, 233, 91, 22));
			btnEditar.setText("Editar");
			btnEditar.setToolTipText("Editar o cadastro do usuário");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//habilitarCampos();//Habilita campos para edição
					btnConfirmar.setEnabled(true);
					btnEditar.setEnabled(false);
				}
			});
		}
		return btnEditar;
	}

	/**
	 * This method initializes txtNmComponente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmComponente() {
		if (txtNmComponente == null) {
			txtNmComponente = new JTextField();
			txtNmComponente.setBounds(new Rectangle(164, 14, 420, 22));
		}
		return txtNmComponente;
	}

	/**
	 * This method initializes txtDsComponente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsComponente() {
		if (txtDsComponente == null) {
			txtDsComponente = new JTextArea();
			txtDsComponente.setBounds(new Rectangle(12, 64, 260, 145));
		}
		return txtDsComponente;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
