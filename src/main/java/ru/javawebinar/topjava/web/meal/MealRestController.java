package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;


@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MealService service;

    public Meal save(Meal meal){
        meal.setId(null);
        log.info("Create {}", meal);
        return service.save(meal);

    }

    public void delete(int id){
        log.info("Delete {}", id);
        service.delete(id);
    }

    public Meal get(int id){
        log.info("Get {}", id);
        return service.get(id);
    }

    public void edit(Meal meal, int id) {
        log.info("Edit {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.edit(meal);
    }

    public List<MealWithExceed> getAll() {
        log.info("GetAll");
        return MealsUtil.getWithExceeded(service.getAll(), AuthorizedUser.getCaloriesPerDay());
    }

}