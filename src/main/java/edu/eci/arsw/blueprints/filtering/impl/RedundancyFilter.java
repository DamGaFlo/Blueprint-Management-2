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
 * RedundancyFilter
 */
@Component
public class RedundancyFilter implements BlueprintFilter {

    public Set<Blueprint> filter(Set<Blueprint> bps) {
        Set<Blueprint> bpst = new HashSet<Blueprint>();
        for (Blueprint bp : bps) {
            List<Point> pnts = new ArrayList<Point>();
            List<Point> pbp=bp.getPoints();
            for (int i = 0;i<pbp.size();i++) {
                boolean flag=true;
                for(int j=0;flag && j<pnts.size();j++){
                    if(pbp.get(i).equals(pnts.get(j))){
                        flag=false;
                    }
                }
                if(flag)pnts.add(pbp.get(i));
            }
            Point[] pts=new Point[pnts.size()];
            int i=0;
            for (Point pnt : pnts) {
                pts[i]=pnt;
                i++;
            }
            bpst.add(new Blueprint(bp.getAuthor(),bp.getName(),pts));
        }
        return bpst;
    }

}