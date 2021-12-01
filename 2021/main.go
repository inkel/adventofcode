package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	fmt.Printf("Day 1.1: %d\n", day1_1(readints("testdata/day1_1.txt")))
	fmt.Printf("Day 1.2: %d\n", day1_2(readints("testdata/day1_1.txt")))
}

func readfile(n string) string {
	data, err := os.ReadFile(n)
	if err != nil {
		panic(err)
	}
	return string(data)
}

func readints(n string) []int {
	f, err := os.Open(n)
	if err != nil {
		panic(err)
	}
	defer f.Close()
	s := bufio.NewScanner(f)
	vs := make([]int, 0)
	for s.Scan() {
		v, _ := strconv.Atoi(s.Text())
		vs = append(vs, v)
	}
	if s.Err() != nil {
		panic(s.Err())
	}
	return vs
}
