(ns adventofcode-2020.day3-test
  (:require  [clojure.test :refer :all]
             [adventofcode-2020.day3 :refer :all]))

(def input ["..##......."
            "#...#...#.."
            ".#....#..#."
            "..#.#...#.#"
            ".#...##..#."
            "..#.##....."
            ".#.#.#....#"
            ".#........#"
            "#.##...#..."
            "#...##....#"
            ".#..#...#.#"])

(deftest test-next-pos
  (is (= '(4 2) (next-pos 1 1 10 10)))
  (is (= '(1 2) (next-pos 1 1 3 3)))
  (is (nil? (next-pos 4 2 10 2))))

(deftest test-count-trees
  (is (= 7 (count-trees input)))
  (is (= 2 (count-trees input 1 1)))
  (is (= 7 (count-trees input 3 1)))
  (is (= 3 (count-trees input 5 1)))
  (is (= 4 (count-trees input 7 1)))
  (is (= 2 (count-trees input 1 2))))

(deftest test-count-trees-by-slopes
  (is (= [2 7 3 4 2] (count-trees-by-slopes input slopes))))
