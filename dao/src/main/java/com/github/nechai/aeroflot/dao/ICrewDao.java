package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Crew;

public interface ICrewDao{
    boolean insert (Crew crew);
    Crew getCrewById(int crewId);
    boolean delete(int crewId);
    boolean update(Crew crew);
}
