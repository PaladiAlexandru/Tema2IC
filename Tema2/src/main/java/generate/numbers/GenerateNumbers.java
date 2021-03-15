package generate.numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateNumbers {

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger x;
    private List<BigInteger> randomNumbers;
    private List<Integer> bitList;

    /**
     * Atribuie lui n produsul lui p si q, iar lui x un numar random intre 1 si n
     */
    public GenerateNumbers() {
        Random rand = new Random();
        int maxLength;

        this.p = BigInteger.probablePrime(1024,rand);
        this.q = BigInteger.probablePrime(1024,rand);
        this.n = p.multiply(q);
        maxLength = n.bitLength();
        this.x = new BigInteger(maxLength,rand);

        if(x.compareTo(new BigInteger("1")) < 0){
            this.x = x.add(new BigInteger("1"));
        }
        if(x.compareTo(n) >= 0){
            this.x= x.mod(n).add(new BigInteger("1"));
        }
    }

    public void generate(int size){
        this.randomNumbers=new ArrayList<>();
        this.bitList = new ArrayList<>();
        BigInteger even = new BigInteger("2");
        BigInteger number = x.multiply(x).mod(n);

        randomNumbers.add(number);
        if(number.mod(even).equals(BigInteger.ZERO)){
            bitList.add(1);
        }else{
            bitList.add(0);
        }
        for(int i =0;i<size-1;i++){
            BigInteger previous = randomNumbers.get(i);
            number = previous.multiply(previous).mod(n);
            randomNumbers.add(number);

            if(number.mod(even).equals(BigInteger.ZERO)){
                bitList.add(1);
            }else{
                bitList.add(0);
            }
        }

    }


    public void testGenerator(){
        int[] vAparitii = new int[3];
        for(Integer integer: bitList){
            vAparitii[integer]++;
        }
        System.out.println("1: " + vAparitii[1]%100 + "%");
        System.out.println("0: " + vAparitii[0]%100 + "%");
    }

    @Override
    public String toString() {
        return "GenerateNumbers{" +
                "p=" + p +
                ", q=" + q +
                ", n=" + n +
                ", x=" + x +
                ", randomNumbers=" + randomNumbers +
                ", bitList=" + bitList +
                '}';
    }

}
