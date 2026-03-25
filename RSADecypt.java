import java.math.BigInteger;

public class RSADecypt {

    public static void main(String[] args) {
        
        int i;

        // Encryption
        
        //First Section
        
        BigInteger N = BigInteger.valueOf(33014297);
        int e = 65537;
        int[] M = {87, 83, 85, 45, 80, 82, 79, 70, 45, 52, 54, 56, 53};
        BigInteger[] C = new BigInteger[M.length];

        for (i = 0; i < M.length; i++) {
            BigInteger message = BigInteger.valueOf(M[i]);
            C[i] = message.modPow(BigInteger.valueOf(e), N);
        }
        
        System.out.println("This is the encrypted message as Characters:\n----------------------------------------\n");
        
        for (i = 0; i < C.length; i++) {
	    int charInt = C[i].intValue() % 255;
            System.out.print((char)charInt + ", ");
        }

	System.out.println("\n\nThis is the encrypted input in integer unicode form:\n----------------------------------------\n");

	for (i = 0; i < C.length; i++){

	    System.out.print(C[i].mod(BigInteger.valueOf(255)) + ", ");

	}
        
        // Decryption
       
        //Second Section
        
        System.out.println("\n\nThis is the decryption process:\n-----------------------------------------");

        int p = 0;
        int q = 0;
        int j = 0;
        int d = 0;
        boolean foundKey = false;

        int originalN = 33014297;
        for (i = 3; i <= originalN; i += 2) {
            while (originalN % i == 0) {
                j++;
                if (j == 1) {
                    q = i;
                } else {
                    p = i;
                }
                originalN /= i;
            }
        }

        System.out.println("\nP = " + p + "\n\nQ = " + q);
        
        //Third Section
        
        int phiOfN = (p - 1) * (q - 1);
        System.out.println("\nphi(N) = " + phiOfN);
        
        //Fourth Section
        
        BigInteger dBig = BigInteger.valueOf(0);
        while (!foundKey) {
            dBig = BigInteger.valueOf(d);
            if (BigInteger.valueOf(e).multiply(dBig).mod(BigInteger.valueOf(phiOfN)).equals(BigInteger.ONE)) {
                foundKey = true;
            } else {
                d++;
            }
        }

        System.out.println("\nd = " + dBig);
        
        //Fifth Section
        
        double[] decryptedM = new double[C.length];
        for (i = 0; i < C.length; i++) {
            BigInteger decrypted = C[i].modPow(dBig, N);
            decryptedM[i] = decrypted.doubleValue();
        }

        System.out.print("\nThe initial text was: ");
        for (i = 0; i < decryptedM.length; i++) {
            System.out.print((char)decryptedM[i]);
        }
        
	System.out.println();
    }
}
