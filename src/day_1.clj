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
  (reduce + (map mass-to-fuel input)))

;; part 2
(defn calculate-fuels-fuel [fuel]
  (reduce + (take-while pos? (iterate mass-to-fuel fuel))))

(defn part-2 []
  (reduce + (map calculate-fuels-fuel (map mass-to-fuel input))))
