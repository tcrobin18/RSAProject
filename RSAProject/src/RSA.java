import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RSA {

    public RSA(){

    }

    public static long randPrime(int m, int n, java.util.Random rand){
        BigInteger prime = new BigInteger((Integer.valueOf(rand.nextInt(n-m)+m)).toString());
        while (!prime.isProbablePrime(100)){
            prime = prime.nextProbablePrime();
            if (prime.compareTo(BigInteger.valueOf(n)) == 1){
                prime = new BigInteger((Integer.valueOf(rand.nextInt(n-m)+m)).toString());
            }
        }
        return prime.longValue();
    }

    public static long relPrime(long n, java.util.Random rand){
        BigInteger relPrime = new BigInteger(Integer.valueOf(rand.nextInt((int)n)).toString());
        while ((relPrime.gcd(BigInteger.valueOf(n))).compareTo(BigInteger.ONE) != 0){
            relPrime = relPrime.add(BigInteger.ONE);
            if (relPrime.compareTo(BigInteger.valueOf(n)) == 1){
                relPrime = new BigInteger((Integer.valueOf(rand.nextInt((int)n))).toString());
            }
        }
        return relPrime.longValue();
    }

    public static void show(long[] cipher){
        System.out.println(Arrays.toString(cipher));
    }


    public static long toLong(String msg, int p){
        List<Byte> bytes = new ArrayList<>();
        bytes.add((byte)msg.charAt(p));
        if (p+1 > msg.length()){
            bytes.add(null);
        }
        else bytes.add(((byte)msg.charAt(p+1)));

        StringBuffer bitString = new StringBuffer();

        bitString.append(Integer.toBinaryString(bytes.get(0)));

        if (bytes.get(1) == null){
            bitString.append("0000000000000000");
        }
        else{
            StringBuffer secondChar = new StringBuffer(Integer.toBinaryString(bytes.get(1)));
            while (secondChar.length() < 16){
                secondChar.insert(0,'0');
            }
            bitString.append(secondChar);
        }

        return Long.valueOf(bitString.toString(), 2);
    }

    public static String longTo2Chars(long x){
        StringBuffer codedLongs = new StringBuffer(Long.toBinaryString(x));
        while (codedLongs.length() < 32){
            codedLongs.insert(0,'0');
        }
        StringBuilder characters = new StringBuilder();
        characters.append(Character.toChars(Integer.valueOf(codedLongs.substring(0, 16), 2)));
        characters.append(Character.toChars(Integer.valueOf(codedLongs.substring(16),2)));
        return characters.toString();
    }


}
