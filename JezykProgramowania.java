import java.util.*;
import java.io.*;


// PRZEBO++ROBIC NA LONG ZAMIAST INTEGER  a najlepiej na more generic typu Object czy cos
/** 
 * Format linii wejsciowej:
 * 
 * token[x]:	b inc 5 if a > 1
 * x:			0  1  2 3  4 5 6
 *
 * 
 Przyklad uzycia:
 JezykProgramowania d=new JezykProgramowania();
 d.doAssigment("A",5);
 d.doAssigment("B",10);
 d.doAssigment("C",15);
 // if(A<B) prlongln(C);
  if(d.isConditionTrue("<",d.valueOfregister("A"),d.valueOfregister("B"))){
  System.out.prlongln(d.valueOfregister("C"));
  }

  Bardziej skomplikowany przyklad uzycia:
  Zalozmy ze mamy linie "b inc 5 if a > 1" rozbita splitem na tokeny:
 * 
 * token[x]:	b inc 5 if a > 1
 * x:			0  1  2 3  4 5 6

  // if(a>1) b=b+5; 
if(d.isConditionTrue(token[5], d.valueOfregister(token[4]), Integer.parseInt(token[6]))){
 d.doAssigment(token[0], d.mathematicalOperation(token[1], d.valueOfregister(token[0]), Integer.parseInt(token[2])));
}
 */


public class JezykProgramowaniaDay18
{
	HashMap <String, Long> rejestry=new HashMap<String, Long>();
	

	long mathematicalOperation(String op, long arg1, long arg2){
		HashMap<String, Integer> opMap=new HashMap<String, Integer>();
		opMap.put("add",1);
		opMap.put("mul",2);
		opMap.put("mod",3);

		switch(opMap.get(op)){
		case 1: return arg1+arg2;
		case 2: return arg1*arg2;
		case 3: return arg1%arg2;
		default: return 0;
		}
	}

	long mathematicalOperation(String op, String arg1, String arg2){
		HashMap<String, Integer> opMap=new HashMap<String, Integer>();
		opMap.put("add",1);
		opMap.put("mul",2);
		opMap.put("mod",3);

		switch(opMap.get(op)){
		case 1: return valueOf(arg1)+valueOf(arg2);
		case 2: return valueOf(arg1)*valueOf(arg2);
		case 3: return valueOf(arg1)%valueOf(arg2);
		default: return 0;
		}
	}

	long twoArgOperation(String op, String arg1, String arg2){
		HashMap<String, Integer> opMap=new HashMap<String, Integer>();
		opMap.put("set",1);
		opMap.put("jgz",2);
		opMap.put("add",3);
		opMap.put("mul",4);
		opMap.put("mod",5);

		

		switch(opMap.get(op)){
		case 1: doAssigment(arg1, valueOf(arg2));
		break;
		case 2:if(valueOf(arg1)>0) return Integer.parseInt(arg2);
		break;
		case 3: doAssigment(arg1, valueOf(arg1)+valueOf(arg2));
		break;
		case 4: doAssigment(arg1, valueOf(arg1)*valueOf(arg2));
		break;
		case 5: doAssigment(arg1, valueOf(arg1)%valueOf(arg2));
		break;
		
		default: 
			break;
		}
		return 1;
	}


	long oneArgOperation(String op, String arg1){
		HashMap<String, Integer> opMap=new HashMap<String, Integer>();
		opMap.put("snd",1);
		opMap.put("rcv",2);
		//opMap.put("mod",3);

		switch(opMap.get(op)){
		case 1: System.out.println("Playing at freq: "+valueOf(arg1));
		break;
		case 2:if(valueOf(arg1)!=0) {
			System.out.println("Recovery reached. End of song");
			System.exit(0);
		}
		break;
		//case 3: return arg1%arg2;
		default: 
			break;
		}
		return 1;
	}

	void doAssigment(String register, long value){
		rejestry.put(register,value);
	}


	long valueOfregister(String register){
		if(rejestry.containsKey(register)){
			return rejestry.get(register);	
		}else{
			/*
			 * najlepiej rzuc wyjatkiem, ale na razie po prostu zakoncz program
			 */
			System.err.format("NO '%s' REGISTER FOUND!\n",register);
			System.exit(0);
			return 0;
		}
	}
	
	
	/*
		 * more generic method
		 * token can be a name of a register or longeger number wrapped in String
		 */
		long valueOf(String token){
			if(rejestry.containsKey(token)){
				return rejestry.get(token);	
			}else{
				try{
					return Integer.parseInt(token);
				} catch (NumberFormatException e){
					System.err.format("NO '%s' REGISTER FOUND!\n",token);
					System.err.format("'%s' IS NEIGHTER A VALID INTEGER!\n",token);
					System.exit(0);
				}
				/*
				 * najlepiej rzuc wyjatkiem, ale na razie po prostu zakoncz program
				 */
				System.err.format("NO '%s' REGISTER FOUND!\n",token);
				System.exit(0);
				return 0;
			}

	}


	boolean isConditionTrue(String op,long arg1, long arg2){

		HashMap<String, Integer> opMap=new HashMap<String, Integer>();
		opMap.put(">",1);
		opMap.put(">=",2);
		opMap.put("<",3);
		opMap.put("<=",4);
		opMap.put("==",5);
		opMap.put("!=",6);
		opMap.put("<>",6);
		opMap.put("><",6);

		switch(opMap.get(op)){
		case 1: return arg1>arg2;
		case 2: return arg1>=arg2;
		case 3: return arg1<arg2;
		case 4: return arg1<=arg2;
		case 5: return arg1==arg2;
		case 6: return arg1!=arg2;
		default: return false;
		}

	}

} // end of class
