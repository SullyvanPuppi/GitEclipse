package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import negocios.Fechamento;


public class FrmNovoFechamento extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1999577451632157426L;

	FrmFolhaPagamento formFolhaPagamento;
		
	//@SuppressWarnings("unused")
	@SuppressWarnings("unused")
	private String data;

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
	/**
	 * This is the xxx default constructor
	 */
	public FrmNovoFechamento(JDesktopPane desktop, int permissao) {
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
		this.setTitle("Cadastrar novo fechamento de folha de pagamento");
		this.setSize(400,100);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Mês de referência");
		final JFormattedTextField txtMes = new JFormattedTextField(setMascara("##/####"));
		JButton btnConfirmar = new JButton("Confirmar");
		JButton btnCancelar = new JButton("Cancelar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();

		linhaUm.add(lblNovo);
		linhaUm.add(txtMes);
		linhaDois.add(btnConfirmar);
		linhaDois.add(Box.createHorizontalStrut(10));
		linhaDois.add(btnCancelar);
		linhaDois.add(Box.createHorizontalStrut(10));
		linhaDois.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaDois);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		setContentPane(jContentPane);

		btnConfirmar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if ((formFolhaPagamento == null) || (!formFolhaPagamento.isVisible())) {
					Fechamento fechar = new Fechamento();
					if(fechar.validaMes(txtMes.getText(), fechar.getHoje())==1){
						JOptionPane.showMessageDialog(null,"Mês de referência inválido","Erro",2);
					}else{
						formFolhaPagamento = new FrmFolhaPagamento(desk, getPermissao(), txtMes.getText());
						formFolhaPagamento.setLocation(100, 100);
						setVisible(false);
						formFolhaPagamento.show();	
					}					
				}
			}});	
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}

}