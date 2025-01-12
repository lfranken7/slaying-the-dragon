
CC := javac
JRE := java

targets := Lox.java
outputs := Lox.class

all: $(outputs)

Lox.class: Lox.java 
	$(CC) $^

.PHONY: run
run: Lox.class
	$(JRE) Lox 

clean:
	rm $(outputs) 
