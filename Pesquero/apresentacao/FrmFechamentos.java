package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocios.CapturaTela;

import java.awt.Dimension;


public class FrmFechamentos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4489272908012942948L;

	FrmNovoFechamento formNovoFechamento;
	
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;
	
	//Direitos de usuário determinado pelo grupo
	private int permissao;

	
	private JLabel lblDivisao = null;
	private JButton btnNovo = null;
	private JButton btnAbrir = null;
	private JButton btnCancelar = null;
	private JButton btnRelatorio = null;
		
	private JTable fechamentos = null;
	private JScrollPane jspFechamentos = null;
	
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
	public FrmFechamentos(JDesktopPane desktop, int permissao) {
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
		this.setTitle("Cadastro de folhas de pagamentos");	
		fechamentos = new JTable();
		jspFechamentos = new JScrollPane(fechamentos);
		jspFechamentos.setPreferredSize(new Dimension(100, 400));
		lblDivisao = new JLabel("_____________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAbrir = new JButton("Abrir");
		btnAbrir.setToolTipText("Exibir item selecionado");
		btnNovo = new JButton("Novo");
		btnNovo.setToolTipText("Iniciar novo cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");
				
		Box linhaUm = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();
				
		linhaUm.add(jspFechamentos);		
		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAbrir);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnNovo);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);
		
		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);
		
		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formNovoFechamento = new FrmNovoFechamento(desk, getPermissao());
				CapturaTela tela = new CapturaTela();
				formNovoFechamento.setLocation((tela.getLarguraTela()/2)-(400/2), (tela.getAlturaTela()/2)-(100/2));
				formNovoFechamento.setVisible(true);
			}});
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"