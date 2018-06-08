package apresentacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import negocios.CapturaTela;
import negocios.Notificacoes;
import negocios.Venda;
import negocios.Visita;
import ajuda.CriaHelp;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class FrmPrincipal extends JFrame {  

	/**
	 * 
	 */
	private static final long serialVersionUID = -6693480164738187383L;

	ImageIcon icon = null;

	FrmNossaEmpresa formNossaEmpresa;
	FrmCadastroUsuario formCadUsuario;
	FrmAliquotasBeneficios formAliquotasBeneficios;

	FrmLogin formLogin;

	FrmNovoFuncionario formNovoFuncionario;
	FrmConsultaFuncionarios formConsultaFuncionarios;
	FrmRelatorioFuncionarios formRelatoriosFuncionario;

	FrmNovoRepresentada formNovoRepresentada;	
	FrmConsultaRepresentada formConsultaRepresentada;
	FrmRelatorioRepresentadas formRelatoriosRepresentada;

	FrmNovoCliente formNovoCliente;
	FrmConsultaCliente formConsultaCliente;
	FrmRelatorioClientes formRelatoriosCliente;

	FrmCadastroVenda formCadastroVenda;
	FrmConsultaVenda formConsultaVenda;
	FrmRelatoriosVendas formRelatoriosVendas;

	FrmCadastroVisita formCadastroVisita;
	FrmConsultaVisita formConsultaVisita;
	FrmRelatorioClientesVisitas formRelatoriosClienteVisitas;

	FrmAnotacoes formAnotacoes;

	FrmAgenda formAgenda;

	FrmNotificacoes formNotificacoes;

	FrmNovoBackup formBackup;

	FrmEvento formEvento;
	
	CriaHelp formAjuda;

	//--Ações admnistrativas
	//Atributo que armazena o critério de direitos no sistema
	private int permissao;
	/*
	 * Ajusta o atributo que representa
	 * seu grupo de usuário
	 */
	public void setPermissao(int x){
		this.permissao = x;	
	}
	/*Retorna um inteiro representando
	 * seu grupo de usuário
	 */
	public int getPermissao(){
		return permissao;	
	}
	//--Armazena login atual
	private String login;

	//--Retorna login atual
	public String getLogin(){
		return this.login;
	}	
	/*
	 * Customiza tela do formulário principal
	 * onde cada grupo tem acessos restritos
	 */
	public void validaPermissao(int x){
		if(x==1){//--Possui direitos de administrador

		}else if(x==2){//--Possui direitos de secretaria

		}else if(x==3){//--Possui direitos de convidado

		}
	}
	@SuppressWarnings("deprecation")
	public void ajustaCascata(){
		int x = deskPrincipal.countComponents();
		int o = 0;
		for(int i=0; i<x; i++){
			o +=25;
		}
		setPosX(o);
		setPosY(o);
	}
	private int posX;

	private int posY;

	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	//--barra de status
	JLabel barStatus = null;

	//--Desktop principal
	private JDesktopPane deskPrincipal;

	//--Barra de menus
	private JMenuBar mnuBarraMenus = new JMenuBar();

	//---------------------------Menus suspensos-------------------------------//
	private JMenu mnuSistema = new JMenu("Sistema");

	private JMenu mnuAdm = new JMenu("Administração");

	private JMenu mnuClientes = new JMenu("Clientes");

	private JMenu mnuVisitas = new JMenu("Visitas");

	private JMenu mnuFuncionarios = new JMenu("Funcionários");

	private JMenu mnuRepresentadas = new JMenu("Representadas");

	private JMenu mnuVendas = new JMenu("Vendas");

	private JMenu mnuUteis = new JMenu("Úteis");

	private JMenu mnuAjuda = new JMenu("Ajuda");

	private JMenu mnuSobre = new JMenu("Sobre");

	//------------------------Menus items-------------------------------------//
	//--Menu Sistema
	private JMenuItem mnuItemLogoff = new JMenuItem("Logoff");

	private JMenuItem mnuItemConf = new JMenuItem("Empresa responsável");

	private JMenuItem mnuItemAlterarSenha = new JMenuItem("Alterar senha");

	private JMenuItem mnuItemCadastroUsuario = new JMenuItem("Cadastro de usuário");

	private JMenuItem mnuItemBackup = new JMenuItem("Cópia de segurança");

	private JMenuItem mnuItemSair = new JMenuItem("Sair");

	//--Menu Adm
	private JMenuItem mnuItemAliquotas = new JMenuItem("Alíquotas e benefícios");

	private JMenuItem mnuItemFolha = new JMenuItem("Folha de pagamento");

	//--Menu Funcionarios
	private JMenuItem mnuItemFuncionarioCad = new JMenuItem("Cadastro");

	private JMenuItem mnuItemFuncionarioCon = new JMenuItem("Consulta");

	private JMenuItem mnuItemFuncionarioRel = new JMenuItem("Relatório");

	//--Menu Representadas
	private JMenuItem mnuItemRepresentadaCad = new JMenuItem("Cadastro");

	private JMenuItem mnuItemRepresentadaCon = new JMenuItem("Consulta");

	private JMenuItem mnuItemRepresentadaRel = new JMenuItem("Relatório");

	//--Menu Clientes
	private JMenuItem mnuItemClienteCad = new JMenuItem("Cadastro");

	private JMenuItem mnuItemClienteCon = new JMenuItem("Consulta");

	private JMenuItem mnuItemClienteRel = new JMenuItem("Relatório");

	//--Menu Clientes visitados
	private JMenuItem mnuItemVisitaCad = new JMenuItem("Cadastro");

	private JMenuItem mnuItemVisitaCon = new JMenuItem("Consulta");

	private JMenuItem mnuItemVisitaRel = new JMenuItem("Relatório");

	//--Menu Vendas
	private JMenuItem mnuItemVendaCad = new JMenuItem("Cadastro");

	private JMenuItem mnuItemVendaCon = new JMenuItem("Consulta");

	private JMenuItem mnuItemVendaRel = new JMenuItem("Relatório");

	//--Menu Uteis
	private JMenuItem mnuItemUteisFormVenda = new JMenuItem("Formulário de venda");

	private JMenuItem mnuItemUteisFormVisita = new JMenuItem("Formulário de visita");

	private JMenuItem mnuItemUteisAgenda = new JMenuItem("Agenda de contatos");

	private JMenuItem mnuItemUteisAnotacoes = new JMenuItem("Recados");

	private JMenuItem mnuItemUteisNotificacoes = new JMenuItem("Notificações");

	//--Menu Ajuda
	private JMenuItem mnuItemAjuda = new JMenuItem("SINTEG Help");

	//--Menu sobre
	private JMenuItem mnuItemSobre = new JMenuItem("Desenvolvedor");

	public FrmPrincipal(int permissao, String login) {  
		this.permissao = permissao;
		this.login = login;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setMinimumSize(new Dimension(700, 500));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(new Dimension(700, 500));
		initialize();	
		eventosHoje();	

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(sair()==0){
					System.exit(0);
				}
			}
		});		
	} 


	@SuppressWarnings("serial")
	private void initialize() {	
		horasData();
		//Timer timer = new Timer(1000, tarefa);
		//timer.start();
		icon = new ImageIcon("apresentacao/fundoB.jpg");
		deskPrincipal = new JDesktopPane(){

			protected void paintComponent(Graphics g){
				//  Dispaly image at at full size
				g.drawImage(icon.getImage(), 0, 0, null);

				//  Scale image to size of component
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);

				//  Fix the image position in the scroll pane
				//Point p = scrollPane.getViewport().getViewPosition();
				//g.drawImage(icon.getImage(), p.x, p.y, null);

				super.paintComponent(g);
			}
		};
		this.setJMenuBar(mnuBarraMenus);
		this.setTitle("][ SINTEG Pesquero Representações ][ Sistema gerenciador de vendas ][");
		try{
			
		}catch(Exception e){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icone.gif")));	
		}		
		deskPrincipal.setDesktopManager(new LimitadorTela());
		//deskPrincipal = new JDesktopPane();
		deskPrincipal.setOpaque( false );
		ImageIcon image1 = new ImageIcon("icones/logoff.gif");  
		JButton btnImgLogoff = new JButton(image1);  
		btnImgLogoff.setToolTipText("Efetuar logoff");
		//ImageIcon image2 = new ImageIcon("icones/folhapagamento.gif");  
		//JButton btnImgFolha = new JButton(image2);
		//btnImgFolha.setToolTipText("Folha de pagamento");
		ImageIcon image3 = new ImageIcon("icones/funcionarios.gif");  
		JButton btnImgFuncionarios = new JButton(image3);
		btnImgFuncionarios.setToolTipText("Funcionários");
		ImageIcon image4 = new ImageIcon("icones/representadas.gif");  
		JButton btnImgRepresentadas = new JButton(image4);
		btnImgRepresentadas.setToolTipText("Representadas");
		ImageIcon image5 = new ImageIcon("icones/clientes.gif");  
		JButton btnImgClientes = new JButton(image5);
		btnImgClientes.setToolTipText("Clientes");
		ImageIcon image6 = new ImageIcon("icones/visitas.gif");  
		JButton btnImgVisitas = new JButton(image6);
		btnImgVisitas.setToolTipText("Visitas realizadas");
		ImageIcon image7 = new ImageIcon("icones/vendas.gif");  
		JButton btnImgVendas = new JButton(image7);
		btnImgVendas.setToolTipText("Vendas");
		ImageIcon image8 = new ImageIcon("icones/recados.gif");  
		JButton btnImgRecados = new JButton(image8);
		btnImgRecados.setToolTipText("Recados");
		ImageIcon image9 = new ImageIcon("icones/agenda.gif");  
		JButton btnImgAgenda = new JButton(image9);
		btnImgAgenda.setToolTipText("Agenda");
		ImageIcon image10 = new ImageIcon("icones/ajuda.gif");  
		JButton btnImgAjuda = new JButton(image10);
		btnImgAjuda.setToolTipText("Ajuda");
		ImageIcon image11 = new ImageIcon("icones/notificacoes.gif");  
		JButton btnImgNotificacoes = new JButton(image11);
		btnImgNotificacoes.setToolTipText("Notificações");
		JToolBar bar = new JToolBar();  

		bar.add(btnImgLogoff);  
		//bar.add(btnImgFolha);  
		bar.add(btnImgFuncionarios);  
		bar.add(btnImgRepresentadas);
		bar.add(btnImgClientes);
		bar.add(btnImgVisitas);
		bar.add(btnImgVendas);
		bar.add(btnImgRecados);
		bar.add(btnImgAgenda);
		bar.add(btnImgNotificacoes);
		bar.add(btnImgAjuda);

		//--Menus suspensos
		mnuBarraMenus.add(mnuSistema);
		//mnuBarraMenus.add(mnuAdm);
		mnuBarraMenus.add(mnuFuncionarios);
		mnuBarraMenus.add(mnuRepresentadas);
		mnuBarraMenus.add(mnuClientes);
		mnuBarraMenus.add(mnuVendas);
		mnuBarraMenus.add(mnuUteis);
		mnuBarraMenus.add(mnuAjuda);
		mnuBarraMenus.add(mnuSobre);
		//--Menu itens
		//--Menu sistema
		mnuSistema.add(mnuItemLogoff);
		mnuSistema.add(mnuItemConf);
		mnuSistema.add(mnuItemBackup);
		mnuSistema.add(mnuItemCadastroUsuario);		
		mnuSistema.add(mnuItemAlterarSenha);
		mnuSistema.addSeparator();
		mnuSistema.add(mnuItemSair);
		//--Menu administrativo
		//mnuAdm.add(mnuItemAliquotas);
		//mnuAdm.add(mnuItemFolha);
		//--Menu funcionários
		mnuFuncionarios.add(mnuItemFuncionarioCad);
		mnuFuncionarios.add(mnuItemFuncionarioCon);
		mnuFuncionarios.add(mnuItemFuncionarioRel);
		//--Menu representadas
		mnuRepresentadas.add(mnuItemRepresentadaCad);
		mnuRepresentadas.add(mnuItemRepresentadaCon);
		mnuRepresentadas.add(mnuItemRepresentadaRel);
		//--Menu clientes
		mnuClientes.add(mnuItemClienteCad);
		mnuClientes.add(mnuItemClienteCon);
		mnuClientes.add(mnuItemClienteRel);
		mnuClientes.add(mnuVisitas);
		//--Menu visitas
		mnuVisitas.add(mnuItemVisitaCad);
		mnuVisitas.add(mnuItemVisitaCon);
		mnuVisitas.add(mnuItemVisitaRel);
		//--Menu vendas
		mnuVendas.add(mnuItemVendaCad);
		mnuVendas.add(mnuItemVendaCon);
		mnuVendas.add(mnuItemVendaRel);
		//--Menu úteis
		mnuUteis.add(mnuItemUteisAnotacoes);
		mnuUteis.add(mnuItemUteisAgenda);
		mnuUteis.add(mnuItemUteisNotificacoes);
		mnuUteis.add(mnuItemUteisFormVenda);
		mnuUteis.add(mnuItemUteisFormVisita);		
		//--Menu ajuda
		mnuAjuda.add(mnuItemAjuda);
		//--Menu sobre
		mnuSobre.add(mnuItemSobre);
		//--Demais itens

		BorderLayout bord = new BorderLayout();  
		/*pane.setLayout(bord);
		pane.add("North", bar);  
		pane.add("Center", deskPrincipal);*/

		JLabel barStatus = new JLabel(horas+"     |     "+data+"     |     Usuário logado atualmente:  "+getLogin()+"     ");
		barStatus.setPreferredSize(new Dimension(700, 20));
		barStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		

		getContentPane().setLayout(bord);
		getContentPane().add("North", bar);  
		getContentPane().add("Center", deskPrincipal);
		getContentPane().add("South", barStatus);

		//setContentPane(pane);  
		atualizaTema();

		//---Ações
		mnuItemAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formAjuda = new CriaHelp();
			}});
		btnImgAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formAjuda = new CriaHelp();
			}});	
		mnuItemBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formBackup = new FrmNovoBackup(deskPrincipal, getLogin());
				CapturaTela tela = new CapturaTela();
				formBackup.setLocation((tela.getLarguraTela()/2)-(200/2), (tela.getAlturaTela()/2)-(160/2));
				formBackup.setVisible(true);
			}});
		mnuItemUteisNotificacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formNotificacoes == null) || (formNotificacoes.isClosed())) {
					formNotificacoes = new FrmNotificacoes(deskPrincipal, getPermissao(), getLogin());
					deskPrincipal.add(formNotificacoes, new Integer(2));
					formNotificacoes.setLocation(getPosX(), getPosY());
					formNotificacoes.show();
				}
			}});
		btnImgNotificacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formNotificacoes == null) || (formNotificacoes.isClosed())) {
					formNotificacoes = new FrmNotificacoes(deskPrincipal, getPermissao(), getLogin());
					deskPrincipal.add(formNotificacoes, new Integer(2));
					formNotificacoes.setLocation(getPosX(), getPosY());
					formNotificacoes.show();
				}
			}});
		mnuItemUteisAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formAgenda == null) || (formAgenda.isClosed())) {
					formAgenda = new FrmAgenda(deskPrincipal, getPermissao());
					deskPrincipal.add(formAgenda, new Integer(2));
					formAgenda.setLocation(getPosX(), getPosY());
					formAgenda.show();
				}
			}});
		btnImgAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formAgenda == null) || (formAgenda.isClosed())) {
					formAgenda = new FrmAgenda(deskPrincipal, getPermissao());
					deskPrincipal.add(formAgenda, new Integer(2));
					formAgenda.setLocation(getPosX(), getPosY());
					formAgenda.show();
				}
			}});
		mnuItemUteisAnotacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formAnotacoes == null) || (formAnotacoes.isClosed())) {
					formAnotacoes = new FrmAnotacoes(deskPrincipal, getPermissao(), getLogin());
					deskPrincipal.add(formAnotacoes, new Integer(2));
					formAnotacoes.setLocation(getPosX(), getPosY());
					formAnotacoes.show();
				}
			}});
		btnImgRecados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formAnotacoes == null) || (formAnotacoes.isClosed())) {
					formAnotacoes = new FrmAnotacoes(deskPrincipal, getPermissao(), getLogin());
					deskPrincipal.add(formAnotacoes, new Integer(2));
					formAnotacoes.setLocation(getPosX(), getPosY());
					formAnotacoes.show();
				}
			}});
		mnuItemVisitaCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaVisita == null) || (formConsultaVisita.isClosed())) {
					formConsultaVisita = new FrmConsultaVisita(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaVisita, new Integer(2));
					formConsultaVisita.setLocation(getPosX(), getPosY());
					formConsultaVisita.show();
				}
			}});
		mnuItemVisitaRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formRelatoriosClienteVisitas = new FrmRelatorioClientesVisitas(deskPrincipal);
				CapturaTela tela = new CapturaTela();
				formRelatoriosClienteVisitas.setLocation((tela.getLarguraTela()/2)-(200/2), (tela.getAlturaTela()/2)-(160/2));
				formRelatoriosClienteVisitas.setVisible(true);
			}});
		btnImgClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaCliente == null) || (formConsultaCliente.isClosed())) {
					formConsultaCliente = new FrmConsultaCliente(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaCliente, new Integer(2));
					formConsultaCliente.setLocation(getPosX(), getPosY());
					formConsultaCliente.show();
				}
			}});
		mnuItemVisitaCad.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				int c = deskPrincipal.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+deskPrincipal.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroVisita")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					formCadastroVisita = new FrmCadastroVisita(deskPrincipal, getPermissao(), 0, "", "");
					deskPrincipal.add(formCadastroVisita, new Integer(2));
					formCadastroVisita.setLocation(getPosX(), getPosY());
					formCadastroVisita.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche a janela aberta de cadastro de visita","Notificação",2);
				}
			}});
		btnImgVisitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaVenda == null) || (formConsultaVenda.isClosed())) {
					formConsultaVisita = new FrmConsultaVisita(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaVisita, new Integer(2));
					formConsultaVisita.setLocation(getPosX(), getPosY());
					formConsultaVisita.show();
				}
			}});
		mnuItemVendaCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaVenda == null) || (formConsultaVenda.isClosed())) {
					formConsultaVenda = new FrmConsultaVenda(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaVenda, new Integer(2));
					formConsultaVenda.setLocation(getPosX(), getPosY());
					formConsultaVenda.show();
				}
			}});		
		mnuItemVendaCad.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				int c = deskPrincipal.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+deskPrincipal.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroVenda")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					formCadastroVenda = new FrmCadastroVenda(deskPrincipal, getPermissao(), 0,"");
					deskPrincipal.add(formCadastroVenda, new Integer(2));
					formCadastroVenda.setLocation(100, 100);
					formCadastroVenda.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de venda","Notificação",1);
				}
			}});
		mnuItemVendaRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formRelatoriosVendas = new FrmRelatoriosVendas(deskPrincipal, getLogin(), getPermissao());
				CapturaTela tela = new CapturaTela();
				formRelatoriosVendas.setLocation((tela.getLarguraTela()/2)-(200/2), (tela.getAlturaTela()/2)-(160/2));
				formRelatoriosVendas.setVisible(true);
			}});
		btnImgVendas.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				int c = deskPrincipal.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+deskPrincipal.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroVenda")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					formCadastroVenda = new FrmCadastroVenda(deskPrincipal, getPermissao(), 0,"");
					deskPrincipal.add(formCadastroVenda, new Integer(2));
					formCadastroVenda.setLocation(100, 100);
					formCadastroVenda.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de venda","Notificação",1);
				}
			}});
		mnuItemClienteCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formNovoCliente = new FrmNovoCliente(deskPrincipal, getPermissao());
				CapturaTela tela = new CapturaTela();
				formNovoCliente.setLocation((tela.getLarguraTela()/2)-(400/2), (tela.getAlturaTela()/2)-(100/2));
				formNovoCliente.setVisible(true);
			}});
		mnuItemClienteRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formRelatoriosCliente = new FrmRelatorioClientes(deskPrincipal);
				CapturaTela tela = new CapturaTela();
				formRelatoriosCliente.setLocation((tela.getLarguraTela()/2)-(200/2), (tela.getAlturaTela()/2)-(160/2));
				formRelatoriosCliente.setVisible(true);
			}});
		mnuItemClienteCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaCliente == null) || (formConsultaCliente.isClosed())) {
					formConsultaCliente = new FrmConsultaCliente(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaCliente, new Integer(2));
					formConsultaCliente.setLocation(getPosX(), getPosY());
					formConsultaCliente.show();
				}
			}});
		btnImgClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaCliente == null) || (formConsultaCliente.isClosed())) {
					formConsultaCliente = new FrmConsultaCliente(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaCliente, new Integer(2));
					formConsultaCliente.setLocation(getPosX(), getPosY());
					formConsultaCliente.show();
				}
			}});
		mnuItemFuncionarioCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaFuncionarios == null) || (formConsultaFuncionarios.isClosed())) {
					formConsultaFuncionarios = new FrmConsultaFuncionarios(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaFuncionarios, new Integer(2));
					formConsultaFuncionarios.setLocation(getPosX(), getPosY());
					formConsultaFuncionarios.show();
				}
			}});
		btnImgFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaFuncionarios == null) || (formConsultaFuncionarios.isClosed())) {
					formConsultaFuncionarios = new FrmConsultaFuncionarios(deskPrincipal, getPermissao());
					deskPrincipal.add(formConsultaFuncionarios, new Integer(2));
					formConsultaFuncionarios.setLocation(getPosX(), getPosY());
					formConsultaFuncionarios.show();
				}
			}});
		mnuItemFuncionarioRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formRelatoriosFuncionario = new FrmRelatorioFuncionarios(deskPrincipal);
				CapturaTela tela = new CapturaTela();
				formRelatoriosFuncionario.setLocation((tela.getLarguraTela()/2)-(200/2), (tela.getAlturaTela()/2)-(160/2));
				formRelatoriosFuncionario.setVisible(true);
			}});		
		mnuItemRepresentadaCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaRepresentada == null) || (formConsultaRepresentada.isClosed())) {
					formConsultaRepresentada = new FrmConsultaRepresentada(deskPrincipal, getPermissao(), getLogin());
					deskPrincipal.add(formConsultaRepresentada, new Integer(2));
					formConsultaRepresentada.setLocation(getPosX(), getPosY());
					formConsultaRepresentada.show();
				}
			}});
		btnImgRepresentadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formConsultaRepresentada == null) || (formConsultaRepresentada.isClosed())) {
					formConsultaRepresentada = new FrmConsultaRepresentada(deskPrincipal, getPermissao(), getLogin());
					deskPrincipal.add(formConsultaRepresentada, new Integer(2));
					formConsultaRepresentada.setLocation(getPosX(), getPosY());
					formConsultaRepresentada.show();
				}
			}});
		mnuItemRepresentadaRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formRelatoriosRepresentada = new FrmRelatorioRepresentadas(deskPrincipal, getLogin());
				CapturaTela tela = new CapturaTela();
				formRelatoriosRepresentada.setLocation((tela.getLarguraTela()/2)-(200/2), (tela.getAlturaTela()/2)-(160/2));
				formRelatoriosRepresentada.setVisible(true);
			}});
		mnuItemRepresentadaCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formNovoRepresentada = new FrmNovoRepresentada(deskPrincipal, getPermissao());
				CapturaTela tela = new CapturaTela();
				formNovoRepresentada.setLocation((tela.getLarguraTela()/2)-(400/2), (tela.getAlturaTela()/2)-(100/2));
				formNovoRepresentada.setVisible(true);
			}});
		mnuItemFuncionarioCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				formNovoFuncionario = new FrmNovoFuncionario(deskPrincipal, getPermissao());
				CapturaTela tela = new CapturaTela();
				formNovoFuncionario.setLocation((tela.getLarguraTela()/2)-(400/2), (tela.getAlturaTela()/2)-(100/2));
				formNovoFuncionario.setVisible(true);
			}});
		/*mnuItemFolha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formFechamentos == null) || (formFechamentos.isClosed())) {
					formFechamentos = new FrmFechamentos(deskPrincipal, getPermissao());
					deskPrincipal.add(formFechamentos, new Integer(2));
					formFechamentos.setLocation(getPosX(), getPosY());
					formFechamentos.show();
				}
			}});
		btnImgFolha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formFechamentos == null) || (formFechamentos.isClosed())) {
					formFechamentos = new FrmFechamentos(deskPrincipal, getPermissao());
					deskPrincipal.add(formFechamentos, new Integer(2));
					formFechamentos.setLocation(getPosX(), getPosY());
					formFechamentos.show();
				}
			}});
		mnuItemAliquotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formAliquotasBeneficios == null) || (formAliquotasBeneficios.isClosed())) {
					formAliquotasBeneficios = new FrmAliquotasBeneficios(deskPrincipal, getPermissao());
					deskPrincipal.add(formAliquotasBeneficios, new Integer(2));
					formAliquotasBeneficios.setLocation(getPosX(), getPosY());
					formAliquotasBeneficios.show();
				}
			}});*/			
		mnuItemConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formNossaEmpresa == null) || (formNossaEmpresa.isClosed())) {
					formNossaEmpresa = new FrmNossaEmpresa(deskPrincipal, getPermissao());
					deskPrincipal.add(formNossaEmpresa, new Integer(2));
					formNossaEmpresa.setLocation(getPosX(), getPosY());
					formNossaEmpresa.show();
				}
			}});
		mnuItemLogoff.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(new JFrame(),
						getLogin()+" deseja realmente realizar logoff no sistema?", "Confirmação",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					dispose();
					CapturaTela tela = new CapturaTela();
					FrmLogin formLogin = new FrmLogin(tela.getLarguraTela(),tela.getAlturaTela());
					formLogin.show();	
				}
			}});
		btnImgLogoff.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(new JFrame(),
						getLogin()+" deseja realmente realizar logoff no sistema?", "Confirmação",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					dispose();
					CapturaTela tela = new CapturaTela();
					FrmLogin formLogin = new FrmLogin(tela.getLarguraTela(),tela.getAlturaTela());
					formLogin.show();	
				}				
			}});
		mnuItemCadastroUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formCadUsuario == null) || (formCadUsuario.isClosed())) {
					formCadUsuario = new FrmCadastroUsuario(deskPrincipal, getPermissao(),0, getLogin());
					deskPrincipal.add(formCadUsuario, new Integer(2));
					formCadUsuario.setLocation(getPosX(), getPosY());
					formCadUsuario.show();
				}
			}});
		mnuItemAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				if ((formCadUsuario == null) || (formCadUsuario.isClosed())) {
					formCadUsuario = new FrmCadastroUsuario(deskPrincipal, getPermissao(),1, getLogin());
					deskPrincipal.add(formCadUsuario, new Integer(2));
					formCadUsuario.setLocation(getPosX(), getPosY());
					formCadUsuario.show();
				}
			}});
		mnuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sair()==0){
					System.exit(0);
				}
			}	
		});
		mnuItemSobre.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				FrmSobre sobre = new FrmSobre();
				sobre.showSplashAndExit();
				sobre.show();
			}	
		});
		mnuItemUteisFormVisita.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Visita gerar = new Visita();
				gerar.gerarRelatorioUteisVisita().show();;
			}	
		});
		mnuItemUteisFormVenda.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Venda gerar = new Venda();
				gerar.gerarRelatorioUteisVenda().show();;
			}	
		});		
	}
	String horas, data;
	String diaSemana[] = {"domingo", "segunda-feira",
			"terça-feira", "quarta-feira",
			"quinta-feira", "sexta-feira",
	"sábado"};

	String meses[] = {"janeiro", "fevereiro", "março",
			"abril", "maio", "junho", "julho",
			"agosto", "setembro", "outubro",
			"novembro", "dezembro"};
	private void horasData(){
		Calendar agora = Calendar.getInstance();
		int ho = agora.get(Calendar.HOUR_OF_DAY);
		int mi = agora.get(Calendar.MINUTE);
		int se = agora.get(Calendar.SECOND);

		int ds = agora.get(Calendar.DAY_OF_WEEK);
		int dia = agora.get(Calendar.DAY_OF_MONTH);
		int mes = agora.get(Calendar.MONTH);
		int ano = agora.get(Calendar.YEAR);

		horas = (formatar(ho % 12) + ":" + formatar(mi) + ":" + formatar(se) + "");

		data = (diaSemana[ds - 1] + ", " + formatar(dia) + " de " + meses[mes] + " de " + ano + "");
	}
	private String formatar(int num){
		DecimalFormat df = new DecimalFormat("00");
		return df.format(num);
	}
	ActionListener tarefa = new ActionListener(){
	     public void actionPerformed(ActionEvent e){
	       horasData();
	       barStatus.setText(horas+"     |     "+data+"     |     Usuário logado atualmente:  "+getLogin()+"     ");
	     }
	 };
	private int sair(){
		if (JOptionPane.showConfirmDialog(new JFrame(),getLogin()+" deseja realmente finalizar o sistema?", "Confirmação",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			return 0;
		}
		return 1;
	}
	private void atualizaTema(){
		try{
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			SwingUtilities.updateComponentTreeUI(rootPane);
			SwingUtilities.updateComponentTreeUI(mnuBarraMenus);
			SwingUtilities.updateComponentTreeUI(mnuSistema);
			SwingUtilities.updateComponentTreeUI(mnuAdm);
			SwingUtilities.updateComponentTreeUI(mnuFuncionarios);
			SwingUtilities.updateComponentTreeUI(mnuRepresentadas);
			SwingUtilities.updateComponentTreeUI(mnuClientes);
			SwingUtilities.updateComponentTreeUI(mnuVisitas);
			SwingUtilities.updateComponentTreeUI(mnuVendas);
			SwingUtilities.updateComponentTreeUI(mnuUteis);
			SwingUtilities.updateComponentTreeUI(mnuAjuda);
			SwingUtilities.updateComponentTreeUI(mnuSobre);
			SwingUtilities.updateComponentTreeUI(mnuItemLogoff);
			SwingUtilities.updateComponentTreeUI(mnuItemConf);
			SwingUtilities.updateComponentTreeUI(mnuItemAlterarSenha);
			SwingUtilities.updateComponentTreeUI(mnuItemCadastroUsuario);
			SwingUtilities.updateComponentTreeUI(mnuItemBackup);
			SwingUtilities.updateComponentTreeUI(mnuItemSair);
			SwingUtilities.updateComponentTreeUI(mnuItemAliquotas);
			SwingUtilities.updateComponentTreeUI(mnuItemFolha);
			SwingUtilities.updateComponentTreeUI(mnuItemFuncionarioCad);
			SwingUtilities.updateComponentTreeUI(mnuItemFuncionarioCon);
			SwingUtilities.updateComponentTreeUI(mnuItemFuncionarioRel);
			SwingUtilities.updateComponentTreeUI(mnuItemRepresentadaCad);
			SwingUtilities.updateComponentTreeUI(mnuItemRepresentadaCon);
			SwingUtilities.updateComponentTreeUI(mnuItemRepresentadaRel);
			SwingUtilities.updateComponentTreeUI(mnuItemClienteCad);
			SwingUtilities.updateComponentTreeUI(mnuItemClienteCon);
			SwingUtilities.updateComponentTreeUI(mnuItemClienteRel);
			SwingUtilities.updateComponentTreeUI(mnuItemVisitaCad);
			SwingUtilities.updateComponentTreeUI(mnuItemVisitaCon);
			SwingUtilities.updateComponentTreeUI(mnuItemVisitaRel);
			SwingUtilities.updateComponentTreeUI(mnuItemVendaCad);
			SwingUtilities.updateComponentTreeUI(mnuItemVendaCon);
			SwingUtilities.updateComponentTreeUI(mnuItemVendaRel);
			SwingUtilities.updateComponentTreeUI(mnuItemUteisAnotacoes);
			SwingUtilities.updateComponentTreeUI(mnuItemUteisAgenda);
			SwingUtilities.updateComponentTreeUI(mnuItemUteisFormVenda);
			SwingUtilities.updateComponentTreeUI(mnuItemUteisFormVisita);
			SwingUtilities.updateComponentTreeUI(mnuItemUteisNotificacoes);
			SwingUtilities.updateComponentTreeUI(mnuItemAjuda);
			SwingUtilities.updateComponentTreeUI(mnuItemSobre);
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null,"Erro interno do sistema.","Erro",2);
		}
	}
	public void eventosHoje(){
		Notificacoes consultar = new Notificacoes();
		int x = consultar.getEventosHoje();
		while(x>0){
			String c = ""+consultar.getIds()[x-1];
			formEvento = new FrmEvento(getPermissao(),1, c, getLogin());
			formEvento.setLocation(200, 200);
			formEvento.setVisible(true);
			x-=1;
		}		
	}
} 