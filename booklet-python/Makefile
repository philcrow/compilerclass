.PHONY: build clean
  
build: $(patsubst %.dot,%.png,$(wildcard *.dot))

%.png: %.dot
	dot -Tpng $< -o $@

clean:
	-rm *.png
