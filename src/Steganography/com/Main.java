package Steganography.com;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.imageio.ImageIO;

public class Main {

	public static void main (String[] args)
	{		
		//Finding the message by decoding the img 
		File inputFile = new File("C:\\Users\\admin\\Desktop\\lab3\\7.bmp");
		try {
			byte[] image = Files.readAllBytes(inputFile.toPath());
			byte[] decodedImage = Decryption.decode(image);
			String str = new String(decodedImage, StandardCharsets.UTF_8);
			try(FileWriter writer = new FileWriter("C:\\Users\\admin\\Desktop\\lab3\\txt.txt", false)) // saving the decode message in a text file 
	        {
	            writer.write(str);
	        }
	        catch(IOException ex){            
	            System.out.println(ex.getMessage());
	        }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
		// Encrypting this message .  to the img
		String message = "This Message we going to  Encrypt then Decrypt ";
		File outputFile = new File("C:\\Users\\admin\\Desktop\\lab3\\2.bmp");
		try {
			byte[] image = Files.readAllBytes(outputFile.toPath());
			byte[] mes = message.getBytes(StandardCharsets.UTF_8);
			byte[] newImage = Decryption.code(image, mes);			
			try(FileOutputStream outputStream = new FileOutputStream(outputFile))
	        {
				outputStream.write(newImage);
	        }        
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//дешифруем ранее шифрованное изображение
		File inputFile1 = new File("C:\\Users\\admin\\Desktop\\lab3\\2.bmp");
		try {
			byte[] image = Files.readAllBytes(inputFile1.toPath());
			byte[] decodedImage = Decryption.decode(image);
			String str = new String(decodedImage, StandardCharsets.UTF_8);
			try (FileWriter writer = new FileWriter("C:\\Users\\admin\\Desktop\\lab3\\txt1.txt", false)) {
				writer.write(str);
				writer.flush();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}