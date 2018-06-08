package apresentacao;

//Imports de Classes internas necess�rias
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

//Importando a classe respons�vel pela l�gica do formu�rio
import negocios.Usuario;

/**
 * @author Sullyvan Puppi
 *
 * Interface com o usu�rio - Formul�rio de Usu�rios
 */
public class FrmUsuarios extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel lblNome = null;

	private JTextField txtNomeUsuario = null;

	private JLabel lblGrupo = null;
	
	private JComboBox cmbGrupoUsuarios = null;

	private JButton btnBuscar = null;

	private JLabel lblDivisao = null;

	private JButton btnExibir = null;

	private JButton btnNovo = null;

	private JScrollPane jspTbUsuarios = null;

	private JTable jtbUsuarios = null;
	
	private JButton btnCancelar = null;
	
	private JButton btnRemover = null;
	
	//Atributo que armazena os items do combobox grupos
	private DefaultComboBoxModel gruposModel;
	
	//Instancia��o das classes dos formul�rios filhos
	FrmCadastroUsuario formCadUsuario;
	
	//Cria��o da �rea de trabalho do formul�rio usu�rios
	private JDesktopPane desk = null;
	
	//Direitos de usu�rio determinado pelo grupo
	private int permissao;
	
	public int getPermissao(){
		return this.permissao;
	}
	//Login do usu�rio atual
	private String login;
	
	public String getLogin(){
		return this.login;
	}
	
	/**
	 * This is the default constructor
	 */
	public FrmUsuarios(JDesktopPane desktop, int permissao, String login) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.login = login;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(514, 480);
		this.setContentPane(getJContentPane());
		this.setTitle("][ Usu�rios cadastrados ][");
		gruposModel.addElement(new String("Selecione um grupo de usu�rios"));
		gruposModel.addElement(new String("Administrador"));
		gruposModel.addElement(new String("Coordenador de cursos"));
		gruposModel.addElement(new String("Secretaria"));
		gruposModel.addElement(new String("Convidado"));
		consultar("","Selecione um grupo de usu�rios");
	}	
	//---Instancia��o da classe Usu�rio
	Usuario consultar;	
	//--Atributo para armazenar item selecionado na tabela
	private String selecionado = "";	
	//--Ajustr sele��o
	private void setSelecionado(String selecao){
		this.selecionado = selecao; 
	}
	//--Retornar sele��o
	public String getSelecionado(){
		return this.selecionado;
	}
	
	//----M�todo para construir tabela de pesquisa
	public void construirTabela(){
		jtbUsuarios = new JTable(getTabela());
		jspTbUsuarios = new JScrollPane( jtbUsuarios );
		jspTbUsuarios.setBounds(10, 67, 490, 348);
		jtbUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado("");
				setSelecionado((String)(jtbUsuarios.getValueAt(jtbUsuarios.getSelectedRow(), 1)));
			}
		});
		jContentPane.add(jspTbUsuarios, null);
	}
	
	//----Atributo para armazenar os itens da tabela(linhasXcolunas)
	DefaultTableModel tabela;

	private JButton btnRelatorio = null;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	
	//----Consultar camada de neg�cios - Classe Usu�rio
	public void consultar(String nome, String grupo){
		consultar = new Usuario();
		consultar.consultar(nome, grupo);
		this.tabela = consultar.getTabela();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhum usu�rio cadastrado com o crit�rio de pesquisa solicitado.","Consulta",1);
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
			lblDivisao.setBounds(new Rectangle(-35, 408, 589, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setForeground(new Color(190, 190, 190));
			lblDivisao.setText("______________________________________________________________________________________________________");
			lblGrupo = new JLabel();
			lblGrupo.setBounds(new Rectangle(19, 37, 111, 20));
			lblGrupo.setText("Grupo de usu�rios");
			lblNome = new JLabel();
			lblNome.setBounds(new Rectangle(19, 7, 34, 20));
			lblNome.setText("Nome");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblNome, null);
			jContentPane.add(getTxtNomeUsuario(), null);
			jContentPane.add(lblGrupo, null);
			jContentPane.add(getCmbGrupoUsuarios(), null);
			jContentPane.add(getBtnBuscar(), null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnExibir(), null);
			jContentPane.add(getBtnNovo(), null);			
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnRemover(), null);
			jContentPane.add(getBtnRelatorio(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes txtNomeUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNomeUsuario() {
		if (txtNomeUsuario == null) {
			txtNomeUsuario = new JTextField();
			txtNomeUsuario.setBounds(new Rectangle(50, 7, 320, 22));
			txtNomeUsuario.setToolTipText("Nome do usu�rio");
		}
		return txtNomeUsuario;
	}

	/**
	 * This method initializes cmbGrupoUsuarios	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbGrupoUsuarios() {
		gruposModel = new DefaultComboBoxModel();
		cmbGrupoUsuarios = new JComboBox(gruposModel);
		cmbGrupoUsuarios.setBounds(new Rectangle(110, 37, 258, 22));
		cmbGrupoUsuarios.setToolTipText("Grupo de usu�rios");
		return cmbGrupoUsuarios;
	}

	/**
	 * This method initializes btnBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setBounds(new Rectangle(410, 37, 90, 22));
			btnBuscar.setText("Buscar");
			btnBuscar.setToolTipText("Buscar usu�rio com os crit�rios selecionados");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jContentPane.remove(jspTbUsuarios);
					consultar(getTxtNomeUsuario().getText(), (String) cmbGrupoUsuarios.getSelectedItem());
				}
			});
		}
		return btnBuscar;
	}

	/**
	 * This method initializes btnExibir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnExibir() {
		if (btnExibir == null) {
			btnExibir = new JButton();
			btnExibir.setBounds(new Rectangle(210, 428, 90, 22));
			btnExibir.setText("Exibir");
			btnExibir.setToolTipText("Exibir cadastro de usu�rio selecionado");
			btnExibir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um usu�rio para ser exibido seus dados","Sele��o",1);
					}else{
						if ((formCadUsuario == null) || (formCadUsuario.isClosed())) {
							formCadUsuario = new FrmCadastroUsuario(desk, getPermissao(), 1, getSelecionado(), getLogin());
							desk.add(formCadUsuario, new Integer(2));
							formCadUsuario.show();
						}	
					}					
				}});
		}
		return btnExibir;
	}

	/**
	 * This method initializes btnNovo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setBounds(new Rectangle(310, 428, 90, 22));
			btnNovo.setText("Novo");
			btnNovo.setToolTipText("Novo cadastro de usu�rio");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((formCadUsuario == null) || (formCadUsuario.isClosed())) {
						formCadUsuario = new FrmCadastroUsuario(desk, getPermissao(), 0, "","x");
						desk.add(formCadUsuario, new Integer(2));
						formCadUsuario.show();
					}
				}});
		}
		return btnNovo;
	}

	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(410, 428, 91, 22));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes btnRemover	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemover() {
		if (btnRemover == null) {
			btnRemover = new JButton();
			btnRemover.setBounds(new Rectangle(110, 428, 91, 22));
			btnRemover.setText("Remover");
			btnRemover.setToolTipText("Remover usu�rio selecionado");
			btnRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um usu�rio para ser exlu�do seus dados","Sele��o",1);
					}else{
						if(getLogin().equals(getSelecionado())){
							JOptionPane.showMessageDialog(null,"Exclus�o de usu�rio em uso n�o � permitido pelo sistema","Opera��o n�o permitida",1);
						}else{
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja realmente excluir este usu�rio?\n\n"+getSelecionado()+"\n\n", "Confirma��o",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								consultar.remover(getSelecionado());
								jContentPane.remove(jspTbUsuarios);
								consultar(getTxtNomeUsuario().getText(), (String) cmbGrupoUsuarios.getSelectedItem());
							}	
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
			btnRelatorio.setBounds(new Rectangle(10, 428, 91, 22));
			btnRelatorio.setText("Relat�rio");
			btnRelatorio.setToolTipText("Relat�rio dos usu�rios pesquisados");
			btnRelatorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnRelatorio;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
