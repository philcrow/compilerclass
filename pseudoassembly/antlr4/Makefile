# abandoned in favor of build.gradle
PALLexer.class : PALLexer.java
	cd build; javac *.java

PALLexer.java : PAL.g4
	java -jar /usr/local/lib/antlr-4.10.1-complete.jar PAL.g4 -o build

.PHONY: clean
clean:
	rm -rf build

#	rm -f PALLexer.*
#	rm -f PALParser*
#	rm -f *Listener*
#	rm -f *.tokens
#	rm -f *.interp

