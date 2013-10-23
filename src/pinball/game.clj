(ns pinball.game)

; Game has a name and slots
(defrecord Game [name slots])

(defn make ([n s] (Game. n s)))

(use '[pinball.slot :exclude [make] :as slot])

(defn default-game [probabilities]
  (pinball.game/make "default game" (map slot/default-make probabilities)))

(defn random-game []
  (let [ b (+ 2 (rand-int 97))
         a (rand-int (- b 1))
         probabilities [a (- b a) (- 100 b)]
       ]
  (pinball.game/make "random game" (map slot/default-make probabilities))))


; A valid game has probabilities that total 100
(defn game-valid? [agame] (= 100 (reduce + (map #(:probability %) (:slots agame)))))

; Weighted Random choice picks a slot from the game given
; probabilities of the slots
(defn weighted-rand-choice [agame]
  (let [w (reductions #(+ % %2) (map #(:probability %) (:slots agame)))
        r (rand-int (last w))]
    (nth (:slots agame) (count (take-while #( <= % r ) w)))))

; Plays n # number of balls on the game.  Returns the new game
(defn play-ball [agame n]
  (let [slotpicked (weighted-rand-choice agame)
        newslot (slot/add-ball slotpicked)
        oneballplayed (make (:name agame) (cons newslot (filter #(not= (:name slotpicked) (:name %)) (:slots agame))))           
        ]
    (if (= n 1)
      oneballplayed
      (recur oneballplayed (dec n)))))
    


