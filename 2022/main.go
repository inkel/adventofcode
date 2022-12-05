package main

import (
	"bufio"
	"fmt"
	"os"
)

var solutions = map[string]dayFunc{
	"1": day1,
	"2": day2,
	"3": day3,
	"4": day4,
}

type dayFunc func([]string) (int, int)

func main() {
	d := os.Args[1]

	in, err := read(d)
	if err != nil {
		fmt.Fprintln(os.Stderr, err)
		os.Exit(1)
	}

	fn := solutions[d]

	fmt.Print(d, "=>")
	fmt.Println(fn(in))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func read(d string) ([]string, error) {
	fp, err := os.Open(fmt.Sprintf("day%02s.txt", d))
	if err != nil {
		return nil, err
	}
	defer fp.Close()

	s := bufio.NewScanner(fp)
	var in []string
	for s.Scan() {
		in = append(in, s.Text())
	}

	return in, s.Err()
}
