package com.tbp.graph.service;


import com.tbp.graph.model.Graph;
import com.tbp.graph.model.Vertex;
import org.apache.commons.lang3.StringUtils;
import org.gephi.graph.api.*;
import org.gephi.project.api.ProjectController;
import org.gephi.statistics.plugin.*;
import org.openide.util.Lookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GephiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GephiService.class);

    public void executeAlgorithm(Graph graphApp) {
        if(graphApp != null && graphApp.getEdgeMap() != null && graphApp.getNodeMap() != null
                && !graphApp.getNodeMap().isEmpty() && !graphApp.getEdgeMap().isEmpty()) {
            // needs to create the gephi workspace ...
            ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
            pc.newProject();
            pc.getCurrentWorkspace();

            GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

            DirectedGraph directedGraph = graphModel.getDirectedGraph();
            for(Vertex n : graphApp.getNodeMap().values()) {
                Node node = graphModel.factory().newNode(n.getId().toString());
                directedGraph.addNode(node);
            }

            for(com.tbp.graph.model.Edge e : graphApp.getEdgeMap().values()) {
                Node source = graphModel.getGraph().getNode(e.getSource().getId().toString());
                Node dest = graphModel.getGraph().getNode(e.getDest().getId().toString());
                Edge e1 = graphModel.factory().newEdge(source, dest, e.getWeight(), true);
                directedGraph.addEdge(e1);
            }

            LOGGER.debug("Calc. graph distance");
            //GraphDistance
            GraphDistance distance = new GraphDistance();
            distance.setDirected(true);
            distance.execute(graphModel);

            graphApp.setAvgDist(distance.getPathLength());
            graphApp.setDiameter(distance.getDiameter());
            graphApp.setRadius(distance.getRadius());

            LOGGER.debug("Calc. page rank");
            // page rank
            PageRank pageRankAlg = new PageRank();
            pageRankAlg.setDirected(true);
            pageRankAlg.execute(graphModel);

            LOGGER.debug("Calc. degree distribution");
            // degree distribution
            Degree degreeCalc = new Degree();
            degreeCalc.execute(graphModel);

            graphApp.setAvgDegree(degreeCalc.getAverageDegree());

            LOGGER.debug("Calc. eigenvector");
            // eigenvector
            EigenvectorCentrality eigenvectorCentrality = new EigenvectorCentrality();
            eigenvectorCentrality.execute(graphModel);

            LOGGER.debug("Calc. modularity");
            // modularity
            Modularity modularityClass = new Modularity();
            modularityClass.setResolution(1d);
            modularityClass.setRandom(true);
            modularityClass.setUseWeight(true);
            modularityClass.execute(graphModel);

            String report = modularityClass.getReport();
            graphApp.setNumberOfCommunity(Integer.parseInt(StringUtils.substringBetween(report, "Number of Communities: ", "<br /><br />")));
            graphApp.setModularityWithResolution(Double.parseDouble(StringUtils.substringBetween(report, "Modularity with resolution: ", "<br>").replace(",", ".")));
            graphApp.setModularity(Double.parseDouble(StringUtils.substringBetween(report, "Modularity: ", "<br>").replace(",", ".")));

            LOGGER.debug("Calc. clustering coefficient");
            // clustering
            ClusteringCoefficient coefficient = new ClusteringCoefficient();
            coefficient.setDirected(true);
            coefficient.execute(graphModel);
            graphApp.setAvgClusteringCoef(coefficient.getAverageClusteringCoefficient());

            LOGGER.debug("Calc. graph density");
            // graph density
            GraphDensity density = new GraphDensity();
            density.setDirected(true);
            density.execute(graphModel);
            graphApp.setDensity(density.getDensity());

            LOGGER.debug("Calc. connected components");
            // components
            ConnectedComponents connectedComponents = new ConnectedComponents();
            connectedComponents.setDirected(true);
            connectedComponents.execute(graphModel);

            report = connectedComponents.getReport();
            graphApp.setWeaklyComponentCount(Integer.parseInt(StringUtils.substringBetween(report, "Number of Weakly Connected Components: ", "<br>")));
            graphApp.setStronglyComponentCount(Integer.parseInt(StringUtils.substringBetween(report, "Number of Strongly Connected Components: ", "<br>")));

            Column columnBetweenness = graphModel.getNodeTable().getColumn(GraphDistance.BETWEENNESS);
            Column columnCloseness = graphModel.getNodeTable().getColumn(GraphDistance.CLOSENESS);
            Column columnEccentricity = graphModel.getNodeTable().getColumn(GraphDistance.ECCENTRICITY);
            Column columnHarmonicCloseness = graphModel.getNodeTable().getColumn(GraphDistance.HARMONIC_CLOSENESS);
            Column columnPagerank = graphModel.getNodeTable().getColumn(PageRank.PAGERANK);
            Column columnIndegree = graphModel.getNodeTable().getColumn(Degree.INDEGREE);
            Column columnOutdegree = graphModel.getNodeTable().getColumn(Degree.OUTDEGREE);
            Column columnDegree = graphModel.getNodeTable().getColumn(Degree.DEGREE);
            Column columnEigenvector = graphModel.getNodeTable().getColumn(EigenvectorCentrality.EIGENVECTOR);
            Column columnModularity = graphModel.getNodeTable().getColumn(Modularity.MODULARITY_CLASS);
            Column columnClusteringCoefficient = graphModel.getNodeTable().getColumn(ClusteringCoefficient.CLUSTERING_COEFF);
            Column columnWeaklyConnected = graphModel.getNodeTable().getColumn(ConnectedComponents.WEAKLY);
            Column columnStronglyConnected = graphModel.getNodeTable().getColumn(ConnectedComponents.STRONG);

            for(Node n: graphModel.getGraph().getNodes()) {
                Double betweenness = (Double) n.getAttribute(columnBetweenness);
                Double closeness = (Double) n.getAttribute(columnCloseness);
                Double eccentricity = (Double) n.getAttribute(columnEccentricity);
                Double harmonicCloseness = (Double) n.getAttribute(columnHarmonicCloseness);
                Double pageRank = (Double) n.getAttribute(columnPagerank);
                Integer indegree = (Integer) n.getAttribute(columnIndegree);
                Integer outdegree = (Integer) n.getAttribute(columnOutdegree);
                Integer degree = (Integer) n.getAttribute(columnDegree);
                Double eigenvector = (Double) n.getAttribute(columnEigenvector);
                Integer modularity = (Integer) n.getAttribute(columnModularity);
                Double clusteringCoefficient = (Double) n.getAttribute(columnClusteringCoefficient);
                Integer stronglyComp = (Integer) n.getAttribute(columnStronglyConnected);
                Integer weaklyComp = (Integer) n.getAttribute(columnWeaklyConnected) ;

                Vertex vertex = graphApp.getNodeMap().get(Long.parseLong(n.getId().toString()));
                vertex.setBetweenness(betweenness);
                vertex.setCloseness(closeness);
                vertex.setEccentricity(eccentricity);
                vertex.setHarmonicCloseness(harmonicCloseness);
                vertex.setPageRank(pageRank);
                vertex.setIndegree(indegree);
                vertex.setOutdegree(outdegree);
                vertex.setDegree(degree);
                vertex.setEigenvector(eigenvector);
                vertex.setModularity(modularity);
                vertex.setClusteringCoefficient(clusteringCoefficient);
                vertex.setStronglyComponent(stronglyComp);
                vertex.setWeaklyComponent(weaklyComp);
            }

            LOGGER.debug("Finishing graph analysis");
        }
    }


}
