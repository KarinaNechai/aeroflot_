package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.ICheckDao;
import com.github.nechai.aeroflot.model.Check;
import com.github.nechai.aeroflot.model.TypeOfCheck;

import java.util.*;
import java.util.stream.Collectors;

public class CheckDao implements ICheckDao {
    private static volatile CheckDao instance;
    private Map<Integer, Check> checks;

    private CheckDao() {
        this.checks = new HashMap<Integer, Check>();
    }

    public static CheckDao getInstance() {
        CheckDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CheckDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Check insert(Check check) {
        int number=checks.size()+1;
        while (checks.containsKey(number))
        {number++;}
        check.setNumber(number);
        checks.putIfAbsent(number, check);
        return check;
    }

    @Override
    public boolean update(Check check) {
        if (check ==null) return false;
        if (checks.get(check.getNumber())==null) return false;
        checks.replace(check.getNumber(), check);
        return true;
    }

    @Override
    public boolean delete(int checkN) {
        Check resultCheck =checks.get(checkN);
        if (resultCheck ==null) {
            return false;
        }
        else{
            resultCheck.setActual(false);
            return update(resultCheck);
        }
    }

    @Override
    public float totalByTypeOfCheck(List<Check> checkList, TypeOfCheck type) {
        float result=0;
        if (checkList!=null){
          result=  checkList.stream().filter(x->x.getTypeOfCheck()==type).map(x->x.getSum()).reduce((float) 0, Float::sum);
        }
        return result;
    }

    @Override
    public List<Check> getChecks(String userLogin) {
        return  checks.entrySet().stream().filter(x->x.getValue().getUserLogin().equals(userLogin)).
                filter(x->x.getValue().getIsActual()==true).map(x->x.getValue()).
                collect(Collectors.toList());
    }

    @Override
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, String userLogin) {
      return  checks.entrySet().stream().filter(x->x.getValue().getUserLogin().equals(userLogin)).
              filter(x->x.getValue().getIsActual()==true).
              filter(x->x.getValue().getDate().getTime()>=dateStart.getTime()).
              filter(x->x.getValue().getDate().getTime()<=dateEnd.getTime()).map(x->x.getValue()).
              collect(Collectors.toList());
    }

    @Override
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, TypeOfCheck type, String userLogin) {
        return  checks.entrySet().stream().filter(x->x.getValue().getUserLogin().equals(userLogin)).
                filter(x->x.getValue().getIsActual()==true).
                filter(x->x.getValue().getDate().getTime()>=dateStart.getTime()).
                filter(x->x.getValue().getDate().getTime()<=dateEnd.getTime()).
                filter(x->x.getValue().getTypeOfCheck().equals(type)).map(x->x.getValue()).collect(Collectors.toList());
    }
}
