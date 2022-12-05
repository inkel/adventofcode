package main

import (
	"fmt"
	"strconv"
)

func day4_eval(s string) (bool, bool) {
	a, b, c, d := day4_parse_pairs(s)
	return (c >= a && d <= b) || (a >= c && b <= d), b >= c && a <= d
}

func split(s string, d rune) (string, string) {
	var i int
	for j, r := range s {
		if r == d {
			i = j
			break
		}
	}
	return s[:i], s[i+1:]
}

func day4_parse_pairs(s string) (int, int, int, int) {
	s1, s2 := split(s, ',')
	a, b := day4_parse_pair(s1)
	c, d := day4_parse_pair(s2)
	return a, b, c, d
}

func day4_parse_pair(s string) (int, int) {
	s1, s2 := split(s, '-')
	a, err := strconv.Atoi(s1)
	if err != nil {
		panic(fmt.Sprintf("parsing %s: %v", s1, err))
	}
	b, err := strconv.Atoi(s2)
	if err != nil {
		panic(fmt.Sprintf("parsing %s: %v", s2, err))
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
