import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Simple_Arithmetic_Calculator extends Application
{
    static int number;
    Stage window;
    static String currentResult;
    Label result;
    public static void main(String args[])
    {
        currentResult = " ";
       
        launch();
        System.out.println("Program Ended");
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Simple Calculator - JavaFX");
        
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        //
        /* ELEMENTS */
                
        //Calculate -> '=' button
        Button calculate = new Button(" = ");
        GridPane.setConstraints(calculate,1,4);
        calculate.setOnAction( (e) -> result.setText(currentResult = evaluate() ) );
        //
        
        // Divide button
        Button divide = new Button(" /  ");
        GridPane.setConstraints(divide,3,1);
        divide.setOnAction( (e)->result.setText(currentResult+="/") );
        
        //
        
         // Multiply button
        Button multiply = new Button(" *  ");
        GridPane.setConstraints(multiply,3,2);
        multiply.setOnAction( (e)->result.setText(currentResult+="*") );
        //
        
         // Add button
        Button add = new Button(" + ");
        GridPane.setConstraints(add,3,3);
        add.setOnAction( (e)->result.setText(currentResult+="+") );
        //
        
         // Subtract button
        Button subtract = new Button(" -  ");
        GridPane.setConstraints(subtract,3,4);
        subtract.setOnAction( (e)->result.setText(currentResult+="-") );
        //
        
         // Zero button - WIDE
        Button zero = new Button(" 0 ");
        GridPane.setConstraints(zero,0,4);
        zero.setOnAction( (e)->result.setText(currentResult+="0") );
        //
        
        // Decimal Point button
        Button dot = new Button(" .  ");
        GridPane.setConstraints(dot,2,4);
        dot.setOnAction( (e)->result.setText(currentResult+=".") );
        
        //Left Bracket button
        Button leftbracket = new Button(" ( ");
        GridPane.setConstraints(leftbracket,0,5);
        leftbracket.setOnAction( (e)->result.setText(currentResult+="(") );

        //Left Bracket button
        Button rightbracket = new Button(" ) ");
        GridPane.setConstraints(rightbracket,1,5);
        rightbracket.setOnAction( (e)->result.setText(currentResult+=")") ); 
        
        //Clear button
        Button clear = new Button(" C ");
        GridPane.setConstraints(clear,2,5);
        clear.setOnAction( (e)->result.setText(currentResult="") );  
        
        //Delete button
        Button delete = new Button("Del");
        GridPane.setConstraints(delete,3,5);
        delete.setOnAction( (e)->result.setText(currentResult=currentResult.length()==0?"":currentResult.substring(0,currentResult.length()-1)) );  
        //
        
        
        Label result_label = new Label("<Result>");
        GridPane.setConstraints(result_label,5,0);
        
        result = new Label(currentResult);
        GridPane.setConstraints(result,5,1);
        
        grid.getChildren().addAll(delete,clear,leftbracket,rightbracket,result,result_label,calculate,divide,multiply,subtract,add,zero,dot);
        // 1 to 9 digit buttons
        Button numbers[] = new Button[9];
        number = 0;
        for(int i=1;i<=3;i++)
        {
            for(int j=0;j<=2;j++)
            {
                numbers[number] = new Button(" "+Integer.toString(number+1)+ " ");   
                GridPane.setConstraints(numbers[number],j,i);
                grid.getChildren().add(numbers[number]);
                number++;
            }
        }
        
        
            numbers[0].setOnAction ( (e)->result.setText(currentResult+= 1 ) );
            numbers[1].setOnAction ( (e)->result.setText(currentResult+= 2 ) );
            numbers[2].setOnAction ( (e)->result.setText(currentResult+= 3 ) );
            numbers[3].setOnAction ( (e)->result.setText(currentResult+= 4 ) );
            numbers[4].setOnAction ( (e)->result.setText(currentResult+= 5 ) );
            numbers[5].setOnAction ( (e)->result.setText(currentResult+= 6 ) );
            numbers[6].setOnAction ( (e)->result.setText(currentResult+= 7 ) );
            numbers[7].setOnAction ( (e)->result.setText(currentResult+= 8 ) );
            numbers[8].setOnAction ( (e)->result.setText(currentResult+= 9 ) );
            
        //
        
        //result.setText("Hello");
        Scene scene = new Scene(grid, 400, 200);
        window.setScene(scene);
        window.show();
    }

   public static String evaluate()
   {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        
        try
        {
        Object answer  = engine.eval(currentResult);
        return answer.toString();
        }catch(Exception exception)
        {
            System.out.println(exception);
        }
        
        return "Error Ocurred";
   }

    
}
