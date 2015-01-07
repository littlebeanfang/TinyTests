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
	
	public void PrintSentAccuracy(String goldfile, String parsefile) throws Exception, IOException{
		int rightcount=0;
		int wrongcount=0;
		
		BufferedReader goldReader=new BufferedReader(new FileReader(goldfile));
		BufferedReader parseReader=new BufferedReader(new FileReader(parsefile));
		String goldlineString="";
		String parselinesString="";
		
		boolean senright=true;
		while((goldlineString=goldReader.readLine())!=null){
			parselinesString=parseReader.readLine();
			if(!goldlineString.equals("")){
				//System.out.println(goldlineString);
				int goldhead=Integer.parseInt(goldlineString.split("\t")[6]);
				int parsehead=Integer.parseInt(parselinesString.split("\t")[6]);
				if(goldhead==parsehead){
					//rightcount++;
				}else{
					senright=false;
				}
			}else{
				if(senright){
					rightcount++;
				}else{
					wrongcount++;
				}
				senright=true;
			}
		}
		System.out.println("Right count:"+rightcount);
		System.out.println("Wrong count:"+wrongcount);
		System.out.println("Total count:"+(rightcount+wrongcount));
		System.out.println("Right percentage:"+(double)rightcount/(rightcount+wrongcount));
		System.out.println("Wrong percentage:"+(double)wrongcount/(rightcount+wrongcount));
	}
	
	public static void main(String args[]) throws Exception{
		
		//String goldfileString=System.getenv("CODEDATA")+File.separator+"wsj_rand100_processindex.txt";
		//String goldfileString=System.getenv("CODEDATA")+File.separator+"wsj_00_malt_processindex.txt";
		//String goldfileString=System.getenv("CODEDATA")+File.separator+"wsj_2-21.conll";
		//String goldfileString=System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_00_01.conll";
//		String goldfileString=System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand100_forprocessindextest.conll";
		String danishgold=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"gold"+File.separator+"danish.txt";
		String dutchgold=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"gold"+File.separator+"dutch.txt";
		String portuguesegold=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"gold"+File.separator+"portuguese.txt";
		String slovenegold=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"gold"+File.separator+"slovene.txt";
		String swedishgold=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"gold"+File.separator+"swedish.txt";
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
//		String iter5=System.getenv("CODEDATA")+File.separator+"bean_wsj_200_10iter_avoidcircle_randprocessindex_parseout.txt";
		String danishbean=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"bean"+File.separator+"danish.txt";
		String dutchbean=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"bean"+File.separator+"dutch.txt";
		String portuguesebean=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"bean"+File.separator+"portuguese.txt";
		String slovenebean=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"bean"+File.separator+"slovene.txt";
		String swedishbean=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"bean"+File.separator+"swedish.txt";
		
		String danishmst=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"mst"+File.separator+"danish.txt";
		String dutchmst=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"mst"+File.separator+"dutch.txt";
		String portuguesemst=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"mst"+File.separator+"portuguese.txt";
		String slovenemst=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"mst"+File.separator+"slovene.txt";
		String swedishmst=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"mst"+File.separator+"swedish.txt";
		
		String danishmalt=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"malt"+File.separator+"danish.txt";
		String dutchmalt=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"malt"+File.separator+"dutch.txt";
		String portuguesemalt=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"malt"+File.separator+"portuguese.txt";
		String slovenemalt=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"malt"+File.separator+"slovene.txt";
		String swedishmalt=System.getenv("CODEDATA")+File.separator+"Multilingual"+File.separator+"Accuracy"+File.separator+"malt"+File.separator+"swedish.txt";
		
//		System.out.println("=====================malt danish");
//		ca.PrintAccuracy(danishgold, danishmalt);
//		System.out.println("=====================malt dutch");
//		ca.PrintAccuracy(dutchgold, dutchmalt);
//		System.out.println("=====================malt portuguese");
//		ca.PrintAccuracy(portuguesegold, portuguesemalt);
//		System.out.println("=====================malt slovene");
//		ca.PrintAccuracy(slovenegold, slovenemalt);
//		System.out.println("=====================malt swedish");
//		ca.PrintAccuracy(swedishgold, swedishmalt);
//		
//		System.out.println("=====================mst danish");
//		ca.PrintAccuracy(danishgold, danishmst);
//		System.out.println("=====================mst dutch");
//		ca.PrintAccuracy(dutchgold, dutchmst);
//		System.out.println("=====================mst portuguese");
//		ca.PrintAccuracy(portuguesegold, portuguesemst);
//		System.out.println("=====================mst slovene");
//		ca.PrintAccuracy(slovenegold, slovenemst);
//		System.out.println("=====================mst swedish");
//		ca.PrintAccuracy(swedishgold, swedishmst);
//		
//		System.out.println("=====================bean danish");
//		ca.PrintAccuracy(danishgold, danishbean);
//		System.out.println("=====================bean dutch");
//		ca.PrintAccuracy(dutchgold, dutchbean);
//		System.out.println("=====================bean portuguese");
//		ca.PrintAccuracy(portuguesegold, portuguesebean);
//		System.out.println("=====================bean slovene");
//		ca.PrintAccuracy(slovenegold, slovenebean);
//		System.out.println("=====================bean swedish");
//		ca.PrintAccuracy(swedishgold, swedishbean);
//		
//		System.out.println("=====================malt danish");
//		ca.PrintSentAccuracy(danishgold, danishmalt);
//		System.out.println("=====================malt dutch");
//		ca.PrintSentAccuracy(dutchgold, dutchmalt);
//		System.out.println("=====================malt portuguese");
//		ca.PrintSentAccuracy(portuguesegold, portuguesemalt);
//		System.out.println("=====================malt slovene");
//		ca.PrintSentAccuracy(slovenegold, slovenemalt);
//		System.out.println("=====================malt swedish");
//		ca.PrintSentAccuracy(swedishgold, swedishmalt);
//		
//		System.out.println("=====================mst danish");
//		ca.PrintSentAccuracy(danishgold, danishmst);
//		System.out.println("=====================mst dutch");
//		ca.PrintSentAccuracy(dutchgold, dutchmst);
//		System.out.println("=====================mst portuguese");
//		ca.PrintSentAccuracy(portuguesegold, portuguesemst);
//		System.out.println("=====================mst slovene");
//		ca.PrintSentAccuracy(slovenegold, slovenemst);
//		System.out.println("=====================mst swedish");
//		ca.PrintSentAccuracy(swedishgold, swedishmst);
//		
//		System.out.println("=====================bean danish");
//		ca.PrintSentAccuracy(danishgold, danishbean);
//		System.out.println("=====================bean dutch");
//		ca.PrintSentAccuracy(dutchgold, dutchbean);
//		System.out.println("=====================bean portuguese");
//		ca.PrintSentAccuracy(portuguesegold, portuguesebean);
//		System.out.println("=====================bean slovene");
//		ca.PrintSentAccuracy(slovenegold, slovenebean);
//		System.out.println("=====================bean swedish");
//		ca.PrintSentAccuracy(swedishgold, swedishbean);
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
		System.out.println("average:");
		String goldfileString=System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_00-01.conll";
		String parsefileString=System.getenv("CODEDATA")+File.separator+"bean_train_wsj2-21_godorder_beam1_test_bean_wsj00-01_easyorderfromgod_beam_2.out";
		
		ca.PrintAccuracy(goldfileString, parsefileString);
		/*
		System.out.println("iter 0:");
		parsefileString=System.getenv("CODEDATA")+File.separator+"BeanParser_Itertest"+File.separator+"MST_trainwsj2-21_test_wsj00-01_itertest_iter0.out";
		ca.PrintAccuracy(goldfileString, parsefileString);
		System.out.println("iter 1:");
		parsefileString=System.getenv("CODEDATA")+File.separator+"BeanParser_Itertest"+File.separator+"MST_trainwsj2-21_test_wsj00-01_itertest_iter1.out";
		ca.PrintAccuracy(goldfileString, parsefileString);
		System.out.println("iter 2:");
		parsefileString=System.getenv("CODEDATA")+File.separator+"BeanParser_Itertest"+File.separator+"MST_trainwsj2-21_test_wsj00-01_itertest_iter2.out";
		ca.PrintAccuracy(goldfileString, parsefileString);
		System.out.println("iter 3:");
		parsefileString=System.getenv("CODEDATA")+File.separator+"BeanParser_Itertest"+File.separator+"MST_trainwsj2-21_test_wsj00-01_itertest_iter3.out";
		ca.PrintAccuracy(goldfileString, parsefileString);
		System.out.println("iter 4:");
		parsefileString=System.getenv("CODEDATA")+File.separator+"BeanParser_Itertest"+File.separator+"MST_trainwsj2-21_test_wsj00-01_itertest_iter4.out";
		ca.PrintAccuracy(goldfileString, parsefileString);
		*/
	}
}
