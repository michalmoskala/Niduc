import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ImageTest extends JFrame implements Serializable, ActionListener {
	/**
	 * <b>serialVersionUID</b> Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	JFrame cards;
	private JMenuBar menuBar;
	private JMenu help, _authors, _ARQ_Algorithms;
	private JMenuItem helpItem, _ARQ_ParityBits, _ARQ_MultiMethod, _ARQ_OwnAlgorithm;

	private JMenuItem[] items = { new JMenuItem("Anna Trębicka"), new JMenuItem("Michał Moskała"),
			new JMenuItem("Paweł Szynal"),

	};

	private JPanel panel;
	static JProgressBar progressBar;

	private final JComboBox<String> selection = new JComboBox<String>();
	private final JComboBox<String> channel = new JComboBox<String>();
	private final JComboBox<Integer> frame = new JComboBox<Integer>();
	private final JComboBox<Integer> howmanyerrors = new JComboBox<Integer>();

	JButton button = new JButton("START");
	JLabel label, label1, label2, label3;

	public ImageTest() {

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1000, 700));

		Container cp = getContentPane();
		cp.add(panel);

		this.setSize(1000, 700); // Domyslnie ustawiam rozmiar głownego okna
		this.setTitle("NIDUC 2"); // nazwa tytuowa programu
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // domyslne

		progressBar = new JProgressBar(0, 100);

		this.channel.addActionListener(this);
		panel.add(channel);
		this.channel.addItem("Dobry");
		this.channel.addItem("Średni");
		this.channel.addItem("Zły");

		this.frame.addActionListener(this);
		panel.add(frame);
		this.frame.addItem(8);
		this.frame.addItem(32);
		this.frame.addItem(64);

		this.howmanyerrors.addActionListener(this);
		panel.add(howmanyerrors);
		this.howmanyerrors.addItem(5);
		this.howmanyerrors.addItem(20);
		this.howmanyerrors.addItem(100);

		this.selection.addActionListener(this);
		panel.add(selection);

		this.selection.addItem("Parity");
		this.selection.addItem("Modulo");
		this.selection.addItem("Muliply");

		// this.selection.addItem(100);
		menuBar = new JMenuBar();
		// Build the first menu.

		help = new JMenu("Pomoc");
		help.getAccessibleContext().setAccessibleDescription("Pomoc");
		help.add(new JLabel());
		menuBar.add(help);

		// Build _authors_menu to menu bar

		_authors = new JMenu("Autorzy");
		_authors.getAccessibleContext().setAccessibleDescription("Autorzy");
		_authors.add(new JLabel());
		menuBar.add(_authors);

		_ARQ_Algorithms = new JMenu("Algorytmy ARQ");
		_ARQ_Algorithms.getAccessibleContext().setAccessibleDescription("Algorytmy ARQ");
		_ARQ_Algorithms.add(new JLabel());
		menuBar.add(_ARQ_Algorithms);

		// a group of JMenuItems

		//
		helpItem = new JMenuItem("");
		helpItem.setText(" Opis programu: ");
		helpItem.isBackgroundSet();
		helpItem.setBackground(Color.cyan);
		helpItem.getAccessibleContext().setAccessibleDescription(null);
		helpItem.addActionListener(this); // this jest naszym słuchaczem
		help.add(helpItem);

		for (int i = 0; i < items.length; i++) {
			items[i].isBackgroundSet();
			items[i].setBackground(Color.LIGHT_GRAY);
			items[i].getAccessibleContext().setAccessibleDescription(null);
		}

		for (int i = 0; i < items.length; i++)
			items[i].addActionListener(this);

		// dodanie opcji do menu "Figury"
		_authors.add(items[0]);
		_authors.add(items[1]);
		_authors.add(items[2]);

		// dodanie opcji do menu "Zapisz"

		_ARQ_ParityBits = new JMenuItem("");
		_ARQ_ParityBits.setText("ARQ: Bit parzystości");
		_ARQ_ParityBits.isBackgroundSet();
		_ARQ_ParityBits.setBackground(Color.LIGHT_GRAY);
		_ARQ_ParityBits.getAccessibleContext().setAccessibleDescription(null);
		_ARQ_ParityBits.addActionListener(this);
		_ARQ_Algorithms.add(_ARQ_ParityBits);

		_ARQ_MultiMethod = new JMenuItem("");
		_ARQ_MultiMethod.setText("ARQ: Metoda mnożnia");
		_ARQ_MultiMethod.isBackgroundSet();
		_ARQ_MultiMethod.setBackground(Color.LIGHT_GRAY);
		_ARQ_MultiMethod.getAccessibleContext().setAccessibleDescription(null);
		_ARQ_MultiMethod.addActionListener(this);
		_ARQ_Algorithms.add(_ARQ_MultiMethod);

		_ARQ_OwnAlgorithm = new JMenuItem("");
		_ARQ_OwnAlgorithm.setText("ARQ: Autorki algorym");
		_ARQ_OwnAlgorithm.isBackgroundSet();
		_ARQ_OwnAlgorithm.setBackground(Color.LIGHT_GRAY);
		_ARQ_OwnAlgorithm.getAccessibleContext().setAccessibleDescription(null);
		_ARQ_OwnAlgorithm.addActionListener(this);
		_ARQ_Algorithms.add(_ARQ_OwnAlgorithm);

		setJMenuBar(menuBar);

		// ______________________________________________

		ImageIcon image = new ImageIcon("ARQ.jpg");
		label = new JLabel("", image, JLabel.CENTER);
		panel.add(label, BorderLayout.CENTER);

		panel.add(button);
		button.addActionListener(this);
		ImageIcon image1 = new ImageIcon("source.jpg");
		label1 = new JLabel("", image1, JLabel.CENTER);

		ImageIcon image2 = new ImageIcon("output.jpg");
		label2 = new JLabel("", image2, JLabel.CENTER);

		ImageIcon image3 = new ImageIcon("outputARQ.jpg");
		label3 = new JLabel("", image3, JLabel.CENTER);

		setVisible(true);

	}

	public static void main(String[] args) {

		new ImageTest();
	}

	static byte[] convertBImageTOByteArray(BufferedImage originalImage, ByteArrayOutputStream byteArrayOutputStream,
			byte[] imageInByte) {

		try {
			ImageIO.write(originalImage, "jpg", byteArrayOutputStream);
			byteArrayOutputStream.flush();
			imageInByte = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imageInByte;
	}

	static String conversionVariableToPolarizationSystem(byte[] imageInByte, String imageInString) {
		String temp;
		int binary;

		for (int i = 0; i < imageInByte.length; i++) {
			binary = imageInByte[i];// konwersja zmiennej do systemu z
									// polaryzacjÂą

			binary = binary + 128;
			temp = Integer.toString(binary, 2);
			while (temp.length() < 8)
				temp = "0" + temp;
			imageInString = imageInString.concat(temp);
		}

		return imageInString;
	}

	static // imageInString=" 11110000111100001111000011110000";
	// beate error rate
	String transmissionOfParity(String parity, char c) {
		double d;
		int bitErrorRate = 0;

		for (int n = 0; n < parity.length(); n++) {
			Random random = new Random();
			d = random.nextDouble();

			if (d > 0.99999) {
				bitErrorRate++;
				parity = parity.substring(0, n);

				if (c == 1)
					parity = parity.concat("0");
				else
					parity = parity.concat("1");

				parity = parity.concat(parity.substring(n + 1, parity.length()));
			}
		}
		System.out.println(bitErrorRate);
		return parity;
	}

	String transmissionOfParity_Modulo(String parity, char c) {
		double d;
		int bitErrorRate = 0;

		for (int n = 0; n < parity.length(); n++) {
			Random random = new Random();
			d = random.nextDouble();

			if (d > 0.99999) {
				bitErrorRate++;
				parity = parity.substring(0, n);

				if (c == 0)
					parity = parity.concat("1");
				else {
					c--;
					String str = Character.toString(c);
					parity = parity.concat(str);
				}
				parity = parity.concat(parity.substring(n + 1, parity.length()));
			}
		}
		System.out.println(bitErrorRate);
		return parity;
	}

	static String createChanelParity(String parity2, int warunek, int partem, String imageFromString, final int ramka,
			char c, int q) {

		String temp2 = new String();
		for (int i = 0; i < warunek; i++) {
			partem = 0;
			if (i < warunek - 1) {
				temp2 = imageFromString.substring((ramka * i) + 1, (ramka * i) + ramka + 1);
				for (int n = 0; n < ramka; n++) {
					c = temp2.charAt(n);
					q = Character.getNumericValue(c);
					if (q == 1)
						partem++;
				}
			} else {
				temp2 = imageFromString.substring((ramka * i) + 1, imageFromString.length());
				for (int n = 0; n < temp2.length(); n++) {
					c = temp2.charAt(n);
					q = Character.getNumericValue(c);
					if (q == 1)
						partem++;
				}
			}

			partem = partem % 2;
			parity2 = parity2.concat(Integer.toString(partem));

		}
		return parity2;
	}

	static String createChanelParity_Modulo(String parity2, int warunek, int partem, String imageFromString,
			final int ramka, char c, int q) {

		String temp2 = new String();
		for (int i = 0; i < warunek; i++) {
			partem = 0;
			if (i < warunek - 1) {
				temp2 = imageFromString.substring((ramka * i) + 1, (ramka * i) + ramka + 1);
				partem = modulus(temp2);
			} else {
				temp2 = imageFromString.substring((ramka * i) + 1, imageFromString.length());
				partem = modulus(temp2);
			}
		

		parity2 = parity2.concat(Integer.toHexString(partem));
		}
		return parity2;
	}

	static String preparingChannelToSending(String imageInString, char c, final double jakosc_kanalu,
			String imageFromString) {
		double val, d;
		String s;
		// TWORZENIE popsutego stringa ... KANAŁ
		for (int n = 1; n < imageInString.length(); n++) {
			System.out.println(n + "/" + imageInString.length());

			c = imageInString.charAt(n);
			val = Character.getNumericValue(c);
			val = val * 5;

			Random random = new Random();

			d = random.nextGaussian();
			d = d / jakosc_kanalu;
			val = val + d;

			if (val > 2.5)
				c = '1';
			if (val <= 2.5)
				c = '0';

			s = Character.toString(c);
			imageFromString = imageFromString.concat(s);

		}
		return imageFromString;
	}

	static short modulus(String s) {

		int a = Integer.parseInt(s, 2);

		a = a % 16;
		short b = (short) a;
		return b;
	}

	static short multipli(String s) {

		int a = Integer.parseInt(s, 2);

		double d = Math.PI;
		d = d * a;
		long l = (long) d;
		double fract = d - l;
		d = fract * 16;
		short b = (short) d;

		return b;
	}

	static short getparity(String s) {
		int a = 0;
		for (int n = 0; n < s.length(); n++) {
			char c = s.charAt(n);
			int q = Character.getNumericValue(c);
			if (q == 1)
				a++;
		}
		a = a % 2;
		short b = (short) a;
		return b;

	}

	static String initParityError(String parity, String parity2) {

		// int errors=0;
		String parityerror = "";
		char c, c2;
		for (int n = 0; n < parity.length(); n++) {
			c = parity.charAt(n);
			c2 = parity2.charAt(n);

			if (c == c2)
				parityerror = parityerror.concat("0");
			else {
				parityerror = parityerror.concat("1");
				// errors++;
			}

		}
		return parityerror;

	}

	static int calcerrors(String parity, String parity2) {

		int errors = 0;
		char c, c2;
		for (int n = 0; n < parity.length(); n++) {
			c = parity.charAt(n);
			c2 = parity2.charAt(n);

			if (c != c2)
				errors++;

		}
		return errors;

	}

	static String getTemperr(final int ramka, String imageInString, final double jakosc_kanalu, int n) {
		char c;
		double val, d;
		String s, temperr = "";
		for (int e = 0; e < ramka; e++) {
			c = imageInString.charAt(ramka * n + e + 1);// ?
			val = Character.getNumericValue(c);
			val = val * 5;

			Random random = new Random();
			d = random.nextGaussian();
			d = d / jakosc_kanalu;
			val = val + d;

			if (val > 2.5)
				c = '1';
			if (val <= 2.5)
				c = '0';

			s = Character.toString(c);
			temperr = temperr.concat(s);
		}
		return temperr;
	}

	static String createSecondaryString(String imageFromString, int n, String temperr, int ramka) {

		String imageFromStringbeta = " ";
		imageFromStringbeta = imageFromString.substring(0, n * ramka + 1);
		imageFromStringbeta = imageFromStringbeta.concat(temperr);
		int a = (n * ramka + 1 + ramka);
		int b = imageFromString.length();
		imageFromStringbeta = imageFromStringbeta.concat(imageFromString.substring(a, b));
		return imageFromStringbeta;
	}

	static String prepareParityStringToTransmission(int warunek, String imageInString, int ramka) {
		int partem = 0;
		String temp;
		String parity = "";
		for (int i = 0; i < warunek; i++) {
			System.out.println(i + "/" + warunek);

			if (i < warunek - 1) {
				temp = imageInString.substring((ramka * i) + 1, (ramka * i) + ramka + 1);

				partem = getparity(temp);

			} else {
				temp = imageInString.substring((ramka * i) + 1, imageInString.length());
				partem = getparity(temp);
			}

			parity = parity.concat(Integer.toString(partem));

		}
		return parity;
	}

	static String prepareParityStringToTransmission_Modulo(int warunek, String imageInString, int ramka) {
		int partem = 0;
		String temp;
		String parity = "";
		for (int i = 0; i < warunek; i++) {
			System.out.println(i + "/" + warunek);

			if (i < warunek - 1) {
				temp = imageInString.substring((ramka * i) + 1, (ramka * i) + ramka + 1);

				partem = modulus(temp);

			} else {
				temp = imageInString.substring((ramka * i) + 1, imageInString.length());
				partem = modulus(temp);
			}

			parity = parity.concat(Integer.toHexString(partem));

		}
		return parity;
	}

	static byte[] createImageFromByte(int warunek, String imageFromString, byte[] imageFromByte) {
		String temp = new String();
		int binary = 0;
		for (int n = 0; n < warunek; n++) {

			// System.out.println(n + "/" + (i-1));
			if (n < warunek - 1) {
				temp = imageFromString.substring((8 * n) + 1, (8 * n) + 9);
			} else {
				temp = imageFromString.substring((8 * n) + 1, (imageFromString.length()));
			}

			try {
				binary = Integer.parseInt(temp, 2);
			} catch (NumberFormatException e) {
				System.err.println(temp.length());
			}

			binary = binary - 128;

			imageFromByte[n] = (byte) binary;

		}
		return imageFromByte;
	}

	static byte[] createImageFromByte2(int i, byte[] imageFromByte2, String imageFromString2) {

		String temp = new String();
		int binary = 0;
		for (int n = 0; n < i; n++) {
			// System.out.println(n + "/" + i);
			temp = imageFromString2.substring((8 * n) + 1, (8 * n) + 9);
			binary = Integer.parseInt(temp, 2);
			binary = binary - 128;
			imageFromByte2[n] = (byte) binary;

		}
		return imageFromByte2;
	}

	static double calculateBitErrorRate(String imageInString, String imageFromString) {
		int ber = 0;
		char c, c2;
		for (int n = 0; n < imageInString.length(); n++) {
			System.out.println(n + "/" + (imageInString.length() - 1));
			c = imageInString.charAt(n);
			c2 = imageFromString.charAt(n);

			if (c != c2)
				ber++;
		}
		double b1 = ber;
		b1 = b1 / imageInString.length();
		b1 = b1 * 100;

		return b1;

	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		String mode = (String) this.selection.getSelectedItem();
		if (source == button && mode == "Parity") {
			try {
				final double jakosc_kanalu = 1.47; // im mniej tym gorszy kanał
													// -
													// od
													// 1.2 do 2; poniżej 1.4
													// może
													// być boguś+
				/*
				 * IDEALNY 1.6 SREDNI 1.54 Zly 1.49
				 * 
				 */
				final int ramka = 8;
				final int errorallowance = 5;// 20,10,5

				int warunek, i, q = 0;
				double b1, b2;
				char c = 0;
				byte[] imageInByte = null;
				String imageInString = " ";
				String imageFromString = " ";
				String imageFromStringbeta = new String();
				String parity = new String();
				String paritybeta = new String();
				String parity2 = new String();
				String parityerror = "";
				String temperr = new String();
				String imageFromString2 = new String();
				int errors = 0;
				int partem = 0;

				BufferedImage originalImage = ImageIO.read(new File("source.jpg"));
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				// convert BufferedImage to byte array

				imageInByte = convertBImageTOByteArray(originalImage, byteArrayOutputStream, imageInByte);

				byte[] imageFromByte = new byte[imageInByte.length];
				byte[] imageFromByte2 = new byte[imageInByte.length];

				// imageInString=" 11110000111100001111000011110000";
				imageInString = conversionVariableToPolarizationSystem(imageInByte, imageInString);

				warunek = imageInString.length() / ramka;

				if (((imageInString.length() % ramka) - 1) != 0)
					warunek++;

				parity = prepareParityStringToTransmission(warunek, imageInString, ramka);

				i = warunek;// NIE RUSZAĆ
				parity = transmissionOfParity(parity, c); // c jest znakiem

				imageFromString = preparingChannelToSending(imageInString, c, jakosc_kanalu, imageFromString);
				imageFromString2 = imageFromString.substring(0);

				warunek = imageFromString.length() / ramka;

				// sprawdzanie niepodzielności i tworzenie podziału na ramki
				if (((imageFromString.length() - 1) % ramka) != 0)
					warunek++;

				// wyliczanie, Porównanie parity i wysylanie poprawek

				do {

					System.out.println(errors);

					parity2 = "";
					parity2 = createChanelParity(parity2, warunek, partem, imageFromString, ramka, c, q);

					errors = 0;
					parityerror = "";

					// pariry error

					parityerror = initParityError(parity, parity2);
					errors = calcerrors(parity, parity2);

					for (int n = 0; n < parityerror.length(); n++) {
						temperr = "";
						c = parityerror.charAt(n);
						q = Character.getNumericValue(c);
						if (q == 1)// jezelibylblad
						{

							temperr = getTemperr(ramka, imageInString, jakosc_kanalu, n); // nowo
																							// pobrane
																							// ramki

							partem = getparity(temperr);

							c = parity.charAt(n);
							q = Character.getNumericValue(c);

							if (partem == q) {

								imageFromStringbeta = createSecondaryString(imageFromString, n, temperr, ramka);

								imageFromString = imageFromStringbeta.substring(0, imageFromStringbeta.length());

								paritybeta = parity2.substring(0, n);

								if (c == 1)
									paritybeta = paritybeta.concat("1");
								else
									paritybeta = paritybeta.concat("0");

								paritybeta = paritybeta.concat(parity2.substring(n + 1, parity2.length()));
								parity2 = paritybeta.substring(0, paritybeta.length());

							}

						}
					}

				} while (errors > errorallowance);

				// DEKODER
				warunek = imageInString.length() / 8;

				imageFromByte = createImageFromByte(warunek, imageFromString, imageFromByte);
				imageFromByte2 = createImageFromByte2(i, imageFromByte2, imageFromString2);

				b1 = calculateBitErrorRate(imageInString, imageFromString);

				b2 = calculateBitErrorRate(imageInString, imageFromString2);

				try (PrintWriter out = new PrintWriter("FROMSTR.txt")) {
					out.println(imageFromString);
				}
				byteArrayOutputStream.close();

				try (PrintWriter out = new PrintWriter("INSTR.txt")) {
					out.println(imageInString);
				}
				byteArrayOutputStream.close();

				System.out.println("ARQ" + b1 + "% \nBEZ ARQ" + b2 + "%");

				InputStream in = new ByteArrayInputStream(imageFromByte2);
				BufferedImage bImageFromConvert = ImageIO.read(in);
				try {
					ImageIO.write(bImageFromConvert, "jpg", new File("output.jpg"));
				} catch (IllegalArgumentException e) {
					System.err.println("Popraw kanał");
				}
				in = new ByteArrayInputStream(imageFromByte);
				bImageFromConvert = ImageIO.read(in);
				ImageIO.write(bImageFromConvert, "jpg", new File("outputARQ.jpg"));

				this.panel.add(label1, BorderLayout.CENTER);
				panel.add(label2, BorderLayout.CENTER);
				panel.add(label3, BorderLayout.CENTER);
				panel.revalidate();
				panel.repaint();

				System.out.println("Gotowe");

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println("XD");
			}
		}

		if (source == button && mode == "Modulo") {
			try {
				final double jakosc_kanalu = 1.45; // im mniej tym gorszy kanał
													// -
													// od
													// 1.2 do 2; poniżej 1.4
													// może
													// być boguś+
				/*
				 * IDEALNY 1.6 SREDNI 1.54 Zly 1.49
				 * 
				 */
				final int ramka = 8;
				final int errorallowance = 5;// 20,10,5

				int warunek, i, q = 0;
				double b1, b2;
				char c = 0;
				byte[] imageInByte = null;
				String imageInString = " ";
				String imageFromString = " ";
				String imageFromStringbeta = new String();
				String parity = new String();
				String paritybeta = new String();
				String parity2 = new String();
				String parityerror = "";
				String temperr = new String();
				String imageFromString2 = new String();
				int errors = 0;
				int partem = 0;

				BufferedImage originalImage = ImageIO.read(new File("source.jpg"));
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				// convert BufferedImage to byte array

				imageInByte = convertBImageTOByteArray(originalImage, byteArrayOutputStream, imageInByte);

				byte[] imageFromByte = new byte[imageInByte.length];
				byte[] imageFromByte2 = new byte[imageInByte.length];

				imageInString = conversionVariableToPolarizationSystem(imageInByte, imageInString);
				//imageInString=" 11110000111100001111000011111010";

				warunek = imageInString.length() / ramka;

				if (((imageInString.length() % ramka) - 1) != 0)
					warunek++;

				parity = prepareParityStringToTransmission_Modulo(warunek, imageInString, ramka);

				i = warunek;// NIE RUSZAĆ
				parity = transmissionOfParity_Modulo(parity, c); // c jest
																	// znakiem

				imageFromString = preparingChannelToSending(imageInString, c, jakosc_kanalu, imageFromString);
				imageFromString2 = imageFromString.substring(0);

				warunek = imageFromString.length() / ramka;

				// sprawdzanie niepodzielności i tworzenie podziału na ramki
				if (((imageFromString.length() - 1) % ramka) != 0)
					warunek++;

				// wyliczanie, Porównanie parity i wysylanie poprawek

				do {

					System.out.println(errors);

					parity2 = "";
					parity2 = createChanelParity_Modulo(parity2, warunek, partem, imageFromString, ramka, c, q);

					errors = 0;
					parityerror = "";

					// pariry error

					parityerror = initParityError(parity, parity2);
					errors = calcerrors(parity, parity2);

					for (int n = 0; n < parityerror.length(); n++) {
						temperr = "";
						c = parityerror.charAt(n);
						q = Character.getNumericValue(c);
						if (q == 1)// jezelibylblad
						{

							temperr = getTemperr(ramka, imageInString, jakosc_kanalu, n); // nowo
																							// pobrane
																							// ramki

							partem = getparity(temperr);

							c = parity.charAt(n);
							q = Character.getNumericValue(c);

							if (partem == q) {

								imageFromStringbeta = createSecondaryString(imageFromString, n, temperr, ramka);

								imageFromString = imageFromStringbeta.substring(0, imageFromStringbeta.length());

								paritybeta = parity2.substring(0, n);
								
								if (c == 1)
									paritybeta = paritybeta.concat("1");
								else
									paritybeta = paritybeta.concat("0");

								paritybeta = paritybeta.concat(parity2.substring(n + 1, parity2.length()));
								parity2 = paritybeta.substring(0, paritybeta.length());

							}

						}
					}

				} while (errors > errorallowance);

				// DEKODER
				warunek = imageInString.length() / 8;

				imageFromByte = createImageFromByte(warunek, imageFromString, imageFromByte);
				imageFromByte2 = createImageFromByte2(i, imageFromByte2, imageFromString2);

				b1 = calculateBitErrorRate(imageInString, imageFromString);

				b2 = calculateBitErrorRate(imageInString, imageFromString2);

				try (PrintWriter out = new PrintWriter("FROMSTR.txt")) {
					out.println(imageFromString);
				}
				byteArrayOutputStream.close();

				try (PrintWriter out = new PrintWriter("INSTR.txt")) {
					out.println(imageInString);
				}
				byteArrayOutputStream.close();

				System.out.println("ARQ" + b1 + "% \nBEZ ARQ" + b2 + "%");

				InputStream in = new ByteArrayInputStream(imageFromByte2);
				BufferedImage bImageFromConvert = ImageIO.read(in);
				try {
					ImageIO.write(bImageFromConvert, "jpg", new File("output.jpg"));
				} catch (IllegalArgumentException e) {
					System.err.println("Popraw kanał");
				}
				in = new ByteArrayInputStream(imageFromByte);
				bImageFromConvert = ImageIO.read(in);
				ImageIO.write(bImageFromConvert, "jpg", new File("outputARQ.jpg"));

				this.panel.add(label1, BorderLayout.CENTER);
				panel.add(label2, BorderLayout.CENTER);
				panel.add(label3, BorderLayout.CENTER);
				panel.revalidate();
				panel.repaint();

				System.out.println("Gotowe");

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println("XD");
			}
		}
	}
}
