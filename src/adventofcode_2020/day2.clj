(ns adventofcode-2020.day2
  (:gen-class))

(require '[clojure.string :as str]
         '[clojure.java.io :as io])

(defn parse-min-max [s]
  (let [parts (str/split s #"-")]
    (list (Integer/parseInt (first parts))
          (Integer/parseInt (second parts)))))

(defn parse-report [s]
  (let [[min-max char password] (str/split s #"\s")]
    (vector (parse-min-max min-max)
            (first char)
            password)))

(defn valid? [s]
  (let [[[min max] char password] (parse-report s)
        c (count (filter #(= char %) password))]
    (and (>= c min) (<= c max))))

(defn count-valid-passwords [input]
  (reduce (fn [acc p] (if (valid? p) (inc acc) acc)) 0 input))

(defn- input []
  (->> "day2.txt"
       io/resource
       slurp
       str/split-lines))

(defn part1 []
  (count-valid-passwords (input)))

;; Part 2
(defn xor [x y]
  (or
   (and x (not y))
   (and (not x) y)))

(defn valid2? [s]
  (let [[[min max] char password] (parse-report s)
        a (get password (dec min))
        b (get password (dec max))]
    (xor (= char a) (= char b))))

(defn part2 []
  (count (filter valid2? (input))))
