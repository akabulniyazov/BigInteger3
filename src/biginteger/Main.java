package biginteger;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		BigInteger b1 = new BigInteger("1234");
		System.out.println(b1.toString());
		
		BigInteger b2=new BigInteger("1357");
		BigInteger b3=new BigInteger("2468");
		BigInteger b4=b2.sub(b3);
		System.out.println(b4);
	}
}
