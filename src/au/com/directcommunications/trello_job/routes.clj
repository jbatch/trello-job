(ns au.com.directcommunications.trello-job.routes
  (:require (compojure [core :refer [defroutes GET POST]]
                       [route :as route])
            (au.com.directcommunications.trello-job [middleware :as middleware]
                                                    [pages :as pages])))

(defroutes public-routes
  (GET "/" []
       (pages/job-entry)))

(defroutes static-routes
  (route/resources "/")
  (route/not-found "<h1>Page not found.</h1>"))

(defroutes app
  (middleware/wrap-layout public-routes)
  static-routes)
