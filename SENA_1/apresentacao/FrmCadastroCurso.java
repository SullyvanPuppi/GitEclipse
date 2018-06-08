/**
 * 
 */
package apresentacao;

//--Imports de classe internas do java
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import negocios.Curso;

/**
 * @author Sullyvan Puppi
 *
 * Formulário de cadastro de curso.
 */
public class FrmCadastroCurso extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8951948947942201177L;
	//--------------Atributos e Métodos para ajustes de opções e permissões-----------------------//
	//Instanciação do formuláro de divulgação do curso
	FrmCursoDivulgacao formDivulgacao;
	FrmCadastroComponente formComponente;
	
	//-----Ajuste de visualização conforme opção
	private int op;
	
	public int getOp(){
		return this.op;
	}
	//---Ajuste da barra de título
	//-----Titulo da janela
	private String titulo ="";  //  @jve:decl-index=0:
	private String getTitulo(){
		return this.titulo;
	}
	
	//----Ajusta quantidade de ciclos do curso para cadastro de ciclos
	private int qtCiclos = 0;

	public void setQtCiclos(int x){
		this.qtCiclos = x;
	}	
	public int getQtCiclos(){
		return this.qtCiclos;
	}
	//----Ajustar curso para exibição
	public void ajustaCurso(String nmCurso){
		Curso consultar = new Curso();
		consultar.consultar(nmCurso);
		getTxtNmCurso().setText(consultar.getNmCurso());
		getCmbTipo().setSelectedItem(consultar.getNmTipo());
		getCmbArea().setSelectedItem(consultar.getNmArea());
		getCmbQtCiclos().setSelectedIndex(consultar.getQtCiclos());
		getCmbDuracaoCiclo().setSelectedItem(consultar.getQtDuracao());
		getTxtCoordenador().setText(consultar.getNmCoordenador());
		getTxtDsCurso().setText(consultar.getDsCurso());	
		setQtCiclos(getCmbQtCiclos().getSelectedIndex());
		jtbAbaPrincipal.addTab("Ciclos", null, getAbaCiclos(), null);
		jtbAbaPrincipal.addTab("Componentes", null, getAbaComponentes(), null);
		consultarCiclos(nmCurso);
	}
	//----Criar combobox para ordenação do ciclo no curso
	/**
	 * This method initializes cmbOrdemCiclo	
	 * 	
	 * Gerar combobox dinâmica de acordo com quantidade de ciclos do curso
	 * para a aba ciclos
	 */
	private void gerarCmbOrdemCiclo() {
		int qt = getQtCiclos();
		qtCiclosOrdem = new DefaultComboBoxModel();
		qtCiclosOrdem.addElement("----------");
		for(int i=1;i<=qt;i++){
			qtCiclosOrdem.addElement(""+i+"");
		}
		cmbOrdemCiclo = new JComboBox(qtCiclosOrdem);
		cmbOrdemCiclo.setBounds(new Rectangle(12, 71, 100, 22));
		cmbOrdemCiclo.setToolTipText("Ordenação do ciclo no curso");
		abaCiclos.add(cmbOrdemCiclo, null);
	}
	/**
	 * This method initializes cmbCicloOrdem	
	 * 	
	 * Gerar combobox dinâmica de acordo com quantidade de ciclos do curso
	 * para a aba componentes	
	 */
	private void getCmbCicloOrdem() {
		int qt = getQtCiclos();
		qtCiclosOrdemComponente = new DefaultComboBoxModel();
		qtCiclosOrdemComponente.addElement("----------");
		for(int i=1;i<=qt;i++){
			qtCiclosOrdemComponente.addElement(""+i+"");
		}
		cmbCicloOrdem = new JComboBox(qtCiclosOrdemComponente);
		cmbCicloOrdem.setBounds(new Rectangle(12, 71, 100, 22));
		cmbCicloOrdem.setToolTipText("Componente curricular pertence ao ciclo");
		abaComponentes.add(cmbCicloOrdem, null);
	}
	//--------------Atributos e métodos para construir tabela de ciclos no curso
	Curso consultar = new Curso();
	//--Atributo para armazenar item selecionado na tabela
	private String cicloSelecionado = "";	
	//--Ajustr seleção
	private void setCicloSelecionado(String selecao){
		this.cicloSelecionado = selecao; 
	}
	//--Retornar seleção
	public String getCicloSelecionado(){
		return this.cicloSelecionado;
	}
	//----Atributo para armazenar os itens da tabela ciclos(linhasXcolunas)
	DefaultTableModel tabelaCiclos;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaCiclos(){
		return this.tabelaCiclos;
	}	
	public void construirTabelaCiclos(){
		jtbCiclos = new JTable(getTabelaCiclos());
		jspTbCiclos = new JScrollPane( jtbCiclos );
		jspTbCiclos.setBounds(12, 131, 185, 242);
		abaCiclos.add(jspTbCiclos, null);
		jtbCiclos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abaCiclos.remove(jspTbCicloComponente);
				setCicloSelecionado("");
				getTxtDsCiclo().setText("");
				setCicloSelecionado((String)(jtbCiclos.getValueAt(jtbCiclos.getSelectedRow(), 0)));
				setCdCicloSelecionado((String)(jtbCiclos.getValueAt(jtbCiclos.getSelectedRow(), 1)));
				consultarCicloComponente(getTxtNmCurso().getText(), getCicloSelecionado());
				consultar.consultarDsCiclo(getTxtNmCurso().getText(), getCicloSelecionado());				
				getTxtDsCiclo().setText(consultar.getDsCiclo());
				if(getCicloSelecionado().equals("")){
					
				}else{
					if(getPermissao()==1 || getPermissao()==2){
						btnRemoverCiclo.setEnabled(true);
						btnEditarCiclo.setEnabled(true);	
					}						
				}				
			}
		});				
	}
	//----Consultar camada de negócios - Classe Curso
	public void consultarCiclos(String nmCurso){
		consultar = new Curso();
		consultar.consultarCiclos(nmCurso);
		this.tabelaCiclos = consultar.getTabelaCiclos();
		if(tabelaCiclos.getRowCount()<=0){
			Object linha[] = new Object[2];
			linha[0] = "";
			linha[1] = "";
			tabelaCiclos.addRow(linha);
			construirTabelaCiclos();			
		}else{
			construirTabelaCiclos();
		}
	}
	//--Tabela ciclo componentes
	//----Atributo para armazenar os itens da tabela ciclos(linhasXcolunas)
	DefaultTableModel tabelaCicloComponente;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaCicloComponente(){
		return this.tabelaCicloComponente;
	}	
	public void construirTabelaCicloComponentes(){
		jtbCicloComponente = new JTable(getTabelaCicloComponente());
		jspTbCicloComponente = new JScrollPane( jtbCicloComponente );
		jspTbCicloComponente.setBounds(212, 131, 185, 242);
		abaCiclos.add(jspTbCicloComponente, null);
	}
	//--Tabela curso componentes
	//--Atributo para armazenar item selecionado na tabela
	private String cursoComponenteSelecionado = "";	
	//--Ajustr seleção
	private void setCursoComponenteSelecionado(String selecao){
		this.cursoComponenteSelecionado = selecao; 
	}
	//--Retornar seleção
	public String getCursoComponenteSelecionado(){
		return this.cursoComponenteSelecionado;
	}
	//----Atributo para armazenar os itens da tabela componentes(linhasXcolunas)
	DefaultTableModel tabelaCursoComponente;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaCursoComponente(){
		return this.tabelaCursoComponente;
	}	
	public void construirTabelaCursoComponentes(){
		jtbCursoComponente = new JTable(getTabelaCursoComponente());
		jspTbCursoComponente = new JScrollPane( jtbCursoComponente );
		jspTbCursoComponente.setBounds(12, 131, 365, 242);
		jtbCursoComponente.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setCursoComponenteSelecionado("");
				String cicloSelecionado = (String) (jtbCursoComponente.getValueAt(jtbCursoComponente.getSelectedRow(), 0));
				setCursoComponenteSelecionado((String)(jtbCursoComponente.getValueAt(jtbCursoComponente.getSelectedRow(), 1)));
				getTxtDsComponente().setText("");
				consultar.consultarDsComponenteCurso(getTxtNmCurso().getText(), getCursoComponenteSelecionado(), cicloSelecionado);
				getTxtDsComponente().setText(consultar.getDsComponente());
				setCdComponenteSelecionado((String) (jtbCursoComponente.getValueAt(jtbCursoComponente.getSelectedRow(), 0)));
				if(getCursoComponenteSelecionado().equals("")){
					
				}else{
					if(getPermissao()==1 || getPermissao()==2){
						btnRemoverComponente.setEnabled(true);
						btnEditarComponente.setEnabled(true);	
					}
				}
			}
		});
		abaComponentes.add(jspTbCursoComponente, null);
	}
	public void consultarCursoComponente(String nmCurso){
		consultar = new Curso();
		consultar.consultarCursoComponentes(nmCurso);
		this.tabelaCursoComponente = consultar.getTabelaCursoComponentes();
		if(tabelaCursoComponente.getRowCount()<=0){
			Object linhaD[] = new Object[2];
			linhaD[0] = "";
			linhaD[1] = "";
			tabelaCursoComponente.addRow(linhaD);
			construirTabelaCursoComponentes();			
		}else{
			construirTabelaCursoComponentes();			
		}
	}
	//----Consultar camada de negócios - Classe Curso
	public void consultarCicloComponente(String nmCurso, String cdCiclo){
		consultar = new Curso();
		consultar.consultarCicloComponentes(nmCurso, cdCiclo);
		this.tabelaCicloComponente = consultar.getTabelaCicloComponentes();
		if(tabelaCicloComponente.getRowCount()<=0){
			Object linhaC[] = new Object[1];
			linhaC[0] = "";
			tabelaCicloComponente.addRow(linhaC);
			construirTabelaCicloComponentes();			
		}else{
			construirTabelaCicloComponentes();			
		}
	}
	//---------Atributos para remover dados
	//--Ciclo
	private String nmCicloSelecionado;
	
	public void setCdCicloSelecionado(String selecao){
		this.nmCicloSelecionado = selecao;
	}
	public String getNmCicloSelecionado(){
		return this.nmCicloSelecionado;
	}
	//--Componente curricular
	private String cdComponenteSelecionado;
	
	public void setCdComponenteSelecionado(String selecao){
		this.cdComponenteSelecionado = selecao;
	}
	public String getCdComponenteSelecionado(){
		return this.cdComponenteSelecionado;
	}	
	
	//-----Ajustar dados de acordo com opção e permissão
	//---Ajusta opção
	public void ajustaOpcao(int op, String nmCurso){
		/*----Opções: 
		 * 0 - Para Novo cadastro
		 * 1 - Para exibir curso selecionado
		 */
		if(op==0){//Cadastro novo
			btnEditarCurso.setEnabled(false);
			btnCancelarCurso.setEnabled(false);
		}else if(op==1){//Exibir curso selecionado
			ajustaCurso(nmCurso);
			desabilitarCurso();
			desabilitarCiclo();
			desabilitarComponente();
			if(getPermissao()==1){//Administrdor		
				btnEditarCurso.setEnabled(true);
			}else if(getPermissao()==2){//Coordenador de curso
				btnEditarCurso.setEnabled(true);
			}else if(getPermissao()==3){//Secretaria
				btnContinuar.setEnabled(false);
				btnEditarCurso.setEnabled(false);
				btnEditarCiclo.setEnabled(false);
				btnEditarComponente.setEnabled(false);
				btnNovoComponente.setEnabled(false);
				btnNovoComponenteIe.setEnabled(false);
				btnNovoCiclo.setEnabled(false);				
				btnAddCiclo.setEnabled(false);
				btnAddComponente.setEnabled(false);
				btnRemoverCiclo.setEnabled(false);
				btnRemoverComponente.setEnabled(false);
			}else if(getPermissao()==4){//Convidado
				btnContinuar.setEnabled(false);
				btnEditarCurso.setEnabled(false);
				btnEditarCiclo.setEnabled(false);
				btnEditarComponente.setEnabled(false);
				btnNovoComponente.setEnabled(false);
				btnNovoComponenteIe.setEnabled(false);
				btnNovoCiclo.setEnabled(false);				
				btnAddCiclo.setEnabled(false);
				btnAddComponente.setEnabled(false);
				btnRemoverCiclo.setEnabled(false);
				btnRemoverComponente.setEnabled(false);
			}
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
	//--------Ajustar campos-----------//
	//---Curso
	public void habilitarCurso(){
		if(getOp()==1){
			desabilitarCiclo();
			desabilitarComponente();	
		}
		btnContinuar.setEnabled(true);
		getCmbTipo().setEnabled(true);
		getCmbArea().setEnabled(true);
		getCmbQtCiclos().setEnabled(true);
		getCmbDuracaoCiclo().setEnabled(true);
		getTxtCoordenador().setEditable(true);
		getTxtDsCurso().setEditable(true);
	}
	public void desabilitarCurso(){
		btnContinuar.setEnabled(false);
		getTxtNmCurso().setEditable(false);
		getCmbTipo().setEnabled(false);
		getCmbArea().setEnabled(false);
		getCmbQtCiclos().setEnabled(false);
		getCmbDuracaoCiclo().setEnabled(false);
		getTxtCoordenador().setEditable(false);
		getTxtDsCurso().setEditable(false);
		btnCancelarCurso.setEnabled(false);
		btnEditarCurso.setEnabled(true);
	}
	//---Ciclo
	public void habilitarCiclo(){
		setQtCiclos(getCmbQtCiclos().getSelectedIndex());
		getTxtNmCiclo().setEditable(true);
		cmbOrdemCiclo.setEnabled(true);
		getTxtDsCiclo().setEditable(true);	
		desabilitarCurso();
		setCicloSelecionado("");
		if(getOp()==1){
			desabilitarComponente();	
		}		
	}
	public void desabilitarCiclo(){
		
		btnRemoverCiclo.setEnabled(false);
		btnEditarCiclo.setEnabled(false);
		getTxtNmCiclo().setEditable(false);
		cmbOrdemCiclo.setEnabled(false);
		getTxtDsCiclo().setEditable(false);
		getTxtNmCiclo().setText("");
		cmbOrdemCiclo.setSelectedIndex(0);
		getTxtDsCiclo().setText("");
		btnCancelarCiclo.setEnabled(false);
		btnAddCiclo.setEnabled(false);
	}
	//---Componente
	public void habilitarComponente(){
		setQtCiclos(getCmbQtCiclos().getSelectedIndex());
		getCmbCicloOrdem();
		getCmbComponente();
		cmbComponente.setEnabled(true);
		cmbCicloOrdem.setEnabled(true);
		getTxtDsComponente().setEditable(true);
		cmbCicloOrdem.setSelectedIndex(0);
		desabilitarCurso();
		desabilitarCiclo();
	}
	public void desabilitarComponente(){
		if(getOp()==1){
			cmbCicloOrdem.setSelectedIndex(0);
		}
		btnEditarComponenteIe.setEnabled(false);
		btnRemoverComponente.setEnabled(false);
		btnEditarComponente.setEnabled(false);
		getTxtDsComponente().setEditable(false);
		btnCancelarComponente.setEnabled(false);
		btnAddComponente.setEnabled(false);
		cmbComponente.setEnabled(false);		
		cmbCicloOrdem.setEnabled(false);
	}	
	
	//--------------Interface ao usuário----------------------------------------------------------//


	//Atributo que armazena os itens do JComboBox
	private DefaultComboBoxModel cursoTipo;
	private DefaultComboBoxModel cursoArea;
	private DefaultComboBoxModel cursoCiclos;
	private DefaultComboBoxModel cursoDuracaoCiclo;
	private DefaultComboBoxModel qtCiclosOrdem;	
	private DefaultComboBoxModel qtCiclosOrdemComponente;

	//----Tabelas----//
	//-Tabela ciclos
	private JScrollPane jspTbCiclos = null;
	private JTable jtbCiclos = null;
	//--Tabela cicloComponentes
	private JScrollPane jspTbCicloComponente = null;
	private JTable jtbCicloComponente = null;
	//--Tabela cursoComponentes
	private JScrollPane jspTbCursoComponente = null;
	private JTable jtbCursoComponente = null;

	//---Painel principal
	private JPanel jContentPane = null;
	private JTabbedPane jtbAbaPrincipal = null;
	private JPanel abaCurso = null;
	private JPanel abaCiclos = null;
	private JPanel abaComponentes = null;
	private JButton btnCancelar = null;
	private JLabel lblDivisao = null;
	private JButton btnRelatorio = null;
	private JLabel lblCurso = null;
	private JTextField txtNmCurso = null;
	private JLabel lblTipo = null;
	private JComboBox cmbArea = null;
	private JLabel lblArea = null;
	private JComboBox cmbTipo = null;
	private JLabel lblQtCiclos = null;
	private JComboBox cmbQtCiclos = null;
	private JLabel lblDuracao = null;
	private JComboBox cmbDuracaoCiclo = null;
	private JLabel lblCoordenador = null;
	private JTextField txtCoordenador = null;
	private JButton btnContinuar = null;
	private JButton btnEditarCurso = null;
	private JLabel lblCiclo = null;
	private JTextField txtNmCiclo = null;
	private JLabel lblOrdenacao = null;
	private JComboBox cmbOrdemCiclo = null;
	private JButton btnAddCiclo = null;
	private JButton btnEditarCiclo = null;
	private JLabel lblDivisao2 = null;
	private JButton btnNovoCiclo = null;
	private JLabel lblCiclos = null;
	private JLabel lblDsCiclo = null;
	private JTextArea txtDsCiclo = null;
	private JLabel lblComponentes = null;
	private JLabel lblDsCurso = null;
	private JTextArea txtDsCurso = null;
	private JButton btnEditarSite = null;
	private JLabel lblComponente = null;
	private JComboBox cmbComponente = null;
	private JLabel lblPertence = null;
	private JLabel lblComponentesC = null;
	private JComboBox cmbCicloOrdem = null;
	private JTextArea txtDsComponente = null;
	private JLabel lblDivisao3 = null;
	private JButton btnNovoComponente = null;
	private JButton btnAddComponente = null;
	private JButton btnEditarComponente = null;
	private JLabel lblDsComponente = null;
	private JButton btnRemoverCiclo = null;
	private JButton btnRemoverComponente = null;
	private JButton btnCancelarCiclo = null;
	private JButton btnCancelarComponente = null;
	private JButton btnCancelarCurso = null;
	
	//Criação da área de trabalho do formulário usuários
	private JDesktopPane desk = null;
	private JButton btnNovoComponenteIe = null;
	private JButton btnEditarComponenteIe = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroCurso(JDesktopPane desktop, int permissao, int op, String nmCurso) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		initialize(op, nmCurso);
	}	
	/**
	 * This method initializes this
	 * 
	 * Recebe Op 0 para cadastro novo 1 para exibir dados.
	 * 
	 * Ajusta a barra de título de acordo com opção.
	 * 
	 * @return void
	 */
	private void initialize(int op, String nmCurso) {
		this.setSize(744, 485);
		this.setContentPane(getJContentPane());
		if(op==0){//Ajusta barra de título 0 para novo cadastro
			this.titulo="][ Novo curso ][";
		}else if(op==1){//1 para exibir dados cadastrados
			this.titulo="][ Cadastro de curso ][ Curso: "+nmCurso+"";
		}
		ajustaOpcao(op, nmCurso);
		this.setTitle(""+getTitulo()+"");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-44, 415, 830, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao.setForeground(new Color(190, 190, 190));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJtbAbaPrincipal(), null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getBtnRelatorio(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jtbAbaPrincipal	
	 * 	
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJtbAbaPrincipal() {
		if (jtbAbaPrincipal == null) {
			jtbAbaPrincipal = new JTabbedPane();
			jtbAbaPrincipal.setBounds(new Rectangle(2, 1, 737, 422));
			jtbAbaPrincipal.addTab("Curso", null, getAbaCurso(), null);
		}
		return jtbAbaPrincipal;
	}
	/**
	 * This method initializes abaCurso	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaCurso() {
		if (abaCurso == null) {
			lblDsCurso = new JLabel();
			lblDsCurso.setBounds(new Rectangle(12, 131, 112, 20));
			lblDsCurso.setText("Descrição do curso");
			lblCoordenador = new JLabel();
			lblCoordenador.setBounds(new Rectangle(22, 101, 129, 20));
			lblCoordenador.setText("Coordenador do curso");
			lblDuracao = new JLabel();
			lblDuracao.setBounds(new Rectangle(402, 51, 127, 20));
			lblDuracao.setText("Duração de cada ciclo");
			lblQtCiclos = new JLabel();
			lblQtCiclos.setBounds(new Rectangle(272, 51, 120, 20));
			lblQtCiclos.setText("Quantidade de ciclos");
			lblArea = new JLabel();
			lblArea.setBounds(new Rectangle(142, 51, 94, 20));
			lblArea.setText("Área de atuação");
			lblTipo = new JLabel();
			lblTipo.setBounds(new Rectangle(12, 51, 79, 20));
			lblTipo.setText("Tipo do curso");
			lblCurso = new JLabel();
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
			abaCurso.add(getBtnContinuar(), null);
			abaCurso.add(getBtnEditarCurso(), null);
			abaCurso.add(lblDsCurso, null);
			abaCurso.add(getTxtDsCurso(), null);
			abaCurso.add(getBtnEditarSite(), null);
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
			lblComponentes = new JLabel();
			lblComponentes.setBounds(new Rectangle(210, 111, 155, 20));
			lblComponentes.setText("Componentes cadastrados");
			lblDsCiclo = new JLabel();
			lblDsCiclo.setBounds(new Rectangle(412, 111, 106, 20));
			lblDsCiclo.setText("Descrição do ciclo");
			lblCiclos = new JLabel();
			lblCiclos.setBounds(new Rectangle(12, 111, 111, 20));
			lblCiclos.setText("Ciclos cadastrados");
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-44, 91, 830, 16));
			lblDivisao2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao2.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblOrdenacao = new JLabel();
			lblOrdenacao.setBounds(new Rectangle(12, 51, 164, 20));
			lblOrdenacao.setText("Ordenação do ciclo no curso");
			lblCiclo = new JLabel();
			lblCiclo.setBounds(new Rectangle(12, 21, 78, 20));
			lblCiclo.setText("Título do ciclo");
			abaCiclos = new JPanel();
			abaCiclos.setLayout(null);			
			abaCiclos.add(lblCiclo, null);
			abaCiclos.add(getTxtNmCiclo(), null);
			abaCiclos.add(lblOrdenacao, null);
			abaCiclos.add(getBtnAddCiclo(), null);
			abaCiclos.add(getBtnEditarCiclo(), null);
			abaCiclos.add(lblDivisao2, null);
			abaCiclos.add(getBtnNovoCiclo(), null);
			abaCiclos.add(lblCiclos, null);
			abaCiclos.add(lblDsCiclo, null);
			abaCiclos.add(getTxtDsCiclo(), null);
			abaCiclos.add(lblComponentes, null);
			abaCiclos.add(getBtnRemoverCiclo(), null);
			abaCiclos.add(getBtnCancelarCiclo(), null);
			gerarCmbOrdemCiclo();
			consultarCicloComponente(getTxtNmCurso().getText(), getCicloSelecionado());
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
			lblDsComponente = new JLabel();
			lblDsComponente.setBounds(new Rectangle(412, 111, 210, 20));
			lblDsComponente.setText("Descrição do componente curricular");
			lblDivisao3 = new JLabel();
			lblDivisao3.setBounds(new Rectangle(-44, 91, 830, 16));
			lblDivisao3.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao3.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao3.setForeground(new Color(190, 190, 190));
			lblComponentesC = new JLabel();
			lblComponentesC.setBounds(new Rectangle(12, 111, 300, 20));
			lblComponentesC.setText("Componentes curriculares cadastrados neste curso");
			lblPertence = new JLabel();
			lblPertence.setBounds(new Rectangle(12, 51, 121, 20));
			lblPertence.setText("Pertence a qual ciclo");
			lblComponente = new JLabel();
			lblComponente.setBounds(new Rectangle(12, 21, 184, 20));
			lblComponente.setText("Nome do componente curricular");
			abaComponentes = new JPanel();
			abaComponentes.setLayout(null);
			abaComponentes.add(lblComponente, null);
			abaComponentes.add(lblPertence, null);
			abaComponentes.add(lblComponentesC, null);
			abaComponentes.add(getTxtDsComponente(), null);
			abaComponentes.add(lblDivisao3, null);
			abaComponentes.add(getBtnNovoComponente(), null);
			abaComponentes.add(getBtnAddComponente(), null);
			abaComponentes.add(getBtnEditarComponente(), null);
			abaComponentes.add(lblDsComponente, null);
			abaComponentes.add(getBtnRemoverComponente(), null);
			abaComponentes.add(getBtnCancelarComponente(), null);
			abaComponentes.add(getBtnNovoComponenteIe(), null);
			abaComponentes.add(getBtnEditarComponenteIe(), null);
			getCmbComponente();
			getCmbCicloOrdem();
			consultarCursoComponente(getTxtNmCurso().getText());
		}
		return abaComponentes;
	}
	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(630, 435, 91, 21));
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
	 * This method initializes btnRelatorio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRelatorio() {
		if (btnRelatorio == null) {
			btnRelatorio = new JButton();
			btnRelatorio.setBounds(new Rectangle(530, 435, 91, 22));
			btnRelatorio.setText("Relatório");
			btnRelatorio.setToolTipText("Relatório detalhado do curso");
			btnRelatorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
		return btnRelatorio;
	}
	/**
	 * This method initializes txtNmCurso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmCurso() {
		if (txtNmCurso == null) {
			txtNmCurso = new JTextField();
			txtNmCurso.setBounds(new Rectangle(130, 21, 594, 22));
			txtNmCurso.setBackground(Color.white);
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
			txtCoordenador.setBackground(Color.white);
			txtCoordenador.setToolTipText("Nome do coordenador do curso");
		}
		return txtCoordenador;
	}
	/**
	 * This method initializes btnContinuar	
	 * 	
	 * @return javax.swing.JButton
	 * 
	 * Possui ações para cadastro de curso
	 */
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton();
			btnContinuar.setBounds(new Rectangle(630, 370, 91, 22));
			btnContinuar.setText("Confirmar");
			btnContinuar.setToolTipText("Confirmar dados do curso e continuar cadastro");
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getOp()==0){//Se op 0 - Cadastro novo
						Curso cadastrar = new Curso();
						//Ajusta dados na classe Curso para validar
						cadastrar.ajustaDadosCurso(getTxtNmCurso().getText(), (String) cmbTipo.getSelectedItem(), (String) cmbArea.getSelectedItem(), cmbQtCiclos.getSelectedIndex(), (String) cmbDuracaoCiclo.getSelectedItem(), getTxtCoordenador().getText(), getTxtDsCurso().getText());
						if(cadastrar.validarCurso()>=1){//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
							JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarCurso()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
						}else{
							//Não encotro pendências nos dados, verifica se curso ja existe
							if(cadastrar.getExisteCurso()>=1){	
								if (JOptionPane.showConfirmDialog(new JFrame(),
										"Este curso ja existe deseja salvar alterações?", "Confirmação",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
									cadastrar.alterarCurso(getTxtNmCurso().getText());
									desabilitarCurso();
									btnCancelarCurso.setEnabled(false);
									btnEditarCurso.setEnabled(true);
								}else{
									getTxtNmCurso().setText("");//limpa o campos
									getTxtNmCurso().grabFocus();//Retorna o focus
									btnCancelarCurso.setEnabled(false);
									btnEditarCurso.setEnabled(false);
								}								
							}//Curso não existe então cadastrar e limpar campos
							else{
								cadastrar.cadastrarCurso();
								setQtCiclos(cmbQtCiclos.getSelectedIndex());
								jtbAbaPrincipal.addTab("Ciclos", null, getAbaCiclos(), null);
								consultarCiclos(getTxtNmCurso().getText());
								desabilitarCiclo();
								desabilitarCurso();
								btnCancelarCurso.setEnabled(false);
								btnEditarCurso.setEnabled(true);
								btnEditarCiclo.setEnabled(false);
								btnRemoverCiclo.setEnabled(false);
							}
						}
					}else{//Editar cadastro
						Curso cadastrar = new Curso();
						//Ajusta dados na classe Curso para validar
						cadastrar.ajustaDadosCurso(getTxtNmCurso().getText(), (String) cmbTipo.getSelectedItem(), (String) cmbArea.getSelectedItem(), cmbQtCiclos.getSelectedIndex(), (String) cmbDuracaoCiclo.getSelectedItem(), getTxtCoordenador().getText(), getTxtDsCurso().getText());
						//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
						if(cadastrar.validarCurso()>=1){
							JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarCurso()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
						}else{
							cadastrar.alterarCurso(getTxtNmCurso().getText());
						}desabilitarCurso();
						btnCancelarCurso.setEnabled(false);
						btnEditarCurso.setEnabled(true);
					}	
				}
			});
		}
		return btnContinuar;
	}
	/**
	 * This method initializes btnEditarCurso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarCurso() {
		if (btnEditarCurso == null) {
			btnEditarCurso = new JButton();
			btnEditarCurso.setBounds(new Rectangle(530, 370, 91, 22));
			btnEditarCurso.setText("Editar");
			btnEditarCurso.setToolTipText("Editar dados do curso");
			btnEditarCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarCurso();
					btnCancelarCurso.setEnabled(true);
					btnEditarCurso.setEnabled(false);
					desabilitarCiclo();
				}
			});
		}
		return btnEditarCurso;
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
			txtNmCiclo.setBackground(Color.white);
			txtNmCiclo.setToolTipText("Título do ciclo no curso");
		}
		return txtNmCiclo;
	}
	/**
	 * This method initializes btnAddCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddCiclo() {
		if (btnAddCiclo == null) {
			btnAddCiclo = new JButton();
			btnAddCiclo.setBounds(new Rectangle(630, 370, 91, 22));
			btnAddCiclo.setText("Confirmar");
			btnAddCiclo.setToolTipText("Adicionar ciclo ao curso");
			btnAddCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getOp()==0){//Se op 0 - Cadastro novo
						Curso cadastrar = new Curso();
						//Ajusta dados na classe Curso para validar
						cadastrar.ajustaDadosCiclo(getTxtNmCurso().getText(), cmbOrdemCiclo.getSelectedIndex(), getTxtNmCiclo().getText(), getTxtDsCiclo().getText());
						if(cadastrar.validarCiclo()>=1){//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
							JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarCiclo()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
						}else{
							//Não encontro pendências nos dados, verifica se ciclo ja existe
							if(cadastrar.getExisteCiclo()>=1){	
								if (JOptionPane.showConfirmDialog(new JFrame(),
										"Este ciclo ja existe deseja salvar alterações?", "Confirmação",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
									//remover tabela de ciclos atual
									abaCiclos.remove(jspTbCiclos);
									//Construir nova tabela
									consultarCiclos(getTxtNmCurso().getText());
									cadastrar.alterarCiclo();
									getTxtNmCiclo().setText("");//limpa o campo
									cmbOrdemCiclo.setSelectedIndex(0);
									getTxtNmCiclo().grabFocus();//Retorna o focus
								}
							}//Ciclo não existe então cadastrar e limpar campos
							else{
								cadastrar.cadastrarCiclo();
								//remover tabela de ciclos atual
								abaCiclos.remove(jspTbCiclos);
								//Construir nova tabela
								consultarCiclos(getTxtNmCurso().getText());
								jtbAbaPrincipal.addTab("Componentes", null, getAbaComponentes(), null);
								desabilitarCiclo();
								desabilitarComponente();
								btnCancelarCiclo.setEnabled(false);
							}
						}
					}else{//Editar cadastro
						Curso cadastrar = new Curso();
						//Ajusta dados na classe Curso para validar
						cadastrar.ajustaDadosCiclo(getTxtNmCurso().getText(), cmbOrdemCiclo.getSelectedIndex(), getTxtNmCiclo().getText(), getTxtDsCiclo().getText());
						//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
						if(cadastrar.validarCiclo()>=1){
							JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarCiclo()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
						}else{
							if(cadastrar.getExisteCiclo()>=1){//Se ciclo existir confirmar alterações
								if (JOptionPane.showConfirmDialog(new JFrame(),
										"Este ciclo ja existe deseja salvar alterações?", "Confirmação",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
									cadastrar.alterarCiclo();
									//remover tabela de ciclos atual
									abaCiclos.remove(jspTbCiclos);
									//Construir nova tabela
									consultarCiclos(getTxtNmCurso().getText());
									getTxtNmCiclo().setText("");//limpa o campo
									cmbOrdemCiclo.setSelectedIndex(0);
									getTxtNmCiclo().grabFocus();//Retorna o focus
								}
							}//Ciclo não existe então cadastrar e limpar campos
							else{
								cadastrar.cadastrarCiclo();
								//remover tabela de ciclos atual
								abaCiclos.remove(jspTbCiclos);
								//Construir nova tabela
								consultarCiclos(getTxtNmCurso().getText());
							}desabilitarCiclo();
							btnCancelarCiclo.setEnabled(false);
						}
					}btnNovoComponente.setEnabled(true);
					btnNovoCiclo.setEnabled(true);
				}
			});
		}
		return btnAddCiclo;
	}
	/**
	 * This method initializes btnEditarCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarCiclo() {
		if (btnEditarCiclo == null) {
			btnEditarCiclo = new JButton();
			btnEditarCiclo.setBounds(new Rectangle(530, 370, 91, 22));
			btnEditarCiclo.setText("Editar");
			btnEditarCiclo.setToolTipText("Editar cadastro do ciclo selecionado");
			btnEditarCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getCicloSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um ciclo para ser editado seus dados","Seleção",1);
					}else{
						getTxtNmCiclo().setText(getNmCicloSelecionado());
						cmbOrdemCiclo.setSelectedItem(getCicloSelecionado());
						habilitarCiclo();
						btnCancelarCiclo.setEnabled(true);
						btnAddCiclo.setEnabled(true);
						btnNovoCiclo.setEnabled(false);
						cmbOrdemCiclo.setEnabled(false);
						btnNovoComponente.setEnabled(true);
					}
				}
			});
		}
		return btnEditarCiclo;
	}
	/**
	 * This method initializes btnNovoCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovoCiclo() {
		if (btnNovoCiclo == null) {
			btnNovoCiclo = new JButton();
			btnNovoCiclo.setBounds(new Rectangle(630, 71, 91, 22));
			btnNovoCiclo.setText("Novo");
			btnNovoCiclo.setToolTipText("Cadastrar novo ciclo ao curso");
			btnNovoCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abaCiclos.remove(cmbOrdemCiclo);
					habilitarCiclo();
					gerarCmbOrdemCiclo();
					cmbOrdemCiclo.setSelectedIndex(0);
					getTxtNmCiclo().setText("");
					getTxtDsCiclo().setText("");
					getTxtNmCiclo().grabFocus();
					btnCancelarCiclo.setEnabled(true);
					btnAddCiclo.setEnabled(true);
					btnRemoverCiclo.setEnabled(false);
					btnEditarCiclo.setEnabled(false);
					if(getOp()==1){
						btnNovoComponente.setEnabled(true);	
					}					
				}
			});
		}
		return btnNovoCiclo;
	}
	/**
	 * This method initializes txtDsCiclo	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsCiclo() {
		if (txtDsCiclo == null) {
			txtDsCiclo = new JTextArea();
			txtDsCiclo.setBounds(new Rectangle(412, 131, 309, 127));
			txtDsCiclo.setBackground(Color.white);
			txtDsCiclo.setToolTipText("Descrição do ciclo no curso");
		}
		return txtDsCiclo;
	}
	/**
	 * This method initializes txtDsCurso	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsCurso() {
		if (txtDsCurso == null) {
			txtDsCurso = new JTextArea();
			txtDsCurso.setBounds(new Rectangle(12, 151, 435, 135));
			txtDsCurso.setBackground(Color.white);
			txtDsCurso.setToolTipText("Descrição do curso");
		}
		return txtDsCurso;
	}
	/**
	 * This method initializes btnEditarSite	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarSite() {
		if (btnEditarSite == null) {
			btnEditarSite = new JButton();
			btnEditarSite.setBounds(new Rectangle(12, 357, 111, 31));
			btnEditarSite.setText("Divulgação");
			btnEditarSite.setToolTipText("Editar dados que serão disponibilizados no site (SENA IV)");
			btnEditarSite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((formDivulgacao == null) || (formDivulgacao.isClosed())) {
						formDivulgacao = new FrmCursoDivulgacao(desk, getPermissao(), getTxtNmCurso().getText());
						desk.add(formDivulgacao, new Integer(2));
						formDivulgacao.show();
					}
				}});
			}
		return btnEditarSite;
	}
	/**
	 * This method initializes cmbComponente	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private void getCmbComponente() {
			Vector cpt = new Vector();
			cpt.addElement("----------");
			Curso consultar = new Curso();
			cpt.addAll(consultar.consultarComponentes());
			cmbComponente = new JComboBox(cpt);
			cmbComponente.setBounds(new Rectangle(164, 21, 420, 22));
			cmbComponente.setToolTipText("Componentes curriculares cadastrados");
			cmbComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getTxtDsComponente().setText("");
					Curso consultar = new Curso();
					consultar.consultarDsComponenteCurso((String) cmbComponente.getSelectedItem());
					getTxtDsComponente().setText(consultar.getDsComponente());
					if((boolean) cmbComponente.getSelectedItem().equals("----------")){
						btnEditarComponenteIe.setEnabled(false);
					}else{
						btnEditarComponenteIe.setEnabled(true);
					}
				}
			});
			abaComponentes.add(cmbComponente, null);
	}
	/**
	 * This method initializes txtDsComponente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsComponente() {
		if (txtDsComponente == null) {
			txtDsComponente = new JTextArea();
			txtDsComponente.setBounds(new Rectangle(412, 131, 309, 127));
			txtDsComponente.setBackground(Color.white);
			txtDsComponente.setToolTipText("Descrição do componente curricular no curso");
		}
		return txtDsComponente;
	}
	/**
	 * This method initializes btnNovoComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovoComponente() {
		if (btnNovoComponente == null) {
			btnNovoComponente = new JButton();
			btnNovoComponente.setBounds(new Rectangle(430, 71, 91, 22));
			btnNovoComponente.setText("Adicionar");
			btnNovoComponente.setToolTipText("Adicionar novo componente curricular ao curso");
			btnNovoComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abaComponentes.remove(cmbCicloOrdem);
					abaComponentes.remove(cmbComponente);
					habilitarComponente();
					btnNovoComponente.setEnabled(false);
					btnEditarComponente.setEnabled(false);									
					getTxtDsComponente().setText("");
					btnCancelarComponente.setEnabled(true);
					btnRemoverComponente.setEnabled(false);
					btnAddComponente.setEnabled(true);
					btnNovoCiclo.setEnabled(true);
				}
			});
		}
		return btnNovoComponente;
	}
	/**
	 * This method initializes btnAddComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddComponente() {
		if (btnAddComponente == null) {
			btnAddComponente = new JButton();
			btnAddComponente.setBounds(new Rectangle(630, 370, 91, 22));
			btnAddComponente.setText("Confirmar");
			btnAddComponente.setToolTipText("Confirmar cadastro de componente curricular neste curso");
			btnAddComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Curso cadastrar = new Curso();
					cadastrar.ajustaDadosComponente((String) cmbComponente.getSelectedItem(), getTxtDsComponente().getText(), cmbCicloOrdem.getSelectedIndex(), getTxtNmCurso().getText());
					if(cadastrar.getExisteComponente()>=1){//Se ciclo existir confirmar alterações
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Salvar alterações?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								
								cadastrar.alterarComponente();
								getTxtDsComponente().setText("");
								//remover tabela de ciclos atual
								abaComponentes.remove(jspTbCursoComponente);
								//Construir nova tabela
								abaCiclos.remove(jspTbCicloComponente);
								consultarCursoComponente(getTxtNmCurso().getText());
								consultarCicloComponente(getTxtNmCurso().getText(), "");
								cmbComponente.setSelectedIndex(0);
								cmbCicloOrdem.setSelectedIndex(0);
							}
						}//Ciclo não existe então cadastrar e limpar campos
						else{
							if(cadastrar.validarComponente()>=1){
								JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarComponente()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
							}else{
								cadastrar.cadastrarComponente();
								//remover tabela de ciclos atual
								abaCiclos.remove(jspTbCicloComponente);
								abaComponentes.remove(jspTbCursoComponente);
								consultarCursoComponente(getTxtNmCurso().getText());
								consultarCicloComponente(getTxtNmCurso().getText(), "");
								cmbComponente.setSelectedIndex(0);
								cmbCicloOrdem.setSelectedIndex(0);	
							}							
						}
						desabilitarComponente();
						btnNovoComponente.setEnabled(true);
						btnNovoCiclo.setEnabled(true);
					}
			});
		}
		return btnAddComponente;
	}
	/**
	 * This method initializes btnEditarComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarComponente() {
		if (btnEditarComponente == null) {
			btnEditarComponente = new JButton();
			btnEditarComponente.setBounds(new Rectangle(530, 370, 91, 22));
			btnEditarComponente.setText("Editar");
			btnEditarComponente.setToolTipText("Editar componente curricular selecionado");
			btnEditarComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getCursoComponenteSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um componente curricular para ser editado seus dados","Seleção",1);
					}else{
						habilitarComponente();						
						cmbComponente.setSelectedItem(getCursoComponenteSelecionado());
						cmbCicloOrdem.setSelectedItem(getCdComponenteSelecionado());
						btnNovoComponente.setEnabled(false);
						btnEditarComponente.setEnabled(false);
						btnCancelarComponente.setEnabled(true);
						btnAddComponente.setEnabled(true);
						btnNovoCiclo.setEnabled(true);
						cmbComponente.setEnabled(false);
						cmbCicloOrdem.setEnabled(false);
						consultar.consultarDsComponenteCurso(getTxtNmCurso().getText(), getCursoComponenteSelecionado(), getCdComponenteSelecionado());
						getTxtDsComponente().setText(consultar.getDsComponente());
					}				
				}
			});
		}
		return btnEditarComponente;
	}
	/**
	 * This method initializes btnRemoverCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverCiclo() {
		if (btnRemoverCiclo == null) {
			btnRemoverCiclo = new JButton();
			btnRemoverCiclo.setBounds(new Rectangle(430, 370, 91, 22));
			btnRemoverCiclo.setText("Remover");
			btnRemoverCiclo.setToolTipText("Remover ciclo selecionado");
			btnRemoverCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getCicloSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um ciclo para ser exluído seus dados","Seleção",1);				
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir o seguinte ciclo?\n\n"+getNmCicloSelecionado()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							Curso remover = new Curso();
							remover.removerCiclo(getTxtNmCurso().getText(), getCicloSelecionado());
							//remove tabela atual
							abaCiclos.remove(jspTbCiclos);
							abaCiclos.remove(jspTbCicloComponente);
							abaComponentes.remove(jspTbCursoComponente);
							//Construir nova tabela
							consultarCiclos(getTxtNmCurso().getText());
							consultarCicloComponente(getTxtNmCurso().getText(), "");
							consultarCursoComponente(getTxtNmCurso().getText());
							getTxtNmCiclo().setText("");
							getTxtDsCiclo().setText("");
							cmbOrdemCiclo.setSelectedIndex(0);
							setCicloSelecionado("");
							btnRemoverCiclo.setEnabled(false);
							btnEditarCiclo.setEnabled(false);
						}
					}
				}
			});
		}
		return btnRemoverCiclo;
	}
	/**
	 * This method initializes btnRemoverComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverComponente() {
		if (btnRemoverComponente == null) {
			btnRemoverComponente = new JButton();
			btnRemoverComponente.setBounds(new Rectangle(430, 370, 91, 22));
			btnRemoverComponente.setText("Remover");
			btnRemoverComponente.setToolTipText("Remover componente curricular selecionado");
			btnRemoverComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getCursoComponenteSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um componente curricular para ser exluído seus dados","Seleção",1);				
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir do curso o seguinte componente curricular?\n\n"+getCursoComponenteSelecionado()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							Curso remover = new Curso();
							remover.removerComponente(getTxtNmCurso().getText(), getCursoComponenteSelecionado(), getCdComponenteSelecionado());
							//remove tabela atual
							abaCiclos.remove(jspTbCicloComponente);
							abaComponentes.remove(jspTbCursoComponente);
							//Construir nova tabela
							consultarCicloComponente(getTxtNmCurso().getText(), "");
							consultarCursoComponente(getTxtNmCurso().getText());
							cmbComponente.setSelectedIndex(0);
							cmbCicloOrdem.setSelectedIndex(0);
							getTxtDsComponente().setText("");
							desabilitarComponente();
						}
					}
				}
			});
		}
		return btnRemoverComponente;
	}
	/**
	 * This method initializes btnCancelarCiclo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarCiclo() {
		if (btnCancelarCiclo == null) {
			btnCancelarCiclo = new JButton();
			btnCancelarCiclo.setBounds(new Rectangle(530, 71, 91, 22));
			btnCancelarCiclo.setText("Cancelar");
			btnCancelarCiclo.setToolTipText("Cancelar cadastro de ciclo");
			btnCancelarCiclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desabilitarCiclo();
					btnNovoCiclo.setEnabled(true);
					btnCancelarCiclo.setEnabled(false);
				}
			});
		}
		return btnCancelarCiclo;
	}
	/**
	 * This method initializes btnCancelarComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarComponente() {
		if (btnCancelarComponente == null) {
			btnCancelarComponente = new JButton();
			btnCancelarComponente.setBounds(new Rectangle(330, 71, 91, 22));
			btnCancelarComponente.setText("Cancelar");
			btnCancelarComponente.setToolTipText("Cancelar cadastro de componente curricular");
			btnCancelarComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desabilitarComponente();
					btnNovoComponente.setEnabled(true);
					btnCancelarComponente.setEnabled(false);
				}
			});
		}
		return btnCancelarComponente;
	}
	/**
	 * This method initializes btnCancelarCurso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarCurso() {
		if (btnCancelarCurso == null) {
			btnCancelarCurso = new JButton();
			btnCancelarCurso.setBounds(new Rectangle(430, 370, 91, 22));
			btnCancelarCurso.setText("Cancelar");
			btnCancelarCurso.setToolTipText("Cancelar alteração do curso");
			btnCancelarCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desabilitarCurso();
				}
			});
		}
		return btnCancelarCurso;
	}
	/**
	 * This method initializes btnNovoComponenteIe	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovoComponenteIe() {
		if (btnNovoComponenteIe == null) {
			btnNovoComponenteIe = new JButton();
			btnNovoComponenteIe.setBounds(new Rectangle(630, 71, 91, 22));
			btnNovoComponenteIe.setText("Novo");
			btnNovoComponenteIe.setToolTipText("Cadastrar novo componente curricular na Instituição de ensino");
			btnNovoComponenteIe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desabilitarComponente();
					btnNovoComponente.setEnabled(true);
					btnCancelarComponente.setEnabled(false);
					if ((formComponente == null) || (formComponente.isClosed())) {
						formComponente = new FrmCadastroComponente(desk, getPermissao(), 0, "");
						desk.add(formComponente, new Integer(2));
						formComponente.show();
					}
				}});
		}
		return btnNovoComponenteIe;
	}
	/**
	 * This method initializes btnEditarComponenteIe	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarComponenteIe() {
		if (btnEditarComponenteIe == null) {
			btnEditarComponenteIe = new JButton();
			btnEditarComponenteIe.setBounds(new Rectangle(530, 71, 91, 22));
			btnEditarComponenteIe.setText("Editar");
			btnEditarComponenteIe.setToolTipText("Editar descrição do componente curricular no cadastro geral");
			btnEditarComponenteIe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desabilitarComponente();
					btnNovoComponente.setEnabled(true);
					btnCancelarComponente.setEnabled(false);
					if ((formComponente == null) || (formComponente.isClosed())) {
						formComponente = new FrmCadastroComponente(desk, getPermissao(), 1, (String) cmbComponente.getSelectedItem());
						desk.add(formComponente, new Integer(2));
						formComponente.show();
					}
				}});
		}
		return btnEditarComponenteIe;
	}

}
