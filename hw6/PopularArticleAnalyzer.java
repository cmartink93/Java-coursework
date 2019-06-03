package edu.macalester.comp124.hw6;

import org.wikapidia.conf.ConfigurationException;
import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.lang.Language;
import org.wikapidia.core.model.LocalPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Analyzes the overlap in popular concepts.
 * Experimental code for Shilad's intro Java course.
 * Note that you MUST correct WikAPIdiaWrapper.DATA_DIRECTORY before this works.
 *
 * @author Shilad Sen
 */
public class PopularArticleAnalyzer {
    private final WikAPIdiaWrapper wpApi;


    /**
     * Constructs a new analyzer.
     * @param wpApi
     *
     */
    public PopularArticleAnalyzer(WikAPIdiaWrapper wpApi) {
        this.wpApi = wpApi;

    }

    /**
     * Returns the n most popular articles in the specified language.
     * @param language
     * @param n
     * @return
     */
    public List<LocalPage> getMostPopular(Language language, int n) {

        List<LocalPage> pages = wpApi.getLocalPages(language);
        List<LocalPagePopularity> pop = new ArrayList<LocalPagePopularity>();
        List<LocalPage> top = new ArrayList<LocalPage>();
        for(LocalPage pg : pages){
            LocalPagePopularity lppop = new LocalPagePopularity(pg, wpApi.getNumInLinks(pg));
            pop.add(lppop);
        }
        Collections.sort(pop);
        Iterator iter = pop.iterator();
        int counter = 1;
        while(iter.hasNext()){
            if(counter == n){ break; }
            LocalPagePopularity lppop = (LocalPagePopularity) iter.next();
            LocalPage page = lppop.getPage();
            top.add(page);
            counter++;
        }
        return top;
    }


    public static void main(String args[]) {
        Language simple = Language.getByLangCode("simple");

        // Change the path below to point to the parent directory on the lab computer
        // or laptop that holds the BIG "db" directory.
        WikAPIdiaWrapper wrapper = new WikAPIdiaWrapper();
        PopularArticleAnalyzer paa = new PopularArticleAnalyzer(wrapper);
        List<LocalPage> popList = paa.getMostPopular(simple, 20);
        //for(LocalPage p: popList) {System.out.println(p); }


    }
}
