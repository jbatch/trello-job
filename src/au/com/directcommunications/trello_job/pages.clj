(ns au.com.directcommunications.trello-job.pages
  (:require [net.cgrand.enlive-html :as html]))

(html/defsnippet job-entry "templates/job_entry.html" [:div#job-entry]
  []
  identity)
