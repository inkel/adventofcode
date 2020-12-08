(ns adventofcode-2020.day4-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [adventofcode-2020.day4 :refer :all]))

(def input (s/split-lines "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in"))

(def lines ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
            "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"
            "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"])

(deftest test-read-input
  (is (= lines (read-input input))))

(deftest test-parse-line
  (is (= {:ecl "gry"
          :pid "860033327"
          :eyr "2020"
          :hcl "#fffffd"
          :byr "1937"
          :iyr "2017"
          :cid "147"
          :hgt "183cm"}
         (parse-line (get lines 0)))))

(deftest test-valid?
  (is (valid? (parse-line (get lines 0))))
  (is (valid? (parse-line (get lines 2))))
  (is (not (valid? (parse-line (get lines 1)))))
  (is (not (valid? (parse-line (get lines 3))))))

(deftest test-count-valid-passports
  (is (= 2 (count-valid-passports input))))
