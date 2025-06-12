package api.project.habit_tracker.exeption;

public class HabbitExeption extends RuntimeException{

    public HabbitExeption(){
    }

    public HabbitExeption(String message){
        super(message);
    }

}
