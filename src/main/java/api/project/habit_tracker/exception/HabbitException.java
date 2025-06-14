package api.project.habit_tracker.exception;

public class HabbitException extends RuntimeException{

    public HabbitException(){
    }

    public HabbitException(String message){
        super(message);
    }

}
