(ns day-1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))


(def input (->> (slurp (io/resource "day_1_input.txt")) ; Read in the input file
                (str/split-lines)                       ; Split it per line
                (map #(Integer/parseInt %))))           ; Convert to int

;; floor(mass/3) - 2
(defn mass-to-fuel [mass]
  (- (quot mass 3) 2))

(defn part-1 []
  (reduce #(+ %1  (mass-to-fuel %2)) 0 input))

;; part 2
(defn calculate-fuels-fuel [fuel]
  (loop [current fuel
         sum 0]
    (if (> current 0)
      (recur (mass-to-fuel current) (+ sum current))
      sum)))

(defn part-2 []
  (reduce #(+ %1 (calculate-fuels-fuel (mass-to-fuel %2))) 0 input))
