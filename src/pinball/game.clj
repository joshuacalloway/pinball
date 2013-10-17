(ns pinball.game
    (:use seesaw.core seesaw.border)
    (:import (java.awt Color))
    )

(require ['pinball.slot :as 'slot])

(def game-canvas
     (vertical-panel
      :background Color/GRAY
      :border (line-border :color Color/BLACK :thickness 5)
      :items [(label :border (line-border)
                     :text "this label"
                     :id
                     :link
                     :cursor
                     :hand)
      (button :id :button :text "This")]
      ))

(def game-controls
     (horizontal-panel
      :items [
      (button :id :button :text "One Ball")
      (button :id :button :text "100 Balls")

      ]
      ))
      
(defn game-gui []
  (invoke-later
   (-> (frame :title "Pinball Demo"
              :width 400 :height 600
              :content (border-panel
                        :center game-canvas
                        :south game-controls
                        )
              :on-close :exit)
    pack!
    show!))
  )


      
(defn run-game [] (game-gui))
