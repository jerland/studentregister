package modell;

public class Studied {
String ccode;
String spnr;
String grade;

public Studied (String spnr, String ccode, String grade) {
	setCcode(ccode);
	setSpnr(spnr);
	setGrade(grade);
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
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
}
