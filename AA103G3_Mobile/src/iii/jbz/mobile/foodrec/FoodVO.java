package iii.jbz.mobile.foodrec;
import java.io.Serializable;
import java.sql.Date;

public class FoodVO implements Serializable{
	private Integer 	fd_no;
	private String		fd_name;
	private Integer 	fd_spone;
	private Integer 	fd_sptwo;
	private Integer 	fd_spthr;
	private Integer 	fd_spfor;
	private Integer 	fd_spfir;
	private Integer 	fd_spsix;
	private Integer 	prot;
	private Integer 	fat;
	private Integer 	mono;
	private Integer 	poly;
	private Integer 	sfa;
	private Integer 	trans;
	private Integer 	cho;
	private Integer 	carb;
	private Integer 	sugar;
	private Integer 	df;
	private Integer 	na;
	private Integer 	ca;
	private Integer 	k;
	private Integer 	cal;
	
	public FoodVO() {
		super();
	}
	public FoodVO(Integer fd_no, String fd_name, Integer fd_spone, Integer fd_sptwo, Integer fd_spthr, Integer fd_spfor,
			Integer fd_spfir, Integer fd_spsix, Integer prot, Integer fat, Integer mono, Integer poly, Integer sfa,
			Integer trans, Integer cho, Integer carb, Integer sugar, Integer df, Integer na, Integer ca, Integer k,
			Integer cal) {
		super();
		this.fd_no = fd_no;
		this.fd_name = fd_name;
		this.fd_spone = fd_spone;
		this.fd_sptwo = fd_sptwo;
		this.fd_spthr = fd_spthr;
		this.fd_spfor = fd_spfor;
		this.fd_spfir = fd_spfir;
		this.fd_spsix = fd_spsix;
		this.prot = prot;
		this.fat = fat;
		this.mono = mono;
		this.poly = poly;
		this.sfa = sfa;
		this.trans = trans;
		this.cho = cho;
		this.carb = carb;
		this.sugar = sugar;
		this.df = df;
		this.na = na;
		this.ca = ca;
		this.k = k;
		this.cal = cal;
	}
	public Integer getFd_no() {
		return fd_no;
	}
	public void setFd_no(Integer fd_no) {
		this.fd_no = fd_no;
	}
	public String getFd_name() {
		return fd_name;
	}
	public void setFd_name(String fd_name) {
		this.fd_name = fd_name;
	}
	public Integer getFd_spone() {
		return fd_spone;
	}
	public void setFd_spone(Integer fd_spone) {
		this.fd_spone = fd_spone;
	}
	public Integer getFd_sptwo() {
		return fd_sptwo;
	}
	public void setFd_sptwo(Integer fd_sptwo) {
		this.fd_sptwo = fd_sptwo;
	}
	public Integer getFd_spthr() {
		return fd_spthr;
	}
	public void setFd_spthr(Integer fd_spthr) {
		this.fd_spthr = fd_spthr;
	}
	public Integer getFd_spfor() {
		return fd_spfor;
	}
	public void setFd_spfor(Integer fd_spfor) {
		this.fd_spfor = fd_spfor;
	}
	public Integer getFd_spfir() {
		return fd_spfir;
	}
	public void setFd_spfir(Integer fd_spfir) {
		this.fd_spfir = fd_spfir;
	}
	public Integer getFd_spsix() {
		return fd_spsix;
	}
	public void setFd_spsix(Integer fd_spsix) {
		this.fd_spsix = fd_spsix;
	}
	public Integer getProt() {
		return prot;
	}
	public void setProt(Integer prot) {
		this.prot = prot;
	}
	public Integer getFat() {
		return fat;
	}
	public void setFat(Integer fat) {
		this.fat = fat;
	}
	public Integer getMono() {
		return mono;
	}
	public void setMono(Integer mono) {
		this.mono = mono;
	}
	public Integer getPoly() {
		return poly;
	}
	public void setPoly(Integer poly) {
		this.poly = poly;
	}
	public Integer getSfa() {
		return sfa;
	}
	public void setSfa(Integer sfa) {
		this.sfa = sfa;
	}
	public Integer getTrans() {
		return trans;
	}
	public void setTrans(Integer trans) {
		this.trans = trans;
	}
	public Integer getCho() {
		return cho;
	}
	public void setCho(Integer cho) {
		this.cho = cho;
	}
	public Integer getCarb() {
		return carb;
	}
	public void setCarb(Integer carb) {
		this.carb = carb;
	}
	public Integer getSugar() {
		return sugar;
	}
	public void setSugar(Integer sugar) {
		this.sugar = sugar;
	}
	public Integer getDf() {
		return df;
	}
	public void setDf(Integer df) {
		this.df = df;
	}
	public Integer getNa() {
		return na;
	}
	public void setNa(Integer na) {
		this.na = na;
	}
	public Integer getCa() {
		return ca;
	}
	public void setCa(Integer ca) {
		this.ca = ca;
	}
	public Integer getK() {
		return k;
	}
	public void setK(Integer k) {
		this.k = k;
	}
	public Integer getCal() {
		return cal;
	}
	public void setCal(Integer cal) {
		this.cal = cal;
	}
	
}
