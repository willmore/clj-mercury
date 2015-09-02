(defproject mercury "0.0.1-SNAPSHOT"
  :description "Message queue producer/consumer"
  :url "https://github.com/willmore/clj-mercury"
  :min-lein-version "2.0.0"
  :dependencies [[com.novemberain/langohr "3.3.0"]]
  :test-paths ["test"]
  :plugins [[lein-midje "3.1.3"]]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]}})