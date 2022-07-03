package com.hrj.dto;

import com.hrj.entity.Setmeal;
import com.hrj.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
