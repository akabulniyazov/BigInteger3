/**
 * Java's primitive data types int and long can represent the numbers 
 * in the following range. 
 * 
 * 			Integer: (-2147483648, 2147483647)
 * 			Long Integer: (-9223372036854775808, 9223372036854775807)
 *
 * We want to represent numbers that larger than 9223372036854775807. 
 * One idea is to use an array to store each digit of the big number. 
 * For example: 9223372036854775807 is stored in an array as 
 * [9,2,2,3,3,7,2,0,3,6,8,5,4,7,7,5,8,0,7]. 
 * 
 * 
 * In this project, you will implement the BigInteger class, which can 
 * store any size integer and perform basic arithmetic operations on the 
 * number. 
 */
package biginteger;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class BigInteger {

	private static final boolean POSITIVE   = true;
	private static final boolean NEGATIVE = false;

	/**
	 * // this array stores the number. Each digit of the number is an 
	 * element of the array
	 */
	private final int[] number;
	/**
	 * sign of this BigInteger. POSITIVE or NEGATIVE
	 */
	private final boolean sign;	

	/**
	 * Default constructor, creates a BigInteger object whose value is 0.
	 * and whose sign is positive
	 * 
	 * This constructor will assign sign of the BigInteger
	 * and will assign BigInteger array called number to 0
	 */
	public BigInteger(){
		//replace assignments for number and sign with accordance to constructor description
		number = new int[] {0} ;
		this.sign=POSITIVE;

	}
	/**
	 * Constructor, creates a BigInteger object with given sign and given array of numbers
	 * @param sign of a number
	 * @param array of integers 
	 * This constructor will assign sign of the BigInteger
	 * and will copy all elements of the given array into BigInteger array called number
	 */
	public BigInteger(boolean sign, int[] val){
		//replace assignments for number and sign with accordance to constructor description
		number =  val;
		this.sign = sign;
	}

	/**
	 * Create a BigInteger object using the number given as string. 
	 * If the string starts with "-", it indicates, the number is negative.
	 * 		in this case assign sign to negative and store all numbers from  a String in number array
	 * If the string starts with "+", or a digit, the number is positive. 
	 * 		in this case assign sign to positive and store all numbers from  a String in number array
	 * If the string is null or empty, the number is zero. zero is a positive number. 
	 * 		in this case assign sign to positive and store 0 in number array
	 * If string contains non-digit characters, throw IllegalArgumentException
	 * 		in this case use the following syntax to throw an exception:
	 * 						throw new IllegalArgumentException();
	 * 
	 * 
	 * 
	 * @param n: the number in string format. 
	 *  For example:
	 * 	BigInteger b1 = new BigInteger("1234567890");
	 *  b1 is a BigInteger with value of 1234567890 stored in number array
	 *  BigInteger b2 = new BigInteger("-1234567");
	 *  b1 is a BigInteger with value of -1234567 stored in number array
	 *  BigInteger b3 = new BigInteger("+123");
	 *  b3 is a BigInteger with value of +123 stored in number array
	 *  BigInteger b3 = new BigInteger("+123xyz");
	 *  throw IllegalArgumentException
	 */
	public BigInteger(String strNumber){
		//this();	//use to call the default constructor
		
		boolean s = false;
		if(strNumber == null || strNumber.isEmpty()) {
		number = new int[] {0};
		this.sign = POSITIVE;
		System.out.println("0");
		return;
	    }
		int[] all;
			if(strNumber.startsWith("-")) {
				all=new int[strNumber.length()-1];
			for(int index=1,b=0;index<strNumber.length();index++,b++) {
				int a=Character.getNumericValue(strNumber.charAt(index));
				if(a<0||a>9) {
					 throw new IllegalArgumentException();
				}
				all[b]=Character.getNumericValue(strNumber.charAt(index));
			}
			s=NEGATIVE;
		}else if(strNumber.startsWith("+")){
			all=new int[strNumber.length()-1];
			for(int index=1,b=0;index<strNumber.length();index++,b++) {
				int a=Character.getNumericValue(strNumber.charAt(index));
				if(a<0||a>9) {
					 throw new IllegalArgumentException();
				}
				all[b]=Character.getNumericValue(strNumber.charAt(index));
					
			}
			s=POSITIVE;
		}else {
			all=new int[strNumber.length()];
			for(int index=0,b=0;index<strNumber.length();index++,b++) {
				int a=Character.getNumericValue(strNumber.charAt(index));
				if(a<0||a>9) {
					 throw new IllegalArgumentException();
				}
				all[b]=Character.getNumericValue(strNumber.charAt(index));
				
			}
			s=POSITIVE;

		}
			
		this.sign=s;
		number=all;
		
		//replace assignments for number and sign with accordance to constructor description
	}
	/**
	 * Create a BigInteger object using another another BigInteger.
	 * This constructor will take another BigInteger as a parameter and will copy all the values into
	 * this BigInteger and assign the sign
	 * @param another: BigInteger object
	 * 	BigInteger b = new BigInteger("1234567890")
	 * 
	 * Create a new BigInteger object using b
	 *  BigInteger b2 = new BigInteger(b)
	 */
	public BigInteger(BigInteger another){
		//replace assignments for number and sign with accordance to constructor description
		number =  another.number;
		this.sign = another.sign;
	}



	/**
	 * Adds two BigIntegers, and creates a new BigInteger with the result of the addition
	 * 
	 * IMPORTANT: Pay attention to the integer sign and length   
	 * 		When two positive values are added, the result is positive (2+2=4)
	 *      When one positive and one negative numbers are added, 
	 *      		the result can be negative (-4 + 2 = -2) or positive (-2 + 3 = 1)
	 *      When two negative values are added, the result is negative (-2 + -2 = -4)
	 *      When both numbers are zeroes, the result is zero as well
	 *      																		 
	 * Two add two big integers, we cad add each digit at the same index
	 * from the two arrays. For example: 
	 * a:[1,2,3,4]
	 * b:[5,6,7,8]
	 * a+b is
	 * [6,9,1,2]    
	 *  																	     
	 * @param b: BigInteger to be added to this BigInteger Object
	 * @return: a new BigInteger object, whose value is the sum of this and b, two BigInteger
	 *          objects
	 */
	public BigInteger add(BigInteger b){
		int [] first=this.number;
		int [] second=b.number;
		BigInteger f=new BigInteger();
		
		ArrayList<Integer> list=new ArrayList<Integer> ();
		
		boolean newsign=f.sign;
		int l1=first.length;
		int l2=second.length;
		int result=0;
		int additional=0;
		
		if(this.sign==true&&b.sign==true) {
			newsign=POSITIVE;
			for(int a=0,c=0;a<l1||c<l2;l1--,l2--) {
				if(l1<=0) {
					result=second[l2-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);	
					}else {
						list.add(result);
					}
				}else if(l2<=0) {
					result=first[l1-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);
						
					}else {
						list.add(result);
						
					}
					
				}else {
					result=first[l1-1]+second[l2-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);
						
					}else {
						list.add(result);
						
					}
				}

			}
			
		}else if(this.sign==false&&b.sign==true) {
			for(int a=0,c=0;a<l1||c<l2;l1--,l2--) {
				if(l1<=0) {
					result=second[l2-1]-additional;
					additional=0;
					if(result<=0) {
						additional=1;
						list.add(Math.abs(result));
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=POSITIVE;
				}else if(l2<=0) {
					result=-first[l1-1]-additional;
					additional=0;
					if(result<=0) {
						list.add(Math.abs(result));
						additional=1;
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=NEGATIVE;
				}else {
					result=-first[l1-1]+second[l2-1]-additional;
					additional=0;
					if(result<0) {
						result=10+result;
						list.add(Math.abs(result));
						additional=1;
					}else {
						list.add(Math.abs(result));
					}
				}

			}	
		}else if(this.sign==true&&b.sign==false) {
			for(int a=0,c=0;a<l1||c<l2;l1--,l2--) {
				if(l1<=0) {
					result=-second[l2-1]-additional;
					additional=0;
					if(result<=0) {
						list.add(Math.abs(result));
						additional=1;
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=POSITIVE;
				}else if(l2<=0) {
					result=first[l1-1]-additional;
					additional=0;
					if(result<=0) {
						list.add(Math.abs(result));
						additional=1;
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=NEGATIVE;
				}else {
					result=-first[l1-1]+second[l2-1]-additional;
					additional=0;
					if(result<0) {
						result=10+result;
						list.add(Math.abs(result));
						additional=1;
					}else {
						
						list.add(Math.abs(result));
					}
				}
				newsign=NEGATIVE;
			}
			
		}
		if(additional==1) {
			list.add(additional);
		}
		for(int t=list.size(); 1<list.size(); t--) {
			if(list.get(t-1)==0) {
				list.remove(t-1);
			}else {
				break;
			}
		}
		int[] newone=new int[list.size()];
		for(int index=list.size()-1,y=0;  0<=index;  index--,y++) {
		newone[y]=list.get(index).intValue();
		}
		return new BigInteger(newsign,newone);
	}
		
	
	/**
	 * Subtracts BigInteger b from this BigInteger, and creates a new BigInteger with 
	 * the result of the subtraction.
	 * @param b: BigInteger to be subtracted from this BigInteger Object
	 * @return: a new BigInteger object
	 */
	public BigInteger sub(BigInteger b){
		int [] first=this.number;
		int [] second=b.number;
		BigInteger f=new BigInteger();
		
		ArrayList<Integer> list=new ArrayList<Integer> ();
		
		boolean newsign=f.sign;
		newsign=NEGATIVE;
		int l1=first.length;
		int l2=second.length;
		int result=0;
		int additional=0;
		
		if(this.sign==true&&b.sign==false) {
			newsign=POSITIVE;
			for(int a=0,c=0;a<l1||c<l2;l1--,l2--) {
				if(l1<=0) {
					result=second[l2-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);	
					}else {
						list.add(result);
					}
				}else if(l2<=0) {
					result=first[l1-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);
						
					}else {
						list.add(result);
						
					}
					
				}else {
					result=first[l1-1]+second[l2-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);
						
					}else {
						list.add(result);
						
					}
				}

			}
			
		}else if(this.sign==false&&b.sign==false) {
			for(int a=0,c=0;a<l1||c<l2;l1--,l2--) {
				if(l1<=0) {
					result=second[l2-1]-additional;
					additional=0;
					if(result<=0) {
						additional=1;
						list.add(Math.abs(result));
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=POSITIVE;
				}else if(l2<=0) {
					result=-first[l1-1]-additional;
					additional=0;
					if(result<=0) {
						list.add(Math.abs(result));
						additional=1;
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=POSITIVE;
				}else {
					result=-first[l1-1]+second[l2-1]-additional;
					additional=0;
					if(result<0) {
						result=10+result;
						list.add(Math.abs(result));
						additional=1;
					}else {
						list.add(Math.abs(result));
					}
					newsign=POSITIVE;
				}

			}	
		}else if(this.sign==true&&b.sign==true) {
			for(int a=0,c=0;a<l1||c<l2;l1--,l2--) {
				if(l1<=0) {
					result=second[l2-1]-additional;
					additional=0;
					if(result<=0) {
						list.add(Math.abs(result));
						additional=1;
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=NEGATIVE;
				}else if(l2<=0) {
					result=first[l1-1]+additional;
					additional=0;
					if(result<=0) {
						list.add(Math.abs(result));
						additional=-1;
					}else {
						
						list.add(Math.abs(result));
					}
					newsign=POSITIVE;
				}else {
					result=first[l1-1]-second[l2-1]+additional;
					additional=0;
					if(result<0&&first[0]>second[0]) {
						result=10+result;
						list.add(Math.abs(result));
						additional=-1;
						newsign=POSITIVE;
					}else {
						
						list.add(Math.abs(result));
						
					}
				}
				
			}
			
		}else if(this.sign==false&&b.sign==true) {
			newsign=POSITIVE;
			for(int a=0,c=0;a<l1||c<l2;l1--,l2--) {
				if(l1<=0) {
					result=second[l2-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);	
					}else {
						list.add(result);
					}
				}else if(l2<=0) {
					result=first[l1-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);
						
					}else {
						list.add(result);
						
					}
					
				}else {
					result=first[l1-1]+second[l2-1]+additional;
					additional=0;
					if(result>9) {
						int rem=result%10;
						additional=result/10;
						list.add(rem);
						
					}else {
						list.add(result);
						
					}
					newsign=NEGATIVE;
				}

			}
			
		}
		if(additional==1) {
			list.add(additional);
		}
		for(int t=list.size(); 1<list.size(); t--) {
			if(list.get(t-1)==0) {
				list.remove(t-1);
			}else {
				break;
			}
		}
		if(list.get(0)==0) {
			newsign=POSITIVE;
		}
		int[] newone=new int[list.size()];
		for(int index=list.size()-1,y=0;  0<=index;  index--,y++) {
		newone[y]=list.get(index).intValue();
		}
		return new BigInteger(newsign,newone);
	}
		
	/**
	 * Returns a BigInteger whose value is (this % b).
	 * @param b: BigInteger object, by which this BigInteger is to be divided, 
	 * and the remainder computed.
	 * @return:  this % b
	 * Throws:
    	     java.lang.ArithmeticException if b's value is zero
	 */
	public BigInteger remainder(BigInteger b){
		//TODO
		return null;
	}


	/**
	 * Compares this BigInteger with the specified BigInteger. 
	 * @param b: BigInteger to which this BigInteger is to be compared.
	 * @return:  -1, 0 or 1 as this BigInteger is numerically less than, 
	 * equal to, or greater than b

	 */
	public int compareTo(BigInteger b) {
		//TODO
		int [] first=this.number;
		int [] second=b.number;
		int count=0;
		int result=-1;
		
		if(this.sign==true&&b.sign==false) {
			return 1;
		}else if(this.sign==false&&b.sign==true) {
			return -1;
		}else if(this.sign==true&&b.sign==true) {
			if(first.length>second.length) {
				return 1;
			}else {
				for(int numbers:first) {
					if(numbers>second[count]) {
						return 1;
					}else if(numbers<second[count]){
						return -1;
					}
					count++;
				}
			}
		}else {
			if(first.length<second.length) {
				return 1;
			}else {
				for(int numbers:first) {
					if(numbers<second[count]) {
						return 1;
					}else if(numbers<second[count]){
						return -1;
					}
					count++;
				}
			}
			
		}
		return 0;
	}

	/**
	 * Returns an int array containing this BigInteger. 
	 * The array will contain each digit of this BigInteger as an element
	 * @return: an int array containing this BigInteger, ignore the sign
	 */
	public int[] toArray(){
		//TODO
		return this.number;
	}
	/**
	 * Check if the BigInteger is zero
	 * @return true if BigInteger value is zero. False otherwise
	 */
	public boolean isZero(){
		if(this.number[0]==0) {
			return true;
		}else
		return false;
	}
	/**
	 * Returns the length of this BigInteger. length is the number of digits in a BigInteger
	 * @return: length of this integer
	 */
	public int length(){
		//TODO
		return this.number.length;
	}

	/**
	 * Returns the sign of this BigInteger.
	 * @return: sign of this integer
	 */
	public boolean sign(){
		return sign;
	}



	/**
	 * returns the the BigInteger in string format. If the number is 
	 * negative, return string must start with a "-"
	 * @return BigInteger in a String format
	 */
	public String toString(){
		StringBuilder a=new StringBuilder();
		if(this.sign==true) {
			
		}else{
			a.append("-");
		}
		for(int c:this.number ) {
			String newone=String.valueOf(c);
			a.append(newone);
		}
		String b=new String(a);
		return b;

	}
	
	public boolean equals(BigInteger b) {
		if(this.number==b.number) {
			return false;
		}else {
		return true;
		}
	}

}
