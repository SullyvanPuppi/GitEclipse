package apresentacao;

//--Imports de classes internas necessárias
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.BrownSugar;
import com.jgoodies.looks.plastic.theme.DarkStar;
import com.jgoodies.looks.plastic.theme.DesertBlue;
import com.jgoodies.looks.plastic.theme.DesertBluer;
import com.jgoodies.looks.plastic.theme.DesertGreen;
import com.jgoodies.looks.plastic.theme.DesertRed;
import com.jgoodies.looks.plastic.theme.DesertYellow;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import com.jgoodies.looks.plastic.theme.ExperienceGreen;
import com.jgoodies.looks.plastic.theme.ExperienceRoyale;
import com.jgoodies.looks.plastic.theme.LightGray;
import com.jgoodies.looks.plastic.theme.Silver;
import com.jgoodies.looks.plastic.theme.SkyBlue;
import com.jgoodies.looks.plastic.theme.SkyBluer;
import com.jgoodies.looks.plastic.theme.SkyGreen;
import com.jgoodies.looks.plastic.theme.SkyKrupp;
import com.jgoodies.looks.plastic.theme.SkyPink;
import com.jgoodies.looks.plastic.theme.SkyRed;
import com.jgoodies.looks.plastic.theme.SkyYellow;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;


import negocios.CapturaTela;
/**
 * @author Sullyvan Puppi
 *
 * Formulário principal do Sistema
 */
public class FrmPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Painel principal
	private JPanel jContentPane = null;
	//Barra de menus
	private JMenuBar barraMenu = new JMenuBar();
	//Itens da barra de menus
	private JMenu mnuSistema = new JMenu("Sistema" ); 
	private JMenu mnuCurso = new JMenu("Cursos"); 
	private JMenu mnuProfessores = new JMenu("Professores");  
	private JMenu mnuAlunos = new JMenu("Alunos");  
	private JMenu mnuClasses = new JMenu("Classes");  
	private JMenu mnuAjuda = new JMenu("Ajuda");  
	private JMenu mnuSobre= new JMenu("Sobre");
	private JMenu mnuTema = new JMenu("Tema visual");

	//criando itens do menu Sistema
	private JMenuItem logon = new JMenuItem("Efetuar Logon");
	private JMenuItem configuracoes = new JMenuItem("Configurações");
	private JMenuItem cadastroUsuario = new JMenuItem("Cadastro de Usuário");
	private JMenuItem AlterarSenha = new JMenuItem("Alterar senha");
	private JMenuItem copiaseguranca = new JMenuItem("Cópia de Segurança");
	private JMenuItem sair = new JMenuItem("Sair");

	//criando itens do menu Curso
	private JMenuItem mnuItemCurso = new JMenuItem("Cadastro");

	//criando itens do menu Professor
	private JMenuItem cadastrar = new JMenuItem("Cadastro");
	private JMenuItem recados = new JMenuItem("Recados");

	//criando itens do menu Alunos
	private JMenuItem cadastrarAluno = new JMenuItem("Cadastro");
	private JMenuItem solicitacoes = new JMenuItem("Solicitações");
	private JMenuItem boletim = new JMenuItem("Boletim");

	//criando itens do menu classes
	private JMenuItem cadastrarclasse = new JMenuItem("Cadastro");

	//Criando itens do menu ajuda
	private JMenuItem mnuItemAjuda = new JMenuItem("Ajuda tópicos");
	private JMenuItem mnuItemManual = new JMenuItem("Manual");
	
	//Criando itens do menu sobre
	private JMenuItem mnuItemSobre = new JMenuItem("SENA");
	
	/**Items do menu temas**/
	private JCheckBoxMenuItem metal = new JCheckBoxMenuItem("Metal");
	private JCheckBoxMenuItem SkyYellow  = new JCheckBoxMenuItem("Sky Yellow");
	private JCheckBoxMenuItem SkyRed  = new JCheckBoxMenuItem("Sky Red");
	private JCheckBoxMenuItem SkyPink  = new JCheckBoxMenuItem("Sky Pink");
	private JCheckBoxMenuItem SkyKrupp  = new JCheckBoxMenuItem("Sky Krupp");
	private JCheckBoxMenuItem SkyGreen  = new JCheckBoxMenuItem("Sky Green");
	private JCheckBoxMenuItem SkyBluer  = new JCheckBoxMenuItem("Sky Bluer");
	private JCheckBoxMenuItem SkyBlue  = new JCheckBoxMenuItem("Sky Blue");
	private JCheckBoxMenuItem Silver  = new JCheckBoxMenuItem("Silver");
	private JCheckBoxMenuItem LightGray  = new JCheckBoxMenuItem("Light Gray");
	private JCheckBoxMenuItem ExperienceRoyale  = new JCheckBoxMenuItem("Experience Royale");
	private JCheckBoxMenuItem ExperienceGreen  = new JCheckBoxMenuItem("Experience Green");
	private JCheckBoxMenuItem ExperienceBlue  = new JCheckBoxMenuItem("Experience Blue");
	private JCheckBoxMenuItem DesertYellow  = new JCheckBoxMenuItem("Desert Yellow");
	private JCheckBoxMenuItem DesertRed  = new JCheckBoxMenuItem("Desert Red");
	private JCheckBoxMenuItem DesertGreen  = new JCheckBoxMenuItem("Desert Green");
	private JCheckBoxMenuItem DesertBluer  = new JCheckBoxMenuItem("Desert Bluer");
	private JCheckBoxMenuItem DesertBlue  = new JCheckBoxMenuItem("Desert Blue");
	private JCheckBoxMenuItem DarkStar  = new JCheckBoxMenuItem("Dark Star");
	private JCheckBoxMenuItem BrownSugar  = new JCheckBoxMenuItem("Brown Sugar");
	private JCheckBoxMenuItem classico = new JCheckBoxMenuItem("Windows Clássico");
	private JCheckBoxMenuItem windows = new JCheckBoxMenuItem("Windows");

	//Criação da área de trabalho do formulário principal
	private JDesktopPane deskPrincipal;

	//Atributo que armazena o critério de direitos no sistema
	private int permissao;

	//Instanciação das classes dos formulários filhos
	FrmCadastroUsuario formCadUsuario;
	FrmUsuarios formUsuarios;
	FrmConfSistema formConfSistema;
	FrmCursos formCursos;
	FrmProfessores formProfessores;
	FrmAlunos formAlunos;
	
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
	
	/**
	 * This is the default constructor
	 */
	public FrmPrincipal(int permissao, String login) {
		super();
		this.login = login;
		setPermissao(permissao);
		initialize(getPermissao());
	}
	
	/*
	 * Customiza tela do formulário principal
	 * onde cada grupo tem acessos restritos
	 */
	public void validaPermissao(int x){
		if(x==1){

		}else if(x==2){
			cadastroUsuario.setVisible(false);
			copiaseguranca.setVisible(false);
			solicitacoes.setVisible(false);
			boletim.setVisible(false);
		}else if(x==3){
			cadastroUsuario.setVisible(false);
			copiaseguranca.setVisible(false);
		}else if(x==4){
			cadastroUsuario.setVisible(false);
			copiaseguranca.setVisible(false);
			recados.setVisible(false);
			solicitacoes.setVisible(false);
			boletim.setVisible(false);
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
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int permissao) {
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "Temas visuais não carregados.","Tema visual",2);
		}
		validaPermissao(permissao);
		atualizaTema(13);//Atualiza o tema para o tema padrão do sistema
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(700, 500));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(new Dimension(700, 500));
		this.setJMenuBar(barraMenu);
		this.setTitle("][ Sistema de Ensino Nova Athenas ][ SENA I ][ Usuário: "+getLogin());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/logo.gif")));
		deskPrincipal = new JDesktopPane();
		deskPrincipal.setDesktopManager(new LimitadorTela());
		deskPrincipal.add(getJContentPane(), getJContentPane().getName());
		this.setContentPane(deskPrincipal);
		setContentPane(deskPrincipal);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			//--Itens da barra de menu
			barraMenu.add(mnuSistema);
			barraMenu.add(mnuCurso);
			barraMenu.add(mnuProfessores);
			barraMenu.add(mnuAlunos);
			barraMenu.add(mnuClasses);
			barraMenu.add(mnuSobre);
			barraMenu.add(mnuAjuda);
			
			//--Sub-menus
			mnuClasses.add(cadastrarclasse);
			mnuProfessores.add(cadastrar);
			mnuProfessores.add(recados);
			mnuAlunos.add(cadastrarAluno);
			mnuAlunos.add(solicitacoes);
			mnuAlunos.add(boletim);
			mnuCurso.add(mnuItemCurso);
			mnuSistema.add(logon);
			mnuSistema.add(configuracoes);
			mnuSistema.add(cadastroUsuario);
			mnuSistema.add(AlterarSenha);
			mnuSistema.add(copiaseguranca);
			mnuSistema.add(mnuTema);
			mnuSistema.addSeparator();
			mnuSistema.add(sair);
			mnuAjuda.add(mnuItemAjuda);
			mnuAjuda.add(mnuItemManual);
			mnuSobre.add(mnuItemSobre);

			/**Menu Temas**/
			mnuTema.add(SkyYellow);
			mnuTema.add(SkyRed);
			mnuTema.add(SkyPink);
			mnuTema.add(SkyKrupp);
			mnuTema.add(SkyGreen);
			mnuTema.add(SkyBluer);
			mnuTema.add(SkyBlue);
			mnuTema.add(Silver);
			mnuTema.add(LightGray);
			mnuTema.add(ExperienceRoyale);
			mnuTema.add(ExperienceGreen);
			mnuTema.add(ExperienceBlue);
			mnuTema.add(DesertYellow);
			mnuTema.add(DesertRed);
			mnuTema.add(DesertGreen);
			mnuTema.add(DesertBluer);
			mnuTema.add(DesertBlue);
			mnuTema.add(DarkStar);
			mnuTema.add(BrownSugar);
			mnuTema.add(windows);
			mnuTema.add(classico);
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());

			//Teclas de atalhos para os menus
			sair.setMnemonic(KeyEvent.VK_A);
			mnuSistema.setMnemonic(KeyEvent.VK_S);
			mnuCurso.setMnemonic(KeyEvent.VK_C);
			mnuProfessores.setMnemonic(KeyEvent.VK_P);
			mnuAlunos.setMnemonic(KeyEvent.VK_A);
			mnuClasses.setMnemonic(KeyEvent.VK_L);
			mnuAjuda.setMnemonic(KeyEvent.VK_A);
			mnuSobre.setMnemonic(KeyEvent.VK_B);
			mnuTema.setMnemonic(KeyEvent.VK_T);
			logon.setMnemonic(KeyEvent.VK_L);
			configuracoes.setMnemonic(KeyEvent.VK_C);
			cadastroUsuario.setMnemonic(KeyEvent.VK_U);
			copiaseguranca.setMnemonic(KeyEvent.VK_S);
			mnuItemCurso.setMnemonic(KeyEvent.VK_C);
			cadastrar.setMnemonic(KeyEvent.VK_C);
			recados.setMnemonic(KeyEvent.VK_R);
			cadastrarAluno.setMnemonic(KeyEvent.VK_A);
			solicitacoes.setMnemonic(KeyEvent.VK_S);
			boletim.setMnemonic(KeyEvent.VK_B);
			cadastrarclasse.setMnemonic(KeyEvent.VK_C);
			SkyYellow.setMnemonic(KeyEvent.VK_Y);
			SkyRed.setMnemonic(KeyEvent.VK_R);
			SkyPink.setMnemonic(KeyEvent.VK_P);
			SkyKrupp.setMnemonic(KeyEvent.VK_K);
			SkyGreen.setMnemonic(KeyEvent.VK_G);
			SkyBluer.setMnemonic(KeyEvent.VK_B);
			SkyBlue.setMnemonic(KeyEvent.VK_L);
			Silver.setMnemonic(KeyEvent.VK_V);
			LightGray.setMnemonic(KeyEvent.VK_L);
			ExperienceRoyale.setMnemonic(KeyEvent.VK_O);
			ExperienceGreen.setMnemonic(KeyEvent.VK_E);
			ExperienceBlue.setMnemonic(KeyEvent.VK_U);
			DesertYellow.setMnemonic(KeyEvent.VK_O);
			DesertRed.setMnemonic(KeyEvent.VK_D);
			DesertGreen.setMnemonic(KeyEvent.VK_N);
			DesertBluer.setMnemonic(KeyEvent.VK_D);
			DesertBlue.setMnemonic(KeyEvent.VK_S);
			DarkStar.setMnemonic(KeyEvent.VK_T);
			BrownSugar.setMnemonic(KeyEvent.VK_W);

			//Ações dos items dos menus
			copiaseguranca.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//GerarBackup backup = new GerarBackup();
				}});		
			logon.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(new JFrame(),"Deseja fechar login?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						dispose();
						CapturaTela tela = new CapturaTela();
						FrmLogin formLogin = new FrmLogin(tela.getLarguraTela(),tela.getAlturaTela());
						formLogin.show();
					}
				}});
			/*solicitacoes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((formUsuarios == null) || (formUsuarios.isClosed())) {
						formUsuarios = new FrmUsuarios(deskPrincipal);
						deskPrincipal.add(formUsuarios, new Integer(2));
						formUsuarios.show();
					}
				}});
			cadastrarclasse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((frmCadastroClasse == null) || (frmCadastroClasse.isClosed())) {
						frmCadastroClasse = new FormCadastroClasse(deskPrincipal, getPermissao());
						deskPrincipal.add(frmCadastroClasse, new Integer(1));
						frmCadastroClasse.show();
					}
				}});			
			recados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((formAluno == null) || (formAluno.isClosed())) {
						formAluno = new FrmCadastroAluno(deskPrincipal, getPermissao(), 0, "", "");
						deskPrincipal.add(formAluno, new Integer(2));
						formAluno.show();
					}
				}});*/
			cadastrarAluno.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaCascata();
					if ((formAlunos == null) || (formAlunos.isClosed())) {
						formAlunos = new FrmAlunos(deskPrincipal, getPermissao());
						deskPrincipal.add(formAlunos, new Integer(2));
						formAlunos.reshape(getPosX(), getPosY(), 716, 500);
						formAlunos.show();
					}
				}});
			
			cadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaCascata();
					if ((formProfessores == null) || (formProfessores.isClosed())) {
						formProfessores = new FrmProfessores(deskPrincipal, getPermissao());
						deskPrincipal.add(formProfessores, new Integer(2));
						formProfessores.reshape(getPosX(), getPosY(), 716, 508);
						formProfessores.show();
					}
				}});
			mnuItemCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaCascata();
					if ((formCursos == null) || (formCursos.isClosed())) {
						formCursos = new FrmCursos(deskPrincipal, getPermissao());
						deskPrincipal.add(formCursos, new Integer(2));
						formCursos.reshape(getPosX(), getPosY(), 666, 500);
						formCursos.show();
					}
				}});
			configuracoes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaCascata();
					if ((formConfSistema == null) || (formConfSistema.isClosed())) {
						formConfSistema = new FrmConfSistema(deskPrincipal, getPermissao());
						deskPrincipal.add(formConfSistema, new Integer(2));
						formConfSistema.reshape(getPosX(), getPosY(), 693, 421);
						formConfSistema.show();
					}
				}});
			cadastroUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaCascata();
					if ((formUsuarios == null) || (formUsuarios.isClosed())) {
						formUsuarios = new FrmUsuarios(deskPrincipal, getPermissao(), getLogin());
						deskPrincipal.add(formUsuarios, new Integer(2));
						formUsuarios.reshape(getPosX(), getPosY(), 514, 480);
						formUsuarios.show();
					}
				}});
			AlterarSenha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaCascata();
					if ((formCadUsuario == null) || (formCadUsuario.isClosed())) {
						formCadUsuario = new FrmCadastroUsuario(deskPrincipal, getPermissao(), 2, getLogin(), getLogin());
						deskPrincipal.add(formCadUsuario, new Integer(2));
						formCadUsuario.reshape(getPosX(), getPosY(), 389, 285);
						formCadUsuario.show();
					}
				}});
			sair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			/**Ações para configurar o tema visual do sistema**/
			metal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(1);
				}
			});
			SkyYellow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(2);
				}
			});
			SkyRed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(3);
				}
			});
			SkyPink.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(4);
				}
			});
			SkyKrupp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(5);
				}
			});
			SkyGreen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(6);
				}
			});
			SkyBluer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(7);
				}
			});
			SkyBlue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(8);
				}
			});
			Silver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(9);
				}
			});
			LightGray.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(10);
				}
			});
			ExperienceRoyale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(11);
				}
			});
			ExperienceGreen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(12);
				}
			});
			ExperienceBlue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(13);
				}
			});
			DesertYellow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(14);
				}
			});
			DesertRed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(15);
				}
			});
			DesertGreen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(16);
				}
			});
			DesertBluer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(17);
				}
			});
			DesertBlue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(18);
				}
			});
			DarkStar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(19);
				}
			});
			BrownSugar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(20);
				}
			});
			windows.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(21);
				}
			});
			classico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atualizaTema(22);
				}
			});
		}
		return jContentPane;
	}
	private void atualizaTema(int x){
		try{
			if (x==2){
				PlasticLookAndFeel.setPlasticTheme(new SkyYellow());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(true);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==3){
				PlasticLookAndFeel.setPlasticTheme(new SkyRed());			
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(true);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==4){
				PlasticLookAndFeel.setPlasticTheme(new SkyPink());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(true);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==5){
				PlasticLookAndFeel.setPlasticTheme(new SkyKrupp());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(true);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==6){
				PlasticLookAndFeel.setPlasticTheme(new SkyGreen());			
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(true);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==7){
				PlasticLookAndFeel.setPlasticTheme(new SkyBluer());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(true);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==8){
				PlasticLookAndFeel.setPlasticTheme(new SkyBlue());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(true);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==9){
				PlasticLookAndFeel.setPlasticTheme(new Silver());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(true);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==10){
				PlasticLookAndFeel.setPlasticTheme(new LightGray());			
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(true);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==11){
				PlasticLookAndFeel.setPlasticTheme(new ExperienceRoyale());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(true);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==12){
				PlasticLookAndFeel.setPlasticTheme(new ExperienceGreen());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(true);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==13){
				PlasticLookAndFeel.setPlasticTheme(new ExperienceBlue());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(true);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==14){
				PlasticLookAndFeel.setPlasticTheme(new DesertYellow());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(true);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==15){
				PlasticLookAndFeel.setPlasticTheme(new DesertRed());			
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(true);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==16){
				PlasticLookAndFeel.setPlasticTheme(new DesertGreen());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(true);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==17){
				PlasticLookAndFeel.setPlasticTheme(new DesertBluer());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(true);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==18){
				PlasticLookAndFeel.setPlasticTheme(new DesertBlue());			
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(true);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==19){
				PlasticLookAndFeel.setPlasticTheme(new DarkStar());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(true);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if (x==20){
				PlasticXPLookAndFeel.setPlasticTheme(new BrownSugar());
				UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(true);
				windows.setSelected(false);
				classico.setSelected(false);
			}else if(x==21){				
				UIManager.setLookAndFeel(new WindowsLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(true);
				classico.setSelected(false);
			}	
			else if(x==22){				
				UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
				SkyYellow.setSelected(false);
				SkyRed.setSelected(false);
				SkyPink.setSelected(false);
				SkyKrupp.setSelected(false);
				SkyGreen.setSelected(false);
				SkyBluer.setSelected(false);
				SkyBlue.setSelected(false);
				Silver.setSelected(false);
				LightGray.setSelected(false);
				ExperienceRoyale.setSelected(false);
				ExperienceGreen.setSelected(false);
				ExperienceBlue.setSelected(false);
				DesertYellow.setSelected(false);
				DesertRed.setSelected(false);
				DesertGreen.setSelected(false);
				DesertBluer.setSelected(false);
				DesertBlue.setSelected(false);
				DarkStar.setSelected(false);
				BrownSugar.setSelected(false);
				windows.setSelected(false);
				classico.setSelected(true);
			}
			SwingUtilities.updateComponentTreeUI(rootPane);
			SwingUtilities.updateComponentTreeUI(barraMenu);
			SwingUtilities.updateComponentTreeUI(mnuSistema);
			SwingUtilities.updateComponentTreeUI(mnuCurso);
			SwingUtilities.updateComponentTreeUI(mnuProfessores);
			SwingUtilities.updateComponentTreeUI(mnuAlunos);
			SwingUtilities.updateComponentTreeUI(mnuClasses);
			SwingUtilities.updateComponentTreeUI(mnuAjuda);
			SwingUtilities.updateComponentTreeUI(mnuSobre);
			SwingUtilities.updateComponentTreeUI(mnuTema);
			SwingUtilities.updateComponentTreeUI(logon);
			SwingUtilities.updateComponentTreeUI(configuracoes);
			SwingUtilities.updateComponentTreeUI(cadastroUsuario);
			SwingUtilities.updateComponentTreeUI(AlterarSenha);
			SwingUtilities.updateComponentTreeUI(copiaseguranca);
			SwingUtilities.updateComponentTreeUI(sair);
			SwingUtilities.updateComponentTreeUI(mnuItemCurso);
			SwingUtilities.updateComponentTreeUI(cadastrar);
			SwingUtilities.updateComponentTreeUI(recados);
			SwingUtilities.updateComponentTreeUI(cadastrarAluno);
			SwingUtilities.updateComponentTreeUI(solicitacoes);
			SwingUtilities.updateComponentTreeUI(boletim);
			SwingUtilities.updateComponentTreeUI(cadastrarclasse);
			SwingUtilities.updateComponentTreeUI(mnuItemAjuda);
			SwingUtilities.updateComponentTreeUI(mnuItemManual);
			SwingUtilities.updateComponentTreeUI(mnuItemSobre);
			SwingUtilities.updateComponentTreeUI(SkyYellow);
			SwingUtilities.updateComponentTreeUI(SkyRed);
			SwingUtilities.updateComponentTreeUI(SkyPink);
			SwingUtilities.updateComponentTreeUI(SkyKrupp);
			SwingUtilities.updateComponentTreeUI(SkyGreen);
			SwingUtilities.updateComponentTreeUI(SkyBluer);
			SwingUtilities.updateComponentTreeUI(SkyBlue);
			SwingUtilities.updateComponentTreeUI(Silver);
			SwingUtilities.updateComponentTreeUI(LightGray);
			SwingUtilities.updateComponentTreeUI(ExperienceRoyale);
			SwingUtilities.updateComponentTreeUI(ExperienceGreen);
			SwingUtilities.updateComponentTreeUI(ExperienceBlue);
			SwingUtilities.updateComponentTreeUI(DesertYellow);
			SwingUtilities.updateComponentTreeUI(DesertRed);
			SwingUtilities.updateComponentTreeUI(DesertGreen);
			SwingUtilities.updateComponentTreeUI(DesertBluer);
			SwingUtilities.updateComponentTreeUI(DesertBlue);
			SwingUtilities.updateComponentTreeUI(DarkStar);
			SwingUtilities.updateComponentTreeUI(BrownSugar);
			SwingUtilities.updateComponentTreeUI(windows);
			SwingUtilities.updateComponentTreeUI(classico);
			
			
			
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null,"Erro interno do sistema.","Erro",2);
		}
	}
}
