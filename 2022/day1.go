package main

import (
	"sort"
)

func atoi(s string) int {
	var n int
	for _, c := range s {
		n = n*10 + int(c-'0')
	}
	return n
}

func day1(in []string) (int, int) {
	var cals []int = make([]int, 0, 1000)
	var cur int

	for _, l := range in {
		if l == "" {
			cals = append(cals, cur)
			cur = 0
		}

		cur += atoi(l)
	}

	sort.Ints(cals)
	l := len(cals)

	return cals[l-1], cals[l-1] + cals[l-2] + cals[l-3]
}
