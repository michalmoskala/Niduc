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
	    	final double jakosc_kanalu=1.45;//im mniej tym lepszy kanał - od 1.2 do 2; poniżej 1.4 może być boguś
	    	final int ramka=76;
	    	
	    	int warunek;
	    	double d,val;
	    	String s;
	    	char c = 0;
	    	int i,q;
	        byte[] imageInByte; 
	        String imageInString = " ";
	        String imageFromString = " ";
	        String temp,temp2;
	        String parity=new String();
	        int binary=0;
	        int partem=0;
	        
	        BufferedImage originalImage = ImageIO.read(new File("source.jpg"));

	        // convert BufferedImage to byte array
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ImageIO.write(originalImage, "jpg", byteArrayOutputStream);
	        byteArrayOutputStream.flush();
	        imageInByte = byteArrayOutputStream.toByteArray();
	    	byte[] imageFromByte=imageInByte;
	        for (i = 0; i < imageInByte.length; i++)
	        {
	        	binary=imageInByte[i];//konwersja zmiennej do systemu z polaryzacjÂą
	        	
	        	System.out.println(i + "/" + imageInByte.length);
	        	
	        	binary=binary+128;
	        	temp = Integer.toString(binary,2);
	        	while(temp.length()<8) temp="0"+temp;
	        	imageInString=imageInString.concat(temp);
	        }
	           
	        
	        warunek=imageInString.length()/ramka;
	        if((imageInString.length()%ramka)!=0) warunek++;
	        
	        for (i=0; i<warunek;i++)
	        {
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
	        
	        try(  PrintWriter out = new PrintWriter( "filename.txt" )  )
	        {
	            out.println(imageInString);
	        }	        	        
	        byteArrayOutputStream.close();
	            
	        try(  PrintWriter out = new PrintWriter( "parity.txt" )  )
	        {
	            out.println(parity);
	        }	        	        
	        byteArrayOutputStream.close();
	        
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
	        
	        //DEKODER
	        warunek=imageInString.length()/8;
	        for (int n=0;n<warunek;n++)
	        {
	        	
	        	System.out.println(n + "/" + (i-1));
	        	if (n<warunek-1)
	        	{
	        	temp=imageFromString.substring((8*n)+1,(8*n)+9);
	        	}
	        	else 
	        	{
	        		System.out.println("TODO");
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
	        
	        try(  PrintWriter out = new PrintWriter( "filename.txt" )  )
	        {
	            out.println(imageFromString);
	        }	        	        
	        byteArrayOutputStream.close();
	            
	        try(  PrintWriter out = new PrintWriter( "parity.txt" )  )
	        {
	            out.println(parity);
	        }	        	        
	        byteArrayOutputStream.close();
	        
	        
	        InputStream in = new ByteArrayInputStream(imageFromByte);
	        BufferedImage bImageFromConvert = ImageIO.read(in);

	        ImageIO.write(bImageFromConvert, "jpg", new File("output.jpg"));
	        System.out.println("Gotowe");


	    } catch (IOException e) 
	    {
	        System.out.println(e.getMessage());
	    }
	}
}
