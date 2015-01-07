import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.omg.CORBA.PUBLIC_MEMBER;


public class GenerateOrderByTreeFile {
	private BufferedReader reader;
	public GenerateOrderByTreeFile(){
		//for scanfile function
	}
	public GenerateOrderByTreeFile(String treefile) throws FileNotFoundException{
		//create conll file reader
		reader=new BufferedReader(new FileReader(treefile));
	}
	public Stack<String> GetOneTree(HashMap<String, String> parent_childs) throws IOException{
		String line=reader.readLine();
		//HashMap<String, String> parent_childs=new HashMap<String,String>();
		Stack<String> ret=new Stack<String>();
		//System.out.println(line);
		if(line==null){
			//System.out.println("return null");
			parent_childs=null;
			return null;
		}
		while(!line.equals("")){
			String columns[]=line.split("\t");
			String head=columns[6];
			String child=columns[0];
			if(parent_childs.containsKey(head)){
				parent_childs.put(head, parent_childs.get(head)+"\t"+child);
			}else{
				parent_childs.put(head, child);
			}
			line=reader.readLine();
			//System.out.println(line);
			if(line==null){
				break;
			}
		}
		Queue<String> temp=new LinkedList<String>();//offer,poll
		temp.offer("0");
		//StringBuffer sb=new StringBuffer();
		if(parent_childs.size()==0){
			return null;
		}
		while(temp.size()!=0){
			String curString=temp.poll();
			if(parent_childs.containsKey(curString)){
				//subtree
				//sb.append(curString+"\t");
				ret.push(curString);
				String childs[]=parent_childs.get(curString).split("\t");
				for(int i=childs.length-1;i>=0;i--){
					temp.offer(childs[i]);
				}
			}else{
				//leaf node
				//sb.append(curString+"\t");
				ret.push(curString);
			}
		}
		return ret;
	}
	public void ScanFile(String readfile, String writefile) throws IOException{
		File outFile=new File(System.getenv("CODEDATA")+File.separator+writefile);
		if(!outFile.exists()){
			outFile.createNewFile();
		}
		FileWriter writer=new FileWriter(outFile);
		
		GenerateOrderByTreeFile test=new GenerateOrderByTreeFile(System.getenv("CODEDATA")+File.separator+readfile);
		HashMap<String, String> map=new HashMap<String,String>();
		Stack<String> stack=test.GetOneTree(map);
		int count=1;
		while(stack!=null){
			System.out.println("\t"+count);
			String txt="";
			int index=1;
			while(!stack.empty()){
				txt+=index+":"+stack.pop()+"\t";
				index++;
			}
			writer.write(txt.trim()+"\n");
			map=new HashMap<String,String>();
			stack=test.GetOneTree(map);
			count++;
			//System.out.println(stack);
		}
		writer.close();
	}
	public void RewriteOrder(String conllfile, String godorderfile,String rewritefile) throws IOException{
		//call after calling ScanFile to get godorder file
		BufferedReader reader=new BufferedReader(new FileReader(System.getenv("CODEDATA")+File.separator+conllfile));
		BufferedReader orderReader=new BufferedReader(new FileReader(System.getenv("CODEDATA")+File.separator+godorderfile));
		File out=new File(System.getenv("CODEDATA")+File.separator+rewritefile);
		if(!out.exists()){
			out.createNewFile();
		}
		FileWriter writer=new FileWriter(out);
		String lineString=reader.readLine();
		
		ArrayList<String> instance=new ArrayList<String>();
		int count=0;
		while(lineString!=null){
			//not to the end
			if(lineString.equals("")&&instance.size()!=0){
				//sentence end
				count++;
				System.out.println(count);
				HashMap<String, String> order=this.OrderLineToMap(orderReader.readLine());
				if(instance.size()!=(order.size()-1)){
					System.out.println("length not match!");
				}else{
					for(int i=0;i<instance.size();i++){
						String tokenString=instance.get(i);
						writer.write(tokenString+"\t"+order.get(tokenString.split("\t")[0])+"\n");
					}
					writer.write("\n");
				}
				instance=new ArrayList<String>();
			}else{
				instance.add(lineString);
			}
			lineString=reader.readLine();
		}
		writer.close();
	}
	private HashMap<String, String> OrderLineToMap(String line){
		//return index-order, line is order-index
		String orders[]=line.split("\t");
		HashMap<String, String> retHashMap=new HashMap<String,String>();
		for(int i=0;i<orders.length;i++){
			String columns[]=orders[i].split(":");
			retHashMap.put(columns[1], columns[0]);
		}
		return retHashMap;
	}
	public void PrintMap(HashMap<String, String> map){
		Iterator iterator=map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry= (Entry<String, String>) iterator.next();
			System.out.println(entry.getKey()+","+entry.getValue());
		}
	}
	public static void main(String args[]) throws IOException{
		String filenameString="WSJ\\wsj_24.conll";
		String writefileString="wsj_24_godorder.pre";
		String rewritefileString="wsj_24_godorder_processindex.txt";
		/*
		GenerateOrderByTreeFile test=new GenerateOrderByTreeFile(System.getenv("CODEDATA")+File.separator+filenameString);
		HashMap<String, String> map=new HashMap<String,String>();
		Stack<String> stack=new Stack<String>();
		test.GetOneTree(map,stack);
		while(!stack.empty()){
			System.out.print("\t"+stack.pop());
		}
		*/
		GenerateOrderByTreeFile test=new GenerateOrderByTreeFile();
		test.ScanFile(filenameString, writefileString);
		test.RewriteOrder(filenameString, writefileString, rewritefileString);
	}
}
