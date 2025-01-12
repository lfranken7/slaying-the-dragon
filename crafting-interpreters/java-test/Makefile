
CC := javac
JRE := java

Test.class: Test.java
	$(CC) $^

.PHONY: run
run: Test.class
	$(JRE) Test 

clean:
	rm Test.class 
