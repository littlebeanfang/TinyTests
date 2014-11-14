import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


public class WriteProcessOrder {
	/**
	 * this class is used to add process index randomly or orderly
	 * @throws IOException 
	 */
	FileWriter writer;
	public WriteProcessOrder(){
		
	}
	public WriteProcessOrder(String writefile) throws IOException{
		File outFile=new File(writefile);
		if(!outFile.exists()){
			outFile.createNewFile();
		}
		this.writer=new FileWriter(outFile);
	}
	public void WriteOrder(String conllfile,boolean rand) throws IOException{
		ArrayList<String> instlineStrings=new ArrayList<String>();//removeall
		BufferedReader reader=new BufferedReader(new FileReader(conllfile));
		String lineString;
		while((lineString=reader.readLine())!=null){
			if(lineString.equals("")){
				int length=instlineStrings.size();
				//get order
				int[] order;
				if(rand){
					order=this.GetRandOrder(length);
				}else{
					order=this.GetIncOrder(length);
				}
				//write file
				for(int i=0;i<length;i++){
					writer.write(instlineStrings.get(i)+"\t"+order[i]+"\n");
				}
				writer.write("\n");
				writer.flush();
				//clear list
				instlineStrings=new ArrayList<String>();
			}else{
				instlineStrings.add(lineString);
			}
		}
		writer.close();
	}
	public int[] GetRandOrder(int m){
		ArrayList list=new ArrayList();
		int[] ret=new int[m];
		for(int i=1;i<=m;i++){
			list.add(i);
		}
		Collections.shuffle(list);
		Iterator iterator=list.iterator();
		int count=0;
		while(iterator.hasNext()){
			ret[count++]=(int) iterator.next();
			//System.out.println(ret[count-1]+"\t");
		}
		return ret;
	}
	public int[] GetIncOrder(int m){
		int[] ret=new int[m];
		for(int i=1;i<=m;i++){
			ret[i-1]=i;
			//System.out.println(ret[i-1]+"\t");
		}
		return ret;
	}
	public static void main(String args[]) throws IOException{
		//WriteProcessOrder wpo=new WriteProcessOrder();
		//wpo.GetRandOrder(10);
		//wpo.GetIncOrder(10);
		WriteProcessOrder wpo1=new WriteProcessOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand100_forprocessindextest_randprocessindex.txt");
		wpo1.WriteOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand100_forprocessindextest.conll",true);
		WriteProcessOrder wpo2=new WriteProcessOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand200_forprocessindextrain_randprocessindex.txt");
		wpo2.WriteOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand200_forprocessindextrain.conll",true);
		WriteProcessOrder wpo3=new WriteProcessOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand100_forprocessindextest_incprocessindex.txt");
		wpo3.WriteOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand100_forprocessindextest.conll",false);
		WriteProcessOrder wpo4=new WriteProcessOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand200_forprocessindextrain_incprocessindex.txt");
		wpo4.WriteOrder(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_2-21_rand200_forprocessindextrain.conll",false);
	}
}
