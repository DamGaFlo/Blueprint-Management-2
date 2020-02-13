package edu.eci.arsw.blueprints.filtering.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filtering.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

/**
 * SubsamplingFilter
 */
@Component
public class SubsamplingFilter implements BlueprintFilter {
    public Set<Blueprint> filter(Set<Blueprint> bps) {
        Set<Blueprint> newBps= new HashSet<Blueprint>();
        for (Blueprint blueprint : bps) {
            List<Point> ps=new ArrayList<Point>();
            List<Point> sps=blueprint.getPoints();
            for (int i = 1; i < sps.size(); i+=2) ps.add(sps.get(i));
            Point[] points=new Point[ps.size()];
            for (int i = 0; i < ps.size(); i++) points[i]=ps.get(i);
            newBps.add(new Blueprint(blueprint.getAuthor(),blueprint.getName(),points));
        }
        return newBps;
    }
}