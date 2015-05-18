import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by denny on 5/18/15.
 */
public class ColorSet extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        Text text = new Text("ColorSet Selector");
//        text.setTextOrigin(VPos.TOP);
//        text.setFont(Font.font(null, FontWeight.BOLD, 18));

        final int SHIFT=32;
        Map<String,Boolean> res = new HashMap<>();

        TabPane tabs = new TabPane();
        for( int b=0; b<255; b+=SHIFT ) {
            GridPane gridPane = new GridPane();
            gridPane.setGridLinesVisible(true);
            gridPane.setHgap(5);
            gridPane.setVgap(5);
            for (int r = 0; r < 255; r += SHIFT) {
                for (int g = 0; g < 255; g += SHIFT) {
                    String key = r+"_"+g+"_"+b;
                    res.put(key, false);
                    Rectangle rect = new Rectangle(90, 90, Color.rgb(r, g, b));
                    gridPane.add(rect, r / SHIFT, g / SHIFT);
                    rect.setOnMouseEntered(e -> {
                        rect.setRotate(10);
                        res.put(key, true);
                    });
                    rect.setOnMouseClicked(e -> {
                        rect.setRotate(0);
                        res.put(key, false);
                    });
                }
            }

            Tab tab = new Tab(""+b);
            tab.setContent(gridPane);
            tab.setClosable(false);
            tabs.getTabs().add(tab);
        }

        Button export = new Button("Export");
        export.setOnAction( (e) -> System.out.println(res));
        export.setStyle("-fx-font-size: 20pt");
        Scene scene = new Scene(new FlowPane(tabs, export)/*, 400, 100*/);
//        text.layoutXProperty().bind(scene.widthProperty().subtract(text.prefWidth(-1)).divide(2));
//        text.layoutYProperty().bind(scene.heightProperty().subtract(text.prefHeight(-1)).divide(2));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

