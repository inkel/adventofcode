package main

import (
	"strconv"
	"strings"
)

func day1_1(input string) int {
	i := 0
	vs := strings.Split(strings.TrimSpace(input), "\n")
	p, _ := strconv.Atoi(vs[0])

	for _, v := range vs[1:] {
		c, _ := strconv.Atoi(v)
		if c > p {
			i++
		}
		p = c

	}

	return i
}
