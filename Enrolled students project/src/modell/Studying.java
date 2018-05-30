package modell;

public class Studying {
String ccode;
String spnr;

public Studying (String ccode, String spnr) {
	setCcode(ccode);
	setSpnr(spnr);
}

public String getCcode() {
	return ccode;
}
public void setCcode(String ccode) {
	this.ccode = ccode;
}
public String getSpnr() {
	return spnr;
}
public void setSpnr(String spnr) {
	this.spnr = spnr;
}
}
