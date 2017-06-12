import java.awt.EventQueue;
import java.awt.Image;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
 
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
 
public class arqFrame {
 
    private JFrame frameNiducArq;
    private JTextField textField;
    private JTextField textFieldARQ;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    arqFrame window = new arqFrame();
                    window.frameNiducArq.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the application.
     */
    public arqFrame() {
        initialize();
    }
 
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
 
        frameNiducArq = new JFrame();
        frameNiducArq.setTitle("NIDUC2 ARQ    1.0");
        frameNiducArq.setBounds(100, 100, 1079, 627);
        frameNiducArq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameNiducArq.getContentPane().setLayout(null);
 
        JLabel labelTitle = new JLabel("");
        Image imgARQ = new ImageIcon(this.getClass().getResource("/ARQ.jpg")).getImage();
        labelTitle.setIcon(new ImageIcon(imgARQ));
        labelTitle.setBounds(25, 11, 274, 94);
        frameNiducArq.getContentPane().add(labelTitle);
 
        JComboBox<String> comboBoxSelection = new JComboBox<String>();
        comboBoxSelection.setBounds(315, 62, 129, 31);
        frameNiducArq.getContentPane().add(comboBoxSelection);
 
        JComboBox<String> comboBoxChannel = new JComboBox<String>();
        comboBoxChannel.setBounds(478, 62, 129, 31);
        frameNiducArq.getContentPane().add(comboBoxChannel);
 
        JComboBox<Integer> comboBoxHowManyErrors = new JComboBox<Integer>();
        comboBoxHowManyErrors.setBounds(644, 62, 183, 31);
        frameNiducArq.getContentPane().add(comboBoxHowManyErrors);
 
        JLabel lblSelection = new JLabel("Wybór Kanału");
        lblSelection.setToolTipText("Wybór Kanału");
        lblSelection.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSelection.setBounds(315, 25, 129, 31);
        frameNiducArq.getContentPane().add(lblSelection);
 
        JLabel labelChanel = new JLabel("Jakość kanału");
        labelChanel.setToolTipText("Jakość kanału");
        labelChanel.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelChanel.setBounds(478, 25, 129, 31);
        frameNiducArq.getContentPane().add(labelChanel);
 
        JLabel labelHowManyErrors = new JLabel("Ilość dopuszczalnych błędów");
        labelHowManyErrors.setToolTipText("Ilość dopuszczalnych błędów");
        labelHowManyErrors.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelHowManyErrors.setBounds(644, 25, 183, 31);
        frameNiducArq.getContentPane().add(labelHowManyErrors);
 
        JLabel lblSource = new JLabel("");
        lblSource.setBackground(Color.LIGHT_GRAY);
        lblSource.setBounds(25, 194, 259, 257);
        Image imgSource = new ImageIcon(this.getClass().getResource("/source.jpg")).getImage();
        lblSource.setIcon(new ImageIcon(imgSource));
        frameNiducArq.getContentPane().add(lblSource);
 
        JLabel lblWithoutARQ = new JLabel("");
        lblWithoutARQ.setBackground(Color.LIGHT_GRAY);
        lblWithoutARQ.setBounds(294, 194, 259, 257);
        Image imgOutput = new ImageIcon(this.getClass().getResource("/output.jpg")).getImage();
        lblWithoutARQ.setIcon(new ImageIcon(imgOutput));
        frameNiducArq.getContentPane().add(lblWithoutARQ);
 
        JLabel lblWithARQ = new JLabel("");
        lblWithARQ.setBackground(Color.LIGHT_GRAY);
        lblWithARQ.setBounds(563, 194, 259, 257);
        Image imgOutputARQ = new ImageIcon(this.getClass().getResource("/outputARQ.jpg")).getImage();
        lblWithoutARQ.setIcon(new ImageIcon(imgOutputARQ));
        frameNiducArq.getContentPane().add(lblWithARQ);
 
        textField = new JTextField();
        textField.setBounds(843, 299, 210, 77);
        frameNiducArq.getContentPane().add(textField);
        textField.setColumns(20);
 
        JComboBox<Integer> comboBoxFrame = new JComboBox<Integer>();
        comboBoxFrame.setBounds(875, 62, 129, 31);
        frameNiducArq.getContentPane().add(comboBoxFrame);
 
        JLabel labelFrame = new JLabel("Ramka");
        labelFrame.setToolTipText("Ramka");
        labelFrame.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelFrame.setBounds(875, 25, 129, 31);
        frameNiducArq.getContentPane().add(labelFrame);
 
        comboBoxChannel.addItem("Dobry");
        comboBoxChannel.addItem("Średni");
        comboBoxChannel.addItem("Zły");
        comboBoxSelection.addItem("Parity");
        comboBoxSelection.addItem("Modulo");
        comboBoxSelection.addItem("Multiply");
        comboBoxHowManyErrors.addItem(0);
        comboBoxHowManyErrors.addItem(5);
        comboBoxHowManyErrors.addItem(20);
        comboBoxHowManyErrors.addItem(100);
        comboBoxFrame.addItem(8);
        comboBoxFrame.addItem(32);
        comboBoxFrame.addItem(64);
        JMenuBar menuBar = new JMenuBar();
        frameNiducArq.setJMenuBar(menuBar);
 
        JMenu mnAuthors = new JMenu("Autorzy");
        menuBar.add(mnAuthors);
 
        JMenuItem[] items = { new JMenuItem("Anna Trębicka"), new JMenuItem("Michał Moskała"),
                new JMenuItem("Paweł Szynal") };
 
        for (int i = 0; i < items.length; i++) {
            items[i].isBackgroundSet();
            items[i].setBackground(Color.LIGHT_GRAY);
            items[i].getAccessibleContext().setAccessibleDescription(null);
        }
        // dodanie opcji do menu "Figury"
        mnAuthors.add(items[0]);
        mnAuthors.add(items[1]);
        mnAuthors.add(items[2]);
 
        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
 
        JMenu mnARQalgorithms = new JMenu("Algorytmy ARQ");
        mnHelp.add(mnARQalgorithms);
 
        JMenuItem[] items_mnARQalgorithms = { new JMenuItem("ARQ: Bit parzystości"),
                new JMenuItem("ARQ: Metoda mnożnia"), new JMenuItem("ARQ: Autorki algorym") };
 
        for (int i = 0; i < items_mnARQalgorithms.length; i++) {
            mnARQalgorithms.add(items_mnARQalgorithms[i]);
            items_mnARQalgorithms[i].isBackgroundSet();
            items_mnARQalgorithms[i].setBackground(Color.LIGHT_GRAY);
            items_mnARQalgorithms[i].getAccessibleContext().setAccessibleDescription(null);
        }
 
        items[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent sourceItems0) {
                JOptionPane.showMessageDialog(null, "Nr. Albumu:      \n Adres Email: ");
            }
        });
        items[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent sourceItems0) {
                JOptionPane.showMessageDialog(null, "Nr. Albumu:      \n Adres Email: ");
            }
        });
        items[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent sourceItems0) {
                JOptionPane.showMessageDialog(null, "Nr. Albumu: 226026\n Adres Email: 226026@student.pwr.edu.pl ");
            }
        });
 
        JButton btnSTART = new JButton("START");
        btnSTART.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
 
                Object source = evt.getSource();
                String mode = (String) comboBoxSelection.getSelectedItem();
 
                if (source == btnSTART && mode == "Parity") {
                    try {
                       
                        String combo = (String) comboBoxChannel.getSelectedItem();
                        double dcombo = 0;
                        if (combo=="Dobry") dcombo=1.6;
                        else if (combo=="Średni") dcombo=1.3;
                        else if (combo=="Zły") dcombo=0.5;
                        else System.exit(0);
                       
                        final double jakosc_kanalu = dcombo; // im mniej tym gorszy
                                                            // kanał
                                                            // -
                                                            // od
                                                            // 1.2 do 2; poniżej
                                                            // 1.4
                                                            // może
                                                            // być boguś+
                        /*
                         * IDEALNY 1.6 SREDNI 1.54 Zly 1.49
                         *
                         */
                        final int ramka = (int) comboBoxFrame.getSelectedItem();
                        // final int errorallowance = 5;// 20,10,5
                        final int errorallowance = (int) comboBoxHowManyErrors.getSelectedItem();
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
 
                        BufferedImage originalImage = ImageIO.read(new File("img/source.jpg"));
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
                        parity = transmissionOfParity(parity, c); // c jest
                                                                    // znakiem
 
                        imageFromString = preparingChannelToSending(imageInString, c, jakosc_kanalu, imageFromString);
                        imageFromString2 = imageFromString.substring(0);
 
                        warunek = imageFromString.length() / ramka;
 
                        // sprawdzanie niepodzielności i tworzenie podziału na
                        // ramki
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
 
                                        imageFromString = imageFromStringbeta.substring(0,
                                                imageFromStringbeta.length());
 
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
                        textField.setText("BEZ ARQ  " + b2 + "%");
                        textFieldARQ.setText("ARQ  " + b1 + "% \n");
                        InputStream in = new ByteArrayInputStream(imageFromByte2);
                        BufferedImage bImageFromConvert = ImageIO.read(in);
                        try {
                            ImageIO.write(bImageFromConvert, "jpg", new File("img/output.jpg"));
                            // lblWithoutARQ.setIcon(arg0);
                            Image imgOutput = bImageFromConvert;
                            lblWithoutARQ.setIcon(new ImageIcon(imgOutput));
 
                        } catch (IllegalArgumentException e) {
                            System.err.println("Popraw kanał");
                        }
                        in = new ByteArrayInputStream(imageFromByte);
                        bImageFromConvert = ImageIO.read(in);
                        try{ImageIO.write(bImageFromConvert, "jpg", new File("img/outputARQ.jpg"));
                        Image imgOutputARQ = bImageFromConvert;
                        lblWithARQ.setIcon(new ImageIcon(imgOutputARQ));
                        }
                        catch(Exception e){System.out.println("Nie udalo sie odtworzyc zdjecia");}
 
                        System.out.println("Gotowe");
 
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.out.println("XD");
                    }
                }
 
                if (source == btnSTART && mode == "Modulo") {
                    try {
                       
                        String combo = (String) comboBoxChannel.getSelectedItem();
                        double dcombo = 0;
                        if (combo=="Dobry") dcombo=1.6;
                        else if (combo=="Średni") dcombo=1.3;
                        else if (combo=="Zły") dcombo=0.5;
                        else System.exit(0);
                       
                        final double jakosc_kanalu = dcombo; // im mniej tym
                                                            // gorszy kanał
                                                            // -
                                                            // od
                                                            // 1.2 do 2; poniżej
                                                            // 1.4
                                                            // może
                                                            // być boguś+
                        /*
                         * IDEALNY 1.6 SREDNI 1.54 Zly 1.49
                         *
                         */
                        final int ramka = (int) comboBoxFrame.getSelectedItem();
                        final int errorallowance = (int) comboBoxHowManyErrors.getSelectedItem();
 
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
 
                        BufferedImage originalImage = ImageIO.read(new File("img/source.jpg"));
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
                        parity = transmissionOfParity_Modulo(parity, c); // c
                                                                            // jest
                                                                            // znakiem
 
                        imageFromString = preparingChannelToSending(imageInString, c, jakosc_kanalu, imageFromString);
                        imageFromString2 = imageFromString.substring(0);
 
                        warunek = imageFromString.length() / ramka;
 
                        // sprawdzanie niepodzielności i tworzenie podziału na
                        // ramki
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
 
                                    partem = modulus(temperr);
 
                                    c = parity.charAt(n);
                                    q = Character.getNumericValue(c);
 
                                    if (partem == q) {
 
                                        imageFromStringbeta = createSecondaryString(imageFromString, n, temperr, ramka);
 
                                        imageFromString = imageFromStringbeta.substring(0,
                                                imageFromStringbeta.length());
 
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
                        textField.setText("BEZ ARQ  " + b2 + "%");
                        textFieldARQ.setText("ARQ  " + b1 + "% \n");
 
                        InputStream in = new ByteArrayInputStream(imageFromByte2);
                        BufferedImage bImageFromConvert = ImageIO.read(in);
                        try {
                            ImageIO.write(bImageFromConvert, "jpg", new File("img/output.jpg"));
                            Image imgOutput = bImageFromConvert;
                            lblWithoutARQ.setIcon(new ImageIcon(imgOutput));
                        } catch (IllegalArgumentException e) {
                            System.err.println("Popraw kanał");
                        }
                        in = new ByteArrayInputStream(imageFromByte);
                        bImageFromConvert = ImageIO.read(in);
                        try{ImageIO.write(bImageFromConvert, "jpg", new File("img/outputARQ.jpg"));
                        Image imgOutputARQ = bImageFromConvert;
                        lblWithARQ.setIcon(new ImageIcon(imgOutputARQ));
                        }
                        catch(Exception e){System.out.println("Nie udalo sie odtworzyc zdjecia");}
                       
                        System.out.println("Gotowe");
 
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.out.println("XD");
                    }
                }
 
                if (source == btnSTART && mode == "Multiply"){

                    try {
                       
                        String combo = (String) comboBoxChannel.getSelectedItem();
                        double dcombo = 0;
                        if (combo=="Dobry") dcombo=1.6;
                        else if (combo=="Średni") dcombo=1.3;
                        else if (combo=="Zły") dcombo=0.5;
                        else System.exit(0);
                       
                        final double jakosc_kanalu = dcombo; // im mniej tym
                                                            // gorszy kanał
                                                            // -
                                                            // od
                                                            // 1.2 do 2; poniżej
                                                            // 1.4
                                                            // może
                                                            // być boguś+
                        /*
                         * IDEALNY 1.6 SREDNI 1.54 Zly 1.49
                         *
                         */
                        final int ramka = (int) comboBoxFrame.getSelectedItem();
                        final int errorallowance = (int) comboBoxHowManyErrors.getSelectedItem();
 
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
 
                        BufferedImage originalImage = ImageIO.read(new File("img/source.jpg"));
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
 
                        parity = prepareParityStringToTransmission_Multiply(warunek, imageInString, ramka);
 
                        i = warunek;// NIE RUSZAĆ
                        parity = transmissionOfParity_Modulo(parity, c); // c
                                                                            // jest
                                                                            // znakiem
 
                        imageFromString = preparingChannelToSending(imageInString, c, jakosc_kanalu, imageFromString);
                        imageFromString2 = imageFromString.substring(0);
 
                        warunek = imageFromString.length() / ramka;
 
                        // sprawdzanie niepodzielności i tworzenie podziału na
                        // ramki
                        if (((imageFromString.length() - 1) % ramka) != 0)
                            warunek++;
 
                        // wyliczanie, Porównanie parity i wysylanie poprawek
 
                        do {
 
                            System.out.println(errors);
 
                            parity2 = "";
                            parity2 = createChanelParity_Multiply(parity2, warunek, partem, imageFromString, ramka, c, q);
 
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
 
                                    partem = multipli(temperr);
 
                                    c = parity.charAt(n);
                                    q = Character.getNumericValue(c);
 
                                    if (partem == q) {
 
                                        imageFromStringbeta = createSecondaryString(imageFromString, n, temperr, ramka);
 
                                        imageFromString = imageFromStringbeta.substring(0,
                                                imageFromStringbeta.length());
 
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
                        textField.setText("BEZ ARQ  " + b2 + "%");
                        textFieldARQ.setText("ARQ  " + b1 + "% \n");
 
                        InputStream in = new ByteArrayInputStream(imageFromByte2);
                        BufferedImage bImageFromConvert = ImageIO.read(in);
                        try {
                            ImageIO.write(bImageFromConvert, "jpg", new File("img/output.jpg"));
                            Image imgOutput = bImageFromConvert;
                            lblWithoutARQ.setIcon(new ImageIcon(imgOutput));
                        } catch (IllegalArgumentException e) {
                            System.err.println("Popraw kanał");
                        }
                        in = new ByteArrayInputStream(imageFromByte);
                        bImageFromConvert = ImageIO.read(in);
                        try{ImageIO.write(bImageFromConvert, "jpg", new File("img/outputARQ.jpg"));
                        Image imgOutputARQ = bImageFromConvert;
                        lblWithARQ.setIcon(new ImageIcon(imgOutputARQ));
                        }
                        catch(Exception e){System.out.println("Nie udalo sie odtworzyc zdjecia");}
                       
                        System.out.println("Gotowe");
 
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.out.println("XD");
                    }
            }
                
            }
        });
        btnSTART.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSTART.setBackground(new Color(51, 255, 255));
        btnSTART.setBounds(843, 194, 210, 94);
        frameNiducArq.getContentPane().add(btnSTART);
 
        JLabel labelImage = new JLabel("Source image");
        labelImage.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelImage.setBounds(88, 152, 129, 31);
        frameNiducArq.getContentPane().add(labelImage);
 
        JLabel lblImgOutput = new JLabel("Zdj\u0119cie bez transmisji ARQ");
        lblImgOutput.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblImgOutput.setBounds(305, 152, 240, 31);
        frameNiducArq.getContentPane().add(lblImgOutput);
 
        JLabel lblImgOutputARQ = new JLabel("Zdj\u0119cie z transmisj\u0105 ARQ");
        lblImgOutputARQ.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblImgOutputARQ.setBounds(578, 152, 240, 31);
        frameNiducArq.getContentPane().add(lblImgOutputARQ);
 
        JProgressBar progressBar = new JProgressBar();
        progressBar.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
               
            }
        });
        progressBar.setBounds(25, 497, 1028, 38);
        frameNiducArq.getContentPane().add(progressBar);
 
        textFieldARQ = new JTextField();
        textFieldARQ.setColumns(10);
        textFieldARQ.setBounds(843, 374, 210, 77);
        frameNiducArq.getContentPane().add(textFieldARQ);
 
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
    
    static String createChanelParity_Multiply(String parity2, int warunek, int partem, String imageFromString,
            final int ramka, char c, int q) {
 
        String temp2 = new String();
        for (int i = 0; i < warunek; i++) {
            partem = 0;
            if (i < warunek - 1) {
                temp2 = imageFromString.substring((ramka * i) + 1, (ramka * i) + ramka + 1);
                partem = multipli(temp2);
            } else {
                temp2 = imageFromString.substring((ramka * i) + 1, imageFromString.length());
                partem = multipli(temp2);
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
    
    static String prepareParityStringToTransmission_Multiply(int warunek, String imageInString, int ramka) {
        int partem = 0;
        String temp;
        String parity = "";
        for (int i = 0; i < warunek; i++) {
            System.out.println(i + "/" + warunek);
 
            if (i < warunek - 1) {
                temp = imageInString.substring((ramka * i) + 1, (ramka * i) + ramka + 1);
 
                partem = multipli(temp);
 
            } else {
                temp = imageInString.substring((ramka * i) + 1, imageInString.length());
                partem = multipli(temp);
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
}
