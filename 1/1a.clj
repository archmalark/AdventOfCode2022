(require '[clojure.string :as str])

(defn parseElement
  [element]
  (if (clojure.string/blank? element) 0 (read-string element)) 
  
  )

(defn calculateAcc [acc add]
  (if (= add 0)
    0
    (+ acc add))
  )

(defn solve
  [input highest acc]
  (if (empty? input)
    highest
    (solve (rest input) (max highest acc) (calculateAcc acc (first input))))
   )



;; Test input
(solve 
 (map parseElement  (str/split (slurp "/home/arkmal/Kod/aoc2022/1/1a-example.input") #"\n"))
 0 
 0)

;; Real input
(solve
 (map parseElement  (str/split (slurp "/home/arkmal/Kod/aoc2022/1/1a.input") #"\n"))
 0
 0)
