package edu.macalester.comp124.hw6;

import org.wikapidia.core.model.LocalPage;

/**
 * @author Shilad Sen
 */
public class LocalPagePopularity implements Comparable<LocalPagePopularity> {
    private LocalPage page;
    private int numInLinks;

    public LocalPagePopularity(LocalPage page, int numInLinks) {
        this.page = page;
        this.numInLinks = numInLinks;
    }

    public LocalPage getPage() {
        return page;
    }

    public int getPopularity() {
        return numInLinks;
    }

    @Override
    public int compareTo(LocalPagePopularity localPagePopularity) {
        if(localPagePopularity.getPopularity() < this.numInLinks){ return -1; }
        else if(localPagePopularity.getPopularity() == this.numInLinks){ return 0; }
        else { return 1; }
    }
}
