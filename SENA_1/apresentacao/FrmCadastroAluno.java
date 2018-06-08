package apresentacao;

//--Imports de classes internas do java
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import negocios.Aluno;
import negocios.AlunoSelecionaFoto;

/**
 * @author Sullyvan Puppi
 *
 * Formulário de cadastro e exibição de alunos.
 */
public class FrmCadastroAluno extends JInternalFrame{

	/**--------------Interface com o usuário-------------------------------------------------**/

	private static final long serialVersionUID = 3754833489803989258L;

	private JPanel jContentPane = null;
	private JButton btnGerarRel = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JTabbedPane abaPrincipal = null;
	private JPanel abaDados = null;
	private JPanel abaSenha = null;
	private JLabel lblCd = null;
	private JTextField txtCd = null;
	private JLabel lblNome = null;
	private JTextField txtNome = null;
	private JLabel lblSexo = null;
	private JComboBox cmbsexo = null;
	private JLabel lblNacionalidade = null;
	private JTextField txtNacionalidade = null;
	private JLabel lblNatural = null;
	private JTextField txtNatural = null;
	private JLabel lblNascimento = null;
	private JFormattedTextField txtNascimento = null;
	private JLabel lblPai = null;
	private JTextField txtPai = null;
	private JLabel lblMae = null;
	private JTextField txtMae = null;
	private JLabel lblSenha = null;
	private JButton btnGerarSenha = null;
	private JLabel lblEndereco = null;
	private JLabel lblNum = null;
	private JLabel lblBairro = null;
	private JLabel lblCidade = null;
	private JComboBox cmbEstado = null;
	private JLabel lblEstado = null;
	private JLabel lblCep = null;
	private JLabel lblTel = null;
	private JLabel lblEmail = null;
	private JTextField txtEndereco = null;
	private JTextField txtNumero = null;
	private JTextField txtBairro = null;
	private JTextField txtCidade = null;
	private JFormattedTextField txtCep = null;
	private JFormattedTextField txtTel = null;
	private JTextField txtEmail = null;
	private JLabel lblCpf = null;
	private JLabel lblRg = null;
	private JFormattedTextField txtCpf = null;
	private JTextField txtRg = null;
	private JLabel lblImgFoto = null;
	private JButton btnCarregar = null;
	private JLabel lblFoto = null;
	private JButton btnEditar = null;
	private JButton btnLimpar = null;
	private JLabel lblComplemento = null;
	private JTextField txtComplemento = null;
	private JLabel lbllogin = null;
	private JLabel lblSenha2 = null;
	private JTextField txtLogin = null;
	private JPasswordField txtSenha = null;	
	private JLabel lblEstadoN = null;
	private JComboBox cmbEstadoN = null;
	private JLabel lblDesde = null;
	private JFormattedTextField txtDtEntrada = null;
	//Armazenará a lista de estados brasileiros que aparece no comboBox
	private DefaultComboBoxModel estados;
	//Sexos
	private DefaultComboBoxModel sexos;

	//--Aba responsável
	private JLabel lblNomeResponsavel = null;
	private JTextField txtNomeResponsavel = null;
	private JLabel lblSexoResponsavel = null;
	private JComboBox cmbsexoResponsavel = null;
	private JLabel lblNacionalidadeResponsavel = null;
	private JTextField txtNacionalidadeResponsavel = null;
	private JLabel lblNaturalResponsavel = null;
	private JTextField txtNaturalResponsavel = null;
	private JLabel lblNascimentoResponsavel = null;
	private JFormattedTextField txtNascimentoResponsavel = null;
	private JLabel lblEnderecoResponsavel = null;
	private JLabel lblNumResponsavel = null;
	private JLabel lblBairroResponsavel = null;
	private JLabel lblCidadeResponsavel = null;
	private JComboBox cmbEstadoResponsavel = null;
	private JLabel lblEstadoResponsavel = null;
	private JLabel lblCepResponsavel = null;
	private JLabel lblTelResponsavel = null;
	private JLabel lblEmailResponsavel = null;
	private JTextField txtEnderecoResponsavel = null;
	private JTextField txtNumeroResponsavel = null;
	private JTextField txtBairroResponsavel = null;
	private JTextField txtCidadeResponsavel = null;
	private JFormattedTextField txtCepResponsavel = null;
	private JFormattedTextField txtTelResponsavel = null;
	private JTextField txtEmailResponsavel = null;
	private JLabel lblCpfResponsavel = null;
	private JLabel lblRgResponsavel = null;
	private JFormattedTextField txtCpfResponsavel = null;
	private JTextField txtRgResponsavel = null;
	private JTextField txtOrgaoResponsavel = null;
	private JLabel lblComplementoResponsavel = null;
	private JTextField txtComplementoResponsavel = null;
	private JLabel lblEstadoNResponsavel = null;
	private JComboBox cmbEstadoNResponsavel = null;

	//Armazena matricula atual
	private int matricula;
	//Retorna matricula atual
	public int getMatricula(){
		return this.matricula;
	}
	//--Gerar matricula
	public void gerarMatricula(){
		Aluno novo = new Aluno();
		this.matricula = novo.getMatriculaNova();
		getTxtCd().setText(""+getMatricula()+"");
	}	 	
	//Armazena inteiro que representa permissão no sistema
	private int permissao;
	//Retorna inteiro que representa permissão no sistema
	public int getPermissao(){
		return permissao;	
	}
	//--Armazena opção 0 para novo cadastro e 1 para exibir dados
	private int op = 0;
	//--Retorna opção
	public int getOp(){
		return this.op;
	}
	Aluno consultar;
	//---Ajusta opção
	public void ajustaOp(){
		consultar = new Aluno();
		if(getOp()==0){//Novo cadastro
			getBtnEditar().setEnabled(false);
			getBtnGerarRel().setEnabled(false);
			getBtnLimpar().setEnabled(false);
			getBtnAlterarClasse().setEnabled(false);
			txtDtEntrada.setText(consultar.getHoje());
		}else{//Exibir dados
			if(getPermissao()==1){//Administrador				
				getBtnEditar().setEnabled(true);//Administrador total acesso
				getBtnConfirmar().setEnabled(true);
				getBtnGerarSenha().setEnabled(true);
				validarClasse();
			}else if(getPermissao()==2){//Coordenador de cursos
				getBtnEditar().setEnabled(false);//não cadastra nem edita aluno
				getBtnConfirmar().setEnabled(false);
				getBtnGerarSenha().setEnabled(false);
			}else if(getPermissao()==3){//Secretaria cadastra e edita dados do aluno
				getBtnEditar().setEnabled(true);
				getBtnConfirmar().setEnabled(true);
				getBtnGerarSenha().setEnabled(true);
				validarClasse();
			}else if(getPermissao()==4){//Convidado
				getBtnEditar().setEnabled(false);
				getBtnConfirmar().setEnabled(false);
				getBtnGerarSenha().setEnabled(false);
			}
		}
	}
	//Caminho da foto
	private String caminhoFoto = "";
	/**
	 * @return the caminhoFoto
	 */
	public String getCaminhoFoto() {
		return caminhoFoto;
	}
	/**
	 * @param caminhoFoto the caminhoFoto to set
	 */
	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}
	//---Ajusta Foto do aluno
	public void ajustaFoto(int opc){
		if(opc==0){//-- 0 - Para limpar
			setCaminhoFoto("");
			abaDados.remove(lblImgFoto);
			lblImgFoto = new JLabel();
			lblImgFoto.setIcon(new ImageIcon(""));
			abaDados.add(lblImgFoto, null);
			lblImgFoto.setBounds(new Rectangle(610, 14, 122, 163));			
		}else if(opc==1){//-- 1 - Para carregar
			try {
				AlunoSelecionaFoto selecionaFoto = new AlunoSelecionaFoto(""+getTxtCd().getText()+"");
				setCaminhoFoto(selecionaFoto.getCaminhoFoto());
				lblImgFoto = new JLabel();
				lblImgFoto.setIcon(new ImageIcon(getClass().getResource(selecionaFoto.getCaminhoFoto())));
				abaDados.add(lblImgFoto, null);
				lblImgFoto.setBounds(new Rectangle(610, 14, 122, 163));	
			} catch (Exception e) {
				
			}			
		}else if(opc==2){//Ajusta foto para exibição
			lblImgFoto = new JLabel();
			lblImgFoto.setIcon(new ImageIcon(getClass().getResource(getCaminhoFoto())));
			abaDados.add(lblImgFoto, null);
			lblImgFoto.setBounds(new Rectangle(610, 14, 122, 163));
		}else if(opc==3){//Confirmar foto para salvar

		}

	}

	//--Ajusta campos para exibição
	public void ajustaAluno(String matricula){
		Aluno consultar = new Aluno();
		consultar.consultarAluno(matricula);
		getTxtCd().setText(consultar.getMatricula());
		getTxtNome().setText(consultar.getNmAluno());
		getTxtCpf().setText(consultar.getCdCpf());
		getTxtRg().setText(consultar.getCdRg());
		getTxtOrgao().setText(consultar.getRgOrgao());
		cmbsexo.setSelectedItem((String) consultar.getSexo());
		getTxtNascimento().setText(consultar.getDtNascimento());
		getTxtNatural().setText(consultar.getNaturalidade());
		cmbEstadoN.setSelectedItem(consultar.getEstadoNatural());
		getTxtNacionalidade().setText(consultar.getNacionalidade());
		getTxtEndereco().setText(consultar.getEndereco());
		getTxtNumero().setText(consultar.getNumero());
		getTxtComplemento().setText(consultar.getComplemento());
		getTxtBairro().setText(consultar.getBairro());
		getTxtCidade().setText(consultar.getCidade());
		cmbEstado.setSelectedItem((String) consultar.getEstado());
		getTxtCep().setText(consultar.getCep());
		getTxtTel().setText(consultar.getTelefone());
		getTxtEmail().setText(consultar.getEmail());
		getTxtPai().setText(consultar.getNmPai());
		getTxtMae().setText(consultar.getNmMae());
		setCaminhoFoto(consultar.getCaminhoFoto());
		getTxtDtEntrada().setText(consultar.getDtMatricula());
		ajustaFoto(2);
		getTxtLogin().setText(consultar.getNmLogin());
		getTxtSenha().setText(consultar.getSenha());
		getChkPerfil().setSelected(consultar.getExibirPerfil());
		getChkCurriculo().setSelected(consultar.getExibirCurriculo());
		getChkProsContras().setSelected(consultar.getExibirProsContras());
		consultarClasses(getTxtCd().getText());	
		consultarPro(getTxtCd().getText());
		consultarContra(getTxtCd().getText());
		consultarDocApresentados(getTxtCd().getText());
		consultarDocPendentes(getTxtCd().getText());
		consultarDocSolicitados(getTxtCd().getText());
		if(getIdade()<18){
			ajustaResponsavel();
		}
		desabilitarCampos();
	}
	public int getIdade(String dtNasc){
		Aluno consultar = new Aluno();
		return consultar.calculaIdade(dtNasc);
	}
	public void ajustaResponsavel(){
		getTxtNome().setText(consultar.getNmAluno());
		getTxtCpf().setText(consultar.getCdCpf());
		getTxtRg().setText(consultar.getCdRg());
		getTxtOrgao().setText(consultar.getRgOrgao());
		cmbsexo.setSelectedItem((String) consultar.getSexo());
		getTxtNascimento().setText(consultar.getDtNascimento());
		getTxtNatural().setText(consultar.getNaturalidade());
		cmbEstadoN.setSelectedItem(consultar.getEstadoNatural());
		getTxtNacionalidade().setText(consultar.getNacionalidade());
		getTxtEndereco().setText(consultar.getEndereco());
		getTxtNumero().setText(consultar.getNumero());
		getTxtComplemento().setText(consultar.getComplemento());
		getTxtBairro().setText(consultar.getBairro());
		getTxtCidade().setText(consultar.getCidade());
		cmbEstado.setSelectedItem((String) consultar.getEstado());
		getTxtCep().setText(consultar.getCep());
		getTxtTel().setText(consultar.getTelefone());
		getTxtEmail().setText(consultar.getEmail());
		getTxtPai().setText(consultar.getNmPai());
		getTxtMae().setText(consultar.getNmMae());
		setCaminhoFoto(consultar.getCaminhoFoto());
		txtDtEntrada.setText(consultar.getDtMatricula());
		ajustaFoto(2);
		getTxtLogin().setText(consultar.getNmLogin());
		getTxtSenha().setText(consultar.getSenha());
	}

	//----Desabilitar campos
	public void desabilitarCampos(){
		getTxtNome().setEditable(false);
		getTxtCpf().setEditable(false);
		getTxtRg().setEditable(false);
		getTxtOrgao().setEditable(false);
		cmbsexo.setEnabled(false);
		getTxtNascimento().setEditable(false);
		getTxtNatural().setEditable(false);
		cmbEstadoN.setEnabled(false);
		getTxtNacionalidade().setEditable(false);
		getTxtEndereco().setEditable(false); 
		getTxtNumero().setEditable(false);
		getTxtComplemento().setEditable(false);
		getTxtBairro().setEditable(false);
		getTxtCidade().setEditable(false);
		cmbEstado.setEnabled(false);
		getTxtCep().setEditable(false);
		getTxtTel().setEditable(false);
		getTxtEmail().setEditable(false);
		getTxtPai().setEditable(false);
		getTxtMae().setEditable(false);
		getTxtSenha().setEditable(false);
		getTxtLogin().setEditable(false);
		getBtnLimpar().setEnabled(false);
		getBtnCarregar().setEnabled(false);
		getBtnRemoverPro().setEnabled(false);
		getBtnRemoverContra().setEnabled(false);
	}
	//----Habilitar campos
	public void habilitarCampos(){
		getTxtNome().setEditable(true);
		getTxtCpf().setEditable(true);
		getTxtRg().setEditable(true);
		getTxtOrgao().setEditable(true);
		cmbsexo.setEnabled(true);
		getTxtNascimento().setEditable(true);
		getTxtNatural().setEditable(true);
		cmbEstadoN.setEnabled(true);
		getTxtNacionalidade().setEditable(true);
		getTxtEndereco().setEditable(true); 
		getTxtNumero().setEditable(true);
		getTxtComplemento().setEditable(true);
		getTxtBairro().setEditable(true);
		getTxtCidade().setEditable(true);
		cmbEstado.setEnabled(true);
		getTxtCep().setEditable(true);
		getTxtTel().setEditable(true);
		getTxtEmail().setEditable(true);
		getTxtPai().setEditable(true);
		getTxtMae().setEditable(true);
		if(getCaminhoFoto().equals("")){
			getBtnLimpar().setEnabled(false);
			getBtnCarregar().setEnabled(true);
		}else{
			getBtnLimpar().setEnabled(true);
			getBtnCarregar().setEnabled(false);
		}
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
	private int getIdade(){
		Aluno calcular = new Aluno();
		return calcular.calculaIdade(getTxtNascimento().getText());
	}
	public boolean maiorIdade(JComponent txtNascimento){
		Aluno calcular = new Aluno();
		if(calcular.calculaIdade(getTxtNascimento().getText())>0 && calcular.calculaIdade(getTxtNascimento().getText())<18){
			return false;
		}else{
			return true;
		}
	}
	DefaultTableModel tabelaHistoricoPro;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaHistoricoPro(){
		return this.tabelaHistoricoPro;
	}
	//--Atributo para armazenar item selecionado na tabela
	private String selecionadoPro = "";	
	//--Ajustr seleção
	private void setSelecionadoPro(Object selecao){
		this.selecionadoPro = ""+selecao; 
	}
	//--Retornar seleção
	public String getSelecionadoPro(){
		return this.selecionadoPro;
	}
	private JScrollPane jspTbHistoricoPro = null;

	private JTable jtbHistoricoPro = null;
	//----Método para construir tabela de pesquisa
	public void construirTabelaHistoricoPro(){
		jtbHistoricoPro = new JTable(getTabelaHistoricoPro());
		jspTbHistoricoPro = new JScrollPane( jtbHistoricoPro );
		jspTbHistoricoPro.setBounds(12, 38, 215, 322);
		jtbHistoricoPro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Aluno consultar = new Aluno();
				setSelecionadoPro((Object) jtbHistoricoPro.getValueAt(jtbHistoricoPro.getSelectedRow(), 0));
				if(!getSelecionadoPro().equals("")){
					consultar.consultarAtividade(1,getTxtCd().getText(), getSelecionadoPro());
					getTxtDtAtividade().setText(consultar.getDtHistoricoPro());
					getTxtDsAtividade().setText(consultar.getDsHistoricoPro());
					getBtnRemoverPro().setEnabled(true);
				}else{
					getBtnRemoverPro().setEnabled(false);
				}				
			}
		});
		abaHistorico.add(jspTbHistoricoPro, null);
	}
	//----Consultar camada de negócios - Classe Aluno
	public void consultarPro(String cdMatricula){
		consultar = new Aluno();
		consultar.consultarHistoricoPro(cdMatricula);
		this.tabelaHistoricoPro = consultar.getTabelaHistoricoPro();
		if(tabelaHistoricoPro.getRowCount()<=0){
			Object linha[] = new Object[1];
			linha[0] = "";
			tabelaHistoricoPro.addRow(linha);
			construirTabelaHistoricoPro();			
		}else{
			construirTabelaHistoricoPro();
		}
	}
	DefaultTableModel tabelaHistoricoContra;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaHistoricoContra(){
		return this.tabelaHistoricoContra;
	}
	//--Atributo para armazenar item selecionado na tabela
	private String selecionadoContra = "";	
	//--Ajustr seleção
	private void setSelecionadoContra(Object selecao){
		this.selecionadoContra = ""+selecao; 
	}
	//--Retornar seleção
	public String getSelecionadoContra(){
		return this.selecionadoContra;
	}
	private JScrollPane jspTbHistoricoContra = null;

	private JTable jtbHistoricoContra = null;
	//----Método para construir tabela de pesquisa
	public void construirTabelaHistoricoContra(){
		jtbHistoricoContra = new JTable(getTabelaHistoricoContra());
		jspTbHistoricoContra = new JScrollPane( jtbHistoricoContra );
		jspTbHistoricoContra.setBounds(257, 38, 215, 322);
		jtbHistoricoContra.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Aluno consultar = new Aluno();
				setSelecionadoContra((Object) jtbHistoricoContra.getValueAt(jtbHistoricoContra.getSelectedRow(), 0));
				if(!getSelecionadoContra().equals("")){
					consultar.consultarAtividade(2,getTxtCd().getText(), getSelecionadoContra());
					getTxtDtAtividade().setText(consultar.getDtHistoricoContra());
					getTxtDsAtividade().setText(consultar.getDsHistoricoContra());
					getBtnRemoverContra().setEnabled(true);
				}else{
					getBtnRemoverContra().setEnabled(false);
				}				
			}
		});
		abaHistorico.add(jspTbHistoricoContra, null);
	}
	//----Consultar camada de negócios - Classe Aluno
	public void consultarContra(String cdMatricula){
		consultar = new Aluno();
		consultar.consultarHistoricoContra(cdMatricula);
		this.tabelaHistoricoContra = consultar.getTabelaHistoricoContra();
		if(tabelaHistoricoContra.getRowCount()<=0){
			Object linha[] = new Object[1];
			linha[0] = "";
			tabelaHistoricoContra.addRow(linha);
			construirTabelaHistoricoContra();			
		}else{
			construirTabelaHistoricoContra();
		}
	}

	DefaultTableModel tabelaDocApresentados;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaDocApresentados(){
		return this.tabelaDocApresentados;
	}
	//--Atributo para armazenar item selecionado na tabela
	private String docApresentado = "";	
	//--Ajustr seleção
	private void setDocApresentado(Object selecao){
		this.docApresentado = ""+selecao; 
	}
	//--Retornar seleção
	public String getDocApresentado(){
		return this.docApresentado;
	}
	private JScrollPane jspTbDocApresentados = null;

	private JTable jtbDocApresentados = null;
	//----Método para construir tabela de pesquisa
	public void construirTabelaDocApresentados(){
		jtbDocApresentados = new JTable(getTabelaDocApresentados());
		jspTbDocApresentados = new JScrollPane( jtbDocApresentados );
		jspTbDocApresentados.setBounds(12, 38, 215, 217);
		jtbDocApresentados.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setDocApresentado((Object) jtbDocApresentados.getValueAt(jtbDocApresentados.getSelectedRow(), 0));
			}
		});
		abaDocumentos.add(jspTbDocApresentados, null);
	}
	//----Consultar camada de negócios - Classe Aluno
	public void consultarDocApresentados(String cdMatricula){
		consultar = new Aluno();
		consultar.consultarDocApresentados(cdMatricula);
		this.tabelaDocApresentados = consultar.getTabelaDocApresentados();
		if(tabelaDocApresentados.getRowCount()<=0){
			Object linha[] = new Object[1];
			linha[0] = "";
			tabelaDocApresentados.addRow(linha);
			construirTabelaDocApresentados();			
		}else{
			construirTabelaDocApresentados();
		}
	}
	DefaultTableModel tabelaDocPendentes;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaDocPendentes(){
		return this.tabelaDocPendentes;
	}
	//--Atributo para armazenar item selecionado na tabela
	private String docPendente = "";	
	//--Ajustr seleção

	private void setDocPendente(Object selecao){
		this.docPendente = ""+selecao; 
	}
	//--Retornar seleção
	public String getDocPendente(){
		return this.docPendente;
	}
	private JScrollPane jspTbDocPendentes = null;

	private JTable jtbDocPendentes = null;
	//----Método para construir tabela de pesquisa
	public void construirTabelaDocPendentes(){
		jtbDocPendentes = new JTable(getTabelaDocPendentes());
		jspTbDocPendentes = new JScrollPane( jtbDocPendentes );
		jspTbDocPendentes.setBounds(257, 38, 215, 217);
		jtbDocPendentes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setDocPendente((Object) jtbDocPendentes.getValueAt(jtbDocPendentes.getSelectedRow(), 0));
			}
		});
		abaDocumentos.add(jspTbDocPendentes, null);
	}
	//----Consultar camada de negócios - Classe Aluno
	public void consultarDocPendentes(String cdMatricula){
		consultar = new Aluno();
		consultar.consultarDocPendentes(cdMatricula);
		this.tabelaDocPendentes = consultar.getTabelaDocPendentes();
		if(tabelaDocPendentes.getRowCount()<=0){
			Object linha[] = new Object[1];
			linha[0] = "";
			tabelaDocPendentes.addRow(linha);
			construirTabelaDocPendentes();			
		}else{
			construirTabelaDocPendentes();
		}
	}
	DefaultTableModel tabelaDocSolicitados;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaDocSolicitados(){
		return this.tabelaDocSolicitados;
	}
	//--Atributo para armazenar item selecionado na tabela
	private String docSolicitado = "";	
	//--Ajustr seleção
	private void setDocSolicitado(Object selecao){
		this.docSolicitado = ""+selecao; 
	}
	//--Retornar seleção
	public String getDocSolicitado(){
		return this.docSolicitado;
	}
	private JScrollPane jspTbDocSolicitados = null;

	private JTable jtbDocSolicitados = null;
	//----Método para construir tabela de pesquisa
	public void construirTabelaDocSolicitados(){
		jtbDocSolicitados = new JTable(getTabelaDocSolicitados());
		jspTbDocSolicitados = new JScrollPane( jtbDocSolicitados );
		jspTbDocSolicitados.setBounds(502, 38, 215, 217);
		jtbDocSolicitados.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setDocSolicitado((Object) jtbDocSolicitados.getValueAt(jtbDocSolicitados.getSelectedRow(), 0));
			}
		});
		abaDocumentos.add(jspTbDocSolicitados, null);
	}
	//----Consultar camada de negócios - Classe Aluno
	public void consultarDocSolicitados(String cdMatricula){
		consultar = new Aluno();
		consultar.consultarDocSolicitados(cdMatricula);
		this.tabelaDocSolicitados = consultar.getTabelaDocSolicitados();
		if(tabelaDocSolicitados.getRowCount()<=0){
			Object linha[] = new Object[1];
			linha[0] = "";
			tabelaDocSolicitados.addRow(linha);
			construirTabelaDocSolicitados();			
		}else{
			construirTabelaDocSolicitados();
		}
	}
	DefaultTableModel tabelaHistoricoClasses;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaHistoricoClasses(){
		return this.tabelaHistoricoClasses;
	}
	//--Atributo para armazenar item selecionado na tabela
	private String selecionadoClasses = "";	
	//--Ajustr seleção
	private void setSelecionadoClasses(Object selecao){
		this.selecionadoClasses = ""+selecao; 
	}
	//--Retornar seleção
	public String getSelecionadoClasses(){
		return this.selecionadoClasses;
	}
	private JScrollPane jspTbHistoricoClasses = null;

	private JTable jtbHistoricoClasses = null;
	//----Método para construir tabela de pesquisa
	public void construirTabelaHistoricoClasses(){
		jtbHistoricoClasses = new JTable(getTabelaHistoricoClasses());
		jspTbHistoricoClasses = new JScrollPane( jtbHistoricoClasses );
		jspTbHistoricoClasses.setBounds(12, 156, 706, 226);
		jtbHistoricoClasses.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionadoClasses((Object) jtbHistoricoClasses.getValueAt(jtbHistoricoClasses.getSelectedRow(), 0));
				if(getTxtStatusClasse().getText().equals("Regular")){
					getBtnAlterarClasse().setEnabled(true);
				}else{
					getBtnAlterarClasse().setEnabled(false);
				}
			}
		});
		abaClasse.add(jspTbHistoricoClasses, null);
	}
	//----Consultar camada de negócios - Classe Aluno
	public void consultarClasses(String cdMatricula){
		consultar = new Aluno();
		consultar.consultarHistoricoClasses(cdMatricula);
		this.tabelaHistoricoClasses = consultar.getTabelaHistoricoClasses();
		if(tabelaHistoricoClasses.getRowCount()<=0){
			Object linha[] = new Object[3];
			linha[0] = "";
			linha[1] = "";
			linha[2] = "";
			tabelaHistoricoClasses.addRow(linha);
			construirTabelaHistoricoClasses();			
		}else{
			construirTabelaHistoricoClasses();
		}
	}
	public void validarClasse(){
		if(getTxtNmCurso().getText().equals("Regular")){
			getBtnAlterarClasse().setEnabled(true);
		}
	}
	//Atributos de chekbox
	private boolean perfil;
	private boolean curriculo;
	private boolean prosContras;

	public boolean getPerfil(){
		return perfil;
	}
	public boolean getCurriculo(){
		return curriculo;
	}
	public boolean getProsContras(){
		return prosContras;
	}

	public int validaDocApresentados(){//Retorna 1 se encontrar menos de 2 documentos apresentados
		int x = 0;
		if(tabelaDocApresentados.getRowCount()<2){
			x = 1;			
		}	
		return x;
	}


	//Formulários que este chama
	DialogoHistoricoPro dialogoPro;
	DialogoHistoricoContra dialogoContra;
	DialogoDocumentoApresentado dialogoDocApresentado;
	DialogoDocumentoPendente dialogoDocPendente;
	DialogoDocumentoSolicitado dialogoDocSolicitado;
	DialogoAlterarClasse dialogoAlterarClasse;

	//Área de trabalho do formulário de cadastro de aluno
	JDesktopPane desk = new JDesktopPane();

	private JLabel lblOrgao = null;

	private JTextField txtOrgao = null;

	private JPanel abaResponsavel = null;

	private JCheckBox chkEndereco = null;

	private JLabel lblConf = null;

	private JLabel lblSkin = null;

	private JComboBox cmbSkins = null;

	private JLabel lblExibicao = null;

	private JCheckBox chkPerfil = null;

	private JCheckBox chkCurriculo = null;

	private JButton btnConfirmarSenaQuatro = null;

	private JCheckBox chkProsContras = null;

	private JPanel abaClasse = null;

	private JPanel abaHistorico = null;

	private JPanel abaDocumentos = null;

	private JLabel lblHistoricoPro = null;

	private JButton btnAddPro = null;

	private JButton btnRemoverPro = null;

	private JLabel lblContra = null;

	private JTextArea txtDsAtividade = null;

	private JButton btnAddContra = null;

	private JButton btnRemoverContra = null;

	private JLabel lblApresentados = null;

	private JFormattedTextField txtDtAtividade = null;

	private JLabel lblPendentes = null;

	private JLabel lblSolicitado = null;

	private JTextArea txtDsDocApresentado = null;

	private JTextArea txtDsDocPendente = null;

	private JTextArea txtDsDocSolicitado = null;

	private JButton btnAddDocApresentado = null;

	private JButton btnRemoverDocApresentado = null;

	private JButton btnAddDocPendente = null;

	private JButton btnRemoverDocPendente = null;

	private JButton btnAvaliarDocSolicitado = null;

	private JButton btnRemoverDocSolicitado = null;

	private JPanel abaCanal = null;

	private JLabel lblMsgs = null;

	private JComboBox cmbMsgs = null;

	private JButton btnResponder = null;

	private JButton btnNovaMsg = null;

	private JButton btnRemoverMsg = null;

	private JLabel lblCanalNome = null;

	private JTextField txtCanalNome = null;

	private JLabel lblAssunto = null;

	private JTextField txtCanalAssunto = null;

	private JLabel lblMensagem = null;

	private JTextArea txtCanalMsg = null;

	private JPanel abaDesempenho = null;

	private JLabel lblAtividade = null;

	private JLabel lblClasse = null;

	private JTextField txtNmClasse = null;

	private JLabel lblCurso = null;

	private JTextField txtNmCurso = null;

	private JLabel lblCiclo = null;

	private JTextField txtCiclo = null;

	private JLabel lblDtInicio = null;

	private JFormattedTextField txtDtInicio = null;

	private JLabel lblStatus = null;

	private JTextField txtStatusClasse = null;

	private JLabel lblDivisao2 = null;

	private JLabel lblClasses = null;

	private JButton btnAlterarClasse = null;

	private JButton btnEnviarMsg = null;

	private JLabel lblDtMsg = null;

	private JTextField txtDtMsg = null;

	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroAluno(JDesktopPane desktop, int permissao, int op, String matricula, String nmAluno) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		initialize(op, matricula, nmAluno);
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int op, String matricula, String nmAluno) {
		String titulo = "";
		if(op==0){//Novo cadastro
			titulo = "][ Novo cadastro ][";
			gerarMatricula();
			this.setContentPane(getJContentPane());
			consultarDocApresentados(getTxtCd().getText());
			consultarDocPendentes(getTxtCd().getText());
			consultarDocSolicitados(getTxtCd().getText());
		}else if(op==1){//Exibir dados
			titulo="][ Cadastro do aluno ][ Aluno: "+nmAluno;
			this.setContentPane(getJContentPane());
			ajustaAluno(matricula);
			abaPrincipal.addTab("Canal direto", null, getAbaCanal(), null);
		}
		this.setSize(744, 485);		
		this.op = op;
		ajustaOp();
		this.setTitle(titulo);	
		getTxtCd().setEnabled(false);
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
			JLabel lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-44, 415, 830, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao.setForeground(new Color(190, 190, 190));
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getAbaPrincipal(), null);
			jContentPane.add(getBtnGerarRel(), null);
			jContentPane.add(getBtnConfirmar(), null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnEditar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnGerarRel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGerarRel() {
		if (btnGerarRel == null) {
			btnGerarRel = new JButton();
			btnGerarRel.setBounds(new Rectangle(330, 435, 91, 21));
			btnGerarRel.setText("Relatório");
			btnGerarRel.setToolTipText("Gerar relatório do aluno");
			btnGerarRel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}});
		}
		return btnGerarRel;
	}

	/**
	 * This method initializes btnConfirmar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton();
			btnConfirmar.setBounds(new Rectangle(530, 435, 91, 21));
			btnConfirmar.setText("Confirmar");
			btnConfirmar.setToolTipText("Confirmar cadastro do aluno");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aluno cadastrar = new Aluno();
					cadastrar.ajustaAluno(getTxtCd().getText(),getTxtNome().getText(), getTxtCpf().getText(), 
							getTxtRg().getText(), getTxtOrgao().getText(), (String) cmbsexo.getSelectedItem(), getTxtNascimento().getText(), 
							getTxtNatural().getText(), (String) cmbEstadoN.getSelectedItem(), getTxtNacionalidade().getText(), getTxtEndereco().getText(), 
							getTxtNumero().getText(), getTxtComplemento().getText(), getTxtBairro().getText(), getTxtCidade().getText(), 
							(String) cmbEstado.getSelectedItem(), getTxtCep().getText(), getTxtTel().getText(), getTxtEmail().getText(), 
							getTxtPai().getText(), getTxtMae().getText(), getCaminhoFoto(), getTxtDtEntrada().getText());
					if(cadastrar.validarAluno()>=1){//Se retornar 1 ou mais encontrada inconsistências
						JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarAluno()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
					}else{
						if(getIdade()<18){
							cadastrar.ajustaResponsavel(getTxtNomeResponsavel().getText(), getTxtCpfResponsavel().getText(), getTxtRgResponsavel().getText(), 
									getTxtOrgaoResponsavel().getText(), (String) cmbsexoResponsavel.getSelectedItem(), getTxtNascimentoResponsavel().getText(),
									getTxtNaturalResponsavel().getText(), (String) cmbEstadoNResponsavel.getSelectedItem(), getTxtNacionalidadeResponsavel().getText(), 
									getTxtEnderecoResponsavel().getText(), getTxtNumeroResponsavel().getText(), getTxtComplementoResponsavel().getText(), 
									getTxtBairroResponsavel().getText(), getTxtCidadeResponsavel().getText(), (String) cmbEstadoResponsavel.getSelectedItem(), 
									getTxtCepResponsavel().getText(), getTxtTelResponsavel().getText(), getTxtEmailResponsavel().getText());
							if(cadastrar.validarResponsavel()>=1){
								JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) nos dados do responsável "+cadastrar.validarAluno()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
							}else{
								if(getCaminhoFoto().equals("") || getCaminhoFoto().equals(null)){
									if (JOptionPane.showConfirmDialog(new JFrame(),
											"Confirmar cadastro do aluno sem foto?\n\nObs.: Aluno exibirá imagem padrão no SENA IV.", "Confirmação",
											JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
										if(validaDocApresentados()==1){
											JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) nos documentos apresentados para matrícula.\nÉ necessário a apresentação de 2 documentos no mínimo!","Dados incorretos",2);
										}else{
											cadastrar.cadastrarAluno();	
										}
										//Adicionar abas
										getTxtLogin().setText(getTxtCd().getText());
										getTxtSenha().setText(getTxtCd().getText());
									}
								}else{
									if(validaDocApresentados()==1){
										JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) nos documentos apresentados para matrícula.\nÉ necessário a apresentação de 2 documentos no mínimo!","Dados incorretos",2);
									}else{
										cadastrar.cadastrarAluno();
										//Adicionar abas
										getTxtLogin().setText(getTxtCd().getText());
										getTxtSenha().setText(getTxtCd().getText());
									}									
								}
							}
						}else{
							if(getCaminhoFoto().equals("") || getCaminhoFoto().equals(null)){
								if (JOptionPane.showConfirmDialog(new JFrame(),
										"Confirmar cadastro do aluno sem foto?\n\nObs.: Aluno exibirá imagem padrão no SENA IV.", "Confirmação",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
									/*if(validaDocApresentados()==1){
										JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) nos documentos apresentados.\nÉ necessário a apresentação de 2 documentos no mínimo!","Dados incorretos",2);
									}else*/{
										cadastrar.cadastrarAluno();
										//Adicionar abas
										getTxtLogin().setText(getTxtCd().getText());
										getTxtSenha().setText(getTxtCd().getText());
									}									
								}
							}else{
								if(validaDocApresentados()==1){
									JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) nos documentos apresentados para matrícula.\nÉ necessário a apresentação de 2 ou mais documentos!","Dados incorretos",2);
								}else{
									cadastrar.cadastrarAluno();
									//Adicionar abas
									getTxtLogin().setText(getTxtCd().getText());
									getTxtSenha().setText(getTxtCd().getText());
								}
							}
						}						
					}
				}});
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
			btnCancelar.setBounds(new Rectangle(630, 435, 91, 21));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar cadastro e sair");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();						
				}});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes abaPrincipal	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getAbaPrincipal() {
		if (abaPrincipal == null) {
			abaPrincipal = new JTabbedPane();
			abaPrincipal.setBounds(new Rectangle(2, 2, 737, 422));
			abaPrincipal.addTab("Dados pessoais", null, getAbaDados(), null);
			abaPrincipal.addTab("SENA IV", null, getAbaSenha(), null);
			abaPrincipal.addTab("Classe", null, getAbaClasse(), null);
			abaPrincipal.addTab("Desempenho", null, getAbaDesempenho(), null);
			abaPrincipal.addTab("Histórico na I.E.", null, getAbaHistorico(), null);
			abaPrincipal.addTab("Documentos", null, getAbaDocumentos(), null);	
		}
		return abaPrincipal;
	}

	/**
	 * This method initializes abaDados	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaDados() {
		if (abaDados == null) {
			lblOrgao = new JLabel();
			lblOrgao.setBounds(new Rectangle(318, 44, 59, 20));
			lblOrgao.setText("Expedidor");
			lblDesde = new JLabel();
			lblDesde.setBounds(new Rectangle(555, 370, 102, 20));
			lblDesde.setText("Data de matrícula");
			lblEstadoN = new JLabel();
			lblEstadoN.setBounds(new Rectangle(232, 104, 41, 20));
			lblEstadoN.setText("Estado");
			lblComplemento = new JLabel();
			lblComplemento.setBounds(new Rectangle(128, 164, 81, 20));
			lblComplemento.setText("Complemento");
			lblFoto = new JLabel();
			lblFoto.setBounds(new Rectangle(570, 14, 25, 20));
			lblFoto.setText("Foto");
			lblRg = new JLabel();
			lblRg.setBounds(new Rectangle(168, 44, 25, 20));
			lblRg.setText("R.G.");
			lblCpf = new JLabel();
			lblCpf.setBounds(new Rectangle(12, 44, 32, 20));
			lblCpf.setText("C.P.F");
			lblEmail = new JLabel();
			lblEmail.setBounds(new Rectangle(242, 224, 37, 20));
			lblEmail.setText("E-mail");
			lblTel = new JLabel();
			lblTel.setBounds(new Rectangle(105, 224, 51, 20));
			lblTel.setText("Telefone");
			lblCep = new JLabel();
			lblCep.setBounds(new Rectangle(12, 224, 23, 20));
			lblCep.setText("CEP");
			lblEstado = new JLabel();
			lblEstado.setBounds(new Rectangle(519, 194, 41, 20));
			lblEstado.setText("Estado");
			lblCidade = new JLabel();
			lblCidade.setBounds(new Rectangle(264, 194, 40, 20));
			lblCidade.setText("Cidade");
			lblBairro = new JLabel();
			lblBairro.setBounds(new Rectangle(12, 194, 38, 20));
			lblBairro.setText("Bairro");
			lblNum = new JLabel();
			lblNum.setBounds(new Rectangle(12, 164, 48, 20));
			lblNum.setText("Número");
			lblEndereco = new JLabel();
			lblEndereco.setBounds(new Rectangle(12, 134, 57, 20));
			lblEndereco.setText("Endereço");
			lblMae = new JLabel();
			lblMae.setBounds(new Rectangle(354, 254, 28, 20));
			lblMae.setText("Mãe");
			lblPai = new JLabel();
			lblPai.setBounds(new Rectangle(12, 254, 20, 20));
			lblPai.setText("Pai");
			lblNascimento = new JLabel();
			lblNascimento.setBounds(new Rectangle(12, 74, 114, 20));
			lblNascimento.setText("Data de nascimento");
			lblNatural = new JLabel();
			lblNatural.setBounds(new Rectangle(12, 104, 74, 20));
			lblNatural.setText("Naturalidade");
			lblNacionalidade = new JLabel();
			lblNacionalidade.setBounds(new Rectangle(312, 104, 84, 20));
			lblNacionalidade.setText("Nacionalidade");
			lblSexo = new JLabel();
			lblSexo.setBounds(new Rectangle(468, 44, 31, 20));
			lblSexo.setText("Sexo");
			lblNome = new JLabel();
			lblNome.setBounds(new Rectangle(127, 14, 38, 20));
			lblNome.setText("Nome");
			lblCd = new JLabel();
			lblCd.setBounds(new Rectangle(12, 14, 58, 20));
			lblCd.setText("Matrícula");			
			abaDados = new JPanel();
			abaDados.setLayout(null);
			abaDados.add(lblCd, null);
			abaDados.add(getTxtCd(), null);
			abaDados.add(lblNome, null);
			abaDados.add(getTxtNome(), null);
			abaDados.add(lblSexo, null);
			abaDados.add(getCmbsexo(), null);
			abaDados.add(lblNacionalidade, null);
			abaDados.add(getTxtNacionalidade(), null);
			abaDados.add(lblNatural, null);
			abaDados.add(lblNascimento, null);
			abaDados.add(getTxtNascimento(), null);
			abaDados.add(getTxtNatural(), null);
			abaDados.add(getTxtNascimento(), null);
			abaDados.add(lblPai, null);
			abaDados.add(getTxtPai(), null);
			abaDados.add(lblMae, null);
			abaDados.add(getTxtMae(), null);
			abaDados.add(lblEndereco, null);
			abaDados.add(lblNum, null);
			abaDados.add(lblBairro, null);
			abaDados.add(lblCidade, null);
			abaDados.add(getCmbEstado(), null);
			abaDados.add(lblEstado, null);
			abaDados.add(lblCep, null);
			abaDados.add(lblTel, null);
			abaDados.add(lblEmail, null);
			abaDados.add(getTxtEndereco(), null);
			abaDados.add(getTxtNumero(), null);
			abaDados.add(getTxtBairro(), null);
			abaDados.add(getTxtCidade(), null);
			abaDados.add(getTxtCep(), null);
			abaDados.add(getTxtTel(), null);
			abaDados.add(getTxtEmail(), null);
			abaDados.add(lblCpf, null);
			abaDados.add(lblRg, null);
			abaDados.add(getTxtCpf(), null);
			abaDados.add(getTxtRg(), null);
			abaDados.add(getBtnCarregar(), null);
			abaDados.add(lblFoto, null);
			abaDados.add(getBtnLimpar(), null);
			abaDados.add(lblComplemento, null);
			abaDados.add(getTxtComplemento(), null);
			abaDados.add(lblEstadoN, null);
			abaDados.add(getCmbEstadoN(), null);
			abaDados.add(lblDesde, null);
			abaDados.add(getTxtDtEntrada(), null);
			abaDados.add(lblOrgao, null);
			abaDados.add(getTxtOrgao(), null);
		}
		return abaDados;
	}

	/**
	 * This method initializes abaSenha	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaSenha() {
		if (abaSenha == null) {
			lblExibicao = new JLabel();
			lblExibicao.setBounds(new Rectangle(12, 164, 57, 20));
			lblExibicao.setText("Exibições");
			lblSkin = new JLabel();
			lblSkin.setBounds(new Rectangle(12, 134, 28, 20));
			lblSkin.setText("Skin");
			lblConf = new JLabel();
			lblConf.setBounds(new Rectangle(12, 104, 177, 20));
			lblConf.setText("Configuração de perfil do aluno");
			lblSenha2 = new JLabel();
			lblSenha2.setBounds(new Rectangle(12, 74, 38, 20));
			lblSenha2.setText("Senha");
			lbllogin = new JLabel();
			lbllogin.setBounds(new Rectangle(12, 44, 34, 20));
			lbllogin.setText("Login");
			lblSenha = new JLabel();
			lblSenha.setBounds(new Rectangle(12, 14, 273, 20));
			lblSenha.setText("Gerar senha provisória para acesso ao SENA IV");
			abaSenha = new JPanel();
			abaSenha.setLayout(null);
			abaSenha.add(lblSenha, null);
			abaSenha.add(getBtnGerarSenha(), null);
			abaSenha.add(lbllogin, null);
			abaSenha.add(lblSenha2, null);
			abaSenha.add(getTxtLogin(), null);
			abaSenha.add(getTxtSenha(), null);
			abaSenha.add(lblConf, null);
			abaSenha.add(lblSkin, null);
			abaSenha.add(getCmbSkins(), null);
			abaSenha.add(lblExibicao, null);
			abaSenha.add(getChkPerfil(), null);
			abaSenha.add(getChkCurriculo(), null);
			abaSenha.add(getBtnConfirmarSenaQuatro(), null);
			abaSenha.add(getChkProsContras(), null);
		}
		return abaSenha;
	}

	/**
	 * This method initializes txtCd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCd() {
		if (txtCd == null) {
			txtCd = new JTextField();
			txtCd.setBounds(new Rectangle(55, 14, 61, 22));
			txtCd.setBackground(Color.white);
			txtCd.setToolTipText("Matrícula do aluno");
		}
		return txtCd;
	}

	/**
	 * This method initializes txtNome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNome() {
		if (txtNome == null) {
			txtNome = new JTextField();
			txtNome.setBounds(new Rectangle(155, 14, 320, 22));
			txtNome.setBackground(Color.white);
			txtNome.setToolTipText("Nome completo do aluno");
		}
		return txtNome;
	}
	/**
	 * This method initializes cmbsexo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbsexo() {
		if (cmbsexo == null) {
			sexos = new DefaultComboBoxModel();
			sexos.addElement("----------");
			sexos.addElement(new String("Feminino"));
			sexos.addElement(new String("Masculino"));
			cmbsexo = new JComboBox(sexos);
			cmbsexo.setBounds(new Rectangle(493, 44, 101, 22));
			cmbsexo.setToolTipText("Sexo do aluno");
		}
		return cmbsexo;
	}

	/**
	 * This method initializes txtNacionalidade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNacionalidade() {
		if (txtNacionalidade == null) {
			txtNacionalidade = new JTextField();
			txtNacionalidade.setBounds(new Rectangle(379, 104, 217, 22));
			txtNacionalidade.setBackground(Color.white);
			txtNacionalidade.setToolTipText("Nacionalidade do aluno");
		}
		return txtNacionalidade;
	}

	/**
	 * This method initializes txtNatural	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNatural() {
		if (txtNatural == null) {
			txtNatural = new JTextField();
			txtNatural.setBounds(new Rectangle(74, 104, 152, 22));
			txtNatural.setBackground(Color.white);
			txtNatural.setToolTipText("Naturalidade do aluno");
		}
		return txtNatural;
	}

	/**
	 * This method initializes txtPai	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPai() {
		if (txtPai == null) {
			txtPai = new JTextField();
			txtPai.setBounds(new Rectangle(26, 254, 320, 22));
			txtPai.setBackground(Color.white);
			txtPai.setToolTipText("Nome do pai do aluno");
		}
		return txtPai;
	}

	/**
	 * This method initializes txtMae	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtMae() {
		if (txtMae == null) {
			txtMae = new JTextField();
			txtMae.setBounds(new Rectangle(374, 254, 320, 22));
			txtMae.setBackground(Color.white);
			txtMae.setToolTipText("Nome da mãe do aluno");
		}
		return txtMae;
	}
	/**
	 * This method initializes txtNascimento	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtNascimento() {
		if (txtNascimento == null) {
			txtNascimento = new JFormattedTextField(setMascara("##/##/####"));
			txtNascimento.setBounds(new Rectangle(107, 74, 86, 22));
			txtNascimento.setBackground(Color.white);
			txtNascimento.setToolTipText("Data de nascimento do aluno");
			txtNascimento.setInputVerifier(new InputVerifier() {
				public boolean verify(JComponent txtNascimento) {
					if (maiorIdade(txtNascimento)){
						if(abaResponsavel != null){
							abaPrincipal.remove(abaResponsavel);	
						}	
						return true; // give up focus
					}else{
						JOptionPane.showMessageDialog(null,"Aluno é menor de idade\ntem "+getIdade()+" anos\nObrigatório informação dos dados do responsável.","Menor de idade",2);
						abaPrincipal.addTab("Responsável", null, getAbaResponsavel(), null);
						return ((JFormattedTextField) txtNascimento).isEditValid();
					}
				}
			});
		}
		return txtNascimento;
	}
	/**
	 * This method initializes btnGerarSenha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGerarSenha() {
		if (btnGerarSenha == null) {
			btnGerarSenha = new JButton();
			btnGerarSenha.setBounds(new Rectangle(200, 74, 111, 21));
			btnGerarSenha.setText("Nova senha");
			btnGerarSenha.setToolTipText("Gerar nova senha para acesso ao SENA IV");
			btnGerarSenha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Confirmar opção de alterar senha
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Deseja alterar a senha deste aluno?\nObs.: Está ação usará seu código de matrícula como login e como senha.", "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						getTxtSenha().setText(getTxtCd().getText());
						getTxtLogin().setText(getTxtCd().getText());						
					}					
				}
			});
		}
		return btnGerarSenha;
	}

	/**
	 * This method initializes cmbEstado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbEstado() {
		if (cmbEstado == null) {
			estados = new DefaultComboBoxModel();
			estados.addElement("--");
			estados.addElement(new String("AC"));
			estados.addElement(new String("AL"));
			estados.addElement(new String("AP"));
			estados.addElement(new String("AM"));
			estados.addElement(new String("BA"));
			estados.addElement(new String("CE"));
			estados.addElement(new String("ES"));
			estados.addElement(new String("GO"));
			estados.addElement(new String("MA"));
			estados.addElement(new String("MT"));
			estados.addElement(new String("MS"));
			estados.addElement(new String("MG"));
			estados.addElement(new String("PA"));
			estados.addElement(new String("PB"));
			estados.addElement(new String("PR"));
			estados.addElement(new String("PE"));
			estados.addElement(new String("PI"));
			estados.addElement(new String("RJ"));
			estados.addElement(new String("RN"));
			estados.addElement(new String("RS"));
			estados.addElement(new String("RO"));
			estados.addElement(new String("RR"));
			estados.addElement(new String("SC"));
			estados.addElement(new String("SP"));
			estados.addElement(new String("SE"));
			estados.addElement(new String("TO"));
			estados.addElement(new String("DF"));
			cmbEstado = new JComboBox(estados);
			cmbEstado.setBounds(new Rectangle(553, 194, 41, 20));
			cmbEstado.setToolTipText("Estado de residência do aluno");
		}
		return cmbEstado;
	}

	/**
	 * This method initializes txtEndereco	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEndereco() {
		if (txtEndereco == null) {
			txtEndereco = new JTextField();
			txtEndereco.setBounds(new Rectangle(57, 134, 539, 22));
			txtEndereco.setBackground(Color.white);
			txtEndereco.setToolTipText("Endereço de residência do aluno");
		}
		return txtEndereco;
	}

	/**
	 * This method initializes txtNumero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(new Rectangle(49, 164, 67, 22));
			txtNumero.setBackground(Color.white);
			txtNumero.setToolTipText("Número no endereço de residênscia do aluno");
		}
		return txtNumero;
	}

	/**
	 * This method initializes txtBairro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtBairro() {
		if (txtBairro == null) {
			txtBairro = new JTextField();
			txtBairro.setBounds(new Rectangle(40, 194, 215, 22));
			txtBairro.setBackground(Color.white);
			txtBairro.setToolTipText("Bairro de residência do aluno");
		}
		return txtBairro;
	}

	/**
	 * This method initializes txtCidade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCidade() {
		if (txtCidade == null) {
			txtCidade = new JTextField();
			txtCidade.setBounds(new Rectangle(297, 194, 215, 22));
			txtCidade.setBackground(Color.white);
			txtCidade.setToolTipText("Cidade de residência do aluno");
		}
		return txtCidade;
	}

	/**
	 * This method initializes txtCep	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getTxtCep() {
		if (txtCep == null) {
			txtCep = new JFormattedTextField(setMascara("##.###-###"));
			txtCep.setBounds(new Rectangle(31, 223, 70, 22));			
			txtCep.setBackground(Color.white);
			txtCep.setToolTipText("C.E.P. da residência do aluno");
		}
		return txtCep;
	}


	/**
	 * This method initializes txtTel	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtTel() {
		if (txtTel == null) {
			txtTel = new JFormattedTextField(setMascara("(##)####-####"));
			txtTel.setBounds(new Rectangle(147, 224, 89, 22));
			txtTel.setBackground(Color.white);
			txtTel.setToolTipText("Telefone de contato do aluno");
		}
		return txtTel;
	}

	/**
	 * This method initializes txtEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setBounds(new Rectangle(271, 224, 325, 22));
			txtEmail.setBackground(Color.white);
			txtEmail.setToolTipText("Email de contato do aluno");
		}
		return txtEmail;
	}
	/**
	 * This method initializes txtCpf	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtCpf() {
		if (txtCpf == null) {
			txtCpf = new JFormattedTextField(setMascara("###.###.###-##"));
			txtCpf.setBounds(new Rectangle(39, 43, 119, 22));
			txtCpf.setBackground(Color.white);
			txtCpf.setToolTipText("C.P.F. do aluno");
		}
		return txtCpf;
	}

	/**
	 * This method initializes txtRg	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtRg() {
		if (txtRg == null) {
			txtRg = new JTextField();
			txtRg.setBounds(new Rectangle(187, 44, 125, 22));
			txtRg.setBackground(Color.white);
			txtRg.setToolTipText("R.G. do aluno");
		}
		return txtRg;
	}

	/**
	 * This method initializes btnCarregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCarregar() {
		if (btnCarregar == null) {
			btnCarregar = new JButton();
			btnCarregar.setBounds(new Rectangle(620, 194, 111, 22));
			btnCarregar.setText("Carregar");
			btnCarregar.setToolTipText("Carregar foto do aluno");
			btnCarregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaFoto(1);
					getBtnCarregar().setEnabled(false);
					getBtnLimpar().setEnabled(true);
				}
			});
		}
		return btnCarregar;
	}

	/**
	 * This method initializes btnEditar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton();
			btnEditar.setBounds(new Rectangle(430, 435, 91, 21));
			btnEditar.setText("Editar");
			btnEditar.setToolTipText("Editar cadastro do aluno");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarCampos();					
					getBtnConfirmar().setEnabled(true);
				}});
		}
		return btnEditar;
	}
	/**
	 * This method initializes btnLimpar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnLimpar() {
		if (btnLimpar == null) {
			btnLimpar = new JButton();
			btnLimpar.setBounds(new Rectangle(620, 219, 111, 22));
			btnLimpar.setText("Limpar");
			btnLimpar.setToolTipText("Limpar foto do aluno");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaFoto(0);
					getBtnCarregar().setEnabled(true);
					getBtnLimpar().setEnabled(false);
				}});
		}
		return btnLimpar;
	}

	/**
	 * This method initializes txtComplemento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtComplemento() {
		if (txtComplemento == null) {
			txtComplemento = new JTextField();
			txtComplemento.setBounds(new Rectangle(193, 164, 148, 22));
			txtComplemento.setBackground(Color.white);
			txtComplemento.setToolTipText("Complemento no endereço de residência do aluno");
		}
		return txtComplemento;
	}

	/**
	 * This method initializes txtLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLogin() {
		if (txtLogin == null) {
			txtLogin = new JTextField();
			txtLogin.setBounds(new Rectangle(41, 44, 152, 22));
			txtLogin.setBackground(Color.white);
			txtLogin.setToolTipText("Login de acesso ao SENA IV");
			txtLogin.setEnabled(false);
		}
		return txtLogin;
	}

	/**
	 * This method initializes txtSenha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtSenha() {
		if (txtSenha == null) {
			txtSenha = new JPasswordField();
			txtSenha.setBounds(new Rectangle(41, 74, 152, 22));
			txtSenha.setBackground(Color.white);
			txtSenha.setToolTipText("Senha de acesso ao SENA IV");
			txtSenha.setEnabled(false);
		}
		return txtSenha;
	}
	/**
	 * This method initializes cmbEstadoN	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbEstadoN() {
		if (cmbEstadoN == null) {
			estados = new DefaultComboBoxModel();
			estados.addElement("--");
			estados.addElement(new String("AC"));
			estados.addElement(new String("AL"));
			estados.addElement(new String("AP"));
			estados.addElement(new String("AM"));
			estados.addElement(new String("BA"));
			estados.addElement(new String("CE"));
			estados.addElement(new String("ES"));
			estados.addElement(new String("GO"));
			estados.addElement(new String("MA"));
			estados.addElement(new String("MT"));
			estados.addElement(new String("MS"));
			estados.addElement(new String("MG"));
			estados.addElement(new String("PA"));
			estados.addElement(new String("PB"));
			estados.addElement(new String("PR"));
			estados.addElement(new String("PE"));
			estados.addElement(new String("PI"));
			estados.addElement(new String("RJ"));
			estados.addElement(new String("RN"));
			estados.addElement(new String("RS"));
			estados.addElement(new String("RO"));
			estados.addElement(new String("RR"));
			estados.addElement(new String("SC"));
			estados.addElement(new String("SP"));
			estados.addElement(new String("SE"));
			estados.addElement(new String("TO"));
			estados.addElement(new String("DF"));
			cmbEstadoN = new JComboBox(estados);
			cmbEstadoN.setBounds(new Rectangle(266, 104, 41, 20));
			cmbEstadoN.setToolTipText("Estado de naturalidade do aluno");
		}
		return cmbEstadoN;
	}
	/**
	 * This method initializes txtDtEntrada	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtEntrada() {
		if (txtDtEntrada == null) {
			txtDtEntrada = new JFormattedTextField(setMascara("##/##/####"));
			txtDtEntrada.setBounds(new Rectangle(645, 370, 86, 22));
			txtDtEntrada.setToolTipText("Data de admissão na Instituição de ensino");
			txtDtEntrada.setEnabled(false);
		}
		return txtDtEntrada;
	}
	/**
	 * This method initializes txtOrgao	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtOrgao() {
		if (txtOrgao == null) {
			txtOrgao = new JTextField();
			txtOrgao.setBounds(new Rectangle(365, 44, 101, 20));
			txtOrgao.setBackground(Color.white);
		}
		return txtOrgao;
	}
	/**
	 * This method initializes abaResponsavel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaResponsavel() {
		if (abaResponsavel == null) {
			JLabel lblOrgaoResponsavel = new JLabel();
			lblOrgaoResponsavel.setBounds(new Rectangle(318, 44, 59, 20));
			lblOrgaoResponsavel.setText("Expedidor");
			lblEstadoNResponsavel = new JLabel();
			lblEstadoNResponsavel.setBounds(new Rectangle(232, 104, 41, 20));
			lblEstadoNResponsavel.setText("Estado");
			lblComplementoResponsavel = new JLabel();
			lblComplementoResponsavel.setBounds(new Rectangle(128, 194, 81, 20));
			lblComplementoResponsavel.setText("Complemento");
			lblRgResponsavel = new JLabel();
			lblRgResponsavel.setBounds(new Rectangle(168, 44, 25, 20));
			lblRgResponsavel.setText("R.G.");
			lblCpfResponsavel = new JLabel();
			lblCpfResponsavel.setBounds(new Rectangle(12, 44, 32, 20));
			lblCpfResponsavel.setText("C.P.F");
			lblEmailResponsavel = new JLabel();
			lblEmailResponsavel.setBounds(new Rectangle(242, 254, 37, 20));
			lblEmailResponsavel.setText("E-mail");
			lblTelResponsavel = new JLabel();
			lblTelResponsavel.setBounds(new Rectangle(105, 254, 51, 20));
			lblTelResponsavel.setText("Telefone");
			lblCepResponsavel = new JLabel();
			lblCepResponsavel.setBounds(new Rectangle(12, 254, 23, 20));
			lblCepResponsavel.setText("CEP");
			lblEstadoResponsavel = new JLabel();
			lblEstadoResponsavel.setBounds(new Rectangle(519, 224, 41, 20));
			lblEstadoResponsavel.setText("Estado");
			lblCidadeResponsavel = new JLabel();
			lblCidadeResponsavel.setBounds(new Rectangle(264, 224, 40, 20));
			lblCidadeResponsavel.setText("Cidade");
			lblBairroResponsavel = new JLabel();
			lblBairroResponsavel.setBounds(new Rectangle(12, 224, 38, 20));
			lblBairroResponsavel.setText("Bairro");
			lblNumResponsavel = new JLabel();
			lblNumResponsavel.setBounds(new Rectangle(12, 194, 48, 20));
			lblNumResponsavel.setText("Número");
			lblEnderecoResponsavel = new JLabel();
			lblEnderecoResponsavel.setBounds(new Rectangle(12, 164, 57, 20));
			lblEnderecoResponsavel.setText("Endereço");
			lblNascimentoResponsavel = new JLabel();
			lblNascimentoResponsavel.setBounds(new Rectangle(12, 74, 114, 20));
			lblNascimentoResponsavel.setText("Data de nascimento");
			lblNaturalResponsavel = new JLabel();
			lblNaturalResponsavel.setBounds(new Rectangle(12, 104, 74, 20));
			lblNaturalResponsavel.setText("Naturalidade");
			lblNacionalidadeResponsavel = new JLabel();
			lblNacionalidadeResponsavel.setBounds(new Rectangle(312, 104, 84, 20));
			lblNacionalidadeResponsavel.setText("Nacionalidade");
			lblSexoResponsavel = new JLabel();
			lblSexoResponsavel.setBounds(new Rectangle(468, 44, 31, 20));
			lblSexoResponsavel.setText("Sexo");
			lblNomeResponsavel = new JLabel();
			lblNomeResponsavel.setBounds(new Rectangle(12, 14, 38, 20));
			lblNomeResponsavel.setText("Nome");
			abaResponsavel = new JPanel();
			abaResponsavel.setLayout(null);
			abaResponsavel.add(lblNomeResponsavel, null);
			abaResponsavel.add(getTxtNomeResponsavel(), null);
			abaResponsavel.add(lblSexoResponsavel, null);
			abaResponsavel.add(getCmbsexoResponsavel(), null);
			abaResponsavel.add(lblNacionalidadeResponsavel, null);
			abaResponsavel.add(getTxtNacionalidadeResponsavel(), null);
			abaResponsavel.add(lblNaturalResponsavel, null);
			abaResponsavel.add(lblNascimentoResponsavel, null);
			abaResponsavel.add(getTxtNascimentoResponsavel(), null);
			abaResponsavel.add(getTxtNaturalResponsavel(), null);
			abaResponsavel.add(getTxtNascimentoResponsavel(), null);
			abaResponsavel.add(lblEnderecoResponsavel, null);
			abaResponsavel.add(lblNumResponsavel, null);
			abaResponsavel.add(lblBairroResponsavel, null);
			abaResponsavel.add(lblCidadeResponsavel, null);
			abaResponsavel.add(getCmbEstadoResponsavel(), null);
			abaResponsavel.add(lblEstadoResponsavel, null);
			abaResponsavel.add(lblCepResponsavel, null);
			abaResponsavel.add(lblTelResponsavel, null);
			abaResponsavel.add(lblEmailResponsavel, null);
			abaResponsavel.add(getTxtEnderecoResponsavel(), null);
			abaResponsavel.add(getTxtNumeroResponsavel(), null);
			abaResponsavel.add(getTxtBairroResponsavel(), null);
			abaResponsavel.add(getTxtCidadeResponsavel(), null);
			abaResponsavel.add(getTxtCepResponsavel(), null);
			abaResponsavel.add(getTxtTelResponsavel(), null);
			abaResponsavel.add(getTxtEmailResponsavel(), null);
			abaResponsavel.add(lblCpfResponsavel, null);
			abaResponsavel.add(lblRgResponsavel, null);
			abaResponsavel.add(getTxtCpfResponsavel(), null);
			abaResponsavel.add(getTxtRgResponsavel(), null);
			abaResponsavel.add(lblComplementoResponsavel, null);
			abaResponsavel.add(getTxtComplementoResponsavel(), null);
			abaResponsavel.add(lblEstadoNResponsavel, null);
			abaResponsavel.add(getCmbEstadoNResponsavel(), null);
			abaResponsavel.add(lblOrgaoResponsavel, null);
			abaResponsavel.add(getTxtOrgaoResponsavel(), null);
			abaResponsavel.add(getChkEndereco(), null);
		}
		return abaResponsavel;
	}
	/**
	 * This method initializes txtNome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNomeResponsavel() {
		if (txtNomeResponsavel == null) {
			txtNomeResponsavel = new JTextField();
			txtNomeResponsavel.setBounds(new Rectangle(42, 14, 320, 22));
			txtNomeResponsavel.setBackground(Color.white);
			txtNomeResponsavel.setToolTipText("Nome completo do aluno");
		}
		return txtNomeResponsavel;
	}
	/**
	 * This method initializes cmbsexo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbsexoResponsavel() {
		if (cmbsexoResponsavel == null) {
			sexos = new DefaultComboBoxModel();
			sexos.addElement("----------");
			sexos.addElement(new String("Feminino"));
			sexos.addElement(new String("Masculino"));
			cmbsexoResponsavel = new JComboBox(sexos);
			cmbsexoResponsavel.setBounds(new Rectangle(493, 44, 101, 22));
			cmbsexoResponsavel.setToolTipText("Sexo do aluno");
		}
		return cmbsexoResponsavel;
	}

	/**
	 * This method initializes txtNacionalidade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNacionalidadeResponsavel() {
		if (txtNacionalidadeResponsavel == null) {
			txtNacionalidadeResponsavel = new JTextField();
			txtNacionalidadeResponsavel.setBounds(new Rectangle(379, 104, 217, 22));
			txtNacionalidadeResponsavel.setBackground(Color.white);
			txtNacionalidadeResponsavel.setToolTipText("Nacionalidade do aluno");
		}
		return txtNacionalidadeResponsavel;
	}

	/**
	 * This method initializes txtNatural	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JTextField getTxtNaturalResponsavel() {
		if (txtNaturalResponsavel == null) {
			txtNaturalResponsavel = new JTextField();
			txtNaturalResponsavel.setBounds(new Rectangle(74, 104, 152, 22));
			txtNaturalResponsavel.setBackground(Color.white);
			txtNaturalResponsavel.setToolTipText("Naturalidade do aluno");
		}
		return txtNaturalResponsavel;
	}
	/**
	 * This method initializes txtNascimento	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtNascimentoResponsavel() {
		if (txtNascimentoResponsavel == null) {
			txtNascimentoResponsavel = new JFormattedTextField(setMascara("##/##/####"));
			txtNascimentoResponsavel.setBounds(new Rectangle(107, 74, 86, 22));
			txtNascimentoResponsavel.setBackground(Color.white);
			txtNascimentoResponsavel.setToolTipText("Data de nascimento do aluno");
		}
		return txtNascimentoResponsavel;
	}
	/**
	 * This method initializes cmbEstado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbEstadoResponsavel() {
		if (cmbEstadoResponsavel == null) {
			estados = new DefaultComboBoxModel();
			estados.addElement("--");
			estados.addElement(new String("AC"));
			estados.addElement(new String("AL"));
			estados.addElement(new String("AP"));
			estados.addElement(new String("AM"));
			estados.addElement(new String("BA"));
			estados.addElement(new String("CE"));
			estados.addElement(new String("ES"));
			estados.addElement(new String("GO"));
			estados.addElement(new String("MA"));
			estados.addElement(new String("MT"));
			estados.addElement(new String("MS"));
			estados.addElement(new String("MG"));
			estados.addElement(new String("PA"));
			estados.addElement(new String("PB"));
			estados.addElement(new String("PR"));
			estados.addElement(new String("PE"));
			estados.addElement(new String("PI"));
			estados.addElement(new String("RJ"));
			estados.addElement(new String("RN"));
			estados.addElement(new String("RS"));
			estados.addElement(new String("RO"));
			estados.addElement(new String("RR"));
			estados.addElement(new String("SC"));
			estados.addElement(new String("SP"));
			estados.addElement(new String("SE"));
			estados.addElement(new String("TO"));
			estados.addElement(new String("DF"));
			cmbEstadoResponsavel = new JComboBox(estados);
			cmbEstadoResponsavel.setBounds(new Rectangle(553, 224, 41, 20));
			cmbEstadoResponsavel.setToolTipText("Estado de residência do aluno");
		}
		return cmbEstadoResponsavel;
	}

	/**
	 * This method initializes txtEndereco	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEnderecoResponsavel() {
		if (txtEnderecoResponsavel == null) {
			txtEnderecoResponsavel = new JTextField();
			txtEnderecoResponsavel.setBounds(new Rectangle(57, 164, 539, 22));
			txtEnderecoResponsavel.setBackground(Color.white);
			txtEnderecoResponsavel.setToolTipText("Endereço de residência do aluno");
		}
		return txtEnderecoResponsavel;
	}

	/**
	 * This method initializes txtNumero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNumeroResponsavel() {
		if (txtNumeroResponsavel == null) {
			txtNumeroResponsavel = new JTextField();
			txtNumeroResponsavel.setBounds(new Rectangle(49, 194, 67, 22));
			txtNumeroResponsavel.setBackground(Color.white);
			txtNumeroResponsavel.setToolTipText("Número no endereço de residênscia do aluno");
		}
		return txtNumeroResponsavel;
	}

	/**
	 * This method initializes txtBairro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtBairroResponsavel() {
		if (txtBairroResponsavel == null) {
			txtBairroResponsavel = new JTextField();
			txtBairroResponsavel.setBounds(new Rectangle(40, 224, 215, 22));
			txtBairroResponsavel.setBackground(Color.white);
		}
		return txtBairroResponsavel;
	}

	/**
	 * This method initializes txtCidade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCidadeResponsavel() {
		if (txtCidadeResponsavel == null) {
			txtCidadeResponsavel = new JTextField();
			txtCidadeResponsavel.setBounds(new Rectangle(297, 224, 215, 22));
			txtCidadeResponsavel.setBackground(Color.white);
			txtCidadeResponsavel.setToolTipText("Cidade de residência do aluno");
		}
		return txtCidadeResponsavel;
	}

	/**
	 * This method initializes txtCep	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getTxtCepResponsavel() {
		if (txtCepResponsavel == null) {
			txtCepResponsavel = new JFormattedTextField(setMascara("##.###-###"));
			txtCepResponsavel.setBounds(new Rectangle(31, 253, 70, 22));			
			txtCepResponsavel.setBackground(Color.white);
			txtCepResponsavel.setToolTipText("C.E.P. da residência do aluno");
		}
		return txtCepResponsavel;
	}


	/**
	 * This method initializes txtTel	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtTelResponsavel() {
		if (txtTelResponsavel == null) {
			txtTelResponsavel = new JFormattedTextField(setMascara("(##)####-####"));
			txtTelResponsavel.setBounds(new Rectangle(147, 254, 89, 22));
			txtTelResponsavel.setBackground(Color.white);
			txtTelResponsavel.setToolTipText("Telefone de contato do aluno");
		}
		return txtTelResponsavel;
	}

	/**
	 * This method initializes txtEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEmailResponsavel() {
		if (txtEmailResponsavel == null) {
			txtEmailResponsavel = new JTextField();
			txtEmailResponsavel.setBounds(new Rectangle(271, 254, 325, 22));
			txtEmailResponsavel.setBackground(Color.white);
			txtEmailResponsavel.setToolTipText("Email de contato do aluno");
		}
		return txtEmailResponsavel;
	}
	/**
	 * This method initializes txtCpf	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtCpfResponsavel() {
		if (txtCpfResponsavel == null) {
			txtCpfResponsavel = new JFormattedTextField(setMascara("###.###.###-##"));
			txtCpfResponsavel.setBounds(new Rectangle(39, 43, 119, 22));
			txtCpfResponsavel.setBackground(Color.white);
			txtCpfResponsavel.setToolTipText("C.P.F. do aluno");
		}
		return txtCpfResponsavel;
	}

	/**
	 * This method initializes txtRg	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtRgResponsavel() {
		if (txtRgResponsavel == null) {
			txtRgResponsavel = new JTextField();
			txtRgResponsavel.setBounds(new Rectangle(187, 44, 125, 22));
			txtRgResponsavel.setBackground(Color.white);
			txtRgResponsavel.setToolTipText("R.G. do aluno");
		}
		return txtRgResponsavel;
	}
	/**
	 * This method initializes txtComplemento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtComplementoResponsavel() {
		if (txtComplementoResponsavel == null) {
			txtComplementoResponsavel = new JTextField();
			txtComplementoResponsavel.setBounds(new Rectangle(193, 194, 148, 22));
			txtComplementoResponsavel.setBackground(Color.white);
			txtComplementoResponsavel.setToolTipText("Complemento no endereço de residência do aluno");
		}
		return txtComplementoResponsavel;
	}
	/**
	 * This method initializes cmbEstadoN	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbEstadoNResponsavel() {
		if (cmbEstadoNResponsavel == null) {
			estados = new DefaultComboBoxModel();
			estados.addElement("--");
			estados.addElement(new String("AC"));
			estados.addElement(new String("AL"));
			estados.addElement(new String("AP"));
			estados.addElement(new String("AM"));
			estados.addElement(new String("BA"));
			estados.addElement(new String("CE"));
			estados.addElement(new String("ES"));
			estados.addElement(new String("GO"));
			estados.addElement(new String("MA"));
			estados.addElement(new String("MT"));
			estados.addElement(new String("MS"));
			estados.addElement(new String("MG"));
			estados.addElement(new String("PA"));
			estados.addElement(new String("PB"));
			estados.addElement(new String("PR"));
			estados.addElement(new String("PE"));
			estados.addElement(new String("PI"));
			estados.addElement(new String("RJ"));
			estados.addElement(new String("RN"));
			estados.addElement(new String("RS"));
			estados.addElement(new String("RO"));
			estados.addElement(new String("RR"));
			estados.addElement(new String("SC"));
			estados.addElement(new String("SP"));
			estados.addElement(new String("SE"));
			estados.addElement(new String("TO"));
			estados.addElement(new String("DF"));
			cmbEstadoNResponsavel = new JComboBox(estados);
			cmbEstadoNResponsavel.setBounds(new Rectangle(266, 104, 41, 20));
			cmbEstadoNResponsavel.setToolTipText("Estado de naturalidade do aluno");
		}
		return cmbEstadoNResponsavel;
	}
	/**
	 * This method initializes txtOrgao	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtOrgaoResponsavel() {
		if (txtOrgaoResponsavel == null) {
			txtOrgaoResponsavel = new JTextField();
			txtOrgaoResponsavel.setBounds(new Rectangle(365, 44, 101, 20));
			txtOrgaoResponsavel.setBackground(Color.white);
		}
		return txtOrgaoResponsavel;
	}
	/**
	 * This method initializes chkEndereco	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkEndereco() {
		if (chkEndereco == null) {
			chkEndereco = new JCheckBox();
			chkEndereco.setBounds(new Rectangle(12, 134, 237, 21));
			chkEndereco.setText("Mesmo endereço informado do aluno");
			chkEndereco.setToolTipText("Assinalar se aluno residir junto com responsável");
			chkEndereco.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange()==ItemEvent.SELECTED){
						getTxtEnderecoResponsavel().setText(getTxtEndereco().getText());
						getTxtNumeroResponsavel().setText(getTxtNumero().getText());
						getTxtComplementoResponsavel().setText(getTxtComplemento().getText());
						getTxtBairroResponsavel().setText(getTxtBairro().getText());
						getTxtCidadeResponsavel().setText(getTxtCidade().getText());
						cmbEstadoResponsavel.setSelectedItem((String) cmbEstado.getSelectedItem());
						getTxtCepResponsavel().setText(getTxtCep().getText());
						getTxtTelResponsavel().setText(getTxtTel().getText());	
					}else{
						getTxtEnderecoResponsavel().setText("");
						getTxtNumeroResponsavel().setText("");
						getTxtComplementoResponsavel().setText("");
						getTxtBairroResponsavel().setText("");
						getTxtCidadeResponsavel().setText("");
						cmbEstadoResponsavel.setSelectedItem("--");
						getTxtCepResponsavel().setText("");
						getTxtTelResponsavel().setText("");
					}

				}
			});
		}
		return chkEndereco;
	}
	/**
	 * This method initializes cmbSkins	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getCmbSkins() {
		if (cmbSkins == null) {
			Vector skins = new Vector();
			Aluno consultar = new Aluno();
			skins.addAll(consultar.consultarCmbSkins());
			cmbSkins = new JComboBox(skins);
			cmbSkins.setBounds(new Rectangle(32, 134, 320, 20));
			cmbSkins.setToolTipText("Skin pessoal para SENA IV");
		}
		return cmbSkins;
	}
	/**
	 * This method initializes chkPerfil	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkPerfil() {
		if (chkPerfil == null) {
			chkPerfil = new JCheckBox();
			chkPerfil.setBounds(new Rectangle(12, 194, 89, 21));
			chkPerfil.setText("Exbir perfil");
			chkPerfil.setToolTipText("Exbir perfil do aluno no SENA IV");
			chkPerfil.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange()==ItemEvent.SELECTED){
						perfil = true;//Sim
					}else{
						perfil = false;//Não
					}
				}
			});	
		}
		return chkPerfil;
	}
	/**
	 * This method initializes chkCurriculo	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkCurriculo() {
		if (chkCurriculo == null) {
			chkCurriculo = new JCheckBox();
			chkCurriculo.setBounds(new Rectangle(105, 194, 112, 21));
			chkCurriculo.setText("Exibir currículo");
			chkCurriculo.setToolTipText("Exibir currículo do aluno no SENA IV");
			chkCurriculo.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange()==ItemEvent.SELECTED){
						curriculo = true;//Sim
						getChkProsContras().setEnabled(true);
					}else{
						curriculo = false;//Não
						getChkProsContras().setEnabled(false);
					}
				}
			});	
		}
		return chkCurriculo;
	}
	/**
	 * This method initializes btnConfirmarSenaQuatro	
	 * 	
	 * @return javax.swing.JButton	Aluno senha = new Aluno();
						senha.novaSenha(getTxtCd().getText(), getTxtNome().getText());
	 */
	private JButton getBtnConfirmarSenaQuatro() {
		if (btnConfirmarSenaQuatro == null) {
			btnConfirmarSenaQuatro = new JButton();
			btnConfirmarSenaQuatro.setBounds(new Rectangle(12, 234, 91, 21));
			btnConfirmarSenaQuatro.setText("Confirmar");
			btnConfirmarSenaQuatro.setToolTipText("Confirmar cadastro no SENA IV");
			btnConfirmarSenaQuatro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aluno cadastrar = new Aluno();
					cadastrar.ajustaUsuarioSenaQuatro(getTxtLogin().getText(), getTxtSenha().getText(), (String) cmbSkins.getSelectedItem(), perfil, curriculo, prosContras);
					cadastrar.alterarSenaQuatro(getTxtCd().getText());
				}});
		}
		return btnConfirmarSenaQuatro;
	}
	/**
	 * This method initializes chkProsContras	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkProsContras() {
		if (chkProsContras == null) {
			chkProsContras = new JCheckBox();
			chkProsContras.setBounds(new Rectangle(218, 194, 145, 21));
			chkProsContras.setText("Exibir prós e contras");
			chkProsContras.setToolTipText("Exibir prós e contras  do aluno no SENA IV");
			chkProsContras.setEnabled(false);
			chkProsContras.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange()==ItemEvent.SELECTED){
						prosContras = true;//Sim
					}else{
						prosContras = false;//Não
					}
				}
			});	
		}
		return chkProsContras;
	}
	/**
	 * This method initializes abaClasse	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaClasse() {
		if (abaClasse == null) {
			lblClasses = new JLabel();
			lblClasses.setBounds(new Rectangle(12, 134, 119, 20));
			lblClasses.setText("Histórico de classes");
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-44, 114, 830, 16));
			lblDivisao2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao2.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblStatus = new JLabel();
			lblStatus.setBounds(new Rectangle(456, 14, 166, 20));
			lblStatus.setText("Status do aluno nesta classe");
			lblDtInicio = new JLabel();
			lblDtInicio.setBounds(new Rectangle(12, 104, 154, 20));
			lblDtInicio.setText("Data de início desta classe");
			lblCiclo = new JLabel();
			lblCiclo.setBounds(new Rectangle(12, 74, 29, 20));
			lblCiclo.setText("Ciclo");
			lblCurso = new JLabel();
			lblCurso.setBounds(new Rectangle(12, 44, 38, 20));
			lblCurso.setText("Curso");
			lblClasse = new JLabel();
			lblClasse.setBounds(new Rectangle(12, 14, 41, 20));
			lblClasse.setText("Classe");
			abaClasse = new JPanel();
			abaClasse.setLayout(null);
			abaClasse.add(lblClasse, null);
			abaClasse.add(getTxtNmClasse(), null);
			abaClasse.add(lblCurso, null);
			abaClasse.add(getTxtNmCurso(), null);
			abaClasse.add(lblCiclo, null);
			abaClasse.add(getTxtCiclo(), null);
			abaClasse.add(lblDtInicio, null);
			abaClasse.add(getTxtDtInicio(), null);
			abaClasse.add(lblStatus, null);
			abaClasse.add(getTxtStatusClasse(), null);
			abaClasse.add(lblDivisao2, null);
			abaClasse.add(lblClasses, null);
			abaClasse.add(getBtnAlterarClasse(), null);
		}
		return abaClasse;
	}
	/**
	 * This method initializes abaHistorico	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaHistorico() {
		if (abaHistorico == null) {
			lblAtividade = new JLabel();
			lblAtividade.setBounds(new Rectangle(502, 14, 61, 22));
			lblAtividade.setText("Descrição");
			lblContra = new JLabel();
			lblContra.setBounds(new Rectangle(257, 14, 107, 20));
			lblContra.setText("Histórico negativo");
			lblHistoricoPro = new JLabel();
			lblHistoricoPro.setBounds(new Rectangle(12, 14, 104, 20));
			lblHistoricoPro.setText("Histórico positivo");
			abaHistorico = new JPanel();
			abaHistorico.setLayout(null);
			abaHistorico.add(lblHistoricoPro, null);
			abaHistorico.add(getBtnAddPro(), null);
			abaHistorico.add(getBtnRemoverPro(), null);
			abaHistorico.add(lblContra, null);
			abaHistorico.add(getTxtDsAtividade(), null);
			abaHistorico.add(getBtnAddContra(), null);
			abaHistorico.add(getBtnRemoverContra(), null);
			abaHistorico.add(getTxtDtAtividade(), null);
			abaHistorico.add(lblAtividade, null);		
		}
		return abaHistorico;
	}
	/**
	 * This method initializes abaDocumentos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaDocumentos() {
		if (abaDocumentos == null) {
			lblSolicitado = new JLabel();
			lblSolicitado.setBounds(new Rectangle(502, 14, 140, 20));
			lblSolicitado.setText("Documentos solicitados");
			lblPendentes = new JLabel();
			lblPendentes.setBounds(new Rectangle(257, 14, 136, 20));
			lblPendentes.setText("Documentos pendentes");
			lblApresentados = new JLabel();
			lblApresentados.setBounds(new Rectangle(12, 14, 156, 20));
			lblApresentados.setText("Documentos apresentados");
			abaDocumentos = new JPanel();
			abaDocumentos.setLayout(null);
			abaDocumentos.add(lblApresentados, null);
			abaDocumentos.add(lblPendentes, null);
			abaDocumentos.add(lblSolicitado, null);
			abaDocumentos.add(getTxtDsDocApresentado(), null);
			abaDocumentos.add(getTxtDsDocPendente(), null);
			abaDocumentos.add(getTxtDsDocSolicitado(), null);
			abaDocumentos.add(getBtnAddDocApresentado(), null);
			abaDocumentos.add(getBtnRemoverDocApresentado(), null);
			abaDocumentos.add(getBtnAddDocPendente(), null);
			abaDocumentos.add(getBtnRemoverDocPendente(), null);
			abaDocumentos.add(getBtnAvaliarDocSolicitado(), null);
			abaDocumentos.add(getBtnRemoverDocSolicitado(), null);
		}
		return abaDocumentos;
	}
	/**
	 * This method initializes btnAddPro	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddPro() {
		if (btnAddPro == null) {
			btnAddPro = new JButton();
			btnAddPro.setBounds(new Rectangle(12, 364, 91, 21));
			btnAddPro.setText("Adicionar");
			btnAddPro.setToolTipText("Adicionar atividade positiva do aluno");
			btnAddPro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((dialogoPro == null) || (dialogoPro.isClosed())) {
						dialogoPro = new DialogoHistoricoPro(getTxtCd().getText(), getTxtNome().getText());
						desk.add(dialogoPro, new Integer(3));
						dialogoPro.show();						
					}
				}
			});
		}
		return btnAddPro;
	}

	/**
	 * This method initializes btnRemoverPro	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverPro() {
		if (btnRemoverPro == null) {
			btnRemoverPro = new JButton();
			btnRemoverPro.setBounds(new Rectangle(112, 364, 91, 21));
			btnRemoverPro.setText("Remover");
			btnRemoverPro.setToolTipText("Remover atividade positiva selecionada do aluno");
			btnRemoverPro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionadoPro().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione uma atividade positiva pra ser excluído seus dados!","Seleção",1);
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir esta atividade positiva?\n\n"+getSelecionadoPro()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							Aluno remover = new Aluno();
							remover.removerHistoricoPro(getTxtCd().getText(), getSelecionadoPro());
							abaHistorico.remove(jspTbHistoricoPro);
							consultarPro(getTxtCd().getText());
							getTxtDtAtividade().setText("");
							getTxtDsAtividade().setText("");
							btnRemoverPro.setEnabled(false);
						}
					}
					if(tabelaHistoricoPro.getRowCount()<=0){
						btnRemoverPro.setEnabled(false);
					}
				}
			});
		}
		return btnRemoverPro;
	}
	/**
	 * This method initializes txtDsAtividade	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsAtividade() {
		if (txtDsAtividade == null) {
			txtDsAtividade = new JTextArea();
			txtDsAtividade.setBounds(new Rectangle(502, 38, 215, 322));
			txtDsAtividade.setToolTipText("Descrição da atividade");
		}
		return txtDsAtividade;
	}
	/**
	 * This method initializes btnAddContra	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddContra() {
		if (btnAddContra == null) {
			btnAddContra = new JButton();
			btnAddContra.setBounds(new Rectangle(257, 364, 91, 21));
			btnAddContra.setText("Adicionar");
			btnAddContra.setToolTipText("Adicionar atividade negativa do aluno");
			btnAddContra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((dialogoContra == null) || (dialogoContra.isClosed())) {
						dialogoContra = new DialogoHistoricoContra(getTxtCd().getText(), getTxtNome().getText());
						desk.add(dialogoContra, new Integer(3));
						dialogoContra.show();						
					}
				}
			});
		}
		return btnAddContra;
	}
	/**
	 * This method initializes btnRemoverContra	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverContra() {
		if (btnRemoverContra == null) {
			btnRemoverContra = new JButton();
			btnRemoverContra.setBounds(new Rectangle(357, 364, 91, 21));
			btnRemoverContra.setText("Remover");
			btnRemoverContra.setToolTipText("Remover atividade negativa selecionada do aluno");
			btnRemoverContra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getSelecionadoContra().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione uma atividade negativa pra ser excluído seus dados!","Seleção",1);
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir esta atividade negativa?\n\n"+getSelecionadoContra()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							Aluno remover = new Aluno();
							remover.removerHistoricoContra(getTxtCd().getText(), getSelecionadoContra());
							abaHistorico.remove(jspTbHistoricoContra);
							consultarContra(getTxtCd().getText());
							getTxtDtAtividade().setText("");
							getTxtDsAtividade().setText("");
							btnRemoverContra.setEnabled(false);
						}
					}
					if(tabelaHistoricoContra.getRowCount()<=0){
						btnRemoverContra.setEnabled(false);
					}
				}
			});
		}
		return btnRemoverContra;
	}
	/**
	 * This method initializes txtDtAtividade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getTxtDtAtividade() {
		if (txtDtAtividade == null) {
			txtDtAtividade = new JFormattedTextField(setMascara("##/##/####"));
			txtDtAtividade.setBounds(new Rectangle(648, 14, 70, 20));
			txtDtAtividade.setToolTipText("Data da atividade do aluno");
		}
		return txtDtAtividade;
	}
	/**
	 * This method initializes txtDsDocApresentado	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsDocApresentado() {
		if (txtDsDocApresentado == null) {
			txtDsDocApresentado = new JTextArea();
			txtDsDocApresentado.setBounds(new Rectangle(12, 264, 215, 95));
			txtDsDocApresentado.setToolTipText("Descrição do documento apresentado pelo aluno");
		}
		return txtDsDocApresentado;
	}
	/**
	 * This method initializes txtDsDocPendente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsDocPendente() {
		if (txtDsDocPendente == null) {
			txtDsDocPendente = new JTextArea();
			txtDsDocPendente.setBounds(new Rectangle(257, 264, 215, 95));
			txtDsDocPendente.setToolTipText("Descrição do documento pendente do aluno");
		}
		return txtDsDocPendente;
	}
	/**
	 * This method initializes txtDsDocSolicitado	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsDocSolicitado() {
		if (txtDsDocSolicitado == null) {
			txtDsDocSolicitado = new JTextArea();
			txtDsDocSolicitado.setBounds(new Rectangle(502, 264, 215, 95));
			txtDsDocSolicitado.setToolTipText("Descrição do documento solicitado pelo aluno");
		}
		return txtDsDocSolicitado;
	}
	/**
	 * This method initializes btnAddDocApresentado	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddDocApresentado() {
		if (btnAddDocApresentado == null) {
			btnAddDocApresentado = new JButton();
			btnAddDocApresentado.setBounds(new Rectangle(12, 364, 91, 21));
			btnAddDocApresentado.setText("Adicionar");
			btnAddDocApresentado.setToolTipText("Adicionar documento apresentado do aluno");
			btnAddDocApresentado.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if ((dialogoDocApresentado == null) || (dialogoDocApresentado.isClosed())) {
						dialogoDocApresentado = new DialogoDocumentoApresentado(getTxtCd().getText(), getTxtNome().getText());
						desk.add(dialogoDocApresentado, new Integer(3));
						dialogoDocApresentado.show();						
					}
				}
			});
		}
		return btnAddDocApresentado;
	}
	/**
	 * This method initializes btnRemoverDocApresentado	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverDocApresentado() {
		if (btnRemoverDocApresentado == null) {
			btnRemoverDocApresentado = new JButton();
			btnRemoverDocApresentado.setBounds(new Rectangle(112, 364, 91, 21));
			btnRemoverDocApresentado.setText("Remover");
			btnRemoverDocApresentado.setToolTipText("Remover documento apresentado selecionado do aluno");
			btnRemoverDocApresentado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
		return btnRemoverDocApresentado;
	}
	/**
	 * This method initializes btnAddDocPendente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddDocPendente() {
		if (btnAddDocPendente == null) {
			btnAddDocPendente = new JButton();
			btnAddDocPendente.setBounds(new Rectangle(257, 364, 91, 21));
			btnAddDocPendente.setText("Adicionar");
			btnAddDocPendente.setToolTipText("Adicionar documento pendente do aluno");
			btnAddDocPendente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if ((dialogoDocPendente == null) || (dialogoDocPendente.isClosed())) {
						dialogoDocPendente = new DialogoDocumentoPendente(getTxtCd().getText(), getTxtNome().getText());
						desk.add(dialogoDocPendente, new Integer(3));
						dialogoDocPendente.show();						
					}
				}
			});
		}
		return btnAddDocPendente;
	}
	/**
	 * This method initializes btnRemoverDocPendente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverDocPendente() {
		if (btnRemoverDocPendente == null) {
			btnRemoverDocPendente = new JButton();
			btnRemoverDocPendente.setBounds(new Rectangle(357, 364, 91, 21));
			btnRemoverDocPendente.setText("Remover");
			btnRemoverDocPendente.setToolTipText("Remover documento pendente selecionado do aluno");
			btnRemoverDocPendente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
		return btnRemoverDocPendente;
	}
	/**
	 * This method initializes btnAvaliarDocSolicitado	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAvaliarDocSolicitado() {
		if (btnAvaliarDocSolicitado == null) {
			btnAvaliarDocSolicitado = new JButton();
			btnAvaliarDocSolicitado.setBounds(new Rectangle(502, 364, 91, 21));
			btnAvaliarDocSolicitado.setText("Avaliar");
			btnAvaliarDocSolicitado.setToolTipText("Avaliar solicitação de documento selecionado do aluno");
			btnAvaliarDocSolicitado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String docSol = "";
					if ((dialogoDocSolicitado == null) || (dialogoDocSolicitado.isClosed())) {
						docSol = ""+(Object) jtbDocSolicitados.getValueAt(jtbDocSolicitados.getSelectedRow(), 0);
						dialogoDocSolicitado = new DialogoDocumentoSolicitado(getTxtCd().getText(), getTxtNome().getText(), docSol);
						desk.add(dialogoDocSolicitado, new Integer(3));
						dialogoDocSolicitado.show();						
					}
				}
			});
		}
		return btnAvaliarDocSolicitado;
	}
	/**
	 * This method initializes btnRemoverDocSolicitado	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverDocSolicitado() {
		if (btnRemoverDocSolicitado == null) {
			btnRemoverDocSolicitado = new JButton();
			btnRemoverDocSolicitado.setBounds(new Rectangle(602, 364, 91, 21));
			btnRemoverDocSolicitado.setText("Remover");
			btnRemoverDocSolicitado.setToolTipText("Remover documento solicitado selecionado do aluno");
			btnRemoverDocSolicitado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
		return btnRemoverDocSolicitado;
	}
	/**
	 * This method initializes abaCanal	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaCanal() {
		if (abaCanal == null) {
			lblDtMsg = new JLabel();
			lblDtMsg.setBounds(new Rectangle(378, 154, 30, 20));
			lblDtMsg.setText("Data");
			lblMensagem = new JLabel();
			lblMensagem.setBounds(new Rectangle(12, 184, 65, 20));
			lblMensagem.setText("Mensagem");
			lblAssunto = new JLabel();
			lblAssunto.setBounds(new Rectangle(12, 154, 48, 20));
			lblAssunto.setText("Assunto");
			lblCanalNome = new JLabel();
			lblCanalNome.setBounds(new Rectangle(12, 124, 38, 20));
			lblCanalNome.setText("Nome");
			lblMsgs = new JLabel();
			lblMsgs.setBounds(new Rectangle(12, 14, 69, 20));
			lblMsgs.setText("Mensagens");
			abaCanal = new JPanel();
			abaCanal.setLayout(null);
			abaCanal.add(lblMsgs, null);
			abaCanal.add(getCmbMsgs(), null);
			abaCanal.add(getBtnResponder(), null);
			abaCanal.add(getBtnNovaMsg(), null);
			abaCanal.add(getBtnRemoverMsg(), null);
			abaCanal.add(lblCanalNome, null);
			abaCanal.add(getTxtCanalNome(), null);
			abaCanal.add(lblAssunto, null);
			abaCanal.add(getTxtCanalAssunto(), null);
			abaCanal.add(lblMensagem, null);
			abaCanal.add(getTxtCanalMsg(), null);
			abaCanal.add(getBtnEnviarMsg(), null);
			abaCanal.add(lblDtMsg, null);
			abaCanal.add(getTxtDtMsg(), null);
		}
		return abaCanal;
	}
	/**
	 * This method initializes cmbMsgs	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getCmbMsgs() {
		Vector<String> msgs = new Vector<String>();
		Aluno consultar = new Aluno();
		msgs.addAll(consultar.consultarCmbMsgs(getTxtCd().getText()));
		cmbMsgs = new JComboBox(msgs);
		cmbMsgs.setBounds(new Rectangle(12, 34, 355, 22));
		cmbMsgs.setToolTipText("Interações com o aluno");
		cmbMsgs.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		return cmbMsgs;
	}	
	/**
	 * This method initializes btnResponder	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnResponder() {
		if (btnResponder == null) {
			btnResponder = new JButton();
			btnResponder.setBounds(new Rectangle(12, 74, 111, 22));
			btnResponder.setText("Responder");
			btnResponder.setToolTipText("Responder mensagem do aluno selecionada");
		}
		return btnResponder;
	}
	/**
	 * This method initializes btnNovaMsg	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovaMsg() {
		if (btnNovaMsg == null) {
			btnNovaMsg = new JButton();
			btnNovaMsg.setBounds(new Rectangle(252, 75, 111, 21));
			btnNovaMsg.setText("Nova");
			btnNovaMsg.setToolTipText("Nova interação com o aluno");
		}
		return btnNovaMsg;
	}
	/**
	 * This method initializes btnRemoverMsg	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverMsg() {
		if (btnRemoverMsg == null) {
			btnRemoverMsg = new JButton();
			btnRemoverMsg.setBounds(new Rectangle(132, 75, 111, 21));
			btnRemoverMsg.setText("Finalizar");
			btnRemoverMsg.setToolTipText("Finalizar interação selecionada com aluno");
		}
		return btnRemoverMsg;
	}
	/**
	 * This method initializes txtCanalNome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCanalNome() {
		if (txtCanalNome == null) {
			txtCanalNome = new JTextField();
			txtCanalNome.setBounds(new Rectangle(51, 124, 320, 22));
			txtCanalNome.setToolTipText("Nome do remetente");
		}
		return txtCanalNome;
	}
	/**
	 * This method initializes txtCanalAssunto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCanalAssunto() {
		if (txtCanalAssunto == null) {
			txtCanalAssunto = new JTextField();
			txtCanalAssunto.setBounds(new Rectangle(51, 154, 320, 22));
			txtCanalAssunto.setToolTipText("Assunto da mensagem selecionada");
		}
		return txtCanalAssunto;
	}
	/**
	 * This method initializes txtCanalMsg	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtCanalMsg() {
		if (txtCanalMsg == null) {
			txtCanalMsg = new JTextArea();
			txtCanalMsg.setBounds(new Rectangle(12, 204, 468, 166));
			txtCanalMsg.setToolTipText("Mensagem selecionada");
		}
		return txtCanalMsg;
	}
	/**
	 * This method initializes abaDesempenho	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaDesempenho() {
		if (abaDesempenho == null) {
			abaDesempenho = new JPanel();
			abaDesempenho.setLayout(new GridBagLayout());
		}
		return abaDesempenho;
	}
	/**
	 * This method initializes txtNmClasse	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmClasse() {
		if (txtNmClasse == null) {
			txtNmClasse = new JTextField();
			txtNmClasse.setBounds(new Rectangle(43, 14, 320, 22));
			txtNmClasse.setToolTipText("Nome da classe em que o aluno está matriculado atualmente");
		}
		return txtNmClasse;
	}
	/**
	 * This method initializes txtNmCurso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNmCurso() {
		if (txtNmCurso == null) {
			txtNmCurso = new JTextField();
			txtNmCurso.setBounds(new Rectangle(43, 44, 320, 22));
			txtNmCurso.setToolTipText("Nome do curso desta classe");
		}
		return txtNmCurso;
	}
	/**
	 * This method initializes txtCiclo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCiclo() {
		if (txtCiclo == null) {
			txtCiclo = new JTextField();
			txtCiclo.setBounds(new Rectangle(43, 74, 40, 22));
			txtCiclo.setToolTipText("Ciclo desta classe no curso");
		}
		return txtCiclo;
	}
	/**
	 * This method initializes txtDtInicio	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtInicio() {
		if (txtDtInicio == null) {
			txtDtInicio = new JFormattedTextField(setMascara("##/##/####"));
			txtDtInicio.setBounds(new Rectangle(140, 104, 86, 20));
		}
		return txtDtInicio;
	}
	/**
	 * This method initializes txtStatusClasse	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtStatusClasse() {
		if (txtStatusClasse == null) {
			txtStatusClasse = new JTextField();
			txtStatusClasse.setBounds(new Rectangle(595, 14, 122, 22));
			txtStatusClasse.setToolTipText("Status do aluno na classe selecionada");
			txtStatusClasse.setBackground(Color.white);
			txtStatusClasse.setEditable(false);			
		}
		return txtStatusClasse;
	}
	/**
	 * This method initializes btnAlterarClasse	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAlterarClasse() {
		if (btnAlterarClasse == null) {
			btnAlterarClasse = new JButton();
			btnAlterarClasse.setBounds(new Rectangle(625, 104, 91, 21));
			btnAlterarClasse.setText("Alterar");
			btnAlterarClasse.setToolTipText("Alterar classe");
			btnAlterarClasse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((dialogoAlterarClasse == null) || (dialogoAlterarClasse.isClosed())) {
						dialogoAlterarClasse = new DialogoAlterarClasse(getTxtNmCurso().getText());
						desk.add(dialogoAlterarClasse, new Integer(3));
						dialogoAlterarClasse.show();						
					}
				}
			});
		}
		return btnAlterarClasse;
	}
	/**
	 * This method initializes btnEnviarMsg	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEnviarMsg() {
		if (btnEnviarMsg == null) {
			btnEnviarMsg = new JButton();
			btnEnviarMsg.setBounds(new Rectangle(500, 348, 111, 21));
			btnEnviarMsg.setText("Enviar");
			btnEnviarMsg.setToolTipText("Enviar mensagem ao aluno");
		}
		return btnEnviarMsg;
	}
	/**
	 * This method initializes txtDtMsg	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDtMsg() {
		if (txtDtMsg == null) {
			txtDtMsg = new JTextField();
			txtDtMsg.setBounds(new Rectangle(403, 154, 80, 22));
		}
		return txtDtMsg;
	}
}