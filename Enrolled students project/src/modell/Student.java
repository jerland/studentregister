package modell;

public class Student {
private String spnr;
private String sname;
private String sadress;

public Student (String spnr, String sname, String sAdress){
	setSpnr(spnr);
	setsName(sname);
	setsAdress(sadress);
}
	
public String getSpnr() {
	return spnr;
}
public void setSpnr(String spnr) {
	this.spnr = spnr;
}
public String getsName() {
	return sname;
}
public void setsName(String sname) {
	this.sname = sname;
}
public String getsadress() {
	return sadress;
}
public void setsAdress(String sadress) {
	this.sadress = sadress;
}
}
