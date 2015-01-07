import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ShuffleNumber {
	public ArrayList<Integer> GenerateNnum(int n){
		ArrayList<Integer> ret=new ArrayList<Integer>();
		while(ret.size()!=n){
			int rand=(int)(Math.random()*n)+1;
			if(!ret.contains(rand)){
				ret.add(rand);
			}
			//System.out.println(rand);
		}
		return ret;
	}
	public void tranverse(ArrayList<Integer> arr){
		Iterator iterator=arr.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	public static void main(String args[]){
		ShuffleNumber sfNumber=new ShuffleNumber();
		ArrayList<Integer> st=sfNumber.GenerateNnum(25);
		sfNumber.tranverse(st);
	}
}
