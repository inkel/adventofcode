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
        (let [[k v] (s/split (first parts) #":")]
          (recur (assoc acc (keyword k) v)
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
