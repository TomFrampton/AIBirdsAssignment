package u1171639.java.view.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import org.springframework.stereotype.Component;

import u1171639.java.agent.Agent;

@Component
public class PerformanceAnalysisViewController extends ViewController {
	
	private List<Agent> agents;
	
	@FXML private LineChart<Number, Number> agentPerformanceLineChart;
	
	@Override
	public String getViewName() {
		return "performance-analysis.fxml";
	}

	@Override
	public void initialise() {
		// TODO Auto-generated method stub
		
	}
	
	public void runPerformanceAnalysis(List<Agent> agents) {
		this.agents = agents;
		
		for(int i = 0; i < agents.size(); ++i) {
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			this.agentPerformanceLineChart.getData().add(series);
		}
		
		//for(;;) {
			for(Agent agent : this.agents) {
				agent.runOneLevel();
				updateChart();
			}
		//}
	}
	
	private void updateChart() {
		
		for(int i = 0; i < this.agents.size(); ++i) {
			int x = this.agents.get(i).levelsCompleted() - 1;
			double y = this.agents.get(i).averageShotsPerLevel();
			
			this.agentPerformanceLineChart.getData().get(0).getData().add(new XYChart.Data<Number, Number>(x,y));
		}
	}

}
