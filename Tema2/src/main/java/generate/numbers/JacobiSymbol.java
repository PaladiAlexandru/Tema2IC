package generate.numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JacobiSymbol {
    private BigInteger n;
    private BigInteger a;
    private List<Integer> bitList;

    public JacobiSymbol() {
        Random rand = new Random();
        this.n=BigInteger.probablePrime(500,rand).multiply(BigInteger.probablePrime(500,rand));
        int maxLength = n.bitLength();
        this.a=new BigInteger(maxLength,rand);

        if(a.compareTo(new BigInteger("1")) < 0){
            this.a = a.mod(n).add(new BigInteger("1"));
        }
        if(a.compareTo(n) >= 0){
            this.a= a.mod(n).add(new BigInteger("1"));
        }
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "JacobiSymbol{" +
                "n=" + n +
                ", a=" + a +
                ", randomNumbers=" +
                ", bitList=" + bitList +
                '}';
    }
    public void getMJacobiSymbols(int m){
        bitList= new ArrayList<>();
        int count1=0,count0=0;
        for(int i=0;i<m;i++){
            int bit = generate(a.add(BigInteger.valueOf(i)),n);

            if(bit == -1){
                bitList.add(0);
                count0++;
            }else{
                bitList.add(1);
                count1++;
            }
        }
        System.out.println("1: " + count1*100/(count0+count1) + "%");
        System.out.println("0: " + count0*100/(count0+count1) + "%");
    }

    public int generate(BigInteger a ,BigInteger n){
        BigInteger number = a.mod(n);
        // if a = 1 or n = 1 return 1
        if (number.equals(BigInteger.ONE) || n.equals(BigInteger.ONE)) {
            return 1;
        }

        if (number.equals(BigInteger.ZERO)) {
            return 0;
        }

        int v = 0;
        while (number.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
            v++;
            number = number.divide(BigInteger.TWO);
        }
        int s;
        if (v % 2 == 0) {
            s = 1;
        } else {

            if (n.mod(BigInteger.valueOf(8)).equals(BigInteger.ONE) || n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(7))) {
                s = 1;
            } else {
                s = -1;
            }
        }

        if (n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && number.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
            s = -s;
        }

        BigInteger number2 = n.mod(number);

        return s * generate(number2, number);

    }


}
