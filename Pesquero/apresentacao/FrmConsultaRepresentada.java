package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import negocios.CapturaTela;
import negocios.Representada;

public class FrmConsultaRepresentada extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2395675145943011543L;
	FrmCadastroRepresentada formCadastroRepresentada;
	FrmCadastroRepresentadaCompleto formCadastroRepresentadaCompleto;
	FrmNovoRepresentada formNovoRepresentada;
	FrmRelatorioRepresentadas formRelatorioRepresentadas;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblRepresentada = null;
	private JTextField txtRepresentada = null;
	private JLabel lblNmRepresentada = null;
	private JTextField txtNmRepresentada = null;

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnBuscar = null;
	private JButton btnRemover = null;

	JTable tbRepresentadas = null;
	JScrollPane jspRepresentadas = null;
	Box linhaDois = Box.createHorizontalBox();
	Box linhas = Box.createVerticalBox();

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	private String selecionado = "";

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
	public void setSelecionado(String selecao){
		this.selecionado = selecao;
	}
	//----Método para construir tabela de pesquisa
	public void construirTabela(){
		tbRepresentadas = new JTable(getTabela());
		jspRepresentadas = new JScrollPane( tbRepresentadas );
		jspRepresentadas.setPreferredSize(new Dimension(500, 400));
		linhaDois.add(jspRepresentadas);
		tbRepresentadas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbRepresentadas.getValueAt(tbRepresentadas.getSelectedRow(), 1));
			}
		});
	}
	Representada consultar = new Representada();
	//----Consultar camada de negócios - Classe Funcionário
	public void consultarRepresentadas(String cnpj, String nome){	
		consultar.consultarRepresentadas(cnpj, nome);
		this.tabela = consultar.getTabelaRepresentadas();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhuma empresa representada cadastrada com o critério de pesquisa solicitado.","Consulta",1);
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
	//---Classe responsável por formatar márcaras de entrada de campos específicos
	private MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try {  
			mask = new MaskFormatter(mascara);  
			mask.setPlaceholderCharacter(' ');  
		}  
		catch (java.text.ParseException exc) { 

		}  
		return mask;  
	}
	private String login;
	
	public String getLogin(){
		return login;
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmConsultaRepresentada(JDesktopPane desktop, int permissao, String login) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.login = login;
		consultarRepresentadas("", "");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de empresas representadas");
		this.setResizable(false);

		lblRepresentada = new JLabel("CNPJ");
		txtRepresentada = new JFormattedTextField(setMascara("##.###.###/####-##"));
		lblNmRepresentada = new JLabel("Nome");
		txtNmRepresentada = new JTextField("",45);
		btnBuscar = new JButton("Buscar");

		lblDivisao = new JLabel("_________________________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAlterar = new JButton("Abrir");
		btnAlterar.setToolTipText("Exibir item selecionado");
		btnConfirmar = new JButton("Novo");
		btnConfirmar.setToolTipText("Iniciar novo cadastro");
		btnRemover = new JButton("Remover");
		btnRemover.setToolTipText("Remover item selecionado");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblRepresentada);
		linhaUm.add(txtRepresentada);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblNmRepresentada);
		linhaUm.add(txtNmRepresentada);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(btnBuscar);		
		linhaDois.add(jspRepresentadas);

		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnRemover);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAlterar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);

		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//--Ações
		tbRepresentadas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbRepresentadas.getValueAt(tbRepresentadas.getSelectedRow(), 1));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				formNovoRepresentada = new FrmNovoRepresentada(desk, getPermissao());
				CapturaTela tela = new CapturaTela();
				formNovoRepresentada.setLocation((tela.getLarguraTela()/2)-(400/2), (tela.getAlturaTela()/2)-(100/2));
				formNovoRepresentada.setVisible(true);
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jspRepresentadas.remove(tbRepresentadas);
				linhaDois.remove(jspRepresentadas);
				jContentPane.remove(linhaDois);
				consultarRepresentadas(txtRepresentada.getText().trim(), txtNmRepresentada.getText().trim());
				linhaDois.doLayout();
				tbRepresentadas.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						setSelecionado((String) tbRepresentadas.getValueAt(tbRepresentadas.getSelectedRow(), 1));
					}
				});
				JOptionPane.showMessageDialog(null, "Encontrado(s) "+consultar.registros()+" registro(s)","Consulta",1);
			}});
		btnAlterar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				formCadastroRepresentada = new FrmCadastroRepresentada(desk, getPermissao(), 0, "", "");
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroRepresentada")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroRepresentadaCompleto")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null,"Selecione uma empresa representada para poder visualizar seus dados","Notificação", 1);
					}else{
						String tipo = (String) tbRepresentadas.getValueAt(tbRepresentadas.getSelectedRow(), 2);
						if(tipo.equals("Simplificado")){
							formCadastroRepresentada = new FrmCadastroRepresentada(desk, getPermissao(), 1, getSelecionado(), tipo);
							desk.add(formCadastroRepresentada, new Integer(2));
							formCadastroRepresentada.setLocation(100, 100);
							formCadastroRepresentada.show();	
						}else if(tipo.equals("Completo")){
							formCadastroRepresentadaCompleto = new FrmCadastroRepresentadaCompleto(desk, getPermissao(), 1, getSelecionado(), tipo);
							desk.add(formCadastroRepresentadaCompleto, new Integer(2));
							formCadastroRepresentadaCompleto.setLocation(100, 100);
							formCadastroRepresentadaCompleto.show();
						}	
					}	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de empresa representada","Notificação",1);
				}
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione uma empresa representada para poder removê-la","Notificação",1);
				}else{
					setSelecionado((String) tbRepresentadas.getValueAt(tbRepresentadas.getSelectedRow(), 0));
					String nome = (String) tbRepresentadas.getValueAt(tbRepresentadas.getSelectedRow(), 1);
					Representada remover = new Representada();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover esta empresa representada?\n\nEmpresa: "+nome, "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado(), nome);
						jspRepresentadas.remove(tbRepresentadas);
						linhaDois.remove(jspRepresentadas);
						jContentPane.remove(linhaDois);
						consultarRepresentadas("", "");
						linhaDois.doLayout();
						tbRepresentadas.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								setSelecionado((String) tbRepresentadas.getValueAt(tbRepresentadas.getSelectedRow(), 1));
							}
						});
					}	
				}					
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Representada gerar = new Representada();
				gerar.gerarRelatorio(txtRepresentada.getText().trim(), txtNmRepresentada.getText().trim()).show();
			}});
	}
}