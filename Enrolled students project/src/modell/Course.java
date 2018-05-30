package modell;

public class Course {
private String ccode;
private String cName;
private String points;

public Course(String ccode, String cName, String points) {
	setCcode(ccode);
	setcName(cName);
	setPoints(points);
}

public String getCcode() {
	return ccode;
}
public void setCcode(String ccode) {
	this.ccode = ccode;
}
public String getcName() {
	return cName;
}
public void setcName(String name) {
	this.cName = name;
}
public String getPoints() {
	return points;
}
public void setPoints(String points) {
	this.points = points;
}

}
