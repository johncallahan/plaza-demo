(ns plaza-demo.webapp)

(use 'plaza.rdf.core)
(use 'plaza.rdf.implementations.jena)
(init-jena-framework)                
(use 'plaza.rdf.vocabularies.foaf)
(use 'plaza.rdf.schemas)     
(make-rdfs-schema foaf:Person
                       :name            {:uri foaf:name           :range :string}       
                       :surname         {:uri foaf:surname        :range :string})      
(use 'plaza.rdf.core)
(use 'plaza.rdf.implementations.jena)
(init-jena-framework)                
(use 'plaza.rdf.vocabularies.foaf)
(use 'plaza.rdf.schemas)     
(def ComputationCelebrity-schema
     (make-rdfs-schema foaf:Person                                                
                       :name            {:uri foaf:name           :range :string} 
                       :surname         {:uri foaf:surname        :range :string}
		       :nick		{:nick foaf:nick 	  :range :string}
                       :birthday	{:uri foaf:birthday 	  :range :date}
                       :interest	{:uri foaf:topic_interest :range :string}
                       :wikipedia_entry	{:uri foaf:holdsAccount   :range rdfs:Resource}))
(use 'plaza.rest.core)
(tbox-register-schema :celebrity ComputationCelebrity-schema)
(tbox-register-schema :foaf-agent foaf:Agent-schema)
(use 'plaza.triple-spaces.core)
(def-ts :celebrities (make-basic-triple-space))
(out (ts :celebrities) (model-to-triples (document-to-model (java.io.FileInputStream. "test.xml") :xml)))
(use 'compojure.core)
(use 'compojure.response)
(use 'ring.adapter.jetty)
(use 'clojure.contrib.logging)    
(use '[compojure.route :as route])
(defroutes example
  (GET "/" [] "<h1>Testing plaza...</h1>")
  (spawn-rest-resource! :celebrity "/Celebrity/:id" :celebrities
        :filters {:post-build-graph-query
                [(fn [request environment]
                     (doseq [t (:query environment)] (log :error (str "q: " t))) environment)]})
  (spawn-rest-resource! :celebrity "/TestResourceOne/:id" :celebrities
                        :id-property-uri foaf:name)
  (spawn-rest-collection-resource! :celebrity "/TestResourceOne" :celebrities
                                   :id-property-uri foaf:name)
  (spawn-rest-resource! :celebrity "/TestResourceTwo/:name" :celebrities
                        :id-property-alias :name)
  (spawn-rest-collection-resource! :celebrity "/TestResourceTwo" :celebrities
                                   :id-property-alias :name)
  (spawn-rest-collection-resource! :celebrity "/Celebrity" :celebrities)
  (route/not-found "Page not found"))

(run-jetty (var example) {:port 8081})
