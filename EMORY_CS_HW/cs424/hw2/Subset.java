import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Subset {

public static void main(String [] args) throws IOException{
FileInputStream file = new FileInputStream("/home/qzhan37/cs424/hw2/1.txt");

BufferedReader keyboard = new BufferedReader(new InputStreamReader(file));
BufferedReader keyboard1 = new BufferedReader(new InputStreamReader(System.in));
HashMap<String, Set<String>> lal = new HashMap<String, Set<String>>();

Set<State> States = new HashSet<State>();
Set<State> StatesD = new HashSet<State>();
Set<Character> Alphabet = new HashSet<Character>();
State state = null;
Set<State> Accepts = new HashSet<State>();
char ch;
System.out.println("import java.io.*;");
System.out.println("import java.util.*;");
System.out.println("public class FSM {");
System.out.print("public enum States {");

String states = keyboard.readLine();
StringTokenizer st1 = new StringTokenizer(states," ");
while(st1.hasMoreTokens()){
String i = st1.nextToken();
States.add(new State(i));

}
Set<Set<State>> all = powerSet(States);
ArrayList<String> in = new ArrayList<String>();
for(Set<State> i: all){

in = new ArrayList<String>();
StringBuilder bs = new StringBuilder();
bs.append("D");
for(State j: i){
String p=j.s_name;
in.add(p);
bs.append(p);
}



StatesD.add(new State(bs.toString(),in));

}
for(State i: StatesD){
System.out.print(i.s_name+",");
}
System.out.println("}");
System.out.println("public static void main(String [] args) throws IOException{");
System.out.println("Reader keyboard = new InputStreamReader (System.in);");
System.out.println("Set<Character> Alphabet = new HashSet<Character>();");
System.out.println("Set<States> Accepts = new HashSet<States>();");

String Alpha = keyboard.readLine();
StringTokenizer st2 = new StringTokenizer(Alpha," ");
while(st2.hasMoreTokens()){
String i = st2.nextToken();

Alphabet.add(i.charAt(0));
System.out.println("Alphabet.add('"+i.charAt(0)+"');");
}




//initial state
String initial = keyboard.readLine();
StringTokenizer st3 = new StringTokenizer(initial," ");
while(st3.hasMoreTokens()){
String i = st3.nextToken();

for(State j:StatesD){
if(j.s_name.equals("D"+i)){
state = j;
System.out.println("States state = States."+j.s_name+";");}
}
}


//final states
String finalstate = keyboard.readLine();
StringTokenizer st4 = new StringTokenizer(finalstate," ");
while(st4.hasMoreTokens()){
String i = st4.nextToken();


for(State j:StatesD){
if(j.s_name.contains(i)){
Accepts.add(j); 
System.out.println("Accepts.add(States."+j.s_name+");");}
}
}

System.out.println("char ch;");
System.out.println("while((ch = (char) keyboard.read()) != '"+"\\" +"n'){");
System.out.println("if(! Alphabet.contains(ch)){");
System.out.println("System.out.println(\"invalid character\" + ch);");
System.out.println("System.exit(0);");
System.out.println("}");

String line = null;

while ((line = keyboard.readLine()) != null) {

StringTokenizer st = new StringTokenizer(line," ");

String source = st.nextToken();
String alpha = st.nextToken();
String target = st.nextToken();




AddTransition(source,alpha.charAt(0),target,StatesD);

}

lal =empty(StatesD);


ConvertTransition1(StatesD,lal);




addTransitionAux(Alphabet,StatesD);


System.out.println("switch (state){");
for(State i : StatesD){
System.out.println("case "+ i.s_name+":");
for(Character j: Alphabet){
if(check(i,j,StatesD)==null){
System.out.println("if(ch == '"+j+"'){state = States.D;}");
}
else{
System.out.println("if(ch == '"+j+"'){ state = States." +check(i,j,StatesD).s_name+";}");
}
}
System.out.println("break;");
}
System.out.println("}");
System.out.println("}");
System.out.println("if(Accepts.contains(state))");
System.out.println("System.out.println(\"Yes\");");
System.out.println("else");
System.out.println("System.out.println(\"No\");");
System.out.println("}");
System.out.println("}");

}


public static HashMap<String, Set<String>> empty(Set<State> StatesD){
HashMap<String, Set<String>> lal = new HashMap<String, Set<String>>();
for(State j : StatesD){
String name = j.s_name;
Set<String> sets = new HashSet<String>();
Set<Character> all = j.t_transition.keySet();

for(Character i:all){
if(i=='~'){
sets = j.t_transition.get(i);
}
}
lal.put(name,sets);
}
return lal;
}

public static Set<Set<State>> powerSet(Set<State> originalSet) {
Set<Set<State>> sets = new HashSet<Set<State>>();


if (originalSet.isEmpty()) {
sets.add(new HashSet<State>());
return sets;
}

List<State> list = new ArrayList<State>(originalSet);
State head = list.get(0);
Set<State> rest = new HashSet<State>(list.subList(1, list.size()));
for (Set<State> set : powerSet(rest)) {
Set<State> newSet = new HashSet<State>();
newSet.add(head);
newSet.addAll(set);
sets.add(newSet);
sets.add(set);
}
return sets;
}
public static void AddTransitionf(Set<State> All){

for(State j: All){
String x = j.s_name;
Set<String> s = new HashSet<String>();
s.add(x);
j.t_transition.put('~',s);

}

}

public static void AddTransition(String fst, char i, String snd, Set<State> All){

Set<State> sources = find(fst,All);
Set<String> target = new HashSet<String>();
//System.out.println(snd);
//System.out.println(source.t_transition.get(i));
for(State source: sources){
if(source.t_transition.get(i) == null){
//System.out.println("abab");
target.add(snd);
// System.out.println(target);
source.t_transition.put(i,target);
target = new HashSet<String>();
//System.out.println(source.t_transition.get(i));}
}
else{
source.t_transition.get(i).add(snd);

}
}

}

public static Set<State> find(String name,Set<State> States){
Set<State> d = new HashSet<State>();


for(State j:States){
if(j.s_name.contains(name))
d.add(j);

}
return d;

}

public static State find(int num,Set<State> States){
Set<State> d = new HashSet<State>();


for(State j:States){
if(j.id == num)
return j;
}
return null;

}

public static void addTransitionAux(Set<Character> Alphabet,Set<State> StatesD){
for(State j : StatesD){
String name = j.s_name;
int number = j.id;
Set<Character> all = j.t_transition.keySet();

for(Character i:Alphabet){

Set<String> con = j.t_transition.get(i);

State num = contvalue(con,StatesD);

AddTransition(j,i,num);
}


}



}

public static void ConvertTransition1(Set<State> StatesD,HashMap<String, Set<String>> lal){


int c = 0;
for(State j : StatesD){
String name = j.s_name;
int number = j.id;
Set<Character> all = j.t_transition.keySet();

for(Character i:all){





Set<String> con = j.t_transition.get(i);

State num = contvalue(con,StatesD);
StringBuilder sb = new StringBuilder();

if(lal.containsKey(num.s_name)){
Set<String> hehe = lal.get(num.s_name);
if(!con.containsAll(hehe)){
con.addAll(hehe);
j.t_transition.put(i,con);
}

}
}

}


}
public static void AddTransition(State fst, char i, State snd){


fst.s_transition.put(i,snd);

}
public static State contvalue(Set<String> set,Set<State> States){
for(State i:States){
if(i.content.equals(set))
return i;
}
return null;

}
public static State check(State source, char i,Set<State> All){
   
   
    State to = source.s_transition.get(i);
return to;
}

}
