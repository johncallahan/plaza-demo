(defproject plaza_demo "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 [plaza "0.0.5-SNAPSHOT" :exclusions [org.clojure/clojure-contrib org.clojure/clojure]]]
  :repl-retry-limit 1000
  :main plaza_demo.webapp)
