package main

func day1_1(input []int) int {
	i := 0
	p := input[0]
	for _, v := range input[1:] {
		if v > p {
			i++
		}
		p = v
	}

	return i
}

func day1_2_windows(input []int) []int {
	w := make([]int, 0)
	for i := 0; i < len(input)-2; i++ {
		w = append(w, input[i]+input[i+1]+input[i+2])
	}
	return w
}

func day1_2(input []int) int {
	return day1_1(day1_2_windows(input))
}
