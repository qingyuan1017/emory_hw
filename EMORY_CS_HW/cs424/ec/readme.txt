For this extra credit program compile: javac Extra.java
usage: java Extra < input.txt
where input.txt is an input list of regular expression
or input the token line by line with ctrl+D as end of the input

The Extra.java program will run the RegToNFA.java,Subset.java and FSMtoProg.
Firstly, it will output a NFA file with program RegToNFA.
Then,it will automatically go though program Subset.java to generate a DFA.
Finaly, the DFA file goes to FSMtoProg.java to printout a tokenizer program.

The tokenizer program can tokenize the input file according to the regular expression we input at very begining.
Compile: javac Tokenizer.java
Usage java Tokenizer input.txt
it will tokenize the input file to a form that each token is in different line.
And add "~" and "<eof>" to mark the end of the file
After the process we can input our new form of file to ECC.java program

ECC.java is a program that transform CFG to PDA
it will check whether a output of tokenizer program is in the CFG.
The program needs input file for CFG with Pyb like language.

Qingyuan Zhang
