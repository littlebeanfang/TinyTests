
public class AlgoLog {
	public static void main(String args[]){
		//enter a set of numbers, count the lg ratio
//		double log=Log2(6.9);
//		System.out.println("Ans:"+log);
		//double N[]={1000,2000,4000,8000};
		double time[]={0.001,0.003,0.015,0.061,0.255,1.081,4.482,18.827,78.740,330.553,1388.211};
		for(int i=0;i<time.length-1;i++){
			System.out.println(time[i+1]/time[i]+":"+Log2(time[i+1]/time[i]));
		}
		
	}
	public static double Log2(double num){
		return Math.log(num)/Math.log(2);
	}
}
