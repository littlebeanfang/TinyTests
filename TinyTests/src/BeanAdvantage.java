import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class BeanAdvantage {
	/**
	 * check bean is right, but mst and malt both wrong
	 * @throws IOException 
	 */
	private TreeMap<String , Integer> AdRelationMap=new TreeMap<String,Integer>();
	public void CheckAndPrint(String beanparse, String maltparse, String mstparse, String goldparse) throws IOException{
		//print sentence # and the total number of this kind
		//may not pass beanparsefile to first argument, then could get comparison of other 2 parser
		System.out.println("===============Advantage Test================");
		System.out.println("AdvantageParser:"+beanparse);
		System.out.println("CompareParser1:"+maltparse);
		System.out.println("CompareParser2:"+mstparse);
		System.out.println("Gold:"+goldparse);
		BufferedReader goldReader=new BufferedReader(new FileReader(goldparse));
		BufferedReader beanReader=new BufferedReader(new FileReader(beanparse));
		BufferedReader maltReader=new BufferedReader(new FileReader(maltparse));
		BufferedReader mstReader=new BufferedReader(new FileReader(mstparse));
		
		String goldline;
		String beanline;
		String maltline;
		String mstline;
		int sentcount=1;
		int tokencount=0;
		int advantagetokencount=0;
		boolean advantagesent=false;
		while((goldline=goldReader.readLine())!=null){
			if(goldline.equals("")){
				
				if(advantagesent){
					System.out.print(" "+sentcount);
					advantagesent=false;
				}
				beanline=beanReader.readLine();
				maltline=maltReader.readLine();
				mstline=mstReader.readLine();
				sentcount++;
			}else{
				tokencount++;
				beanline=beanReader.readLine();
				maltline=maltReader.readLine();
				mstline=mstReader.readLine();
//				System.out.println("goldline:"+goldline);
//				System.out.println("beanline:"+beanline);
//				System.out.println("maltline:"+maltline);
//				System.out.println("mstline:"+mstline);
//				System.out.println("line num:"+(tokencount+sentcount));
				if(beanline==null){
					System.out.println("bean end !");
				}else if(maltline==null){
					System.out.println("malt end !");
				}else if(mstline==null){
					System.out.println("mst end !");
				}
				String goldhead=goldline.split("\t")[6];
				String beanhead=beanline.split("\t")[6];
				String malthead=maltline.split("\t")[6];
				String msthead=mstline.split("\t")[6];
				if(goldhead.equals(beanhead)&&!goldhead.equals(malthead)&&!goldhead.equals(msthead)){
					advantagesent=true;
					advantagetokencount++;
					this.PushAdMap(goldline.split("\t")[7]);
				}
			}
		}
		System.out.println();
		System.out.println("Ad count:"+advantagetokencount);
		System.out.println("Total count:"+tokencount);
		System.out.println("Percentage:"+(double)advantagetokencount/tokencount);
	}
	private void PushAdMap(String relationname){
		if(this.AdRelationMap.containsKey(relationname)){
			this.AdRelationMap.put(relationname, this.AdRelationMap.get(relationname)+1);
		}else{
			this.AdRelationMap.put(relationname, 1);
		}
	}
	public void PrintMap(){
		for(java.util.Iterator iterator=this.AdRelationMap.entrySet().iterator();iterator.hasNext();){
			Map.Entry<String, Integer> entry=(Map.Entry<String, Integer>) iterator.next();
			//System.out.println("Relation:"+entry.getKey()+" Count:"+entry.getValue());
			System.out.println(""+entry.getKey()+"\t"+entry.getValue());
		}
		
	}
	public static void main(String args[]) throws IOException{
		BeanAdvantage ba=new BeanAdvantage();
		ba.CheckAndPrint("D:\\Study\\Data\\BeanParserV1Test\\bean_wsj_2-21_10iter_avoidcircle_parseout.txt","D:\\Study\\Data\\BeanParserV1Test\\mst_51\\mst_2order_wsj2-21_proj_iter10_51_parseout.txt","D:\\Study\\Data\\BeanParserV1Test\\malt_eng_wsj2-21_00-01test.conll",   "D:\\Study\\Data\\WSJ\\wsj_00-01.conll");
		ba.PrintMap();
	}
}
