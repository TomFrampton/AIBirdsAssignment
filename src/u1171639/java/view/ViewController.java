package u1171639.java.view;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import u1171639.java.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;

public abstract class ViewController {
	// The view that this controller manages
	private Parent view;
	
	// View Manager - used for interacting with other views and moving between views
	@Autowired private ViewManager viewManager;
	
	// Application Controller
	@Autowired private Controller controller;
	
	public ViewController() {
		
	}
	
	public Parent getView() {
		return this.view;
	}
	
	public void setView(Parent view) {
		this.view = view;
	}
	
	public void showView() {
		if(this.view == null) {
			this.loadFxmlView(this.getViewName());
		}
		
		this.viewManager.showScreen(this);
	}
	
	public abstract String getViewName();
	
	protected void loadFxmlView(String fxmlResource) {
		FXMLLoader fxmlLoader = new FXMLLoader(ViewController.class.getClass().getResource("/u1171639/fxml/" + fxmlResource));
		
		try {
			fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
				
                @Override
                public Object call(Class<?> aClass) {
                    return ViewController.this;
                }
            });
			
			this.setView((Parent) fxmlLoader.load());
		} catch(IOException e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}
}
