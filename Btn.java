package MayinGUI;

import javax.swing.JButton;

public class Btn extends JButton {
	private int satir, sutun, sayac;
	private boolean mayin, bayrak;

	public Btn(int satir, int sutun) {
		this.satir = satir;
		this.sutun = sutun;
		this.mayin = false;
		this.bayrak = false;
		this.sayac = 0;
	}

	public int getSayac() {
		return sayac;
	}

	public void setSayac(int sayac) {
		this.sayac = sayac;
	}

	public int getSatir() {
		return satir;
	}

	public void setSatir(int satir) {
		this.satir = satir;
	}

	public int getSutun() {
		return sutun;
	}

	public void setSutun(int sutun) {
		this.sutun = sutun;
	}

	public boolean isMayin() {
		return mayin;
	}

	public void setMayin(boolean mayin) {
		this.mayin = mayin;
	}

	public boolean isBayrak() {
		return bayrak;
	}

	public void setBayrak(boolean bayrak) {
		this.bayrak = bayrak;
	}

}
