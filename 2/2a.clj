(require '[clojure.string :as str])

 (defn calculateWinPoint [me him]
   (- 6 (* 3 (mod (- him me) 3)))
   )
(defn calculateChoiceScore [choice] 
  (+ (mod (- (int (.charAt  choice 0)) 1) 3) 1)
  )

 
 (defn asciiMod [inputstring]
   (mod (int (.charAt  inputstring 0)) 3)
   )
 
 (defn calculateRoundPoint [elements]
   (+ (calculateWinPoint (asciiMod (last elements)) (asciiMod (first elements))) (calculateChoiceScore (last elements)))
   )

(defn solve
  [input]
  (if (empty? input)
    0
    (+ (calculateRoundPoint (str/split (first input) #" ") ) (solve (rest input)) )
    ))


;; Test input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/2/2a-example.input") #"\n")
)

 ;; Real input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/2/2a.input") #"\n"))



; X 88 Rock 1 
; Y 89 Paper 2
; Z 90 Scissor 0

; A 65 Rock 2
; B 66 Paper 0
; C 67 Scissor 1

; X A f(1, 2) = 3  
; X B f(1, 0)= 0
; X C f(1, 1) = 6

; Y A f(2, 2) = 6
; Y B f(2, 0)= 3
; Y C f(2, 1) = 0

; Z A f(0, 2) = 0
; Z B f(0, 0) = 6
; Z A f(0, 1) = 3

;;  (defn f [x y]
;;    (- 6 (* 3 (mod (- y x) 3))))




; 0 0 = 3
; 0 1 = 0
; 0 2 = 6

; 1 0 = 6
; 1 1 = 3
; 1 2 = 0

 ; 1
 ; 2
 ; 3
 ; 1 1 = 3
 ; 1 2 = 0
 ; 1 3 = 6
 
