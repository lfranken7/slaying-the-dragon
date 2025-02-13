
CC := javac
JRE := java

OUTDIR := astout 

all:
	$(CC) -d . *.java
  

.PHONY: run
run: 
	$(JRE) com.dragon.tool.GenerateAst $(OUTDIR)

clean:
	rm -rf com 
