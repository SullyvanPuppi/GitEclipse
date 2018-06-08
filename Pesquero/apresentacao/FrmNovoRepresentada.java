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

public class FrmNovoRepresentada extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4124937534362836912L;
	FrmCadastroRepresentada formCadastroRepresentada;
	FrmCadastroRepresentadaCompleto formCadastroRepresentadaCompleto;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	private JButton btnSimples = null;
	private JButton btnCompleta = null;
	private JButton btnCancelar = null;

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
	public FrmNovoRepresentada(JDesktopPane desktop, int permissao) {
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
		this.setTitle("Empresa representada");
		this.setSize(400,100);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Cadastrar nova empresa representada de forma simplificada ou completa?");
		btnSimples = new JButton("Simplificado");
		btnSimples.setToolTipText("Cadastro simplificado de empresa representada");
		btnCompleta = new JButton("Completo");
		btnCompleta.setToolTipText("Cadastro completo de empresa representada");
		btnCancelar = new JButton("Cancelar");
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
				formCadastroRepresentada = new FrmCadastroRepresentada(desk, getPermissao(), 0, "", "");
				setVisible(false);
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
					desk.add(formCadastroRepresentada, new Integer(2));
					formCadastroRepresentada.setLocation(100, 100);
					formCadastroRepresentada.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de empresa representada","Notificação",1);
				}		
			}});
		btnCompleta.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				formCadastroRepresentadaCompleto = new FrmCadastroRepresentadaCompleto(desk, getPermissao(), 0, "", "");
				setVisible(false);
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
					desk.add(formCadastroRepresentadaCompleto, new Integer(2));
					formCadastroRepresentadaCompleto.setLocation(100, 100);
					formCadastroRepresentadaCompleto.show();	
				}else{
					JOptionPane.showMessageDialog(null,"Feche janela aberta de cadastro de empresa representada","Notificação",1);
				}		
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}

}