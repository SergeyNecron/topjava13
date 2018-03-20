package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetween;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> mealsWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        mealsWithExceeded.forEach(System.out::println);
        mealsWithExceeded = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsWithExceeded.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final Map<LocalDate, Integer> map = new HashMap<>();
        meals.forEach(meal -> map.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<UserMealWithExceed> rezult = new ArrayList<>();

        meals.forEach(meal -> {

            if (isBetween(meal.getTime(), startTime, endTime))
                rezult.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), map.get(meal.getDate()) > caloriesPerDay));
        });
        return rezult;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories))
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal ->
                        new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                                caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
