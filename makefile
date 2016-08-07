# Makefile for Assignment 3

top:
	javac -d classfiles -sourcepath src src/main/Kruskal.java

test:
	kruskal -r 0 data

clean:
	rm classfiles/*/*
