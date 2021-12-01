(ns adventofcode-2020.day2-test
  (:require [clojure.test :refer :all]
            [adventofcode-2020.day2 :refer :all]))

(def input ["1-3 a: abcde"
            "1-3 b: cdefg"
            "2-9 c: ccccccccc"])

(deftest test-parse-report
  (is (= ['(1 3) \a "abcde"] (parse-report "1-3 a: abcde")))
  (is (= ['(1 3) \b "cdefg"] (parse-report "1-3 b: cdefg")))
  (is (= ['(2 9) \c "ccccccccc"] (parse-report "2-9 c: ccccccccc"))))

(deftest test-parse-min-max
  (is (= '(1 3) (parse-min-max "1-3")))
  (is (= '(2 9) (parse-min-max "2-9"))))

(deftest test-valid?
  (is (valid? "1-3 a: abcde"))
  (is (not (valid? "1-3 b: cdefg")))
  (is (valid? "2-9 c: ccccccccc")))

(deftest test-count-valid-passwords
  (is (= 2 (count-valid-passwords input))))

(deftest test-xor
  (is (true? (xor true false)))
  (is (true? (xor false true)))
  (is (false? (xor false false)))
  (is (false? (xor true true))))

(deftest test-valid2?
  (is (valid2? "1-3 a: abcde"))
  (is (not (valid2? "1-3 b: cdefg")))
  (is (not (valid2? "2-9 c: ccccccccc"))))
