package apresentacao;

//---Imports de classes internas do java
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

//--Importando classe responsável pela lógica do formulário Alunos
import negocios.Aluno;

/**
 * 
 * @author Sullyvan Puppi
 *
 */
public class FrmAlunos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6566005901390126258L;
	private JPanel jContentPane = null;
	private JLabel lblAlunos = null;
	private JTextField txtNmAluno = null;
	private JButton btnBuscar = null;
	private JLabel lblDivisao = null;
	private JButton btnCancelar = null;
	private JButton btnNovo = null;
	private JButton btnExibir = null;
	private JButton btnRemover = null;
	private JButton btnRelatorio = null;
	private JScrollPane jspTbAlunos = null;
	private JTable jtbAlunos = null;
	private JLabel lblCurso = null;
	private JComboBox cmbCurso = null;
	private JLabel lblClasse = null;
	private JComboBox cmbClasse = null;

	//--Instanciação do formulário de cadastro de Aluno
	FrmCadastroAluno formCadastroAluno;

	//Criação da área de trabalho do formulário usuários
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		
			getBtnNovo().setEnabled(true);
			getBtnRemover().setEnabled(true);
		}else if(getPermissao()==2){//Coordenador de curso
			getBtnNovo().setEnabled(false);
			getBtnRemover().setEnabled(false);
		}else if(getPermissao()==3){//Secretaria
			getBtnNovo().setEnabled(true);
			getBtnRemover().setEnabled(true);
		}else if(getPermissao()==4){//Convidado
			getBtnNovo().setEnabled(false);
			getBtnRemover().setEnabled(false);
		}
	}
	//---Instanciação da classe Aluno
	Aluno consultar;	
	//--Atributo para armazenar item selecionado na tabela
	private String selecionado = "";	
	//--Ajustr seleção
	private void setSelecionado(Object selecao){
		this.selecionado = ""+selecao; 
	}
	private String nmAluno= "";
	private void setNmAluno(String nome){
		this.nmAluno = nome;
	}
	public String getNmAluno(){
		return this.nmAluno;
	}
	//----Atributo para armazenar os itens da tabela(linhasXcolunas)	
	DefaultTableModel tabela;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	//--Retornar seleção
	public String getSelecionado(){
		return this.selecionado;
	}
	//----Método para construir tabela de pesquisa
	public void construirTabela(){
		jtbAlunos = new JTable(getTabela());
		jspTbAlunos = new JScrollPane( jtbAlunos );
		jspTbAlunos.setBounds(19, 67, 675, 369);
		jtbAlunos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((Object) jtbAlunos.getValueAt(jtbAlunos.getSelectedRow(), 0));
				setNmAluno((String) jtbAlunos.getValueAt(jtbAlunos.getSelectedRow(), 1));
			}
		});
		jContentPane.add(jspTbAlunos, null);
	}

	//----Consultar camada de negócios - Classe Aluno
	public void consultarAlunos(String nmAluno, String situacao, String curso, String classe){
		consultar = new Aluno();
		consultar.consultarAlunos(nmAluno, situacao, curso, classe);
		this.tabela = consultar.getTabelaAlunos();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado com o critério de pesquisa solicitado.","Consulta",1);
			Object linha[] = new Object[3];
			linha[0] = "";
			linha[1] = "";
			linha[2] = "";
			tabela.addRow(linha);
			construirTabela();			
		}else{
			construirTabela();
		}
	}

	/**
	 * This is the xxx default constructor
	 */
	public FrmAlunos(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(716, 500);
		this.setTitle("][ Alunos ][");
		setNmCurso("----------");
		setClasses(getNmCurso());
		this.setContentPane(getJContentPane());
		consultarAlunos("","----------","----------","----------");
		ajustaPermissao();		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblSituacao = new JLabel();
			lblSituacao.setBounds(new Rectangle(372, 7, 126, 20));
			lblSituacao.setText("Situação de matrícula");
			lblClasse = new JLabel();
			lblClasse.setBounds(new Rectangle(308, 37, 42, 20));
			lblClasse.setText("Classe");
			lblCurso = new JLabel();
			lblCurso.setBounds(new Rectangle(19, 37, 38, 20));
			lblCurso.setText("Curso");
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-66, 429, 868, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setForeground(new Color(190, 190, 190));
			lblDivisao.setText("_________________________________________________________________________________________________________________________________");
			lblAlunos = new JLabel();
			lblAlunos.setBounds(new Rectangle(19, 7, 59, 20));
			lblAlunos.setText("Aluno");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblAlunos, null);
			jContentPane.add(getTxtNmAluno(), null);
			jContentPane.add(getBtnBuscar(), null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnNovo(), null);
			jContentPane.add(getBtnExibir(), null);
			jContentPane.add(getBtnRemover(), null);
			jContentPane.add(getBtnRelatorio(), null);
			jContentPane.add(lblCurso, null);
			jContentPane.add(lblClasse, null);
			jContentPane.add(lblSituacao, null);
			getCmbMatricula();
			getCmbCurso();
			getCmbClasse();
		}
		return jContentPane;
	}

	/**
	 * This method initializes txtNmAluno	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmAluno() {
		if (txtNmAluno == null) {
			txtNmAluno = new JTextField();
			txtNmAluno.setBounds(new Rectangle(48, 7, 320, 22));
			txtNmAluno.setToolTipText("Nome do Aluno");
		}
		return txtNmAluno;
	}

	/**
	 * This method initializes btnBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setBounds(new Rectangle(602, 37, 91, 22));
			btnBuscar.setText("Buscar");
			btnBuscar.setToolTipText("Buscar aluno com os critérios selecionados");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jContentPane.remove(jspTbAlunos);
					consultarAlunos(getTxtNmAluno().getText(), (String) cmbMatricula.getSelectedItem(), (String) cmbCurso.getSelectedItem(), (String) cmbClasse.getSelectedItem());
				}
			});
		}
		return btnBuscar;
	}
	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(602, 449, 91, 22));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar cadastro de aluno e sair");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes btnNovo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setBounds(new Rectangle(502, 449, 91, 22));
			btnNovo.setText("Novo");
			btnNovo.setToolTipText("Novo cadastro de aluno");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((formCadastroAluno == null) || (formCadastroAluno.isClosed())) {
						formCadastroAluno = new FrmCadastroAluno(desk, getPermissao(), 0, "", "");
						desk.add(formCadastroAluno, new Integer(2));
						formCadastroAluno.show();
					}
				}});
		}
		return btnNovo;
	}

	/**
	 * This method initializes btnExibir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnExibir() {
		if (btnExibir == null) {
			btnExibir = new JButton();
			btnExibir.setBounds(new Rectangle(302, 449, 91, 22));
			btnExibir.setText("Exibir");
			btnExibir.setToolTipText("Exibir aluno selecionado");
			btnExibir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um aluno para ser exibido seus dados","Seleção",1);
					}else{
						if ((formCadastroAluno == null) || (formCadastroAluno.isClosed())) {
							formCadastroAluno = new FrmCadastroAluno(desk, getPermissao(), 1, getSelecionado(), getNmAluno());
							desk.add(formCadastroAluno, new Integer(2));
							formCadastroAluno.show();
						}
					}					
				}});
		}
		return btnExibir;
	}

	/**
	 * This method initializes btnRemover	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemover() {
		if (btnRemover == null) {
			btnRemover = new JButton();
			btnRemover.setBounds(new Rectangle(402, 449, 91, 22));
			btnRemover.setText("Remover");
			btnRemover.setToolTipText("Remover aluno selecionado");
			btnRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um Aluno para ser exluído seus dados","Seleção",1);
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir este Aluno?\n\n"+getNmAluno()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							consultar.removerAluno(getSelecionado());
							jContentPane.remove(jspTbAlunos);

							setSelecionado("");
						}
					}					
				}
			});
		}
		return btnRemover;
	}

	/**
	 * This method initializes btnRelatorio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRelatorio() {
		if (btnRelatorio == null) {
			btnRelatorio = new JButton();
			btnRelatorio.setBounds(new Rectangle(202, 449, 91, 22));
			btnRelatorio.setText("Relatório");
			btnRelatorio.setToolTipText("Gerar relatório de alunos com o critério pesquisado");
		}
		return btnRelatorio;
	}
	/**
	 * This method initializes cmbCurso	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JLabel lblSituacao = null;
	private JComboBox cmbMatricula = null;

	@SuppressWarnings("unchecked")
	private void getCmbCurso() {
		Vector cpt = new Vector();
		cpt.addElement("----------");
		Aluno consultar = new Aluno();
		cpt.addAll(consultar.consultarCmbCursos());
		@SuppressWarnings("unused")
		DefaultComboBoxModel cursos = new DefaultComboBoxModel(cpt);
		cmbCurso = new JComboBox(cpt);
		cmbCurso.setBounds(new Rectangle(48, 37, 250, 22));
		cmbCurso.setToolTipText("Cursos");
		cmbCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jContentPane.remove(cmbClasse);
				jContentPane.revalidate();
				setNmCurso((String) cmbCurso.getSelectedItem());
				setClasses(getNmCurso());
				getCmbClasse();
			}
		});
		jContentPane.add(cmbCurso, null);
	}
	private String nmCurso;

	public void setNmCurso(String curso){
		this.nmCurso = curso;
	}
	public String getNmCurso(){
		return this.nmCurso;
	}
	private Vector classes;
	
	public void setClasses(String nmCurso){
		Aluno consultar = new Aluno();
		this.classes = consultar.consultarCmbClasses(getNmCurso());
	}
	public Vector getClasses(){
		return this.classes;
	}
	/**
	 * This method initializes cmbClasse	
	 * 
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private void getCmbClasse() {
			Vector classes = new Vector();
			classes.addElement("----------");
			classes.addElement("Sem classe");
			classes.addAll(getClasses());
			cmbClasse = new JComboBox(classes);
			cmbClasse.setBounds(new Rectangle(340, 37, 250, 22));
			cmbClasse.setToolTipText("Classes");
			jContentPane.add(cmbClasse, null);
	}
	/**
	 * This method initializes cmbMatricula	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private void getCmbMatricula() {
		Vector matricula = new Vector();
		matricula.addElement("----------");
		matricula.addElement("Regular");
		matricula.addElement("Trancada");
		matricula.addElement("Desistente");
		cmbMatricula = new JComboBox(matricula);
		cmbMatricula.setBounds(new Rectangle(475, 7, 114, 22));
		cmbMatricula.setToolTipText("Situação de matrícula do aluno");
		jContentPane.add(cmbMatricula, null);
	}
}