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

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import persistencia.CadastroImovel;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Imovel {

	public Imovel() {
		super();
	}	

	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;

	//Campos do cadastro	
	public String getNmImovel() {
		return nmImovel;
	}
	public String getNmEndereco() {
		return nmEndereco;
	}
	public String getNmBairro() {
		return nmBairro;
	}
	public String getNmCidade() {
		return nmCidade;
	}
	public String getNmTipo() {
		return nmTipo;
	}
	public String getNmTamanho() {
		return nmTamanho;
	}
	public String getDormitorios() {
		return dormitorios;
	}
	public String getDtInicio() {
		return dtInicio;
	}
	public String getDtEntrega() {
		return dtEntrega;
	}
	public String getDsImovel() {
		return dsImovel;
	}
	public String getVlImovel() {
		return vlImovel;
	}
	public String getDsFormaPagamento() {
		return dsFormaPagamento;
	}
	public String getDsFoto1() {
		return dsFoto1;
	}
	public String getDsFoto2() {
		return dsFoto2;
	}
	public String getDsFoto3() {
		return dsFoto3;
	}
	public String getDsFoto4() {
		return dsFoto4;
	}
	public String getDsFoto5() {
		return dsFoto5;
	}
	public String getCdImovel(){
		return cdImovel;
	}
	public String getSgEstado(){
		return sgEstado;
	}


	@SuppressWarnings("null")
	public String geraCdImovel(){
		CadastroImovel cadastrar = null;
		return "imovel"+cadastrar.geraCodImovel();
	}

	private String cdImovel;

	private String nmImovel;

	private String nmEndereco;

	private String nmBairro;

	private String nmCidade;

	private String sgEstado;

	private String nmTipo;

	private String nmTamanho;

	private String dormitorios;

	private String dtInicio;

	private String dtEntrega;

	private String dsImovel;

	private String vlImovel;

	private String dsFormaPagamento;

	private String dsFoto1;

	private String dsFoto2;

	private String dsFoto3;

	private String dsFoto4;

	private String dsFoto5;

	public String getMsgStatus(){
		return msgStatus;
	}
	public int registros(){
		return registros;
	}

	public int validaDataEntrega(String dtInicio, String dtEntrega){//Se retorna 1, data de entrega inválida
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataInicio = null;
		Date dataEntrega = null;

		try {
			dataInicio= sdf.parse(dtInicio);
			dataEntrega= sdf.parse(dtEntrega);
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataInicio);
		date2.setTime(dataEntrega);

		if (dataEntrega.before(dataInicio)) {
			x = 1;
		}

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
	public void ajustaDados(String txtCdImovel, String txtNmImovel, String txtNmEndereco, String txtNmBairro, String txtNmCidade, String cmbSgEstado, String cmbTipo, String txtNmTamanho, String txtDormitorios, String txtDtInicio, String txtDtEntrega, String txtDsImovel, String txtvlImovel, String txtDsFormaPagamento, String txtDsFoto1, String txtDsFoto2, String txtDsFoto3, String txtDsFoto4, String txtDsFoto5) {
		this.cdImovel = txtCdImovel;
		this.nmImovel = txtNmImovel;
		this.nmEndereco = txtNmEndereco;
		this.nmBairro = txtNmBairro;
		this.nmCidade = txtNmCidade;
		this.sgEstado = cmbSgEstado;
		this.nmTipo = cmbTipo;
		this.nmTamanho = txtNmTamanho;
		this.dormitorios = txtDormitorios;
		this.dtInicio = txtDtInicio;
		this.dtEntrega = txtDtEntrega;
		this.dsImovel = txtDsImovel;
		this.vlImovel = txtvlImovel;
		this.dsFormaPagamento = txtDsFormaPagamento;
		this.dsFoto1 = txtDsFoto1;
		this.dsFoto2 = txtDsFoto2;
		this.dsFoto3 = txtDsFoto3;
		this.dsFoto4 = txtDsFoto4;
		this.dsFoto5 = txtDsFoto5;
	}
	public int verificaExiste(){
		CadastroImovel cadastrar = new CadastroImovel();
		return cadastrar.verificaExiste(getNmImovel(), getCdImovel());
	}
	public String novoImovel(){
		CadastroImovel cadastrar = new CadastroImovel();
		String novo = "";
		if(cadastrar.geraCodImovel()>100){
			novo = "imovel"+cadastrar.geraCodImovel();
		}else{
			if(cadastrar.geraCodImovel()>=10){
				novo = "imovel0"+cadastrar.geraCodImovel();	
			}else{
				novo = "imovel00"+cadastrar.geraCodImovel();
			}
		}	
		return novo;
	}
	public int validaDados(){
		if(getNmImovel().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome do imóvel inválido";
		}
		if(getNmEndereco().equals("")){
			this.status +=1;
			this.msgStatus +="\nEndereço do imóvel inválido";
		}
		if(getNmBairro().equals("")){
			this.status +=1;
			this.msgStatus +="\nBairro do endereço do imóvel inválido";
		}
		if(getNmCidade().equals("")){
			this.status +=1;
			this.msgStatus +="\nCidade do endereço do imóvel inválido";
		}
		if(getSgEstado().equals("")){
			this.status +=1;
			this.msgStatus +="\nEstado do endereço do imóvel inválido";
		}
		if(getNmTipo().equals("")){
			this.status +=1;
			this.msgStatus +="\nTipo do imóvel inválido";
		}
		if(getNmTamanho().equals("")){
			this.status +=1;
			this.msgStatus +="\nTamanho do imóvel inválido";
		}
		if(getDormitorios().equals("")){
			this.status +=1;
			this.msgStatus +="\nQuantidade de dormitórios do imóvel inválido";
		}
		if(getDtInicio().equals("  /  /    ")){
			this.status +=1;
			this.msgStatus +="\nData de início da construção do imóvel inválido";
		}
		if(getDtEntrega().equals("  /  /    ")){
			this.status +=1;
			this.msgStatus +="\nData de entrega do imóvel inválido";
		}
		if(getDsImovel().equals("")){
			this.status +=1;
			this.msgStatus +="\nDescrição do imóvel inválido";
		}	
		return status;
	}
	public void cadastrar(){
		CadastroImovel cadastrar = new CadastroImovel();
		cadastrar.ajustaDados(getCdImovel(), getNmImovel(), getNmEndereco(), getNmBairro(), getNmCidade(), getSgEstado(), getNmTipo(), getNmTamanho(), getDormitorios(), getDtInicio(), getDtEntrega(), getDsImovel(), getVlImovel(), getDsFormaPagamento(), getDsFoto1(), getDsFoto2(), getDsFoto3(), getDsFoto4(), getDsFoto5());
		cadastrar.cadastrar();
		if(!getDsFoto1().equals("")){
			cadastrarFoto(getDsFoto1(), getCdImovel()+"a");
		}
		if(!getDsFoto2().equals("")){
			cadastrarFoto(getDsFoto2(), getCdImovel()+"b");
		}
		if(!getDsFoto3().equals("")){
			cadastrarFoto(getDsFoto3(), getCdImovel()+"c");
		}
		if(!getDsFoto4().equals("")){
			cadastrarFoto(getDsFoto4(), getCdImovel()+"d");
		}
		if(!getDsFoto5().equals("")){
			cadastrarFoto(getDsFoto5(), getCdImovel()+"e");
		}
	}
	public void cadastrarFoto(String caminho, String referencia){
		if(caminho.equals("")){

		}else{
			try {
				//copy(caminho, getCdImovel());
				redimensionar(caminho, getCdImovel());
				enviarFoto("temp/"+referencia.substring(0, referencia.length()-1), referencia.trim()+".jpg");
			} catch (Exception e) {

			}

		}
	}
	public void alterar(){
		CadastroImovel cadastrar = new CadastroImovel();
		cadastrar.ajustaDados(getCdImovel(), getNmImovel(), getNmEndereco(), getNmBairro(), getNmCidade(), getSgEstado(), getNmTipo(), getNmTamanho(), getDormitorios(), getDtInicio(), getDtEntrega(), getDsImovel(), getVlImovel(), getDsFormaPagamento(), getDsFoto1(), getDsFoto2(), getDsFoto3(), getDsFoto4(), getDsFoto5());
		cadastrar.alterar(getCdImovel());
		if(!getDsFoto1().equals("")){
			if(!getDsFoto1().equals("imagens/"+getCdImovel()+"a.jpg")){
				cadastrarFoto(getDsFoto1(), getCdImovel()+"a");	
			}			
		}
		if(!getDsFoto2().equals("")){
			if(!getDsFoto2().equals("imagens/"+getCdImovel()+"b.jpg")){
				cadastrarFoto(getDsFoto2(), getCdImovel()+"b");	
			}
		}
		if(!getDsFoto3().equals("")){
			if(!getDsFoto3().equals("imagens/"+getCdImovel()+"c.jpg")){
				cadastrarFoto(getDsFoto3(), getCdImovel()+"c");	
			}
		}
		if(!getDsFoto4().equals("")){
			if(!getDsFoto4().equals("imagens/"+getCdImovel()+"d.jpg")){
				cadastrarFoto(getDsFoto4(), getCdImovel()+"d");	
			}
		}
		if(!getDsFoto5().equals("")){
			if(!getDsFoto5().equals("imagens/"+getCdImovel()+"e.jpg")){
				cadastrarFoto(getDsFoto5(), getCdImovel()+"e");	
			}
		}		
	}
	public void ajustarFotos(String foto1, String foto2, String foto3, String foto4, String foto5){
		this.dsFoto1 = foto1;
		this.dsFoto2 = foto2;
		this.dsFoto3 = foto3;
		this.dsFoto4 = foto4;
		this.dsFoto4 = foto5;
		System.out.println("ajustarFotos");
		System.out.println(getDsFoto1());
		System.out.println(getDsFoto2());
		System.out.println("------");
	}
	DefaultTableModel tabelaImoveis = new DefaultTableModel();

	public DefaultTableModel getTabelaImoveis(){
		return this.tabelaImoveis;
	}
	public void consultarImoveis(String matricula, String nome, String tipo){
		CadastroImovel consultar = new CadastroImovel();
		consultar.consultar(matricula, nome, tipo);
		this.registros = consultar.getRegistros();
		this.tabelaImoveis = consultar.getTabelaImoveis();
	}
	public void consultarImovel(String matricula){
		CadastroImovel consulta = new CadastroImovel();
		consulta.consultarImovel(matricula);
		this.cdImovel = consulta.getCdImovel();
		this.nmImovel = consulta.getNmImovel();
		this.nmEndereco = consulta.getNmEndereco();
		this.nmBairro = consulta.getNmBairro();
		this.nmCidade = consulta.getNmCidade();
		this.sgEstado = consulta.getSgEstado();
		this.nmTipo = consulta.getNmTipo();
		this.nmTamanho = consulta.getNmTamanho();
		this.dormitorios = consulta.getDormitorios();
		this.dtInicio = consulta.getDtInicio();
		this.dtEntrega = consulta.getDtEntrega();
		this.dsImovel = consulta.getDsImovel();
		this.vlImovel = consulta.getVlImovel();
		this.dsFormaPagamento = consulta.getDsFormaPagamento();
		this.dsFoto1 = consulta.getDsFoto1();
		this.dsFoto2 = consulta.getDsFoto2();
		this.dsFoto3 = consulta.getDsFoto3();
		this.dsFoto4 = consulta.getDsFoto4();
		this.dsFoto5 = consulta.getDsFoto5();	
	}
	public void enviarFoto(String caminho, String nmFoto){
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect( "ftp.pesqueroconstrutora.com.br" );
			//verifica se conectou com sucesso!
			if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {
				ftp.login( "pesqueroconstrutora", "pci@adm@" );
			} else {
				//erro ao se conectar
				ftp.disconnect();
				System.exit(1);
			}
			//abre um stream com o arquivo a ser enviado
			InputStream is = new FileInputStream(caminho);
			ftp.setFileType( FTPClient.BINARY_FILE_TYPE );
			ftp.changeWorkingDirectory("/public_html/imoveis/imagens/");
			//faz o envio do arquivo
			ftp.storeFile( nmFoto, is );

			ftp.disconnect();
		} catch( Exception e ) {
			JOptionPane.showMessageDialog(null,"Erro ao tentar enviar foto de exibição do imóvel. Tente novamente","Erro",2);
		}
	}
	//Mï¿½todo para redimensionar imagens (criar thubmnails)
	private void redimensionar(String caminho, String nomeImagem) {
		int width = 610; // Lagura da miniatura
		int height = 457; // Altuta da miniatura
		int quality = 80; // Qualidade da imagem [0~100]

		Image image = new ImageIcon(caminho).getImage();
		// Calculos necessï¿½rios para manter as propoï¿½oes da imagem, conhecido
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
		// Fim do cï¿½lculo

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
			to = new FileOutputStream(toFile, false);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1)to.write(buffer, 0, bytesRead); // write
			
			
		}catch(Exception e){

		}
		finally {
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
		CadastroImovel cadastrar = new CadastroImovel();
		cadastrar.remover(matricula);
	}
}