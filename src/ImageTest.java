import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageTest 
{
	
	
	
	public static void main(String[] args) {

	    try {
	    	final double jakosc_kanalu=1.5;//im mniej tym lepszy kanał - od 1.2 do 2; poniżej 1.4 może być boguś
	    	final int ramka=8;
	    	final int errorallowance=10;
	    	
	    	int warunek,i,q,a,b,ber=0,ber2=0;
	    	double d,val;
	    	String s;
	    	char c = 0,c2=0;
	        byte[] imageInByte; 
	        String imageInString = " ";
	        String imageFromString = " ";
	        String imageFromStringbeta = new String();
	        String temp,temp2=new String();
	        String parity=new String();
	        String paritybeta=new String();
	        String parity2=new String();
	        String parityerror="";
	        String temperr=new String();
	        String imageFromString2=new String();
	        int binary=0;
	        int errors=0;
	        int partem=0;
	        
	        BufferedImage originalImage = ImageIO.read(new File("source.jpg"));

	        // convert BufferedImage to byte array
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ImageIO.write(originalImage, "jpg", byteArrayOutputStream);
	        byteArrayOutputStream.flush();
	        imageInByte = byteArrayOutputStream.toByteArray();
	        int len=imageInByte.length;
	    	byte[] imageFromByte=new byte[len];
	    	byte[] imageFromByte2=new byte[len];

	    	
	        for (i = 0; i < imageInByte.length; i++)
	        {
	        	binary=imageInByte[i];//konwersja zmiennej do systemu z polaryzacjÂą
	        	
	        	System.out.println(i + "/" + imageInByte.length);
	        	
	        	binary=binary+128;
	        	temp = Integer.toString(binary,2);
	        	while(temp.length()<8) temp="0"+temp;
	        	imageInString=imageInString.concat(temp);
	        }
	           
	      //imageInString=" 11110000111100001111000011110000";
	        
	        warunek=imageInString.length()/ramka;
	        if(((imageInString.length()%ramka)-1)!=0) warunek++;
	        
	        for (i=0; i<warunek;i++)
	        {
	        	System.out.println(i + "/" + warunek);
	        	partem=0;
	        	if(i<warunek-1){
	        	temp2=imageInString.substring((ramka*i)+1,(ramka*i)+ramka+1);
	        	for (int n=0;n<ramka;n++)
	        	{
	        		c=temp2.charAt(n);
	        		q=Character.getNumericValue(c);
	        		if (q==1) partem++;        
	        	}
	        	}
	        	else
	        	{
	        	temp2=imageInString.substring((ramka*i)+1, imageInString.length());
	        	for (int n=0;n<temp2.length();n++)
	        	{
	        		c=temp2.charAt(n);
	        		q=Character.getNumericValue(c);
	        		if (q==1) partem++;        
	        	}
	        	}
	        	
	        		
        		partem=partem%2;
        		parity=parity.concat(Integer.toString(partem));
	        	
	        	
	        }
	        
	       
	        for (int n=1;n<imageInString.length();n++)
	        {
	        	System.out.println(n + "/" + imageInString.length());
	        	
	        	c=imageInString.charAt(n);
	        	val=Character.getNumericValue(c);
	        	val=val*5;
	        	
	        	Random random=new Random();
	        	
	        	d=random.nextGaussian();
	        	d=d/jakosc_kanalu;
	        	val=val+d;
	        	
	        	if (val>2.5) c='1';
	        	if (val<=2.5) c='0';
	        	
	        	
	        	s=Character.toString(c);
	        	imageFromString=imageFromString.concat(s);
	        }
	        
	        imageFromString2=imageFromString.substring(0);
	        
	        warunek=imageFromString.length()/ramka;
	        if(((imageFromString.length()-1)%ramka)!=0) warunek++;
	        
	        //wyliczanie, Porównanie parity i wysylanie poprawek

	        do{
	        
	        System.out.println(errors);

        	parity2="";	
	        for (i=0; i<warunek;i++)
	        {
	        	partem=0;
	        	if(i<warunek-1){
	        	temp2=imageFromString.substring((ramka*i)+1,(ramka*i)+ramka+1);
	        	for (int n=0;n<ramka;n++)
	        	{
	        		c=temp2.charAt(n);
	        		q=Character.getNumericValue(c);
	        		if (q==1) partem++;        
	        	}
	        	}
	        	else
	        	{
	        	temp2=imageFromString.substring((ramka*i)+1, imageFromString.length());
	        	for (int n=0;n<temp2.length();n++)
	        	{
	        		c=temp2.charAt(n);
	        		q=Character.getNumericValue(c);
	        		if (q==1) partem++;        
	        	}
	        	}
	        	
        		partem=partem%2;
        		parity2=parity2.concat(Integer.toString(partem));
	        	
	        	
	        }
	        
	        
	        	errors=0;
	        	parityerror="";
	        for (int n=0;n<parity.length();n++)
	        {
	        	c=parity.charAt(n);
	        	c2=parity2.charAt(n);
	        	
	        	if (c==c2) parityerror=parityerror.concat("0");
	        	else {
	        		parityerror=parityerror.concat("1");
	        		errors++;
	        	}
	        	
	        }
	        
	        for(int n=0;n<parityerror.length();n++)
	        {
	        	temperr="";
	        	c=parityerror.charAt(n);
        		q=Character.getNumericValue(c);
        		if (q==1)//jezelibylblad
        		{
        			for(int e=0;e<ramka;e++){
        			c=imageInString.charAt(ramka*n+e+1);//?
    	        	val=Character.getNumericValue(c);
    	        	val=val*5;
    	        	
    	        	Random random=new Random();
    	        	d=random.nextGaussian();
    	        	d=d/jakosc_kanalu;
    	        	val=val+d;
    	        	
    	        	if (val>2.5) c='1';
    	        	if (val<=2.5) c='0';
    	        	
    	        	
    	        	s=Character.toString(c);
    	        	temperr=temperr.concat(s);
        		}
        			
    	        for (int w=0;w<ramka;w++)
    	        {
        		c=temperr.charAt(w);
        		q=Character.getNumericValue(c);
        		if (q==1) partem++;       
    	        }
    	        partem=partem%2;
    	        c=parity.charAt(n);
        		q=Character.getNumericValue(c);
    	        if (partem==q)
    	        {
    	        	imageFromStringbeta=imageFromString.substring(0, n*ramka+1);
    	        	imageFromStringbeta=imageFromStringbeta.concat(temperr);
    	        	a=(n*ramka+1+ramka);
    	        	b=imageFromString.length();
    	        	imageFromStringbeta=imageFromStringbeta.concat(imageFromString.substring(a, b));
    	        	imageFromString=imageFromStringbeta.substring(0, imageFromStringbeta.length());
    	        	
    	        	paritybeta=parity2.substring(0,n);
    	        	
    	        	if (c==1)
    	        	paritybeta=paritybeta.concat("1");
    	        	else
    	        	paritybeta=paritybeta.concat("0");
    	        	
    	        	paritybeta=paritybeta.concat(parity2.substring(n+1, parity2.length()));
    	        	parity2=paritybeta.substring(0,paritybeta.length());
    	        	
    	        }
        		
        		}
	        }
	        
	        
	        }while(errors>errorallowance);
	        
	        
	        //DEKODER
	        warunek=imageInString.length()/8;
	        for (int n=0;n<warunek;n++)
	        {
	        	
	        	//System.out.println(n + "/" + (i-1));
	        	if (n<warunek-1)
	        	{
	        	temp=imageFromString.substring((8*n)+1,(8*n)+9);
	        	}
	        	else 
	        	{
	        		temp=imageFromString.substring((8*n)+1,(imageFromString.length()));
	        	}
	        	
	        	try {
		        	binary = Integer.parseInt(temp, 2);
	        		} 
	        	catch (NumberFormatException e) {
	        		    System.err.println(temp.length());
	        	}
	        	
	        	binary=binary-128;
				imageFromByte[n]=(byte) binary;
	        		        	
	        }
	        
	        
	        for (int n=0;n<i;n++)
	        {
	      
	        	//System.out.println(n + "/" + i);
	        	temp=imageFromString2.substring((8*n)+1,(8*n)+9);
	        	binary = Integer.parseInt(temp, 2);
	        	binary=binary-128;
				imageFromByte2[n]=(byte) binary;

	        	
	        }
	        
	        for(int n=0;n<imageInString.length();n++)
	        {
	        	System.out.println(n + "/" + (imageInString.length()-1));
	        	c=imageInString.charAt(n);
	        	c2=imageFromString.charAt(n);
	        	
	        	if (c!=c2) ber++;
	        }
	        
	        for(int n=0;n<imageInString.length();n++)
	        {
	        	System.out.println(n + "/" + (imageInString.length()-1));
	        	c=imageInString.charAt(n);
	        	c2=imageFromString2.charAt(n);
	        	
	        	if (c!=c2) ber2++;
	        }
	        
	        try(  PrintWriter out = new PrintWriter( "FROMSTR.txt" )  )
	        {
	            out.println(imageFromString);
	        }	        	        
	        byteArrayOutputStream.close();
	            
	        try(  PrintWriter out = new PrintWriter( "INSTR.txt" )  )
	        {
	            out.println(imageInString);
	        }	        	        
	        byteArrayOutputStream.close();
	        
	        
	        InputStream in = new ByteArrayInputStream(imageFromByte2);
	        BufferedImage bImageFromConvert = ImageIO.read(in);
	        try{
	        ImageIO.write(bImageFromConvert, "jpg", new File("output.jpg"));
	        }
        	catch (IllegalArgumentException e) {
        		    System.err.println("XD");
        	}
	        in=new ByteArrayInputStream(imageFromByte);
	        bImageFromConvert=ImageIO.read(in);
	        ImageIO.write(bImageFromConvert, "jpg", new File("outputARQ.jpg"));
	        
	        System.out.println("Gotowe");
	        System.out.println(ber + " " + ber2);


	    } catch (IOException e) 
	    {
	        System.out.println(e.getMessage());
	        System.out.println("XD");
	    }
	}
}
