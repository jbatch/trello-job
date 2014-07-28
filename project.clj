(defproject trello-job "0.1.0-SNAPSHOT"
  :description "Simple job entry into Trello."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [enlive "1.1.5"]
                 [environ "0.5.0"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-devel "1.3.0"]
                 [ring/ring-jetty-adapter "1.3.0"]
                 [trellolib "0.2.1-SNAPSHOT"]]
  :plugins [[lein-environ "0.5.0"]
            [lein-ring "0.8.11"]]
  :ring {:handler au.com.directcommunications.trello-job.routes/app}
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[midje "1.6.3"]
                                  [org.clojure/tools.namespace "0.2.5"]]}
             :production {:env {:production true}}
             :uberjar {:aot :all}}
  :main au.com.directcommunications.trello-job.system)
