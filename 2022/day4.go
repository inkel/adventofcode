package main

import (
	"fmt"
	"strconv"
	"strings"
)

func day4_eval(s string) (bool, bool) {
	a, b, c, d := day4_parse_pairs(s)
	return (c >= a && d <= b) || (a >= c && b <= d), b >= c && a <= d
}

func day4_parse_pairs(s string) (int, int, int, int) {
	ps := strings.Split(s, ",")
	a, b := day4_parse_pair(ps[0])
	c, d := day4_parse_pair(ps[1])
	return a, b, c, d
}

func day4_parse_pair(s string) (int, int) {
	ps := strings.Split(s, "-")
	a, err := strconv.Atoi(ps[0])
	if err != nil {
		panic(fmt.Sprintf("parsing %s: %v", ps[0], err))
	}
	b, err := strconv.Atoi(ps[1])
	if err != nil {
		panic(fmt.Sprintf("parsing %s: %v", ps[0], err))
	}
	return a, b
}

func day4(in []string) (int, int) {
	var p1, p2 int
	for _, s := range in {
		c, o := day4_eval(s)
		if c {
			p1++
		}
		if o {
			p2++
		}
	}
	return p1, p2
}
