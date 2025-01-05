
FLEX := flex
CC := gcc



all: lex.yy.c counter 


lex.yy.c: ltest.l
	$(FLEX) $^

counter: lex.yy.c
	$(CC) $^ -o $@ 


.PHONY: clean
clean:
	rm lex.yy.c counter
