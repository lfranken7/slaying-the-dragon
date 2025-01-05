CC := gcc
FLEX := flex
CFLAGS := -g -c 
INCLUDES :=

TARGETS := readline.o step0_repl.o lex_scanner.c scanner.o step0


all: $(TARGETS) 

readline.o: readline.c 
	$(CC) $(CFLAGS) $^ $(INCLUDES) -o $@ 	

lex_scanner.c: lexer.l
	$(FLEX) -o $@ $^

scanner.o: lex_scanner.c
	$(CC) $(CFLAGS) $^ $(INCLUDES) -o $@

step0_repl.o: step0_repl.c 
	$(CC) $(CFLAGS) $^ $(INCLUDES) -o $@ 	

step0: readline.o step0_repl.o
	$(CC) $^ -o $@



clean:
	rm $(TARGETS)
