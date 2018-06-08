package apresentacao;

//Imports de classes internas do java
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FrmCursoDivulgacao extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9157627943903008392L;
	//---Ajuste da barra de título
	//-----Titulo da janela
	private String titulo ="";
	//Retorna título para barra de título	
	private String getTitulo(){
		return this.titulo;
	}
	//----Permissão de acordo com o grupo de usuários
	private int permissao;	
	public int getPermissao(){
		return permissao;	
	}
	public void setPermissao(int x){
		this.permissao = x;
	}
	//---Ajusta permissão no sistema de acordo com o grupo de usuários----//
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		
			
		}else if(getPermissao()==2){//Coordenador de curso
		
		}else if(getPermissao()==3){//Secretaria
		
		}else if(getPermissao()==4){//Convidado
		
		}
	}
	
	//-------------------Interface com o usuário------------------------------------------------//
	//Atributo que armazena os itens do JComboBox
	private DefaultComboBoxModel cursoTipo;
	private DefaultComboBoxModel cursoArea;
	private DefaultComboBoxModel cursoCiclos;
	private DefaultComboBoxModel cursoDuracaoCiclo;
	//private DefaultComboBoxModel qtCiclosOrdem;	
	//private DefaultComboBoxModel qtCiclosOrdemComponente;
	
	
	private JPanel jContentPane = null;
	private JLabel lblDivisao = null;
	private JTabbedPane abaPrincipal = null;
	private JPanel abaCurso = null;
	private JPanel abaCiclos = null;
	private JPanel abaComponentes = null;
	private JPanel abaProfessores = null;
	private JPanel abaTurmas = null;
	private JPanel abaAtividades = null;
	private JPanel abaFotos = null;
	private JPanel abaDownloads = null;
	private JPanel abaLinks = null;
	//---Aba Curso
	private JButton btnConfirmarCurso = null;
	private JButton btnEditarCurso = null;
	private JButton btnCancelarCurso = null;
	private JTextField txtNmCurso = null;
	private JTextArea txtDsCurso = null;
	private JComboBox cmbArea = null;
	private JComboBox cmbDuracaoCiclo = null;
	private JComboBox cmbTipo = null;
	private JComboBox cmbQtCiclos = null;
	private JTextField txtCoordenador = null;
	//---Aba Ciclo
	private JButton btnConfirmarCiclo = null;
	private JButton btnEditarCiclo = null;
	private JButton btnCancelarCiclo = null;
	//---Aba Componente curricular
	private JButton btnConfirmarComponente = null;
	private JButton btnEditarComponente = null;
	private JButton btnCancelarComponente = null;
	//---Aba professores
	private JButton btnConfirmarProfessor = null;
	private JButton btnEditarProfessor = null;
	private JButton btnCancelarProfessor = null;
	//---Aba Turmas
	private JButton btnConfirmarTurma = null;
	private JButton btnEditarTurma = null;
	private JButton btnCancelarTurma = null;
	//---Aba atividades
	private JButton btnConfirmarAtividade = null;
	private JButton btnEditarAtividade = null;
	private JButton btnCancelarAtividade = null;
	private JButton btnCancelar = null;
	private JButton btnRelatorio = null;
	private JLabel lblCiclos = null;
	private JLabel lblMedidaCiclos = null;
	private JLabel lblNmCiclo = null;
	private JTextField txtNmCiclo = null;
	private JLabel lblDsCiclo = null;
	private JTextArea txtDsCiclo = null;
	private JLabel lblNmComponente = null;
	private JTextField txtNmComponente = null;
	private JLabel lblDsComponente = null;
	private JTextArea txtDsComponente = null;
	private JLabel lblComponentes = null;
	private JLabel lblMedidaComponente = null;
	private JLabel lblHistorico = null;
	private JTextArea txtDsHistorico = null;
	private JLabel lblPerfil = null;
	private JTextArea txtDsPerdilEstudante = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public FrmCursoDivulgacao(JDesktopPane desktop, int permissao, String nmCurso) {
		super();
		this.titulo = nmCurso;
		initialize(permissao);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int permissao) {
		this.setSize(744, 560);
		this.setTitle("][ Divulgação de cuso ][ Curso: "+getTitulo());
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getAbaPrincipal(), null);
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-44, 490, 830, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao.setForeground(new Color(190, 190, 190));
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnCancelar(), null);			
			jContentPane.add(getBtnRelatorio(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes abaPrincipal	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getAbaPrincipal() {
		if (abaPrincipal == null) {
			abaPrincipal = new JTabbedPane();
			abaPrincipal.setBounds(new Rectangle(2, 2, 737, 496));
			abaPrincipal.addTab("Curso", null, getAbaCurso(), null);
			abaPrincipal.addTab("Ciclos", null, getAbaCiclos(), null);
			abaPrincipal.addTab("Componentes curriculares", null, getAbaComponentes(), null);
			abaPrincipal.addTab("professores", null, getAbaProfessores(), null);
			abaPrincipal.addTab("Turmas", null, getAbaTurmas(), null);
			abaPrincipal.addTab("Atividades", null, getAbaAtividades(), null);
			abaPrincipal.addTab("Fotos", null, getAbaFotos(), null);
			abaPrincipal.addTab("Downloads", null, getAbaDownloads(), null);
			abaPrincipal.addTab("Links úteis", null, getAbaLinks(), null);
		}
		return abaPrincipal;
	}
	/**
	 * This method initializes abaCurso	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaCurso() {
		if (abaCurso == null) {
			lblPerfil = new JLabel();
			lblPerfil.setBounds(new Rectangle(12, 331, 108, 20));
			lblPerfil.setText("Perfil do estudante");
			lblHistorico = new JLabel();
			lblHistorico.setBounds(new Rectangle(14, 231, 53, 20));
			lblHistorico.setText("Histórico");
			JLabel lblDsCurso = new JLabel();
			lblDsCurso.setBounds(new Rectangle(12, 131, 112, 20));
			lblDsCurso.setText("Descrição do curso");
			JLabel lblCoordenador = new JLabel();
			lblCoordenador.setBounds(new Rectangle(22, 101, 129, 20));
			lblCoordenador.setText("Coordenador do curso");
			JLabel lblDuracao = new JLabel();
			lblDuracao.setBounds(new Rectangle(402, 51, 127, 20));
			lblDuracao.setText("Duração de cada ciclo");
			JLabel lblQtCiclos = new JLabel();
			lblQtCiclos.setBounds(new Rectangle(272, 51, 120, 20));
			lblQtCiclos.setText("Quantidade de ciclos");
			JLabel lblArea = new JLabel();
			lblArea.setBounds(new Rectangle(142, 51, 94, 20));
			lblArea.setText("Área de atuação");
			JLabel lblTipo = new JLabel();
			lblTipo.setBounds(new Rectangle(12, 51, 79, 20));
			lblTipo.setText("Tipo do curso");
			JLabel lblCurso = new JLabel();
			lblCurso.setBounds(new Rectangle(12, 21, 143, 20));
			lblCurso.setText("Nome completo do curso");
			abaCurso = new JPanel();
			abaCurso.setLayout(null);
			abaCurso.add(lblCurso, null);
			abaCurso.add(getTxtNmCurso(), null);
			abaCurso.add(lblTipo, null);
			abaCurso.add(getCmbArea(), null);
			abaCurso.add(lblArea, null);
			abaCurso.add(getCmbTipo(), null);
			abaCurso.add(lblQtCiclos, null);
			abaCurso.add(getCmbQtCiclos(), null);
			abaCurso.add(lblDuracao, null);
			abaCurso.add(getCmbDuracaoCiclo(), null);
			abaCurso.add(lblCoordenador, null);
			abaCurso.add(getTxtCoordenador(), null);
			abaCurso.add(getBtnEditarCurso(), null);
			abaCurso.add(lblDsCurso, null);
			abaCurso.add(getTxtDsCurso(), null);
			abaCurso.add(getBtnCancelarCurso(), null);
			abaCurso.add(getBtnConfirmarCurso(), null);
			abaCurso.add(lblHistorico, null);
			abaCurso.add(getTxtDsHistorico(), null);
			abaCurso.add(lblPerfil, null);
			abaCurso.add(getTxtDsPerdilEstudante(), null);
			abaCurso.add(getBtnEditarCurso(), null);
			abaCurso.add(getBtnCancelarCurso(), null);
		}
		return abaCurso;
	}
	/**
	 * This method initializes abaCiclos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaCiclos() {
		if (abaCiclos == null) {
			lblDsCiclo = new JLabel();
			lblDsCiclo.setBounds(new Rectangle(412, 51, 107, 22));
			lblDsCiclo.setText("Descrição do ciclo");
			lblNmCiclo = new JLabel();
			lblNmCiclo.setBounds(new Rectangle(12, 21, 80, 20));
			lblNmCiclo.setText("Título do ciclo");
			lblMedidaCiclos = new JLabel();
			lblMedidaCiclos.setBounds(new Rectangle(12, 71, 365, 242));
			lblMedidaCiclos.setText("JLabel");
			lblCiclos = new JLabel();
			lblCiclos.setBounds(new Rectangle(12, 51, 38, 20));
			lblCiclos.setText("Ciclos");
			abaCiclos = new JPanel();
			abaCiclos.setLayout(null);
			abaCiclos.add(getBtnConfirmarCiclo(), null);
			abaCiclos.add(getBtnEditarCiclo(), null);
			abaCiclos.add(getBtnCancelarCiclo(), null);
			abaCiclos.add(lblCiclos, null);
			abaCiclos.add(lblMedidaCiclos, null);
			abaCiclos.add(lblNmCiclo, null);
			abaCiclos.add(getTxtNmCiclo(), null);
			abaCiclos.add(lblDsCiclo, null);
			abaCiclos.add(getTxtDsCiclo(), null);
		}
		return abaCiclos;
	}
	/**
	 * This method initializes abaComponentes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaComponentes() {
		if (abaComponentes == null) {
			lblMedidaComponente = new JLabel();
			lblMedidaComponente.setBounds(new Rectangle(12, 71, 365, 242));
			lblMedidaComponente.setText("JLabel");
			lblComponentes = new JLabel();
			lblComponentes.setBounds(new Rectangle(12, 51, 155, 20));
			lblComponentes.setText("Componentes curriculares");
			lblDsComponente = new JLabel();
			lblDsComponente.setBounds(new Rectangle(412, 51, 210, 22));
			lblDsComponente.setText("Descrição do componente curricular");
			lblNmComponente = new JLabel();
			lblNmComponente.setBounds(new Rectangle(12, 21, 185, 20));
			lblNmComponente.setText("Nome do componente curricular");
			abaComponentes = new JPanel();
			abaComponentes.setLayout(null);
			abaComponentes.add(getBtnConfirmarComponente(), null);
			abaComponentes.add(getBtnEditarComponente(), null);
			abaComponentes.add(getBtnCancelarComponente(), null);
			abaComponentes.add(lblNmComponente, null);
			abaComponentes.add(getTxtNmComponente(), null);
			abaComponentes.add(lblDsComponente, null);
			abaComponentes.add(getTxtDsComponente(), null);
			abaComponentes.add(lblComponentes, null);
			abaComponentes.add(lblMedidaComponente, null);
		}
		return abaComponentes;
	}
	/**
	 * This method initializes abaProfessores	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaProfessores() {
		if (abaProfessores == null) {
			abaProfessores = new JPanel();
			abaProfessores.setLayout(null);
			abaProfessores.add(getBtnConfirmarProfessor(), null);
			abaProfessores.add(getBtnEditarProfessor(), null);
			abaProfessores.add(getBtnCancelarProfessor(), null);
		}
		return abaProfessores;
	}
	/**
	 * This method initializes abaTurmas	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaTurmas() {
		if (abaTurmas == null) {
			abaTurmas = new JPanel();
			abaTurmas.setLayout(null);
			abaTurmas.add(getBtnConfirmarTurma(), null);
			abaTurmas.add(getBtnEditarTurma(), null);
			abaTurmas.add(getBtnCancelarTurma(), null);
		}
		return abaTurmas;
	}
	/**
	 * This method initializes abaAtividades	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaAtividades() {
		if (abaAtividades == null) {
			abaAtividades = new JPanel();
			abaAtividades.setLayout(null);
			abaAtividades.add(getBtnConfirmarAtividade(), null);
			abaAtividades.add(getBtnEditarAtividade(), null);
			abaAtividades.add(getBtnCancelarAtividade(), null);
		}
		return abaAtividades;
	}
	/**
	 * This method initializes abaFotos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaFotos() {
		if (abaFotos == null) {
			abaFotos = new JPanel();
			abaFotos.setLayout(null);
		}
		return abaFotos;
	}
	/**
	 * This method initializes abaDownloads	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaDownloads() {
		if (abaDownloads == null) {
			abaDownloads = new JPanel();
			abaDownloads.setLayout(null);
		}
		return abaDownloads;
	}
	/**
	 * This method initializes abaLinks	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaLinks() {
		if (abaLinks == null) {
			abaLinks = new JPanel();
			abaLinks.setLayout(null);
		}
		return abaLinks;
	}
	
	//---Aba cursos
	/**
	 * This method initializes txtNmCurso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmCurso() {
		if (txtNmCurso == null) {
			txtNmCurso = new JTextField();
			txtNmCurso.setBounds(new Rectangle(130, 21, 594, 22));
			txtNmCurso.setBackground(SystemColor.activeCaptionText);
			txtNmCurso.setToolTipText("Nome completo do curso");
		}
		return txtNmCurso;
	}
	/**
	 * This method initializes cmbArea	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbArea() {
		if (cmbArea == null) {
			cursoArea = new DefaultComboBoxModel();
			cursoArea.addElement(new String("----------"));
			cursoArea.addElement(new String("Exatas"));
			cursoArea.addElement(new String("Humanas"));
			cursoArea.addElement(new String("Biológicas"));
			cmbArea = new JComboBox(cursoArea);
			cmbArea.setBounds(new Rectangle(142, 71, 100, 22));
			cmbArea.setToolTipText("Área de atuação do curso");
		}
		return cmbArea;
	}
	/**
	 * This method initializes cmbTipo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbTipo() {
		if (cmbTipo == null) {
			cursoTipo = new DefaultComboBoxModel();
			cursoTipo.addElement(new String("----------"));
			cursoTipo.addElement(new String("Técnico"));
			cursoTipo.addElement(new String("Técnólogo"));
			cursoTipo.addElement(new String("Bacharelado"));
			cursoTipo.addElement(new String("Doutorado"));
			cursoTipo.addElement(new String("Mestrado"));
			cursoTipo.addElement(new String("Outros"));
			cmbTipo = new JComboBox(cursoTipo);
			cmbTipo.setBounds(new Rectangle(12, 71, 100, 22));
			cmbTipo.setToolTipText("Tipo do curso");
		}
		return cmbTipo;
	}
	/**
	 * This method initializes cmbQtCiclos	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbQtCiclos() {
		if (cmbQtCiclos == null) {
			cursoCiclos = new DefaultComboBoxModel();
			cursoCiclos.addElement(new String("----------"));
			cursoCiclos.addElement(new String("1"));
			cursoCiclos.addElement(new String("2"));
			cursoCiclos.addElement(new String("3"));
			cursoCiclos.addElement(new String("4"));
			cursoCiclos.addElement(new String("5"));
			cursoCiclos.addElement(new String("6"));
			cursoCiclos.addElement(new String("7"));
			cursoCiclos.addElement(new String("8"));
			cursoCiclos.addElement(new String("9"));
			cursoCiclos.addElement(new String("10"));
			cursoCiclos.addElement(new String("11"));
			cursoCiclos.addElement(new String("12"));
			cmbQtCiclos = new JComboBox(cursoCiclos);
			cmbQtCiclos.setBounds(new Rectangle(272, 71, 100, 22));
			cmbQtCiclos.setToolTipText("Quantidade de ciclos que o curso possui");
		}
		return cmbQtCiclos;
	}
	/**
	 * This method initializes cmbDuracaoCiclo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbDuracaoCiclo() {
		if (cmbDuracaoCiclo == null) {
			cursoDuracaoCiclo = new DefaultComboBoxModel();
			cursoDuracaoCiclo.addElement(new String("----------"));
			cursoDuracaoCiclo.addElement(new String("Bimestral"));
			cursoDuracaoCiclo.addElement(new String("Trimestral"));
			cursoDuracaoCiclo.addElement(new String("Semestral"));
			cursoDuracaoCiclo.addElement(new String("Anual"));
			cursoDuracaoCiclo.addElement(new String("Outros"));
			cmbDuracaoCiclo = new JComboBox(cursoDuracaoCiclo);
			cmbDuracaoCiclo.setBounds(new Rectangle(402, 71, 100, 22));
			cmbDuracaoCiclo.setToolTipText("Duração de cada ciclo do curso");
		}
		return cmbDuracaoCiclo;
	}
	/**
	 * This method initializes txtCoordenador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCoordenador() {
		if (txtCoordenador == null) {
			txtCoordenador = new JTextField();
			txtCoordenador.setBounds(new Rectangle(130, 101, 320, 22));
			txtCoordenador.setBackground(SystemColor.activeCaptionText);
			txtCoordenador.setToolTipText("Nome do coordenador do curso");
		}
		return txtCoordenador;
	}
	/**
	 * This method initializes txtDsCurso	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsCurso() {
		if (txtDsCurso == null) {
			txtDsCurso = new JTextArea();
			txtDsCurso.setBounds(new Rectangle(12, 151, 711, 70));
			txtDsCurso.setBackground(SystemColor.activeCaptionText);
			txtDsCurso.setToolTipText("Descrição do curso");
		}
		return txtDsCurso;
	}
	/**
	 * This method initializes btnConfirmarCurso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmarCurso() {
		if (btnConfirmarCurso == null) {
			btnConfirmarCurso = new JButton();
			btnConfirmarCurso.setBounds(new Rectangle(630, 445, 91, 22));
			btnConfirmarCurso.setText("Confirmar");
			btnConfirmarCurso.setToolTipText("Confirmar dados do curso");
			btnConfirmarCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnConfirmarCurso;
	}
	/**
	 * This method initializes btnEditarCurso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarCurso() {
		if (btnEditarCurso == null) {
			btnEditarCurso = new JButton();
			btnEditarCurso.setBounds(new Rectangle(530, 445, 91,22));
			btnEditarCurso.setText("Editar");
			btnEditarCurso.setToolTipText("Editar dados do curso");
			btnEditarCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnEditarCurso;
	}
	/**
	 * This method initializes btnCancelarCurso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarCurso() {
		if (btnCancelarCurso == null) {
			btnCancelarCurso = new JButton();
			btnCancelarCurso.setBounds(new Rectangle(430, 445, 91, 22));
			btnCancelarCurso.setText("Cancelar");
			btnCancelarCurso.setToolTipText("Cancelar alteração no curso");
			btnCancelarCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnCancelarCurso;
	}
	
	//-----Aba ciclos
	/**
	 * This method initializes btnConfirmarCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmarCiclo() {
		if (btnConfirmarCiclo == null) {
			btnConfirmarCiclo = new JButton();
			btnConfirmarCiclo.setBounds(new Rectangle(630, 445, 91, 22));
			btnConfirmarCiclo.setText("Confirmar");
			btnConfirmarCiclo.setToolTipText("Confirmar dados do Ciclo");
			btnConfirmarCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnConfirmarCiclo;
	}
	/**
	 * This method initializes btnEditarCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarCiclo() {
		if (btnEditarCiclo == null) {
			btnEditarCiclo = new JButton();
			btnEditarCiclo.setBounds(new Rectangle(530, 445, 91,22));
			btnEditarCiclo.setText("Editar");
			btnEditarCiclo.setToolTipText("Editar dados do Ciclo");
			btnEditarCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnEditarCiclo;
	}
	/**
	 * This method initializes btnCancelarCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarCiclo() {
		if (btnCancelarCiclo == null) {
			btnCancelarCiclo = new JButton();
			btnCancelarCiclo.setBounds(new Rectangle(430, 445, 91, 22));
			btnCancelarCiclo.setText("Cancelar");
			btnCancelarCiclo.setToolTipText("Cancelar alteração no Ciclo");
			btnCancelarCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnCancelarCiclo;
	}
	//--Aba Componente
	/**
	 * This method initializes btnConfirmarComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmarComponente() {
		if (btnConfirmarComponente == null) {
			btnConfirmarComponente = new JButton();
			btnConfirmarComponente.setBounds(new Rectangle(630, 445, 91, 22));
			btnConfirmarComponente.setText("Confirmar");
			btnConfirmarComponente.setToolTipText("Confirmar dados do Componente curricular");
			btnConfirmarComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnConfirmarComponente;
	}
	/**
	 * This method initializes btnEditarComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarComponente() {
		if (btnEditarComponente == null) {
			btnEditarComponente = new JButton();
			btnEditarComponente.setBounds(new Rectangle(530, 445, 91,22));
			btnEditarComponente.setText("Editar");
			btnEditarComponente.setToolTipText("Editar dados do Componente curricular");
			btnEditarComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnEditarComponente;
	}
	/**
	 * This method initializes btnCancelarComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarComponente() {
		if (btnCancelarComponente == null) {
			btnCancelarComponente = new JButton();
			btnCancelarComponente.setBounds(new Rectangle(430, 445, 91, 22));
			btnCancelarComponente.setText("Cancelar");
			btnCancelarComponente.setToolTipText("Cancelar alteração no Componente curricular");
			btnCancelarComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnCancelarComponente;
	}
	//---Aba professores
	/**
	 * This method initializes btnConfirmarProfessor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmarProfessor() {
		if (btnConfirmarProfessor == null) {
			btnConfirmarProfessor = new JButton();
			btnConfirmarProfessor.setBounds(new Rectangle(630, 445, 91, 22));
			btnConfirmarProfessor.setText("Confirmar");
			btnConfirmarProfessor.setToolTipText("Confirmar dados do Professor");
			btnConfirmarProfessor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnConfirmarProfessor;
	}
	/**
	 * This method initializes btnEditarProfessor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarProfessor() {
		if (btnEditarProfessor == null) {
			btnEditarProfessor = new JButton();
			btnEditarProfessor.setBounds(new Rectangle(530, 445, 91,22));
			btnEditarProfessor.setText("Editar");
			btnEditarProfessor.setToolTipText("Editar dados do Professor");
			btnEditarProfessor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnEditarProfessor;
	}
	/**
	 * This method initializes btnCancelarProfessor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarProfessor() {
		if (btnCancelarProfessor == null) {
			btnCancelarProfessor = new JButton();
			btnCancelarProfessor.setBounds(new Rectangle(430, 445, 91, 22));
			btnCancelarProfessor.setText("Cancelar");
			btnCancelarProfessor.setToolTipText("Cancelar alteração no Professor");
			btnCancelarProfessor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnCancelarProfessor;
	}
	//---Aba Turmas
	/**
	 * This method initializes btnConfirmarTurma	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmarTurma() {
		if (btnConfirmarTurma == null) {
			btnConfirmarTurma = new JButton();
			btnConfirmarTurma.setBounds(new Rectangle(630, 445, 91, 22));
			btnConfirmarTurma.setText("Confirmar");
			btnConfirmarTurma.setToolTipText("Confirmar dados das Turmas");
			btnConfirmarTurma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnConfirmarTurma;
	}
	/**
	 * This method initializes btnEditarTurma	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarTurma() {
		if (btnEditarTurma == null) {
			btnEditarTurma = new JButton();
			btnEditarTurma.setBounds(new Rectangle(530, 445, 91,22));
			btnEditarTurma.setText("Editar");
			btnEditarTurma.setToolTipText("Editar dados das Turmas");
			btnEditarTurma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnEditarTurma;
	}
	/**
	 * This method initializes btnCancelarTurma	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarTurma() {
		if (btnCancelarTurma == null) {
			btnCancelarTurma = new JButton();
			btnCancelarTurma.setBounds(new Rectangle(430, 445, 91, 22));
			btnCancelarTurma.setText("Cancelar");
			btnCancelarTurma.setToolTipText("Cancelar alteração em Turmas");
			btnCancelarTurma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnCancelarTurma;
	}
	//---Aba Atividades
	/**
	 * This method initializes btnConfirmarAtividade	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmarAtividade() {
		if (btnConfirmarAtividade == null) {
			btnConfirmarAtividade = new JButton();
			btnConfirmarAtividade.setBounds(new Rectangle(630, 445, 91, 22));
			btnConfirmarAtividade.setText("Confirmar");
			btnConfirmarAtividade.setToolTipText("Confirmar dados das Atividades");
			btnConfirmarAtividade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnConfirmarAtividade;
	}
	/**
	 * This method initializes btnEditarAtividade	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarAtividade() {
		if (btnEditarAtividade == null) {
			btnEditarAtividade = new JButton();
			btnEditarAtividade.setBounds(new Rectangle(530, 445, 91,22));
			btnEditarAtividade.setText("Editar");
			btnEditarAtividade.setToolTipText("Editar dados das Atividades");
			btnEditarAtividade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnEditarAtividade;
	}
	/**
	 * This method initializes btnCancelarAtividade	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarAtividade() {
		if (btnCancelarAtividade == null) {
			btnCancelarAtividade = new JButton();
			btnCancelarAtividade.setBounds(new Rectangle(430, 445, 91, 22));
			btnCancelarAtividade.setText("Cancelar");
			btnCancelarAtividade.setToolTipText("Cancelar alteração em Atividades");
			btnCancelarAtividade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnCancelarAtividade;
	}
	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(630, 509, 91, 22));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar e sair");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}
	/**
	 * This method initializes btnRelatorio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRelatorio() {
		if (btnRelatorio == null) {
			btnRelatorio = new JButton();
			btnRelatorio.setBounds(new Rectangle(530, 509, 91, 22));
			btnRelatorio.setText("Relatório");
			btnRelatorio.setToolTipText("Relatório com os dados de divulgação do curso");
			btnRelatorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnRelatorio;
	}
	/**
	 * This method initializes txtNmCiclo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmCiclo() {
		if (txtNmCiclo == null) {
			txtNmCiclo = new JTextField();
			txtNmCiclo.setBounds(new Rectangle(76, 21, 594, 22));
			txtNmCiclo.setBackground(SystemColor.activeCaptionText);
			txtNmCiclo.setToolTipText("Título do ciclo no curso");
		}
		return txtNmCiclo;
	}
	/**
	 * This method initializes txtDsCiclo	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsCiclo() {
		if (txtDsCiclo == null) {
			txtDsCiclo = new JTextArea();
			txtDsCiclo.setBounds(new Rectangle(412, 71, 309, 127));
			txtDsCiclo.setBackground(SystemColor.activeCaptionText);
			txtDsCiclo.setToolTipText("Descrição do ciclo no curso");
		}
		return txtDsCiclo;
	}
	/**
	 * This method initializes txtNmComponente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmComponente() {
		if (txtNmComponente == null) {
			txtNmComponente = new JTextField();
			txtNmComponente.setBounds(new Rectangle(164, 21, 420, 22));
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
			txtDsComponente.setBounds(new Rectangle(412, 71, 309, 127));
			txtDsComponente.setToolTipText("Descrição do componente curricular");
		}
		return txtDsComponente;
	}
	/**
	 * This method initializes txtDsHistorico	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsHistorico() {
		if (txtDsHistorico == null) {
			txtDsHistorico = new JTextArea();
			txtDsHistorico.setBounds(new Rectangle(12, 251, 711, 70));
		}
		return txtDsHistorico;
	}
	/**
	 * This method initializes txtDsPerdilEstudante	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsPerdilEstudante() {
		if (txtDsPerdilEstudante == null) {
			txtDsPerdilEstudante = new JTextArea();
			txtDsPerdilEstudante.setBounds(new Rectangle(12, 351, 711, 70));
		}
		return txtDsPerdilEstudante;
	}
	
}
