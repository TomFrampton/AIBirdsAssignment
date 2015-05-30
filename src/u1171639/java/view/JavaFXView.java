package u1171639.java.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFXView extends Application implements View {

	@Autowired private ViewManager viewManager;
	
	private static ViewManager applicationViewManager;
	
	@Override
	public void start() {
		System.out.println("JavaFX View Starting...");
		// We need to store the view manager in a static field. Spring does not allow
		// us to do this directly via dependency injection so we are doing it here
		applicationViewManager = this.viewManager;
		JavaFXView.launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		
		applicationViewManager.initStage(stage, 1200, 600);
		applicationViewManager.agentSelection().showView();
	}

}
