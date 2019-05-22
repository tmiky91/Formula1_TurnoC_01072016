package it.polito.tdp.formulaone.model;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private SimpleWeightedGraph<Driver, DefaultWeightedEdge> grafo;

	public static List<Constructor> getAllConstructor() {
		FormulaOneDAO dao = new FormulaOneDAO();
		return dao.getAllConstructors();
	}

	public String getMigliorPilota(Constructor c) {
		String risultato="";
		double max=0;
		Driver migliore=null;
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		FormulaOneDAO dao = new FormulaOneDAO();
		
		List<SquadraPiloti> squadra = dao.getCoppiePiloti(grafo, c);
		for(SquadraPiloti sp: squadra) {
			Graphs.addEdgeWithVertices(grafo, sp.getP1(), sp.getP2());
			DefaultWeightedEdge edge = grafo.getEdge(sp.getP1(), sp.getP2());
			
			grafo.setEdgeWeight(edge, sp.getPeso());
		}
		System.out.println("Vertici: "+grafo.vertexSet().size()+" Archi: "+grafo.edgeSet().size());
		for(Driver d: grafo.vertexSet()) {
			List<Driver> vicini = Graphs.neighborListOf(grafo, d);
			double somma=0;
			for(Driver d1: vicini) {
				DefaultWeightedEdge edge = grafo.getEdge(d, d1);
				somma+=grafo.getEdgeWeight(edge);
			}
			if(somma>max) {
				max=somma;
				migliore=d;
				risultato="L'ID del miglior pilota è: "+migliore.getDriverId()+" con un numero di gare disputate pari a: "+max;
			}
		}
		return risultato;
	}
	
	
	

}
