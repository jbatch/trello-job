(ns au.com.directcommunications.trello-job.routes
  (:require (compojure [core :refer [defroutes GET POST]]
                       [route :as route])
            (au.com.directcommunications.trello-job [middleware :as middleware]
                                                    [pages :as pages]
                                                    [trello :as trello])
            [ring.middleware.params :refer [wrap-params]]))

(defroutes public-routes
  (GET "/" []
       (pages/job-entry))
  (POST "/job" {params :params}
        (trello/handle-post-card params))
  (GET "/job" {params :params}
       "Hello Wolrd!!"))

(defroutes static-routes
  (route/resources "/")
  (route/not-found "<h1>Page not found.</h1>"))

(defroutes app
  (-> public-routes
      (middleware/wrap-layout)
      (wrap-params))
  static-routes)
