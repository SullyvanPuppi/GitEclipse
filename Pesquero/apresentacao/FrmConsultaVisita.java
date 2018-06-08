package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocios.Visita;

public class FrmConsultaVisita extends JInternalFrame {

	FrmRelatorioClientesVisitas formRelatoriosClienteVisitas;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7278813158109761254L;

	FrmCadastroVisita formCadastroVisita;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblCriterioUm = null;
	private JTextField txtTipo1 = null;
	private JLabel lblCriterioDois = null;
	private JTextField txtTipo2 = null;
	private JLabel lblDivisao = null;
	private JButton btnNovo = null;
	private JButton btnRemover = null;
	private JButton btnCancelar = null;
	private JButton btnAbrir = null;
	private JButton btnRelatorio = null;
	private JButton btnBuscar = null;

	JTable tbVisitas = null;
	JScrollPane jspVisitas = null;
	Box linhaDois = Box.createHorizontalBox();
	Box linhaQuatro = Box.createHorizontalBox();
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
		tbVisitas = new JTable(getTabela());
		jspVisitas = new JScrollPane( tbVisitas );
		jspVisitas.setPreferredSize(new Dimension(500, 400));
		linhaQuatro.add(jspVisitas);
		tbVisitas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbVisitas.getValueAt(tbVisitas.getSelectedRow(), 0));
			}
		});
	}
	Visita consultar = new Visita();
	//----Consultar camada de negócios - Classe Funcionário
	public void consultarVisitas(String nmEmpresaVisita, String nmRepresentada, String nmVendedor){	
		consultar.consultarVisitas(nmEmpresaVisita, nmRepresentada, nmVendedor);
		this.tabela = consultar.getTabelaVisitas();
		if(tabela.getRowCount()<=0){
			JOptionPane.showMessageDialog(null, "Nenhuma visita cadastrada com o critério de pesquisa solicitado.","Consulta",1);
			Object linha[] = new Object[4];
			linha[0] = "";
			linha[1] = "";
			linha[2] = "";
			linha[3] = "";
			tabela.addRow(linha);
			construirTabela();			
		}else{
			construirTabela();			
		}
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmConsultaVisita(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		consultarVisitas("", "", "");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de visitas realizadas");
		this.setResizable(false);

		lblCriterioUm = new JLabel("Cliente");
		txtTipo1 = new JTextField();
		lblCriterioDois = new JLabel("Representada");
		txtTipo2 = new JTextField();
	
		btnBuscar = new JButton("Buscar");

		lblDivisao = new JLabel("_________________________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAbrir = new JButton("Abrir");
		btnAbrir.setToolTipText("Exibir item selecionado");
		btnNovo = new JButton("Novo");
		btnNovo.setToolTipText("Iniciar novo cadastro");
		btnRemover = new JButton("Remover");
		btnRemover.setToolTipText("Remover item selecionado");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();

		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblCriterioUm);
		linhaUm.add(txtTipo1);
		linhaDois.add(lblCriterioDois);
		linhaDois.add(txtTipo2);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(btnBuscar);
		linhaQuatro.add(jspVisitas);

		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnRemover);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAbrir);		
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnNovo);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);
		linhas.add(Box.createVerticalStrut(5));

		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//--Ações
		tbVisitas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbVisitas.getValueAt(tbVisitas.getSelectedRow(), 0));
			}
		});
		btnNovo.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {					
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroVisita")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					formCadastroVisita = new FrmCadastroVisita(desk, getPermissao(), 0, "", "");
					desk.add(formCadastroVisita, new Integer(2));
					formCadastroVisita.setLocation(50, 50);
					formCadastroVisita.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche a janela aberta de cadastro de visita","Notificação",1);
				}
			}});
		btnAbrir.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroVisita")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					if(getSelecionado().equals("")){
						JOptionPane.showMessageDialog(null,"Selecione uma visita para poder visualizá-la","Notificação",1);
					}else{
						setSelecionado((String) tbVisitas.getValueAt(tbVisitas.getSelectedRow(), 0));
						String nmEmpresa = (String) tbVisitas.getValueAt(tbVisitas.getSelectedRow(), 1);
						formCadastroVisita = new FrmCadastroVisita(desk, getPermissao(), 1, getSelecionado(), nmEmpresa);
						desk.add(formCadastroVisita, new Integer(2));
						formCadastroVisita.setLocation(50, 50);
						formCadastroVisita.show();
					}		
				}else{
					JOptionPane.showMessageDialog(null,"Feche a janela aberta de cadastro de visita","Notificação",1);
				}	
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaVisitas();
				JOptionPane.showMessageDialog(null, "Encontrado(s) "+consultar.registros()+" registro(s)","Consulta",1);
			}});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione uma visita realizada para poder removê-la","Notificação",1);
				}else{
					setSelecionado((String) tbVisitas.getValueAt(tbVisitas.getSelectedRow(), 0));
					String nmEmpresa = (String) tbVisitas.getValueAt(tbVisitas.getSelectedRow(), 1 ); 
					Visita remover = new Visita();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover esta visita realizada?\n\nEmpresa: "+nmEmpresa, "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado(), nmEmpresa);
						atualizaVisitas();
					}	
				}					
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Visita gerar = new Visita();
				gerar.gerarRelatorio(txtTipo1.getText().trim(), txtTipo2.getText().trim()).show();
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
	private void atualizaVisitas(){
		jspVisitas.remove(tbVisitas);
		linhaQuatro.remove(jspVisitas);
		jContentPane.remove(linhaQuatro);
		consultarVisitas("","","");
		linhaQuatro.doLayout();
		tbVisitas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbVisitas.getValueAt(tbVisitas.getSelectedRow(), 0));
			}
		});
	}
}