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
		
		if(count ==6) {return "1등";}
		else if(count ==5 &&b.contains(bonus)) {return "2등";}
		else if(count ==5) {return "3등";}
		else if(count ==4||(count==3&&sub==1)) {return "4등";}
		else if(count ==3||(count==2&&sub==1)) {return "5등";}
		else return "꽝";
	}

	public static void main(String[] args) {
		Scanner price = new Scanner(System.in);
		
		System.out.println("구입 금액을 입력해 주세요.");
		int getPrice = price.nextInt();
		
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		int handWorked = price.nextInt();
		int autoMatic = getPrice/1000-handWorked;
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		String[] handWrite = new String[handWorked]; //String으로 값 가지고 있다가 마지막에 리스트로 출력?
		String empty; // 위에서 입력받은 nextInt에 엔터(개행값 \n)값이 뒤에 따라오므로 그것 저장해주어서 뒤에 nextLine으로 받는 값이 잘 저장되도록
		empty = price.nextLine();
		
		for(int i=0;i<handWorked;i++) {
			handWrite[i] = price.nextLine();
		}
		
		int[][] storeNum = new int[handWorked][6];
		String[][] strs = new String[handWorked][6]; // 이중행렬로 구현해서 각 인자값을 저장한 뒤
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
				arrToList.add(storeNum[j][i]);  // 수동으로 입력한 로또번호 세트들 ArrayList에 저장
			}writeNum.add(arrToList);
		}
		
		System.out.println("수동으로 "+handWorked+"장, 자동으로 "+autoMatic+"장을 구매했습니다.");
		
		for(int i=0;i<autoMatic;i++) {
			writeNum.add(autoRandom());
		}
		
		printNum(writeNum);
		
		System.out.println("지난 주 당첨 번호를 입력해 주세요."); 
		String lastWeek;
		lastWeek = price.nextLine();
		System.out.println("보너스 볼을 입력하세요.");
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
		
		System.out.println("당첨 통계");
		System.out.println("--------------------");// 여기까지 테스트 완료!!!!!! 아오 힘들다 화이팅
		int first=0,second=0,third=0,forth=0,fifth =0;
		for(int i=0;i<writeNum.size();i++) {
			if(compareNum(lastNum,writeNum.get(i),bonus)=="1등") {first++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="2등"){second++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="3등"){third++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="4등"){forth++;}
			else if(compareNum(lastNum,writeNum.get(i),bonus)=="5등"){fifth++;}
		}
		int total = fifth*5000+forth*50000+third*1500000+second*30000000+first*2000000000;
		double totalPercent = ((double)total/(double)getPrice)*100;
		System.out.println("3개 일치 (5000원)- "+fifth+"개");
		System.out.println("4개 일치 (50000원)- "+forth+"개");
		System.out.println("5개 일치 (1500000원)- "+third+"개");
		System.out.println("5개 일치, 보너스 볼 일치 (30000000원)- "+second+"개");
		System.out.println("6개 일치 (2000000000원)- "+first+"개");
		
		System.out.println("총 수익률은 "+String.format("%.1f", totalPercent)+"%입니다.");
		
	}

}
