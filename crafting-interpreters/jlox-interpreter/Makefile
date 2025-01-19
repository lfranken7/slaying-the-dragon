
CC := javac
JRE := java

outputs := TokenType.class Token.class Scanner.class Lox.class

all:
	$(CC) -d . *.java
  

.PHONY: run
run: 
	$(JRE) com.dragon.lox.Lox 

clean:
	rm -rf com 
