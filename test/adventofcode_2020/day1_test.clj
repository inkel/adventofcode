(ns adventofcode-2020.day1-test
  (:require [clojure.test :refer :all]
            [adventofcode-2020.day1 :refer :all]))

(def input '(1721
             979
             366
             299
             675
             1456))

(deftest test-sum-2020?
  (is (sum-2020? 2019 1))
  (is (not (sum-2020? 2019 2)))
  (is (sum-2020? 979 366 675))
  (is (not (sum-2020? 1 2 3))))

;; Part 1
(deftest test-find-pairs-sum-2020
  (let [pair (find-pairs-sum-2020 (shuffle input))]
    (is (or (= '(299 1721) pair)
            (= '(1721 299) pair)))))
