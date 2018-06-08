package negocios;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.view.JasperViewer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import persistencia.CadastroFuncionario;

public class Funcionario {

	public Funcionario() {
		super();
	}	

	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;

	//Campos do cadastro simplificado
	private String txtFuncionario;
	private String txtNmFuncionario;
	private String txtFuncao;
	private double txtSalario;
	private String txtDoc;
	private String txtTelefone;
	private String txtEmail;
	private boolean chkValeTransporte;
	private boolean chkValeRefeicao;
	private int txtDependentes;
	private boolean chkValeFamilia;
	private String sexo;
	private String txtObs;

	private String vlSalario;
	private String qtDependentes;

	//Campos do cadastro completo
	private String txtCbo;
	private String txtCtps;
	private String txtDtNascimento;
	private String txtCpf;
	private String txtEndereco;
	private String txtEnderecoNumero;
	private String txtEnderecoComplemento;
	private String txtEnderecoBairro;
	private String txtEnderecoCidade;
	private String cmbEnderecoEstado;
	private String txtEnderecoCep;
	private String txtTelefoneContato;

	//--Campos do cadastro completo
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
	}
	public String getTxtCbo() {
		return txtCbo;
	}
	public String getTxtCpf() {
		return txtCpf;
	}
	public String getTxtCtps() {
		return txtCtps;
	}
	public String getTxtDtNascimento() {
		return txtDtNascimento;
	}
	public String getTxtEndereco() {
		return txtEndereco;
	}
	public String getTxtEnderecoBairro() {
		return txtEnderecoBairro;
	}
	public String getTxtEnderecoCep() {
		return txtEnderecoCep;
	}
	public String getTxtEnderecoCidade() {
		return txtEnderecoCidade;
	}
	public String getTxtEnderecoComplemento() {
		return txtEnderecoComplemento;
	}
	public String getTxtEnderecoNumero() {
		return txtEnderecoNumero;
	}
	public String getTxtTelefoneContato() {
		return txtTelefoneContato;
	}
	//--Campos do cadastro simples
	public String getTxtObs(){
		return txtObs;
	}	
	public String getSexo(){
		return sexo;
	}	
	public boolean getChkValeFamilia() {
		return chkValeFamilia;
	}
	public boolean getChkValeRefeicao() {
		return chkValeRefeicao;
	}
	public boolean getChkValeTransporte() {
		return chkValeTransporte;
	}
	public int getTxtDependentes() {
		return txtDependentes;
	}
	public String getQtDependentes() {
		return qtDependentes;
	}
	public String getTxtDoc() {
		return txtDoc;
	}
	public String getTxtEmail() {
		return txtEmail;
	}
	public String getTxtFuncao() {
		return txtFuncao;
	}
	public String getTxtFuncionario() {
		return txtFuncionario;
	}
	public String getTxtNmFuncionario() {
		return txtNmFuncionario;
	}
	public double getTxtSalario() {
		return txtSalario;
	}
	public String getVlSalario() {
		return vlSalario;
	}
	public String getTxtTelefone() {
		return txtTelefone;
	}
	public String getMsgStatus(){
		return msgStatus;
	}
	public int registros(){
		return registros;
	}
	//---Valida data de nascimento do funcionário irá retornar 1 se funcionário menor de idade
	public int validaDtNasc(String dtNasc){
		int x = 0;
		int age = 0;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascInput = null;

		try {
			dataNascInput= sdf.parse(dtNasc);
		} catch (Exception e) {}

		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNascInput);		

		//Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		//Obtém a idade baseado no ano
		age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		if(age<18){
			x = 1;
		}
		if(age<=0){//Se data inválida
			x = 2;
		}
		return x;
	}
	public int calculaIdade(String dataNasc){
		int age = 0;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascInput = null;

		try {
			dataNascInput= sdf.parse(dataNasc);
		} catch (Exception e) {

		}

		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNascInput);

		int dia = dateOfBirth.get(Calendar.DAY_OF_MONTH);

		int mes = dateOfBirth.get(Calendar.MONTH)+1; 

		int ano = dateOfBirth.get(Calendar.YEAR);

		//Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		if(dia<32 && dia>0 && mes>0 && mes<=12 && ano>1900 && ano<today.get(Calendar.YEAR)){
			//Obtém a idade baseado no ano
			age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

			dateOfBirth.add(Calendar.YEAR, age);
			if (today.before(dateOfBirth)) {
				age--;
			}	
		}
		return age;
	}
	public int validaDataNascimento(String dtNasc, String dtMatricula){//Se retorna 1, data de nascimento inválida
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascimento = null;
		Date dataMatricula = null;

		try {
			dataNascimento= sdf.parse(dtNasc);
			dataMatricula= sdf.parse(dtMatricula);
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataNascimento);
		date2.setTime(dataMatricula);

		return x;
	}
	public int validaData(String data){
		int x = 0;		

		int dia = Integer.parseInt(data.substring(0, 2));

		int mes = Integer.parseInt(data.substring(3, 5));

		if(dia>31 || dia<1){
			x=1;
		}
		if(mes>12 || mes<1){
			x=1;
		}
		return x;
	}
	public void ajustaDados(String txtFuncionario, String txtNmFuncionario, String txtFuncao, String txtSalario, String txtDoc, String txtTelefone, String txtEmail, boolean chkValeTransporte, boolean chkValeRefeicao, String txtDependentes, boolean chkValeFamilia, String sexo, String txtObs) {
		this.txtFuncionario = txtFuncionario.trim();
		this.txtNmFuncionario = txtNmFuncionario.trim();
		this.txtFuncao = txtFuncao.trim();
		this.txtDoc = txtDoc.trim();
		this.txtTelefone = txtTelefone.trim();
		this.txtEmail = txtEmail.trim();
		this.chkValeTransporte = chkValeTransporte;
		this.chkValeRefeicao = chkValeRefeicao;
		this.chkValeFamilia = chkValeFamilia;
		this.sexo = sexo.trim();
		this.txtObs = txtObs.trim();
	}
	public int verificaExiste(){
		CadastroFuncionario cadastrar = new CadastroFuncionario();
		return cadastrar.verificaExiste(getTxtNmFuncionario(), getTxtDoc());
	}
	public String novoFuncionario(){
		CadastroFuncionario cadastrar = new CadastroFuncionario();
		String novo = "";
		if(cadastrar.geraCodFuncionario()>100){
			novo = "func"+cadastrar.geraCodFuncionario();
		}else{
			if(cadastrar.geraCodFuncionario()>=10){
				novo = "func0"+cadastrar.geraCodFuncionario();	
			}else{
				novo = "func00"+cadastrar.geraCodFuncionario();
			}
		}	
		return novo;
	}
	public int validaDadosSimples(){
		if(getTxtNmFuncionario().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome do funcionário inválido";
		}
		if(getTxtFuncao().equals("")){
			this.status +=1;
			this.msgStatus +="\nFunção do funcionário inválido";
		}
		if(getTxtDoc().equals("")){
			this.status +=1;
			this.msgStatus +="\nDocumento do funcionário inválido";
		}
		if(getTxtTelefone().equals("(  )    -")){
			this.status +=1;
			this.msgStatus +="\nTelefone do funcionário inválido";
		}
		if(getTxtEmail().equals("")){
			this.status +=1;
			this.msgStatus +="\nE-mail do funcionário inválido";
		}
		if(getSexo().equals("")){
			this.status +=1;
			this.msgStatus +="\nSexo do funcionário inválido";
		}	
		return status;
	}
	public void cadastrarSimples(String caminho){
		CadastroFuncionario cadastrar = new CadastroFuncionario();
		cadastrar.ajustaDados(getTxtFuncionario().trim(), getTxtNmFuncionario().trim(), getTxtFuncao().trim(), getTxtSalario(), getTxtDoc().trim(), getTxtTelefone().trim(), getTxtEmail().trim(), getChkValeTransporte(), getChkValeRefeicao(), getTxtDependentes(), getChkValeFamilia(), getSexo().trim(), getTxtObs().trim());
		cadastrar.cadastrarSimples();
		if(caminho.equals("")){
			Image image = new ImageIcon("temp/semFoto.jpg").getImage();
			redimensionar(image, getTxtFuncionario());
			enviarFoto("temp/semFoto.jpg", getTxtFuncionario().trim()+".jpg");
		}else{
			try {
				copy(caminho, getTxtFuncionario());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			Image image = new ImageIcon("temp/"+getTxtFuncionario()).getImage();
			redimensionar(image, getTxtFuncionario());
			enviarFoto("temp/"+getTxtFuncionario(), getTxtFuncionario().trim()+".jpg");	
		}	
	}
	public void alterarSimples(){
		CadastroFuncionario cadastrar = new CadastroFuncionario();
		cadastrar.ajustaDados(getTxtFuncionario().trim(), getTxtNmFuncionario().trim(), getTxtFuncao().trim(), getTxtSalario(), getTxtDoc().trim(), getTxtTelefone().trim(), getTxtEmail().trim(), getChkValeTransporte(), getChkValeRefeicao(), getTxtDependentes(), getChkValeFamilia(), getSexo().trim(), getTxtObs().trim());
		cadastrar.alterarSimples();
	}
	public void ajustaDados(String txtFuncionario, String txtNmFuncionario, String txtFuncao, String txtSalario, String txtDoc, String txtTelefone, String txtEmail, boolean chkValeTransporte, boolean chkValeRefeicao, String txtDependentes, boolean chkValeFamilia, String sexo, String txtObs, String txtCbo, String txtCtps, String txtDtNascimento, String txtCpf, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelefoneContato) {
		this.txtFuncionario = txtFuncionario.trim();
		this.txtNmFuncionario = txtNmFuncionario.trim();
		this.txtFuncao = txtFuncao.trim();
		this.txtDoc = txtDoc.trim();
		this.txtTelefone = txtTelefone.trim();
		this.txtEmail = txtEmail.trim();
		this.chkValeTransporte = chkValeTransporte;
		this.chkValeRefeicao = chkValeRefeicao;
		this.chkValeFamilia = chkValeFamilia;
		this.sexo = sexo.trim();
		this.txtObs = txtObs.trim();
		this.txtCbo = txtCbo.trim();
		this.txtCtps = txtCtps.trim();
		this.txtDtNascimento = txtDtNascimento.trim();
		this.txtCpf = txtCpf.trim();
		this.txtEndereco = txtEndereco.trim();
		this.txtEnderecoNumero = txtEnderecoNumero.trim();
		this.txtEnderecoComplemento = txtEnderecoComplemento.trim();
		this.txtEnderecoBairro = txtEnderecoBairro.trim();
		this.txtEnderecoCidade = txtEnderecoCidade.trim();
		this.cmbEnderecoEstado = cmbEnderecoEstado.trim();
		this.txtEnderecoCep = txtEnderecoCep.trim();
		this.txtTelefoneContato = txtTelefoneContato.trim();
	}
	public int validaDadosCompleto(){
		if(getTxtNmFuncionario().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome do funcionário inválido";
		}
		if(getTxtFuncao().equals("")){
			this.status +=1;
			this.msgStatus +="\nFunção do funcionário inválido";
		}
		if(getTxtDoc().equals("")){
			this.status +=1;
			this.msgStatus +="\nDocumento do funcionário inválido";
		}
		if(getTxtDtNascimento().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData de nascimento do funcionário inválido";
		}else{
			if(validaData(getTxtDtNascimento())>0){
				this.status +=1;
				this.msgStatus +="\nData de nascimento do funcionário inválido";
			}	
		}		
		if(getTxtTelefone().equals("(  )    -")){
			this.status +=1;
			this.msgStatus +="\nTelefone do funcionário inválido";
		}
		if(getTxtEmail().equals("")){
			this.status +=1;
			this.msgStatus +="\nE-mail do funcionário inválido";
		}
		if(getSexo().equals("")){
			this.status +=1;
			this.msgStatus +="\nSexo do funcionário inválido";
		}
		if(getTxtCpf().equals(".   .   -")){
			this.status +=1;
			this.msgStatus +="\nCPF do funcionário inválido";
		}
		if(getTxtEndereco().equals("")){
			this.status +=1;
			this.msgStatus +="\nEndereço do funcionário inválido";
		}
		if(getTxtEnderecoNumero().equals("")){
			this.status +=1;
			this.msgStatus +="\nNúmero do endereço do funcionário inválido";
		}
		if(getTxtEnderecoCidade().equals("")){
			this.status +=1;
			this.msgStatus +="\nCidade do endereço do funcionário inválido";
		}
		if(getCmbEnderecoEstado().equals("--")){
			this.status +=1;
			this.msgStatus +="\nEstado do endereço do funcionário inválido";
		}
		if(getTxtEnderecoCep().equals(".   -")){
			this.status +=1;
			this.msgStatus +="\nCEP do endereço do funcionário inválido";
		}
		return status;
	}	
	public void cadastrarCompleto(String caminho){
		CadastroFuncionario cadastrar = new CadastroFuncionario();
		cadastrar.ajustaDados(getTxtFuncionario().trim(), getTxtNmFuncionario().trim(), getTxtFuncao().trim(), getTxtSalario(), getTxtDoc().trim(), getTxtTelefone().trim(), getTxtEmail().trim(), getChkValeTransporte(), getChkValeRefeicao(), getTxtDependentes(), getChkValeFamilia(), getSexo().trim(), getTxtObs().trim(), getTxtCbo().trim(), getTxtCtps().trim(), getTxtDtNascimento().trim(), getTxtCpf().trim(), getTxtEndereco().trim(), getTxtEnderecoNumero().trim(), getTxtEnderecoComplemento().trim(), getTxtEnderecoBairro().trim(), getTxtEnderecoCidade().trim(), getCmbEnderecoEstado().trim(), getTxtEnderecoCep().trim(), getTxtTelefoneContato().trim());
		cadastrar.cadastrarCompleto();
		if(caminho.equals("")){
			Image image = new ImageIcon("temp/"+getTxtFuncionario()).getImage();
			redimensionar(image, getTxtFuncionario());
			enviarFoto("temp/semFoto", getTxtFuncionario().trim()+".jpg");
		}else{
			try {
				copy(caminho, getTxtFuncionario());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			Image image = new ImageIcon("temp/"+getTxtFuncionario()).getImage();
			redimensionar(image, getTxtFuncionario());
			enviarFoto("temp/"+getTxtFuncionario(), getTxtFuncionario().trim()+".jpg");	
		}	
	}
	public void alterarCompleto(){
		CadastroFuncionario cadastrar = new CadastroFuncionario();
		cadastrar.ajustaDados(getTxtFuncionario().trim(), getTxtNmFuncionario().trim(), getTxtFuncao().trim(), getTxtSalario(), getTxtDoc().trim(), getTxtTelefone().trim(), getTxtEmail().trim(), getChkValeTransporte(), getChkValeRefeicao(), getTxtDependentes(), getChkValeFamilia(), getSexo().trim(), getTxtObs().trim(), getTxtCbo().trim(), getTxtCtps().trim(), getTxtDtNascimento().trim(), getTxtCpf().trim(), getTxtEndereco().trim(), getTxtEnderecoNumero().trim(), getTxtEnderecoComplemento().trim(), getTxtEnderecoBairro().trim(), getTxtEnderecoCidade().trim(), getCmbEnderecoEstado().trim(), getTxtEnderecoCep().trim(), getTxtTelefoneContato().trim());
		cadastrar.alterarCompleto();
	}
	DefaultTableModel tabelaFuncionarios = new DefaultTableModel();

	public DefaultTableModel getTabelaFuncionarios(){
		return this.tabelaFuncionarios;
	}
	public void consultarFuncionarios(String matricula, String nome){
		CadastroFuncionario consultar = new CadastroFuncionario();
		consultar.consultar(matricula, nome);
		this.registros = consultar.getRegistros();
		this.tabelaFuncionarios = consultar.getTabelaFuncionarios();
	}
	public void consultarFuncionario(String matricula, String tipo){
		CadastroFuncionario consultar = new CadastroFuncionario();
		consultar.consultarFunc(matricula, tipo);
		this.txtFuncionario = consultar.getTxtFuncionario();
		this.txtNmFuncionario = consultar.getTxtNmFuncionario();
		this.txtFuncao = consultar.getTxtFuncao();
		if(consultar.getTxtSalario()<1000){
			if(consultar.getTxtSalario()<100){
				if(consultar.getTxtSalario()<10){
					String x = (String) (Double.toString(consultar.getTxtSalario())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtSalario())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.vlSalario = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtSalario())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtSalario())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.vlSalario = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtSalario())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtSalario())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.vlSalario = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtSalario())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtSalario())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.vlSalario = z;
		}
		this.txtDoc = consultar.getTxtDoc();
		this.txtTelefone = consultar.getTxtTelefone();
		this.txtEmail = consultar.getTxtEmail();
		this.chkValeTransporte = consultar.getChkValeTransporte();
		this.chkValeRefeicao = consultar.getChkValeRefeicao();
		if(consultar.getTxtDependentes()<100){
			if(consultar.getTxtDependentes()<10){
				this.qtDependentes = 0+""+0+(String) (Double.toString(consultar.getTxtDependentes())).substring(0);	
			}else{
				this.qtDependentes = 0+(String) (Double.toString(consultar.getTxtDependentes())).subSequence(0, 2);
			}			
		}
		this.chkValeFamilia = consultar.getChkValeFamilia();
		this.sexo = consultar.getSexo();
		this.txtObs = consultar.getTxtObs();
		this.txtCbo = consultar.getTxtCbo();
		this.txtCtps = consultar.getTxtCtps();
		this.txtDtNascimento = consultar.getTxtDtNascimento();
		this.txtCpf = consultar.getTxtCpf();
		this.txtEndereco = consultar.getTxtEndereco();
		this.txtEnderecoNumero = consultar.getTxtEnderecoNumero();
		this.txtEnderecoComplemento = consultar.getTxtEnderecoComplemento();
		this.txtEnderecoBairro = consultar.getTxtEnderecoBairro();
		this.txtEnderecoCidade = consultar.getTxtEnderecoCidade();
		this.cmbEnderecoEstado = consultar.getCmbEnderecoEstado();
		this.txtEnderecoCep = consultar.getTxtEnderecoCep();
		this.txtTelefoneContato = consultar.getTxtTelefoneContato();
	}
	public void enviarFoto(String caminho, String nmFoto){
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect( "ftp.pesquerorepresentacoes.com.br" );
			//verifica se conectou com sucesso!
			if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {
				ftp.login( "pesquerorepresentaco", "ftp@adm@" );
			} else {
				//erro ao se conectar
				ftp.disconnect();
				System.exit(1);
			}
			//abre um stream com o arquivo a ser enviado
			InputStream is = new FileInputStream( caminho );
			ftp.setFileType( FTPClient.BINARY_FILE_TYPE );
			ftp.changeWorkingDirectory("/public_html/quemsomos/equipe/");
			//faz o envio do arquivo
			ftp.storeFile( nmFoto, is );

			ftp.disconnect();
		} catch( Exception e ) {
			JOptionPane.showMessageDialog(null,"Erro ao tentar enviar imagem de exibição de funcionário. Tente novamente","Erro",2);
		}
	}
	//Método para redimensionar imagens (criar thubmnails)
	private void redimensionar(Image image, String nomeImagem) {
		int width = 122; // Lagura da miniatura
		int height = 163; // Altuta da miniatura
		int quality = 80; // Qualidade da imagem [0~100]

		// Calculos necessários para manter as propoçoes da imagem, conhecido
		// como "aspect ratio"
		double thumbRatio = (double) width / (double) height;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);

		double imageRatio = (double) imageWidth / (double) imageHeight;

		if (thumbRatio < imageRatio) {
			height = (int) (width / imageRatio);
		} else {
			width = (int) (height * imageRatio);
		}
		// Fim do cálculo

		BufferedImage thumbImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		graphics2D.drawImage(image, 0, 0, width, height, null);

		BufferedOutputStream out;

		try {
			out = new BufferedOutputStream(new FileOutputStream("temp/"+nomeImagem));
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
			quality = Math.max(0, Math.min(quality, 100));
			param.setQuality((float) quality / 100.0f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(thumbImage);
			out.close();
		} catch (FileNotFoundException e) {
			
		} catch (ImageFormatException e) {
			
		} catch (IOException e) {
			
		}
	}
	public void copy(String nmCaminho, String nmDestino)
	throws IOException {
		File fromFile = new File(nmCaminho);
		File toFile = new File("temp/"+nmDestino);

		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(fromFile);
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1)to.write(buffer, 0, bytesRead); // write
		} finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					;
				}
				if (to != null)
					try {
						to.close();
					} catch (IOException e) {
						;
					}
		}
	}
	public void remover(String matricula, String nmFuncionario){
		CadastroFuncionario cadastrar = new CadastroFuncionario();
		cadastrar.remover(matricula, nmFuncionario);
	}
	public JasperViewer gerarRelatorio(String matricula, String nome){
		CadastroFuncionario gerar = new CadastroFuncionario();
		//exibe o resultado		
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio(matricula, nome) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioResumido(){
		CadastroFuncionario gerar = new CadastroFuncionario();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioResumido() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
