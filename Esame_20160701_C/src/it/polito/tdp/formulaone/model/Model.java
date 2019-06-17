package it.polito.tdp.formulaone.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private Map<Integer, Driver> idMap;
	private SimpleWeightedGraph<Driver, DefaultWeightedEdge> grafo;
	
	public Model() {
		idMap = new HashMap<>();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}

	public List<Constructor> getCostruttori() {
		FormulaOneDAO dao = new FormulaOneDAO();
		return dao.getAllConstructors();
	}

	public String creaGrafo(Constructor c) {
		String risultato="";
		FormulaOneDAO dao = new FormulaOneDAO();
		dao.getAllDrivers(idMap);
		List<GareComuni> gareComuni = dao.getGareComuni(idMap, c);
		for(GareComuni gc: gareComuni) {
			if(!grafo.containsVertex(gc.getD1())) {
				grafo.addVertex(gc.getD1());
			}
			if(!grafo.containsVertex(gc.getD2())) {
				grafo.addVertex(gc.getD2());
			}
			DefaultWeightedEdge edge = grafo.getEdge(gc.getD1(), gc.getD2());
			if(edge==null) {
				Graphs.addEdgeWithVertices(grafo, gc.getD1(), gc.getD2(), gc.getPeso());
			}
		}
		risultato="Grafo Creato! Vertici: "+grafo.vertexSet().size()+" Archi: "+grafo.edgeSet().size()+"\n";
		double max=0;
		Driver best=null;
		for(Driver d: grafo.vertexSet()) {
			List<Driver> vicini = Graphs.neighborListOf(grafo, d);
			double somma=0;
			for(Driver d1: vicini) {
				DefaultWeightedEdge edge = grafo.getEdge(d, d1);
				somma+=grafo.getEdgeWeight(edge);
			}
			if(somma>max) {
				max=somma;
				best=d;
				risultato="Il miglior pilota per il costruttore selezionato è: "+best.getSurname()+"\n"+"Numero gare disputate: "+max;
			}
		}
		return risultato;
	}


}
