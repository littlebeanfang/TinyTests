import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class CheckNonprojectivity {
	/**
	 * this class is used to check the nonprojectivity of parse tree
	 * @throws IOException 
	 */
	public void Check(String conllfile) throws IOException{
		System.out.println("Check Nonprojectivity of file:"+conllfile);
		BufferedReader reader=new BufferedReader(new FileReader(conllfile));
		HashMap<Integer, Integer> dependentmap_head=new HashMap<Integer, Integer>();
		int tokennum=0;
		int crossnum=0;
		String lineString;
		//loop:for every instance
		while((lineString=reader.readLine())!=null){
			//System.out.println("line:"+lineString);
			if(lineString.equals("")){
				//end of instance
				//step 1:read instance to fill map, count token
				//step 2: for every arc, to check whether cross other arcs, count cross
				//System.out.println("Size:"+dependentmap_head.size());
				Iterator iterator=dependentmap_head.entrySet().iterator();
				for(;iterator.hasNext();){
					Entry<Integer, Integer> entry=(Entry<Integer, Integer>)iterator.next();
					Iterator iterator2=dependentmap_head.entrySet().iterator();
					for(;iterator2.hasNext();){
						Entry<Integer, Integer> entry2=(Entry<Integer, Integer>)iterator2.next();
						if(entry2.getKey()!=entry.getKey()){
							//System.out.println("check:"+entry.getKey()+","+entry.getValue()+","+ entry2.getKey()+","+entry2.getValue());
							if(this.Cross(entry.getKey(), entry.getValue(), entry2.getKey(), entry2.getValue())){
								//System.out.println("===CROSS");
								crossnum++;
							}else{
								//System.out.println("===No");
							}
						}
					}
				}
				//step 3: clear map
				dependentmap_head=new HashMap<Integer,Integer>();
			}else{
				String[] columns=lineString.split("\t");
				int head=Integer.parseInt(columns[6]);
				int dependent=Integer.parseInt(columns[0]);
				dependentmap_head.put(dependent, head);
				//System.out.println("Size:"+dependentmap_head.size());
				tokennum++;
			}
		}
		crossnum/=2;
		System.out.println("Token num:"+tokennum);
		System.out.println("Cross num:"+crossnum);
		System.out.println("Nonproj:"+(double)crossnum/tokennum);
	}
	public boolean Cross(int arc1_node1, int arc1_node2, int arc2_node1, int arc2_node2){
		boolean cross=false;
		//step 1: position
		int arc1_left=arc1_node1<arc1_node2?arc1_node1:arc1_node2;
		int arc1_right=arc1_node1>arc1_node2?arc1_node1:arc1_node2;
		int arc2_left=arc2_node1<arc2_node2?arc2_node1:arc2_node2;
		int arc2_right=arc2_node1>arc2_node2?arc2_node1:arc2_node2;
		//System.out.println("arc1_node1:"+arc1_node1+",arc1_node2:"+arc1_node2+",arc2_node1:"+arc2_node1+",arc2_node2:"+arc2_node2);
		//System.out.println("arc1_left:"+arc1_left+",arc1_right:"+arc1_right+",arc2_left:"+arc2_left+",arc2_right:"+arc2_right);
		//step 2: 
		if(arc1_left<arc2_left){
			//arc 1 is on the left of arc2
			if(arc2_left>=arc1_right){
				cross=false;
			}else if(arc2_right<=arc1_right){
				cross=false;
			}else{
				cross=true;
			}
		}else if(arc1_left>arc2_left){
			//arc 1 is on the right of arc2
			cross=Cross(arc2_node1, arc2_node2, arc1_node1, arc1_node2);
		}else{
			return false;
		}
		return cross;
	}
	public static void main(String args[]) throws IOException{
		CheckNonprojectivity cnCheckNonprojectivity=new CheckNonprojectivity();
//		cnCheckNonprojectivity.Check("crosstest1.txt");
//		cnCheckNonprojectivity.Check("crosstest2.txt");
		cnCheckNonprojectivity.Check("danish_ddt_test.conll");
		cnCheckNonprojectivity.Check("danish_ddt_train.conll");
		cnCheckNonprojectivity.Check("dutch_alpino_test.conll");
		cnCheckNonprojectivity.Check("dutch_alpino_train.conll");
		cnCheckNonprojectivity.Check("portuguese_bosque_test.conll");
		cnCheckNonprojectivity.Check("portuguese_bosque_train.conll");
		cnCheckNonprojectivity.Check("slovene_sdt_test.conll");
		cnCheckNonprojectivity.Check("slovene_sdt_train.conll");
		cnCheckNonprojectivity.Check("swedish_talbanken05_test.conll");
		cnCheckNonprojectivity.Check("swedish_talbanken05_train.conll");
	}
}
