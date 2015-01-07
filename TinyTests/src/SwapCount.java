import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SwapCount {
	public int[] CountFromMaltDiagFile(String maltdiagfile) throws IOException{
		int[] count={0,0,0,0,0,0,0,0};
		BufferedReader reader=new BufferedReader(new FileReader(maltdiagfile));
		String line=reader.readLine();
		while(line!=null){
			String actions[]=line.split(" ");
			int tempcount=0;
			boolean lastsw=false;
			for(int i=0;i<actions.length;i++){
				if(actions[i].equals("SW")){
					//cur=SW,tempcount
					tempcount++;
					lastsw=true;
				}else if(lastsw==true){
					//update count[]
					if(tempcount>8){
						System.out.println("count > 8");
						continue;
					}
					count[tempcount-1]++;
					tempcount=0;
					lastsw=false;
				}
			}
			line=reader.readLine();
		}
		return count;
	}
	public void PrintAns(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.println("length="+(i+1)+", count="+arr[i]);
		}
	}
	public static void main(String args[]) throws IOException{
		SwapCount sc=new SwapCount();
		System.out.println("swedish_train_diag.txt======continous SW");
		int[] arr=sc.CountFromMaltDiagFile("swedish_train_diag.txt");
		sc.PrintAns(arr);
		System.out.println("swedish_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("swedish_diag.txt");
		sc.PrintAns(arr);
		System.out.println("slovene_train_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("slovene_train_diag.txt");
		sc.PrintAns(arr);
		System.out.println("slovene_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("slovene_diag.txt");
		sc.PrintAns(arr);
		System.out.println("dutch_train_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("dutch_train_diag.txt");
		sc.PrintAns(arr);
		System.out.println("dutch_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("dutch_diag.txt");
		sc.PrintAns(arr);
		System.out.println("portuguese_train_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("portuguese_train_diag.txt");
		sc.PrintAns(arr);
		System.out.println("portuguese_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("portuguese_diag.txt");
		sc.PrintAns(arr);
		System.out.println("danish_train_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("danish_train_diag.txt");
		sc.PrintAns(arr);
		System.out.println("danish_diag.txt======continous SW");
		arr=sc.CountFromMaltDiagFile("danish_diag.txt");
		sc.PrintAns(arr);
	}
}
