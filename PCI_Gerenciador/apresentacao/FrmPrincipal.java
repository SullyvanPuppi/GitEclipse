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
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import negocios.CapturaTela;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class FrmPrincipal extends JFrame {  

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078915646814907331L;
	/**
	 * 
	 */	
	FrmCadastroImovel formCadastroImovel;
	FrmConsultaImoveis formConsultaImoveis;
	
	ImageIcon icon = null;

	//--Funçoes admnistrativas
	//--Armazena login atual
	private String login;

	//--Retorna login atual
	public String getLogin(){
		return this.login;
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
	private JMenu mnuImoveis = new JMenu("Imóveis");

	private JMenu mnuSobre = new JMenu("Sobre");

	//------------------------Menus items-------------------------------------//
	//--Menu Imï¿½veis
	private JMenuItem mnuItemCadastro = new JMenuItem("Cadastro");

	private JMenuItem mnuItemConsulta = new JMenuItem("Consulta");

	private JMenuItem mnuItemSair = new JMenuItem("Sair");

	//--Menu sobre
	private JMenuItem mnuItemSobre = new JMenuItem("Desenvolvedor");

	public FrmPrincipal(String login) {  
		this.login = login;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setMinimumSize(new Dimension(700, 500));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(new Dimension(700, 500));
		initialize();	
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(sair()==0){
					System.exit(0);
				}
			}
		});		
	}
	private void initialize() {	
		horasData();
		//Timer timer = new Timer(1000, tarefa);
		//timer.start();
		icon = new ImageIcon("apresentacao/fundoB.jpg");
		deskPrincipal = new JDesktopPane(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -3677263681323292699L;

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
		this.setTitle("][ Pesquero Construtora e Incorporadora ][ Sistema gerenciador de conteúdo ][");
		try{

		}catch(Exception e){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icone.gif")));	
		}		
		deskPrincipal.setDesktopManager(new LimitadorTela());
		//deskPrincipal = new JDesktopPane();
		deskPrincipal.setOpaque( false );
		//--Menus suspensos
		mnuBarraMenus.add(mnuImoveis);
		//--Menu itens
		//--Menu sistema
		mnuImoveis.add(mnuItemCadastro);
		mnuImoveis.add(mnuItemConsulta);
		mnuImoveis.addSeparator();
		mnuImoveis.add(mnuItemSair);
		//--Menu sobre
		mnuBarraMenus.add(mnuSobre);
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
		getContentPane().add("Center", deskPrincipal);
		getContentPane().add("South", barStatus);

		//setContentPane(pane);  
		atualizaTema();

		//---Aï¿½ï¿½es
		mnuItemCadastro.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				int c = deskPrincipal.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+deskPrincipal.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroImovel")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					formCadastroImovel = new FrmCadastroImovel(deskPrincipal, 0, "");
					deskPrincipal.add(formCadastroImovel, new Integer(2));
					formCadastroImovel.setLocation(getPosX(), getPosY());
					formCadastroImovel.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche a janela aberta de cadastro de imóvel","Notificação",2);
				}
			}});
		mnuItemConsulta.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ajustaCascata();
				int c = deskPrincipal.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+deskPrincipal.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmConsultaImoveis")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					formConsultaImoveis = new FrmConsultaImoveis(deskPrincipal);
					deskPrincipal.add(formConsultaImoveis, new Integer(2));
					formConsultaImoveis.setLocation(getPosX(), getPosY());
					formConsultaImoveis.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Já existe uma janela de consulta de imóveis aberta!","Notificação",2);
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
			barStatus.setText(horas+"     |     "+data+"     |     Usuï¿½rio logado atualmente:  "+getLogin()+"     ");
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
			SwingUtilities.updateComponentTreeUI(mnuImoveis);
			SwingUtilities.updateComponentTreeUI(mnuItemCadastro);
			SwingUtilities.updateComponentTreeUI(mnuItemConsulta);
			SwingUtilities.updateComponentTreeUI(mnuSobre);
			SwingUtilities.updateComponentTreeUI(mnuItemSair);
			SwingUtilities.updateComponentTreeUI(mnuItemSobre);
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null,"Erro interno do sistema.","Erro",2);
		}
	}
} 