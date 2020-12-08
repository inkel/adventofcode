(ns adventofcode-2020.day4)

(require '[clojure.string :as s]
         '[clojure.java.io :as io])

(defn read-input [input]
  (loop [acc []
         input input]
    (if (empty? input)
      acc
      (let [lines (take-while not-empty input)]
        (recur (conj acc (s/join " " lines))
               (rest (drop-while not-empty input)))))))

(defn parse-line [line]
  (let [parts (s/split line #" ")]
    (loop [acc {}
           parts parts]
      (if (empty? parts)
        acc
        (let [kv (s/split (first parts) #":")
              k (keyword (first kv))
              v (second kv)]
          (recur (assoc acc k (if (or (= :eyr k) (= :iyr k) (= :byr k))
                                (Integer/parseInt v)
                                v))
                 (rest parts)))))))

(def required-fields [:byr :iyr :eyr :hgt :hcl :ecl :pid])

(defn valid? [passport]
  (every? #(contains? passport %) required-fields))

(defn count-valid-passports [input]
  (->> input
       read-input
       (map parse-line)
       (filter valid?)
       count))

(defn- input []
  (->> "day4.txt"
       io/resource
       slurp
       s/split-lines))

(defn part1 [] (count-valid-passports (input)))

(defn valid-byr? [passport] (<= 1920 (:byr passport) 2002))
(defn valid-iyr? [passport] (<= 2010 (:iyr passport) 2020))
(defn valid-eyr? [passport] (<= 2020 (:eyr passport) 2030))

(defn valid-height? [height]
  (let [[m h u] (re-matches #"^(\d+)(cm|in)$" height)]
    (cond
      (nil? m) false
      (= "cm" u) (<= 150 (Integer/parseInt h) 193)
      (= "in" u) (<= 59 (Integer/parseInt h) 76)
      :else false)))

(defn valid-hair-color? [color]
  (re-matches #"^#[a-f0-9]{6}$" color))

(defn valid-eye-color? [color]
  (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} color))

(defn valid-passport-id? [pid]
  (re-matches #"^[0-9]{9}$" pid))

(defn extra-valid? [passport]
  (and (valid? passport)
       (valid-byr? passport)
       (valid-iyr? passport)
       (valid-eyr? passport)
       (valid-height? (:hgt passport))
       (valid-hair-color? (:hcl passport))
       (valid-eye-color? (:ecl passport))
       (valid-passport-id? (:pid passport))))

(defn count-extra-valid-passports [input]
  (->> input
       read-input
       (map parse-line)
       (filter extra-valid?)
       count))

(defn part2 [] (count-extra-valid-passports (input)))
