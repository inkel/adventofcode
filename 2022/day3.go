package main

import "fmt"

func day3_priority(r rune) int {
	var p int
	if r >= 'a' && r <= 'z' {
		p = int(r) - 96
	} else {
		p = int(r) - 38
	}
	return p
}

func day3_repeated(s string) (rune, int) {
	s1 := make(map[rune]struct{})
	m := len(s) / 2
	var rr rune
	for i, r := range s {
		if i < m {
			s1[r] = struct{}{}
		} else if _, ok := s1[r]; ok {
			rr = r
			break
		}
	}
	return rr, day3_priority(rr)
}

func day3(in []string) (int, int) {
	var p1, p2 int

	for _, s := range in {
		_, p := day3_repeated(s)
		p1 += p
	}

	for i := 0; i < len(in); i += 3 {
		p2 += day3_priority(day3_badge(in[i : i+3]))
	}

	return p1, p2
}

func day3_badge(in []string) rune {
	if len(in) != 3 {
		panic(fmt.Sprintf("invalid length %d: %v", len(in), in))
	}

	is := make(map[rune]int, len(in[0]))
	for _, r := range in[0] {
		is[r] = 1
	}
	for _, r := range in[1] {
		if _, ok := is[r]; ok {
			is[r] = 2
		}
	}
	for _, r := range in[2] {
		if v, ok := is[r]; ok && v == 2 {
			return r
		}
	}

	return 0
}
