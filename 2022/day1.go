package main

import (
	"sort"
	"strconv"
)

func day1(in []string) (int, int) {
	var cals []int
	var cur int

	for _, l := range in {
		if l == "" {
			cals = append(cals, cur)
			cur = 0
		}

		c, _ := strconv.Atoi(l)
		cur += c
	}

	sort.Ints(cals)
	l := len(cals)

	return cals[l-1], cals[l-1] + cals[l-2] + cals[l-3]
}
