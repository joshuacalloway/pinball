(ns pinball.game
    (:use seesaw.core seesaw.border)
    (:import (java.awt Color))
    )

(require ['pinball.ball :as 'ball])
(require ['pinball.position :as 'position])

(defn game-gui []
  (invoke-later
  (-> (frame :title "Pinball Demo"
             :content (vertical-panel
                       :background Color/GRAY
                       :border (line-border :color Color/BLACK :thickness 5)
                       :items [(label :border (line-border)
                                      :text "this label"
                                      :id
                                      :link
                                      :cursor
                                      :hand)
                       (button :id :button :text "This")
                       ])
             :on-close :exit)
    pack!
    show!))
  )


      
(defn run-game [] (game-gui))
