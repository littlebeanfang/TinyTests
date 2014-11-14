import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Extract100SentenceFromWSJ {
	Map<Integer,String> instmap=new HashMap<Integer,String>();
	private int sentnum;
	public void Extract(String writefile, int count) throws IOException{
		File out=new File(writefile);
		if(!out.exists()){
			out.createNewFile();
		}
		FileWriter writer=new FileWriter(out);
		
		Random random=new Random();
		while(count!=0){
			int rand=random.nextInt(sentnum)+1;
			count--;
			writer.write(instmap.get(rand)+"\n");
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
	public static void main(String args[]) throws IOException{
		//String wsjfileString="wsj_2-21_malt_processindex_new.txt";
		//String writefileString="wsj_rand100_processindex_new.txt";
		//String wsjfileString="wsj_2-21.conll";
		String wsjfileString="wsj_2-21_malt_processindex_new.txt";
		String writefileString="wsj_first1000in2-21.txt";
		String writefilerand200="wsj_2-21_rand200_forprocessindextrain.txt";
		String writefilerand100="wsj_2-21_rand100_forprocessindextest.txt";
		Extract100SentenceFromWSJ test=new Extract100SentenceFromWSJ(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+wsjfileString);
		//test.ExtractFirstNSentence(System.getenv("CODEDATA")+File.separator+writefileString, 1000);
		test.Extract(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+writefilerand100, 100);
		test.Extract(System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+writefilerand200, 200);
	}
}
