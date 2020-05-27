package LottoDemo;

import java.util.*;

public class LottoDemo {
	
	public static ArrayList autoRandom() {
		ArrayList<Integer> making = new ArrayList<Integer>();
		int randNum;
		int count =0;
		

		while(count !=6) {
			randNum = (int)(Math.random()*44+1);
			if(count ==0) {
				making.add(randNum);
				count++;
			}
			else {
				if(making.contains(randNum)) {
					continue;
				}else {
					making.add(randNum);
					count++;
				}
			}		
		}
		Collections.sort(making);
		return making;
	}
	
	public static void printNum(ArrayList<ArrayList<Integer>> a) {
		for(int i=0;i<a.size();i++) {
			System.out.println(a.get(i).toString());
		}
	}
	
	public static String compareNum(ArrayList<Integer> a, ArrayList<Integer> b, int bonus) {
		ArrayList c = new ArrayList();
		int count =0;
		int sub=0;
		
		for(int k : a) {
			if(b.contains(k)) {
				c.add(k);
				count++;
			}
		}
		
		if(b.contains(bonus)) {sub++;}
		
		if(count ==6) {return "1��";}
		else if(count ==5 &&b.contains(bonus)) {return "2��";}
		else if(count ==5) {return "3��";}
		else if(count ==4||(count==3&&sub==1)) {return "4��";}
		else if(count ==3||(count==2&&sub==1)) {return "5��";}
		else return "��";
	}

	public static void main(String[] args) {
		Scanner price = new Scanner(System.in);
		
		System.out.println("���� �ݾ��� �Է��� �ּ���.");
		int getPrice = price.nextInt();
		
		System.out.println("�������� ������ �ζ� ���� �Է��� �ּ���.");
		int handWorked = price.nextInt();
		int autoMatic = getPrice/1000-handWorked;
		System.out.println("�������� ������ ��ȣ�� �Է��� �ּ���.");
		String[] handWrite = new String[handWorked]; //String���� �� ������ �ִٰ� �������� ����Ʈ�� ���?
		String empty; // ������ �Է¹��� nextInt�� ����(���ప \n)���� �ڿ� ������Ƿ� �װ� �������־ �ڿ� nextLine���� �޴� ���� �� ����ǵ���
		empty = price.nextLine();
		
		for(int i=0;i<handWorked;i++) {
			handWrite[i] = price.nextLine();
		}
		
		int[][] storeNum = new int[handWorked][6];
		String[][] strs = new String[handWorked][6]; // ������ķ� �����ؼ� �� ���ڰ��� ������ ��
		for(int j=0;j<handWorked;j++) {
			strs[j] = handWrite[j].trim().split(",");
		}
		for(int j=0;j<handWorked;j++) {
			for(int i=0;i<6;i++) {
				storeNum[j][i] = Integer.parseInt(strs[j][i]);
			}
		}
		
		ArrayList<ArrayList<Integer>> writeNum = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> arrToList =null;
		
		for(int j=0;j<handWorked;j++) {
			arrToList = new ArrayList<Integer>(); 
			for(int i=0;i<6;i++) {
				arrToList.add(storeNum[j][i]);  // �������� �Է��� �ζǹ�ȣ ��Ʈ�� ArrayList�� ����
			}writeNum.add(arrToList);
		}
		
		System.out.println("�������� "+handWorked+"��, �ڵ����� "+autoMatic+"���� �����߽��ϴ�.");
		
		for(int i=0;i<autoMatic;i++) {
			writeNum.add(autoRandom());
		}
		
		printNum(writeNum);
		
		System.out.println("���� �� ��÷ ��ȣ�� �Է��� �ּ���."); 
		String lastWeek;
		lastWeek = price.nextLine();
		System.out.println("���ʽ� ���� �Է��ϼ���.");
		int bonus = price.nextInt();
		
		ArrayList<Integer> lastNum = new ArrayList<Integer>();
		int[] lastNum_Int = new int[6];
		String[] lastNum_Str = new String[6];
		for(int i=0;i<6;i++) {
			lastNum_Str = lastWeek.trim().split(",");
		}
		for(int i=0;i<6;i++) {
			lastNum_Int[i] = Integer.parseInt(lastNum_Str[i]);
			lastNum.add(lastNum_Int[i]);
		}
		
		System.out.println("��÷ ���");
		System.out.println("--------------------");// ������� �׽�Ʈ �Ϸ�!!!!!! �ƿ� ����� ȭ����
		int first=0,second=0,third=0,forth=0,fifth =0;
		for(int i=0;i<writeNum.size();i++) {
			if(compareNum(lastNum,writeNum.get(i),bonus)=="1��") {first++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="2��"){second++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="3��"){third++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="4��"){forth++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="5��"){fifth++;}
		}
		int total = fifth*5000+forth*50000+third*1500000+second*30000000+first*2000000000;
		double totalPercent = ((double)total/(double)getPrice)*100;
		System.out.println("3�� ��ġ (5000��)- "+fifth+"��");
		System.out.println("4�� ��ġ (50000��)- "+forth+"��");
		System.out.println("5�� ��ġ (1500000��)- "+third+"��");
		System.out.println("5�� ��ġ, ���ʽ� �� ��ġ (30000000��)- "+second+"��");
		System.out.println("6�� ��ġ (2000000000��)- "+first+"��");
		
		System.out.println("�� ���ͷ��� "+String.format("%.1f", totalPercent)+"%�Դϴ�.");
		
	}

}
