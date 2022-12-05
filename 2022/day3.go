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

func day3_has(s string, r rune) bool {
	for _, rr := range s {
		if r == rr {
			return true
		}
	}
	return false
}

func day3_repeated(s string) (rune, int) {
	m := len(s) / 2
	var rr rune
	for _, r := range s[:m] {
		if day3_has(s[m:], r) {
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

	for _, r := range in[0] {
		if day3_has(in[1], r) && day3_has(in[2], r) {
			return r
		}
	}

	return 0
}
