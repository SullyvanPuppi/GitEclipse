package negocios;

import java.util.Calendar;
import net.sf.jasperreports.view.JasperViewer;

import persistencia.CadastroAliquotasBeneficios;

public class AliquotasBeneficios {

	private int status = 0;
	private String msgStatus = "";

	private double txtVlTransporte;
	private double txtVlRefeicao;
	private double txtVlFgts;
	private double txtVlInss1;
	private double txtVlInss2;
	private double txtVlInss3;
	private double txtVlInss1P;
	private double txtVlInss2P;
	private double txtVlInss3P;
	private double txtVlInssTeto;
	private double txtVlIr1;
	private double txtVlIr2;
	private double txtVlIr3;	
	private double txtVlIr1P;
	private double txtVlIr2P;
	private double txtVlIr3P;
	private double txtVlIrDed1;
	private double txtVlIrDed2;
	private double txtVlIrDed3;
	private double txtVlDependentes;
	private double txtVlFamilia1;
	private double txtVlFamilia2;
	private double txtVlFamilia1P;
	private double txtVlFamilia2P;

	private String VlTransporte;
	private String VlRefeicao;
	private String VlFgts;
	private String VlInss1;
	private String VlInss2;
	private String VlInss3;
	private String VlInssTeto;
	private String VlIr1;
	private String VlIr2;
	private String VlIr3;
	private String VlDependentes;
	private String VlFamilia1;
	private String VlFamilia2;
	private String VlInss1P;
	private String VlInss2P;
	private String VlInss3P;
	private String VlIr1P;
	private String VlIr2P;
	private String VlIr3P;
	private String VlIrDed1;
	private String VlIrDed2;
	private String VlIrDed3;
	private String VlFamilia1P;
	private String VlFamilia2P;

	private String atualizado;

	public AliquotasBeneficios(){
		super();
	}



	public int getStatus() {
		return status;
	}



	public String getVlIr3() {
		return VlIr3;
	}



	public String getVlIrDed3() {
		return VlIrDed3;
	}



	public double getTxtVlIr3() {
		return txtVlIr3;
	}

	public double getTxtVlIr3P() {
		return txtVlIr3P;
	}

	public double getTxtVlIrDed1() {
		return txtVlIrDed1;
	}

	public double getTxtVlIrDed2() {
		return txtVlIrDed2;
	}

	public double getTxtVlIrDed3() {
		return txtVlIrDed3;
	}

	public String getVlFamilia1P() {
		return VlFamilia1P;
	}

	public String getVlFamilia2P() {
		return VlFamilia2P;
	}

	public String getVlInss1P() {
		return VlInss1P;
	}

	public String getVlInss2P() {
		return VlInss2P;
	}

	public String getVlInss3P() {
		return VlInss3P;
	}
	public String getVlInssTeto() {
		return VlInssTeto;
	}
	public String getVlIr1P() {
		return VlIr1P;
	}
	public String getVlIr2P() {
		return VlIr2P;
	}

	public double getTxtVlFamilia1P() {
		return txtVlFamilia1P;
	}

	public double getTxtVlFamilia2P() {
		return txtVlFamilia2P;
	}

	public double getTxtVlInss1P() {
		return txtVlInss1P;
	}

	public double getTxtVlInss2P() {
		return txtVlInss2P;
	}

	public double getTxtVlInss3P() {
		return txtVlInss3P;
	}
	public double getTxtVlInssTeto() {
		return txtVlInssTeto;
	}

	public double getTxtVlIr1P() {
		return txtVlIr1P;
	}

	public double getTxtVlIr2P() {
		return txtVlIr2P;
	}

	public double getTxtVlDependentes() {
		return txtVlDependentes;
	}
	public double getTxtVlFamilia1() {
		return txtVlFamilia1;
	}
	public double getTxtVlFamilia2() {
		return txtVlFamilia2;
	}
	public double getTxtVlFgts() {
		return txtVlFgts;
	}
	public double getTxtVlInss1() {
		return txtVlInss1;
	}
	public double getTxtVlInss2() {
		return txtVlInss2;
	}
	public double getTxtVlInss3() {
		return txtVlInss3;
	}
	public double getTxtVlIr1() {
		return txtVlIr1;
	}
	public double getTxtVlIr2() {
		return txtVlIr2;
	}
	public double getTxtVlRefeicao() {
		return txtVlRefeicao;
	}
	public double getTxtVlTransporte() {
		return txtVlTransporte;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public String getAtualizado(){
		return atualizado;
	}	
	public String getVlDependentes() {
		return VlDependentes;
	}

	public String getVlFamilia1() {
		return VlFamilia1;
	}

	public String getVlFamilia2() {
		return VlFamilia2;
	}

	public String getVlFgts() {
		return VlFgts;
	}

	public String getVlInss1() {
		return VlInss1;
	}

	public String getVlInss2() {
		return VlInss2;
	}

	public String getVlInss3() {
		return VlInss3;
	}

	public String getVlIr1() {
		return VlIr1;
	}

	public String getVlIr2() {
		return VlIr2;
	}

	public String getVlRefeicao() {
		return VlRefeicao;
	}

	public String getVlTransporte() {
		return VlTransporte;
	}
	public int validar(String txtVlTransporte, String txtVlRefeicao, String txtVlFgts, String txtVlInss1, String txtVlInss2, String txtVlInss3, String txtVlIr1, String txtVlIr2, String txtVlDependentes, String txtVlFamilia1, String txtVlFamilia2, String vlInss1P, String vlInss2P, String vlInss3P, String vlIr1P, String vlIr2P, String vlFamilia1P, String vlFamilia2P, String txtVlIr3, String vlIr3P, String vlIrDed1, String vlIrDed2, String vlIrDed3, String txtVlInssTeto){
		if(txtVlTransporte.equals("%")){
			this.status +=1;
			this.msgStatus += "\nValor do vale transporte inválido";
		}else{
			this.txtVlTransporte = Double.parseDouble((String) txtVlTransporte.subSequence(0, 3));
			if(getTxtVlTransporte()>100){
				this.status +=1;
				this.msgStatus += "\nValor do vale transporte inválido";	
			}
		}
		if(txtVlRefeicao.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor do vale refeição inválido";
		}else{
			String x = txtVlRefeicao.substring(2,6);
			String y = txtVlRefeicao.substring(txtVlRefeicao.lastIndexOf(","));
			String a = y.substring(1);
			String z = x+"."+a;
			this.txtVlRefeicao = Double.parseDouble(z);
		}
		if(txtVlFgts.equals("%")){
			this.status +=1;
			this.msgStatus += "\nValor do fundo de garantia inválido";
		}else{
			this.txtVlFgts = Double.parseDouble((String) txtVlFgts.subSequence(0, 3));
			if(getTxtVlFgts()>100){
				this.status +=1;
				this.msgStatus += "\nValor do fundo de garantia inválido";
			}
		}
		if(txtVlInss1.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 1 do INSS inválido";
		}else{
			if(vlInss1P.equals("%")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 1 do INSS inválido";	
			}else{
				String x = txtVlInss1.substring(2,6);
				String y = txtVlInss1.substring(txtVlInss1.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlInss1 = Double.parseDouble(z);
				if(vlInss1P.equals("%")){
					this.status +=1;
					this.msgStatus += "\nValor da faixa 1 do INSS inválido";
				}else{
					this.txtVlInss1P = Double.parseDouble((String) vlInss1P.subSequence(0, 3));
					if(getTxtVlInss1P()>100){
						this.status +=1;
						this.msgStatus += "\nValor da faixa 1 do INSS inválido";
					}
				}
			}			
		}
		if(txtVlInss2.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 2 do INSS inválido";
		}else{
			if(vlInss2P.equals("%")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 2 do INSS inválido";	
			}else{
				String x = txtVlInss2.substring(2,6);
				String y = txtVlInss2.substring(txtVlInss2.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlInss2 = Double.parseDouble(z);	
				this.txtVlInss2P = Double.parseDouble((String) vlInss2P.subSequence(0, 3));
				if(getTxtVlInss2P()>100){
					this.status +=1;
					this.msgStatus += "\nValor da faixa 2 do INSS inválido";
				}
			}			
		}
		if(txtVlInss3.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 3 do INSS inválido";
		}else{
			if(vlInss3P.equals("%")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 3 do INSS inválido";	
			}else{
				String x = txtVlInss3.substring(2,6);
				String y = txtVlInss3.substring(txtVlInss3.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlInss3 = Double.parseDouble(z);	
				this.txtVlInss3P = Double.parseDouble((String) vlInss3P.subSequence(0, 3));
				if(getTxtVlInss3P()>100){
					this.status +=1;
					this.msgStatus += "\nValor da faixa 3 do INSS inválido";
				}
			}			
		}
		if(txtVlInssTeto.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor do teto máximo de contribuição ao INSS inválido";
		}else{
			String x = txtVlInssTeto.substring(2,6);
			String y = txtVlInssTeto.substring(txtVlInssTeto.lastIndexOf(","));
			String a = y.substring(1);
			String z = x+"."+a;
			this.txtVlInssTeto = Double.parseDouble(z);
		}
		if(txtVlIr1.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 1 do IR inválido";
		}else{
			if(vlIr1P.equals("%")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 1 do IR inválido";	
			}else{
				String x = txtVlIr1.substring(2,6);
				String y = txtVlIr1.substring(txtVlIr1.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlIr1 = Double.parseDouble(z);	
				this.txtVlIr1P = Double.parseDouble((String) vlIr1P.subSequence(0, 3));
				if(getTxtVlIr1P()>100){
					this.status +=1;
					this.msgStatus += "\nValor da faixa 1 do IR inválido";	
				}
			}			
		}
		if(vlIrDed1.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor do valor de dedução da faixa 1 do IR inválido";
		}else{
			String x = vlIrDed1.substring(2,6);
			String y = vlIrDed1.substring(vlIrDed1.lastIndexOf(","));
			String a = y.substring(1);
			String z = x+"."+a;
			this.txtVlIrDed1 = Double.parseDouble(z);
		}
		if(txtVlIr2.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 2 do IR inválido";
		}else{
			if(vlIr2P.equals("%")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 2 do IR inválido";	
			}else{
				String x = txtVlIr2.substring(2,6);
				String y = txtVlIr2.substring(txtVlIr2.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlIr2 = Double.parseDouble(z);	
				this.txtVlIr2P = Double.parseDouble((String) vlIr2P.subSequence(0, 3));
				if(getTxtVlIr2P()>100){
					this.status +=1;
					this.msgStatus += "\nValor da faixa 2 do IR inválido";	
				}
			}
			if(vlIrDed2.equals("R$    ,")){
				this.status +=1;
				this.msgStatus += "\nValor do valor de dedução da faixa 2 do IR inválido";
			}else{
				String x = vlIrDed2.substring(2,6);
				String y = vlIrDed2.substring(vlIrDed2.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlIrDed2 = Double.parseDouble(z);
			}
		}
		if(txtVlIr3.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 3 do IR inválido";
		}else{
			if(vlIr3P.equals("%")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 3 do IR inválido";	
			}else{
				String x = txtVlIr3.substring(2,6);
				String y = txtVlIr3.substring(txtVlIr3.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlIr3 = Double.parseDouble(z);	
				this.txtVlIr3P = Double.parseDouble((String) vlIr3P.subSequence(0, 3));
				if(getTxtVlIr2P()>100){
					this.status +=1;
					this.msgStatus += "\nValor da faixa 3 do IR inválido";	
				}
			}
			if(vlIrDed3.equals("R$    ,")){
				this.status +=1;
				this.msgStatus += "\nValor do valor de dedução da faixa 3 do IR inválido";
			}else{
				String x = vlIrDed3.substring(2,6);
				String y = vlIrDed3.substring(vlIrDed3.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlIrDed3 = Double.parseDouble(z);
			}
		}
		if(txtVlDependentes.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da da dedução por dependente inválido";
		}else{
			String x = txtVlDependentes.substring(2,6);
			String y = txtVlDependentes.substring(txtVlDependentes.lastIndexOf(","));
			String a = y.substring(1);
			String z = x+"."+a;
			this.txtVlDependentes = Double.parseDouble(z);
		}
		if(txtVlFamilia1.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 1 do salário família inválido";
		}else{
			if(vlFamilia1P.equals("R$    ,")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 1 do salário família inválido";
			}else{
				String x = txtVlFamilia1.substring(2,6);
				String y = txtVlFamilia1.substring(txtVlFamilia1.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlFamilia1 = Double.parseDouble(z);
				String q = vlFamilia1P.substring(2,6);
				String w = vlFamilia1P.substring(vlFamilia1P.lastIndexOf(","));
				String e = w.substring(1);
				String r = q+"."+e;
				this.txtVlFamilia1P = Double.parseDouble(r);
			}			
		}
		if(txtVlFamilia2.equals("R$    ,")){
			this.status +=1;
			this.msgStatus += "\nValor da faixa 2 do salário família inválido";
		}else{
			if(vlFamilia2P.equals("R$    ,")){
				this.status +=1;
				this.msgStatus += "\nValor da faixa 2 do salário família inválido";
			}else{
				String x = txtVlFamilia2.substring(2,6);
				String y = txtVlFamilia2.substring(txtVlFamilia2.lastIndexOf(","));
				String a = y.substring(1);
				String z = x+"."+a;
				this.txtVlFamilia2 = Double.parseDouble(z);	
				String q = vlFamilia2P.substring(2,6);
				String w = vlFamilia2P.substring(vlFamilia2P.lastIndexOf(","));
				String e = w.substring(1);
				String r = q+"."+e;
				this.txtVlFamilia2P = Double.parseDouble(r);
			}			
		}
		return status;
	}
	public String getHoje(){
		String data = "";
		//Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		int dia = today.get(Calendar.DAY_OF_MONTH);

		int mes = today.get(Calendar.MONTH)+1; 

		int ano = today.get(Calendar.YEAR);

		if(dia<10){
			if(mes<10){
				data = "0"+dia+"0"+mes+ano;
			}else{
				data = "0"+dia+mes+ano;
			}
		}else{
			if(mes<10){
				data = ""+dia+"0"+mes+ano;
			}else{
				data = ""+dia+mes+ano;
			}
		}

		return data;
	}	
	public int verificaExiste(){
		CadastroAliquotasBeneficios cadastrar = new CadastroAliquotasBeneficios();
		return cadastrar.verificaExiste();
	}
	public void cadastrar(){
		CadastroAliquotasBeneficios cadastrar = new CadastroAliquotasBeneficios();
		cadastrar.ajustaDados(getTxtVlTransporte(), getTxtVlRefeicao(), getTxtVlFgts(), getTxtVlInss1(), getTxtVlInss2(), getTxtVlInss3(), getTxtVlIr1(), getTxtVlIr2(), getTxtVlDependentes(), getTxtVlFamilia1(), getTxtVlFamilia2(), getHoje(), getTxtVlInss1P(), getTxtVlInss2P(), getTxtVlInss3P(), getTxtVlIr1P(), getTxtVlIr2P(), getTxtVlFamilia1P(), getTxtVlFamilia2P(), getTxtVlIr3(), getTxtVlIr3P(), getTxtVlIrDed1(), getTxtVlIrDed2(), getTxtVlIrDed3(), getTxtVlInssTeto());
		cadastrar.cadastrar();
	}
	public void alterar(){
		CadastroAliquotasBeneficios cadastrar = new CadastroAliquotasBeneficios();
		cadastrar.ajustaDados(getTxtVlTransporte(), getTxtVlRefeicao(), getTxtVlFgts(), getTxtVlInss1(), getTxtVlInss2(), getTxtVlInss3(), getTxtVlIr1(), getTxtVlIr2(), getTxtVlDependentes(), getTxtVlFamilia1(), getTxtVlFamilia2(), getHoje(), getTxtVlInss1P(), getTxtVlInss2P(), getTxtVlInss3P(), getTxtVlIr1P(), getTxtVlIr2P(), getTxtVlFamilia1P(), getTxtVlFamilia2P(), getTxtVlIr3(), getTxtVlIr3P(), getTxtVlIrDed1(), getTxtVlIrDed2(), getTxtVlIrDed3(), getTxtVlInssTeto());
		cadastrar.alterar();
	}
	public void consultar(){
		CadastroAliquotasBeneficios consultar = new CadastroAliquotasBeneficios();
		consultar.consultar();
		if(consultar.getTxtVlTransporte()<100){
			if(consultar.getTxtVlTransporte()<10){
				this.VlTransporte = 0+""+0+(String) (Double.toString(consultar.getTxtVlTransporte())).substring(0);	
			}else{
				this.VlTransporte = 0+(String) (Double.toString(consultar.getTxtVlTransporte())).subSequence(0, 2);
			}			
		}
		if(consultar.getTxtVlRefeicao()<1000){
			if(consultar.getTxtVlRefeicao()<100){
				if(consultar.getTxtVlRefeicao()<10){
					String x = (String) (Double.toString(consultar.getTxtVlRefeicao())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlRefeicao())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlRefeicao = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlRefeicao())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlRefeicao())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlRefeicao = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlRefeicao())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlRefeicao())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlRefeicao = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlRefeicao())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlRefeicao())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlRefeicao = z;
		}
		if(consultar.getTxtVlFgts()<100){
			if(consultar.getTxtVlFgts()<10){
				this.VlFgts = 0+""+0+(String) (Double.toString(consultar.getTxtVlFgts())).substring(0);
			}else{
				this.VlFgts = 0+(String) (Double.toString(consultar.getTxtVlFgts())).subSequence(0, 2);
			}
		}		
		//----INSS 1
		if(consultar.getTxtVlInss1()<1000){
			if(consultar.getTxtVlInss1()<100){
				if(consultar.getTxtVlInss1()<10){
					String x = (String) (Double.toString(consultar.getTxtVlInss1())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlInss1())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlInss1 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlInss1())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlInss1())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlInss1 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlInss1())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlInss1())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlInss1 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlInss1())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlInss1())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlInss1 = z;
		}
		if(consultar.getTxtVlInss1P()<100){
			if(consultar.getTxtVlInss1P()<10){
				this.VlInss1P = 0+""+0+(String) (Double.toString(consultar.getTxtVlInss1P())).subSequence(0, 0);
			}else{
				this.VlInss1P = 0+(String) (Double.toString(consultar.getTxtVlInss1P())).subSequence(0, 2);
			}			
		}
		//----INSS 2
		if(consultar.getTxtVlInss2()<1000){
			if(consultar.getTxtVlInss2()<100){
				if(consultar.getTxtVlInss2()<10){
					String x = (String) (Double.toString(consultar.getTxtVlInss2())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlInss2())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlInss2 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlInss2())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlInss2())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlInss2 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlInss2())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlInss2())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlInss2 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlInss2())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlInss2())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlInss2 = z;
		}
		if(consultar.getTxtVlInss2P()<100){
			if(consultar.getTxtVlInss2P()<10){
				this.VlInss2P = 0+""+0+(String) (Double.toString(consultar.getTxtVlInss2P())).subSequence(0, 0);
			}else{
				this.VlInss2P = 0+(String) (Double.toString(consultar.getTxtVlInss2P())).subSequence(0, 2);
			}			
		}
		//----INSS 3
		if(consultar.getTxtVlInss3()<1000){
			if(consultar.getTxtVlInss3()<100){
				if(consultar.getTxtVlInss3()<10){
					String x = (String) (Double.toString(consultar.getTxtVlInss3())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlInss3())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlInss3 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlInss3())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlInss3())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlInss3 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlInss3())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlInss3())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlInss3 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlInss3())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlInss3())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlInss3 = z;
		}
		if(consultar.getTxtVlInss3P()<100){
			if(consultar.getTxtVlInss3P()<10){
				this.VlInss3P = 0+""+0+(String) (Double.toString(consultar.getTxtVlInss3P())).subSequence(0, 0);
			}else{
				this.VlInss3P = 0+(String) (Double.toString(consultar.getTxtVlInss3P())).subSequence(0, 2);
			}			
		}
		//----Inss Teto
		if(consultar.getTxtVlInssTeto()<1000){
			if(consultar.getTxtVlInssTeto()<100){
				if(consultar.getTxtVlInssTeto()<10){
					String x = (String) (Double.toString(consultar.getTxtVlInssTeto())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlInssTeto())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlInssTeto = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlInssTeto())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlInssTeto())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlInssTeto = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlInssTeto())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlInssTeto())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlInssTeto = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlInssTeto())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlInssTeto())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlInssTeto = z;
		}
		//----IR 1
		if(consultar.getTxtVlIr1()<1000){
			if(consultar.getTxtVlIr1()<100){
				if(consultar.getTxtVlIr1()<10){
					String x = (String) (Double.toString(consultar.getTxtVlIr1())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlIr1())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlIr1 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlIr1())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlIr1())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlIr1 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlIr1())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlIr1())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlIr1 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlIr1())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlIr1())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlIr1 = z;
		}
		if(consultar.getTxtVlIr1Ded()<1000){
			if(consultar.getTxtVlIr1Ded()<100){
				if(consultar.getTxtVlIr1Ded()<10){
					String x = (String) (Double.toString(consultar.getTxtVlIr1Ded())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlIr1Ded())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlIrDed1 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlIr1Ded())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlIr1Ded())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlIrDed1 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlIr1Ded())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlIr1Ded())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlIrDed1 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlIr1Ded())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlIr1Ded())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlIrDed1 = z;
		}
		if(consultar.getTxtVlIr1P()<100){
			if(consultar.getTxtVlIr1P()<10){
				this.VlIr1P = 0+""+0+(String) (Double.toString(consultar.getTxtVlIr1P())).subSequence(0, 0);
			}else{
				this.VlIr1P = 0+(String) (Double.toString(consultar.getTxtVlIr1P())).subSequence(0, 2);
			}			
		}
		//----IR 2
		if(consultar.getTxtVlIr2()<1000){
			if(consultar.getTxtVlIr2()<100){
				if(consultar.getTxtVlIr2()<10){
					String x = (String) (Double.toString(consultar.getTxtVlIr2())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlIr2())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlIr2 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlIr2())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlIr2())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlIr2 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlIr2())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlIr2())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlIr2 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlIr2())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlIr2())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlIr2 = z;
		}
		if(consultar.getTxtVlIr2Ded()<1000){
			if(consultar.getTxtVlIr2Ded()<100){
				if(consultar.getTxtVlIr2Ded()<10){
					String x = (String) (Double.toString(consultar.getTxtVlIr2Ded())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlIr2Ded())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlIrDed2 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlIr2Ded())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlIr2Ded())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlIrDed2 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlIr2Ded())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlIr2Ded())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlIrDed2 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlIr2Ded())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlIr2Ded())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlIrDed2 = z;
		}
		if(consultar.getTxtVlIr2P()<100){
			if(consultar.getTxtVlIr2P()<10){
				this.VlIr2P = 0+""+0+(String) (Double.toString(consultar.getTxtVlIr2P())).subSequence(0, 0);
			}else{
				this.VlIr2P = 0+(String) (Double.toString(consultar.getTxtVlIr2P())).subSequence(0, 2);
			}			
		}
		//--Ir3
		if(consultar.getTxtVlIr3()<1000){
			if(consultar.getTxtVlIr3()<100){
				if(consultar.getTxtVlIr3()<10){
					String x = (String) (Double.toString(consultar.getTxtVlIr3())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlIr3())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlIr3 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlIr3())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlIr3())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlIr3 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlIr3())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlIr3())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlIr3 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlIr3())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlIr3())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlIr3 = z;
		}
		if(consultar.getTxtVlIr3Ded()<1000){
			if(consultar.getTxtVlIr3Ded()<100){
				if(consultar.getTxtVlIr3Ded()<10){
					String x = (String) (Double.toString(consultar.getTxtVlIr3Ded())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlIr3Ded())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlIrDed3 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlIr3Ded())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlIr3Ded())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlIrDed3 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlIr3Ded())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlIr3Ded())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlIrDed3 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlIr3Ded())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlIr3Ded())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlIrDed3 = z;
		}
		if(consultar.getTxtVlIr3P()<100){
			if(consultar.getTxtVlIr3P()<10){
				this.VlIr3P = 0+""+0+(String) (Double.toString(consultar.getTxtVlIr3P())).subSequence(0, 0);
			}else{
				this.VlIr3P = 0+(String) (Double.toString(consultar.getTxtVlIr3P())).subSequence(0, 2);
			}			
		}
		//----Familia 1
		if(consultar.getTxtVlFamilia1()<1000){
			if(consultar.getTxtVlFamilia1()<100){
				if(consultar.getTxtVlIr2()<10){
					String x = (String) (Double.toString(consultar.getTxtVlFamilia1())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia1())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlFamilia1 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlFamilia1())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia1())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlFamilia1 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlFamilia1())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlFamilia1())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlFamilia1 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlFamilia1())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlFamilia1())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlFamilia1 = z;
		}
		if(consultar.getTxtVlFamilia1P()<1000){
			if(consultar.getTxtVlFamilia1P()<100){
				if(consultar.getTxtVlFamilia1P()<10){
					String x = (String) (Double.toString(consultar.getTxtVlFamilia1P())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia1P())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlFamilia1P = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlFamilia1P())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia1P())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlFamilia1P = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlFamilia1P())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlFamilia1P())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlFamilia1P = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlFamilia1P())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlFamilia1P())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlFamilia1P = z;
		}
		//----Familia 2
		if(consultar.getTxtVlFamilia2()<1000){
			if(consultar.getTxtVlFamilia2()<100){
				if(consultar.getTxtVlFamilia2()<10){
					String x = (String) (Double.toString(consultar.getTxtVlFamilia2())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia2())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlFamilia2 = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlFamilia2())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia2())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlFamilia2 = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlFamilia2())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlFamilia2())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlFamilia2 = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlFamilia2())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlFamilia2())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlFamilia2 = z;
		}
		if(consultar.getTxtVlFamilia2P()<1000){
			if(consultar.getTxtVlFamilia2P()<100){
				if(consultar.getTxtVlIr2()<10){
					String x = (String) (Double.toString(consultar.getTxtVlFamilia2P())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia2P())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlFamilia2P = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlFamilia2P())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlFamilia2P())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlFamilia2P = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlFamilia2P())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlFamilia2P())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlFamilia2P = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlFamilia2P())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlFamilia2P())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlFamilia2P = z;
		}
		//--Dedução de dependentes
		if(consultar.getTxtVlDependentes()<1000){
			if(consultar.getTxtVlDependentes()<100){
				if(consultar.getTxtVlDependentes()<10){
					String x = (String) (Double.toString(consultar.getTxtVlDependentes())).subSequence(0, 1);
					String y = (String) (Double.toString(consultar.getTxtVlDependentes())).substring(2);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+""+0+x+","+y;
					this.VlDependentes = z;
				}else{
					String x = (String) (Double.toString(consultar.getTxtVlDependentes())).subSequence(0, 2);
					String y = (String) (Double.toString(consultar.getTxtVlDependentes())).substring(3);
					if(Double.parseDouble(y)<10){
						y+=""+0;
					}
					String z = 0+""+0+x+","+y;
					this.VlDependentes = z;
				}
			}else{
				String x = (String) (Double.toString(consultar.getTxtVlDependentes())).subSequence(0, 3);
				String y = (String) (Double.toString(consultar.getTxtVlDependentes())).substring(4);
				if(Double.parseDouble(y)<10){
					y+=""+0;
				}
				String z = 0+x+","+y;
				this.VlDependentes = z;
			}
		}else{
			String x = (String) (Double.toString(consultar.getTxtVlDependentes())).subSequence(0, 4);
			String y = (String) (Double.toString(consultar.getTxtVlDependentes())).substring(5);
			if(Double.parseDouble(y)<10){
				y+=""+0;
			}
			String z = x+","+y;
			this.VlDependentes = z;
		}
		String dia = (String) consultar.getAtualizado().subSequence(0, 2);
		String mes = (String) consultar.getAtualizado().subSequence(2, 4);
		String ano = (String) consultar.getAtualizado().subSequence(4, 8);
		this.atualizado = dia+"/"+mes+"/"+ano;	
	}

	public String getVlIr3P() {
		return VlIr3P;
	}

	public void setVlIr3P(String vlIr3P) {
		VlIr3P = vlIr3P;
	}

	public String getVlIrDed1() {
		return VlIrDed1;
	}

	public void setVlIrDed1(String vlIrDed1) {
		VlIrDed1 = vlIrDed1;
	}

	public String getVlIrDed2() {
		return VlIrDed2;
	}

	public void setVlIrDed2(String vlIrDed2) {
		VlIrDed2 = vlIrDed2;
	}
	public JasperViewer gerarRelatorio(){
		CadastroAliquotasBeneficios gerar = new CadastroAliquotasBeneficios();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
