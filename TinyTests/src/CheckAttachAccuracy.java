import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;


public class CheckAttachAccuracy {
	public void PrintAccuracy(String goldfile, String parsefile) throws IOException{
		int rightcount=0;
		int wrongcount=0;
		
		BufferedReader goldReader=new BufferedReader(new FileReader(goldfile));
		BufferedReader parseReader=new BufferedReader(new FileReader(parsefile));
		String goldlineString="";
		String parselinesString="";
		while((goldlineString=goldReader.readLine())!=null){
			parselinesString=parseReader.readLine();
			if(!goldlineString.equals("")){
				//System.out.println(goldlineString);
				int goldhead=Integer.parseInt(goldlineString.split("\t")[6]);
				int parsehead=Integer.parseInt(parselinesString.split("\t")[6]);
				if(goldhead==parsehead){
					rightcount++;
				}else{
					wrongcount++;
				}
			}
		}
		System.out.println("Right count:"+rightcount);
		System.out.println("Wrong count:"+wrongcount);
		System.out.println("Total count:"+(rightcount+wrongcount));
		System.out.println("Right percentage:"+(double)rightcount/(rightcount+wrongcount));
		System.out.println("Wrong percentage:"+(double)wrongcount/(rightcount+wrongcount));
	}
	
	public static void main(String args[]) throws IOException{
		
		//String goldfileString=System.getenv("CODEDATA")+File.separator+"wsj_rand100_processindex.txt";
		//String goldfileString=System.getenv("CODEDATA")+File.separator+"wsj_00_malt_processindex.txt";
		//String goldfileString=System.getenv("CODEDATA")+File.separator+"wsj_2-21.conll";
		String goldfileString=System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_00_01.conll";
		CheckAttachAccuracy ca=new CheckAttachAccuracy();
		/*
		String mstfileString=System.getenv("CODEDATA")+File.separator+"mst_rand100_mstout.txt";
		String beanfileString=System.getenv("CODEDATA")+File.separator+"mstmodel_rand100_parseout.txt";
		String mstfile_2order_String=System.getenv("CODEDATA")+File.separator+"mst_rand100_mstout_2order.txt";
		//String beantrain=System.getenv("CODEDATA")+File.separator+"bean_rand100_train_parseout.txt";
		String beantrain=System.getenv("CODEDATA")+File.separator+"rand100_traintest.txt";
		
		System.out.println("=====================MST out");
		ca.PrintAccuracy(goldfileString, mstfileString);
		System.out.println("=====================Bean out");
		ca.PrintAccuracy(goldfileString, beanfileString);
		System.out.println("=====================MST_2order out");
		ca.PrintAccuracy(goldfileString, mstfile_2order_String);
		System.out.println("=====================Bean train out");
		ca.PrintAccuracy(goldfileString, beantrain);
		*/
//		String iter1=System.getenv("CODEDATA")+File.separator+"afterdebug_wsj_rand100_processindex.txt";
//		System.out.println("=====================afterdebug_wsj_rand100_processindex");
//		ca.PrintAccuracy(goldfileString, iter1);
//		
//		String iter3=System.getenv("CODEDATA")+File.separator+"mst_1order_rand100_parseout.txt";
//		System.out.println("=====================mst_1order_rand100_parseout");
//		ca.PrintAccuracy(goldfileString, iter3);
		
		//String iter5=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_2order_mst2-21_nonproj_iter10_51_parseout.txt";
		String iter5=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"bean_wsj_2-21_10iter_avoidcircle_parseout.txt";
		
		System.out.println("=====================bean_wsj_2-21_10iter_avoidcircle_parseout");
		ca.PrintAccuracy(goldfileString, iter5);
		/*
		String iter7=System.getenv("CODEDATA")+File.separator+"rand100_traintest_7iter.txt";
		System.out.println("=====================7 iter");
		ca.PrintAccuracy(goldfileString, iter7);
		String iter10=System.getenv("CODEDATA")+File.separator+"rand100_traintest_10iter.txt";
		System.out.println("=====================10 iter");
		ca.PrintAccuracy(goldfileString, iter10);
		String iter15=System.getenv("CODEDATA")+File.separator+"rand100_traintest_15iter.txt";
		System.out.println("=====================15 iter");
		ca.PrintAccuracy(goldfileString, iter15);
		*/
	}
}
