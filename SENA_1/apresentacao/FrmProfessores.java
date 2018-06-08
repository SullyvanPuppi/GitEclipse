package apresentacao;

//---Imports de classe internas do java
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
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

//--Importando classe responsável pela lógica do formulário Professores
import negocios.Professor;

/**
 * 
 * @author Sullyvan Puppi
 *
 */
public class FrmProfessores extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6566005901390126258L;
	private JPanel jContentPane = null;
	private JLabel lblProfessores = null;
	private JTextField txtNmProfessor = null;
	private JLabel lblComponente = null;
	private JComboBox cmbComponente = null;
	
	//--Instanciação do formulário de cadastro de professor
	FrmCadastroProfessor formCadastroProfessor;

	//Criação da área de trabalho do formulário usuários
	private JDesktopPane desk = null;
	//Direitos de usuário determinado pelo grupo
	private int permissao;
	private JButton btnBuscar = null;
	private JLabel lblDivisao = null;
	private JButton btnCancelar = null;
	private JButton btnNovo = null;
	private JButton btnExibir = null;
	private JButton btnRemover = null;
	private JButton btnRelatorio = null;
	
	private JScrollPane jspTbProfessores = null;

	private JTable jtbProfessores = null;
	
	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		
			getBtnNovo().setEnabled(true);
			getBtnRemover().setEnabled(true);
		}else if(getPermissao()==2){//Coordenador de curso
			getBtnNovo().setEnabled(true);
			getBtnRemover().setEnabled(true);
		}else if(getPermissao()==3){//Secretaria
			getBtnNovo().setEnabled(false);
			getBtnRemover().setEnabled(false);
		}else if(getPermissao()==4){//Convidado
			getBtnNovo().setEnabled(false);
			getBtnRemover().setEnabled(false);
		}
	}
	//---Instanciação da classe Professor
	Professor consultar;	
	//--Atributo para armazenar item selecionado na tabela
	private String selecionado = "";	
	//--Ajustr seleção
	private void setSelecionado(Object selecao){
		this.selecionado = ""+selecao; 
	}
	private String nmProfessor= "";
	private void setNmProfessor(String nome){
		this.nmProfessor = nome;
	}
	public String getNmProfessor(){
		return this.nmProfessor;
	}
	//--Retornar seleção
	public String getSelecionado(){
		return this.selecionado;
	}
	//----Método para construir tabela de pesquisa
	public void construirTabela(){
		jtbProfessores = new JTable(getTabela());
		jspTbProfessores = new JScrollPane( jtbProfessores );
		jspTbProfessores.setBounds(19, 97, 675, 339);
		jtbProfessores.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((Object) jtbProfessores.getValueAt(jtbProfessores.getSelectedRow(), 0));
				setNmProfessor((String) jtbProfessores.getValueAt(jtbProfessores.getSelectedRow(), 1));
			}
		});
		jContentPane.add(jspTbProfessores, null);
	}
	
	//----Atributo para armazenar os itens da tabela(linhasXcolunas)
	DefaultTableModel tabela;
	private JLabel lblCurso = null;
	private JComboBox cmbCurso = null;
	private JLabel lblClasse = null;
	private JComboBox cmbClasse = null;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabela(){
		return this.tabela;
	}	
	//----Consultar camada de negócios - Classe Curso
	public void consultar(String nome, String componente){
		consultar = new Professor();
		consultar.consultar(nome, componente);
		this.tabela = consultar.getTabela();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhum professor cadastrado com o critério de pesquisa solicitado.","Consulta",1);
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
	public FrmProfessores(JDesktopPane desktop, int permissao) {
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
		this.setSize(716, 508);
		this.setTitle("][ Professores ][");
		this.setContentPane(getJContentPane());
		consultar("","Todos");
		ajustaPermissao();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblClasse = new JLabel();
			lblClasse.setBounds(new Rectangle(308, 67, 42, 20));
			lblClasse.setText("Classe");
			lblCurso = new JLabel();
			lblCurso.setBounds(new Rectangle(19, 67, 38, 20));
			lblCurso.setText("Curso");
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-66, 429, 868, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setForeground(new Color(190, 190, 190));
			lblDivisao.setText("_________________________________________________________________________________________________________________________________");
			lblComponente = new JLabel();
			lblComponente.setBounds(new Rectangle(19, 37, 133, 20));
			lblComponente.setText("Componente curricular");
			lblProfessores = new JLabel();
			lblProfessores.setBounds(new Rectangle(19, 7, 59, 20));
			lblProfessores.setText("Professor");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblProfessores, null);
			jContentPane.add(getTxtNmProfessor(), null);
			jContentPane.add(lblComponente, null);
			jContentPane.add(getCmbComponente(), null);
			jContentPane.add(getBtnBuscar(), null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnNovo(), null);
			jContentPane.add(getBtnExibir(), null);
			jContentPane.add(getBtnRemover(), null);
			jContentPane.add(getBtnRelatorio(), null);
			jContentPane.add(lblCurso, null);
			jContentPane.add(getCmbCurso(), null);
			jContentPane.add(lblClasse, null);
			jContentPane.add(getCmbClasse(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes txtNmProfessor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmProfessor() {
		if (txtNmProfessor == null) {
			txtNmProfessor = new JTextField();
			txtNmProfessor.setBounds(new Rectangle(65, 7, 628, 22));
			txtNmProfessor.setToolTipText("Nome do professor");
		}
		return txtNmProfessor;
	}

	/**
	 * This method initializes cmbComponente	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getCmbComponente() {
		Vector cpt = new Vector();
		cpt.addElement("Todos");
		cpt.addElement("Sem componente curricular associado");
		Professor consultar = new Professor();
		cpt.addAll(consultar.consultarComponentes());
		cmbComponente = new JComboBox(cpt);
		cmbComponente.setBounds(new Rectangle(128, 37, 564, 22));
		return cmbComponente;
	}
	/**
	 * This method initializes btnBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setBounds(new Rectangle(602, 68, 91, 22));
			btnBuscar.setText("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jContentPane.remove(jspTbProfessores);
					consultar(getTxtNmProfessor().getText(), (String) cmbComponente.getSelectedItem());
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
			btnCancelar.setToolTipText("Cancelar cadastro e sair");
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
			btnNovo.setToolTipText("Novo cadastro de curso");
			btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((formCadastroProfessor == null) || (formCadastroProfessor.isClosed())) {
					formCadastroProfessor = new FrmCadastroProfessor(desk, getPermissao(), 0, "", "");
					desk.add(formCadastroProfessor, new Integer(2));
					formCadastroProfessor.show();
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
			btnExibir.setToolTipText("Exibir curso selecionado");
			btnExibir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um professor para ser exibido seus dados","Seleção",1);
					}else{
						if ((formCadastroProfessor == null) || (formCadastroProfessor.isClosed())) {
							formCadastroProfessor = new FrmCadastroProfessor(desk, getPermissao(), 1, getSelecionado(), getNmProfessor());
							desk.add(formCadastroProfessor, new Integer(2));
							formCadastroProfessor.show();
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
			btnRemover.setToolTipText("Remover curso selecionado");
			btnRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um professor para ser exluído seus dados","Seleção",1);
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir este professor?\n\n"+getNmProfessor()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							consultar.removerProfessor(getSelecionado());
							jContentPane.remove(jspTbProfessores);
							consultar(getTxtNmProfessor().getText(), (String) cmbComponente.getSelectedItem());
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
			btnRelatorio.setToolTipText("Gerar relatório de cursos com o critério pesquisado");
		}
		return btnRelatorio;
	}
	/**
	 * This method initializes cmbCurso	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbCurso() {
		if (cmbCurso == null) {
			cmbCurso = new JComboBox();
			cmbCurso.setBounds(new Rectangle(48, 67, 250, 22));
			cmbCurso.setToolTipText("Cursos");
		}
		return cmbCurso;
	}
	/**
	 * This method initializes cmbClasse	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbClasse() {
		if (cmbClasse == null) {
			cmbClasse = new JComboBox();
			cmbClasse.setBounds(new Rectangle(340, 67, 250, 22));
			cmbClasse.setToolTipText("Classes");
		}
		return cmbClasse;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"