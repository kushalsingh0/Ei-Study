import java.util.ArrayList;
import java.util.List;
interface Observer {
    void update(String weatherConditions);
}

class WeatherForecast {
    private List<Observer> observers;
    private String weatherConditions;

    public WeatherForecast() {
        this.observers = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(weatherConditions);
        }
    }

    public void setWeatherConditions(String weatherConditions) {
        this.weatherConditions = weatherConditions;
        notifyObservers();
    }
}

class User implements Observer {
    @Override
    public void update(String weatherConditions) {
        System.out.println("Received update: " + weatherConditions);
    }
}

public class Observer_Pattern{
    public static void main(String[] args) {
        WeatherForecast weatherForecast = new WeatherForecast();
        User user = new User();
        weatherForecast.registerObserver(user);
        weatherForecast.setWeatherConditions("Sunny");
    }
}