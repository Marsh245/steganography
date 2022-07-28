package Steganography.com;

import java.util.ArrayList;

public class Decryption 
{
	public static int offset = 54;
	private static byte byteEndMessage = (byte) 0xFF;

	public static byte[] code(byte[] image, byte[] message) throws Exception
    {
        byte[] newImage = new byte[image.length];
        int neededBytesForImage = offset + (message.length + 1) * 4;

        if (newImage.length < neededBytesForImage)
            throw new Exception("The data does not match the .bmp format");

        for (int i = 0; i < image.length; i++)
            newImage[i] = image[i];

        for (int i = offset; i < neededBytesForImage - 4; i++)
        {
            newImage[i] = (byte)(image[i] & 0xFC);
            
            switch ((i - offset) % 4) {
			case 0: {
				newImage[i] |= (byte)(message[(i - offset) / 4] >> 6 & 3);
			}
				break;
			case 1: {
				newImage[i] |= (byte)(message[(i - offset) / 4] >> 4 & 3);
			}
				break;
			case 2: {
				newImage[i] |= (byte)(message[(i - offset) / 4] >> 2 & 3);
			}
				break;
			case 3: {
				newImage[i] |= (byte)(message[(i - offset) / 4] & 3);
			}
				break;
			}
        }

        for (int i = neededBytesForImage - 4; i < neededBytesForImage; i++)
            newImage[i] |= 3;

        return newImage;
    }
	
	public static byte[] decode(byte[] image) throws Exception {
		if (image.length <= offset)
			throw new Exception("The data does not match the .bmp format");

		ArrayList<Byte> message = new ArrayList<Byte>();

		byte charValue = 0;
		for (int i = offset; i < image.length; i++) {
			switch (((i - offset) % 4)) {
			case 0: {
				charValue |= (byte) ((image[i] & 3) << 6);
			}
				break;
			case 1: {
				charValue |= (byte) ((image[i] & 3) << 4);
			}
				break;
			case 2: {
				charValue |= (byte) ((image[i] & 3) << 2);
			}
				break;
			case 3: {
				charValue |= (byte) (image[i] & 3);
			}
				break;
			}

			if (charValue == byteEndMessage)
				return byteListToArray(message);

			if ((i - offset) % 4 == 3) {
				message.add(charValue);
				charValue = 0;
			}
		}
	
		return byteListToArray(message);
	}
	
	public static byte[] byteListToArray(ArrayList <Byte> message)
	{
		byte[] bytes = new byte[message.size()];
		for (int k = 0; k < message.size(); k++)
			bytes[k] = message.get(k);
		return bytes;
	}
}