(ns pinball.core-test
  (:require [clojure.test :refer :all]
            [pinball.core :refer :all]))

(require ['pinball.game :as 'game])
(require ['pinball.slot :as 'slot])

(deftest validgame-is-valid
  (testing "A valid game should be valid")
  (is (= true (game/game-valid? (game/default-game [10 50 40]))))
  )

(deftest invalidgame-is-valid
  (testing "An invalid game should be invalid")
  (is (= false (game/game-valid? (game/default-game [15 50 40])))
      ))

(deftest add-ball-works
  (testing "Adding a ball to slot increments balls by 1")
  (let [aslot (slot/default-make 30)]
    (is (= (+ 1 (:numballs aslot))
           (:numballs (slot/add-ball aslot))
        ))))

(deftest simple-play-ball-works
  (testing "Simple play ball should increments # of balls in all slots by 1 ")
  (let [g (game/default-game [0 70 15 15])
        g++ (game/play-ball g)
        totalballs? #(reduce + (map (fn[x] (:numballs x)) (:slots %)))
        ]
    (is (= (+ 1 (totalballs? g)) (totalballs? (game/play-ball g))))))

