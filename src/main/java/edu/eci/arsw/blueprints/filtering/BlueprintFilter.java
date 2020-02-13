package edu.eci.arsw.blueprints.filtering;

import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;

/**
 * BlueprintFilter
 */
public interface BlueprintFilter {

    public Set<Blueprint> filter(Set<Blueprint> bps);
}