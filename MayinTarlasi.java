package MayinGUI;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MayinTarlasi implements MouseListener {
	JFrame frame;
	Btn[][] board = new Btn[10][10];// oyuncun gordugu
	int open;

	public MayinTarlasi() {
		frame = new JFrame("Mayin Tarlasi");
		frame.setLayout(new GridLayout(10, 10));
		open = 0;
		for (int i = 0; i < board.length; i++) {// frame button ekleme
			for (int j = 0; j < board[0].length; j++) {
				Btn b = new Btn(i, j);
				board[i][j] = b;
				b.addMouseListener(this);
				frame.add(b);
			}
		}
		bomb();
		check();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void bomb() {// bomba yerlestirme
		int i = 0, bombsatir, bombsutun;
		int size = board.length * board[0].length;
		while ((size / 4) > i) {
			bombsatir = (int) (Math.random() * board.length);
			bombsutun = (int) (Math.random() * board[0].length);
			while (board[bombsatir][bombsutun].isMayin()) {
				bombsatir = (int) (Math.random() * board.length);
				bombsutun = (int) (Math.random() * board[0].length);
			}
			board[bombsatir][bombsutun].setMayin(true);
			i++;
		}
	}

	public void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isMayin()) {
					board[i][j].setIcon(new ImageIcon(MayinTarlasi.class.getResource("/MayinGUI/mayin.png")));
				} else {// Bu biraz çakalik setText sadece string yazar ama biz int ile str("")
					board[i][j].setText(board[i][j].getSayac() + " "); // toplatip sanki degerimiz str gibi gösteriyoruz
					board[i][j].setEnabled(false);
				}
			}
		}
	}

	public void check() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isMayin()) {
					circle(i, j);
				}
			}
		}
	}

	public void circle(int i, int j) {// tiklanan noktanin etrafini kontrol eder
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				try {// uc noktalarda bug olmasini engelliyor
					int value = board[x][y].getSayac();
					board[x][y].setSayac(++value);
				} catch (Exception e) {
				}
			}
		}
	}

	public void open(int r, int c) {// kutularin acilmasi
		if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c].getText().length() > 0
				|| board[r][c].isEnabled() == false) {
			return;
		} else if (board[r][c].getSayac() != 0) {
			board[r][c].setText(board[r][c].getSayac() + "");
			board[r][c].setEnabled(false);
			open++;
		} else {
			open++;
			board[r][c].setEnabled(false);
			open(r - 1, c);
			open(r + 1, c);
			open(r, c - 1);
			open(r, c + 1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 1 sol /2 orta /3 sag click
		Btn b = (Btn) e.getComponent();
		int size = board.length * board[0].length;
		if (e.getButton() == 1) {
			if (b.isMayin()) {
				JOptionPane.showMessageDialog(frame, "Mayina Bastiniz!!!!!!!", "Öldün!!!", JOptionPane.WARNING_MESSAGE);
				print();
			} else {
				open(b.getSatir(), b.getSutun());
				if (open == (size - (size / 4))) {
					JOptionPane.showMessageDialog(frame, "Kazandiniz!!!!", "Winn!!!", JOptionPane.WARNING_MESSAGE);
					print();
				}
			}
			System.out.println("sol");
		} else if (e.getButton() == 3) {
			System.out.println("sag");
			if (!b.isBayrak()) {
				b.setIcon(new ImageIcon(MayinTarlasi.class.getResource("/MayinGUI/bayrak.png")));
				b.setBayrak(true);
			} else {
				b.setIcon(null);
				b.setBayrak(false);
			}
		}
	}

	public void mayinprint() {// hile sadece mayinlari gosteriyor
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isMayin()) {
					board[i][j].setIcon(new ImageIcon(MayinTarlasi.class.getResource("/MayinGUI/mayin.png")));
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
