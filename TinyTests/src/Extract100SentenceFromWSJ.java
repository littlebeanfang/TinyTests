import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;


public class Extract100SentenceFromWSJ {
	Map<Integer,String> instmap=new HashMap<Integer,String>();
	private int sentnum;
	public void Extract(String writefile, int count) throws IOException{
		HashSet<Integer> sentsrecord=new HashSet<Integer>();
		File out=new File(writefile);
		if(!out.exists()){
			out.createNewFile();
		}
		FileWriter writer=new FileWriter(out);
		
		Random random=new Random();
		while(count!=0){
			int rand=random.nextInt(sentnum)+1;
			if(!sentsrecord.contains(rand)){
				count--;
				writer.write(instmap.get(rand)+"\n");
				sentsrecord.add(rand);
			}
		}
		writer.close();
	}
	public void ExtractFirstNSentence(String writefile, int N) throws IOException{
		File out=new File(writefile);
		if(!out.exists()){
			out.createNewFile();
		}
		FileWriter writer=new FileWriter(out);
		for(int i=1;i<=N;i++){
			writer.write(instmap.get(i)+"\n");
		}
		writer.close();
	}
	public Extract100SentenceFromWSJ(String wsjfile) throws IOException{
		BufferedReader reader=new BufferedReader(new FileReader(wsjfile));
		String lineString;
		StringBuffer sb=new StringBuffer();
		int count=1;
		while((lineString=reader.readLine())!=null){
			if(!lineString.equals("")){
				sb.append(lineString+"\n");
			}else{
				instmap.put(count,sb.toString());
				count++;
				sb.setLength(0);
			}
		}
		instmap.put(count,sb.toString());
		sentnum=count;
	}
	public void ExtractSentences(String writefile,String sennums) throws Exception{
		File out=new File(writefile);
		if(!out.exists()){
			out.createNewFile();
		}
		FileWriter writer=new FileWriter(out);
		String sennum[]=sennums.split("\t");
		for(int i=0;i<sennum.length;i++){
			int num=Integer.parseInt(sennum[i]);
			writer.write(instmap.get(num)+"\n");
		}
		writer.close();
	}
	public static void main(String args[]) throws IOException{
		//String wsjfileString="wsj_2-21_malt_processindex_new.txt";
		//String writefileString="wsj_rand100_processindex_new.txt";
		//String wsjfileString="wsj_2-21.conll";
		String wsjfileString="wsj_2-21_godorder_processindex.txt";
		String writefileString="wsj_2-21_rand1000_godorder.txt";
		String writefilerand10000="wsj_2-21_rand10000_godorder.txt";
		String writefilerand500="wsj_2-21_rand500_godorder.txt";
		String writefilerand200="wsj_2-21_rand200_forprocessindextest_2.txt";
		String writefilerand100="wsj_2-21_rand100_forprocessindextest_2.txt";
		Extract100SentenceFromWSJ test=new Extract100SentenceFromWSJ(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+wsjfileString);
		//test.ExtractFirstNSentence(System.getenv("CODEDATA")+File.separator+writefileString, 1000);
		//test.Extract(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+writefilerand1000, 1000);
		test.Extract(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+writefilerand10000, 10000);
		//test.Extract(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+writefilerand500, 500);
	}
}
