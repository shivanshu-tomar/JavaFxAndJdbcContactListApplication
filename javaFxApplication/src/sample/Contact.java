package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Contact {
     StringProperty name;
     private StringProperty mob;
     private StringProperty Sno;
     private StringProperty bucks;

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMob() {
        return mob.get();
    }

    public StringProperty mobProperty() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob.set(mob);
    }

    public String getSno() {
        return Sno.get();
    }

    public StringProperty snoProperty() {
        return Sno;
    }

    public void setSno(String sno) {
        this.Sno.set(sno);
    }

    public String getBucks() {
        return bucks.get();
    }

    public StringProperty bucksProperty() {
        return bucks;
    }

    public void setBucks(String bucks) {
        this.bucks.set(bucks);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + name +
                ", mob=" + mob +
                ", Sno=" + Sno +
                ", bucks=" + bucks +
                '}';
    }

    public Contact(StringProperty name, StringProperty mob, StringProperty sno, StringProperty bucks) {
        this.name = name;
        this.mob = mob;
        Sno = sno;
        this.bucks = bucks;
    }
}
