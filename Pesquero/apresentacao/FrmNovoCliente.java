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

public class FrmNovoCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2115237055182459099L;
	FrmCadastroCliente formCadastroCliente;
	FrmCadastroClienteCompleto formCadastroClienteCompleto;

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
	public FrmNovoCliente(JDesktopPane desktop, int permissao) {
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
		this.setTitle("Empresa cliente");
		this.setSize(400,100);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Cadastrar nova empresa cliente de forma simplificada ou completa?");
		JButton btnSimples = new JButton("Simplificado");
		btnSimples.setToolTipText("Cadastro simplificado de cliente");
		JButton btnCompleta = new JButton("Completo");
		btnCompleta.setToolTipText("Cadastro completo de cliente");
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
				formCadastroCliente = new FrmCadastroCliente(desk, getPermissao(), 0, "", "");
				setVisible(false);
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroCliente")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroClienteCompleto")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					desk.add(formCadastroCliente, new Integer(2));
					formCadastroCliente.setLocation(50, 50);
					formCadastroCliente.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche a janela aberta de cadastro de cliente","Notificação",1);
				}			
			}});
		btnCompleta.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				formCadastroClienteCompleto = new FrmCadastroClienteCompleto(desk, getPermissao(), 0, "", "");
				setVisible(false);
				int c = desk.countComponents();
				int y=0;
				int acusa = 0;
				while(y<c){
					String x = ""+desk.getComponents()[y].getClass();
					x = x.substring(19);
					if(x.equals("FrmCadastroCliente")){
						acusa = 1;
					}
					if(x.equals("FrmCadastroClienteCompleto")){
						acusa = 1;
					}
					y +=1;
				}
				if(acusa==0){
					desk.add(formCadastroClienteCompleto, new Integer(2));
					formCadastroClienteCompleto.setLocation(50, 50);
					formCadastroClienteCompleto.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche a janela aberta de cadastro de cliente","Notificação",1);
				}
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}

}