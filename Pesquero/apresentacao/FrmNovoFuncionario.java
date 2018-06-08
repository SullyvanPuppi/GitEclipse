package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrmNovoFuncionario extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4943663076811187934L;
	FrmCadastroFuncionario formCadastroFuncionario;
	FrmCadastroFuncionarioCompleto formCadastroFuncionarioCompleto;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

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
	/**
	 * This is the xxx default constructor
	 */
	public FrmNovoFuncionario(JDesktopPane desktop, int permissao) {
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
		this.setTitle("Funcionário");
		this.setSize(400,100);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Cadastrar novo funcionário de forma simplificado ou completo?");
		JButton btnSimples = new JButton("Simplificado");
		btnSimples.setToolTipText("Cadastro simplificado de funcionário");
		JButton btnCompleta = new JButton("Completo");
		btnCompleta.setToolTipText("Cadastro completo de funcionário");
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();

		linhaUm.add(lblNovo);
		linhaDois.add(btnSimples);
		linhaDois.add(Box.createHorizontalStrut(10));
		linhaDois.add(btnCompleta);
		linhaDois.add(Box.createHorizontalStrut(10));
		linhaDois.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaDois);


		jContentPane = new JPanel();
		jContentPane.add(linhas);
		setContentPane(jContentPane);


		btnSimples.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				formCadastroFuncionario = new FrmCadastroFuncionario(desk, getPermissao(), 0, "", "");
				setVisible(false);
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroFuncionario")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroFuncionarioCompleto")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					desk.add(formCadastroFuncionario, new Integer(2));
					formCadastroFuncionario.setLocation(100, 100);
					formCadastroFuncionario.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de funcionário","Notificação",1);
				}	
			}});
		btnCompleta.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				formCadastroFuncionarioCompleto = new FrmCadastroFuncionarioCompleto(desk, getPermissao(), 0, "", "");
				setVisible(false);
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroFuncionario")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroFuncionarioCompleto")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					desk.add(formCadastroFuncionarioCompleto, new Integer(2));
					formCadastroFuncionarioCompleto.setLocation(100, 100);
					formCadastroFuncionarioCompleto.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de funcionário","Notificação",1);
				}
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});	
	}

}