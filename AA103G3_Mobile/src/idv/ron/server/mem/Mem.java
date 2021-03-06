package idv.ron.server.mem;

import java.sql.Timestamp;

import oracle.sql.CHAR;



public class Mem 
{
	private Integer memno;
	private String id;
	private String acc;
	private String pwd;
	private String name;
	private String sex;
	private Timestamp bir;
	private Integer height;
	private Integer weight;
	private String phone;
	private String email;
	private String act_type;
	private String sos1;
	private String sos2;
	private String sos3;
	private String blood;
	private String sugar;
	private String oil;
	private String bone;
	private String breathe;
	private String heart;
	private String stom;
	private String cpt;
	private String regId;
	private byte[] photo;



	public Mem(Integer memno, String id, String acc, String pwd, String name, String sex, Timestamp bir, Integer height,
			Integer weight, String phone, String email, String act_type, String sos1, String sos2, String sos3,
			String blood, String sugar, String oil, String bone, String breathe, String heart, String stom, String cpt,
			String regId) {
		super();
		this.memno = memno;
		this.id = id;
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
		this.sex = sex;
		this.bir = bir;
		this.height = height;
		this.weight = weight;
		this.phone = phone;
		this.email = email;
		this.act_type = act_type;
		this.sos1 = sos1;
		this.sos2 = sos2;
		this.sos3 = sos3;
		this.blood = blood;
		this.sugar = sugar;
		this.oil = oil;
		this.bone = bone;
		this.breathe = breathe;
		this.heart = heart;
		this.stom = stom;
		this.cpt = cpt;
		this.regId = regId;
	}

	public Mem(int memno, String id, String acc, String pwd,
			String name, String sex,Timestamp bir,Integer height,Integer weight
			,String phone,String email,String act_type,String sos1,String sos2,String sos3
			,String blood,String sugar,String oil,String bone,String breathe,String heart
			,String stom,String cpt, String regId, byte[] photo) 
	{
		this.memno = memno;
		this.id= id;
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
		this.sex = sex;
		this.bir = bir;
		this.height = height;
		this.weight = weight;
		this.phone = phone;
		this.email = email;
		this.act_type = act_type;
		this.sos1 = sos1;
		this.sos2 = sos2;
		this.sos3 = sos3;
		this.blood = blood;
		this.sugar = sugar;
		this.oil = oil;
		this.bone = bone;
		this.breathe = breathe;
		this.heart = heart;
		this.stom = stom;
		this.cpt = cpt;
		this.regId = regId;
		this.photo = photo;
	}

	public Integer getMemno() {
		return memno;
	}

	public void setMemno(Integer memno) {
		this.memno = memno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getBir() {
		return bir;
	}

	public void setBir(Timestamp bir) {
		this.bir = bir;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAct_type() {
		return act_type;
	}

	public void setAct_type(String act_type) {
		this.act_type = act_type;
	}

	public String getSos1() {
		return sos1;
	}

	public void setSos1(String sos1) {
		this.sos1 = sos1;
	}

	public String getSos2() {
		return sos2;
	}

	public void setSos2(String sos2) {
		this.sos2 = sos2;
	}

	public String getSos3() {
		return sos3;
	}

	public void setSos3(String sos3) {
		this.sos3 = sos3;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public String getBone() {
		return bone;
	}

	public void setBone(String bone) {
		this.bone = bone;
	}

	public String getBreathe() {
		return breathe;
	}

	public void setBreathe(String breathe) {
		this.breathe = breathe;
	}

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getStom() {
		return stom;
	}

	public void setStom(String stom) {
		this.stom = stom;
	}
	public String getCpt() {
	        return cpt;
	    }

	public void setCpt(String cpt) {
	        this.cpt = cpt;
	    }
	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
