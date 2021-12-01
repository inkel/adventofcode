package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Printf("Day 1.1: %d\n", day1_1(readfile("testdata/day1_1.txt")))
}

func readfile(n string) string {
	data, err := os.ReadFile(n)
	if err != nil {
		panic(err)
	}
	return string(data)
}
