(ns pinball.gamegui
    (:use seesaw.core seesaw.border)
    (:import (java.awt Color))
    )

(require ['pinball.slot :as 'slot])
(require ['pinball.game :as 'game])

(defn slotgui [s]
  (label :text (str (:name s) " has #" (:numballs s) " balls")))

(defn game-canvas [g]
  (vertical-panel
   :background Color/GRAY
   :border (line-border :color Color/BLACK :thickness 5)
   :items [(vec (map #(slotgui %) (:slots g)))]))


(def game-controls
  (horizontal-panel
   :items [
           (button :id :button :text "One Ball")
           (button :id :button :text "100 Balls")
           (button :id :button :text "1,000 Balls")
           (button :id :button :text "10,000 Balls")
           ]))

(defn game-gui [g]
  (invoke-later
   (-> (frame :title "Pinball Demo"
              :width 400 :height 600
              :content (border-panel
                        :center (game-canvas g)
                        :south game-controls
                        )
              :on-close :exit)
       pack!
       show!))
  )
      
(defn run-game [] (game-gui (game/default-game [31 19 50])))
