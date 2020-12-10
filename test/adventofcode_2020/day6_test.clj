(ns adventofcode-2020.day6-test
  (:require [adventofcode-2020.day6 :refer :all]
            [clojure.test :refer :all]))

(def ^:private input "abc

a
b
c

ab
ac

a
a
a
a

b")

(deftest test-prepare-input
  (is (= ["abc" "abc" "abac" "aaaa" "b"]
         (prepare-input input))))

(deftest test-count-group-answers
  (is (= 3 (count-group-answers "abc")))
  (is (= 3 (count-group-answers "abac")))
  (is (= 1 (count-group-answers "aaaa")))
  (is (= 1 (count-group-answers "b"))))

(deftest test-sum-groups-answers
  (is (= 11 (sum-groups-answers (prepare-input input)))))
