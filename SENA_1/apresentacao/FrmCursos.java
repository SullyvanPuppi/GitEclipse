package apresentacao;

//Imports de classes internas do java
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

//Importando a classe responsável pela lógica do formulário Cursos
import negocios.Curso;

/**
 * @author Sullyvan Puppi
 *
 * Interface com o usuário - Formulário de Cursos
 */
public class FrmCursos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1286115908953080180L;
	private JPanel jContentPane = null;
	private JLabel lblNome = null;
	private JTextField txtCurso = null;
	private JLabel lblTipo = null;
	private JComboBox cmbTipo = null;
	private JLabel lblArea = null;
	private JComboBox cmbArea = null;
	private JButton btnBuscar = null;
	private JLabel lblDivisao = null;
	private JButton btnCancelar = null;
	private JButton btnNovo = null;
	private JButton btnExibir = null;
	private JButton btnRemover = null;
	private JButton btnRelatorio = null;
	
	private JScrollPane jspTbCursos = null;

	private JTable jtbCursos = null;
	
	//Atributo que armazena os itens do JComboBox
	private DefaultComboBoxModel cursoTipo;
	private DefaultComboBoxModel cursoArea;
	
	//--Instanciação do formulário de cadastro de curso
	FrmCadastroCurso formCadastroCurso;

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
			btnNovo.setEnabled(true);
		}else if(getPermissao()==2){//Coordenador de curso
			btnNovo.setEnabled(true);
		}else if(getPermissao()==3){//Secretaria
			btnNovo.setEnabled(false);
			btnRemover.setEnabled(false);
		}else if(getPermissao()==4){//Convidado
			btnNovo.setEnabled(false);
			btnRemover.setEnabled(false);
		}
	}
	
	//---------------Interface com o usuário--------------------------------------------------//
	/**
	 * This is the xxx default constructor
	 */
	public FrmCursos(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		initialize();
		ajustaPermissao();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(666, 500);
		this.setTitle("][ Cursos cadastrados ][");
		this.setContentPane(getJContentPane());
		cursoTipo.addElement(new String("Todos"));
		cursoTipo.addElement(new String("Técnico"));
		cursoTipo.addElement(new String("Técnólogo"));
		cursoTipo.addElement(new String("Bacharelado"));
		cursoTipo.addElement(new String("Doutorado"));
		cursoTipo.addElement(new String("Mestrado"));
		cursoArea.addElement(new String("Todas"));
		cursoArea.addElement(new String("Exatas"));
		cursoArea.addElement(new String("Humanas"));
		cursoArea.addElement(new String("Biológicas"));
		consultar("","Todos","Todas");	
	}
	//---Instanciação da classe Cursos
	Curso consultar;	
	//--Atributo para armazenar item selecionado na tabela
	private String selecionado = "";	
	//--Ajustr seleção
	private void setSelecionado(String selecao){
		this.selecionado = selecao; 
	}
	//--Retornar seleção
	public String getSelecionado(){
		return this.selecionado;
	}
	//----Método para construir tabela de pesquisa
	public void construirTabela(){
		jtbCursos = new JTable(getTabela());
		jspTbCursos = new JScrollPane( jtbCursos );
		jspTbCursos.setBounds(19, 67, 625, 369);
		jtbCursos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado("");
				setSelecionado((String)(jtbCursos.getValueAt(jtbCursos.getSelectedRow(), 0)));
			}
		});
		jContentPane.add(jspTbCursos, null);
	}
	
	//----Atributo para armazenar os itens da tabela(linhasXcolunas)
	DefaultTableModel tabela;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabela(){
		return this.tabela;
	}	
	//----Consultar camada de negócios - Classe Curso
	public void consultar(String nome, String tipo, String area){
		consultar = new Curso();
		consultar.consultar(nome, tipo, area);
		this.tabela = consultar.getTabela();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhum Curso cadastrado com o critério de pesquisa solicitado.","Consulta",1);
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
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-66, 429, 774, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setForeground(new Color(190, 190, 190));
			lblDivisao.setText("_________________________________________________________________________________________________________________________________");
			lblArea = new JLabel();
			lblArea.setBounds(new Rectangle(145, 38, 28, 20));
			lblArea.setText("Área");
			lblTipo = new JLabel();
			lblTipo.setBounds(new Rectangle(28, 37, 24, 20));
			lblTipo.setText("Tipo");
			lblNome = new JLabel();
			lblNome.setBounds(new Rectangle(19, 7, 38, 20));
			lblNome.setText("Curso");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblNome, null);
			jContentPane.add(getTxtCurso(), null);
			jContentPane.add(lblTipo, null);
			jContentPane.add(getCmbTipo(), null);
			jContentPane.add(lblArea, null);
			jContentPane.add(getCmbArea(), null);
			jContentPane.add(getBtnBuscar(), null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnNovo(), null);
			jContentPane.add(getBtnExibir(), null);
			jContentPane.add(getBtnRemover(), null);
			jContentPane.add(getBtnRelatorio(), null);
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes txtCurso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCurso() {
		if (txtCurso == null) {
			txtCurso = new JTextField();
			txtCurso.setBounds(new Rectangle(50, 7, 594, 22));
			txtCurso.setToolTipText("Nome do curso");
		}
		return txtCurso;
	}

	/**
	 * This method initializes cmbTipo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbTipo() {
		if (cmbTipo == null) {
			cursoTipo = new DefaultComboBoxModel();
			cmbTipo = new JComboBox(cursoTipo);
			cmbTipo.setBounds(new Rectangle(50, 37,  87, 22));
			cmbTipo.setToolTipText("Tipo do curso");
		}
		return cmbTipo;
	}

	/**
	 * This method initializes cmbArea	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbArea() {
		if (cmbArea == null) {
			cursoArea = new DefaultComboBoxModel();
			cmbArea = new JComboBox(cursoArea);
			cmbArea.setBounds(new Rectangle(170, 37, 87, 22));
			cmbArea.setToolTipText("Área de atuação do curso");
		}
		return cmbArea;
	}
	

	/**
	 * This method initializes btnBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setBounds(new Rectangle(552, 37, 91, 22));
			btnBuscar.setText("Buscar");
			btnBuscar.setToolTipText("Buscar cursos com os critérios selecionados");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jContentPane.remove(jspTbCursos);
					consultar(getTxtCurso().getText(), (String) cmbTipo.getSelectedItem(), (String) cmbArea.getSelectedItem());
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
			btnCancelar.setBounds(new Rectangle(552, 449, 91, 22));
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
			btnNovo.setBounds(new Rectangle(452, 449, 91, 22));
			btnNovo.setText("Novo");
			btnNovo.setToolTipText("Novo cadastro de curso");
			btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((formCadastroCurso == null) || (formCadastroCurso.isClosed())) {
					formCadastroCurso = new FrmCadastroCurso(desk, getPermissao(), 0, "");
					desk.add(formCadastroCurso, new Integer(2));
					formCadastroCurso.show();
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
			btnExibir.setBounds(new Rectangle(252, 449, 91, 22));
			btnExibir.setText("Exibir");
			btnExibir.setToolTipText("Exibir curso selecionado");
			btnExibir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um curso para ser exibido seus dados","Seleção",1);
					}else{
						if ((formCadastroCurso == null) || (formCadastroCurso.isClosed())) {
							formCadastroCurso = new FrmCadastroCurso(desk, getPermissao(), 1, getSelecionado());
							desk.add(formCadastroCurso, new Integer(2));
							formCadastroCurso.show();
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
			btnRemover.setBounds(new Rectangle(352, 449, 91, 22));
			btnRemover.setText("Remover");
			btnRemover.setToolTipText("Remover curso selecionado");
			btnRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um curso para ser exluído seus dados","Seleção",1);
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir este curso?\n\n"+getSelecionado()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							consultar.removerCurso(getSelecionado());
							jContentPane.remove(jspTbCursos);
							consultar(getTxtCurso().getText(), (String) cmbTipo.getSelectedItem(), (String) cmbArea.getSelectedItem());
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
			btnRelatorio.setBounds(new Rectangle(152, 449, 91, 22));
			btnRelatorio.setText("Relatório");
			btnRelatorio.setToolTipText("Gerar relatório de cursos com o critério pesquisado");
		}
		return btnRelatorio;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
