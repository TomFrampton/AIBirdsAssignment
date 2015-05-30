package u1171639.java.view.controller;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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

	}
	
	public void runPerformanceAnalysis(final List<Agent> agents) {
		this.agents = agents;
		
		for(int i = 0; i < agents.size(); ++i) {
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			series.setName(agents.get(i).getName());
			this.agentPerformanceLineChart.getData().add(series);
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(;;Thread.sleep(200)) {
						for(Agent agent : agents) {
							agent.runOneLevel();
						}
						updateChart();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void updateChart() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < agents.size(); ++i) {
					int x = agents.get(i).levelsCompleted() - 1;
					double y = agents.get(i).averageShotsPerLevel();
					
					agentPerformanceLineChart.getData().get(i).getData().add(new XYChart.Data<Number, Number>(x,y));
				}
			}
		});
	}

}
