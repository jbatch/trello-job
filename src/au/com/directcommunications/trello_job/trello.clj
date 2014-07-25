(ns au.com.directcommunications.trello-job.trello
  (:require [ring.util.response :as resp]
            [trellolib.core :as trello]))

(defn handle-post-card
  "This function is called when the user makes a POST request on
  /job"
  [params]
  (let [card {:name (str (params "customer-po")
                         "-"
                         (params "customer-name"))
              :desc (params "to-do")
              :idList "53c8c8207dfb7da6d039cb95"}
        cred (trello/get-credentials hard-client :POST (trello/get-url "cards"))]
    (trello/post-card card cred))
  (resp/redirect "/job"))
